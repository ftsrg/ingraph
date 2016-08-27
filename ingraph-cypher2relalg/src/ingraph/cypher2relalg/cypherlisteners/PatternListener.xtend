package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherParser.PatternContext
import ingraph.antlr.CypherParser.PatternElementChainContext

class PatternListener extends RelalgBaseCypherListener {

	override enterPattern(PatternContext ctx) {
		println("[entered pattern]")
	}
	
	override enterPatternElementChain(PatternElementChainContext ctx) {
		println("[chain]")
	}

}
