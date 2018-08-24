package ingraph.compiler.sql.driver

import org.neo4j.driver.internal._
import org.neo4j.driver.internal.value._
import org.neo4j.driver.v1.Value

import scala.collection.JavaConverters._

abstract class JsonGraphElement extends AsValue {
  var id: Long = _
  var properties: java.util.Map[String, Any] = _

  protected def propertiesMap: java.util.Map[String, Value] = properties.asScala.mapValues(SqlDriver.toValue).asJava
}

class JsonVertex extends JsonGraphElement {
  var labels: Array[String] = _

  private def labelsCollection: java.util.Collection[String] = labels.toList.asJava

  override def asValue(): NodeValue = new NodeValue(new InternalNode(id, labelsCollection, propertiesMap))
}

class JsonEdge extends JsonGraphElement {
  var startNode: Long = _
  var endNode: Long = _
  var `type`: String = _

  override def asValue(): RelationshipValue =
    new RelationshipValue(new InternalRelationship(id, startNode, endNode, `type`, propertiesMap))
}
