package ingraph.relalg.util

import com.google.common.collect.ImmutableMap
import java.util.Map
import relalg.Operator

class SchemaToMap {
 
	def Map<String, Integer> schemaToMapNames(Operator op) {
		val mapBuilder = new ImmutableMap.Builder<String, Integer>()
		for (i : 0 ..< op.internalSchema.length) {
			mapBuilder.put(op.internalSchema.get(i).toString, i)
		}
		return mapBuilder.build
	}

}
