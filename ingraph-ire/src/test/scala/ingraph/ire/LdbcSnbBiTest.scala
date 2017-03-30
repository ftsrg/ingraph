package ingraph.ire

import java.io.FileInputStream

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import hu.bme.mit.ire.TransactionFactory
import org.scalatest.FunSuite
import org.supercsv.prefs.CsvPreference
import relalg._

import scala.io.Source

class LdbcSnbBiTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(query: Int): String = s"../queries/ldbc-snb-bi/query-$query.cypher"

  def queryResultPath(query: Int): String = queryPath(query).dropRight("cypher".length) + "bin"

  case class TestCase(number: Int, expectedResultSize: Int, dummy: Int)

  Vector(
//        TestCase(3, 1, 0),
        TestCase(4, 4, 0),
//        TestCase(5, 21, 0), // maybe alldifferent
//        TestCase(6, 3, 0),
//        TestCase(7, 26, 0), // maybe alldifferent
//        TestCase(8, 65, 0), // PATH
//        TestCase(9, 1, 0), // WHERE WITH

//        TestCase(12, 30, 0), // WHERE WITH
//        TestCase(13, 5, 0),
//        TestCase(14, 28, 0), // PATH
//        TestCase(15, 2, 0), // WHERE WITH
//        TestCase(16, 99, 0), // PATH
//        TestCase(20, 15, 0), // PATH
        TestCase(23, 100, 0),
        TestCase(24, 3, 0)
  ) //
    .foreach(
    t => test(s"query-${t.number}-size-1") {
      val query = Source.fromFile(queryPath(t.number)).getLines().mkString("\n")
      val adapter = new IngraphAdapter(query)
      val tf = new TransactionFactory(16)
      tf.subscribe(adapter.engine.inputLookup)
      val tran = tf.newBatchTransaction()

      val nodeFilenames: Map[String, List[String]] = Map(
        modelPath("comment") -> List("Message", "Comment"),
        modelPath("forum") -> List("Forum"),
        modelPath("person") -> List("Person"),
        modelPath("place") -> List("Place"),
        modelPath("post") -> List("Message", "Post"),
        modelPath("tagclass") -> List("TagClass"),
        modelPath("tag") -> List("Tag"))

      val relationshipFilenames: Map[String, String] = Map(
        modelPath("comment_hasCreator_person") -> "hasCreator",
        modelPath("comment_isLocatedIn_place") -> "isLocatedIn",
        modelPath("comment_replyOf_comment") -> "replyOf",
        modelPath("comment_replyOf_post") -> "replyOf",
        modelPath("forum_containerOf_post") -> "containerOf",
        modelPath("forum_hasMember_person") -> "hasMember",
        modelPath("forum_hasModerator_person") -> "hasModerator",
        modelPath("forum_hasTag_tag") -> "hasTag",
        modelPath("person_hasInterest_tag") -> "hasInterest",
        modelPath("person_isLocatedIn_place") -> "isLocatedIn",
        modelPath("person_knows_person") -> "knows",
        modelPath("person_likes_comment") -> "likes",
        modelPath("person_likes_post") -> "likes",
        modelPath("place_isPartOf_place") -> "isPartOf",
        modelPath("post_hasCreator_person") -> "hasCreator",
        modelPath("comment_hasTag_tag") -> "hasTag",
        modelPath("post_hasTag_tag") -> "hasTag",
        modelPath("post_isLocatedIn_place") -> "isLocatedIn",
        modelPath("tagclass_isSubclassOf_tagclass") -> "isSubclassOf",
        modelPath("tag_hasType_tagclass") -> "hasType"
      )

      val csvPreference = new CsvPreference.Builder('"', '|', "\n").build()
      adapter.readCsv(nodeFilenames, relationshipFilenames, tran, csvPreference)
      tran.close()
      import ingraph.expressionparser.Conversions._
      val resultNames = adapter.plan.getRootExpression.getBasicSchema.map {
        case a: AttributeVariable => s"${a.getElement.getName}.${a.getName}"
        case e => e.getName
      }
      println(resultNames)
      val actualResults = adapter.engine.getResults()

      val javaResults = new Kryo().readClassAndObject(new Input(new FileInputStream(queryResultPath(t.number))))
        .asInstanceOf[java.util.ArrayList[java.util.Map[String, Any]]]
      import scala.collection.JavaConverters._
      val expectedResults = javaResults.asScala.map(f => resultNames.map(f.get))
      expectedResults.foreach(n => assert(n != null))

      assert(expectedResults.size == actualResults.size)
      for ((expected, actual) <- expectedResults.zip(actualResults.toVector)) {
        println(actual, expected)
        assert(expected == actual)
      }
    })
}
