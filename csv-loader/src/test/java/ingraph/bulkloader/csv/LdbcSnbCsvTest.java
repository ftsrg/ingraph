package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableMap;
import org.supercsv.prefs.CsvPreference;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class LdbcSnbCsvTest {

	public static final String PREFIX = TestConstants.GRAPHS_DIR + "/ldbc-snb-bi/17/";
	public static final String POSTFIX = "_0_0.csv";
	public static final CsvPreference LDBC_CSV_PREFERENCE =
			new CsvPreference.Builder('"', '|', "\n").build();

	final Map<String, Collection<String>> verticesCsvs = ImmutableMap.<String, Collection<String>>builder() //
			.put("comment", Arrays.asList("Message", "Comment")) //
			.put("forum", Arrays.asList("Forum")) //
			.put("organisation", Arrays.asList("Organisation")) //
			.put("person", Arrays.asList("Person")) //
			.put("place", Arrays.asList("Place")) //
			.put("post", Arrays.asList("Message", "Post")) //
			.put("tag", Arrays.asList("Tag")) //
			.put("tagclass", Arrays.asList("TagClass")) //
			.build();

	final Map<String, String> edgesCsvs = ImmutableMap.<String, String>builder() //
		.put("comment_hasCreator_person", "HAS_CREATOR") //
			.put("comment_hasTag_tag", "HAS_TAG") //
			.put("comment_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("comment_replyOf_comment", "REPLY_OF") //
			.put("comment_replyOf_post", "REPLY_OF") //
			.put("forum_containerOf_post", "CONTAINER_OF") //
			.put("forum_hasMember_person", "HAS_MEMBER") //
			.put("forum_hasModerator_person", "HAS_MODERATOR") //
			.put("forum_hasTag_tag", "HAS_TAG") //
			.put("organisation_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("person_hasInterest_tag", "HAS_INTEREST") //
			.put("person_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("person_knows_person", "KNOWS") //
			.put("person_likes_comment", "LIKES") //
			.put("person_likes_post", "LIKES") //
			.put("person_studyAt_organisation", "STUDY_OF") //
			.put("person_workAt_organisation", "WORK_AT") //
			.put("place_isPartOf_place", "IS_PART_OF") //
			.put("post_hasCreator_person", "HAS_CREATOR") //
			.put("post_hasTag_tag", "HAS_TAG") //
			.put("post_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("tag_hasType_tagclass", "HAS_TYPE") //
			.put("tagclass_isSubclassOf_tagclass", "IS_SUBCLASS_OF") //
			.build();

	final Map<String, Collection<String>> vertexFilenames = addPreAndPostFix(verticesCsvs);
	final Map<String, String> edgeFilenames = addPreAndPostFix(edgesCsvs);

	protected <T> Map<String, T> addPreAndPostFix(Map<String, T> names) {
		return names.entrySet()
		.stream()
		.collect(Collectors.toMap(
			e -> PREFIX + e.getKey() + POSTFIX,
			e -> e.getValue())
		);
	}


//	@Test
//	public void testLoad() throws IOException {
//		final MassCsvLoader mcl = new MassCsvLoader(vertexFilenames, edgeFilenames, LDBC_CSV_PREFERENCE);
//		List<CsvVertex> vertices = mcl.getVertices();
//		List<CsvEdge> edges = mcl.getEdges();
//
//		Assert.assertEquals(6, vertices.size());
//		Assert.assertEquals(8, edges.size());
//	}

}
