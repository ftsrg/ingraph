package hu.bme.mit.ingraph.rete;

import java.util.Collection;
import java.util.List;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

import hu.bme.mit.ingraph.rete.data.Tuple;

public abstract class AbstractJoin {

	protected MutableMultimap<Tuple, Tuple> primaryIndexer = Multimaps.mutable.bag.empty();
	protected MutableMultimap<Tuple, Tuple> secondaryIndexer = Multimaps.mutable.bag.empty();
	
	public AbstractJoin(final List<Integer> leftMask, final List<Integer> secondaryMask) {
	}

	protected void index(final MutableMultimap<Tuple, Tuple> indexer, final List<Integer> mask, final Collection<Tuple> tuples) {
		for (final Tuple tuple : tuples) {
			final Tuple extracted = tuple.extract(mask);
			indexer.put(extracted, tuple);
		}
	}

	public abstract Collection<Tuple> join(final Collection<Tuple> leftInput, final Collection<Tuple> rightInput);

}