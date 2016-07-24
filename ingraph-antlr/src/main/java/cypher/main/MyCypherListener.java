package cypher.main;

import cypher.grammar.CypherBaseListener;
import cypher.grammar.CypherParser.MatchContext;
import cypher.grammar.CypherParser.ReturnContext;
import cypher.grammar.CypherParser.VariableContext;
import relalg.AlgebraExpression;

public class MyCypherListener extends CypherBaseListener {

	@Override
	public void enterMatch(MatchContext ctx) {
		System.out.println("Enter: " + ctx.getText());
		AlgebraExpression a;
	}

	@Override
	public void exitMatch(MatchContext ctx) {
		System.out.println("Exit: " + ctx.getText());
	}

	@Override
	public void enterVariable(VariableContext ctx) {
		System.out.println("var: " + ctx.getText());
	}

	@Override
	public void enterReturn(ReturnContext ctx) {
		System.out.println("Enter: " + ctx.getText());
	}

	@Override
	public void exitReturn(ReturnContext ctx) {
		System.out.println("Enter: " + ctx.getText());
	}
}
