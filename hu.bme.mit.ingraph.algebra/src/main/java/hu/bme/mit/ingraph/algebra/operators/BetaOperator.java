package hu.bme.mit.ingraph.algebra.operators;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@AllArgsConstructor
public abstract class BetaOperator extends WorkerOperator {

	@Getter protected AbstractOperator leftParent;
	@Getter protected AbstractOperator rightParent;

	@Getter @Singular protected List<Integer> lms;
	@Getter @Singular protected List<Integer> rms;

	@Getter @Setter protected double density;
	
}
