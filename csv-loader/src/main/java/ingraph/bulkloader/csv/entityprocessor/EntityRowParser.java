package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.IdSpaces;
import org.neo4j.driver.v1.types.Entity;

import java.util.Map;
import java.util.Optional;

public abstract class EntityRowParser<TEntity extends Entity> {

	protected final IdSpaces idSpaces;

	public EntityRowParser(IdSpaces idSpaces) {
		super();
		this.idSpaces = idSpaces;
	}

	public abstract TEntity processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors);

	/**
	 * If there is an IdSpace for the column, get the id from the IdSpace for the internal id. Else, return the internal id.
	 *
	 * @param idSpaceName
	 * @param internalId
	 * @return
	 */
	protected long getId(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors, final String internalIdName) {
		final Optional<String> idSpaceName = columnDescriptors.get(internalIdName).getIdSpaceName();
		final Object internalId = row.get(internalIdName);

		final long id;
		if (idSpaceName.isPresent()) {
			id = idSpaces.add(idSpaceName.get(), internalId);
		} else {
			id = (long) internalId;
		}
		return id;
	}

}
