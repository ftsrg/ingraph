package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public class PrinterVisitor implements TreeVisitor<Void> {

	public Void visit(final ProductionOperation operation) {
		System.out.println("Production operation");
		operation.getParent().accept(this);
		return null;
	}

	public Void visit(final JoinOperation operation) {
		System.out.println("Join operation: " + operation.getLms() + " " + operation.getRms());
		operation.getLeftParent().accept(this);
		operation.getRightParent().accept(this);
		return null;
	}

	public Void visit(final InputOperation operation) {
		System.out.println("Input node: " + operation.getName());
		return null;
	}

}
