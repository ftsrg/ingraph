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

  protected extension SchemaInferencer schemaInferencer = new SchemaInferencer
  protected extension TupleInferencer tupleInferencer = new TupleInferencer
  protected extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation

  val config = RelalgSerializerConfig.builder.consoleOutput(false).build
  val drawer = new RelalgTreeSerializer(config)

  def process(String query) {
    val cypher = CypherParser.parseFile("trainbenchmark/" + query)
    val container = Cypher2Relalg.processCypher(cypher)

    container.transformToRete
    RelalgUtil.save(container, "query-models/" + query)
  // drawer.serialize(container, "queries/" + query)
  }

  @Test
  def void test01() {
    val cypher = CypherParser.parseString('''
      MATCH (p:Person)
      RETURN p.name AS name
      ORDER BY p.name
      LIMIT 1
    ''')
    val container = Cypher2Relalg.processCypher(cypher)
    container.transformToRete
    container.addSchemaInformation
    container.addDetailedSchemaInformation
  }

}
