package ingraph.bulkloader.csv;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.neo4j.driver.internal.value.NodeValue;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import com.google.common.base.Joiner;

public class PrettyPrinter {

	public static String toString(Record record) {
		Joiner entityJoiner = Joiner.on(", ");

		List<String> formattedEntityList = record.fields() //
				.stream() //
				.map(pair -> String.format("%s=%s", pair.key(), toString(pair.value()))) //
				.collect(Collectors.toList());

		return String.format("<%s>", entityJoiner.join(formattedEntityList));
	}

	public static String toString(Node node) {
		Joiner labelJoiner = Joiner.on("");
		List<String> formattedLabelList = StreamSupport //
				.stream(node.labels().spliterator(), false) //
				.map(label -> ":" + label) //
				.collect(Collectors.toList());
		String labelsString = labelJoiner.join(formattedLabelList);

		String propertiesString = toString(node.asMap());

		return String.format( //
				"(%s%s%s)", //
				labelsString, //
				labelsString.isEmpty() || propertiesString.isEmpty() ? "" : " ", //
				propertiesString);
	}

	public static String toString(Relationship relationship) {
		String propertiesString = toString(relationship.asMap());

		return String.format("[:%s%s%s]", relationship.type(), //
				propertiesString.isEmpty() ? "" : " ", //
				propertiesString);
	}

	public static String toString(Path path) {
		throw new UnsupportedOperationException("Serialization of paths is not yet implemented.");
	}

	private static String toString(Map<String, Object> propertiesMap) {
		if (propertiesMap.isEmpty()) {
			return "";
		} else {
			Joiner propertyJoiner = Joiner.on(", ");

			List<String> formattedPropertyList = propertiesMap //
					.entrySet() //
					.stream() //
					.map(entry -> entry.getKey() + ": " + format(entry.getValue())) //
					.collect(Collectors.toList());
			return "{" + propertyJoiner.join(formattedPropertyList) + "}";
		}
	}

	public static String toString(Value value) {
		if (value instanceof NodeValue) {
			return toString(value.asNode());
		} else if (value instanceof RelationshipValue) {
			return toString(value.asRelationship());
		} else if (value instanceof PathValue) {
			return toString(value.asPath());
		}

		return value.toString();
	}

	private static String format(Object value) {
		if (value instanceof String) {
			return "\"" + value + "\"";
		} else {
			return value.toString();
		}
	}

}
