package hu.bme.mit.ingraph.rete.data;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastList;

public class Tuple<TElement> {

	protected final ImmutableList<TElement> list;

	protected Tuple(final TElement[] elements) {
		this.list = Lists.immutable.of(elements);
	}

	protected Tuple(final ImmutableList<TElement> list) {
		this.list = list;
	}

	protected Tuple(final List<TElement> list) {
		this.list = Lists.immutable.ofAll(list);
	}

	@SafeVarargs
	public static <TElement> Tuple<TElement> of(final TElement... elements) {
		return new Tuple<>(elements);
	}

	public static <TElement> Tuple<TElement> of(final List<TElement> elements) {
		return new Tuple<>(elements);
	}

	public TElement get(final int i) {
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

	public Tuple<TElement> extract(final List<Integer> mask) {
		final MutableList<TElement> extractedList = Lists.mutable.of();
		for (final Integer m : mask) {
			extractedList.add(list.get(m));
		}
		return new Tuple<>(extractedList);
	}

	public static <TElement> Tuple<TElement> join(final Tuple<TElement> leftTuple, final Tuple<TElement> rightTuple,
			final List<Integer> leftMask, final List<Integer> rightMask) {
		final int size = leftTuple.size() + rightTuple.size() - leftMask.size();
		final List<TElement> joinedList = FastList.newList(size);

		for (int i = 0; i < rightTuple.list.size(); i++) {
			if (!rightMask.contains(i)) {
				joinedList.add(rightTuple.list.get(i));
			}
		}
		return new Tuple<>(joinedList);
	}
	
}
