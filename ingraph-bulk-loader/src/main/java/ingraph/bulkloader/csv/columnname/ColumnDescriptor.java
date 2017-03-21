package ingraph.bulkloader.csv.columnname;

import java.util.Optional;

public class ColumnDescriptor {

	private final String name;
	private final ColumnType columnType;
	private final Optional<String> idSpace;

	public ColumnDescriptor(String name, ColumnType columnType, Optional<String> idSpace) {
		this.name = name;
		this.columnType = columnType;
		this.idSpace = idSpace;
	}

	public String getName() {
		return name;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public Optional<String> getIdSpace() {
		return idSpace;
	}

}
