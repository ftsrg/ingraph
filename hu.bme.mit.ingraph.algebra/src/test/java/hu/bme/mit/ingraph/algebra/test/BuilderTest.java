package hu.bme.mit.ingraph.algebra.test;

import org.junit.Test;

import hu.bme.mit.ingraph.algebra.InputNode;
import hu.bme.mit.ingraph.algebra.JoinNode;
import hu.bme.mit.ingraph.algebra.ProductionNode;
import hu.bme.mit.ingraph.algebra.visitor.PrinterVisitor;
import hu.bme.mit.ingraph.algebra.visitor.StatisticsVisitor;

public class BuilderTest {

	@Test
	public void test() {
		final InputNode sw = InputNode.builder().name("switch").build();
		final InputNode follows = InputNode.builder().name("follows").build();
		final InputNode sensor = InputNode.builder().name("sensor").build();
		
		final JoinNode join1 = JoinNode.builder().leftParent(sw).rightParent(follows).lm(0).rm(1).build();
		final JoinNode join2 = JoinNode.builder().leftParent(join1).rightParent(sensor).lm(1).rm(0).build();
		final ProductionNode production = ProductionNode.builder().parent(join2).build();
		
		sw.setTuples(100);
		follows.setTuples(200);
		sensor.setTuples(300);
		join1.setDensity(0.1);
		join2.setDensity(0.2);
		
		final StatisticsVisitor statisticsVisitor = new StatisticsVisitor();
		final long tuples = statisticsVisitor.visit(production);
		System.out.println(tuples);
		
		final PrinterVisitor printerVisitor = new PrinterVisitor();
		printerVisitor.visit(production);
	}
	
}
