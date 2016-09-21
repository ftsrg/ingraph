package com.tinkerpop.blueprints.util.wrappers.event;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.VertexProperty.Cardinality;

/**
 * An vertex with a GraphChangedListener attached. Those listeners are notified when changes occur to the properties of the vertex.
 *
 * @author Stephen Mallette
 */
public class EventVertex extends EventElement implements Vertex {
	
	public EventVertex(final Vertex rawVertex, final EventGraph eventGraph) {
		super(rawVertex, eventGraph);
	}

	// TODO use keyValues
	@Override
	public Edge addEdge(final String label, final Vertex vertex, Object... keyValues) {
		return this.eventGraph.addEdge(null, this, vertex, label);
	}

	public Vertex getBaseVertex() {
		return (Vertex) this.baseElement;
	}

	@Override
	public Object id() {
		return baseElement.id();
	}

	@Override
	public String label() {
		return baseElement.label();
	}

	@Override
	public Graph graph() {
		return baseElement.graph();
	}

	@Override
	public <V> VertexProperty<V> property(String key) {
		return ((Vertex) baseElement).property(key);
	}
	
	@Override
	public <V> VertexProperty<V> property(String key, V value) {
		return ((Vertex) baseElement).property(key, value);
	}

	@Override
	public Iterator<Edge> edges(Direction direction, String... edgeLabels) {
		return ((Vertex) baseElement).edges(direction, edgeLabels);
	}

	@Override
	public Iterator<Vertex> vertices(Direction direction, String... edgeLabels) {
		return ((Vertex) baseElement).vertices(direction, edgeLabels);
	}

	@Override
	public <V> Iterator<VertexProperty<V>> properties(String... propertyKeys) {
		return ((Vertex) baseElement).properties(propertyKeys);
	}

	@Override
	public <V> VertexProperty<V> property(Cardinality cardinality, String key, V value, Object... keyValues) {
		return ((Vertex) baseElement).property(cardinality, key, value, keyValues);
	}
}
