package ingraph.compiler.cypher2qplan.builders

import java.util

import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.mutable.ListBuffer

object ExpressionBuilder {

  def buildExpression(expression: oc.Expression, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    expression match {
      //FIXME: case e: oc.ContainsExpression => cExpr.Contains(buildExpression(e.getLeft), buildExpression(e.getRight))
      //FIXME: case e: oc.EndsWithExpression => cExpr.EndsWith(buildExpression(e.getLeft), buildExpression(e.getRight))
      case e: oc.ExpressionComparison => buildExpressionComparision(e, joins)
      case e: oc.ExpressionAnd => cExpr.And(buildExpression(e.getLeft, joins), buildExpression(e.getRight, joins))
      case e: oc.ExpressionOr => cExpr.Or(buildExpression(e.getLeft, joins), buildExpression(e.getRight, joins))
      case e: oc.ExpressionXor => buildExpressionXor(e, joins)
      case e if "not".equalsIgnoreCase(e.getOperator) => cExpr.Not(buildExpression(e.getLeft, joins))
      //TODO: case e: oc.InCollectionExpression => buildExpressionAux(e, joins)
      case e: oc.IsNotNullExpression => cExpr.IsNotNull(expr.EStub()) //FIXME: ce.vb.buildRelalgVariable(e.left)
      case e: oc.IsNullExpression => cExpr.IsNull(expr.EStub()) //FIXME: ce.vb.buildRelalgVariable(e.left)
      case e: oc.ParenthesizedExpression => buildExpression(e.getExpression, joins)
      //TODO: case e: oc.RegExpMatchingExpression => buildExpressionAux(e, joins)
      //TODO: case e: oc.RelationshipsPattern => buildExpressionAux(e, joins)
      //FIXME: case e: oc.StartsWithExpression => cExpr.StartsWith(buildExpression(e.getLeft), buildExpression(e.getRight))
      //TODO: case e: oc.Count => buildExpressionAux(e, joins)
      //TODO: case e: oc.ExpressionMulDiv => buildExpressionAux(e, joins)
      case e: oc.ExpressionNodeLabelsAndPropertyLookup => UnresolvedAttribute(Seq(e.getLeft.asInstanceOf[oc.VariableRef].getVariableRef.getName, e.getPropertyLookups.get(0).getPropertyKeyName))
      //TODO: case e: oc.ExpressionPlusMinus => buildExpressionAux(e, joins)
      //TODO: case e: oc.ExpressionPower => buildExpressionAux(e, joins)
      //TODO: case e: oc.FunctionInvocation => buildExpressionAux(e, joins)
      case e: oc.NumberConstant => LiteralBuilder.buildNumberLiteral(e)
      //TODO: case e: oc.Parameter => buildExpressionAux(e, joins)
      case e: oc.StringConstant => LiteralBuilder.buildStringLiteral(e)
      case e: oc.VariableRef => UnresolvedAttribute(e.getVariableRef.getName)
      //TODO: case e: oc.CaseExpression => buildExpressionAux(e, joins)
      //TODO: case e: oc.ExpressionList => buildExpressionAux(e, joins)
      //TODO: case e: oc.IndexExpression => buildExpressionAux(e, joins)
      case e: oc.NullConstant => cExpr.Literal(null)

      case e => throw new RuntimeException("TODO: " + s"Unhandled parameter types: ${util.Arrays.asList[Object](e)}")
    }
  }

  /**
    * This is a wrapper around the buildExpression to be used
    * in contexts where no join clauses should be generated.
    *
    * We use this outside of WHERE clauses.
    */
  def buildExpressionNoJoinAllowed(e: oc.Expression): cExpr.Expression = {
    // there should be no join clauses added when we build
    // a logical expression outside the WHERE clause
    val dummyJoins = ListBuffer[qplan.QNode]()
    val logicalExp = buildExpression(e, dummyJoins)
    if (!dummyJoins.isEmpty) {
      //FIXME: proper error handling
      throw new RuntimeException("Joins found when building a logical expression in generic expression position.")
    }

    logicalExp
  }

