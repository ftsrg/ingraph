package ingraph.driver.ingraph;

import java.util.Collection;

import org.supercsv.prefs.CsvPreference;

import hu.bme.mit.ire.Transaction;
import ingraph.ire.IngraphAdapter;
import neo4j.driver.reactive.data.RecordChangeSet;
import neo4j.driver.reactive.interfaces.RecordChangeSetListener;
import scala.collection.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;

public class IngraphRecordChangeSetListener implements RecordChangeSetListener {

	private final IngraphAdapter adapter;

	public IngraphRecordChangeSetListener(IngraphAdapter adapter) {
		super();
		this.adapter = adapter;
	}

	@Override
	public void notify(RecordChangeSet rcs) {
	}

	public void readCsv(Map<String, List<String>> nodeFilenames, Map<String, String> relationshipFilenames,
			CsvPreference csvPreference) {
		final Transaction transaction = adapter.getNewTransaction();
		adapter.readCsv(nodeFilenames, relationshipFilenames, transaction, csvPreference);
		transaction.close();

		Collection<IndexedSeq<Object>> results = adapter.getResult();
		for (IndexedSeq<Object> indexedSeq : results) {
			System.out.println(indexedSeq);
		}
	}

}
