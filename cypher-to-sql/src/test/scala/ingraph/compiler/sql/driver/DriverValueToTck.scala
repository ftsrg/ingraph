package ingraph.compiler.sql.driver

import java.lang.{Boolean => javaBoolean, Double => javaDouble, Long => javaLong}
import java.util.{List => javaList, Map => javaMap}

import org.neo4j.driver.v1.types.Path.Segment
import org.neo4j.driver.v1.types.{Node, Path, Relationship}
import org.opencypher.tools.tck.values._

import scala.collection.JavaConverters._

object DriverValueToTck extends (AnyRef => CypherValue) {
  override def apply(value: AnyRef): CypherValue =
    value match {
      case long: javaLong => CypherInteger(long)
      case double: javaDouble => CypherFloat(double)
      case boolean: javaBoolean => CypherBoolean(boolean)
      case string: String => CypherString(string)
      case null => CypherNull
      case node: Node => convert(node)
      case relationship: Relationship => convert(relationship)
      case path: Path => CypherPath(convert(path.start), convert(path))
      case map: javaMap[String, AnyRef] => convert(map)
      case list: javaList[AnyRef] => CypherOrderedList(list.asScala.map(DriverValueToTck).toList)
    }

  private def convert(node: Node): CypherNode =
    CypherNode(node.labels().asScala.toSet, convert(node.asMap))

  private def convert(relationship: Relationship): CypherRelationship =
    CypherRelationship(relationship.`type`(), convert(relationship.asMap))

  private def convert(segments: java.lang.Iterable[Segment]): List[Connection] = {
    segments.asScala
      .map { segment =>
        val cypherRelationship = convert(segment.relationship)
        val cypherEndNode = convert(segment.end)

        if (segment.start.id == segment.relationship.startNodeId)
          Forward(cypherRelationship, cypherEndNode)
        else
          Backward(cypherRelationship, cypherEndNode)
      }
      .toList
  }

  private def convert(map: javaMap[String, AnyRef]): CypherPropertyMap =
    CypherPropertyMap(map.asScala.map { case (k, v) => k -> DriverValueToTck(v) }.toMap)
}
