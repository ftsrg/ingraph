package hu.bme.mit.ingraph.algebra.operators;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class BetaOperator extends WorkerOperator {

	@Getter protected AbstractOperator leftParent;
	@Getter protected AbstractOperator rightParent;

	@Getter protected List<Integer> lms;
	@Getter protected List<Integer> rms;

	@Getter @Setter protected double density;
	
}
