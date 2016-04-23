package hu.bme.mit.ingraph.tinkerpop.join;

import java.util.Collection;
import java.util.List;

public class AntiJoin extends AbstractJoin {

	public AntiJoin(final List<Integer> primaryMask, final List<Integer> secondaryMask) {
		super(primaryMask, secondaryMask);
	}

	@Override
	public Collection<Tuple> join(final Collection<Tuple> primaryInput, final Collection<Tuple> secondaryInput) {
		index(primaryIndexer, primaryMask, primaryInput);
		index(secondaryIndexer, secondaryMask, secondaryInput);

		final Collection<Tuple> joinedTuples = null; 
//		primaryIndexer.selectKeysValues()
//		primaryIndexer.entries().parallelStream().filter(entry -> {
//			final Tuple primaryTupleJoinAttributes = entry.getKey();
//			return secondaryIndexer.get(primaryTupleJoinAttributes).isEmpty();
//		}).map(entry -> {
//			final Tuple primaryTuple = entry.getValue();
//			return primaryTuple;
//		}).collect(Collectors.toList());

		return joinedTuples;
	}
}
