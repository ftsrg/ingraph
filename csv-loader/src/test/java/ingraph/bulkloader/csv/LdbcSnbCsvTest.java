package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableList;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import org.junit.Assert;
import org.junit.Test;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LdbcSnbCsvTest {

	public static final String PREFIX = TestConstants.GRAPHS_DIR + "/ldbc-snb-bi/17/";
	public static final String POSTFIX = "_0_0.csv";
	public static final CsvPreference LDBC_CSV_PREFERENCE =
			new CsvPreference.Builder('"', '|', "\n").build();

	final List<String> vertexFileNames = addPreAndPostFix(ImmutableList.of(
		"comment", "forum", "organisation", "person", "place", "post", "tag", "tagclass"
	));

	final List<String> edgeFileNames = addPreAndPostFix(ImmutableList.of(
		"comment_hasCreator_person", "comment_hasTag_tag", "comment_isLocatedIn_place", "comment_replyOf_comment", "comment_replyOf_post",
		"forum_containerOf_post", "forum_hasMember_person", "forum_hasModerator_person", "forum_hasTag_tag",
		"organisation_isLocatedIn_place",
		"person_hasInterest_tag", "person_isLocatedIn_place", "person_knows_person", "person_likes_comment", "person_likes_post", "person_studyAt_organisation", "person_workAt_organisation",
		"place_isPartOf_place",
		"post_hasCreator_person", "post_hasTag_tag", "post_isLocatedIn_place",
		"tag_hasType_tagclass", "tagclass_isSubclassOf_tagclass"
	));

	protected List<String> addPreAndPostFix(List<String> names) {
		return names
			.stream()
			.map(x -> PREFIX + x + POSTFIX)
			.collect(Collectors.toList());
	}

	@Test
	public void testLoad() throws IOException {
		final MassCsvLoader mcl = new MassCsvLoader(LDBC_CSV_PREFERENCE);

		int vertexCount = 0;
		int edgeCount = 0;
		for (String fileName : vertexFileNames) {
			vertexCount += mcl.loadVertices(fileName).size();
		}
		for (String fileName : edgeFileNames) {
			edgeCount += mcl.loadEdges(fileName).size();
		}

		Assert.assertEquals(6, vertexCount);
		Assert.assertEquals(8, edgeCount);
	}

}
