package hu.bme.mit.ingraph.rete;

import java.util.List;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.rete.data.Tuple;
import lombok.Builder;

@Builder
public class InputNode extends AbstractNode<InputOperation> {

	public List<Tuple> getContents() {
		return null;
	}

}
