package ingraph.driver.ingraph;

import ingraph.bulkloader.csv.loader.MassCsvLoader;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.ire.Indexer;
import ingraph.ire.IngraphIncrementalAdapter;
import neo4j.driver.reactive.data.RecordChangeSet;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.neo4j.driver.v1.types.TypeSystem;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class IngraphSession implements Session {

	Indexer indexer = new Indexer();

	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public StatementResult run(String statementTemplate, Value parameters) {
		return run(statementTemplate, parameters.asMap());
	}

	@Override
	public StatementResult run(String statementTemplate, Map<String, Object> statementParameters) {
		throw new UnsupportedOperationException("Vanilla queries are not yet supported");
	}

	@Override
	public StatementResult run(String statementTemplate, Record statementParameters) {
		return run(statementTemplate, statementParameters.asMap());
	}

	@Override
	public StatementResult run(String statementTemplate) {
		return run(statementTemplate, Collections.emptyMap());
	}

	@Override
	public StatementResult run(Statement statement) {
		return run(statement.text(), statement.parameters());
	}

	@Override
	public TypeSystem typeSystem() {
		return null;
	}

	@Override
	public Transaction beginTransaction() {
		return new IngraphTransaction(this);
	}

	@Override
	public Transaction beginTransaction(String bookmark) {
		throw new UnsupportedOperationException("Bookmarks are not supported.");
	}

	@Override
	public <T> T readTransaction(TransactionWork<T> work) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public <T> T writeTransaction(TransactionWork<T> work) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public String lastBookmark() {
		throw new UnsupportedOperationException("Bookmarks are not supported.");
	}

	@Override
	public void reset() {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public void close() {
	}

	public IngraphQueryHandler registerQuery(String queryName, String querySpecification, Map<String, Object> statementParameters) {
		final IngraphIncrementalAdapter adapter = new IngraphIncrementalAdapter(querySpecification, queryName, indexer);
		return new IngraphQueryHandler(adapter);
	}

	public IngraphQueryHandler registerQuery(String queryName, String querySpecification) {
		return registerQuery(queryName, querySpecification, Collections.emptyMap());
	}

	public void readCsv(Map<String, Collection<String>> nodeFilenames, Map<String, String> relationshipFilenames, CsvPreference csvPreference) throws IOException {
		MassCsvLoader loader = new MassCsvLoader(nodeFilenames, relationshipFilenames, csvPreference);
		for (Node n : loader.getNodes()) indexer.addVertex(n);
		for (Relationship r : loader.getRelationships()) indexer.addEdge(r);
	}

	public RecordChangeSet getDeltas(String queryName) {
		return null;
	}

}
