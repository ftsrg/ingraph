package hu.bme.mit.ingraph.rete.data;

import java.util.Collection;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ChangeSet {

	@Getter protected Collection<Tuple> positive;
	@Getter protected Collection<Tuple> negative;
	
}
