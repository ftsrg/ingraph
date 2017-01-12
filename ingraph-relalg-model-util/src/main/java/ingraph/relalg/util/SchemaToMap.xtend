package ingraph.relalg.util

import com.google.common.collect.ImmutableMap
import relalg.Operator
import relalg.Variable

class SchemaToMap {

  // TODO this might need some caching as it can be invoked millions of times from IRE 
  def schemaToMap(Operator op) {
    val mapBuilder = new ImmutableMap.Builder<Variable, Integer>()
    for (i : 0 ..< op.detailedSchema.length) {
      mapBuilder.put(op.detailedSchema.get(i), i)
    }
    
    val map = mapBuilder.build
    return map
  }

}
