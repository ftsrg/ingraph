(ns sre.plan.compiler
  "Cost-based search plan compiler related functions, types etc."
  (:require [cats.core :as m]
            [cats.monad
             [exception :as exc]
             [maybe :as maybe]]
            [clojure
             [pprint :refer :all]
             [set :refer :all]]
            [clojure.algo.generic.functor :refer :all]
            [sre.core :refer :all]
            [sre.plan
             [config :as config]
             [constraint :as constraint]
             [estimation :as estimation]
             [lookup :as lookup]]
            [taoensso.tufte :as profiler :refer [defnp]])
  (:import [java.io BufferedReader StringReader]
           sre.plan.config.IConfig
           [sre.plan.estimation Cost Weight]
           sre.plan.lookup.ConstraintLookup))

(defrecord BindingBranchNode [op var-lkp todo done])

(defnp branch-constr [constr-lkp ^BindingBranchNode stump]
  (let [[[type props] & rest-constr] (:todo stump)
        var-lkp (:var-lkp stump)
        cond (:cond props)]
    (loop [branches ()
           [first & rest] (into () (get (lookup/lookup-by-type constr-lkp (nth cond 1)) type))]
      (if-not (some? first)
        ;; every valid branch of this condition type has been already created
        branches
        ;; try to create a branch
        (let [bindings (zipmap (:params props) first)
              conflict? (some (fn [[k v]]
                                (and (some? (var-lkp k))
                                     (not= (var-lkp k) v))) bindings)]
          (if conflict?
            ;; conflict while matching variables
            (recur branches rest)
            ;; create branch
            (let [new-branch
                  (-> stump
                      ;; remove constraint from to-do list
                      ;; note that :n is not maintained. We don't have rely on it apart from determining
                      ;; the initial order, which we don't have to change even if :n is decremented,
                      ;; as the potentially modified items will be already on the top. Moreover, reaching an
                      ;; unsatisfiable state by hitting :n = 0 can be determined by counting constr-lkp-types
                      ;; immediately in the next turn whose performance overhead is negligible.
                      (assoc-in [:todo] rest-constr)
                      ;; add to done list
                      (update-in [:done] #(cons [(nth cond 1) type first] %1))
                      ;; merge its variables with already resolved ones for fast lookup
                      (update-in [:var-lkp] #(merge %1 bindings)))]
              (recur (cons new-branch branches) rest))))))))

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

(defn- map-by-type [f constr-by-type]
  (reduce-kv (fn [a k v] (assoc a k (fmap (fn [v] (f v k)) v))) {} constr-by-type))

(defnp lkp-to-match-list [lkp]
  (reduce-kv (fn [set t bs] (apply (partial conj set)
                                   (map #(vector t %) bs)))
             (sorted-set-by compare-constraint-descriptors) lkp))

(defnp build-match-list [op constr-counts condition]
  (lkp-to-match-list
   (map-by-type #(array-map :n (let [c (constr-counts %2)] (case c nil 0 c))
                            :arity (:arity %2)
                            :params %1
                            :cond condition)
                (get op (nth condition 0)))))

(defnp bind-op
  "Selects all applicable operation bindings for the given op for the constraints in the lookup specified by condition-types.
  The condition types should be passed as a vector in `condition-types` containing either :free, :bound or both.
  Specifying :free matches post-conditions so the result will contain non-past operations, while binding
  :bound matches pre-conditions resulting in non-future operations. This means that matching both will result in
  present operations. You can use bind-op incrementally, ie. (bind-op MyOp var-lkp constr-lkp [:free]),
  then use the resulting descriptor to (bind-op MyOp modified-var-lkp constr-lkp [:bound])
  later."
  [op var-lkp constr-lkp condition-types]
  (let [conditions (->> condition-types (map #(case % :free [:satisfies :free] :bound [:requires :bound])))
        sorted-constr (apply union (map #(build-match-list op
                                                           (fmap count (lookup/lookup-by-type constr-lkp (nth % 1)))
                                                           %) conditions))]
    (loop [open (list (map->BindingBranchNode {:op      op
                                               :var-lkp var-lkp
                                               :todo    (into () sorted-constr) ; initially everything is on the to-do list
                                               :done    ()})) ; and nothing is done
           result ()]
      (let [[first-open & rest-open] open]
        (if-not (some? first-open)
          ;; no unvisited branches, we are ready
          result
          ;; get the first constraint to be matched
          (let [[first-constr rest-constr] (:todo first-open)]
            (if-not (some? first-constr)
              ;; every constraint has already been matched
              (recur rest-open (cons first-open result))
              ;; continue matching
              (recur (concat (branch-constr constr-lkp first-open) rest-open) result))))))))

(defrecord SearchPlanCell [^Cost cost-calculator
                           ^Weight weight-calculator
                           ops
                           ^ConstraintLookup constr-lkp
                           free-count
                           non-past-op-bindings
                           non-past-op-bindings-immediate])

(defrecord SearchPlanColumn [ordered-cells cells-by-free])

(defn- bind-postcond [ops constr-lkp]
  (mapcat #(bind-op % {} constr-lkp [:free]) ops))

(defn compare-search-plan-cell-by-cost [a b]
  (let [cost-cmp (compare (:cost-calculator b) (:cost-calculator a))]
    (if (= cost-cmp 0)
      (not= a b)
      cost-cmp)))

(defn- satisfiable? [constr-lkp non-past-op-binding]
  (every? #(subset? #{(nth % 2)}
                    (get (lookup/lookup-by-type constr-lkp (nth % 0))
                         (nth % 1)))
          (:done non-past-op-binding)))

(defn create-search-plan-cell [index cost-calculator weight-calculator bound cell constr-lkp]
  (map->SearchPlanCell {:cost-calculator      cost-calculator
                        :weight-calculator    weight-calculator
                        :ops                  (conj (:ops cell) bound)
                        :constr-lkp           constr-lkp
                        :free-count           index
                        :non-past-op-bindings (filter (partial satisfiable? constr-lkp) (:non-past-op-bindings cell))
                        :<non-past-op-bindings< (filter (partial satisfiable? constr-lkp) (:<non-past-op-bindings< cell))
                        :>non-past-op-bindings> (filter (partial satisfiable? constr-lkp) (:>non-past-op-bindings> cell))}))

(defnp insert-cell [cell k plans [weight present-binding op-binding]]
  ;; we have to find out how many constraints are getting bound because that determines the first index.
  ;; it should be equal to the length of the done list as everything in there is a single newly bound
  ;; constraint
  (let [index             (- (:free-count cell) (count (:done present-binding))) ; 3:9
        column            (get plans index)
        ordered-cells     (get column :ordered-cells)
        cost-calculator   (estimation/update-cost (:cost-calculator cell) weight)
        weight-calculator (:weight-calculator cell)
        constr-lkp        (reduce lookup/bind (:constr-lkp cell) (map (fn [[_ type bindings]]
                                                                        (constraint/->ConstraintBinding type bindings))
                                                                      (:done present-binding)))
        free              (lookup/lookup constr-lkp :free)
        ;; determine duplicate (if any)
        dupe              (get-in column [:cells-by-free free])
        ;; determine whether it fits into the k best or not (only needed if not a dupe actually)
        full?             (>= (count ordered-cells) k)]
    (cond
      (some? dupe) (if (< (compare cost-calculator (:cost-calculator dupe)) 0)
                     ;; this is better, evict dupe
                     (let [cell (create-search-plan-cell index cost-calculator
                                                         weight-calculator op-binding
                                                         cell constr-lkp)]
                       (-> plans
                           (update-in [index :ordered-cells] #(-> % (disj dupe) (conj cell)))
                           (assoc-in [index :cells-by-free free] cell)))
                     ;; existing is better, so don't modify the plan
                     plans)
      ;; we can fit it in somehow, either by consuming an empty slot, or replacing the worst
      (or (not full?)
          (< (compare cost-calculator (-> ordered-cells first :cost-calculator)) 0))
      (let [cell (create-search-plan-cell index cost-calculator
                                          weight-calculator op-binding
                                          cell constr-lkp)]
        (if (not full?)
          ;; using up an empty slot
          (-> plans
              ;; yuck. the set might not even exist at this point.
              (update-in [index :ordered-cells]
                         #(conj (or % (sorted-set-by compare-search-plan-cell-by-cost)) cell))
              (assoc-in [index :cells-by-free free] cell))
          ;; replacing the worst
          (let [evicted (-> ordered-cells first)]
            (-> plans
                ;; get rid of it from the cells-by-free map
                (update-in [index :cells-by-free] #(dissoc %1 (-> evicted :constr-lkp (lookup/lookup :free))))
                ;; get rid of it from the ordered cells set
                (update-in [index :ordered-cells] #(disj %1 evicted))
                ;; add new item to ordered cells set
                (update-in [index :ordered-cells] #(conj %1 cell))
                ;; add new item to cells-be-free map
                (assoc-in [index :cells-by-free free] cell)))))
      :else        plans)))

(defn bind-present [config cell non-past-op-bindings]
  (for [non-past-binding non-past-op-bindings
        ;; remember that for bindings will result in a mapcat (just like in Scala)
        ;; so using this can look weird but generally a single non-past op
        ;; can branch into more than one present op because
        ;; required constraints have not been checked
        present-binding  (bind-op (:op non-past-binding)
                                  (:var-lkp non-past-binding)
                                  (:constr-lkp cell)
                                  [:bound])
        ;; replace the things this operation will do if we
        ;; eventually choose it
        ;; note that we can do this instead of concating because only
        ;; free constraints are moved (binding an op
        ;; for required constraints always results in an empty list)
        :let             [present-binding (assoc-in present-binding [:done] (:done non-past-binding))]
        ;; the cost-fn requires that the op is properly bound to vars, so we do that here
        ;; yeah, it smells that we have two different representations for the damn same thing
        ;; so refactor once you have the fatigue
        :let             [op-binding (bind-map (:op present-binding) (:var-lkp present-binding))
                          weight (estimation/get-weight (:weight-calculator cell) config op-binding (:constr-lkp cell))]]
    [weight present-binding op-binding]))

(defn step [^Integer k ^IConfig config plans ^SearchPlanCell cell]
  (maybe/from-maybe
   ;; try and bind an immediate step. look how cool it is that I can use bind-present here!
   ;; It is because for is actually lazy
   (m/mlet [immediate-binding (maybe/seq->maybe (bind-present config cell (:<non-past-op-bindings< cell)))]
           (m/return (insert-cell cell k plans immediate-binding)))
   ;; no immediate step, default to normal action (from-maybe option default) is similar to Scala option.getOrElse(default))
   ;; the idea here is that we have operations in non-past-op-bindings that may or may not be applicable at the moment
   ;; because we haven't checked them for required constraints. so first figure out the applicable ops
   (let [regular-bindings (bind-present config cell (:non-past-op-bindings cell))]
     (if (seq regular-bindings) ; this is the idiomatic way in Clojure to check for a non-empty sequence (http://clojuredocs.org/clojure.core/empty_q)
       ;; insert regular steps
       (reduce (partial insert-cell cell k) plans regular-bindings)
       ;; if no regular steps are available, try inserting a deferred step
       (maybe/from-maybe (m/mlet [deferred-binding (maybe/seq->maybe (bind-present config cell (:>non-past-op-bindings> cell)))]
                                 (m/return (insert-cell cell k plans deferred-binding)))
                         plans)))))

(defnp run
  "Algorithm that calculates the search plan (list of operations) for the set of constraints given in constr-lkp. Implementation
  based on \"An algorithm for generating model-sensitive search plans for pattern matching on EMF models\" paper bt G. Varro et al,
  although there are notable differences.
  The most prominent difference is that here operations and constraints are separate things. The algorithm tries to find a sequence
  of operations that satisfy the given constraints.
  Another difference is the generalized cost-calculator. While the original article was very specific about how to calculate costs
  it hinted that the only requirement is that it should be incrementally computable. Thus here the cost calculator should be
  supplied can be supplied by the invoker."
  [^ConstraintLookup constr-lkp
   ^IConfig config {^Cost cost-calculator     :cost-calculator
                    ^Weight weight-calculator :weight-calculator
                    ^Integer k                :k
                    :or                       {cost-calculator   estimation/default-cost-calculator
                                               weight-calculator estimation/default-weight-calculator
                                               k                 10}}]
  ;; comments starting with x:y mark that the line corresponds to the algorithm
  ;; listing `x` line `y` from the above mentioned paper.
  ;; it hopefully gives a good reference what the code does.
  ;; e.g. 3:1 -> Algorithm 3 (The procedure calculateSearchPlan(...), line 1
  (let [ops                      (config/operations config)
        n                        (count (lookup/lookup constr-lkp :free))
        step                     (partial step k config)
        {immediate :immediate regular :regular deferred :deferred} (->> ops (group-by :disposition))
        cell                     (map->SearchPlanCell {:cost-calculator        cost-calculator
                                                       :weight-calculator      weight-calculator
                                                       :ops                    []
                                                       :constr-lkp             constr-lkp
                                                       :free-count             n
                                                       :non-past-op-bindings   (bind-postcond regular constr-lkp)
                                                       :<non-past-op-bindings< (bind-postcond immediate constr-lkp)
                                                       :>non-past-op-bindings> (bind-postcond deferred constr-lkp)})] ; 3:1 set n
    (loop [i     0
           plans (sorted-map-by > n (map->SearchPlanColumn {:ordered-cells (sorted-set-by compare-search-plan-cell-by-cost cell)
                                                            :cells-by-free {(lookup/lookup constr-lkp :free) cell}}))]
      (if-let [keys (keys plans)]
        (if (= [0] keys)
          (exc/success (-> plans (get 0) :ordered-cells first)) ; 3:18 - ready. return best plan
          (let [j (first keys)]
            (let [column (get plans j)
                  plans  (as-> plans x
                               (dissoc x j)
                               (reduce #(step %1 %2) x (:ordered-cells column)))] ; 3.4
              (recur (inc i) plans))))
        (let [reader (fn [str] (BufferedReader. (StringReader. str)))
              free   (with-out-str (pprint (lookup/lookup constr-lkp :free)))
              bound  (with-out-str (pprint (lookup/lookup constr-lkp :bound)))]
          (exc/failure (Exception. (str "no solution. Gave up after " i " steps"
                                        "\nsatisfying\n"
                                        (apply str (interpose "\n"
                                                              (for [line (line-seq (reader free))] (str "  " line))))
                                        "\nfrom\n"
                                        (apply str (interpose "\n"
                                                              (for [line (line-seq (reader bound))] (str "  " line))))))))))))
