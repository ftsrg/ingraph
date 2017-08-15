package ingraph.compiler.cypher2qplan.builders

import ingraph.compiler.cypher2qplan.util.GrammarUtil
import ingraph.model.expr
import ingraph.model.qplan._
import org.slizaa.neo4j.opencypher.{openCypher => oc}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object StatementBuilder {
  def build(statement: oc.Statement): QNode = {
    statement match {
      case s: oc.SingleQuery => buildStatement(s)
      case s: oc.RegularQuery => buildStatement(s)
    }
  }

  /**
    * Builds the qplan for a single query built from several query parts.
    */
  def buildStatement(singleQuery: oc.SingleQuery): QNode = {
    val clauses = singleQuery.getClauses.asScala.toArray
    val ops = ListBuffer.empty[QNode]

    // Do some checks on the clauses of the single query
    //FIXME: Validator.checkSingleQueryClauseSequence(clauses, ce.l)

    /**
      * Process each subquery.
      *
      * A subquery has the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)
      *
      * Note: GrammarUtil.isCudClause is currently limited to CREATE and DELETE clauses.
      */
    var from = 0
    for (i <- clauses.indices) {
      val current = clauses(i)
      val next = if (i + 1 < clauses.length) {
        clauses(i + 1)
      } else {
        null
      }
      if (GrammarUtil.isCudClause(current) && ! GrammarUtil.isCudClause(next) && ! next.isInstanceOf[oc.Return]
        || current.isInstanceOf[oc.With] && ! next.isInstanceOf[oc.Unwind]
        || current.isInstanceOf[oc.Unwind]
        ||	current.isInstanceOf[oc.Return]
      ) {
        // [fromX, toX) is the range of clauses that form a subquery
        val fromX = from
        val toX = i + 1

        ops += QStub("MATCH clause")
        // FIXME: RelalgBuilder._buildRelalgSubQuery(clauses.subList(fromX, toX), subQueryCompilerEnvironment)

        from = i + 1
      }
    }

    /*
     * Compiled form of the query is the chain if the subqueries on the first BinaryOperator having unpopulated leftInput.
     *
     * The first qubquery will receive a dual object source there.
     */
//    Cypher2RelalgUtil.chainEncapsulatedBinaryOperatorsLeft(
//      modelFactory.createDualObjectSourceOperator
//      , ops
//      , EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC
//      , ce.l
//    )
    var result: QNode = GetVertices(expr.VertexAttribute("FIXME: DUAL"))
    for(o <- ops) {
      result = Join(result, o)
    }

    result
  }

  def buildStatement(singleQuery: oc.RegularQuery): QNode = {
    throw new NotImplementedError
  }
}
