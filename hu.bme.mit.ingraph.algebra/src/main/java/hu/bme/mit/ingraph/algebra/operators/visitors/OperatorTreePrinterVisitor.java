package hu.bme.mit.ingraph.algebra.operators.visitors;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public class OperatorTreePrinterVisitor extends Printer implements OperatorTreeVisitor<String> {

	protected OperatorTreePrinterVisitor(int indentationStep) {
		super(indentationStep);
	}

	public static OperatorTreePrinterVisitor create(final int indentationStep) {
		return new OperatorTreePrinterVisitor(indentationStep);
	}

	@Override
	public String visit(final InputOperator operator) {
		return "Input node [" + operator.getName() + "]\n";
	}

	@Override
	public String visit(final JoinOperator operator) {
		String joinString = "Join operation " + operator.getLms() + " " + operator.getRms() + "\n";

		increaseIndentation();
		joinString = joinString + indent("(L) " + operator.getLeftParent().accept(this));
		joinString = joinString + indent("(R) " + operator.getRightParent().accept(this));
		decreaseIndentation();

		return joinString;
	}

	@Override
	public String visit(final ProductionOperator operator) {
		String productionString = "Production operation\n";

		increaseIndentation();
		productionString = productionString + indent(operator.getParent().accept(this));
		decreaseIndentation();

		return productionString;
	}

}
