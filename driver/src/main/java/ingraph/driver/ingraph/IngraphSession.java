package ingraph.driver.ingraph;

import ingraph.driver.data.IngraphQueryHandler;
import ingraph.ire.Indexer;
import ingraph.ire.IncrementalQueryAdapter;
import ingraph.ire.OneTimeQueryAdapter;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Statement;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.StatementResultCursor;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.TypeSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class IngraphSession implements Session {

	final Indexer indexer = new Indexer();
	int oneTimeQueryIndex = 0;

	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public StatementResult run(String statementTemplate, Value parameters) {
		return run(statementTemplate, parameters.asMap());
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Value parameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate, Map<String, Object> statementParameters) {
		final OneTimeQueryAdapter adapter = new OneTimeQueryAdapter(statementTemplate, "onetime" + oneTimeQueryIndex, indexer);
		oneTimeQueryIndex++;
		adapter.results();
		return null;
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Map<String, Object> statementParameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate, Record statementParameters) {
		return run(statementTemplate, statementParameters.asMap());
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Record statementParameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate) {
		return run(statementTemplate, Collections.emptyMap());
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate) {
		return null;
	}

	@Override
	public StatementResult run(Statement statement) {
		return run(statement.text(), statement.parameters());
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(Statement statement) {
		return null;
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
	public CompletionStage<Transaction> beginTransactionAsync() {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public <T> T readTransaction(TransactionWork<T> work) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public <T> CompletionStage<T> readTransactionAsync(TransactionWork<CompletionStage<T>> work) {
		throw new UnsupportedOperationException("unimplemented");
	}

	@Override
	public <T> T writeTransaction(TransactionWork<T> work) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public <T> CompletionStage<T> writeTransactionAsync(TransactionWork<CompletionStage<T>> work) {
		throw new UnsupportedOperationException("unimplemented");
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
		for (IncrementalQueryAdapter adapter : adapters) {
			adapter.engine().shutdown();
		}
	}

	@Override
	public CompletionStage<Void> closeAsync() {
		throw new UnsupportedOperationException("unimplemented");
	}

	private ArrayList<IncrementalQueryAdapter> adapters = new ArrayList<>();

	public IngraphQueryHandler registerQuery(String queryName, String querySpecification, Map<String, Object> statementParameters) {
		final IncrementalQueryAdapter adapter = new IncrementalQueryAdapter(querySpecification, queryName, indexer);
		adapters.add(adapter);
		return new IngraphQueryHandler(adapter);
	}

	public IngraphQueryHandler registerQuery(String queryName, String querySpecification) {
		return registerQuery(queryName, querySpecification, Collections.emptyMap());
	}

}
