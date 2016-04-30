package hu.bme.mit.ingraph.algebra.operators.visitors;

import org.apache.commons.lang.StringUtils;

public class Printer {
	
	protected int indentation = 0;
	protected final int indentationStep;

	public Printer(int indentationStep) {
		this.indentationStep = indentationStep;
	}

	protected String indent(String string) {
		return StringUtils.repeat(" ", indentation) + string;
	}

	protected void decreaseIndentation() {
		indentation -= indentationStep;
	}

	protected void increaseIndentation() {
		indentation += indentationStep;	
	}
	
}