  def buildExpressionXor(e: oc.ExpressionXor, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    cExpr.Or(cExpr.And(l, cExpr.Not(r)), cExpr.And(cExpr.Not(l), r))
  }

//  def buildExpressionAux(e: oc.RegExpMatchingExpression, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
//    val fe = modelFactory.createFunctionLogicalExpression => [
//    expressionContainer = ce.tlc
//    ]
//
//    fe.functor = Function.REGEX_LIKE
//
//    fe.arguments.add(buildExpression(e.left))
//    fe.arguments.add(buildExpression(e.right))
//
//    fe
//  }
//
//  def buildExpressionAux(e: oc.RelationshipsPattern, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
//    // We add all the variables in the pattern as a NOT NULL expression
//    val EList<LogicalExpression> relationshipVariableExpressions = new BasicEList<LogicalExpression>()
//
//    relationshipVariableExpressions.add(modelFactory.createUnaryGraphObjectLogicalExpression => [
//    operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
//    operand = ce.vb.buildVertexVariable(e.nodePattern)
//    expressionContainer = ce.tlc
//    ])
//
//    relationshipVariableExpressions.addAll(
//      // use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
//      e.chain.map [
//    val mapIt = it
//    modelFactory.createUnaryGraphObjectLogicalExpression => [
//    operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
//    operand = ce.vb.buildEdgeVariable(mapIt.relationshipPattern.detail)
//    expressionContainer = ce.tlc
//    ]
//    ]
//    )
//    relationshipVariableExpressions.addAll(
//      // use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
//      e.chain.map [
//    val mapIt = it
//    modelFactory.createUnaryGraphObjectLogicalExpression => [
//    operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
//    operand = ce.vb.buildVertexVariable(mapIt.nodePattern)
//    expressionContainer = ce.tlc
//    ]
//    ]
//    )
//
//    joins.add(RelalgBuilder.buildRelalg(e))
//
//    Cypher2RelalgUtil.buildLeftDeepTree(BinaryLogicalOperatorType.AND, relationshipVariableExpressions.iterator)
//  }

