package ingraph.compiler.sql.driver

import java.lang.reflect.Type

import com.google.gson._

// https://stackoverflow.com/a/8683689
class TypedJsonAdapter[T](val allowedClasses: Class[_ <: T]*) extends JsonSerializer[T] with JsonDeserializer[T] {
  val typeKey = "type"
  val valueKey = "value"

  // Whitelist allowed classes to prevent unsafe reflection
  val classMap: Map[String, Class[_ <: T]] = allowedClasses
    .map(clazz => clazz.getSimpleName -> clazz)
    .toMap

  override def serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    val clazz = src.getClass
    assert(allowedClasses.contains(clazz), clazz + " is not allowed for dynamic deserialization. Please check the whitelist.")
    val className = clazz.getSimpleName

    val valueJson = context.serialize(src)

    val retValue = new JsonObject
    retValue.addProperty(typeKey, className)
    retValue.add(valueKey, valueJson)

    retValue
  }

  override def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T = {
    val jsonObject = json.getAsJsonObject
    val className = jsonObject.getAsJsonPrimitive(typeKey).getAsString

    val clazz = classMap(className)

    context.deserialize(jsonObject.get(valueKey), clazz)
  }
}


