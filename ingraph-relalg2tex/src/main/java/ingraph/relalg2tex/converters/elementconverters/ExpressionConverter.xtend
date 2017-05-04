package ingraph.relalg2tex.converters.elementconverters

import ingraph.relalg2tex.constants.RelNullConstants
import relalg.AbstractEdgeVariable
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BigIntegerLiteral
import relalg.BinaryArithmeticOperationExpression
import relalg.BinaryLogicalExpression
import relalg.BooleanLiteral
import relalg.Direction
import relalg.DoubleLiteral
import relalg.ElementVariable
import relalg.EmptyListExpression
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.IntegerLiteral
import relalg.ListExpression
import relalg.NavigationDescriptor
import relalg.NullLiteral
import relalg.Parameter
import relalg.ParameterComparableExpression
import relalg.StringLiteral
import relalg.UnaryGraphObjectLogicalExpression
import relalg.UnaryLogicalExpression
import relalg.VariableExpression
import relalg.VertexVariable
import relalg.function.Function

class ExpressionConverter {
	
	extension StringEscaper stringEscaper = new StringEscaper
	extension ExpressionOperatorTypeConverter operatorConverter = new ExpressionOperatorTypeConverter
	extension ElementConverter elementConverter = new ElementConverter


	/**
	 * In case of VariableExpression wrapping a VertexVariable or AbstractEdgeVariable,
	 * labelset constraints will also be serialized.
	 *
	 * Otherwise, it falls back to convertExpression.
	 */
	def CharSequence convertExpressionWithLabelSet(Expression exp) {
		// Note: this is not a nice, expression-based Xtend code.
		// It's more like a Java procedural code because of the return's
		if (exp instanceof VariableExpression) {
			val v = exp.variable
			if (v instanceof VertexVariable) {
				return '''\nodevariable{«v.escapedName»}{«convertVertexLabelSet(v.vertexLabelSet)»}'''
			} else if (v instanceof AbstractEdgeVariable) {
				return '''\edgevariable{«v.escapedName»}{«convertEdgeLabelSet(v.edgeLabelSet)»}'''
			}
		}

		return convertExpression(exp)
	}

	def dispatch CharSequence convertExpression(IntegerLiteral integerLiteral) {
		'''\literal{«integerLiteral.value»}'''
	}
	
	def dispatch CharSequence convertExpression(BigIntegerLiteral bigintegerLiteral)	{
		'''\literal{«bigintegerLiteral.value»}'''
	}
	
	def dispatch CharSequence convertExpression(DoubleLiteral doubleLiteral) {
		'''\literal{«doubleLiteral.value»}'''
	}

	def dispatch CharSequence convertExpression(StringLiteral stringLiteral) {
		'''\literal{"«stringLiteral.value.escape»"}'''
	}

	def dispatch CharSequence convertExpression(Parameter parameter) {
		'''\var{\$«parameter.name»}'''
	}

	def dispatch CharSequence convertExpression(ParameterComparableExpression pce) {
		'''«convertExpression(pce.parameter)»'''
	}

	def dispatch CharSequence convertExpression(ElementVariable elementVariable) {
		'''\var{«elementVariable.escapedName»}'''
	}

	def dispatch CharSequence convertExpression(AttributeVariable attributeVariable) {
		'''\var{«IF attributeVariable.element !== null»«attributeVariable.element.escapedName»«ELSE»«attributeVariable.expVar.escapedName»«ENDIF».«attributeVariable.escapedName»}'''
	}

	def dispatch CharSequence convertExpression(ExpressionVariable expVariable) {
		if (expVariable.hasInferredName) {
			convertExpression(expVariable.expression)
		} else {
			'''\var{«expVariable.escapedName»}'''
		}
	}
	
	def dispatch CharSequence convertExpression(VariableExpression ve) {
		convertExpression(ve.variable)
	}

	def dispatch CharSequence convertExpression(FunctionExpression fe) {
		'''
			\literal{«fe.functor.toString.escape»}
			«IF fe.functor != Function.COUNT_ALL»\left( «fe.arguments.map[convertExpression].join(", ")» \right)«ENDIF»
		'''
	}

	def dispatch CharSequence convertExpression(EmptyListExpression fe) {
		'''\left[ \right]'''
	}

	def dispatch CharSequence convertExpression(ListExpression fe) {
		var retVal = '''\left['''

		for(var i = fe; !(i instanceof EmptyListExpression); i = i.tail) {
			retVal += ''' «i.head.convertExpression»«IF !(i.tail instanceof EmptyListExpression)»,«ENDIF»'''
		}

		retVal + ''' \right]'''
	}

	def dispatch CharSequence convertExpression(BinaryLogicalExpression exp) {
		'''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
	}

	def dispatch CharSequence convertExpression(UnaryLogicalExpression exp) {
		'''«exp.operator.convert» \left( «exp.operand.convertExpression» \right)'''
	}

	def dispatch CharSequence convertExpression(UnaryGraphObjectLogicalExpression exp) {
		'''«exp.operand.convertExpression» «exp.getOperator.convert»'''
	}

	def dispatch CharSequence convertExpression(ArithmeticComparisonExpression exp) {
		'''«exp.leftOperand.convertExpression» «exp.operator.convertOperatorType» «exp.rightOperand.convertExpression»'''
	}

	def dispatch CharSequence convertExpression(BinaryArithmeticOperationExpression exp) {
		'''«exp.leftOperand.convertExpression» «exp.operator.convert» «exp.rightOperand.convertExpression»'''
	}

	def dispatch CharSequence convertExpression(NavigationDescriptor exp) {
		'''\navigationdescriptor'''
		+ switch exp.direction {
			case Direction.IN: '''in'''
			case Direction.OUT: '''out'''
			case Direction.BOTH: '''both'''
		}
		+ '''«exp.edgeVariable.convertElement»''' // creates {\var{e}}{Label1|Label2...}
		+ '''{«exp.sourceVertexVariable.convertExpression»}{«exp.targetVertexVariable.convertExpression»}'''
	}

	def dispatch CharSequence convertExpression(BooleanLiteral exp) {
		'''\mathtt{«if (exp.value) "true" else "false"»}'''
	}

	def dispatch CharSequence convertExpression(NullLiteral x) {
		RelNullConstants.relNull
	}

	def dispatch CharSequence convertExpression(Void x) {
		'''Void:null'''
	}

}
