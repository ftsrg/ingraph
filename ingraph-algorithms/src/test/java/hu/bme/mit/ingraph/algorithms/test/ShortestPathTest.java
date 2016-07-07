package hu.bme.mit.ingraph.algorithms.test;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.ingraph.algorithms.TestGraph;

public class ShortestPathTest {

	protected final TestGraph g = TestGraph.open();

	@Before
	public void createTestGraph() {
		for (int i = 1; i <= 7; i++) {
			g.createVertex();
		}

		// (1)-->
		g.createEdge(g.v(1), g.v(2), 5);
		g.createEdge(g.v(1), g.v(3), 8);
		g.createEdge(g.v(1), g.v(5), 7);
		g.createEdge(g.v(1), g.v(6), 10);

		// (2)-->
		g.createEdge(g.v(2), g.v(3), 1);
		g.createEdge(g.v(2), g.v(5), 3);

		// (3)-->
		g.createEdge(g.v(3), g.v(4), 6);

		// (5)-->
		g.createEdge(g.v(5), g.v(4), 4);
		g.createEdge(g.v(5), g.v(6), 2);
		g.createEdge(g.v(5), g.v(7), 7);

		// (6)-->
		g.createEdge(g.v(6), g.v(7), 3);

		// (7)-->
		g.createEdge(g.v(7), g.v(3), 4);
		g.createEdge(g.v(7), g.v(4), 5);
	}

	@Test
	public void dijsktraTest() {
		System.out.println(g);

		final MutableSet<Vertex> Q = Sets.mutable.of();
		final MutableMap<Vertex, Integer> dist = Maps.mutable.with();
		final MutableMap<Vertex, Vertex> prev = Maps.mutable.with();

		for (final Vertex v : g.getVertices()) {
			dist.put(v, Integer.MAX_VALUE);
			// prev returns null for all vertices
			Q.add(v);
		}

		dist.put(g.v(1), 0);
		while (Q.notEmpty()) {
			// get vertex from Q with the smallest distance
			int minDistance = Integer.MAX_VALUE;
			Vertex u = null;
			for (Vertex q : Q) {
				int qDistance = dist.get(q);
				if (qDistance < minDistance) {
					minDistance = qDistance; 
					u = q;
				}
			}
			
			// remove u from Q
			Q.remove(u);
			
			final Iterable<Edge> outgoingEdges = g.outgoingEdges(u);
			for (Edge e : outgoingEdges) {
				Vertex v = e.inVertex();
				if (!Q.contains(v)) continue;
				
				final int alt = dist.get(u) + g.weight(e);
				if (alt < dist.get(v)) { // a shorter path to v has been found
					dist.put(v, alt);
					prev.put(v, u);
				}
			}
		}

		System.out.println(dist);
		System.out.println(prev);
	}

}
