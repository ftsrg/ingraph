(ns sre.compiler
  (:require
    [clojure.set :refer :all]
    [clojure.algo.generic.functor :refer :all]
    [sre.op :as op]
    [sre.constraint :as constraint]))

(defn- constr-lkp-map [f constr-lkp]
  (reduce-kv (fn [a k v] (assoc a k (fmap (fn [v] (f v k)) v))) {} constr-lkp))

(defn compare-constraint-descriptors [[a-k a-v] [b-k b-v]]
  "Compares two constraint descriptors by count ASC > arity DESC > type ASC > everything else
  Last two needed for differentiating to prevent deduping in sorted sets. The resulting
  order gives a fairly good satisfaction sequence"
  (let [count-cmp (compare (:n a-v) (:n b-v))]
    (if (= count-cmp 0)
      (let [arity-cmp (compare (:arity a-v) (:arity b-v))]
        (if (= arity-cmp 0)
          (or (not= a-k b-k) (not= a-v b-v))
          (- arity-cmp)))
      count-cmp)))

(defn- constr-def-lkp-into-sorted [constr-def-lkp]
  (apply (partial sorted-set-by compare-constraint-descriptors)
         (mapcat (fn [[t b]] (fmap (fn [x] [t x]) b)) (into () constr-def-lkp))))

(defn branch-constr [stump]
  (let [[[type props] & rest-constr] (:sorted-constr stump)
        var-lkp (:var-lkp stump)
        cond (:cond props)
        constr-lkp (if (= cond :pre)
                     (:bound-constr-lkp stump)
                     (:free-constr-lkp stump))]
    (loop [branches ()
           [first & rest] (constr-lkp type)]
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
            (let [type-eliminated? (= 1 (count (constr-lkp type)))
                  new-branch (-> stump
                                 ; remove constraint from to-do list
                                 ; note that :n is not maintained. We don't have rely on it apart from determining
                                 ; the initial order, which we don't have to change even if :n is decremented,
                                 ; as the potential modified items will be already on the top. Moreover, reaching an
                                 ; unsatisfiable state by hitting :n = 0 can be determined by counting constr-lkp-types
                                 ; immediately in the next turn whose performance overhead is negligible.
                                 (update-in [:sorted-constr] (constantly rest-constr))
                                 ; merge its variables with already resolved ones
                                 (update-in [:var-lkp] #(merge %1 bindings))
                                 ; move post-condition from free constraints to bound ones
                                 ((fn [branch]
                                    (case cond :post (-> branch
                                                         (update-in [:free-constr-lkp]
                                                                    (constantly
                                                                      (if type-eliminated?
                                                                        ; no more constraints of this type, so remove
                                                                        ; completely from the lookup
                                                                        (dissoc constr-lkp type)
                                                                        ; remove constraint from set of bindings
                                                                        (update-in constr-lkp [type] clojure.core/rest))))
                                                         (update-in [:bound-constr-lkp]
                                                                    (fn [map]
                                                                      (merge-with #(into [] (concat %1 %2)) {type [first]} map))))
                                               :pre branch))))]
              (recur (cons new-branch branches) rest))))))))

(defn constr-set-to-constr-lkp [constrs]
  "Turns tthe given a set of constraints into a lookup table"
  (apply merge-with #(concat %1 %2) (map #(hash-map (:name %1) [(:vars %1)]) constrs)))

(defn expand-implications [constr-defs]
  (apply union (map sre.constraint/implies* constr-defs)))

(defn bind-op [op
               bound-constr-lkp
               free-constr-lkp]
  "Binds an operation in all possible ways to satisfy at least one free constraint"
  (let [pre-constr (:requires op)
        post-constr (:satisfies op)]
    ; this check is not required, it serves as an optimization
    ; for exiting as early as possible in this highly likely situation
    (if-not (and (subset? (keys pre-constr) (into #{} (keys bound-constr-lkp)))
                 (subset? (keys post-constr) (into #{} (keys free-constr-lkp))))
      ; missing pre-condition requirement or superfluous (thus restricting) post-condition constraint (1)
      ()
      (let [bound-constr-count (fmap count bound-constr-lkp)
            free-constr-count (fmap count free-constr-lkp)
            sorted-constr (union (constr-def-lkp-into-sorted
                                   (constr-lkp-map #(hash-map :n (let [count (bound-constr-count %2)] (if (nil? count) 0 count))
                                                              :arity (:arity (deref %2))
                                                              :params %1
                                                              :cond :pre) pre-constr))
                                 (constr-def-lkp-into-sorted
                                   (constr-lkp-map #(hash-map :n (let [count (free-constr-count %2)] (if (nil? count) 0 count))
                                                              :arity (:arity (deref %2))
                                                              :params %1
                                                              :cond :post) post-constr)))]

        (loop [open (list {:var-lkp          {}
                           :bound-constr-lkp bound-constr-lkp
                           :free-constr-lkp  free-constr-lkp
                           :sorted-constr    (into () sorted-constr)})
               result ()]
          (let [[first-open & rest-open] open]
            (if-not (some? first-open)
              ; no unvisited branches, we are ready
              result
              ; get the first constraint to be matched
              (let [[first-constr rest-constr] (:sorted-constr first-open)]
                (if-not (some? first-constr)
                  ; every constraint has already been matched
                  (recur rest-open (cons first-open result))
                  ; continue matching
                  (recur (concat (branch-constr first-open) rest-open) result))))))))))
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
