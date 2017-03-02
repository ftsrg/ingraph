package ingraph.relalg2tex.converters.elementconverters

import java.util.List

class TupleConverter {
	
	def convertTuple(List<Integer> tuple) {
		'''\[ \var{«tuple.map[
			if (it == -1) '''\#''' else it
		].join(", ")»} \]'''
	} 
	
	
}