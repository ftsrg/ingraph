package ingraph.cypher2relalg.expressions

import ingraph.antlr.CypherLexer
import ingraph.antlr.CypherParser
import ingraph.cypher2relalg.cypherlisteners.RelalgCypherListener
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.Test

class NumberLiteralTest {

	def convert(String inputString) {
		print(inputString + ": ")
		val input = new ANTLRInputStream(inputString)
		val lexer = new CypherLexer(input)
		val tokenStream = new CommonTokenStream(lexer)
		val parser = new CypherParser(tokenStream)

		val context = parser.numberLiteral()
		val listener = new RelalgCypherListener()
		ParseTreeWalker.DEFAULT.walk(listener, context)
	}

	@Test
	def void testIntegerLiteral1() {
		convert("99")
	}

	@Test
	def void testIntegerLiteral2() {
		convert("077")
	}

	@Test
	def void testIntegerLiteral3() {
		convert("0xFF")
	}
	
	@Test
	def void testDoubleLiteral1() {
		convert("0.12")
	}
	
	@Test
	def void testDoubleLiteral2() {
		convert("1.2")
	}

	@Test
	def void testDoubleLiteral3() {
		convert("1e2")
	}

	@Test
	def void testDoubleLiteral4() {
		convert("1E2")
	}

}
