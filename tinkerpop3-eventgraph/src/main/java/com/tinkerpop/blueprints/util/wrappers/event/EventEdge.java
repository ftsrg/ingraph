package com.tinkerpop.blueprints.util.wrappers.event;

import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * An edge with a GraphChangedListener attached.  Those listeners are notified when changes occur to
 * the properties of the edge.
 *
 * @author Stephen Mallette
 */
public class EventEdge extends EventElement implements Edge {

    public EventEdge(final Edge rawEdge, final EventGraph eventGraph) {
        super(rawEdge, eventGraph);
    }

    public Iterator<Vertex> vertices(final Direction direction) throws IllegalArgumentException {
    	return this.getBaseEdge().vertices(direction);
    }

    public String label() {
        return ((Edge) this.baseElement).label();
    }

    public Edge getBaseEdge() {
        return (Edge) this.baseElement;
    }

	@Override
	public Object id() {
		return this.baseElement.id();
	}

	@Override
	public Graph graph() {
		return this.baseElement.graph();
	}

	@Override
	public <V> Property<V> property(String key, V value) {
		return this.property(key, value);
	}

	@Override
	public <V> Iterator<Property<V>> properties(String... propertyKeys) {
		return this.properties(propertyKeys);
	}
}
