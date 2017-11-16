package neo4j.driver.reactive.data;

import com.google.common.collect.Multiset;
import org.neo4j.driver.v1.Record;

public class RecordChangeSet extends ChangeSet<Record> {

	public RecordChangeSet(Multiset<Record> positive, Multiset<Record> negative) {
		super(positive, negative);
	}

}
