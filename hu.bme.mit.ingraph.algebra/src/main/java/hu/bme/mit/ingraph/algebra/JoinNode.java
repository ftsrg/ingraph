package hu.bme.mit.ingraph.algebra;

import java.util.List;

import hu.bme.mit.ingraph.algebra.visitor.Visitor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Builder
public class JoinNode extends WorkerNode {

	@Getter protected AbstractNode leftParent;
	@Getter protected AbstractNode rightParent;

	@Getter @Singular protected List<Integer> lms;
	@Getter @Singular protected List<Integer> rms;

	@Getter @Setter protected double density;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
