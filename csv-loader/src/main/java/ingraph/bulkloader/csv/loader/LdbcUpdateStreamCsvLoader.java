package ingraph.bulkloader.csv.loader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ObjectArrays;
import ingraph.bulkloader.csv.data.IdSpaces;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LdbcUpdateStreamCsvLoader {

	private final List<Node> nodes = new ArrayList<>();
	private final List<Relationship> relationships = new ArrayList<>();
	private final IdSpaces idSpaces = new IdSpaces();

	public LdbcUpdateStreamCsvLoader(List<String> csvFilenames, CsvPreference csvPreference) throws IOException {
		loadUpdates(csvFilenames, csvPreference);
	}

	final CellProcessor[] UPDATE_COMMON = new CellProcessor[] {
		new NotNull(), // 1 scheduled start time
		new NotNull(), // 2 dependency time
		new NotNull(), // 3 event type (1..8)
	};

	final CellProcessor[] UPDATE_1 = new CellProcessor[] {
		new NotNull(),  // 4 person.id
		new NotNull(),  // 5 person.firstName
		new NotNull(),  // 6 person.lastName
		new NotNull(),  // 7 person.gender
		new NotNull(),  // 8 person.birthDay
		new NotNull(),  // 9 person.creationDate
		new NotNull(),  // 10 person.locationIp
		new NotNull(),  // 11 person.browserUsed
		new NotNull(),  // 12 person-isLocatedIn->City.id
		new NotNull(),  // 13 person.speaks
		new NotNull(),  // 14 person.email
		new NotNull(),  // 15 person-hasInterest->Tag.id
		new Optional(), // 16 {person-studyAt->University.id, Person-studyAt.classYear->University}
		new Optional(), // 17 {person-workAt->Company.id, Person-workAt.workFrom->Company}
	};

	final CellProcessor[] UPDATE_2 = new CellProcessor[] {
		new NotNull(), // 4 person.id
		new NotNull(), // 5 post.id
		new NotNull(), // 6 person-likes.creationDate->post
	};

	final CellProcessor[] UPDATE_3 = new CellProcessor[] {
		new NotNull(), // 4 person.id
		new NotNull(), // 5 comment.id
		new NotNull(), // 6 person-likes.creationDate->comment
	};

	final CellProcessor[] UPDATE_4 = new CellProcessor[] {
		new NotNull(), // 4 forum.id
		new NotNull(), // 5 forum.title
		new NotNull(), // 6 forum.creationDate
		new NotNull(), // 7 forum-hasModerator->Person.id
		new NotNull(), // 8 forum-hasTag->Tag.id
	};

	final CellProcessor[] UPDATE_5 = new CellProcessor[] {
		new NotNull(), // 4 person.id
		new NotNull(), // 5 Forum-hasMember->person.id
		new NotNull(), // 6 Forum-hasMember.joinDate->person
	};

	final CellProcessor[] UPDATE_6 = new CellProcessor[] {
		new NotNull(),  // 4 post.id
		new Optional(), // 5 post.imageFile
		new NotNull(),  // 6 post.creationDate
		new NotNull(),  // 7 post.locationIp
		new NotNull(),  // 8 post.browserUsed
		new Optional(), // 9 post.language
		new Optional(), // 10 post.content
		new NotNull(),  // 11 post.length
		new NotNull(),  // 12 post-hasCreator->Person.id
		new NotNull(),  // 13 post<-containerOf-Forum.id
		new NotNull(),  // 14 post-isLocatedIn->Country.id
		new Optional(), // 15 {post-hasTag->Tag.id}
	};

	final CellProcessor[] UPDATE_7 = new CellProcessor[] {
		new NotNull(),  // 4 comment.id
		new NotNull(),  // 5 comment.creationDate
		new NotNull(),  // 6 comment.locationIp
		new NotNull(),  // 7 comment.browserUsed
		new NotNull(),  // 8 comment.content
		new NotNull(),  // 9 comment.length
		new NotNull(),  // 10 comment-hasCreator->Person.id
		new NotNull(),  // 11 comment-isLocatedIn->Country.id
		new NotNull(),  // 12 comment-replyOf->Post.id
		new NotNull(),  // 13 comment-replyOf->Comment.id
		new Optional(), // 14 {comment-hasTag->Tag.id}
	};

	final CellProcessor[] UPDATE_8 = new CellProcessor[] {
		new NotNull(), // 1 person1.id
		new NotNull(), // 2 person2.id
		new NotNull(), // 3 knows.creationDate
	};

	final List<CellProcessor[]> UPDATES =
		ImmutableList.of(UPDATE_1, UPDATE_2, UPDATE_3, UPDATE_4, UPDATE_5, UPDATE_6, UPDATE_7, UPDATE_8);

	final Map<Integer, CellProcessor[]> UPDATE_PROCESSORS =
		IntStream
			.range(0, UPDATES.size())
			.boxed()
			.collect(Collectors.toMap(
				i -> i + 1, // according to the LDBC specification, indexing of updates starts from 1
				i -> ObjectArrays.concat(UPDATE_COMMON, UPDATES.get(i), CellProcessor.class)
			));

	private void loadUpdates(List<String> csvFilenames, CsvPreference csvPreference) throws IOException {
		for (String csvFilename: csvFilenames) {
			System.out.println(csvFilename);

			ICsvListReader listReader = null;
			try {
				listReader = new CsvListReader(new FileReader(csvFilename), csvPreference);

				while( (listReader.read()) != null ) {
					int updateType = Integer.valueOf(listReader.get(3));
					final CellProcessor[] cellProcessor = UPDATE_PROCESSORS.get(updateType);
					final List<Object> update = listReader.executeProcessors(cellProcessor);
					System.out.println(update);
				}
			} finally {
				if( listReader != null ) {
					listReader.close();
				}
			}
		}
	}

}
