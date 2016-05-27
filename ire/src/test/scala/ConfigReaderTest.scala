import java.io.ByteArrayInputStream

import hu.bme.mit.incqueryds.{ChangeSet, ConfigReader}
import org.scalatest.FlatSpec

import scala.io.Source

/**
  * Created by wafle on 5/23/2016.
  */
class ConfigReaderTest  extends FlatSpec {
  "alpha nodes configs should work" should "work" in {
    val config = """nodes:
      |  - f1:
      |      type: Checker
      |      condition: "(n) => { n(0) == 5L }"
      |      next: production
      |input:
      |  test: [f1]
    """.stripMargin
    val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")));
    val testInput = engine.inputLookup("test")
    testInput(ChangeSet(positive = Vector(Vector(0L), Vector(5L))))
    assert(engine.getResults() == Set(Vector(5L)))
  }

  "beta nodes configs" should "work" in {
    val config = """nodes:
                   |  - j1:
                   |      type: HashJoiner
                   |      primaryLength: 1
                   |      primarySelector: Vector(0)
                   |      secondaryLength: 1
                   |      secondarySelector: Vector(0)
                   |      next: production
                   |input:
                   |  pri: [j1.primary]
                   |  sec: [j1.secondary]
                 """.stripMargin
    val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")));
    val primaryInput = engine.inputLookup("pri")
    val secondaryInput = engine.inputLookup("sec")
    primaryInput(ChangeSet(positive = Vector(Vector(0L), Vector(5L))))
    secondaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
    assert(engine.getResults() == Set(Vector(5L)))
  }
  "multi node configs" should "work" in {
    val config = """input:
                   |  fIn: [f1]
                   |  sec: [j1.secondary]
                   |
                   |nodes:
                   |  - f1:
                   |      type: Checker
                   |      condition: "(n) => { n(0) == 5L }"
                   |      next: j1.primary
                   |  - j1:
                   |      type: HashJoiner
                   |      primaryLength: 1
                   |      primarySelector: Vector(0)
                   |      secondaryLength: 1
                   |      secondarySelector: Vector(0)
                   |      next: production
                 """.stripMargin
    val engine = ConfigReader.parse("testQuery", new ByteArrayInputStream(config.getBytes("UTF-8")));
    val primaryInput = engine.inputLookup("fIn")
    val secondaryInput = engine.inputLookup("sec")
    primaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
    secondaryInput(ChangeSet(positive = Vector(Vector(1L), Vector(5L))))
    assert(engine.getResults() == Set(Vector(5L)))
  }
}
