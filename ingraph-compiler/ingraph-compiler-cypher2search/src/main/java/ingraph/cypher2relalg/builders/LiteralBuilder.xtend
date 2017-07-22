package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import ingraph.cypher2relalg.util.StringUtil
import java.math.BigInteger
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import relalg.RelalgFactory

package class LiteralBuilder {

	/** The model factory for the relational graph algebra representation */
	val protected static modelFactory = RelalgFactory.eINSTANCE

	def static buildNumber(NumberConstant e, CompilerEnvironment ce) {
		var int n
		try {
			n = Integer.parseInt(e.value)
		} catch (NumberFormatException ex1) {
			ce.l.unrecoverableError('''Unable to parse «e.value» as integer.''')
		}
		n
	}

	def static buildNumberLiteral(NumberConstant e, CompilerEnvironment ce) {
		try {
			val n = Long.parseLong(e.value)
			modelFactory.createIntegerLiteral => [
				value = n
				expressionContainer = ce.tlc
			]
		} catch (NumberFormatException ex1) {
			try {
				val n = new BigInteger(e.value)
				modelFactory.createBigIntegerLiteral => [
					value = n
					expressionContainer = ce.tlc
				]
			} catch (NumberFormatException ex2) {
				modelFactory.createDoubleLiteral => [
					value = Double.parseDouble(e.value)
					expressionContainer = ce.tlc
				]
			}
		}
	}

	def static buildStringLiteral(StringConstant e, CompilerEnvironment ce) {
		modelFactory.createStringLiteral => [
			value = StringUtil.unescapeCypherString(e.value, ce.l)
			expressionContainer = ce.tlc
		]
	}	
}