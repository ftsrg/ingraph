package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraAttributeInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgSerializerConfig
import ingraph.relalg2tex.serializers.RelalgTreeSerializer
import org.junit.Ignore
import org.junit.Test

class ReteSandboxTest {

  extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
  extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
  extension ExtraAttributeInferencer extraAttributeInferencer = new ExtraAttributeInferencer
  extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

  val config = RelalgSerializerConfig.builder.consoleOutput(false).standaloneDocument(true).build
  val drawer = new RelalgTreeSerializer(config)

  def process(String query, String cypher) {
    // search-based
    val containerSearchBased = Cypher2Relalg.processString(cypher)
    containerSearchBased.inferBasicSchema
    drawer.serialize(containerSearchBased, "sandbox/" + query + "-search")
    RelalgUtil.save(containerSearchBased, "query-models/" + query + "-search")

    // Rete
    val containerRete = Cypher2Relalg.processString(cypher)
    containerRete.transformToRete
    containerRete.inferBasicSchema
    containerRete.inferExtraAttributes
    containerRete.inferFullSchema
    drawer.serialize(containerRete, "sandbox/" + query + "-rete")
    RelalgUtil.save(containerSearchBased, "query-models/" + query + "-rete")
  }

  @Test
  def void t() {
    process("t", '''
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
    process("test-Simple",
    '''
    MATCH
      (a {name: 'a'})-[:REL]->(x),
      (d {name: 'd'})-[:REL]->(y),
      ( (x)-[r:REL*]->(y) )
    RETURN r
    '''
    );
  }

}
