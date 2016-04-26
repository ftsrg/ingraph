package hu.bme.mit.ingraph.algebra.operations.visitors;

public interface Visitable {

	public long accept(final Visitor visitor);
	
}
