package ingraph.neo4j.test;

import apoc.export.graphml.ExportGraphML;
import apoc.graph.Graphs;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.api.exceptions.KernelException;
import org.neo4j.test.TestGraphDatabaseFactory;

public class Neo4jTest {

	@Test
	public void test() throws KernelException {
		int queryNumber = 17;

		GraphDatabaseSettings.BoltConnector bolt = GraphDatabaseSettings.boltConnector( "0" );

		GraphDatabaseService gds = new TestGraphDatabaseFactory()
			.newImpermanentDatabaseBuilder()
			.setConfig("apoc.import.file.enabled", "true")
			.setConfig( bolt.type, "BOLT" )
			.setConfig( bolt.enabled, "true" )
			.setConfig( bolt.address, "localhost:7688" )
			.newGraphDatabase();

		ApocHelper.registerProcedure(gds, ExportGraphML.class, Graphs.class);

		final String graphFilename = String.format("bi-" + queryNumber + ".graphml");
		try (final Transaction t = gds.beginTx()) {
			gds.execute(String.format( //
				"CALL apoc.import.graphml('%s', {batchSize: 10000, readLabels: true})", //
				"src/test/resources/" + graphFilename //
			));
		}

		final String queryBase = "\n" +
			"MATCH (country:Country {name: $country})\n" +
			"MATCH (a:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)\n" +
			"MATCH (b:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)\n" +
			"MATCH (c:Person)-[:isLocatedIn]->(:City)-[:isPartOf]->(country)\n" +
			"MATCH (a)-[:knows]-(b), (b)-[:knows]-(c), (c)-[:knows]-(a)\n" +
			"WHERE a.id < b.id\n" +
			"  AND b.id < c.id\n" +
			"RETURN count(*)";
		final String query = queryBase.replace("$country", "'Austria'");
		final Result result = gds.execute(query);
		System.out.println(result.next());
	}

}
