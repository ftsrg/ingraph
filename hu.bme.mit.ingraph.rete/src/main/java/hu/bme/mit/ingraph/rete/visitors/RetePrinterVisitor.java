package hu.bme.mit.ingraph.rete.visitors;

import hu.bme.mit.ingraph.algebra.operators.visitors.Printer;
import hu.bme.mit.ingraph.rete.nodes.InputNode;
import hu.bme.mit.ingraph.rete.nodes.JoinNode;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;

public class RetePrinterVisitor extends Printer implements ReteVisitor<String> {

	protected RetePrinterVisitor(int indentationStep) {
		super(indentationStep);
	}

	public static RetePrinterVisitor create(final int indentationStep) {
		return new RetePrinterVisitor(indentationStep);
	}

	@Override
	public String visit(InputNode node) {
		return "Input node [" + node.getOperator().getName() + "]\n";
	}

	@Override
	public String visit(JoinNode node) {
		String joinString = "Join node " + node.getOperator().getLms() + " " + node.getOperator().getRms() + "\n";

		increaseIndentation();
		joinString = joinString + indent("(L) " + node.getLeftParent().accept(this));
		joinString = joinString + indent("(R) " + node.getRightParent().accept(this));
		decreaseIndentation();

		return joinString;
	}

	@Override
	public String visit(ProductionNode node) {
		String productionString = "Production node\n";

		increaseIndentation();
		productionString = productionString + indent(node.getParent().accept(this));
		decreaseIndentation();

		return productionString;
	}

}
