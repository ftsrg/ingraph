package hu.bme.mit.ingraph.rete;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

import hu.bme.mit.ingraph.rete.data.Tuple;

public abstract class AbstractJoinNode {

	protected MutableMultimap<Tuple, Tuple> leftIndexer = Multimaps.mutable.bag.empty();
	protected MutableMultimap<Tuple, Tuple> rightIndexer = Multimaps.mutable.bag.empty();
	
	
}