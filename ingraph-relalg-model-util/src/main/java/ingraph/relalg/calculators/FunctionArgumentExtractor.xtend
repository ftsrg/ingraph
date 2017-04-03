package ingraph.relalg.calculators

import java.util.List
import relalg.ArithmeticOperationExpression
import relalg.FunctionExpression
import relalg.RelalgFactory
import relalg.UnaryArithmeticOperationExpression
import relalg.Variable
import relalg.VariableExpression
import relalg.Literal
import relalg.ListExpression
import ingraph.relalg.util.ListExpressionUtil

class FunctionArgumentExtractor {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def List<? extends Variable> extractFunctionArguments(FunctionExpression fe) {
//		if (fe.functor.aggregation) {
//			println(fe.arguments)
//			null
//			fe.
//			// TODO we should handle aggregations here but note that aggregations cannot be nested.
//			// also, maybe this cannot happen now?
//		} else 
		if (fe.functor.meta) {
			#[createExpressionVariable => [
				namedElementContainer = fe.expressionContainer
				expression = fe
				// all metafunctions have exactly one argument
				name = '''«fe.functor»(«(fe.arguments.get(0) as VariableExpression).variable.name»)'''
			]]
		} else {
			fe.arguments.map[extractVariableFromExpression]
		}.flatten.toList
	}
//			createExpressionVariable => [
//				namedElementContainer = a.container
//				expression = a
//				name = '''«functionExpression.functor.name.toLowerCase»(«(functionExpression.arguments.get(0) as VariableExpression).variable.name»)'''
//			]
//		}
//		fe.arguments.map[extractVariableFromArgument].flatten.toList

	def dispatch List<? extends Variable> extractVariableFromExpression(FunctionExpression a) {
		extractFunctionArguments(a)
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(VariableExpression a) {
		#[a.variable]
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(ArithmeticOperationExpression a) {
		#[extractVariableFromExpression(a.leftOperand), extractVariableFromExpression(a.rightOperand)].flatten.toList
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(UnaryArithmeticOperationExpression a) {
		#[extractVariableFromExpression(a.operand)].flatten.toList
	}

	def dispatch List<? extends Variable> extractVariableFromExpression(Literal a) {
		#[]
	}
	
	def dispatch List<? extends Variable> extractVariableFromExpression(ListExpression a) {
		ListExpressionUtil.toList(a).map[extractVariableFromExpression].flatten.toList
	}

}
