package ingraph.compiler.sql.driver

import java.lang.reflect.Type

import com.google.gson._
import org.neo4j.driver.internal.InternalPath.SelfContainedSegment
import org.neo4j.driver.internal.value._
import org.neo4j.driver.internal.{InternalNode, InternalPath, InternalRelationship}
import org.neo4j.driver.v1.Value
import org.neo4j.driver.v1.types.Path.Segment
import org.neo4j.driver.v1.types.{Node, Path, Relationship}

import scala.reflect.ClassTag

object ValueJsonConversion {

  private implicit class GsonBuilderExtension(val gsonBuilder: GsonBuilder) extends AnyVal {
    private def registerTypedAdapterInternal[T](clazz: Class[T], allowedClasses: Class[_ <: T]*): GsonBuilder = {
      gsonBuilder.registerTypeAdapter(clazz, new TypedJsonAdapter[T](allowedClasses: _*))
    }

    def registerTypedAdapter[T](allowedClasses: Class[_ <: T]*)(implicit classTag: ClassTag[T]): GsonBuilder = {
      registerTypedAdapterInternal(classTag.runtimeClass.asInstanceOf[Class[Any]], allowedClasses: _*)
    }

    def registerTypedAdapter[T, ConcreteType]()(implicit classTagT: ClassTag[T], classTagConcreteType: ClassTag[ConcreteType]): GsonBuilder = {
      registerTypedAdapterInternal(classTagT.runtimeClass.asInstanceOf[Class[Any]], classTagConcreteType.runtimeClass)
    }

    def registerSingletonValueAdapter[T <: Value](singletonValue: T): GsonBuilder = {
      gsonBuilder.registerTypeAdapter(singletonValue.getClass, new JsonSerializer[T] with JsonDeserializer[Value] {
        override def serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement =
          new JsonObject

        override def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Value =
          singletonValue
      })
    }
  }

  val gson: Gson = (new GsonBuilder)
    .registerSingletonValueAdapter(NullValue.NULL)
    .registerSingletonValueAdapter(BooleanValue.FALSE)
    .registerSingletonValueAdapter(BooleanValue.TRUE)

    .registerTypedAdapter[Value](
    classOf[StringValue],
    classOf[NodeValue],
    classOf[RelationshipValue],
    classOf[NullValue],
    classOf[IntegerValue],
    classOf[MapValue],
    classOf[BytesValue],
    classOf[ListValue],
    classOf[PathValue],
    classOf[FloatValue],
    BooleanValue.FALSE.getClass,
    BooleanValue.TRUE.getClass
  )

    .registerTypedAdapter[Node, InternalNode]
    .registerTypedAdapter[Relationship, InternalRelationship]
    .registerTypedAdapter[Path, InternalPath]
    .registerTypedAdapter[Segment, SelfContainedSegment]

    .create()
}
