package hu.bme.mit.ingraph.algorithms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

import lombok.Getter;

public class TestGraph {

	private static final String EDGE = "edge";
	private static final String WEIGHT = "weight";

	protected TinkerGraph graph = TinkerGraph.open();

	@Getter protected MutableList<Vertex> vertices = Lists.mutable.of();
//	@Getter protected MutableList<Edge> edges = Lists.mutable.of();

	protected TestGraph() {
	}

	public static TestGraph open() {
		return new TestGraph();
	}

	public Vertex createVertex() {
		final Vertex v = graph.addVertex();
		vertices.add(v);
		return v;
	}

	public Edge createEdge(final Vertex source, final Vertex target, final int weight) {
		final Edge e = source.addEdge(EDGE, target, WEIGHT, weight);
//		edges.add(e);
		return e;
	}

	public Vertex v(final int id) {
		return vertices.get(id - 1);
	}
	
	public Iterable<Vertex> neighbors(final Vertex v) {
		final Iterable<Edge> outgoingEdges = () -> v.edges(Direction.OUT);
		final Stream<Edge> stream = StreamSupport.stream(outgoingEdges.spliterator(), false);
		final List<Vertex> neighbors = stream.map(it -> it.inVertex()).collect(Collectors.toList());
		return neighbors;
	}

	public Iterable<Edge> outgoingEdges(final Vertex v) {
		final Iterable<Edge> outgoingEdges = () -> v.edges(Direction.OUT);
		return outgoingEdges;
	}

	
	public int weight(final Edge edge) {
		return (int) edge.property(WEIGHT).value();
	}

}
