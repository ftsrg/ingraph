package ingraph.compiler.cypher2qplan.builders

import java.util

import ingraph.model.misc.Function
import ingraph.model.{expr, qplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedFunction
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
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
      case e: oc.ExpressionNot => cExpr.Not(buildExpression(e.getLeft, joins))
      //TODO: case e: oc.InCollectionExpression => buildExpressionAux(e, joins)
      case e: oc.IsNotNullExpression => cExpr.IsNotNull(buildExpressionNoJoinAllowed(e.getLeft))
      case e: oc.IsNullExpression => cExpr.IsNull(buildExpressionNoJoinAllowed(e.getLeft))
      case e: oc.ParenthesizedExpression => buildExpression(e.getExpression, joins)
      //TODO: case e: oc.RegExpMatchingExpression => buildExpressionAux(e, joins)
      case e: oc.RelationshipsPattern => buildExpressionPattern(e, joins)
      //FIXME: case e: oc.StartsWithExpression => cExpr.StartsWith(buildExpression(e.getLeft), buildExpression(e.getRight))
      case e: oc.ExpressionNodeLabelsAndPropertyLookup => AttributeBuilder.buildAttribute(e)
      case e: oc.ExpressionPlusMinus => buildExpressionArithmetic(e, joins)
      case e: oc.ExpressionUnaryPlusMinus => buildExpressionArithmetic(e, joins)
      case e: oc.ExpressionMulDiv => buildExpressionArithmetic(e, joins)
      case e: oc.ExpressionPower => buildExpressionArithmetic(e, joins)
      //FIXME#206: this should pass function name unresolved
      case e: oc.FunctionInvocation => UnresolvedFunction(e.getFunctionName.getName, e.getParameter.asScala.map( e => buildExpression(e, joins) ), e.isDistinct)
      case _: oc.Count => UnresolvedFunction(Function.COUNT_ALL.getPrettyName, Seq[cExpr.Expression](), false)
      case e: oc.NumberConstant => LiteralBuilder.buildNumberLiteral(e)
      case e: oc.Parameter => buildParameter(e)
      case e: oc.StringConstant => LiteralBuilder.buildStringLiteral(e)
      case e: oc.VariableRef => AttributeBuilder.buildAttribute(e)
      case e: oc.CaseExpression => buildExpressionCase(e)
      //TODO: case e: oc.ExpressionList => buildExpressionAux(e, joins)
      //TODO: case e: oc.IndexExpression => buildExpressionAux(e, joins)
      case e: oc.NullConstant => cExpr.Literal(null)
      case e: oc.BoolConstant => LiteralBuilder.buildBoolLiteral(e)

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

  def buildExpressionPattern(e: oc.RelationshipsPattern, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    val currentPattern = PatternBuilder.buildPattern(e)
    joins += currentPattern

    // We extract all the attributes in the pattern (which must be an Expand chain) as a NOT NULL expression
    AttributeBuilder.extractAttributesFromExpandChain(currentPattern).map( e => cExpr.IsNotNull(e) ).foldLeft[Expression]( cExpr.Literal(true) )( (b, a) => cExpr.And(b, a) )
  }

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

  def buildExpressionArithmetic(e: oc.ExpressionPlusMinus, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    e.getOperator match {
      case "+" => cExpr.Add(l, r)
      case "-" => cExpr.Subtract(l, r)
    }
  }

  def buildExpressionArithmetic(e: oc.ExpressionUnaryPlusMinus, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)

    e.getOperator match {
      case "-" => cExpr.UnaryMinus(l)
      case "+" => cExpr.UnaryPositive(l)
    }
  }

  def buildExpressionArithmetic(e: oc.ExpressionMulDiv, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    e.getOperator match {
      case "*" => cExpr.Multiply(l, r)
      case "/" => cExpr.Divide(l, r)
      case "%" => cExpr.Pmod(l, r)
    }
  }

  def buildExpressionArithmetic(e: oc.ExpressionPower, joins: ListBuffer[qplan.QNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    e.getOperator match {
      case "^" => UnresolvedFunction(Function.POW.getPrettyName, Seq[cExpr.Expression](l, r), isDistinct = false)
    }
  }

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

  def buildExpressionCase(e: oc.CaseExpression): cExpr.Expression = {
    e.getExpression match {
      case caseExpr: oc.CaseExpression => {
        val elseExpr: Option[cExpr.Expression] = caseExpr.getElseExpression match {
          case e: Any => Some(buildExpressionNoJoinAllowed(e))
          case _ => None
        }

        val branches: Seq[(cExpr.Expression, cExpr.Expression)] = caseExpr.getCaseAlternatives.asScala.map( ca => { //(condExpr, valueExpr)
          (buildExpressionNoJoinAllowed(ca.getWhen), buildExpressionNoJoinAllowed(ca.getThen))
        })

        caseExpr.getCaseExpression match {
          case e: Any => {
            val simpleCaseExpression: cExpr.Expression = buildExpressionNoJoinAllowed(e)
            val emulatedBranches: Seq[(cExpr.Expression, cExpr.Expression)] = branches.map({
              case (condExpr, valueExpr) => {
                (cExpr.EqualTo(simpleCaseExpression, condExpr), valueExpr)
              }
            })
            cExpr.CaseWhen(emulatedBranches, elseExpr)
          }
          case _ => cExpr.CaseWhen(branches, elseExpr)
        }
      }
      case _ => throw new RuntimeException("According to the grammar implementation, outer CaseExpressions should contain a CaseExpression")
    }
  }

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
  def buildParameter(e: oc.Parameter): expr.Parameter = {
    e.getParameter match {
      case name: String => expr.Parameter(name)
    }
  }
}
