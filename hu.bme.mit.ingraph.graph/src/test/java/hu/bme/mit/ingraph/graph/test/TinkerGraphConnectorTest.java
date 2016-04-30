package hu.bme.mit.ingraph.graph.test;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Assert;
import org.junit.Test;

import hu.bme.mit.ingraph.graph.TinkerGraphConnector;

public class TinkerGraphConnectorTest {

	@Test
	public void test() {
		// Arrange
		final TinkerGraph graph = TinkerFactory.createModern();
		final TinkerGraphConnector connector = TinkerGraphConnector.on(graph);

		// Act
		final Collection<Vertex> persons = connector.collectVertices("person");
		final Collection<Vertex> softwares = connector.collectVertices("software");

		final Collection<Edge> createds = connector.collectEdges("created");
		final Collection<Edge> knowss = connector.collectEdges("knows");
		
		// Assert
		Assert.assertEquals(4, persons.size());
		Assert.assertEquals(2, softwares.size());

		Assert.assertEquals(4, createds.size());
		Assert.assertEquals(2, knowss.size());
	}

}
