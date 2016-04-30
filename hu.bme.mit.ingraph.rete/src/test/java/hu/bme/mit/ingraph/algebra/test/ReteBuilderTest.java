package hu.bme.mit.ingraph.algebra.test;

import static hu.bme.mit.ingraph.algebra.operators.util.AlgebraUtil.L;

import org.junit.Test;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;
import hu.bme.mit.ingraph.rete.builder.ReteBuilderVisitor;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;

public class ReteBuilderTest {

	@Test
	public void test() {
		final InputOperator sw = InputOperator.builder().name("switch").build();
		final InputOperator follows = InputOperator.builder().name("follows").build();
		final InputOperator sensor = InputOperator.builder().name("sensor").build();

		final JoinOperator join1 = JoinOperator.builder().leftParent(sw).rightParent(follows).lms(L(0)).rms(L(1)).build();
		final JoinOperator join2 = JoinOperator.builder().leftParent(join1).rightParent(sensor).lms(L(1)).rms(L(0)).build();
		final ProductionOperator production = ProductionOperator.builder().parent(join2).build();

		sw.setTuples(100);
		follows.setTuples(200);
		sensor.setTuples(300);
		join1.setDensity(0.1);
		join2.setDensity(0.2);

		final ReteBuilderVisitor reteBuilderVisitor = ReteBuilderVisitor.create();
		final ProductionNode productionNode = (ProductionNode) production.accept(reteBuilderVisitor);

		System.out.println(productionNode.prettyPrint(2));
	}

}
