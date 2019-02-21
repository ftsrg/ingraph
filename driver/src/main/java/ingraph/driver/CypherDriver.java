package ingraph.driver;

import org.neo4j.driver.v1.Driver;

import java.util.concurrent.CompletionStage;

public abstract class CypherDriver implements Driver {

	@Override
	public CompletionStage<Void> closeAsync() {
		throw new UnsupportedOperationException("unimplemented");
	}

}
