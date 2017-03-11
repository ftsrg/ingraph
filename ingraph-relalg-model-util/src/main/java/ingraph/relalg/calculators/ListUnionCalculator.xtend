package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List

class ListUnionCalculator {

	def <T> union(List<? extends T>... lists) {
		Lists.newArrayList(Iterables.concat(lists))
	}

}