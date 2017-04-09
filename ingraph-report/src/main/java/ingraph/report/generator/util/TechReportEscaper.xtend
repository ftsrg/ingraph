package ingraph.report.generator.util

class TechReportEscaper {

	def escape(String s) {
		s //
			.replace("#", " no.") //
			.replace("_", '''\_''') //
			.replace("`", "'") //
			.replace("[", "{[}") //
			.replace("]", "{]}") //
	}

	def cleanup(String s) {
		s //
			.replace('''"""''', "") // """
			.replace("'''", "") // '''
			.replaceAll("\n   ", "\n") // indentation
			.replaceAll("^\n", "") // newline at the start
			.replaceAll("\n$", "") // newline at the end
	}

	def unindent(String s) {
		s //
			.replaceAll('''^\s*''', "") //
			.replaceAll('''\n\s*''', "\n") //
			.replaceAll('''\n$''', "") //
	}
	
	def toLabel(String s) {
		s //
			.replaceAll(" ", "_") //
	}

}