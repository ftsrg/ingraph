package ingraph.driver.ingraph;

import ingraph.driver.data.TupleToRecordRepackager;
import ingraph.model.fplan.Production;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.ire.Indexer;
import ingraph.ire.IngraphIncrementalAdapter;
import ingraph.ire.IngraphOneTimeAdapter;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.NoSuchRecordException;
import org.neo4j.driver.v1.summary.ResultSummary;
import org.neo4j.driver.v1.types.TypeSystem;
import org.neo4j.driver.v1.util.Function;
import scala.collection.JavaConversions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	public StatementResult run(String statementTemplate, Map<String, Object> statementParameters) {
		final IngraphOneTimeAdapter adapter = new IngraphOneTimeAdapter(statementTemplate, "onetime" + oneTimeQueryIndex, indexer);
		oneTimeQueryIndex++;
		adapter.terminate();

		Production prod = (Production) adapter.plan();
		TupleToRecordRepackager repackager = new TupleToRecordRepackager(prod.outputNames());

		return new StatementResult() {
			@Override
			public List<String> keys() {
				return JavaConversions.seqAsJavaList(prod.outputNames().toSeq());
			}

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public Record next() {
				return null;
			}

			@Override
			public Record single() throws NoSuchRecordException {
				return null;
			}

			@Override
			public Record peek() {
				return null;
			}

			@Override
			public List<Record> list() {
				return (List<Record>)repackager.repackageResult(adapter.engine().getResults());
			}

			@Override
			public <T> List<T> list(Function<Record, T> mapFunction) {
				return null;
			}

			@Override
			public ResultSummary consume() {
				return null;
			}

			@Override
			public ResultSummary summary() {
				return null;
			}
		};
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

}
