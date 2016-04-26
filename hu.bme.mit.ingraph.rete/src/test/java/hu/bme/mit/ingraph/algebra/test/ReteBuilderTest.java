package hu.bme.mit.ingraph.algebra.test;

import org.junit.Test;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;
import hu.bme.mit.ingraph.rete.builder.ReteBuilder;

public class ReteBuilderTest {

	@Test
	public void test() {
		final InputOperation sw = InputOperation.builder().name("switch").build();
		final InputOperation follows = InputOperation.builder().name("follows").build();
		final InputOperation sensor = InputOperation.builder().name("sensor").build();

		final JoinOperation join1 = JoinOperation.builder().leftParent(sw).rightParent(follows).lm(0).rm(1).build();
		final JoinOperation join2 = JoinOperation.builder().leftParent(join1).rightParent(sensor).lm(1).rm(0).build();
		final ProductionOperation production = ProductionOperation.builder().parent(join2).build();

		sw.setTuples(100);
		follows.setTuples(200);
		sensor.setTuples(300);
		join1.setDensity(0.1);
		join2.setDensity(0.2);

		final ReteBuilder reteBuilder = new ReteBuilder();
		production.accept(reteBuilder);
	}

}
