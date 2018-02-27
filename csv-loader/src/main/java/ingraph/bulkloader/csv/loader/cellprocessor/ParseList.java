package ingraph.bulkloader.csv.loader.cellprocessor;

import com.google.common.base.Splitter;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CsvContext;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ParseList extends CellProcessorAdaptor {

	public static char SEPARATOR = ';';
	private Function<? super String, ?> mapper;

	public ParseList(Function<? super String, ?> mapper) {
		super();
		this.mapper = mapper;
	}

	public Object execute(Object value, CsvContext context) {
		final String nonNullValue = Optional.ofNullable(value).orElse("").toString();
		final Iterable<String> splitted = Splitter.on(SEPARATOR).split(nonNullValue);
		return StreamSupport.stream(splitted.spliterator(), false)
			.filter(s -> !s.isEmpty())
			.map(mapper)
			.collect(Collectors.toList());
	}
}
