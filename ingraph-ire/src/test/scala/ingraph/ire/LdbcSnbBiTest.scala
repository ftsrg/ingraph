package ingraph.ire

import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FlatSpec

import scala.io.Source

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
          modelPath(t.size, "hasCreator") -> "comment_hasCreator_person",
          modelPath(t.size, "isLocatedIn") -> "comment_isLocatedIn_place",
          modelPath(t.size, "replyOf") -> "comment_replyOf_comment",
          modelPath(t.size, "replyOf") -> "comment_replyOf_post",
          modelPath(t.size, "containerOf") -> "forum_containerOf_post",
          modelPath(t.size, "hasMember") -> "forum_hasMember_person",
          modelPath(t.size, "hasModerator") -> "forum_hasModerator_person",
          modelPath(t.size, "hasTag") -> "forum_hasTag_tag",
          modelPath(t.size, "hasInterest") -> "person_hasInterest_tag",
          modelPath(t.size, "isLocatedIn") -> "person_isLocatedIn_place",
          modelPath(t.size, "knows") -> "person_knows_person",
          modelPath(t.size, "likes") -> "person_likes_comment",
          modelPath(t.size, "likes") -> "person_likes_post",
          modelPath(t.size, "isPartOf") -> "place_isPartOf_place",
          modelPath(t.size, "hasCreator") -> "post_hasCreator_person",
          modelPath(t.size, "hasTag") -> "comment_hasTag_tag",
          modelPath(t.size, "hasTag") -> "post_hasTag_tag",
          modelPath(t.size, "isLocatedIn") -> "post_isLocatedIn_place",
          modelPath(t.size, "isSubclassOf") -> "tagclass_isSubclassOf_tagclass",
          modelPath(t.size, "hasType") -> "tag_hasType_tagclass")

        adapter.readCsv(nodeFilenames, relationshipFilenames, tran)
        tran.close()
        val results = adapter.engine.getResults().size
        assert(results == t.expectedResultSize)
      })
}
