package ingraph.expressionparser.wrapper;

import ingraph.expressionparser.ExpressionParser;
import relalg.Expression;
import relalg.Variable;
import scala.Function1;
import scala.collection.IndexedSeq;
import scala.collection.immutable.Map;

public class ExpressionParserWrapper {

	public static Function1<IndexedSeq<Object>, Object> parse(Expression expression, Map<Variable, Integer> lookup) {
		return ExpressionParser.parse(expression, lookup);
	}

}
