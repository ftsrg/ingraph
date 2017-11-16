package ingraph.bulkloader.csv.columnname;

import com.google.common.collect.Sets;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.util.Set;

public class ParseLabels extends CellProcessorAdaptor implements StringCellProcessor {

	/**
	 * Constructs a new <tt>ParseLabels</tt> processor, which converts a String
	 * to labels.
	 *
	 */
	public ParseLabels() {
		super();
	}

	/**
	 * Constructs a new <tt>ParseLabels</tt> processor, which converts a String to
	 * labels, then calls the next processor in the chain.
	 *
	 * @param next
	 *          the next processor in the chain
	 */
	public ParseLabels(final CellProcessor next) {
		super(next);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws SuperCsvCellProcessorException
	 *           if value is null or can't be parsed
	 */
	@Override
	public Set<String> execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		final String inputString = value.toString();

		Set<String> labels = Sets.newHashSet(inputString.split(";"));
		return labels;
	}
}
