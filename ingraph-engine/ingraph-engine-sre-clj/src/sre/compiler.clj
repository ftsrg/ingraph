(ns sre.compiler
  (:require
    [clojure.set :refer :all]
    [clojure.algo.generic.functor :refer :all]
    [sre.op :as op]
    [sre.constraint :as constraint]))

(defn branch-constr [stump]
  (let [[[type props] & rest-constr] (:match-list stump)
        var-lkp (:var-lkp stump)
        cond (:cond props)
        constr-lkp (:constr-lkp stump)]
    (loop [branches ()
           [first & rest] ((constr-lkp (nth cond 1)) type)]
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
                      ; as the potential modified items will be already on the top. Moreover, reaching an
                      ; unsatisfiable state by hitting :n = 0 can be determined by counting constr-lkp-types
                      ; immediately in the next turn whose performance overhead is negligible.
                      (update-in [:match-list] (constantly rest-constr))
                      ; merge its variables with already resolved ones
                      (update-in [:var-lkp] #(merge %1 bindings))
                      ; move post-condition from free constraints to bound ones
                      ((fn [branch]
                         (case (nth cond 0)
                           :requires branch
                           :satisfies (->
                                        branch
                                        (update-in
                                          [:constr-lkp :free]
                                          (constantly
                                            (if (= 1 (count ((:free constr-lkp) type)))
                                              ; no more constraints of this type, so remove
                                              ; completely from the lookup
                                              (dissoc (:free constr-lkp) type)
                                              ; remove constraint from set of bindings
                                              (update-in (:free constr-lkp) [type] clojure.core/rest))))
                                        (update-in
                                          [:constr-lkp :bound]
                                          (fn [map]
                                            (merge-with #(into [] (concat %1 %2)) {type [first]} map))))))))]
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
  then use the resulting descriptor to (bind-op MyOp modified-var-lkp modified-constr-lkp [:bound])
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
                                                               (update-in constr-lkp [(nth %1 1)] (fn [m] (fmap count m)))
                                                               %1) conditions))]
        (loop [open (list {:var-lkp    var-lkp
                           :constr-lkp constr-lkp
                           :match-list (into () sorted-constr)})
               result ()]
          (let [[first-open & rest-open] open]
            (if-not (some? first-open)
              ; no unvisited branches, we are ready
              result
              ; get the first constraint to be matched
              (let [[first-constr rest-constr] (:match-list first-open)]
                (if-not (some? first-constr)
                  ; every constraint has already been matched
                  (recur rest-open (cons first-open result))
                  ; continue matching
                  (recur (concat (branch-constr first-open) rest-open) result))))))))))

;(defn plan-iteration [ops bound-constr-lkp free-constr-lkp]
;  (let [applicable-ops (map #(bind-op %1 bound-constr-lkp free-constr-lkp) ops)]
;    ()))
;
;(defn dyn-plan-step
;  "Progresses one step in the main search planning algorithm"
;  [ops bound-constr-lkp free-constr-lkp]
;  (as-> ops xs
;        (mapcat (fn [x] (bind-op x bound-constr-lkp free-constr-lkp)) xs)
;        (map (fn [x] {:op x :cost }))))
;
;(defn plan [ops constr-lkp]
;  "Main search planning algorithm. Binds some sequence of operations from the given `ops`
;  to satisfy all constraints supplied in `constr-lkp`."
;  (loop [bound-constr-lkp {}
;         free-constr-lkp constr-lkp])
;  )

;(defn plan [ops constr-lkp k]
;  "Main search planning algorithm. Binds a sequence of operations from the given `ops`
;  to satisfy all constraints supplied in `constr-lkp`. `k` is an integer within [0, 10) that tunes the algorithm's
;  trade-off between performance and optimality"
;  (loop [bound-constr-lkp {}
;         free-constr-lkp constr-lkp]
;    (let [bound-ops (map (fn [op] (bind-op op bound-constr-lkp free-constr-lkp)) ops)]
;
;      ))
;  )
