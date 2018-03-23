package ingraph.bulkloader.csv.loader;

import com.google.common.collect.Sets;
import ingraph.bulkloader.csv.data.CsvEdge;
import ingraph.bulkloader.csv.data.CsvVertex;
import ingraph.bulkloader.csv.entityprocessor.EdgeRowParser;
import ingraph.bulkloader.csv.entityprocessor.IdGenerator;
import ingraph.bulkloader.csv.entityprocessor.VertexRowParser;
import ingraph.bulkloader.csv.idspaces.IdSpaces;
import ingraph.bulkloader.csv.parser.CsvParser;
import org.apache.commons.lang3.tuple.Triple;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MassCsvLoader {

	private final List<CsvVertex> vertices = new ArrayList<>();
	private final List<CsvEdge> edges = new ArrayList<>();
	private final IdSpaces idSpaces = new IdSpaces();

	public MassCsvLoader(Map<String, Collection<String>> vertexFilenames, Map<String, ? extends Triple<String, String, String>> edgeFilenames)
			throws IOException {
		this(vertexFilenames, edgeFilenames, CsvPreference.STANDARD_PREFERENCE);
	}

	public MassCsvLoader(Map<String, Collection<String>> vertexFilenames, Map<String, ? extends Triple<String, String, String>> edgeFilenames,
			CsvPreference csvPreference) throws IOException {
		loadVertices(vertexFilenames, csvPreference);
		loadEdges(edgeFilenames, csvPreference);
	}

	private void loadVertices(Map<String, Collection<String>> vertexFilenames, CsvPreference csvPreference)
			throws IOException {
		for (final Map.Entry<String, Collection<String>> vertexFileEntry : vertexFilenames.entrySet()) {
			final String filename = vertexFileEntry.getKey();
			final Set<String> labels = Sets.newHashSet(vertexFileEntry.getValue());

			final VertexRowParser vertexProcessor = new VertexRowParser(idSpaces, labels);
			vertices.addAll(CsvParser.parse(filename, csvPreference, vertexProcessor));
		}
	}

	private void loadEdges(Map<String, ? extends Triple<String, String, String>> edgeFilenames, CsvPreference csvPreference) throws IOException {
		final IdGenerator idGenerator = new IdGenerator();

		for (final Map.Entry<String, ? extends Triple<String, String, String>> edgeFileEntry : edgeFilenames.entrySet()) {
			final String filename = edgeFileEntry.getKey();
			final Triple<String, String, String> type = edgeFileEntry.getValue();

			final EdgeRowParser edgeProcessor = new EdgeRowParser(idSpaces, type, idGenerator);
			edges.addAll(CsvParser.parse(filename, csvPreference, edgeProcessor));
		}
	}

	public List<CsvVertex> getVertices() {
		return vertices;
	}

	public List<CsvEdge> getEdges() {
		return edges;
	}

}
