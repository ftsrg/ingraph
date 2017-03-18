package ingraph.optimization.test

import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import org.junit.Test

class LdbcSnbBiCypher2Relalg2Rete2TexTest extends Cypher2Relalg2Rete2TexTest {
		
	override protected directory() {
		"ldbc-snb-bi"
	}

	def process(String query) {		
		val querySpecification = FileUtils.readFileToString(new File('''../queries/«directory()»/«query».cypher'''), Charset.forName("UTF-8"))
		process(query, querySpecification)
	}

	@Test
	def void test3() throws IOException {
		process("query-3")
	}

	@Test
	def void test4() throws IOException {
		process("query-4")
	}

	@Test
	def void test5() throws IOException {
		process("query-5")
	}

	@Test
	def void test6() throws IOException {
		process("query-6")
	}

	@Test
	def void test7() throws IOException {
		process("query-7")
	}

	@Test
	def void test8() throws IOException {
		process("query-8")
	}

	@Test
	def void test9() throws IOException {
		process("query-9")
	}

	@Test
	def void test12() throws IOException {
		process("query-12")
	}

	@Test
	def void test13() throws IOException {
		process("query-13")
	}

	@Test
	def void test14() throws IOException {
		process("query-14")
	}

	@Test
	def void test15() throws IOException {
		process("query-15")
	}

	@Test
	def void test16() throws IOException {
		process("query-16")
	}

	@Test
	def void test19() throws IOException {
		process("query-19")
	}

	@Test
	def void test20() throws IOException {
		process("query-20")
	}

	@Test
	def void test23() throws IOException {
		process("query-23")
	}

	@Test
	def void test24() throws IOException {
		process("query-24")
	}

}
