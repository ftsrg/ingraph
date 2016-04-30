package hu.bme.mit.ingraph.rete.visitors;

public interface ReteVisitable {

	public <R> R accept(final ReteVisitor<? extends R> visitor);
	
}
