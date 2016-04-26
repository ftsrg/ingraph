package hu.bme.mit.ingraph.rete;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;

public class Tuple {

	protected final ImmutableList<Long> list;

	public Tuple() {
		this.list = null;
	}

	public Tuple(final Long... elements) {
		this.list = Lists.immutable.of(elements);
	}

	Tuple(final ImmutableList<Long> list) {
		this.list = list;
	}

	Tuple(final List<Long> list) {
		this.list = Lists.immutable.ofAll(list);
	}

	public static Tuple createTuple(final Long... elements) {
		return new Tuple(elements);
	}

	public static Tuple createTuple(final List<Long> tuple) {
		return new Tuple(tuple);
	}

	public Long get(final int i) {
		return list.get(i);
	}

	public int size() {
		return list.size();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder("⟨");
		if (list.size() > 0) {
			builder.append(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				builder.append(", ");
				builder.append(list.get(i));
			}
		}
		builder.append("⟩");
		return builder.toString();
	}

	public Tuple extract(final List<Integer> mask) {
		final MutableList<Long> extractedList = Lists.mutable.of();
		for (final Integer m : mask) {
			extractedList.add(list.get(m));
		}
		return new Tuple(extractedList);
	}

	public static Tuple join(final Tuple primaryTuple, final Tuple secondaryTuple, final List<Integer> primaryMask,
			final List<Integer> secondaryMask) {
		final int size = primaryTuple.size() + secondaryTuple.size() - primaryMask.size();
		final List<Long> joinedList = FastList.newList(size);
		for (int i = 0; i < secondaryTuple.list.size(); i++) {
			if (!secondaryMask.contains(i)) {
				joinedList.add(secondaryTuple.list.get(i));
			}
		}
		return new Tuple(joinedList);
	}
}
