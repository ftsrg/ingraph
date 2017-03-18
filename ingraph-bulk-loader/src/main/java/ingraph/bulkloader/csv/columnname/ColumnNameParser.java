package ingraph.bulkloader.csv.columnname;

public class ColumnNameParser {

	private final String name;
	private final ColumnType type;

	public ColumnNameParser(String columnName) {
		final String[] splitted = columnName.split(":");

		if (splitted.length > 1) {
			type = ColumnType.valueOf(splitted[1].toUpperCase());
		} else {
			type = ColumnType.STRING;
		}

		if (type == ColumnType.ID) {
			name = ColumnConstants.INTERNAL_ID;
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
