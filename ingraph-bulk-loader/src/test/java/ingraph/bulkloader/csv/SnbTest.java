package ingraph.bulkloader.csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

	final Map<String, Collection<String>> nodeFilenames = ImmutableMap.<String, Collection<String>>builder() //
			.put(PREFIX + "comment" + POSTFIX, Arrays.asList("Message", "Comment")) //
			.put(PREFIX + "forum" + POSTFIX, Arrays.asList("Forum")) //
			.put(PREFIX + "person" + POSTFIX, Arrays.asList("Person")) //
			.put(PREFIX + "place" + POSTFIX, Arrays.asList("Place")) //
			.put(PREFIX + "post" + POSTFIX, Arrays.asList("Message", "Post")) //
			.put(PREFIX + "tagclass" + POSTFIX, Arrays.asList("TagClass")) //
			.put(PREFIX + "tag" + POSTFIX, Arrays.asList("Tag")) //
			.build();

	final Map<String, String> relationshipFilenames = ImmutableMap.<String, String>builder() //
			.put(PREFIX + "comment_hasCreator_person" + POSTFIX, "hasCreator") //
			.put(PREFIX + "comment_isLocatedIn_place" + POSTFIX, "isLocatedIn") //
			.put(PREFIX + "comment_replyOf_comment" + POSTFIX, "replyOf") //
			.put(PREFIX + "comment_replyOf_post" + POSTFIX, "replyOf") //
			.put(PREFIX + "forum_containerOf_post" + POSTFIX, "containerOf") //
			.put(PREFIX + "forum_hasMember_person" + POSTFIX, "hasMember") //
			.put(PREFIX + "forum_hasModerator_person" + POSTFIX, "hasModerator") //
			.put(PREFIX + "forum_hasTag_tag" + POSTFIX, "hasTag") //
			.put(PREFIX + "person_hasInterest_tag" + POSTFIX, "hasInterest") //
			.put(PREFIX + "person_isLocatedIn_place" + POSTFIX, "isLocatedIn") //
			.put(PREFIX + "person_knows_person" + POSTFIX, "knows") //
			.put(PREFIX + "person_likes_comment" + POSTFIX, "likes") //
			.put(PREFIX + "person_likes_post" + POSTFIX, "likes") //
			.put(PREFIX + "place_isPartOf_place" + POSTFIX, "isPartOf") //
			.put(PREFIX + "post_hasCreator_person" + POSTFIX, "hasCreator") //
			.put(PREFIX + "comment_hasTag_tag" + POSTFIX, "hasTag") //
			.put(PREFIX + "post_hasTag_tag" + POSTFIX, "hasTag") //
			.put(PREFIX + "post_isLocatedIn_place" + POSTFIX, "isLocatedIn") //
			.put(PREFIX + "tagclass_isSubclassOf_tagclass" + POSTFIX, "isSubclassOf") //
			.put(PREFIX + "tag_hasType_tagclass" + POSTFIX, "hasType") //
			.build();

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
