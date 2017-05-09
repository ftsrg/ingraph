package ingraph.debugger.backend.managers;

import com.google.common.collect.ImmutableMap;
import ingraph.driver.data.IngraphDeltaHandler;
import ingraph.driver.data.IngraphQueryHandler;
import ingraph.driver.ingraph.IngraphDriver;
import ingraph.driver.ingraph.IngraphSession;
import org.glassfish.jersey.media.sse.EventOutput;
import org.neo4j.driver.v1.Record;
import org.supercsv.prefs.CsvPreference;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;
import scala.collection.immutable.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class DriverManager {

	private static final String PREFIX = "../graphs/snb_50/";
	private static final String POSTFIX = "_0_0.csv";

	final Map<String, List<String>> nodeFilenames = ImmutableMap.<String, List<String>>builder() //
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

	public class DeltaHandler extends IngraphDeltaHandler {
		private final Vector<String> keys;
		private final EventOutput eventOutput;

		public DeltaHandler(EventOutput eventOutput, Vector<String> keys) {
			this.eventOutput = eventOutput;
			this.keys = keys;
		}

		@Override
		public Vector<String> keys() {
			return keys;
		}

		@Override
		public void onChange(List<? extends Record> positiveRecords, List<? extends Record> negativeRecords) {
			System.out.println(positiveRecords.size() + " - " + negativeRecords.size());
		}
	}

	private IngraphSession session;

	private Map<UUID, IngraphQueryHandler> queryHandlerMap = new HashMap<>();

	public DriverManager() {
		IngraphDriver driver = new IngraphDriver();
		this.session = driver.session();
	}

	public UUID register(String definition) {
		UUID id = UUID.randomUUID();
		IngraphQueryHandler handler = session.registerQuery(id.toString(), definition);

		final CsvPreference csvPreference = new CsvPreference.Builder('"', '|', "\n").build();
		handler.readCsv(nodeFilenames, relationshipFilenames, csvPreference);

		queryHandlerMap.put(id, handler);
		//Iterable<IndexedSeq<Object>> results = handler.adapter().engine().getResults();
		//System.out.println(results);
		return id;
	}

	public List<String> getKeys(UUID id) {
		return queryHandlerMap.get(id).adapter().resultNamesJava();
	}

	public void registerHandler(UUID id, EventOutput eventOutput) {
		DeltaHandler handler = new DeltaHandler(eventOutput, queryHandlerMap.get(id).adapter().resultNames());
		queryHandlerMap.get(id).registerDeltaHandler(handler);
	}

}
