package hu.bme.mit.ingraph.algebra.operators.visitors;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public class PrinterVisitor implements TreeVisitor<Void> {
	
	protected PrinterVisitor() {
		
	}
	
	public static PrinterVisitor create() {
		return new PrinterVisitor();
	}
	
	public Void visit(final ProductionOperator operator) {
		System.out.println("Production operation");
		operator.getParent().accept(this);
		return null;
	}

	public Void visit(final JoinOperator operator) {
		System.out.println("Join operation: " + operator.getLms() + " " + operator.getRms());
		operator.getLeftParent().accept(this);
		operator.getRightParent().accept(this);
		return null;
	}

	public Void visit(final InputOperator operator) {
		System.out.println("Input node: " + operator.getName());
		return null;
	}

}
