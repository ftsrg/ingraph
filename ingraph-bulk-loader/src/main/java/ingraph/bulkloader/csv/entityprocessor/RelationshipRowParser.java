package ingraph.bulkloader.csv.entityprocessor;

import java.util.Map;

import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Relationship;

import ingraph.bulkloader.csv.columnname.ColumnConstants;

public class RelationshipRowParser extends EntityRowParser<Relationship> {

	private final String type;

	public RelationshipRowParser(String type) {
		super();
		this.type = type;
	}

	@Override
	public Relationship processRow(Map<String, Object> row) {
		final Long id = 0L; // TODO generate id for relationships
		final Long start = (Long) row.get(ColumnConstants.INTERNAL_START_ID);
		final Long end = (Long) row.get(ColumnConstants.INTERNAL_END_ID);
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		return new InternalRelationship(id, start, end, type, properties);
	}

}
