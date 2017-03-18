package ingraph.bulkloader.csv.columnname;

import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public enum ColumnType {

	ID(new ParseLong()),
	START_ID(new ParseLong()),
	END_ID(new ParseLong()),
	STRING(new NotNull()),
	BOOLEAN(new ParseBool()),
	INT(new ParseInt()),
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
