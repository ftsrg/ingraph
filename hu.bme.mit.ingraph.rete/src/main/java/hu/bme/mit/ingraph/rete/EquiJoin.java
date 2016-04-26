package hu.bme.mit.ingraph.rete;

import java.util.Collection;
import java.util.List;

public class EquiJoin extends AbstractJoin {

	public EquiJoin(final List<Integer> primaryMask, final List<Integer> secondaryMask) {
		super(primaryMask, secondaryMask);
	}

	@Override
	public Collection<Tuple> join(final Collection<Tuple> primaryInput, final Collection<Tuple> secondaryInput) {
		index(primaryIndexer, primaryMask, primaryInput);
		index(secondaryIndexer, secondaryMask, secondaryInput);

		final Collection<Tuple> joinedTuples = null;
//				primaryIndexer
//				.keyMultiValuePairsView()
//				
//				.parallelStream()
//				.flatMap(
//						entry -> {
//							final Tuple primaryTuple = entry.getValue();
//							final Tuple primaryTupleJoinAttributes = entry.getKey();
//							final Collection<Tuple> secondaryTuples = secondaryIndexer.get(primaryTupleJoinAttributes);
//
//							final Stream<Tuple> stream = secondaryTuples.parallelStream().map(
//									secondaryTuple -> Tuple.join(primaryTuple, secondaryTuple, primaryMask, secondaryMask));
//							return stream;
//						}).collect(Collectors.toList());

		return joinedTuples;
	}
}
