package ingraph.ire

import hu.bme.mit.ire.Transaction
import ingraph.bulkloader.csv.loader.MassCsvLoader
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg2rete.Relalg2ReteTransformationAndInferencer

class IngraphAdapter(querySpecification: String) {
  private val plan = Relalg2ReteTransformationAndInferencer.apply(Cypher2Relalg.processString(querySpecification))

  val engine = EngineFactory.createQueryEngine(plan.getRootExpression)

  private val indexer = new Indexer()
  private val listener = new GetOperatorTransformer(
    engine.vertexConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap,
    engine.edgeConverters.map(kv => kv._1.toSet -> kv._2.toSet).toMap, engine.inputLookup, indexer) with LongIdParser
  def readCsv(nodeFilenames: Map[String, List[String]], relationshipFilenames: Map[String, String], transaction: Transaction) {
    import scala.collection.JavaConverters._

    // sorry :-)
    val loader = new MassCsvLoader(nodeFilenames.map(kv => kv._1 -> java.util.Arrays.asList(kv._2: _*)).asJava
      .asInstanceOf[java.util.Map[String, java.util.Collection[String]]], relationshipFilenames.asJava)
    for (node <- loader.getNodes.asScala) {
      indexer.addVertex(node)
    }
    for (relationship <- loader.getRelationships.asScala) {
      indexer.addEdge(relationship)
    }

    listener.loadEverythingFromIndexer(transaction)
  }
}
