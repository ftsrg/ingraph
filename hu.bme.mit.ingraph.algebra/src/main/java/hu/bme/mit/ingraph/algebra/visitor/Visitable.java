package hu.bme.mit.ingraph.algebra.visitor;

public interface Visitable {

	public long accept(final Visitor visitor);
	
}
