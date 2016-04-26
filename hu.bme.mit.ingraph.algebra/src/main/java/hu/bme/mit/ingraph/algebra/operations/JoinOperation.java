package hu.bme.mit.ingraph.algebra.operations;

import java.util.List;

import hu.bme.mit.ingraph.algebra.operations.visitors.AlgebraTreeVisitor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Builder
public class JoinOperation extends WorkerOperation {

	@Getter protected AbstractOperation leftParent;
	@Getter protected AbstractOperation rightParent;

	@Getter @Singular protected List<Integer> lms;
	@Getter @Singular protected List<Integer> rms;

	@Getter @Setter protected double density;

	public long accept(AlgebraTreeVisitor visitor) {
		return visitor.visit(this);
	}

}
