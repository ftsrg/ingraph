package pkg

import cypher.grammar.CypherBaseListener
import cypher.grammar.CypherParser

class MyCypherListener : CypherBaseListener() {

    override fun enterMatch(ctx: CypherParser.MatchContext) {
        println("Enter: " + ctx.text)
    }

    override fun exitMatch(ctx: CypherParser.MatchContext) {
        println("Exit: " + ctx.text)
    }

    override fun enterVariable(ctx: CypherParser.VariableContext) {
        println("var: " + ctx.text)
    }

    override fun enterReturn(ctx: CypherParser.ReturnContext) {
        println("Enter: " + ctx.text)
    }

    override fun exitReturn(ctx: CypherParser.ReturnContext) {
        println("Exit " + ctx.text)
    }

    override fun enterWhere(ctx: CypherParser.WhereContext) {
        println("Enter: " + ctx.text)
    }

    override fun exitWhere(ctx: CypherParser.WhereContext) {
        println("Exit " + ctx.text)
    }
}
