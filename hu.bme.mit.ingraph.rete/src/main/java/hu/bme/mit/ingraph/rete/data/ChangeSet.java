package hu.bme.mit.ingraph.rete.data;

import java.util.Collection;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ChangeSet<TElement> {

	@Getter protected Collection<Tuple<TElement>> positive;
	@Getter protected Collection<Tuple<TElement>> negative;
	
}
