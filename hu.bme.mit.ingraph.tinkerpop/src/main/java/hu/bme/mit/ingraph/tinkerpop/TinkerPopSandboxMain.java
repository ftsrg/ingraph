package hu.bme.mit.ingraph.tinkerpop;

import java.io.IOException;
import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.eclipse.collections.api.multimap.MutableMultimap;

public class TinkerPopSandboxMain {

	public static final String TYPE = "type";
	public static final String graphFile = "../graphs/railway-repair-1-tinkerpop.graphson";
	
	public static void main(final String[] args) throws IOException {
		final TinkerGraph graph = TinkerGraph.open();
		graph.createIndex(TYPE, Vertex.class);
		graph.io(IoCore.graphson()).readGraph(graphFile);
	
		final TinkerGraphProcessor processor = new TinkerGraphProcessor(graph);
		
		final List<Edge> followss = processor.getEdges("follows");
		final List<Edge> targets = processor.getEdges("target");
		final List<Edge> monitoredBys = processor.getEdges("monitoredBy");

		final MutableMultimap<Vertex, Vertex> followsMM = processor.edgesToMultimap(followss);

		graph.close();
	}
	
}
