package hu.bme.mit.ingraph.tinkerpop;

import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Multimaps;

public class TinkerGraphProcessor {

	protected final TinkerGraph graph;

	public TinkerGraphProcessor(final TinkerGraph graph) {
		this.graph = graph;
	}

	public List<Edge> getEdges(final String label) {
		final List<Edge> filteredEdges = Lists.mutable.empty();
		graph.edges().forEachRemaining(edge -> {
			if (label.equals(edge.label())) filteredEdges.add(edge);
		});
		return filteredEdges;
	}

	public MutableMultimap<Vertex, Vertex> edgesToMultimap(final List<Edge> edges) {
		final MutableMultimap<Vertex, Vertex> map = Multimaps.mutable.bag.empty();
		edges.forEach(edge -> map.put(edge.outVertex(), edge.inVertex()));
		return map;
	}

}
