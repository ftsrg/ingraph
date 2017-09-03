(ns ingraph.sre.util
  "SRE currently doesn't support constraint an operation parametrization,
  so every argument of a constraint is in effect a variable. In some cases
  these are very awkward variables. If you take the GenUnaryAssertion for example,
  it is clear the the condition is not really a variable, but a parameter. Under
  no circumstance will you have a bound `x` and try to bind a `condition` that given `x`
  condition(x) evaluates to true. Not only that, but also the condition should be known during
  planning because it is necessary for calculating costs. The same applies
  to the less severe looking cases of The HasLabels or HasType constraints with
  their label and type parameters.

  This namespace tries to workaround this by supplying structures to help discriminate
  these different types of 'variables'.

  Also, be free to use this namespace to put other helper stuff here.")

(defrecord Var [id])
(defrecord Const [id])

(defn -createVar ^Var [id] (->Var id))
(defn -createConst ^Const [id] (->Const id))

(gen-class :name ingraph.sre.util.Variable
           :methods [^:static [createVar [Object] ingraph.sre.util.Var]
                     ^:static [createConst [Object] ingraph.sre.util.Const]])

