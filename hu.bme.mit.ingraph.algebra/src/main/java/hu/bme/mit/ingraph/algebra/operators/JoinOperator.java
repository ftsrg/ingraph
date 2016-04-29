package hu.bme.mit.ingraph.algebra.operators;

import java.util.List;

import hu.bme.mit.ingraph.algebra.operators.visitors.TreeVisitor;
import lombok.Builder;

public class JoinOperator extends BetaOperator {

	@Builder
	public JoinOperator(AbstractOperator leftParent, AbstractOperator rightParent, List<Integer> lms, List<Integer> rms, double density) {
		super(leftParent, rightParent, lms, rms, density);
	}

	@Override
	public <R> R accept(TreeVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}

}
