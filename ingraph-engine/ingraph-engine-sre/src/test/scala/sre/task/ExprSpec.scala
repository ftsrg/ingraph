package sre.task

import org.scalatest.FunSpec
import sre.task.Core._
import sre.task.Expressions.OrderingAssert.CantCompare
import sre.task.Expressions._

import scalaz.{-\/, \/-}

class ExprSpec extends FunSpec {
  case class TestElement(properties: Map[String, AnyRef]) extends ElementT

  it("Expressions should work") {
    assert(IsNull(Null)() == \/-(Some(true)))
    assert(IsNull(Const(new java.lang.Float(42)))() == \/-(Some(false)))

    def testContext(e: (String, AnyRef)*) = Context(TestElement(Map(e : _*)), Map())

    assert(IsNull(This.Property("name"))
      (testContext("name" -> "John Doe")) == \/-(Some(false)))

    assert(IsNull(This.Property("name"))(testContext()) == \/-(Some(true)))


    assert(Eq(Null, Null)() == \/-(None))
    assert(Eq(Null, False)() == \/-(None))
    assert(Eq(Const(new Integer(42)), Null)() == \/-(None))
    assert(Eq(Const("John Doe"), This.Property("name"))(testContext("name" -> "John Doe")) == \/-(Some(true)))

    assert(
      Gt(
        Const(new Integer(42)),
        This.Property("age"))
      (testContext("age" -> new Integer(5))) == \/-(Some(true))
    )


    assert(
      Lte(Const("Oups!"), Const(new Integer(42)))() match {
        case -\/(exc: CantCompare) => true
        case _ => false
      }
    )
  }
}
