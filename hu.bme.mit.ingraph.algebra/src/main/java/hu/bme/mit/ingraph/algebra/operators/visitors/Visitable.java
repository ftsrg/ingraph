package hu.bme.mit.ingraph.algebra.operators.visitors;

public interface Visitable {

	public <R> R accept(final TreeVisitor<? extends R> visitor);
	
}
