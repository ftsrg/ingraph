package neo4j.driver.reactive.interfaces;

import neo4j.driver.reactive.data.RecordChangeSet;

public interface RecordChangeSetListener {

	void notify(RecordChangeSet rcs);

}
