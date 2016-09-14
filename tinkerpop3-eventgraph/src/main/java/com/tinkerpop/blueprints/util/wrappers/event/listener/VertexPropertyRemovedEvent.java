package com.tinkerpop.blueprints.util.wrappers.event.listener;

import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * Event fired when a vertex property is removed.
 *
 * @author Stephen Mallette
 */
public class VertexPropertyRemovedEvent extends VertexPropertyEvent {

    public VertexPropertyRemovedEvent(final Vertex vertex, final String key, final Object removedValue) {
        super(vertex, key, removedValue, null);
    }

    @Override
    void fire(final GraphChangedListener listener, final Vertex vertex, final String key, final Object oldValue, final Object newValue) {
        listener.vertexPropertyRemoved(vertex, key, oldValue);
    }
}
