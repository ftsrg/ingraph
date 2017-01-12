package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg.util.TupleInferencer
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test

class ReteSandboxTest {

  extension SchemaInferencer schemaInferencer = new SchemaInferencer
  extension TupleInferencer tupleInferencer = new TupleInferencer
  extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation

  val config = RelalgSerializerConfig.builder.consoleOutput(false).standaloneDocument(true).build
  val drawer = new RelalgTreeSerializer(config)

  def process(String query, String cypher) {
    val container = Cypher2Relalg.processString(cypher)
    container.transformToRete
    container.addSchemaInformation
    container.addDetailedSchemaInformation
    
//    RelalgUtil.save(container, "query-models/" + query)
    drawer.serialize(container, "queries/" + query)
  }

  @Test
  def void test01() {
    process("test01", '''
      MATCH (p:Person)
      RETURN p.name AS name
      ORDER BY p.name
      LIMIT 1
    ''')
  }
  
  @Test
  def void test02() {
    process("test02", '''
      MATCH (n)
      WHERE n.something="emfsucks"
      RETURN n.something
    ''')
  }

}
