package ingraph.bulkloader.csv.parser;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.columnname.ColumnNameParser;
import ingraph.bulkloader.csv.columnname.ColumnType;
import ingraph.bulkloader.csv.data.CsvEntity;
import ingraph.bulkloader.csv.entityprocessor.EntityRowParser;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_END_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_START_ID;

public class CsvParser {

	public static <TEntity extends CsvEntity> List<TEntity> parse(String csv, CsvPreference csvPreference,
																																EntityRowParser<TEntity> processor) throws IOException {
		try (final ICsvMapReader reader = new CsvMapReader(new FileReader(csv), csvPreference)) {
			// process header
			final String[] header = reader.getHeader(true);

			final LinkedHashMap<String, ColumnDescriptor> columnDescriptors = new LinkedHashMap<>();
			for (String column : header) {
				final ColumnNameParser cnp = new ColumnNameParser(column);
				columnDescriptors.put(cnp.getName(), new ColumnDescriptor(cnp.getType()));
			}

			if (columnDescriptors.keySet().stream() //
					.filter(x -> x.equals(INTERNAL_ID) || x.equals(INTERNAL_START_ID) || x.equals(INTERNAL_END_ID)) //
					.collect(Collectors.toList()).isEmpty()) {
				throw new IllegalStateException(
						"CSV header does not have an 'ID' column (nodes) or 'START_ID' and 'END_ID' columns (relationships).");
			}

			final String[] columnNames = columnDescriptors.keySet().toArray(new String[]{});
			final CellProcessor[] processors = columnDescriptors.values().stream()
					.map(ColumnDescriptor::getColumnType) //
					.map(ColumnType::getCellProcessor) //
					.toArray(CellProcessor[]::new);

			// process rows
			final List<TEntity> entities = new ArrayList<>();

			Map<String, Object> row;
			while ((row = reader.read(columnNames, processors)) != null) {
				final TEntity entity = processor.processRow(row, columnDescriptors);
				entities.add(entity);
			}

			return entities;
		}
	}

}
