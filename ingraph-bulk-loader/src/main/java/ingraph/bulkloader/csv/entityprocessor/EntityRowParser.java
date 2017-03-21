package ingraph.bulkloader.csv.entityprocessor;

import java.util.Map;

import org.neo4j.driver.v1.types.Entity;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.IdSpaces;

public abstract class EntityRowParser<TEntity extends Entity> {

	protected final IdSpaces idSpaces;

	public EntityRowParser(IdSpaces idSpaces) {
		super();
		this.idSpaces = idSpaces;
	}

	public abstract TEntity processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors);

}
