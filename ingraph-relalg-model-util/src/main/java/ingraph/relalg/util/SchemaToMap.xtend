package ingraph.relalg.util

import com.google.common.collect.ImmutableMap
import java.util.Map
import relalg.Operator
import relalg.Variable

class SchemaToMap {
 
	def Map<Variable, Integer> schemaToMap(Operator op) {
		val mapBuilder = new ImmutableMap.Builder<Variable, Integer>()
		for (i : 0 ..< op.fullSchema.length) {
			mapBuilder.put(op.fullSchema.get(i), i)
		}
		return mapBuilder.build
	}

	def Map<String, Integer> schemaToMapNames(Operator op) {
		val mapBuilder = new ImmutableMap.Builder<String, Integer>()
		for (i : 0 ..< op.fullSchema.length) {
			mapBuilder.put(op.fullSchema.get(i).toString, i)
		}
		return mapBuilder.build
	}

}
