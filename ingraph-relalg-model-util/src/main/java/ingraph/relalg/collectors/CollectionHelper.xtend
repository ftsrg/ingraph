package ingraph.relalg.collectors

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import com.google.common.collect.Sets
import java.util.List
import java.util.ArrayList

class CollectionHelper {

	def <T> List<T> union(Iterable<? extends T>... lists) {
		Lists.newArrayList(Iterables.concat(lists))
	}
	
	def <T> List<T> uniqueUnion(Iterable<? extends T>... lists) {
		val result = new ArrayList<T> 
		union(lists).forEach[
			if (!result.map[toString].contains(it.toString)) {
				result.add(it)
			}
		]
		result
	}

	def <T> List<T> minus(Iterable<? extends T> iterable1, Iterable<? extends T> iterable2) {
		val set1 = Sets.newLinkedHashSet(iterable1)
		val set2 = Sets.newLinkedHashSet(iterable2)
		Sets.difference(set1, set2).toList
	}


}
