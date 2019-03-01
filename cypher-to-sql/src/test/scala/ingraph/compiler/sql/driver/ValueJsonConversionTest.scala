package ingraph.compiler.sql.driver

import ingraph.compiler.sql.driver.ValueJsonConversion._
import ingraph.compiler.sql.driver.ValueJsonConversionTest._
import org.neo4j.driver.internal.value._
import org.neo4j.driver.internal.{InternalNode, InternalPath, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.scalactic.source
import org.scalactic.source.Position
import org.scalatest.FunSuite

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

class ValueJsonConversionTest extends FunSuite {
  testParameters.foreach { case (value, testName, pos) =>
    test(testName) {
      println(value)

      val jsonString = gson.toJson(value, classOf[Value])
      println(jsonString)

      val deserialized = gson.fromJson(jsonString, classOf[Value])

      assert(value == deserialized)
    }(pos)
  }
}

object ValueJsonConversionTest {
  val testValues: ArrayBuffer[Value] = ArrayBuffer.empty
  val testParameters: ArrayBuffer[(Value, String, Position)] = ArrayBuffer.empty

  def addTest(value: Value, testName: String = null)(implicit pos: source.Position): Unit = {
    testValues += value
    testParameters += ((value, Option(testName).getOrElse(value.getClass.getSimpleName), pos))
  }

  private val stringValue = new StringValue("John")
  private val integerValue = new IntegerValue(101)
  private val propertiesMap = Map[String, Value]("name" -> stringValue).asJava

  addTest(new MapValue(propertiesMap))
  addTest(new BytesValue(Array[Byte](0, 42, 127, -128)))
  addTest(new ListValue(stringValue, integerValue))
  addTest(new NodeValue(new InternalNode(5, List("Label1", "Label2").asJavaCollection, propertiesMap)))
  addTest(new RelationshipValue(new InternalRelationship(42, 10, 20, "Edge_Type_1", propertiesMap)))
  addTest(new PathValue(new InternalPath(
    new InternalNode(0),
    new InternalRelationship(101, 0, 1, "TYPE_A"),
    new InternalNode(1)
  )))
  addTest(BooleanValue.FALSE)
  addTest(BooleanValue.TRUE)
  addTest(NullValue.NULL)
  addTest(stringValue)
  addTest(integerValue)
  addTest(new FloatValue(3.14))
}
