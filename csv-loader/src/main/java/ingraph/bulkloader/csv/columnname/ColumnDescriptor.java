package ingraph.bulkloader.csv.columnname;

public class ColumnDescriptor {

	private final ColumnType columnType;

	public ColumnDescriptor(ColumnType columnType) {
		this.columnType = columnType;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

}
