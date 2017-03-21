package ingraph.bulkloader.csv.parser;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_END_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_START_ID;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.types.Entity;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import ingraph.bulkloader.csv.columnname.ColumnNameParser;
import ingraph.bulkloader.csv.entityprocessor.EntityProcessor;

public class CsvParser {

	public static <TEntity extends Entity> List<TEntity> parse(String csv, CsvPreference csvPreference, EntityProcessor<TEntity> processor) throws IOException {
		try (final ICsvMapReader reader = new CsvMapReader(new FileReader(csv), csvPreference)) {

			// process header
			final String[] header = reader.getHeader(true);
			final List<String> columnNameList = new ArrayList<>(header.length);
			final List<CellProcessor> processorsList = new ArrayList<>(header.length);
			for (String column : header) {
				final ColumnNameParser cnp = new ColumnNameParser(column);
				columnNameList.add(cnp.getName());
				processorsList.add(cnp.getType().getCellProcessor());
			}

			// throw exception if there is no "ID" field in the header
			if (!(columnNameList.contains(INTERNAL_ID) //
					|| (columnNameList.contains(INTERNAL_START_ID) && columnNameList.contains(INTERNAL_END_ID)) //
					)) {
				throw new IllegalStateException("CSV file does not have an 'ID' column (nodes) or 'START_ID' and 'END_ID' columns (relationships).");
			}

			final String[] columnNames = columnNameList.toArray(new String[] {});
			final CellProcessor[] processors = processorsList.toArray(new CellProcessor[] {});

			// process rows
			final List<TEntity> entities = new ArrayList<>();

			Map<String, Object> row;
			while ((row = reader.read(columnNames, processors)) != null) {
				final TEntity entity = processor.processRow(row);
				entities.add(entity);
			}

			return entities;
		}
	}

}
