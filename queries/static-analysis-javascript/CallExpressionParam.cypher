MATCH
  (arguments:List) <-[:arguments]- (callExp:CallExpression) -[:callee]->
  (:IdentifierExpression) <-[:`node`]- (:Reference) <-[:references]-
  (:Variable) -[:declarations]-> (:Declaration) -[:`node`]->
  (:BindingIdentifier) <-[:name]- (fd:FunctionDeclaration) -[:params]->
  (:FormalParameters) -[:items]-> (params:List),

  (callExp)     -[:`_end`]->  (callExpE:`End`),
  (fd)          -[:`_end`]->  (fdE:`End`),
  (arguments)   -[:`_end`]->  (argumentsE:`End`)

MERGE
  (callExp)     -[:`_normal`]-> (arguments) -[:`_end`]->
  (argumentsE)  -[:`_normal`]-> (fd)        -[:`_end`]->
  (fdE)         -[:`_normal`]-> (callExpE)

