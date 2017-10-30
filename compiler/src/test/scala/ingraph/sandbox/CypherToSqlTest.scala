package ingraph.sandbox

import java.net.URI

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.CypherParser
import ingraph.model.expr.ElementAttribute
import ingraph.model.qplan
import org.apache.spark.sql.catalyst.analysis.{Analyzer, FunctionRegistry, UnresolvedAlias, UnresolvedAttribute}
import org.apache.spark.sql.catalyst.catalog.{CatalogDatabase, InMemoryCatalog, SessionCatalog}
import org.apache.spark.sql.catalyst.expressions.{AttributeReference, Expression, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical
import org.apache.spark.sql.catalyst.plans.logical.{LocalRelation, LogicalPlan}
import org.apache.spark.sql.catalyst.{TableIdentifier, analysis, plans, expressions => cExpr}
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.types.StringType
import org.scalatest.FunSuite

class CypherToSqlTest extends FunSuite {

  test("Convert Cypher query to SQL") {
    val qplan = CypherToQPlan.build(
      CypherParser.parseString("MATCH (n:Person) WHERE n.age > 25 RETURN n.name")
    )

    println(qplan)
    println(QPlanToSqlPlan.transformAndResolve(qplan))
  }

}

object QPlanToSqlPlan {

  def getPropertyAttributeName(eaName: String, nameParts: Seq[String]): Option[String] = {
    if (nameParts.length > 1 && nameParts(0) == eaName) {
      Some(nameParts(1))
    } else {
      None
    }
  }

  def transformAndResolve(plan: qplan.QNode): LogicalPlan = {
    val conf = new SQLConf().copy(SQLConf.CASE_SENSITIVE -> true)

    val elementAttributes = gatherElementAttributes(plan).distinct
    val unresolvedAliases: Seq[NamedExpression] = gatherUnresolvedAliases(plan)

    val catalog = new SessionCatalog(new InMemoryCatalog, FunctionRegistry.builtin, conf)
    catalog.createDatabase(CatalogDatabase("default", "", new URI("loc"), Map.empty), ignoreIfExists = false)

    for (ea <- elementAttributes) {
      val attributes: Seq[String] = unresolvedAliases.flatMap {
        case UnresolvedAttribute(nameParts) => getPropertyAttributeName(ea.name, nameParts)
        case UnresolvedAlias(UnresolvedAttribute(nameParts), _) => getPropertyAttributeName(ea.name, nameParts)
        case _ => None
      }.distinct
      val output: Seq[AttributeReference] = attributes.map(s => AttributeReference(s, StringType)())

      catalog.createTempView(ea.name, LocalRelation(output), overrideIfExists = true)
    }

    // resolve plan
    val analyzer = new Analyzer(catalog, conf)

    val unresolvedSqlPlan = transform(plan)
    val resolvedSqlPlan = analyzer.execute(unresolvedSqlPlan)
    return resolvedSqlPlan
  }

  def transform(plan: qplan.QNode): logical.LogicalPlan = {
    plan match {
      // leaf
      // GetVertices are transformed to a table
      case qplan.GetVertices(v) => analysis.UnresolvedRelation(TableIdentifier(v.name))
      case qplan.Dual() => logical.OneRowRelation

      // unary read
      // Expands are transformed to inner joins on the table of the edge and the target vertex
      case qplan.Expand(src, trg, edge, dir, child) =>
        logical.Join(
          transform(child),
          logical.Join(
            analysis.UnresolvedRelation(TableIdentifier(edge.name)),
            analysis.UnresolvedRelation(TableIdentifier(trg.name)),
            plans.Inner,
            None
          ),
          plans.Inner,
          None
        )
      case qplan.Top(skipExpr, limitExpr, qplan.Sort(order, child)) => logical.GlobalLimit(skipExpr, transform(child))

      case qplan.Production(child) => transform(child) // throw away production nodes for now
      case qplan.Projection(projectList, child) => logical.Project(projectList, transform(child))
      case qplan.Selection(condition, child) => logical.Filter(condition, transform(child))
      case qplan.DuplicateElimination(child) => logical.Distinct(transform(child))
      case qplan.AllDifferent(edges, child) => transform(child) // TODO
      //      case qplan.Unwind(collection, element, child) => iplan.Unwind(collection, element, transform(child))
      // unary CUD
      //      case qplan.Create(attributes, child) => iplan.Create(attributes, transform(child))
      //      case qplan.Delete(attributes, detach, child) => iplan.Delete(attributes, detach, transform(child))
      //      case qplan.Merge(attributes, child) => iplan.Merge(attributes, transform(child))
      //      case qplan.Remove(vertexLabelUpdates, child) => iplan.Remove(vertexLabelUpdates, transform(child))
      //      case qplan.SetNode(vertexLabelUpdates, child) => iplan.SetNode(vertexLabelUpdates, transform(child))

      // binary
      case qplan.Union(true, l, r)                   => logical.Union(transform(l), transform(r))
      case qplan.Union(false, l, r)                  => logical.Distinct(logical.Union(transform(l), transform(r)))
      case qplan.Join(l, r)                          => logical.Join(transform(l), transform(r), plans.Inner, null)
      case qplan.LeftOuterJoin(l, r)                 => logical.Join(transform(l), transform(r), plans.LeftOuter, null)
      case qplan.ThetaLeftOuterJoin(l, r, condition) => logical.Join(transform(l), transform(r), plans.LeftOuter, null)
      case qplan.AntiJoin(l, r)                      => logical.Join(transform(l), transform(r), plans.LeftAnti, null)
    }
  }

  /**
    * Filters the attributes passed in for being included in the child.output schema
    * @param attributes
    * @param child
    * @param invert iff true, match is inverted, i.e. only those are returned which were not found
    * @return
    */
  protected def filterForAttributesOfChildOutput(attributes: Seq[cExpr.Attribute], child: qplan.QNode, invert: Boolean = false): Seq[cExpr.Attribute] = {
    attributes.flatMap( a => if ( invert.^(child.output.exists( co => co.name == a.name )) ) Some(a) else None )
  }

  def gatherElementAttributes(qNode: qplan.QNode): Seq[ElementAttribute] = {
    qNode.flatMap {
      case qplan.GetVertices(v) => Seq(v)
      case qplan.Expand(src, trg, edge, _, _) => Seq(trg, edge)
      case _ => None
    }
  }

  def gatherUnresolvedAliases(qNode: qplan.QNode): Seq[NamedExpression] = {
    qNode.flatMap {
      case qplan.Projection(projectList, _) => projectList.flatMap(extractPropertyAttributesFromExpression(_))
      case qplan.Selection(condition, _) => extractPropertyAttributesFromExpression(condition)
      case qplan.Sort(condition, _) => ???
      case _ => Seq()
    }
  }

  def extractPropertyAttributesFromExpression(expression: Expression): Seq[NamedExpression] = {
    (expression match {
      case a: UnresolvedAttribute => Seq(a)
      case a: UnresolvedAlias => Seq(a)
      case _ => Seq()
    }) ++ expression.children.flatMap(extractPropertyAttributesFromExpression(_))
  }
}
