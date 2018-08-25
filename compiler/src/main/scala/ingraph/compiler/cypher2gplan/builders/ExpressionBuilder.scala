package ingraph.compiler.cypher2gplan.builders

import java.util

import ingraph.compiler.cypher2gplan.util.BuilderUtil
import ingraph.compiler.exceptions.{CompilerException, PatternNotAllowedException, UnexpectedTypeException, UnsupportedException}
import ingraph.model.misc.Function
import ingraph.model.{expr, gplan}
import org.apache.spark.sql.catalyst.analysis.UnresolvedFunction
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object ExpressionBuilder {

  def buildExpression(expression: oc.Expression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    expression match {
      case e: oc.ComparisonExpression => buildExpressionComparision(e, joins)
      case e: oc.AndExpression => cExpr.And(buildExpression(e.getLeft, joins), buildExpression(e.getRight, joins))
      case e: oc.OrExpression => cExpr.Or(buildExpression(e.getLeft, joins), buildExpression(e.getRight, joins))
      case e: oc.XorExpression => buildExpressionXor(e, joins)
      case e: oc.NotExpression => cExpr.Not(buildExpression(e.getLeft, joins))
      case e: oc.IsNotNullExpression => cExpr.IsNotNull(buildExpressionNoJoinAllowed(e.getLeft))
      case e: oc.IsNullExpression => cExpr.IsNull(buildExpressionNoJoinAllowed(e.getLeft))
      case e: oc.ParenthesizedExpression => buildExpression(e.getExpression, joins)
      case e: oc.RelationshipsPattern => buildExpressionPattern(e, joins)
      case e: oc.PropertyLookupExpression => AttributeBuilder.buildAttribute(e)
      case e: oc.NodeLabelsExpression => UnresolvedFunction(Function.NODE_HAS_LABELS.getPrettyName, Seq[cExpr.Expression](buildExpressionNoJoinAllowed(e.getLeft), BuilderUtil.parseToVertexLabelSet(e.getNodeLabels)), false)
      case e: oc.AddOrSubtractExpression => buildExpressionArithmetic(e, joins)
      case e: oc.UnaryAddOrSubtractExpression => buildExpressionArithmetic(e, joins)
      case e: oc.MultiplyDivideModuloExpression => buildExpressionArithmetic(e, joins)
      case e: oc.PowerOfExpression => buildExpressionArithmetic(e, joins)
      case e: oc.FunctionInvocation => UnresolvedFunction(e.getFunctionName, e.getParameter.asScala.map( e => buildExpression(e, joins) ), e.isDistinct)
      case _: oc.Count => UnresolvedFunction(Function.COUNT_ALL.getPrettyName, Seq[cExpr.Expression](), false)
      case e: oc.IndexRangeExpression => buildExpressionIndexRange(e) // foo[a..b], should return list, even for foo[1..2]
      case e: oc.IndexLookupExpression => buildExpressionIndexLookup(e) // foo[a], should return scalar
      case e: oc.InCollectionExpression => UnresolvedFunction(Function.IN_COLLECTION.getPrettyName, Seq[Expression]( buildExpressionNoJoinAllowed(e.getLeft), buildExpressionNoJoinAllowed(e.getRight)), isDistinct=false)
      // String predicates
      case e: oc.ContainsExpression => UnresolvedFunction(Function.CONTAINS.getPrettyName, Seq[Expression]( buildExpressionNoJoinAllowed(e.getLeft), buildExpressionNoJoinAllowed(e.getRight)), isDistinct=false) //cExpr.Contains
      case e: oc.StartsWithExpression => UnresolvedFunction(Function.STARTS_WITH.getPrettyName, Seq[Expression]( buildExpressionNoJoinAllowed(e.getLeft), buildExpressionNoJoinAllowed(e.getRight)), isDistinct=false) //cExpr.StartsWith
      case e: oc.EndsWithExpression => UnresolvedFunction(Function.ENDS_WITH.getPrettyName, Seq[Expression]( buildExpressionNoJoinAllowed(e.getLeft), buildExpressionNoJoinAllowed(e.getRight)), isDistinct=false) //cExpr.EndsWith
      case e: oc.RegExpMatchingExpression => UnresolvedFunction(Function.REGEX_LIKE.getPrettyName, Seq[Expression]( buildExpressionNoJoinAllowed(e.getLeft), buildExpressionNoJoinAllowed(e.getRight)), isDistinct=false)
      // End string predicates
      case e: oc.NumberLiteral => LiteralBuilder.buildNumberLiteral(e)
      case e: oc.Parameter => buildParameter(e)
      case e: oc.StringLiteral => LiteralBuilder.buildStringLiteral(e)
      case e: oc.VariableRef => AttributeBuilder.buildAttribute(e)
      case e: oc.CaseExpression => buildExpressionCase(e)
      case e: oc.ListLiteral => expr.ListExpression( e.getExpressions.asScala.map( buildExpressionNoJoinAllowed(_) ) )
      case e: oc.NULL => cExpr.Literal(null)
      case e: oc.BooleanLiteral => LiteralBuilder.buildBoolLiteral(e)

      case e => throw new CompilerException("TODO: " + s"Unhandled parameter types: ${util.Arrays.asList[Object](e)}")
    }
  }

  /**
    * This is a wrapper around the buildExpression to be used
    * in contexts where no join clauses should be generated.
    *
    * We use this outside of WHERE clauses.
    *
    * @throws PatternNotAllowedException if pattern expression (join) was found despite not allowed
    */
  def buildExpressionNoJoinAllowed(e: oc.Expression): cExpr.Expression = {
    // there should be no join clauses added when we build
    // a logical expression outside the WHERE clause
    val dummyJoins = ListBuffer[gplan.GNode]()
    val logicalExp = buildExpression(e, dummyJoins)
    if (!dummyJoins.isEmpty) {
      //FIXME: proper error handling
      throw new PatternNotAllowedException("Pattern expression (joins) found when building a logical expression in generic expression position.")
    }

    logicalExp
  }

  def buildExpressionXor(e: oc.XorExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    cExpr.Or(cExpr.And(l, cExpr.Not(r)), cExpr.And(cExpr.Not(l), r))
  }

  def buildExpressionPattern(e: oc.RelationshipsPattern, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    val currentPattern = PatternBuilder.buildPattern(e)
    joins += currentPattern

    // We extract all the attributes in the pattern (which must be an Expand chain) as a NOT NULL expression
    AttributeBuilder.extractAttributesFromExpandChain(currentPattern).map( e => cExpr.IsNotNull(e) ).foldLeft[Expression]( cExpr.Literal(true) )( (b, a) => cExpr.And(b, a) )
  }

  def buildExpressionComparision(e: oc.ComparisonExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
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

  def buildExpressionArithmetic(e: oc.AddOrSubtractExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    e.getOperator match {
      case "+" => cExpr.Add(l, r)
      case "-" => cExpr.Subtract(l, r)
    }
  }

  def buildExpressionArithmetic(e: oc.UnaryAddOrSubtractExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)

    e.getOperator match {
      case "-" => cExpr.UnaryMinus(l)
      case "+" => cExpr.UnaryPositive(l)
    }
  }

  def buildExpressionArithmetic(e: oc.MultiplyDivideModuloExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
    // process them only once because of the possible joins there
    val l = buildExpression(e.getLeft, joins)
    val r = buildExpression(e.getRight, joins)

    e.getOperator match {
      case "*" => cExpr.Multiply(l, r)
      case "/" => cExpr.Divide(l, r)
      case "%" => cExpr.Pmod(l, r)
    }
  }

  def buildExpressionArithmetic(e: oc.PowerOfExpression, joins: ListBuffer[gplan.GNode]): cExpr.Expression = {
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

  def buildExpressionIndexRange(indexExpr: oc.IndexRangeExpression): expr.IndexRangeExpression = {
    def buildBoundary(b: oc.Expression): Option[Int] = Option(b) match {
      case None => None
      case Some(n) => n match {
        case n: oc.NumberLiteral => Option(LiteralBuilder.buildNumber(n)) match {
          case None => throw new UnsupportedException("boundary is missing")
          case Some(m) if m < 0 => throw new UnsupportedException("negative indexing")
          case x => x
        }
        case x => throw new UnexpectedTypeException(x, "only literal indexing is supported for index expressions")
      }
    }

    val collection = buildExpressionNoJoinAllowed(indexExpr.getLeft)
    val lower = buildBoundary(indexExpr.getLower)
    val upper = buildBoundary(indexExpr.getUpper)

    if (lower.isEmpty && upper.isEmpty) {
      throw new UnsupportedException("Both lower and upper bound are missing")
    }

    expr.IndexRangeExpression(collection, lower, upper)
  }

  def buildExpressionIndexLookup(indexLookupExpr: oc.IndexLookupExpression): expr.IndexLookupExpression = {
    val collection = buildExpressionNoJoinAllowed(indexLookupExpr.getLeft)
    val idx = indexLookupExpr.getExpression match {
      case n: oc.NumberLiteral => LiteralBuilder.buildNumber(n)
      case x => throw new UnexpectedTypeException(x, "only literal indexing is supported for index expressions")
    }

    expr.IndexLookupExpression(collection, idx)
  }

  def buildExpressionCase(caseExpr: oc.CaseExpression): cExpr.Expression = {
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
