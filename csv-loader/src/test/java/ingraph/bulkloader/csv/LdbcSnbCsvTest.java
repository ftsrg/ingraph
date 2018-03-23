package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableMap;
import ingraph.bulkloader.csv.data.CsvEdge;
import ingraph.bulkloader.csv.data.CsvVertex;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Assert;
import org.junit.Test;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

	final Map<String, ? extends Triple<String, String, String>> edgesCsvs = ImmutableMap.<String, ImmutableTriple<String, String, String>>builder() //
		.put("comment_hasCreator_person", ImmutableTriple.of("Message", "HAS_CREATOR", "Person"))
		.put("comment_hasTag_tag", ImmutableTriple.of("Message", "IS_LOCATED_IN", "Place"))
		.put("comment_isLocatedIn_place", ImmutableTriple.of("Message", "REPLY_OF", "Message"))
		.put("comment_replyOf_comment", ImmutableTriple.of("Message", "REPLY_OF", "Message"))
		.put("comment_replyOf_post", ImmutableTriple.of("Forum", "CONTAINER_OF", "Message"))
		.put("forum_containerOf_post", ImmutableTriple.of("Forum", "HAS_MEMBER", "Person"))
		.put("forum_hasMember_person", ImmutableTriple.of("Forum", "HAS_MODERATOR", "Person"))
		.put("forum_hasModerator_person", ImmutableTriple.of("Forum", "HAS_TAG", "Tag"))
		.put("forum_hasTag_tag", ImmutableTriple.of("Person", "HAS_INTEREST", "Tag"))
		.put("organisation_isLocatedIn_place", ImmutableTriple.of("Person", "IS_LOCATED_IN", "Place"))
		.put("person_hasInterest_tag", ImmutableTriple.of("Person", "KNOWS", "Person"))
		.put("person_isLocatedIn_place", ImmutableTriple.of("Person", "LIKES", "Message"))
		.put("person_knows_person", ImmutableTriple.of("Person", "LIKES", "Message"))
		.put("person_likes_comment", ImmutableTriple.of("Place", "IS_PART_OF", "Place"))
		.put("person_likes_post", ImmutableTriple.of("Message", "HAS_CREATOR", "Person"))
		.put("person_studyAt_organisation", ImmutableTriple.of("Message", "HAS_TAG", "Tag"))
		.put("person_workAt_organisation", ImmutableTriple.of("Message", "HAS_TAG", "Tag"))
		.put("place_isPartOf_place", ImmutableTriple.of("Message", "IS_LOCATED_IN", "Place"))
		.put("post_hasCreator_person", ImmutableTriple.of("TagClass", "IS_SUBCLASS_OF", "TagClass"))
		.put("post_hasTag_tag", ImmutableTriple.of("Tag", "HAS_TYPE", "TagClass"))
		.put("post_isLocatedIn_place", ImmutableTriple.of("Organisation", "IS_LOCATED_IN", "Place"))
		.put("tag_hasType_tagclass", ImmutableTriple.of("Person", "STUDY_OF", "Organisation"))
		.put("tagclass_isSubclassOf_tagclass", ImmutableTriple.of("Person", "WORK_AT", "Organisation"))
			.build();

	final Map<String, Collection<String>> vertexFilenames = addPreAndPostFix(verticesCsvs);
	final Map<String, ? extends Triple<String, String, String>> edgeFilenames = addPreAndPostFix(edgesCsvs);

	protected <T> Map<String, T> addPreAndPostFix(Map<String, T> names) {
		return names.entrySet()
		.stream()
		.collect(Collectors.toMap(
			e -> PREFIX + e.getKey() + POSTFIX,
			e -> e.getValue())
		);
	}

	@Test
	public void testLoad() throws IOException {
		final MassCsvLoader mcl = new MassCsvLoader(vertexFilenames, edgeFilenames, LDBC_CSV_PREFERENCE);
		List<CsvVertex> vertices = mcl.getVertices();
		List<CsvEdge> edges = mcl.getEdges();

		Assert.assertEquals(6, vertices.size());
		Assert.assertEquals(8, edges.size());
	}

}
