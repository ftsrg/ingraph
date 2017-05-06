package ingraph.driver.ingraph;

import ingraph.driver.data.IngraphQueryHandler;
import ingraph.ire.IngraphAdapter;
import neo4j.driver.reactive.data.RecordChangeSet;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.TypeSystem;

import java.util.Collections;
import java.util.Map;

public class IngraphSession implements Session {

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

	public IngraphQueryHandler registerQuery(String queryName, String querySpecification) {
		final IngraphAdapter adapter = new IngraphAdapter(querySpecification, queryName);
		final IngraphQueryHandler listener = new IngraphQueryHandler(adapter);
		return listener;
	}

	public RecordChangeSet getDeltas(String queryName) {
		return null;
	}

}
