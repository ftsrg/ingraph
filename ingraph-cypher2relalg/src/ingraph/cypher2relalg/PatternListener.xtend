package ingraph.cypher2relalg

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.PatternContext

class PatternListener extends CypherBaseListener {

	override enterPattern(PatternContext ctx) {
		println("[entered pattern]")
	}

}
