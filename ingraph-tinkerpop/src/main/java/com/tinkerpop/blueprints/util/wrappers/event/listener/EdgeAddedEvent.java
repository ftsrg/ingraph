package com.tinkerpop.blueprints.util.wrappers.event.listener;


import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Edge;

public class EdgeAddedEvent implements Event {

    private final Edge edge;

    public EdgeAddedEvent(final Edge edge) {
        this.edge = edge;
    }

    @Override
    public void fireEvent(final Iterator<GraphChangedListener> eventListeners) {
        while (eventListeners.hasNext()) {
            eventListeners.next().edgeAdded(edge);
        }
    }
}
