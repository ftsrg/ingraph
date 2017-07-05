package ingraph.relalg.calculators

///**
//  * Calculates the external schema of the operators in the relational algebra tree.
//  *
//  * This calculation uses a postorder traversal (action are applied from the bottom to the top)
//  * first it uses recursion / dispatch methods to reach the (unary) input nodes,
//  * then each method returns with the inferred schema.
//  *
//  * For example, a join node concatenates the schema of its input nodes (left/right) and removes
//  * duplicate attributes.
//  */
//class ExternalSchemaCalculator {
//
//  extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
//  extension JoinSchemaCalculator joinSchemaCalculator = new JoinSchemaCalculator
//  extension RelalgFactory factory = RelalgFactory.eINSTANCE
//  val boolean includeEdges
//
//  new() {
//    this(true)
//  }
//
//  new(boolean includeEdges) {
//    this.includeEdges = includeEdges
//  }
//
//  def calculateExternalSchema(RelalgContainer container) {
//    if (container.isExternalSchemaInferred) {
//      throw new IllegalStateException("ExternalSchemaCalculator on relalg container was already executed")
//    } else {
//      container.externalSchemaInferred = true
//    }
//
//    container.rootExpression.traverse([fillExternalSchema])
//    container
//  }
//
//  // nullary operators
//  private def dispatch List<Variable> fillExternalSchema(DualObjectSourceOperator op) {
//    op.defineExternalSchema(#[])
//  }
//
//  private def dispatch List<Variable> fillExternalSchema(GetVerticesOperator op) {
//    op.defineExternalSchema(#[op.vertexVariable])
//  }
//
//  private def dispatch List<Variable> fillExternalSchema(GetEdgesOperator op) {
//    if (includeEdges) {
//      op.defineExternalSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])
//    } else {
//      op.defineExternalSchema(#[op.sourceVertexVariable, op.targetVertexVariable])
//    }
//  }
//
//  // unary operators
//  private def dispatch List<Variable> fillExternalSchema(ProjectionOperator op) {
//    val inputSchema = op.input.externalSchema
//
//    // check if all projected variables are in the schema
//    // 1) vertices and edges
//    op.elements.map[expression].filter(ElementVariable).forEach [
//    if (!inputSchema.contains(it)) {
//      throw new IllegalStateException('''Variable «it.name» is not part of the schema in projection operator.''')
//    }
//    ]
//    // 2) attributes
//    op.elements.map[expression].filter(AttributeVariable).forEach [
//    if (!inputSchema.contains(it.element)) {
//      throw new IllegalStateException('''Attribute «it.name» cannot be projected as its vertex/edge variable does not exists.''')
//    }
//    ]
//
//    op.defineExternalSchema(op.elements.extractVariables)
//  }
//
//  private def extractVariables(List<ExpressionVariable> expressionVariables) {
//    expressionVariables.map [ expressionVariable |
//    if (!expressionVariable.hasInferredName) {
//      createExpressionVariable => [
//      name = expressionVariable.name
//      namedElementContainer = expressionVariable.namedElementContainer
//      ]
//    } else {
//      val expr = expressionVariable.expression
//      if (expr instanceof VariableExpression) {
//        expr.variable
//      } else {
//        expressionVariable
//      }
//    }
//    ].toList
//  }
//
//  private def dispatch List<Variable> fillExternalSchema(ExpandOperator op) {
//    val schema = Lists.newArrayList(op.input.externalSchema)
//
//    if (includeEdges) {
//      schema.add(op.edgeVariable)
//    }
//    schema.add(op.targetVertexVariable)
//    op.defineExternalSchema(schema)
//  }
//
//  // rest of the unary operators
//  private def dispatch List<Variable> fillExternalSchema(UnaryOperator op) {
//    val schema = Lists.newArrayList(op.input.externalSchema)
//    op.defineExternalSchema(schema)
//  }
//
//  // binary operators
//  private def dispatch List<Variable> fillExternalSchema(AbstractJoinOperator op) {
//    val leftInputSchema = Lists.newArrayList(op.leftInput.externalSchema)
//    val rightInputSchema = Lists.newArrayList(op.rightInput.externalSchema)
//    val schema = calculateJoinSchema(op, leftInputSchema, rightInputSchema)
//    op.defineExternalSchema(schema)
//
//    // calculate common variables
//    val rightSchemaNames = op.rightInput.externalSchema.map[name]
//    val commonVariables = op.leftInput.externalSchema.filter[
//      variable | rightSchemaNames.contains(variable.name)
//    ]
//    op.commonVariables.addAll(commonVariables)
//
//    op.externalSchema
//  }
//
//  private def dispatch List<Variable> fillExternalSchema(UnionOperator op) {
//    // TODO I think this does the right thing but I am not sure - SzG
//    if (op.leftInput.externalSchema.equals(op.rightInput.externalSchema)) {
//      throw new IllegalStateException("All sub queries in a UNION must have the same column names")
//    }
//    // we only keep the left schema
//    op.defineExternalSchema(op.leftInput.externalSchema)
//  }
//
//  // unary operator again
//  private def dispatch List<Variable> fillExternalSchema(PathOperator op) {
//    val schema = Lists.newArrayList(op.input.externalSchema)
//
//    //FIXME: do something meaningful here
//    //		if (includeEdges) {
//    //			val listExpressionVariable = createExpressionVariable => [
//    //				expression = op.listVariable
//    //			]
//    //			schema.add(listExpressionVariable)
//    //		}
//    schema.add(op.targetVertexVariable)
//    op.defineExternalSchema(schema)
//  }
//
//  /**
//    * defineSchema
//    */
//  private def defineExternalSchema(Operator op, List<Variable> externalSchema) {
//    // EObjectEList.addAll() would remove duplicates anyways,
//    // but we use Guava to explicitly remove duplicates (while preserving iteration order)
//    val uniqueList = ImmutableSet.copyOf(externalSchema).asList()
//    op.externalSchema.addAll(uniqueList)
//    externalSchema
//  }
//
//}
