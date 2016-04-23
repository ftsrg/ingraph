package hu.bme.mit.ingraph.tinkerpop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class TinkerPopSandboxMain {

	public static final String TYPE = "type";
//	public static final String graphFile = "../graphs/railway-minimal-routesensor-tinkerpop.graphson";
	public static final String graphFile = "../graphs/railway-repair-1-tinkerpop.graphson";
	
	public static void main(final String[] args) throws IOException {
		final TinkerGraph graph = TinkerGraph.open();
		graph.createIndex(TYPE, Vertex.class);
		graph.io(IoCore.graphson()).readGraph(graphFile);
		
		final List<Edge> followss = getEdges(graph, "follows");
		final List<Edge> targets = getEdges(graph, "target");
		final List<Edge> monitoredBys = getEdges(graph, "monitoredBy");

		System.out.println(followss.size());
		System.out.println(targets.size());
		System.out.println(monitoredBys.size());
		
		graph.close();
	}

	private static List<Edge> getEdges(final TinkerGraph graph, final String label) {
		final List<Edge> filteredEdges = new ArrayList<>();
		
		final Iterator<Edge> allEdges = graph.edges();
		while (allEdges.hasNext()) {
			final Edge edge = allEdges.next();
			if (label.equals(edge.label())) {
				filteredEdges.add(edge);
			}
		}
		
		return filteredEdges;
	}

}
