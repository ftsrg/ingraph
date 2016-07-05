import java.io.ByteArrayInputStream

import hu.bme.mit.incqueryds.{ChangeSet, ConfigReader}
import org.scalatest.FlatSpec

/**
  * Created by wafle on 5/23/2016.
  */
class ConfigReaderTest  extends FlatSpec {
  "alpha nodes configs should work" should "work" in {
  val config = """nodes:
           |  - f1:
           |    type: Checker
           |    condition: "(n) => { n(0) == 5L }"
           |    next: [production]
           |input:
           |  edges:
           |  test: [f1]
         """.stripMargin
  val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  val testInput = engine.inputLookup("test")
  testInput(ChangeSet(positive = Vector(Vector(0L), Vector(5L))))
  assert(engine.getResults() == Set(Vector(5L)))
  }

  "beta nodes configs" should "work" in {
  val config = """nodes:
           |  - j1:
           |    type: HashJoiner
           |    primaryLength: 1
           |    primarySelector: Vector(0)
           |    secondaryLength: 1
           |    secondarySelector: Vector(0)
           |    next: [production]
           |input:
           |  edges:
           |  pri: [j1.primary]
           |  sec: [j1.secondary]
         """.stripMargin
  val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  val primaryInput = engine.inputLookup("pri")
  val secondaryInput = engine.inputLookup("sec")
  primaryInput(ChangeSet(positive = Vector(Vector(0L), Vector(5L))))
  secondaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
  assert(engine.getResults() == Set(Vector(5L)))
  }

  "type passing" should "work" in {
  val config = """input:
           |  types:
           |  fIn: [f1]
           |
           |nodes:
           |  - f1:
           |    type: Checker
           |    condition: "(n) => { true }"
           |    next: [production]
         """.stripMargin
  val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  val input = engine.inputLookup("type")
  input(ChangeSet(positive = Vector(Vector(1L, "fIn"), Vector(2L, "fIn"), Vector(3L, "fNotIn"))))
  assert(engine.getResults() == Set(Vector(1L), Vector(2L)))
  }

  "multi node configs" should "work" in {
  val config = """input:
           |  edges:
           |  fIn: [f1]
           |  sec: [j1.secondary]
           |
           |nodes:
           |  - f1:
           |    type: Checker
           |    condition: "(n) => { n(0) == 5L }"
           |    next: [j1.primary]
           |  - j1:
           |    type: HashJoiner
           |    primaryLength: 1
           |    primarySelector: Vector(0)
           |    secondaryLength: 1
           |    secondarySelector: Vector(0)
           |    next: [production]
         """.stripMargin
  val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  val primaryInput = engine.inputLookup("fIn")
  val secondaryInput = engine.inputLookup("sec")
  primaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
  secondaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
  assert(engine.getResults() == Set(Vector(5L)))
  }
  "unused nodes" should "throw runtime exception when never used" in {
  val config = """input:
           |  edges:
           |  fIn: []
           |  sec: [j1.secondary]
           |
           |nodes:
           |  - f1:
           |    type: Checker
           |    condition: "(n) => { n(0) == 5L }"
           |    next: [j1.primary]
           |  - j1:
           |    type: HashJoiner
           |    primaryLength: 1
           |    primarySelector: Vector(0)
           |    secondaryLength: 1
           |    secondarySelector: Vector(0)
           |    next: [production]
         """.stripMargin
  intercept[RuntimeException] {
    val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  }
  }
  they should "throw runtime exception when a (primary xor secondary) input is used" in {
  val config = """input:
           |  edges:
           |  fIn: [f1]
           |  sec: [j1.secondary]
           |
           |nodes:
           |  - f1:
           |    type: Checker
           |    condition: "(n) => { n(0) == 5L }"
           |    next: [f1]
           |  - j1:
           |    type: HashJoiner
           |    primaryLength: 1
           |    primarySelector: Vector(0)
           |    secondaryLength: 1
           |    secondarySelector: Vector(0)
           |    next: [production]
         """.stripMargin
  intercept[RuntimeException] {
    val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")))
  }
  }
}
