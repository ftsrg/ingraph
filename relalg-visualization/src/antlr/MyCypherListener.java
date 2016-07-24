package antlr;

import cypher.grammar.CypherBaseListener;
import cypher.grammar.CypherParser;
import cypher.grammar.CypherParser.MatchContext;
import cypher.grammar.CypherParser.ReturnContext;
import cypher.grammar.CypherParser.VariableContext;
import relalg.InputRelation;
import relalg.RelalgFactory;

import java.util.HashMap;
import java.util.Map;

public class MyCypherListener extends CypherBaseListener {

    final Map<String, InputRelation> relations = new HashMap<>();
    final RelalgFactory factory = RelalgFactory.eINSTANCE;


    @Override
    public void enterRelTypeName(CypherParser.RelTypeNameContext ctx) {
        final String type = ctx.getText();
        System.out.println("Enter reltypename: " + type);

        if (!relations.containsKey(type)) {
            final InputRelation r = factory.createInputRelation();
            r.setType(type);

            relations.put(type, r);
        }
    }

    @Override
    public void enterMatch(MatchContext ctx) {
        

        System.out.println("Enter match: " + ctx.getText());
    }

//    @Override
//    public void exitMatch(MatchContext ctx) {
//        System.out.println("Exit match: " + ctx.getText());
//    }

    @Override
    public void enterWhere(CypherParser.WhereContext ctx) {
        System.out.println("Enter where: " + ctx.getText());
    }

//    @Override
//    public void exitWhere(CypherParser.WhereContext ctx) {
//        System.out.println("Exit where: " + ctx.getText());
//    }

    @Override
    public void enterVariable(VariableContext ctx) {
        //System.out.println("var: " + ctx.getText());
    }

    @Override
    public void enterReturn(ReturnContext ctx) {
        System.out.println("Enter: " + ctx.getText());
    }

//    @Override
//    public void exitReturn(ReturnContext ctx) {
//        System.out.println("Exit: " + ctx.getText());
//    }

}
