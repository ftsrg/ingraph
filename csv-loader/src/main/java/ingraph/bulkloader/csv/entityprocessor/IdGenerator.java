package ingraph.bulkloader.csv.entityprocessor;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	protected AtomicLong latestId = new AtomicLong(0L);

	public Long generateNewId() {
		return latestId.incrementAndGet();
	}

}
