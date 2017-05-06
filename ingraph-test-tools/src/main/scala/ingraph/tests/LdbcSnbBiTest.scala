package ingraph.tests

import scala.Vector
import scala.io.Source
import org.scalatest.FunSuite
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder

abstract class LdbcSnbBiTest extends FunSuite {

  def modelPath(entityName: String) = s"../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(query: Int): String = s"../queries/ldbc-snb-bi/query-$query.cypher"

  def queryResultPath(query: Int): String = queryPath(query).dropRight("cypher".length) + "bin"

  val nodeFilenames: Map[String, List[String]] = Map(
    modelPath("comment") -> List("Message", "Comment"),
    modelPath("forum") -> List("Forum"),
    modelPath("organisation") -> List("Company", "University"),
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
    modelPath("tag_hasType_tagclass") -> "hasType",
    modelPath("organisation_isLocatedIn_place") -> "isLocatedIn",
    modelPath("person_studyAt_organisation") -> "studyAt",
    modelPath("person_workAt_organisation") -> "workAt"
  )

  val converterConfig = new RelalgConverterConfigBuilder() //
    .setConsoleOutput(false) //
    .setStandaloneDocument(true) //
    .setIncludeCommonVariables(true) //
    .setSchemaIndices(true) //
    .build
  val converter = new Relalg2TexTreeConverter(converterConfig)

  case class TestCase(number: Int)

  Vector(
//        TestCase(1), // CASE
        TestCase(2),
        TestCase(3),
        TestCase(4),
        TestCase(5),
        TestCase(6),
        TestCase(7),
//        TestCase(8), // PATH
        TestCase(9),
//        TestCase(10), // CASE
//        TestCase(11), // unwind parameter
        TestCase(12),
        TestCase(13),
//        TestCase(14), // PATH
        TestCase(15),
//        TestCase(16), // PATH
//        TestCase(17), // no Cypher implementation yet
//        TestCase(18), // no Cypher implementation yet
//        TestCase(19), // antijoin
//        TestCase(20), // PATH
//        TestCase(21), // no Cypher implementation yet
        //TestCase(22), // no Cypher implementation yet
        //TestCase(23), // no Cypher implementation yet
        TestCase(24),
//        TestCase(25), // no cypher implementation yet
        
        null
  ).filter(_ != null) //
    .foreach(
    t => test(s"query-${t.number}-size-1") {
      val queryNumber = t.number
      val queryName = s"ldbc-snb-bi-${t.number}"
      val querySpecification = Source.fromFile(queryPath(queryNumber)).getLines().mkString("\n")

      runQuery(queryNumber, queryName, querySpecification)
    })
    
  def runQuery(queryNumber : Int, queryName : String, querySpecification : String)
}
