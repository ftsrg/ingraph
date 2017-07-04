package ingraph.relalg.util

import com.google.common.collect.ImmutableMap
import relalg.Operator

class SchemaToMap {

  def schemaToMapNames(op: Operator): java.util.Map[String, Integer] =  {
    val mapBuilder = new ImmutableMap.Builder[String, Integer]()
    for (i <- 0 to op.getInternalSchema.size-1) {
      mapBuilder.put(op.getInternalSchema.get(i).fullName, i)
    }
    return mapBuilder.build
  }

}
