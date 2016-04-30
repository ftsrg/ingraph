package hu.bme.mit.ingraph.rete.visitors;

import org.apache.commons.lang3.StringUtils;

import hu.bme.mit.ingraph.rete.nodes.InputNode;
import hu.bme.mit.ingraph.rete.nodes.JoinNode;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetePrinterVisitor implements ReteVisitor<String> {
	
	protected final int indentation;
	protected final int indentationStep;	
	
	public static RetePrinterVisitor create(final int indentation, final int indentationStep) {
		return new RetePrinterVisitor(indentation, indentationStep);
	}
	
	protected String indent(int indentation) {
		return StringUtils.repeat(" ", indentation);
	}

	@Override
	public String visit(JoinNode node) {
		return indent(indentation) + "Join node, left mask: " + operator.getLms() + ", right mask: " + operator.getRms() + "\n" + 
				indent(indentation) + "- Left parent:\n" +
				leftParent.prettyPrint(indentation + indentationStep, indentationStep) + "\n" +
				indent(indentation) + "- Right parent:\n" +
				rightParent.prettyPrint(indentation + indentationStep, indentationStep);
	}

	@Override
	public String visit(InputNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ProductionNode node) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
