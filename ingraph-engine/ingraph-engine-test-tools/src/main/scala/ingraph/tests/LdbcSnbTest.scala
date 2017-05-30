package ingraph.tests

import scala.Vector
import scala.io.Source
import org.scalatest.FunSuite
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder

abstract class LdbcSnbTest extends FunSuite {

  def modelPath(entityName: String) = s"../../graphs/snb_50/${entityName}_0_0.csv"

  def queryPath(workload: String, query: Int): String = s"../../queries/ldbc-snb-$workload/$workload-$query.cypher"

  def queryResultPath(workload: String, query: Int): String = queryPath(workload, query).dropRight("cypher".length) + "bin"

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

  case class TestCase(workload: String, number: Int)

  Vector(
        TestCase("bi", 2),
        TestCase("bi", 3),
        TestCase("bi", 4),
        TestCase("bi", 5),
        TestCase("bi", 6),
        TestCase("bi", 7),
        TestCase("bi", 9),
        TestCase("bi", 12),
        TestCase("bi", 13),
        TestCase("bi", 14), // PATH
        TestCase("bi", 15),
        TestCase("bi", 20), // PATH
        TestCase("bi", 24),

        // simple tests
        TestCase("simple", 1), // PATH
        TestCase("simple", 2), // PATH
        TestCase("simple", 3), // PATH
        TestCase("simple", 4), // PATH
//        TestCase("simple", 5), // PATH
//        TestCase("simple", 6), // PATH

//        TestCase("bi", 8), // PATH
//        TestCase("bi", 16), // PATH

//        TestCase("bi", 1), // CASE
//        TestCase("bi", 10), // CASE
//        TestCase("bi", 19), // antijoin

//        TestCase("bi", 11), // requires list comprehensions

//        TestCase("bi", 17), // no Cypher implementation yet
//        TestCase("bi", 18), // no Cypher implementation yet
//        TestCase("bi", 21), // no Cypher implementation yet
//        TestCase("bi", 22), // no Cypher implementation yet
//        TestCase("bi", 23), // no Cypher implementation yet
//        TestCase("bi", 25), // no cypher implementation yet

        
        null
  ).filter(_ != null) //
    .foreach(
    t =>
      test(s"${t.workload}-${t.number}-size-1") {
      
      val queryName = s"ldbc-snb-${t.workload}-${t.number}"
      val querySpecification = Source.fromFile(queryPath(t.workload, t.number)).getLines().mkString("\n")

      runQuery(t.workload, t.number, queryName, querySpecification)
    })

  def runQuery(workload: String, queryNumber: Int, queryName: String, querySpecification: String)
}