  def buildExpressionComparision(e: oc.ExpressionComparison, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    //FIXME: this was invoked as LogicalExpressionBuilder.buildLogicalExpressionNoJoinAllowed(e, ce)
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    // WONTFIX here: add type check to ensure that the operands are comparable
    e.getOperator match {
      case "=" => cExpr.EqualTo(l, r)
      case "<>" => cExpr.Not(cExpr.EqualTo(l, r))
      case "<" => cExpr.LessThan(l, r)
      case "<=" => cExpr.LessThanOrEqual(l, r)
      case ">" => cExpr.GreaterThan(l, r)
      case ">=" => cExpr.GreaterThanOrEqual(l, r)
    }
  }

//  /**
//    * Processes IN by creating a function invocation: IN_COLLECTION(ANY, LIST expression)
//    */
//  def buildExpressionAux(e: oc.InCollectionExpression, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
//    modelFactory.createFunctionLogicalExpression => [
//    functor = Function.IN_COLLECTION
//    arguments.add(buildExpression(e.left))
//    arguments.add(buildExpression(e.right))
//    expressionContainer = ce.tlc
//    ]
//  }

//  def buildExpressionAux(e: oc.Parameter): cExpr.Expression = {
//    modelFactory.createParameterComparableExpression => [
//    parameter = modelFactory.createParameter => [
//    name = e.parameter
//    expressionContainer = ce.tlc
//    ]
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.VariableRef): cExpr.Expression = {
//    modelFactory.createVariableComparableExpression => [
//    variable = ce.vb.buildRelalgVariable(e)
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.ExpressionPlusMinus): cExpr.Expression = {
//    modelFactory.createBinaryArithmeticOperationExpression => [
//    operator = switch e.operator {
//      case "+": BinaryArithmeticOperatorType.PLUS
//      case "-": BinaryArithmeticOperatorType.MINUS
//    }
//    leftOperand = buildArithmeticExpression(e.left, ce)
//    rightOperand = buildArithmeticExpression(e.right, ce)
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.ExpressionMulDiv): cExpr.Expression = {
//    modelFactory.createBinaryArithmeticOperationExpression => [
//    operator = switch e.operator {
//      case "*": BinaryArithmeticOperatorType.MULTIPLICATION
//      case "/": BinaryArithmeticOperatorType.DIVISION
//      case "%": BinaryArithmeticOperatorType.MOD
//    }
//    leftOperand = buildArithmeticExpression(e.left, ce)
//    rightOperand = buildArithmeticExpression(e.right, ce)
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.ExpressionPower): cExpr.Expression = {
//    modelFactory.createBinaryArithmeticOperationExpression => [
//    operator = switch e.operator {
//      case "^": BinaryArithmeticOperatorType.POWER
//    }
//    leftOperand = buildArithmeticExpression(e.left, ce)
//    rightOperand = buildArithmeticExpression(e.right, ce)
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.ExpressionNodeLabelsAndPropertyLookup): cExpr.Expression = {
//    val x = ce.vb.buildRelalgVariable(e)
//    // as AttributeVariable
//    if (x instanceof AttributeVariable) {
//      modelFactory.createVariableComparableExpression => [
//      variable = x
//      expressionContainer = ce.tlc
//      ]
//    } else {
//      ce.l.unsupported('''Unsupported type received: «x.class.name»''')
//      null
//    }
//  }
//
//  def buildExpressionAux(fi: oc.FunctionInvocation): cExpr.Expression = {
//    val fe = modelFactory.createFunctionComparableExpression => [
//    expressionContainer = ce.tlc
//    ]
//
//    BuilderUtil.populateFunctionExpression(fe, fi, ce)
//
//    fe
//  }
//
//  def buildExpressionAux(fi: oc.Count): cExpr.Expression = {
//    modelFactory.createFunctionArithmeticExpression => [
//    functor = Function.COUNT_ALL
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  def buildExpressionAux(e: oc.CaseExpression): cExpr.Expression = {
//    if (!(e.expression instanceof CaseExpression)) {
//      ce.l.unrecoverableError("Outer CaseExpressions should contain a CaseExpression")
//    }
//    val caseExpr = e.expression as CaseExpression
//
//    // do we process a simple case expression,
//    // i.e. when there is a single value we search for
//    var boolean isSimple = false
//
//    val retVal = if (caseExpr.caseExpression === null) {
//      modelFactory.createGenericCaseExpression => [
//      expressionContainer = ce.tlc
//      ]
//    } else {
//      isSimple = true
//      modelFactory.createSimpleCaseExpression => [
//      expressionContainer = ce.tlc
//      test = ComparableElementBuilder.buildComparableElement(caseExpr.caseExpression, ce)
//      ]
//    }
//
//    // WHEN when THEN then
//    for (ca: caseExpr.caseAlternatives) {
//      val case_ = modelFactory.createCase => [
//      then = buildExpression(ca.then, ce)
//      ]
//      if (isSimple) {
//        case_.when = ComparableElementBuilder.buildComparableElement(ca.when, ce)
//      } else {
//        case_.when = LogicalExpressionBuilder.buildLogicalExpressionNoJoinAllowed(ca.when, ce)
//      }
//      retVal.cases.add(case_)
//    }
//
//    // ELSE elseExpression
//    if (caseExpr.elseExpression !== null) {
//      retVal.fallback = buildExpression(caseExpr.elseExpression, ce)
//    }
//
//    retVal
//  }
//
//  def buildExpressionAux(el: oc.ExpressionList): cExpr.Expression = {
//    val emptyList = modelFactory.createEmptyListExpression => [
//    head = null
//    tail = null
//    expressionContainer = ce.tlc
//    ]
//    // the tail of the first expression will be the list that was built
//    val first = modelFactory.createListExpression => [
//    tail = emptyList
//    ]
//    var recent = first
//
//    for (e : el.expressions) {
//      recent.tail = modelFactory.createListExpression => [
//      head = buildExpression(e, ce)
//      tail = emptyList
//      expressionContainer = ce.tlc
//      ]
//      recent = recent.tail
//    }
//
//    first.tail
//  }
//
//  def buildExpressionAux(ie: oc.IndexExpression): cExpr.Expression = {
//    var IndexAccessExpression retVal
//    if (ie.expression === null) {
//      ce.l.unrecoverableError('''Index lookup expression found having null as subscript.''')
//    }
//    if (ie.expression instanceof NumberConstant) {
//      if (ie.upper === null) {
//        retVal = modelFactory.createIndexSimpleAccessExpression => [
//        idx = LiteralBuilder.buildNumber(ie.expression as NumberConstant, ce)
//        ]
//      } else {
//        if (ie.upper instanceof NumberConstant) {
//          retVal = modelFactory.createIndexRangeAccessExpression => [
//          lower = LiteralBuilder.buildNumber(ie.expression as NumberConstant, ce)
//          upper = LiteralBuilder.buildNumber(ie.upper as NumberConstant, ce)
//          ]
//        } else {
//          ce.l.unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.upper.class.name».''')
//        }
//      }
//    } else {
//      ce.l.unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.expression.class.name».''')
//    }
//    retVal => [
//    list = buildExpression(ie.left, ce)
//    expressionContainer = ce.tlc
//    ]
//  }
//
//  protected def buildRelalgParameter(expression: oc.Parameter): cExpr.Expression = {
//    modelFactory.createParameter => [
//    name = expression.parameter
//    expressionContainer = ce.tlc
//    ]
//  }
}
