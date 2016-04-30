package hu.bme.mit.ingraph.rete.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ChangeSet<TElement> {

	@Getter protected Iterable<Tuple<TElement>> positive;
	@Getter protected Iterable<Tuple<TElement>> negative;
	
	public static <TElement> ChangeSet<TElement> of(Iterable<Tuple<TElement>> positive, Iterable<Tuple<TElement>> negative) {
		return new ChangeSet<>(positive, negative);
	}
	
}
