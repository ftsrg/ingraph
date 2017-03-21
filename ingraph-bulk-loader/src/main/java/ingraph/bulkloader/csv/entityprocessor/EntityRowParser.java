package ingraph.bulkloader.csv.entityprocessor;

import java.util.Map;

import org.neo4j.driver.v1.types.Entity;

public abstract class EntityRowParser<TEntity extends Entity> {

	public abstract TEntity processRow(final Map<String, Object> row);

}
