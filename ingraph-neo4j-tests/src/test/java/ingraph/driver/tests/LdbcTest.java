package ingraph.driver.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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

	@Parameters(name = "workload={0}, query={1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				// BI
				{ "bi", 3 }, { "bi", 4 }, { "bi", 5 }, { "bi", 6 }, { "bi", 7 }, { "bi", 8 }, { "bi", 9 }, { "bi", 12 },
				{ "bi", 13 }, { "bi", 14 }, { "bi", 15 }, { "bi", 16 }, { "bi", 20 }, { "bi", 23 }, { "bi", 24 },
				// interactive
				{ "interactive", 1 }, { "interactive", 2 }, { "interactive", 3 }, { "interactive", 4 }, { "interactive", 5 },
				{ "interactive", 6 }, { "interactive", 7 }, { "interactive", 8 }, { "interactive", 9 }, { "interactive", 10 },
				{ "interactive", 11 }, { "interactive", 12 }, { "interactive", 13 } });
	}

	@Parameter(0)
	public String workload;

	@Parameter(1)
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
			final String queryPathname = String.format("../queries/ldbc-snb-%s/query-%d.cypher", workload, queryNumber);
			final String querySpecification = Files.toString(new File(queryPathname), Charsets.UTF_8);

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
