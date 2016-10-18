package ingraph.cypher2relalg

import ingraph.cypherparser.CypherParser
import ingraph.emf.util.PrettyPrinter
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import relalg.RelalgFactory
import relalg.RelationalAlgebraContainer

class Cypher2RelAlg {
	
	def static RelationalAlgebraContainer processFile(String queryFile) {
		val cypher = CypherParser.parseFile(queryFile)
		return processCypher(cypher)
	}
		
	
	def static RelationalAlgebraContainer processString(String queryString) {
		val cypher = CypherParser.parseString(queryString)
		return processCypher(cypher)		
	}

	def static RelationalAlgebraContainer processCypher(Cypher cypherQuery) {
		val container = RelalgFactory.eINSTANCE.createRelationalAlgebraContainer

		val singleQuery = cypherQuery.statement as SingleQuery
		singleQuery.clauses.filter(typeof(Match)).forEach [
			println(PrettyPrinter.prettyPrint(it))
		]
		singleQuery.clauses.filter(typeof(Return)).forEach [
			println(PrettyPrinter.prettyPrint(it))
		]

		container
	}
	
}