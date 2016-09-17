package com.tinkerpop.blueprints.util.wrappers.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper;
import org.apache.tinkerpop.gremlin.structure.util.StringFactory;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraphVariables;

import com.tinkerpop.blueprints.util.wrappers.WrapperGraph;
import com.tinkerpop.blueprints.util.wrappers.event.listener.EdgeAddedEvent;
import com.tinkerpop.blueprints.util.wrappers.event.listener.EdgeRemovedEvent;
import com.tinkerpop.blueprints.util.wrappers.event.listener.GraphChangedListener;
import com.tinkerpop.blueprints.util.wrappers.event.listener.VertexAddedEvent;
import com.tinkerpop.blueprints.util.wrappers.event.listener.VertexRemovedEvent;

/**
 * An EventGraph is a wrapper to existing Graph implementations and provides for
 * graph events to be raised to one or more listeners on changes to the Graph.
 * Notifications to the listeners occur for the following events: new
 * vertex/edge, vertex/edge property changed, vertex/edge property removed,
 * vertex/edge removed.
 *
 * The limiting factor to events being raised is related to out-of-process
 * functions changing graph elements.
 *
 * To gather events from EventGraph, simply provide an implementation of the
 * {@link GraphChangedListener} to the EventGraph by utilizing the addListener
 * method. EventGraph allows the addition of multiple GraphChangedListener
 * implementations. Each listener will be notified in the order that it was
 * added.
 *
 * @author Stephen Mallette
 */
public class EventGraph<T extends Graph> implements Graph, WrapperGraph<T> {

	protected EventTrigger trigger;

	protected final T baseGraph;

	protected final List<GraphChangedListener> graphChangedListeners = new ArrayList<GraphChangedListener>();

	private final Features features;

	public EventGraph(final T baseGraph) {
		this.baseGraph = baseGraph;
		this.features = this.baseGraph.features(); // TODO: previously this way
													// a deep copy
		this.trigger = new EventTrigger(this, false);
	}

	public void removeAllListeners() {
		this.graphChangedListeners.clear();
	}

	public void addListener(final GraphChangedListener listener) {
		this.graphChangedListeners.add(listener);
	}

	public Iterator<GraphChangedListener> getListenerIterator() {
		return this.graphChangedListeners.iterator();
	}

	public EventTrigger getTrigger() {
		return this.trigger;
	}

	public void removeListener(final GraphChangedListener listener) {
		this.graphChangedListeners.remove(listener);
	}

	protected void onVertexAdded(Vertex vertex) {
		this.trigger.addEvent(new VertexAddedEvent(vertex));
	}

	protected void onVertexRemoved(final Vertex vertex, Map<String, Object> props) {
		this.trigger.addEvent(new VertexRemovedEvent(vertex, props));
	}

	protected void onEdgeAdded(Edge edge) {
		this.trigger.addEvent(new EdgeAddedEvent(edge));
	}

	protected void onEdgeRemoved(final Edge edge, Map<String, Object> props) {
		this.trigger.addEvent(new EdgeRemovedEvent(edge, props));
	}

	/**
	 * Raises a vertexAdded event.
	 */
	public Vertex addVertex(final Object id) {
		final Vertex vertex = this.baseGraph.addVertex(id);
		if (vertex == null) {
			return null;
		} else {
			this.onVertexAdded(vertex);
			return new EventVertex(vertex, this);
		}
	}

	public Vertex getVertex(final Object id) {
		final Vertex vertex = this.baseGraph.vertices(id).next();
		if (vertex == null) {
			return null;
		} else {
			return new EventVertex(vertex, this);
		}
	}

	/**
	 * Raises a vertexRemoved event.
	 */
	public void removeVertex(final Vertex vertex) {
		Map<String, Object> props = ElementHelper.propertyValueMap(vertex);
		vertex.remove();
		this.onVertexRemoved(vertex, props);
	}

	/**
	 * Raises an edgeAdded event.
	 */
	public Edge addEdge(final Object id, final Vertex outVertex, final Vertex inVertex, final String label) {
		Vertex outVertexToSet = outVertex;
		if (outVertex instanceof EventVertex) {
			outVertexToSet = ((EventVertex) outVertex).getBaseVertex();
		}

		Vertex inVertexToSet = inVertex;
		if (inVertex instanceof EventVertex) {
			inVertexToSet = ((EventVertex) inVertex).getBaseVertex();
		}

		final Edge edge = outVertexToSet.addEdge(label, inVertexToSet);
		if (edge == null) {
			return null;
		} else {
			this.onEdgeAdded(edge);
			return new EventEdge(edge, this);
		}
	}

	public Edge getEdge(final Object id) {
		final Edge edge = this.baseGraph.edges(id).next();
		if (edge == null) {
			return null;
		} else {
			return new EventEdge(edge, this);
		}
	}

	/**
	 * Raises an edgeRemoved event.
	 */
	public void removeEdge(final Edge edge) {
		Map<String, Object> props = ElementHelper.propertyValueMap(edge);
		edge.remove();
		this.onEdgeRemoved(edge, props);
	}

	public Iterable<Edge> getEdges() {
		return new EventEdgeIterable(() -> this.baseGraph.edges(), this);
	}

	public Iterable<Edge> getEdges(final String key, final Object value) {
		return new EventEdgeIterable(() -> this.baseGraph.edges(key, value), this);
	}

	@Override
	public void close() {
		try {
			this.baseGraph.close();

			// TODO: hmmmmmm??
			this.trigger.fireEventQueue();
			this.trigger.resetEventQueue();
		} catch (Exception re) {

		}
	}

	@Override
	public String toString() {
		return StringFactory.graphString(this, this.baseGraph.toString());
	}

	@Override
	public T getBaseGraph() {
		return this.baseGraph;
	}

	@Override
	public Features features() {
		return features;
	}

	@Override
	public Vertex addVertex(final String label) {
		Vertex vertex = baseGraph.addVertex(label);
		this.onVertexAdded(vertex);
		return new EventVertex(vertex, this);
	}

	@Override
	public Vertex addVertex(Object... keyValues) {
		Vertex vertex = baseGraph.addVertex(keyValues);
		this.onVertexAdded(vertex);
		return new EventVertex(vertex, this);
	}

	@Override
	public <C extends GraphComputer> C compute(Class<C> graphComputerClass) throws IllegalArgumentException {
		return baseGraph.compute(graphComputerClass);
	}

	@Override
	public GraphComputer compute() throws IllegalArgumentException {
		return baseGraph.compute();
	}

	@Override
	public Iterator<Vertex> vertices(Object... vertexIds) {
		return baseGraph.vertices(vertexIds);
	}

	@Override
	public Iterator<Edge> edges(Object... edgeIds) {
		return baseGraph.edges(edgeIds);
	}

	@Override
	public Transaction tx() {
		throw new UnsupportedOperationException("Transactions not supported");
	}

	@Override
	public Variables variables() {
		return new TinkerGraphVariables();
	}

	@Override
	public Configuration configuration() {
		return new BaseConfiguration();
	}

}
