package ingraph.bulkloader.csv.columnname;

import java.util.Optional;

public class ColumnDescriptor {

	private final ColumnType columnType;
	private final Optional<String> idSpaceName;

	public ColumnDescriptor(ColumnType columnType, Optional<String> idSpaceName) {
		this.columnType = columnType;
		this.idSpaceName = idSpaceName;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public Optional<String> getIdSpaceName() {
		return idSpaceName;
	}

}
