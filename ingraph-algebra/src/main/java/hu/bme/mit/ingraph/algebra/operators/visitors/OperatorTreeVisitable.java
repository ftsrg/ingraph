package hu.bme.mit.ingraph.algebra.operators.visitors;

public interface OperatorTreeVisitable {

	public <R> R accept(final OperatorTreeVisitor<? extends R> visitor);
	
}
