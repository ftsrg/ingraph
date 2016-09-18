package com.tinkerpop.blueprints.tests;

import java.io.IOException;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLIo;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.junit.Test;

import com.tinkerpop.blueprints.util.wrappers.event.EventGraph;
import com.tinkerpop.blueprints.util.wrappers.event.listener.ConsoleGraphChangedListener;

public class LoadEventGraphTest {

	@Test
	public void test() throws IOException {
		final String modelPath = "/home/szarnyasg/git/trainbenchmark/models/railway-repair-1.graphml";
		
		try (final EventGraph<Graph> graph = new EventGraph<>(TinkerGraph.open())) {
			graph.addListener(new ConsoleGraphChangedListener(graph));
			GraphMLIo io = graph.io(IoCore.graphml());
			io.readGraph(modelPath);
		}
	}

}
