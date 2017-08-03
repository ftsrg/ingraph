(ns sre.compiler
  (:require
    [cats.monad.either :refer :all]
    [clojure.set :refer :all]
    [clojure.algo.generic.functor :refer :all]
    [sre.op :as op]
    [sre.constraint :as constraint]
    [clojure.pprint :refer :all]))

(defrecord BindingBranchNode [op var-lkp todo done])

(defn branch-constr [constr-lkp ^BindingBranchNode stump]
  (let [[[type props] & rest-constr] (:todo stump)
        var-lkp (:var-lkp stump)
        cond (:cond props)]
    (loop [branches ()
           [first & rest] (into () ((constr-lkp (nth cond 1)) type))]
      (if-not (some? first)
        ; every valid branch of this condition type has been already created
        branches
        ; try to create a branch
        (let [bindings (zipmap (:params props) first)
              conflict? (some (fn [[k v]]
                                (and (some? (var-lkp k))
                                     (not= (var-lkp k) v))) bindings)]
          (if conflict?
            ; conflict while matching variables
            (recur branches rest)
            ; create branch
            (let [new-branch
                  (-> stump
                      ; remove constraint from to-do list
                      ; note that :n is not maintained. We don't have rely on it apart from determining
                      ; the initial order, which we don't have to change even if :n is decremented,
                      ; as the potentially modified items will be already on the top. Moreover, reaching an
                      ; unsatisfiable state by hitting :n = 0 can be determined by counting constr-lkp-types
                      ; immediately in the next turn whose performance overhead is negligible.
                      (assoc-in [:todo] rest-constr)
                      ; add to done list
                      (update-in [:done] #(cons [(nth cond 1) type first] %1))
                      ; merge its variables with already resolved ones for fast lookup
                      (update-in [:var-lkp] #(merge %1 bindings)))]
              (recur (cons new-branch branches) rest))))))))

(defn expand-implications [constr-defs]
  (apply union (map sre.constraint/implies* constr-defs)))

(defn compare-constraint-descriptors
  "Compares two constraint descriptors by count ASC > arity DESC > type ASC > everything else
  Last two needed for differentiating to prevent deduping in sorted sets. The resulting
  order gives a fairly good satisfaction sequence"
  [[a-k a-v] [b-k b-v]]
  (let [count-cmp (compare (:n a-v) (:n b-v))]
    (if (= count-cmp 0)
      (let [arity-cmp (compare (:arity a-v) (:arity b-v))]
        (if (= arity-cmp 0)
          (or (not= a-k b-k) (not= a-v b-v))
          (- arity-cmp)))
      count-cmp)))

(defn- lkp-map [f constr-lkp]
  (reduce-kv (fn [a k v] (assoc a k (fmap (fn [v] (f v k)) v))) {} constr-lkp))

(defn- lkp-to-match-list [lkp]
  (apply (partial sorted-set-by compare-constraint-descriptors)
         (mapcat (fn [[t b]] (fmap (fn [x] [t x]) b)) (into () lkp))))

(defn build-match-list [op constr-counts condition]
  (lkp-to-match-list
    (lkp-map #(hash-map :n (let [constr-count ((constr-counts (nth condition 1)) %2)]
                             (if (nil? constr-count) 0 constr-count))
                        :arity (:arity (deref %2))
                        :params %1
                        :cond condition)
             (op (nth condition 0)))))

(defn bind-op
  "Selects all applicable operation bindings for the given constraints and condition criteria. The
  condition criteria should be passed as a vector in `condition-types` containing either :free, :bound or both.
  Specifying :free matches post-conditions so the result will contain non-past operations, while binding
  :bound matches pre-conditions resulting in non-future operations. This means that matching both will result in
  present operations. You can use bind-op incrementally, ie. (bind-op MyOp var-lkp constr-lkp [:free]),
  then use the resulting descriptor to (bind-op MyOp modified-var-lkp constr-lkp [:bound])
  later."
  [op var-lkp constr-lkp condition-types]
  (let [conditions (map #(case %1 :free [:satisfies :free]
                                  :bound [:requires :bound]) condition-types)]
    ; this check is not required, it serves as an optimization
    ; for exiting as early as possible in this highly likely situation
    (if-not (reduce #(and %1 (subset?
                               (keys (op (nth %2 0)))
                               (into #{} (keys (constr-lkp (nth %2 1)))))) conditions)
      ()
      (let [constr-counts (map conditions)
            sorted-constr (apply union (map #(build-match-list op
                                                               ; this is shady but we only replace the constraints with their counts
                                                               ; on the respective map
                                                               (update-in constr-lkp [(nth %1 1)] (fn [m] (fmap count m)))
                                                               %1) conditions))]
        (loop [open (list (map->BindingBranchNode {:op      op
                                                   :var-lkp var-lkp
                                                   :todo    (into () sorted-constr) ; initially everything is on the to-do list
                                                   :done    ()})) ; and nothing is done
               result ()]
          (let [[first-open & rest-open] open]
            (if-not (some? first-open)
              ; no unvisited branches, we are ready
              result
              ; get the first constraint to be matched
              (let [[first-constr rest-constr] (:todo first-open)]
                (if-not (some? first-constr)
                  ; every constraint has already been matched
                  (recur rest-open (cons first-open result))
                  ; continue matching
                  (recur (concat (branch-constr constr-lkp first-open) rest-open) result))))))))))

(defrecord SearchPlanCell [cost ops constr-lkp free-count non-past-ops])

(defn- bind-constr [constr-lkp [_ type constr]]
  ; note that we don't use the first parameter, we expect it to be :free
  (-> constr-lkp
      (update-in [:free] #(if (= 1 (count (%1 type)))
                            (dissoc %1 type)
                            (update-in %1 [type] (fn [s] (disj s constr)))))
      (update-in [:bound] (fn [map] (merge-with #(into [] (concat %1 %2)) {type [constr]} map))))
  )

(defn- bind-free [ops constr-lkp]
  (mapcat #(bind-op %1 {} constr-lkp [:free]) ops))

(defn compare-search-plan-cell-by-cost [a b]
  ; we will always compare candidates with the most costly upon insert so order descending
  (let [cost-cmp (* -1 (compare (:c (:cost a)) (:c (:cost b))))]
    ; once again we have to differentiate between every entry else they will be deduplicated
    (if (= cost-cmp 0)
      (not= a b)
      cost-cmp)))

(defn search-plan-step [plans ^SearchPlanCell cell ^Integer k]
  ; the idea here is that we have operations in non-past-ops that may or may not be applicable at the moment
  ; because we haven't checked them for required constraints. so first figure out the applicable ops
  (let [cost-binding-pairs (for [non-past-binding (:non-past-ops cell)
                                 ; remember that for bindings will result in a mapcat (just like in Scala)
                                 ; sou using this can look weird but generally a single non-past op
                                 ; can branch into more than one present op because
                                 ; required constraints have not been checked
                                 present-binding (bind-op (:op non-past-binding)
                                                          (:var-lkp non-past-binding)
                                                          (:constr-lkp cell)
                                                          [:bound])
                                 ; replace the things this operation will do if we
                                 ; eventually choose it
                                 ; note that we can do this instead of concating because only
                                 ; free constraints are moved (binding an op
                                 ; for required constraints always results in an empty list)
                                 :let [present-binding (assoc-in present-binding [:done] (:done non-past-binding))]
                                 ; the cost-fn requires that the op is properly bound to vars, so we do that here
                                 ; yeah, it smells that we have two different representations for the damn same thing
                                 ; so refactor once you have the fatigue
                                 :let [bound (op/bind-map (:op present-binding) (:var-lkp present-binding))
                                       ; TODO need to find out a way to get stats about the variables
                                       weight (op/weight bound {})]]
                             [weight present-binding bound])]
    ; go through all applicable ops and try to fit them somewhere in our table.
    (reduce (fn [plans [weight present-binding bound]]
              ; we have to find out how many constraints are getting bound because that determines the first index.
              ; it should be equal to the length of the done list as everything in there is a single newly bound
              ; constraint
              (let [column (- (:free-count cell) (count (:done present-binding))) ; 3:9
                    p-next (* (-> cell :cost :p) weight)
                    c-next (+ (-> cell :cost :c) p-next)
                    ; for the row we only need to determine whether it fits into the k best or not
                    fits? (or
                            (< (count (get plans column)) k)
                            (< c-next (-> (get plans column) first :cost :c)))]
                (if fits?
                  ; if it fits insert
                  (let [next-constr-lkp (reduce bind-constr (:constr-lkp cell) (:done present-binding))
                        cell (map->SearchPlanCell {:cost         {:c c-next :p p-next}
                                                   :ops          (cons bound (:ops cell))
                                                   :constr-lkp   next-constr-lkp
                                                   :free-count   column
                                                   :non-past-ops (filter (fn [x] (every? #(subset? #{(nth %1 2)}
                                                                                                   (get-in next-constr-lkp
                                                                                                           [(nth %1 0) (nth %1 1)])
                                                                                                   )
                                                                                         (:done x)))
                                                                         (:non-past-ops cell))})
                        plans (update-in plans [column] #(conj (if (nil? %1) (sorted-set-by compare-search-plan-cell-by-cost) %1) cell))]
                    plans)
                  ; else don't modify plans
                  plans)))
            plans
            cost-binding-pairs)))

(defn calculate-search-plan
  "Algorithm that calculates the search plan (list of operations) for the set of constraints given in constr-lkp. Implementation
  based on \"An algorithm for generating model-sensitive search plans for pattern matching on EMF models\" paper bt G. Varro et al,
  although there are notable differences.
  The most prominent difference is that here an operations isn't a bound version of a constraints in the sense that
  every operation is a constraint with a so called adornment (specifying for each variable whether it is free or bound).
  "
  [ops constr-lkp ^Integer k]
  ; comments starting with x:y mark that the line corresponds to the algorithm
  ; listing `x` line `y` from the above mentioned paper.
  ; it hopefully gives a good reference what the code does.
  ; e.g. 3:1 -> Algorithm 3 (The procedure calculateSearchPlan(...), line 1
  (let [n (reduce-kv #(+ %1 (count %3)) 0 (:free constr-lkp))] ; 3:1 set n
    (loop [plans (sorted-map-by > n (sorted-set-by
                                      compare-search-plan-cell-by-cost
                                      (map->SearchPlanCell {:cost         {:c 0 ; 3:2
                                                                           :p 1}
                                                            :ops          ()
                                                            :constr-lkp   constr-lkp
                                                            :free-count   (reduce-kv #(+ %1 (count %3)) 0 (:free constr-lkp))
                                                            :non-past-ops (bind-free ops constr-lkp)})))]
      (if-let [keys (keys plans)]
        (if (= [0] keys)
          (right (plans 0))                                 ; 3:18 - ready. return best k plans
          (let [j (first keys)]
            (let [column (get plans j)
                  plans (as-> plans x
                              (dissoc x j)
                              (reduce #(search-plan-step %1 %2 k) x column))] ; 3.4
              (recur plans))))
        (left :no-solution)))))

