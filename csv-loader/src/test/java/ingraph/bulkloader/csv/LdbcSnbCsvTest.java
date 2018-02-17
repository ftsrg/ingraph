package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableMap;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import neo4j.driver.util.GraphPrettyPrinter;
import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LdbcSnbCsvTest {

	public static final String PREFIX = TestConstants.GRAPHS_DIR + "/ldbc-snb-bi/01/";
	public static final String POSTFIX = "_0_0.csv";
	public static final CsvPreference LDBC_CSV_PREFERENCE =
			new CsvPreference.Builder('"', '|', "\n").build();

	final Map<String, Collection<String>> nodeCsvs = ImmutableMap.<String, Collection<String>>builder() //
			.put("comment", Arrays.asList("Message", "Comment")) //
			.put("forum", Arrays.asList("Forum")) //
			.put("organisation", Arrays.asList("Organisation")) //
			.put("person", Arrays.asList("Person")) //
			.put("place", Arrays.asList("Place")) //
			.put("post", Arrays.asList("Message", "Post")) //
			.put("tagclass", Arrays.asList("TagClass")) //
			.put("tag", Arrays.asList("Tag")) //
			.build();

	final Map<String, String> relationshipCsvs = ImmutableMap.<String, String>builder() //
			.put("comment_hasCreator_person", "HAS_CREATOR") //
			.put("comment_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("comment_replyOf_comment", "REPLY_OF") //
			.put("comment_replyOf_post", "REPLY_OF") //
			.put("forum_containerOf_post", "CONTAINER_OF") //
			.put("forum_hasMember_person", "HAS_MEMBER") //
			.put("forum_hasModerator_person", "HAS_MODERATOR") //
			.put("forum_hasTag_tag", "HAS_TAG") //
			.put("person_hasInterest_tag", "HAS_INTEREST") //
			.put("person_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("person_knows_person", "KNOWS") //
			.put("person_likes_comment", "LIKES") //
			.put("person_likes_post", "LIKES") //
			.put("place_isPartOf_place", "IS_PART_OF") //
			.put("post_hasCreator_person", "HAS_CREATOR") //
			.put("comment_hasTag_tag", "HAS_TAG") //
			.put("post_hasTag_tag", "HAS_TAG") //
			.put("post_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("tagclass_isSubclassOf_tagclass", "IS_SUBCLASS_OF") //
			.put("tag_hasType_tagclass", "HAS_TYPE") //
			.put("organisation_isLocatedIn_place", "IS_LOCATED_IN") //
			.put("person_studyAt_organisation", "STUDY_OF") //
			.put("person_workAt_organisation", "WORK_AT") //
			.build();

	final Map<String, Collection<String>> nodeFilenames = addPreAndPostFix(nodeCsvs);
	final Map<String, String> relationshipFilenames = addPreAndPostFix(relationshipCsvs);

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
		final MassCsvLoader mcl = new MassCsvLoader(nodeFilenames, relationshipFilenames, LDBC_CSV_PREFERENCE);
		List<Node> nodes = mcl.getNodes();
		List<Relationship> relationships = mcl.getRelationships();

		for (int i = 0; i < Math.min(100, nodes.size()); i++) {
			System.out.println(GraphPrettyPrinter.toString(nodes.get(i)));
		}

		for (int i = 0; i < Math.min(100, relationships.size()); i++) {
			System.out.println(GraphPrettyPrinter.toString(relationships.get(i)));
		}
	}

}
