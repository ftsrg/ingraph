package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source
import org.supercsv.prefs.CsvPreference

class LdbcSnbBiTest extends FlatSpec {

  def modelPath(size: Int, entityName: String) = s"../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(query: Int): String = s"../queries/ldbc-snb-bi/query-$query.cypher"

  case class TestCase(number: Int, size: Int, expectedResultSize: Int)

  Vector(
//    TestCase(3, 1, 0),
    TestCase(4, 4, 0),
    TestCase(5, 21, 0),
//    TestCase(6, 3, 0),
    TestCase(7, 26, 0),
    TestCase(8, 65, 0),
//    TestCase(9, 1, 0),

//    TestCase(12, 30, 0),
//    TestCase(13, 5, 0),
    TestCase(14, 28, 0),
//    TestCase(15, 2, 0),
    TestCase(16, 99, 0),
    TestCase(20, 15, 0),
    TestCase(23, 100, 0),
    TestCase(24, 3, 0)) //
    .foreach(
      t => s"query-${t.number}-size-${t.size}" should "work" in {
        val query = Source.fromFile(queryPath(t.number)).getLines().mkString(" ")
        val adapter = new IngraphAdapter(query)
        val tf = new TransactionFactory(16)
        tf.subscribe(adapter.engine.inputLookup)
        val tran = tf.newBatchTransaction()

        val nodeFilenames: Map[String, List[String]] = Map(
          modelPath(t.size, "comment") -> List("Message", "Comment"),
          modelPath(t.size, "forum") -> List("Forum"),
          modelPath(t.size, "person") -> List("Person"),
          modelPath(t.size, "place") -> List("Place"),
          modelPath(t.size, "post") -> List("Message", "Post"),
          modelPath(t.size, "tagclass") -> List("TagClass"),
          modelPath(t.size, "tag") -> List("Tag"))

        val relationshipFilenames: Map[String, String] = Map(
          modelPath(t.size, "comment_hasCreator_person") -> "hasCreator",
          modelPath(t.size, "comment_isLocatedIn_place") -> "isLocatedIn",
          modelPath(t.size, "comment_replyOf_comment") -> "replyOf",
          modelPath(t.size, "comment_replyOf_post") -> "replyOf",
          modelPath(t.size, "forum_containerOf_post") -> "containerOf",
          modelPath(t.size, "forum_hasMember_person") -> "hasMember",
          modelPath(t.size, "forum_hasModerator_person") -> "hasModerator",
          modelPath(t.size, "forum_hasTag_tag") -> "hasTag",
          modelPath(t.size, "person_hasInterest_tag") -> "hasInterest",
          modelPath(t.size, "person_isLocatedIn_place") -> "isLocatedIn",
          modelPath(t.size, "person_knows_person") -> "knows",
          modelPath(t.size, "person_likes_comment") -> "likes",
          modelPath(t.size, "person_likes_post") -> "likes",
          modelPath(t.size, "place_isPartOf_place") -> "isPartOf",
          modelPath(t.size, "post_hasCreator_person") -> "hasCreator",
          modelPath(t.size, "comment_hasTag_tag") -> "hasTag",
          modelPath(t.size, "post_hasTag_tag") -> "hasTag",
          modelPath(t.size, "post_isLocatedIn_place") -> "isLocatedIn",
          modelPath(t.size, "tagclass_isSubclassOf_tagclass") -> "isSubclassOf",
          modelPath(t.size, "tag_hasType_tagclass") -> "hasType"
        )

        val csvPreference = new CsvPreference.Builder('"', '|', "\n").build();
        adapter.readCsv(nodeFilenames, relationshipFilenames, tran, csvPreference)
        tran.close()
        val results = adapter.engine.getResults().size
        assert(results == t.expectedResultSize)
      })
}
