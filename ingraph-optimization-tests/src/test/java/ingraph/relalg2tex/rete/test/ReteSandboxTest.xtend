package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.optimization.transformations.reteoptimization.ReteOptimization
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraVariableInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import org.junit.Ignore
import org.junit.Test

class ReteSandboxTest {

	extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	extension ReteOptimization optimization = new ReteOptimization

	val config = RelalgConverterConfig.builder.consoleOutput(false).standaloneDocument(true).build
	val drawer = new Relalg2TexTreeConverter(config)

	def process(String query, String cypher) {
		// search-based
		val containerSearchBased = Cypher2Relalg.processString(cypher)
		containerSearchBased.inferBasicSchema
		drawer.convert(containerSearchBased, "sandbox/" + query + "-search")
		RelalgUtil.save(containerSearchBased, "query-models/" + query + "-search")

		// Rete
		val containerRete = Cypher2Relalg.processString(cypher)
		containerRete.transformToRete
		containerRete.inferBasicSchema
		containerRete.inferExtraVariables
		containerRete.inferFullSchema
		drawer.convert(containerRete, "sandbox/" + query + "-rete")
		RelalgUtil.save(containerSearchBased, "query-models/" + query + "-rete")
		return containerSearchBased
	}

	@Test
	def void t1() {
		process("t1", '''
			MATCH
			(x:X)-[:ASD]->(person:Person)-[:KNOWS*1..2]-(friend:Person),
			(friend)<-[:HAS_CREATOR]-(friendPost:Post)-[:HAS_TAG]->(knownTag:Tag)
			WHERE NOT(person = friend)
			MATCH (friendPost)-[:HAS_TAG]->(commonTag:Tag)
			WHERE NOT(commonTag = knownTag)
			WITH DISTINCT commonTag, knownTag, friend
			MATCH (commonTag)<-[:HAS_TAG]-(commonPost:Post)-[:HAS_TAG]->(knownTag)
			WHERE (commonPost)-[:HAS_CREATOR]->(friend)
			RETURN
			commonTag.name AS tagName,
			count(commonPost) AS postCount
			ORDER BY
			postCount DESC,
			tagName ASC
			LIMIT 10
		''')
	}

	@Test
	def void t2() {
		process("t2", '''
			MATCH (n)
			RETURN n.division, count(*)
			ORDER BY count(*) DESC, n.division ASC
		''')
	}

	@Test
	def void t3() {
		process("t3", '''
			MATCH (n)-[r]-()
			RETURN type(r), labels(n), keys(n), keys(r), properties(n), properties(r)
		''')
	}

	@Test
	def void test() {
		process("test", '''
			RETURN 1 AS x
			UNION
			RETURN 2 AS x
		''')
	}

	@Ignore
	@Test
	def void test01() {
		process("test01", '''
			MATCH (p:Person)
			RETURN p.name AS name
			ORDER BY p.name
			LIMIT 1
		''')
	}

	@Ignore
	@Test
	def void test02() {
		process("test02", '''
			MATCH (n)
			WHERE n.something="emfsucks"
			RETURN n.something
		''')
	}

	@Ignore
	@Test
	def void testNoRangePath() {
		process("test-NoRange-path", '''MATCH p=(n)-[:REL]->(m) RETURN n, m''')
	}

	@Test
	def void testNoRange() {
		process("test-NoRange", '''MATCH (n)-[:REL]->(m) RETURN n, m''')
	}

	@Test
	def void testRangeDefaultMinDefaultMax() {
		process("test-Range-DefaultMin-DefaultMax", '''MATCH (n)-[:REL*..]->(m) RETURN n, m''')
	}

	@Test
	def void testRangeDefaultMinSpecifiedMax() {
		process("test-Range-DefaultMin-SpecifiedMax", '''MATCH (n)-[:REL*..5]->(m) RETURN n, m''')
	}

	@Test
	def void testRangeSpecifiedMinDefaultMax() {
		process("test-Range-SpecifiedMin-DefaultMax", '''MATCH (n)-[:REL*3..]->(m) RETURN n, m''')
	}

	@Test
	def void testRangeSpecifiedMinSpecifiedMax() {
		process("test-Range-SpecifiedMin-SpecifiedMax", '''MATCH (n)-[:REL*3..5]->(m) RETURN n, m''')
	}

	@Test
	def void testSimple() {
		process(
			"test-Simple",
			'''
				MATCH
					(a {name: 'a'})-[:REL]->(x),
					(d {name: 'd'})-[:REL]->(y),
					( (x)-[r:REL*]->(y) )
				RETURN r
			'''
		);
	}

	@Test
	def void posLength() {
		process(
			"posLength",
			'''
				MATCH (n)
				RETURN n.length
			'''
		);
	}

	@Test
	def void testSnbQ9() {
		process(
			"Q9",
			'''
			MATCH (:Person)-[:KNOWS*1..2]-(friend:Person)<-[:HAS_CREATOR]-(message)
			WHERE message.creationDate < "x"
			RETURN DISTINCT message, friend
			ORDER BY message.creationDate DESC, message.id ASC
			LIMIT 20
			'''
		)
	}


	@Test
	def void test1() {
		// arrange
		//val ctr = process("hello", "MATCH (n) WHERE true = false RETURN n") // TODO the exception from this
		val ctr = process("hello", "MATCH (n) WHERE 1 = 1 RETURN n")
		RelalgUtil.save(ctr, "testModel1")
//		println(ctr.convert)

		// act
		ctr.performSimpleOptimization
//		println(ctr.convert)
	}

	@Test
	def void constantFolding() {
		process('test-constant-folding', '''MATCH (n) WHERE 1=1 RETURN n''')
	}
	
	@Test
	def void undirectedEdges1() {
		process('undirectedEdges-1', '''MATCH (n)-[r1:REL1]->(m)-[r2:REL2]-(o) RETURN n''')
	}

	@Test
	def void undirectedEdges2() {
		process('undirectedEdges-2', '''MATCH (n)-[r1:REL1]->(m)-[r2:REL2]-(o) RETURN n''')
	}

}
