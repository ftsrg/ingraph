package hu.bme.mit.ingraph.graph.test;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;

import hu.bme.mit.ingraph.graph.TinkerGraphConnector;
import hu.bme.mit.ingraph.rete.data.ChangeSet;
import hu.bme.mit.ingraph.rete.data.Tuple;

public class TinkerReteTest {

	@Test
	public void test() {
		final TinkerGraph graph = TinkerFactory.createModern();
		final TinkerGraphConnector connector = TinkerGraphConnector.on(graph);
		final ImmutableList<Vertex> softwares = Lists.immutable.ofAll(connector.collectVertices("software"));
		final ImmutableList<Vertex> persons = Lists.immutable.ofAll(connector.collectVertices("person"));
		
		final Iterable<Tuple<Vertex>> softwarePositive = Lists.immutable.of(Tuple.of(softwares.get(0)), Tuple.of(softwares.get(1)));
		final Iterable<Tuple<Vertex>> softwareNegative = Lists.immutable.of();

		final ChangeSet<Vertex> softwareChangeSet = ChangeSet.of(softwarePositive, softwareNegative);
		System.out.println(softwareChangeSet);
		
		
		final Iterable<Tuple<Vertex>> personsPositive = Lists.immutable.of(Tuple.of(persons.get(0)), Tuple.of(persons.get(1)));

	}
	
}
