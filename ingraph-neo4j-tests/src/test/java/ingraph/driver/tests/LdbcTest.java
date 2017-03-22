package ingraph.driver.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import neo4j.driver.testkit.EmbeddedTestkitDriver;
import neo4j.driver.testkit.EmbeddedTestkitSession;
import neo4j.driver.util.GraphPrettyPrinter;

@RunWith(Parameterized.class)
public class LdbcTest {

	@Parameters(name = "query={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(3, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15, 16, 20, 23, 24);
	}

	@Parameter
	public int queryNumber;

	final String graphMlPath = "../graphs/snb_50.graphml";
	final EmbeddedTestkitDriver driver = new EmbeddedTestkitDriver();

	@Before
	public void load() throws FileNotFoundException, XMLStreamException {
		final GraphDatabaseService graphDb = driver.getUnderlyingDatabaseService();

		try (final org.neo4j.graphdb.Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(graphMlPath)), MapNodeCache.usingHashMap());
			tx.success();
		}
	}

	@Test
	public void test() throws IOException {
		final EmbeddedTestkitSession session = driver.session();
		try (org.neo4j.driver.v1.Transaction tx = session.beginTransaction()) {
			final String querySpecification = Files
					.toString(new File("../queries/ldbc-snb-bi/query-" + queryNumber + ".cypher"), Charsets.UTF_8);

			final StatementResult statementResult = session.run(querySpecification);
			final List<Record> results = Lists.newArrayList(statementResult);

			System.out.println("Query " + queryNumber + ": " + results.size());
			for (Record record : results) {
				System.out.println(GraphPrettyPrinter.toString(record));
			}
			System.out.println();
		}
	}

}
