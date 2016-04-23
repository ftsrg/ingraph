package hu.bme.mit.ingraph.tinkerpop.join;

import java.util.Collection;
import java.util.List;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

public abstract class AbstractJoin {

	protected MutableMultimap<Tuple, Tuple> primaryIndexer = Multimaps.mutable.bag.empty();
	protected MutableMultimap<Tuple, Tuple> secondaryIndexer = Multimaps.mutable.bag.empty();
	protected final List<Integer> primaryMask;
	protected final List<Integer> secondaryMask;

	public AbstractJoin(final List<Integer> primaryMask, final List<Integer> secondaryMask) {
		this.primaryMask = primaryMask;
		this.secondaryMask = secondaryMask;
	}

	protected void index(final MutableMultimap<Tuple, Tuple> indexer, final List<Integer> mask, final Collection<Tuple> tuples) {
		for (final Tuple tuple : tuples) {
			final Tuple extracted = tuple.extract(mask);
			indexer.put(extracted, tuple);
		}
	}

	public abstract Collection<Tuple> join(final Collection<Tuple> primaryInput, final Collection<Tuple> secondaryInput);

}