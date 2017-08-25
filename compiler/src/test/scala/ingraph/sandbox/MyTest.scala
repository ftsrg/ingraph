package ingraph.sandbox

import ingraph.compiler.qplan2iplan.{QPlanToIPlan, SchemaInferencer}
import ingraph.model._
import org.apache.spark.sql.catalyst.expressions.{GreaterThan, Literal}
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

  test("qplan to iplan #0") {
    val v = expr.VertexAttribute("v")
    val gv = qplan.GetVertices(v)

    val qp = gv
    val ip = QPlanToIPlan.transform(qp)
    println(ip)
  }

  test("qplan to iplan #1") {
    val v = expr.VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val qp = de

    val ip = QPlanToIPlan.transform(qp)
    println(ip)

    val ip2 = SchemaInferencer.transform(ip)
    println(ip2)
  }

//  test("qplan to iplan #2") {
//    val v = VertexAttribute("v")
//    val gv = qplan.GetVertices(v)
//
//    val sort = qplan.Sort(gv)
//    val top = qplan.Top(sort)
//
//    val qp = top
//    val ip = QPlanToIPlan.transform(qp)
//    println(ip)
//  }

  test("qplan to iplan #3") {
    val v = expr.VertexAttribute("v")
    val gv = qplan.GetVertices(v)
    val de = qplan.DuplicateElimination(gv)

    val i = QPlanToIPlan.transform(de)
    println(i)
  }

  test("qplan to iplan #4") {
    val els = expr.EdgeLabelSet(Set("REF"))

    val v = expr.VertexAttribute("v")
    val e = expr.EdgeAttribute("e", els)
    val w = expr.VertexAttribute("w")

    val gv = qplan.GetVertices(v)
    val exp = qplan.Expand(v, w, e, expr.Out, gv)
    val de = qplan.DuplicateElimination(exp)

    val i = QPlanToIPlan.transform(de)
    println(i)

    val s = SchemaInferencer.transform(i)
    println(s)
  }

  test("qplan to iplan #5") {
    val vls = expr.VertexLabelSet(Set("Person"), expr.NonEmpty)


    val n = expr.VertexAttribute("n", vls)
    val name = expr.PropertyAttribute("name", n)
    val age = expr.PropertyAttribute("age", n)

    val projectList = Seq(name)
    val condition = GreaterThan(age, Literal(27))

    val q = qplan.Projection(
      projectList,
      qplan.Selection(
        condition,
        qplan.GetVertices(n)
      )
    )

    val i = QPlanToIPlan.transform(q)
    println(i)

    val s = SchemaInferencer.transform(i)
    println(s)
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

}
