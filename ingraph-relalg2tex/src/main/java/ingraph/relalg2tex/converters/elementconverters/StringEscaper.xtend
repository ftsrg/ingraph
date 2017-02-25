package ingraph.relalg2tex.converters.elementconverters

import relalg.NamedElement

class StringEscaper {
	
	def escape(CharSequence s) {
		s.toString
		.replace('\b', '''\b''')
		.replace('\f', '''\f''')
		.replace('\n', '''\n''')
		.replace('\r', '''\r''')
		.replace('\t', '''\t''')
		.replace('''{''', '''\string{''')
		.replace('''}''', '''\string}''')
		.replaceAll('''\\(?!string[{}])''', '''\\backslash{}''')
		.replace('''_''', '''\_''')
		.replace(''' ''', '''\ ''')
	}

	def escapedName(NamedElement element) {
		'''«element?.name?.escape»'''
	}
	
}
