package ingraph.relalg2tex.converters.elementconverters

import relalg.NamedElement

class StringEscaper {
	
	def escape(String s) {
		s
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
		// these are required when displaying square brackets in optional arguments
		// such as \subsection[toctitle]{title}
		.replace('''[''', '''{[}''')
		.replace(''']''', '''{]}''')
	}

	def escapedName(NamedElement element) {
		'''«element?.name?.escape»'''
	}
	
}
