package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.CsvEntity;

import java.util.Map;

public abstract class EntityRowParser<TEntity extends CsvEntity> {

	public EntityRowParser() {
		super();
	}

	public abstract TEntity processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors);

	protected long getId(final Map<String, Object> row, final String internalIdName) {
 		return (long) row.get(internalIdName);
	}


}
