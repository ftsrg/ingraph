package ingraph.bulkloader.csv.loader;

import ingraph.bulkloader.csv.data.CsvEdge;
import ingraph.bulkloader.csv.data.CsvVertex;
import ingraph.bulkloader.csv.entityprocessor.EdgeRowParser;
import ingraph.bulkloader.csv.entityprocessor.IdGenerator;
import ingraph.bulkloader.csv.entityprocessor.VertexRowParser;
import ingraph.bulkloader.csv.parser.CsvParser;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MassCsvLoader {

	private final CsvPreference csvPreference;
	private final VertexRowParser vertexProcessor;
	private final EdgeRowParser edgeProcessor;

	public MassCsvLoader() {
		this(CsvPreference.STANDARD_PREFERENCE);
	}

	public MassCsvLoader(final CsvPreference csvPreference) {
		this.vertexProcessor = new VertexRowParser();
		this.edgeProcessor = new EdgeRowParser(new IdGenerator());
		this.csvPreference = csvPreference;
	}

	public List<CsvVertex> loadVertices(String fileName) throws IOException {
		List<CsvVertex> vertices = new ArrayList<>();
		vertices.addAll(CsvParser.parse(fileName, csvPreference, vertexProcessor));
		return vertices;
	}

	public List<CsvEdge> loadEdges(String fileName) throws IOException {
		List<CsvEdge> edges = new ArrayList<>();
		edges.addAll(CsvParser.parse(fileName, csvPreference, edgeProcessor));
		return edges;
	}

}
