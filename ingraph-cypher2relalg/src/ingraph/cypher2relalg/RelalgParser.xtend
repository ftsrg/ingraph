package ingraph.cypher2relalg

import java.io.File
import org.apache.commons.io.FileUtils
import relalg.RelalgFactory

class RelalgParser {

	def static parseFile(String queryName) {
		val filepath = "../queries/" + queryName + ".cyp"
		val query = FileUtils.readFileToString(new File(filepath), "UTF-8")
		parse(query)
	}	
	
	def static parse(String query) {
		val container = RelalgFactory.eINSTANCE.createRelationalAlgebraContainer
		container
	}

}
