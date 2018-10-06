package ingraph.bulkloader.csv.loader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ObjectArrays;
import ingraph.bulkloader.csv.loader.cellprocessor.ParseEpochToDate;
import ingraph.bulkloader.csv.loader.cellprocessor.ParseEpochToDateTime;
import ingraph.bulkloader.csv.loader.cellprocessor.ParseList;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LdbcUpdateStreamCsvLoader {

	// make sure to load persons first
	public static final List<String> CSV_FILENAMES = ImmutableList.of("person", "forum");
	public static final String PREFIX = "updateStream_0_0_";
	public static final String POSTFIX = ".csv";
	public static final CsvPreference LDBC_CSV_PREFERENCE =
		new CsvPreference.Builder('"', '|', "\n").build();

	final List<List<Object>> updates = new ArrayList<>();

	public LdbcUpdateStreamCsvLoader(String csvDir) throws IOException {
		loadUpdates(csvDir);
	}

	final CellProcessor[] UPDATE_COMMON = new CellProcessor[]{
		new ParseLong(),                    //  1 scheduled start time
		new ParseLong(),                    //  2 dependency time
		new ParseInt(),                     //  3 event type (1..8)
	};

	final CellProcessor[] UPDATE_1 = new CellProcessor[]{
		new ParseLong(),                    //  4 person.id
		new NotNull(),                      //  5 person.firstName
		new NotNull(),                      //  6 person.lastName
		new NotNull(),                      //  7 person.gender
		new ParseEpochToDate(),             //  8 person.birthDay
		new ParseEpochToDateTime(),         //  9 person.creationDate
		new NotNull(),                      // 10 person.locationIP
		new NotNull(),                      // 11 person.browserUsed
		new ParseLong(),                    // 12 person-isLocatedIn->City.id
		new ParseList(Function.identity()), // 13 {person.speaks}
		new ParseList(Function.identity()), // 14 {person.email}
		new ParseList(Long::valueOf),       // 15 person-hasInterest->Tag.id
		new Optional(),                     // 16 {person-studyAt->University.id, Person-studyAt.classYear->University}
		new Optional(),                     // 17 {person-workAt->Company.id, Person-workAt.workFrom->Company}
	};

	final CellProcessor[] UPDATE_2 = new CellProcessor[]{
		new ParseLong(),                    //  4 person.id
		new ParseLong(),                    //  5 post.id
		new ParseEpochToDateTime(),         //  6 person-likes.creationDate->post
	};

	final CellProcessor[] UPDATE_3 = new CellProcessor[]{
		new ParseLong(),                    //  4 person.id
		new ParseLong(),                    //  5 comment.id
		new ParseEpochToDateTime(),         //  6 person-likes.creationDate->comment
	};

	final CellProcessor[] UPDATE_4 = new CellProcessor[]{
		new ParseLong(),                    //  4 forum.id
		new NotNull(),                      //  5 forum.title
		new ParseEpochToDateTime(),         //  6 forum.creationDate
		new ParseLong(),                    //  7 forum-hasModerator->Person.id
		new ParseList(Long::valueOf),       //  8 forum-hasTag->Tag.id
	};

	final CellProcessor[] UPDATE_5 = new CellProcessor[]{
		new ParseLong(),                    //  4 person.id
		new ParseLong(),                    //  5 Forum-hasMember->person.id
		new ParseEpochToDateTime(),         //  6 Forum-hasMember.joinDate->person
	};

	final CellProcessor[] UPDATE_6 = new CellProcessor[]{
		new ParseLong(),                    //  4 post.id
		new Optional(),                     //  5 post.imageFile
		new ParseEpochToDateTime(),         //  6 post.creationDate
		new NotNull(),                      //  7 post.locationIP
		new NotNull(),                      //  8 post.browserUsed
		new Optional(),                     //  9 post.language
		new Optional(),                     // 10 post.content
		new ParseInt(),                     // 11 post.length
		new ParseLong(),                    // 12 post-hasCreator->Person.id
		new ParseLong(),                    // 13 post<-containerOf-Forum.id
		new ParseLong(),                    // 14 post-isLocatedIn->Country.id
		new ParseList(Long::valueOf),       // 15 {post-hasTag->Tag.id}
	};

	final CellProcessor[] UPDATE_7 = new CellProcessor[]{
		new ParseLong(),                    //  4 comment.id
		new ParseEpochToDateTime(),         //  5 comment.creationDate
		new NotNull(),                      //  6 comment.locationIP
		new NotNull(),                      //  7 comment.browserUsed
		new NotNull(),                      //  8 comment.content
		new ParseInt(),                     //  9 comment.length
		new ParseLong(),                    // 10 comment-hasCreator->Person.id
		new ParseLong(),                    // 11 comment-isLocatedIn->Country.id
		new ParseLong(),                    // 12 comment-replyOf->Post.id
		new ParseLong(),                    // 13 comment-replyOf->Comment.id
		new ParseList(Long::valueOf),       // 14 {comment-hasTag->Tag.id}
	};

	final CellProcessor[] UPDATE_8 = new CellProcessor[]{
		new ParseLong(),                    //  4 person1.id
		new ParseLong(),                    //  5 person2.id
		new ParseEpochToDateTime(),         //  6 knows.creationDate
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

	private void loadUpdates(final String csvDir) throws IOException {
		for (final String csvFilename : CSV_FILENAMES) {
			final String csvPath = csvDir + "/" + PREFIX + csvFilename + POSTFIX;

			ICsvListReader listReader = null;
			try {
				listReader = new CsvListReader(new FileReader(csvPath), LDBC_CSV_PREFERENCE);

				while ((listReader.read()) != null) {
					int eventType = Integer.valueOf(listReader.get(3));
					final CellProcessor[] cellProcessor = UPDATE_PROCESSORS.get(eventType);
					final List<Object> update = listReader.executeProcessors(cellProcessor);
					updates.add(update);
				}
			} finally {
				if (listReader != null) {
					listReader.close();
				}
			}
		}
	}

	public List<List<Object>> getUpdates() {
		return updates;
	}

}
