package ingraph.driver.ingraph;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Statement;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.StatementResultCursor;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.TypeSystem;

import java.util.Map;
import java.util.concurrent.CompletionStage;

public class IngraphTransaction implements Transaction {

	final IngraphSession session;

	public IngraphTransaction(final IngraphSession session) {
		this.session = session;
	}

	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public StatementResult run(String statementTemplate, Value parameters) {
		return session.run(statementTemplate, parameters);
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Value parameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate, Map<String, Object> statementParameters) {
		return session.run(statementTemplate, statementParameters);
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Map<String, Object> statementParameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate, Record statementParameters) {
		return session.run(statementTemplate, statementParameters);
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate, Record statementParameters) {
		return null;
	}

	@Override
	public StatementResult run(String statementTemplate) {
		return session.run(statementTemplate);
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(String statementTemplate) {
		return null;
	}

	@Override
	public StatementResult run(Statement statement) {
		return session.run(statement);
	}

	@Override
	public CompletionStage<StatementResultCursor> runAsync(Statement statement) {
		return null;
	}

	@Override
	public TypeSystem typeSystem() {
		return session.typeSystem();
	}

	@Override
	public void success() {
	}

	@Override
	public void failure() {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public void close() {
	}

	@Override
	public CompletionStage<Void> commitAsync() {
		return null;
	}

	@Override
	public CompletionStage<Void> rollbackAsync() {
		return null;
	}

}
