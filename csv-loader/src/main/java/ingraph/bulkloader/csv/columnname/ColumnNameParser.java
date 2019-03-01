package ingraph.bulkloader.csv.columnname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColumnNameParser {

	private final String name;
	private final ColumnType type;

	public ColumnNameParser(String columnName) {
		final String[] splitted = columnName.split(":");

		if (splitted.length > 1) {
			final String typeInfo = splitted[1];

			if (typeInfo.toLowerCase().equals("string[]")) {
				type = ColumnType.STRING_ARRAY;
			} else {
				Pattern idSpaceRegex = Pattern.compile("(ID|START_ID|END_ID)\\((.*)\\)", Pattern.CASE_INSENSITIVE);
				Matcher matcher = idSpaceRegex.matcher(typeInfo);

				if (matcher.matches()) {
					type = ColumnType.valueOf(matcher.group(1).toUpperCase());
				} else {
					type = ColumnType.valueOf(typeInfo);
				}
			}
		} else {
			type = ColumnType.STRING;
		}

		if (type == ColumnType.LABEL) {
			name = ColumnConstants.INTERNAL_LABEL;
		} else if (type == ColumnType.ID) {
			name = ColumnConstants.INTERNAL_ID;
		} else if (type == ColumnType.START_ID) {
			name = ColumnConstants.INTERNAL_START_ID;
		} else if (type == ColumnType.END_ID) {
			name = ColumnConstants.INTERNAL_END_ID;
		} else {
			name = splitted[0];
		}
	}

	public String getName() {
		return name;
	}

	public ColumnType getType() {
		return type;
	}

}
