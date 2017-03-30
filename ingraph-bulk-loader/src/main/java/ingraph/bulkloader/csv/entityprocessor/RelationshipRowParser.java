package ingraph.bulkloader.csv.entityprocessor;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_END_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_START_ID;

import java.util.Map;

import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Relationship;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.IdSpaces;

public class RelationshipRowParser extends EntityRowParser<Relationship> {

	private final String type;
	private final IdGenerator idGenerator;

	public RelationshipRowParser(final IdSpaces idSpaces, final String type, final IdGenerator idGenerator) {
		super(idSpaces);
		this.type = type;
		this.idGenerator = idGenerator;
	}

	@Override
	public Relationship processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors) {
		// ids
		final Long id = idGenerator.generateNewId();
		final Long startId = getId(row, columnDescriptors, INTERNAL_START_ID);
		final Long endId = getId(row, columnDescriptors, INTERNAL_END_ID);
		// properties
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		return new InternalRelationship(id, startId, endId, type, properties);
	}

}
