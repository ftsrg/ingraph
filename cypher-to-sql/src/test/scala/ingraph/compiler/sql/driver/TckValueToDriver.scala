package ingraph.compiler.sql.driver

import org.opencypher.tools.tck.values._

import scala.collection.JavaConverters._

object TckValueToDriver extends (CypherValue => AnyRef) {
  override def apply(value: CypherValue): AnyRef =
    (value match {
      case CypherString(s) => s
      case CypherPropertyMap(properties) => properties.map { case (k, v) => k -> TckValueToDriver(v) }.asJava
      case CypherProperty(k, v) => k -> TckValueToDriver(v)
      case list: CypherList => list.elements.map(TckValueToDriver).asJava
      case CypherFloat(d) => d
      case CypherBoolean(b) => b
      case CypherInteger(l) => l
      case CypherNull => null
    })
      // to box primitives
      .asInstanceOf[AnyRef]
}
