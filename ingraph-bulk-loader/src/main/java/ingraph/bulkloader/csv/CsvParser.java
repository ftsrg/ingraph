package ingraph.bulkloader.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import ingraph.bulkloader.csv.columnname.ColumnConstants;
import ingraph.bulkloader.csv.columnname.ColumnNameParser;

public class CsvParser {

	public void parse(String csv, Collection<String> labels) throws FileNotFoundException, IOException {
		try (final ICsvMapReader reader = new CsvMapReader(new FileReader(csv), CsvPreference.STANDARD_PREFERENCE)) {

			// process header
			final String[] header = reader.getHeader(true);
			final List<String> columnNameList = new ArrayList<>(header.length);
			final List<CellProcessor> processorsList = new ArrayList<>(header.length);
			for (String column : header) {
				final ColumnNameParser cnp = new ColumnNameParser(column);
				columnNameList.add(cnp.getName());
				processorsList.add(cnp.getType().getCellProcessor());
			}

			// throw exception if there is not "ID" field in the header
			if (!columnNameList.contains(ColumnConstants.INTERNAL_ID)) {
				throw new IllegalStateException("CSV file does not have an 'ID' column.");
			}

			final String[] columnNames = columnNameList.toArray(new String[] {});
			final CellProcessor[] processors = processorsList.toArray(new CellProcessor[] {});

			// process rows
			Map<String, Object> row;
			while ((row = reader.read(columnNames, processors)) != null) {
				//System.out.println(String.format("lineNo=%s, rowNo=%s, customerMap=%s", reader.getLineNumber(), reader.getRowNumber(), row));

				final Long id = (Long) row.get(ColumnConstants.INTERNAL_ID);
				final Map<String, Value> properties = new HashMap<>();

				for (Entry<String, Object> entry : row.entrySet()) {
					if (entry.getKey().equals(ColumnConstants.INTERNAL_ID)) {
						continue;
					} else {
						properties.put(entry.getKey(), Values.value(entry.getValue()));
					}
				}

				final InternalNode node = new InternalNode(id, labels, properties);
				System.out.println(PrettyPrinter.toString(node));

			}
		}
	}

}
