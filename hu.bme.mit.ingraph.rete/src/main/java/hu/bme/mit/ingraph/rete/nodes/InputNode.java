package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;

public class InputNode extends AbstractNode<InputOperator> {

	public InputNode(InputOperator operator) {
		super(operator);
	}
	
	@Override
	public String prettyPrint(int indentation, int indentationStep) {
		return indent(indentation) + "Input node [" + operator.getName() + "]";
	}

}
