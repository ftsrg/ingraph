package neo4j.driver.reactive.interfaces;

import neo4j.driver.reactive.data.RecordChangeSet;
import org.neo4j.driver.v1.Session;

public interface ReactiveSession extends Session {

	RecordChangeSetListener registerQuery(String queryName, String querySpecification);

	RecordChangeSet getDeltas(String queryName);

}
