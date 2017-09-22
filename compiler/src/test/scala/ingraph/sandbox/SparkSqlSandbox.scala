package ingraph.sandbox

import java.net.URI

import ingraph.compiler.CypherToQPlan
import ingraph.compiler.cypher2qplan.{CypherParser, QPlanResolver}
import ingraph.model._
import org.apache.spark.sql.catalyst.analysis.{Analyzer, EliminateSubqueryAliases, FunctionRegistry, SimpleAnalyzer, _}
import org.apache.spark.sql.catalyst.catalog.{CatalogDatabase, InMemoryCatalog, SessionCatalog}
import org.apache.spark.sql.catalyst.expressions.{Alias, Attribute, AttributeMap, AttributeSet, CreateArray, CreateNamedStruct, CreateStruct, Expression, ExtractValue, Generator, Murmur3Hash, NamedExpression, OuterReference, SortOrder, SubqueryExpression}
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.catalyst.rules.Rule
import org.apache.spark.sql.execution.SparkSqlParser
import org.apache.spark.sql.internal.SQLConf
import org.scalatest.FunSuite



class QplanAnalyzer(catalog: SessionCatalog,
                    conf: SQLConf) extends Analyzer(catalog, conf) {
  override lazy val batches: Seq[Batch] = Seq(
    Batch("Resolution", fixedPoint,
        ResolveReferences2 ::
        TypeCoercion.typeCoercionRules ++
          extendedResolutionRules : _*)
  )

  object ResolveReferences2 extends Rule[LogicalPlan] {
    def apply(plan: LogicalPlan): LogicalPlan = plan.resolveOperators {
      case q: LogicalPlan =>
//        println(q)
        q.transformExpressionsUp  {
          case u @ UnresolvedAttribute(nameParts) =>
            // Leave unchanged if resolution fails. Hopefully will be resolved next round.
            val result = withPosition(u) { q.resolveChildren(nameParts, resolver).getOrElse(u) }
            logDebug(s"Resolving $u to $result")
            result
        }
    }

    def newAliases(expressions: Seq[NamedExpression]): Seq[NamedExpression] = {
      expressions.map {
        case a: Alias => Alias(a.child, a.name)(isGenerated = a.isGenerated)
        case other => other
      }
    }

    def findAliases(projectList: Seq[NamedExpression]): AttributeSet = {
      AttributeSet(projectList.collect { case a: Alias => a.toAttribute })
    }

    /**
      * Build a project list for Project/Aggregate and expand the star if possible
      */
    private def buildExpandedProjectList(
                                          exprs: Seq[NamedExpression],
                                          child: LogicalPlan): Seq[NamedExpression] = {
      exprs.flatMap {
        // Using Dataframe/Dataset API: testData2.groupBy($"a", $"b").agg($"*")
        case s: Star => s.expand(child, resolver)
        // Using SQL API without running ResolveAlias: SELECT * FROM testData2 group by a, b
        case UnresolvedAlias(s: Star, _) => s.expand(child, resolver)
        case o if containsStar(o :: Nil) => expandStarExpression(o, child) :: Nil
        case o => o :: Nil
      }.map(_.asInstanceOf[NamedExpression])
    }

    /**
      * Returns true if `exprs` contains a [[Star]].
      */
    def containsStar(exprs: Seq[Expression]): Boolean =
      exprs.exists(_.collect { case _: Star => true }.nonEmpty)

    /**
      * Expands the matching attribute.*'s in `child`'s output.
      */
    def expandStarExpression(expr: Expression, child: LogicalPlan): Expression = {
      expr.transformUp {
        case f1: UnresolvedFunction if containsStar(f1.children) =>
          f1.copy(children = f1.children.flatMap {
            case s: Star => s.expand(child, resolver)
            case o => o :: Nil
          })
        case c: CreateNamedStruct if containsStar(c.valExprs) =>
          val newChildren = c.children.grouped(2).flatMap {
            case Seq(k, s : Star) => CreateStruct(s.expand(child, resolver)).children
            case kv => kv
          }
          c.copy(children = newChildren.toList )
        case c: CreateArray if containsStar(c.children) =>
          c.copy(children = c.children.flatMap {
            case s: Star => s.expand(child, resolver)
            case o => o :: Nil
          })
        case p: Murmur3Hash if containsStar(p.children) =>
          p.copy(children = p.children.flatMap {
            case s: Star => s.expand(child, resolver)
            case o => o :: Nil
          })
        // count(*) has been replaced by count(1)
        case o if containsStar(o.children) =>
          failAnalysis(s"Invalid usage of '*' in expression '${o.prettyName}'")
      }
    }
  }
}

class SparkSqlSandbox extends FunSuite {

  private def makeAnalyzer(conf: SQLConf): Analyzer = {
    val catalog = new SessionCatalog(new InMemoryCatalog, FunctionRegistry.builtin, conf)
    catalog.createDatabase(
      CatalogDatabase("default", "", new URI("loc"), Map.empty),
      ignoreIfExists = false)
    new QplanAnalyzer(catalog, conf) {
      override val extendedResolutionRules =
        ResolveGetVertices ::
        Nil
    }
  }

  object ResolveGetVertices extends Rule[LogicalPlan] {
    def apply(plan: LogicalPlan): LogicalPlan = plan transformUp {
      case qplan.GetVertices(v) => qplan.GetVertices(v)
    }
  }

  test("infer schema #1") {
    val conf = new SQLConf().copy(SQLConf.CASE_SENSITIVE -> true)
    val parser = new SparkSqlParser(conf)

//    println(parser.parsePlan("SELECT 1 AS foo"))
//    println(parser.parsePlan("SELECT 1 UNION DISTINCT SELECT 1"))
//    val plan0 = parser.parsePlan("SELECT y AS z FROM (SELECT 1 AS y)")
//    print(plan0)

//    val analyzer = makeAnalyzer(conf)
//    val analyzer = SimpleAnalyzer
//    val plan1 = analyzer.execute(plan0)
//    analyzer.checkAnalysis(plan1)


    val cypher = CypherParser.parseString("MATCH (p)-[:REL]->(r) RETURN p.name, r.city")
    val plan = CypherToQPlan.build(cypher)
//    println(plan)
  }

}
