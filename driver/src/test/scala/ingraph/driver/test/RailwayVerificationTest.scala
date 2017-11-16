package ingraph.driver.test


//class RailwayVerificationTest extends FunSuite {
//
//  def modelPath(entityName: String) = s"../../graphs/railway-verification/${entityName}.csv"
//
//  def queryPath(query: Int): String = s"../../queries/railway-verification/railway-$query.cypher"
//
//  val nodeFilenames: Map[String, List[String]] = Map(
//    modelPath("segment") -> List("Segment"),
//    modelPath("switch") -> List("Switch"),
//    modelPath("train") -> List("Train")
//  )
//
//  val relationshipFilenames: Map[String, String] = Map(
//    modelPath("on") -> "ON",
//    modelPath("next") -> "NEXT",
//    modelPath("straight") -> "STRAIGHT",
//    modelPath("top") -> "TOP",
//    modelPath("diverging") -> "DIVERGING"
//  )
//
//  val converterConfig = new RelalgConverterConfigBuilder() //
//    .setConsoleOutput(false) //
//    .setStandaloneDocument(true) //
//    .setIncludeCommonVariables(true) //
//    .setSchemaIndices(true) //
//    .build
//  val converter = new Relalg2TexTreeConverter(converterConfig)
//
//  case class TestCase(number: Int)
//
//  Vector(
//    TestCase(1),
//    TestCase(2),
//    null
//  ).filter(_ != null) //
//    .foreach(
//    t => test(s"railway-${t.number}-size-1") {
//      val queryNumber = t.number
//      val queryName = s"railway-${t.number}"
//      val querySpecification = Source.fromFile(queryPath(queryNumber)).getLines().mkString("\n")
//
//      runQuery(queryNumber, queryName, querySpecification)
//    })
//
//  def runQuery(queryNumber : Int, queryName : String, querySpecification : String) {
//    val driver = new IngraphDriver()
//    val session = driver.session()
//
//    val queryHandler = session.registerQuery(queryName, querySpecification)
//    var actualResults: util.List[_ <: Record] = null
//    class AssertionHandler(override val keys: Vector[String]) extends IngraphDeltaHandler {
//      override def onChange(positiveRecords: util.List[_ <: Record], negativeRecords: util.List[_ <: Record]): Unit = {
//        actualResults = positiveRecords
//      }
//    }
//
////    val kryo = new Kryo
////    kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()))
////    kryo.addDefaultSerializer(
////        Collections.unmodifiableCollection( Collections.EMPTY_LIST ).getClass,
////        classOf[UnmodifiableCollectionsSerializer])
//
//    import scala.collection.JavaConverters._
//    queryHandler.registerDeltaHandler(new AssertionHandler(queryHandler.adapter.resultNames()))
//    queryHandler.readCsv(nodeFilenames.mapValues(_.asJava).asJava, relationshipFilenames.asJava, CsvPreference.STANDARD_PREFERENCE)
//
//    println(queryName + ": " +  actualResults)
//
////    val expectedResults = kryo.readClassAndObject(new Input(new FileInputStream(queryResultPath(queryNumber))))
////      .asInstanceOf[java.util.ArrayList[Record]]
////    assertResult(expectedResults.size)(actualResults.size)
////    for ((expected, actual) <- expectedResults.asScala.zip(actualResults.asScala.toVector)) {
////      assertResult(expected.asMap())(actual.asMap())
////    }
//  }
//
//}
