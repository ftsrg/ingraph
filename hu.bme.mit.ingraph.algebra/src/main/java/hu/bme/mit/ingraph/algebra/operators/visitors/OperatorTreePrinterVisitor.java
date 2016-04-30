package hu.bme.mit.ingraph.algebra.operators.visitors;

import org.apache.commons.lang.StringUtils;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public class OperatorTreePrinterVisitor implements OperatorTreeVisitor<String> {

	protected int indentation = 0;
	protected final int indentationStep;

	public OperatorTreePrinterVisitor(int indentationStep) {
		this.indentationStep = indentationStep;
	}

	public static OperatorTreePrinterVisitor create(final int indentationStep) {
		return new OperatorTreePrinterVisitor(indentationStep);
	}

	protected String indent(String string) {
		return StringUtils.repeat(" ", indentation) + string;
	}

	@Override
	public String visit(final ProductionOperator operator) {
		String productionString = "Production operation\n";
		indentation += indentationStep;
		productionString = productionString + indent(operator.getParent().accept(this));
		indentation -= indentationStep;
		return productionString;
	}

	@Override
	public String visit(final JoinOperator operator) {
		String joinString = "Join operation " + operator.getLms() + " " + operator.getRms() + "\n";
		indentation += indentationStep;
		joinString = joinString + indent(operator.getLeftParent().accept(this)); 
		joinString = joinString + indent(operator.getRightParent().accept(this));
		indentation -= indentationStep;
		return joinString;
	}

	@Override
	public String visit(final InputOperator operator) {
		return "Input node [" + operator.getName() + "]\n";
	}

}
