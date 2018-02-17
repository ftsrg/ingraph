package ingraph.bulkloader.csv.loader.cellprocessor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ParseEpochToDateTime extends CellProcessorAdaptor implements StringCellProcessor {

	final static String DATETIME_FORMAT = "yyyyMMddHHmmssSSS";
	final static DateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);

	static {
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public ParseEpochToDateTime() {
		super();
	}

	public Object execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		if (!(value instanceof String)) {
			throw new SuperCsvCellProcessorException(String.class, value, context, this);
		}

		long epoch = Long.parseLong(value.toString());
		long result = Long.valueOf(formatter.format(new java.util.Date(epoch)));

		return next.execute(result, context);
	}

}
