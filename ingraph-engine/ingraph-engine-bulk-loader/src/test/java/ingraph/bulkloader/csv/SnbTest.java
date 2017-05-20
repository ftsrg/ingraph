package ingraph.bulkloader.csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.supercsv.prefs.CsvPreference;

import com.google.common.collect.ImmutableMap;

import ingraph.bulkloader.csv.loader.MassCsvLoader;
import neo4j.driver.util.GraphPrettyPrinter;

public class SnbTest {

	private static final String PREFIX = "../graphs/snb_50/";
	private static final String POSTFIX = "_0_0.csv";

	final Map<String, Collection<String>> nodeCsvs = ImmutableMap.<String, Collection<String>>builder() //
			.put("comment", Arrays.asList("Message", "Comment")) //
			.put("forum", Arrays.asList("Forum")) //
			.put("organisation", Arrays.asList("Company", "University")) //
			.put("person", Arrays.asList("Person")) //
			.put("place", Arrays.asList("Place")) //
			.put("post", Arrays.asList("Message", "Post")) //
			.put("tagclass", Arrays.asList("TagClass")) //
			.put("tag", Arrays.asList("Tag")) //
			.build();

	final Map<String, String> relationshipCsvs = ImmutableMap.<String, String>builder() //
			.put("comment_hasCreator_person", "hasCreator") //
			.put("comment_isLocatedIn_place", "isLocatedIn") //
			.put("comment_replyOf_comment", "replyOf") //
			.put("comment_replyOf_post", "replyOf") //
			.put("forum_containerOf_post", "containerOf") //
			.put("forum_hasMember_person", "hasMember") //
			.put("forum_hasModerator_person", "hasModerator") //
			.put("forum_hasTag_tag", "hasTag") //
			.put("person_hasInterest_tag", "hasInterest") //
			.put("person_isLocatedIn_place", "isLocatedIn") //
			.put("person_knows_person", "knows") //
			.put("person_likes_comment", "likes") //
			.put("person_likes_post", "likes") //
			.put("place_isPartOf_place", "isPartOf") //
			.put("post_hasCreator_person", "hasCreator") //
			.put("comment_hasTag_tag", "hasTag") //
			.put("post_hasTag_tag", "hasTag") //
			.put("post_isLocatedIn_place", "isLocatedIn") //
			.put("tagclass_isSubclassOf_tagclass", "isSubclassOf") //
			.put("tag_hasType_tagclass", "hasType") //
			.put("organisation_isLocatedIn_place", "isLocatedIn") //
			.put("person_studyAt_organisation", "studyAt") //
			.put("person_workAt_organisation", "workAt") //
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
		final CsvPreference csvPreference = new CsvPreference.Builder('"', '|', "\n").build();
		final MassCsvLoader mcl = new MassCsvLoader(nodeFilenames, relationshipFilenames, csvPreference);
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
