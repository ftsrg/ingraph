package ingraph.bulkloader.csv.columnname;

import com.google.common.collect.Lists;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.util.List;

public class ParseStringArray extends CellProcessorAdaptor implements StringCellProcessor {

	/**
	 * Constructs a new <tt>ParseStringArray</tt> processor, which converts a String
	 * to labels.
	 *
	 */
	public ParseStringArray() {
		super();
	}

	/**
	 * Constructs a new <tt>ParseStringArray</tt> processor, which converts a String to
	 * labels, then calls the next processor in the chain.
	 *
	 * @param next
	 *          the next processor in the chain
	 */
	public ParseStringArray(final CellProcessor next) {
		super(next);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws SuperCsvCellProcessorException
	 *           if value is null or can't be parsed
	 */
	@Override
	public List<String> execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		final String inputString = value.toString();

		List<String> labels = Lists.newArrayList(inputString.split(";"));
		return labels;
	}
}
