package hu.bme.mit.ingraph.algebra.operations.visitors;

public interface Visitable {

	public <R> R accept(final TreeVisitor<? extends R> visitor);
	
}
