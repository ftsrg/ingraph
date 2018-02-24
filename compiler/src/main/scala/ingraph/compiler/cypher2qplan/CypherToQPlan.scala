package ingraph.compiler

import ingraph.compiler.cypher2qplan.builders.StatementBuilder
import ingraph.compiler.cypher2qplan.{QPlanBeautifier, QPlanExpander, QPlanResolver}
import ingraph.model.qplan
import org.slizaa.neo4j.opencypher.openCypher.Cypher

object CypherToQPlan {
  /**
    * Compile an openCypher query to our QPlan representation.
    *
    * Compilation has 4 stages:
    *  1. create a canonical, unresolved raw query plan
    *  2. expand the raw query plan to a full query plan (this can't be skipped)
    *  3. resolve attribute references
    *  4. beautify the plan by e.g. removing operations that are structurally idempotent, e.g. natural join with Dual
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    * @param queryName A name to identify the processed query.
    * @return The QPlan representation of the query.
    */
  def build(cypher: Cypher, queryName: Option[String] = None): qplan.QNode = {
    build_IKnowWhatImDoing(cypher, queryName, skipResolve = false, skipBeautify = false)
  }

  /**
    * Compile an openCypher query to our QPlan representation.
    * This version is for experimenting, testing and debugging purposes only.
    *
    * Compilation has 4 stages:
    *  1. create a canonical, unresolved raw query plan
    *  2. expand the raw query plan to a full query plan (this can't be skipped)
    *  3. resolve attribute references
    *  4. beautify the plan by e.g. removing operations that are structurally idempotent, e.g. natural join with Dual
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    * @param queryName A name to identify the processed query.
    * @param skipResolve Whether to skip resolving the attribute references or not. For debugging and expreimenting only! Change value at your own risk!
    * @param skipBeautify Whether to skip beautifying the query plan or not. For debugging and expreimenting only! Change value at your own risk!
    * @return The QPlan representation of the query.
    */
  def build_IKnowWhatImDoing(cypher: Cypher, queryName: Option[String] = None, skipResolve: Boolean = false, skipBeautify: Boolean = false): qplan.QNode = {
    val _queryName = queryName.getOrElse("Unnamed query")
    // compilation
    val stage1 = compileToQPlan(cypher)

    val stage2 = expandQPlan(stage1)

    // resolve if requested
    val stage3 = if (skipResolve) {
      stage2
    } else {
      resolveQPlan(stage2)
    }

    // beautify if requested
    val stage4 = if (skipBeautify) {
      stage3
    } else {
      // use different beautifiers for resolved and unresolved QPlans
      if (skipResolve) beautifyUnresolvedQPlan(stage3) else beautifyResolvedQPlan(stage3)
    }

    stage4
  }

  /**
    * Given an openCypher query, create its canonical, unresolved QPlan representation.
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    */
  def compileToQPlan(cypher: Cypher): qplan.QNode = {
    val statement = StatementBuilder.dispatchBuildStatement(cypher.getStatement)

    qplan.Production(statement)
  }

  /**
    * Given a raw QPlan, expand complex operations to a combination
    * of more simple operations, like filter criteria given as a map in a vertex pattern
    * is expanded into a selection node.
    *
    * @param rawQueryPlan
    * @return
    */
  def expandQPlan(rawQueryPlan: qplan.QNode): qplan.QNode = {
    QPlanExpander.expandQPlan(rawQueryPlan)
  }

  /**
    * Given an unresolved QPlan, resolve its attribute-references and
    * resolve the unresolved elements in the plan like UnresolvedFunction,
    * UnresolvedProjection to either Grouping with explicit aggregation criteria
    * or Projection.
    *
    * @param unresolvedQueryPlan
    * @return
    */
  def resolveQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    QPlanResolver.resolveQPlan(unresolvedQueryPlan)
  }

  /**
    * Beautify a resolved query plan.
    *
    * For example this removes operations that are structurally idempotent, e.g. natural join with Dual.
    */
  def beautifyResolvedQPlan(resolvedQueryPlan: qplan.QNode): qplan.QNode = {
    QPlanBeautifier.beautifyResolvedQPlan(resolvedQueryPlan)
  }

  /**
    * Beautify an unresolved query plan.
    *
    * For example this removes operations that are structurally idempotent, e.g. natural join with Dual.
    */
  def beautifyUnresolvedQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    QPlanBeautifier.beautifyUnresolvedQPlan(unresolvedQueryPlan)
  }

}
