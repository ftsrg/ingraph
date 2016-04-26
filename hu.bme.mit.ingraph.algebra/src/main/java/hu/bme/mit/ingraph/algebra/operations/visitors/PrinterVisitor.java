package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public class PrinterVisitor implements Visitor {

	public long visit(final ProductionOperation node) {
		System.out.println("Production node");
		node.getParent().accept(this);
		return 0;
	}

	public long visit(final JoinOperation node) {
		System.out.println("Join node: " + node.getLms() + " " + node.getRms());
		node.getLeftParent().accept(this);
		node.getRightParent().accept(this);
		return 0;
	}

	public long visit(InputOperation node) {
		System.out.println("Input node: " + node.getName());
		return 0;
	}

}
