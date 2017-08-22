package neo4j.driver.reactive.data;

import org.neo4j.driver.v1.Record;

import com.google.common.collect.Multiset;

public class RecordChangeSet extends ChangeSet<Record> {

	public RecordChangeSet(Multiset<Record> positive, Multiset<Record> negative) {
		super(positive, negative);
	}

}
