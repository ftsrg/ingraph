package ingraph.bulkloader.csv.columnname;

import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;

public enum ColumnType {

	LABEL(new ParseLabels()),
	ID(new ParseLong()),
	START_ID(new ParseLong()),
	END_ID(new ParseLong()),
	STRING(null), // null means the string in the cell is parsed "as is"
	BOOLEAN(new ParseBool()),
	INT(new ParseInt()),
	LONG(new ParseLong()),
	FLOAT(new ParseDouble()),
	;

	private final CellProcessor cellProcessor;

	ColumnType(final CellProcessor cellProcessor) {
		this.cellProcessor = cellProcessor;
	}

	public CellProcessor getCellProcessor() {
		return cellProcessor;
	}

}
