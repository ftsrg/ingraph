package com.tinkerpop.blueprints.tests;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Test;

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph;
import com.tinkerpop.blueprints.util.wrappers.event.listener.ConsoleGraphChangedListener;

public class MyTest {

	@Test
	public void test() {
		final EventGraph<Graph> graph = new EventGraph<>(TinkerGraph.open());
		graph.addListener(new ConsoleGraphChangedListener(graph));
		
		final Vertex ms1 = graph.addVertex("microservice");
		final Vertex ms2 = graph.addVertex("microservice");
		ms1.addEdge("linked", ms2);
	}
	
}
