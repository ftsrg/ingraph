package ingraph.sandbox

import ingraph.compiler.JPlanParser
import ingraph.compiler.qplan2jplan.{QPlanToJPlan, SchemaInferencer}
import ingraph.model._
import ingraph.model.expr.VertexAttribute
import ingraph.model.jplan.JNode
import ingraph.model.qplan.{GetVertices, Projection, Selection}
import org.apache.spark.sql.catalyst.expressions.{And, EqualTo, GreaterThan, Literal}
import org.scalatest.FunSuite

class MyTest extends FunSuite {

//  test("should work") {
//    val ingraphDir = "/home/szarnyasg/git/ingraph"
//
//    val plan = TexConverter.parse("SELECT hello.x2 FROM hello, world WHERE (hello.x2 = world.x2 AND world.y < 4) OR (hello.y > 3)")
//    val tex = TexConverter.toTex(plan)
//    TexConverter.toFile(tex, ingraphDir)
//    TexConverter.compile("x")
//  }

//  test("modify immutable data structures") {
//    val v1 = VertexAttribute("hello")
//    val v2 = VertexAttribute("world")
//
//    val e1 = GetEdges(v1, null, null, null)
//    val e2 = e1.copy(src = v2)
//
//    println(e1)
//    println(e2)
//  }
//
//  test("update tree") {
//    val x1 = UnresolvedRelation(null)
//    val x2 = UnresolvedRelation(null)
//
//    val gl = GlobalLimit(null, x1)
//    val t1 = Intersect(gl, x2)
//
//    val t2 = t1.transform {
//      case GlobalLimit(gl.limitExpr, gl.child) => LocalLimit(gl.limitExpr, gl.child)
//    }
//
//    println(t2)
//  }

  test("qplan to jplan #0") {
    val v = expr.VertexAttribute("v")
    val gv = qplan.GetVertices(v)

    val qp = gv
    val ip = QPlanToJPlan.transform(qp)

    // TODO assert
  }

//  test("qplan to jplan #2") {
//    val v = VertexAttribute("v")
//    val gv = qplan.GetVertices(v)
//
//    val sort = qplan.Sort(gv)
//    val top = qplan.Top(sort)
//
//    val qp = top
//    val ip = QPlanToJPlan.transform(qp)
//    println(ip)
//  }

  test("qplan to jplan #3") {
    val v = expr.VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val i = QPlanToJPlan.transform(de)
    println(i)
  }

//  test("infer schema") {
//    val v = VertexAttribute("v")
//    val e = EdgeAttribute("e")
//    val w = VertexAttribute("w")
//
//    val gv = GetVertices(v)
//    val exp = Expand(v, w, e, Out(), gv)
//    val p = Project(Seq(), exp)
//    println(p.output)
//
//    p.transformDown {
//      case p: Project => p.copy(schema)
//    }
//    println(p.output)
//  }
//
//    test("infer schema") {
//      val v = VertexAttribute("v")
//      val w = VertexAttribute("w")
//
//      val gv = GetVerticesOp(v)
//      val p = ProjectionOp(Seq(), gv)
//
//      val condition = And(GreaterThan(Literal(3), Literal(2)), EqualTo(v, w))
//      val s = SelectionOp(condition, p)
//
//      println(extractAttributes(condition))
//
//      // set extra variables
//      val plan2 = s.transformDown {
//        case p: ProjectionOp => p
//        case s: SelectionOp => s.copy(extraAttributes = extractAttributes(s.condition))
//      }.asInstanceOf[Op]
//      println(plan2.output)
//      println(plan2.internalSchema)
//    }
//
//  def extractAttributes(expression: Expression): Seq[Attribute] = {
//    expression match {
//      case b: BinaryOperator => extractAttributes(b.left) ++ extractAttributes(b.right)
//      case a: Attribute => Seq(a)
//      case _ => Seq()
//    }
//
//  }

  test("resolving should work") {
    JPlanParser.parse("MATCH (v) RETURN v.a")
  }

  test("handling untyped and unnamed edges should work") {
    JPlanParser.parse("MATCH (v)--(w) RETURN v.a")
  }

}
