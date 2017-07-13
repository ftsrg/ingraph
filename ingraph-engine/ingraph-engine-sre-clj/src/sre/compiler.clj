(ns sre.compiler
  (:require
    [clojure.set :refer :all]
    [clojure.algo.generic.functor :refer :all]
    [sre.op :as op]
    [sre.constraint :as constraint]))

(defn- constr-lkp-map [f constr-lkp]
  (reduce-kv (fn [a k v] (assoc a k (fmap (fn [v] (f v k)) v))) {} constr-lkp))

(defn compare-constraint-descriptors [[a-k a-v] [b-k b-v]]
  "Compares two constraint descriptors by count ASC > arity DESC > type ASC > condition ASC
  Last two needed for differentiating to prevent deduping in sorted sets. The resulting
  order gives a fairly good satisfaction sequence"
  (let [count-cmp (compare (:n a-v) (:n b-v))]
    (if (= count-cmp 0)
      (let [arity-cmp (compare (:arity a-v) (:arity b-v))]
        (if (= arity-cmp 0)
          (let [type-cmp (compare a-k b-k)]
            (if (= type-cmp 0)
              (compare (:cond a-v) (:cond b-v))
              type-cmp))
          (- arity-cmp)))
      count-cmp)))

(defn- constr-def-lkp-into-sorted [constr-def-lkp]
  (apply (partial sorted-set-by compare-constraint-descriptors)
         (mapcat (fn [[t b]] (fmap (fn [x] [t x]) b)) (into () constr-def-lkp))))

(defn branch-constr [stump]
  (let [sorted-constr (:sorted-constr stump)
        [type props] (first sorted-constr)
        rest-constr (disj sorted-constr (first sorted-constr))
        var-lkp (:var-lkp stump)
        cond (:cond props)
        constr-lkp (if (= cond :pre)
                     (:sat-constr-lkp stump)
                     (:unsat-constr-lkp stump))]
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
                                 (update-in [:sorted-constr] (constantly rest-constr))
                                 ; merge its variables with already resolved ones
                                 (update-in [:var-lkp] #(merge %1 bindings))
                                 ; remove constraint from corresponding lookup table also
                                 ; to prevent double matching
                                 (update-in (if (= cond :pre) [:sat-constr-lkp] [:unsat-constr-lkp])
                                            (constantly (if type-eliminated?
                                                          ; no more constraints of this type, so remove
                                                          ; completely from the lookup
                                                          (dissoc constr-lkp type)
                                                          ; remove constraint from set of bindings
                                                          (update-in constr-lkp [type] clojure.core/rest)))))]
              (recur (cons new-branch branches) rest))))))))

(defn bind-op [op
               sat-constr-lkp
               unsat-constr-lkp]
  "Binds an operation in all possible ways to satisfy at least one unsatisfied constraint"
  (let [pre-constr (:requires op)
        post-constr (:satisfies op)]
    (if-not (and (subset? (into #{} (keys pre-constr)) (into #{} (keys sat-constr-lkp)))
             (subset? (into #{} (keys post-constr)) (into #{} (keys unsat-constr-lkp))))
      ; missing pre-condition requirement or superfluous (thus restricting) post-condition constraint (1)
      ()
      (let [sat-constr-count (fmap count sat-constr-lkp)
            unsat-constr-count (fmap count unsat-constr-lkp)
            sorted-constr (union (constr-def-lkp-into-sorted
                                   (constr-lkp-map #(hash-map :n (let [count (sat-constr-count %2)] (if (nil? count) 0 count))
                                                              :arity (:arity (deref %2))
                                                              :params %1
                                                              :cond :pre) pre-constr))
                                 (constr-def-lkp-into-sorted
                                   (constr-lkp-map #(hash-map :n (let [count (unsat-constr-count %2)] (if (nil? count) 0 count))
                                                              :arity (:arity (deref %2))
                                                              :params %1
                                                              :cond :post) post-constr)))]

        (if (or (empty? sorted-constr) (= 0 (:n (last (first sorted-constr)))))
          ; we either have an unsatisfied requirement or a superfluous (thus restricting) post-condition
          ()
          (loop [open (list {:var-lkp          {}
                             :sat-constr-lkp   sat-constr-lkp
                             :unsat-constr-lkp unsat-constr-lkp
                             :sorted-constr    sorted-constr})
             result ()]
        (let [[first-open & rest-open] open]
          (if-not (some? first-open)
            ; no unvisited branches, we are ready
            result
            ; get the first constraint to be matched
            (let [constr (:sorted-constr first-open)
                  first-constr (first constr)
                  rest-constr (disj constr first-constr)]
              (if-not (some? first-constr)
                ; no first, every constraint has already been matched
                (recur rest-open (cons first-open result))
                ; continue matching
                (recur (concat (branch-constr first-open) rest-open) result)))))))))))
