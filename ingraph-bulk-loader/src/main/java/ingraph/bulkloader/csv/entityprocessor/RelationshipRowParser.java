package ingraph.bulkloader.csv.entityprocessor;

import java.util.Map;

import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Relationship;

import ingraph.bulkloader.csv.columnname.ColumnConstants;
import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.IdSpaces;

public class RelationshipRowParser extends EntityRowParser<Relationship> {

	private final String type;

	public RelationshipRowParser(final IdSpaces idSpaces, final String type) {
		super(idSpaces);
		this.type = type;
	}

	@Override
	public Relationship processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors) {
		final Long id = 0L; // TODO generate id for relationships
		final Long start = (Long) row.get(ColumnConstants.INTERNAL_START_ID);
		final Long end = (Long) row.get(ColumnConstants.INTERNAL_END_ID);
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		return new InternalRelationship(id, start, end, type, properties);
	}

}
