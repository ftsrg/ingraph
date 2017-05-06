package ingraph.driver.ingraph;

import hu.bme.mit.ire.Transaction;
import ingraph.driver.data.IngraphDeltaHandler;
import ingraph.driver.data.Repackager;
import ingraph.ire.IngraphAdapter;
import org.neo4j.driver.v1.Record;
import org.supercsv.prefs.CsvPreference;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class IngraphQueryHandler {

	private final IngraphAdapter adapter;

	public IngraphQueryHandler(IngraphAdapter adapter) {
		super();
		this.adapter = adapter;
	}

	public void registerDeltaHandler(IngraphDeltaHandler listener) {
		adapter.addListener(listener);
	}

	public void readCsv(Map<String, List<String>> nodeFilenames, Map<String, String> relationshipFilenames,
			CsvPreference csvPreference) {
		final Transaction transaction = adapter.newTransaction();

		adapter.readCsvJava(nodeFilenames, relationshipFilenames, transaction, csvPreference);
		transaction.close();

		final Iterable<IndexedSeq<Object>> result = adapter.result();
		final Repackager repackager = new Repackager(adapter.resultNames());
		final Collection<? extends Record> records = repackager.repackageResult(result);
		for (Record record : records) {
			System.out.println(record);
		}
	}
}
