package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.DetailedSchemaInferencer
import ingraph.relalg.inferencers.ExtraAttributeInferencer
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.serializers.RelalgTreeSerializer
import org.junit.Ignore
import org.junit.Test

class ReteSandboxTest {

  extension SchemaInferencer schemaInferencer = new SchemaInferencer
  extension ExtraAttributeInferencer tupleInferencer = new ExtraAttributeInferencer
  extension DetailedSchemaInferencer detailedSchemaInferencer = new DetailedSchemaInferencer
  extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation

  val config = RelalgSerializerConfig.builder.consoleOutput(false).standaloneDocument(true).build
  val drawer = new RelalgTreeSerializer(config)

  def process(String query, String cypher) {
    val containerSearchBased = Cypher2Relalg.processString(cypher)

    containerSearchBased.addSchemaInformation
    drawer.serialize(containerSearchBased, "sandbox/" + query + "-search")
    
    RelalgUtil.save(containerSearchBased, "query-models/" + query + "-search")
    
    val containerRete = Cypher2Relalg.processString(cypher)
    containerRete.transformToRete
    containerRete.addSchemaInformation
    containerRete.addExtraAttributes
    containerRete.addDetailedSchemaInformation
    drawer.serialize(containerRete, "sandbox/" + query + "-rete")
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
