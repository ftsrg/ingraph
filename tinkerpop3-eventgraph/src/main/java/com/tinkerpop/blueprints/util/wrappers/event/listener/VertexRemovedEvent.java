package com.tinkerpop.blueprints.util.wrappers.event.listener;

import java.util.Iterator;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * Event fired when a vertex is removed.
 *
 * @author Stephen Mallette
 */
public class VertexRemovedEvent implements Event {

    private final Vertex vertex;
    private final Map<String, Object> props;

    public VertexRemovedEvent(final Vertex vertex, final Map<String, Object> props) {
        this.vertex = vertex;
        this.props = props;
    }

    @Override
    public void fireEvent(final Iterator<GraphChangedListener> eventListeners) {
        while (eventListeners.hasNext()) {
            eventListeners.next().vertexRemoved(vertex, props);
        }
    }
}
