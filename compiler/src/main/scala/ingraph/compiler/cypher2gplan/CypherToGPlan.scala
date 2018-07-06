package ingraph.compiler

import ingraph.compiler.cypher2gplan.builders.StatementBuilder
import ingraph.compiler.cypher2gplan.{GPlanBeautifier, GPlanExpander, GPlanResolver}
import ingraph.model.gplan
import org.slizaa.neo4j.opencypher.openCypher.Cypher

object CypherToGPlan {
  /**
    * Compile an openCypher query to our GPlan representation.
    *
    * Compilation has 4 stages:
    *  1. create a canonical, unresolved raw query plan
    *  2. expand the raw query plan to a full query plan (this can't be skipped)
    *  3. resolve attribute references
    *  4. beautify the plan by e.g. removing operations that are structurally idempotent, e.g. natural join with Dual
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    * @param queryName A name to identify the processed query.
    * @return The GPlan representation of the query.
    */
  def build(cypher: Cypher, queryName: Option[String] = None): gplan.GNode = {
    build_IKnowWhatImDoing(cypher, queryName, skipResolve = false, skipBeautify = false)
  }

  /**
    * Compile an openCypher query to our GPlan representation.
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
    * @return The GPlan representation of the query.
    */
  def build_IKnowWhatImDoing(cypher: Cypher, queryName: Option[String] = None, skipResolve: Boolean = false, skipBeautify: Boolean = false): gplan.GNode = {
    val _queryName = queryName.getOrElse("Unnamed query")
    // compilation
    val stage1 = compileToGPlan(cypher)

    val stage2 = expandGPlan(stage1)

    // resolve if requested
    val stage3 = if (skipResolve) {
      stage2
    } else {
      resolveGPlan(stage2)
    }

    // beautify if requested
    val stage4 = if (skipBeautify) {
      stage3
    } else {
      // use different beautifiers for resolved and unresolved GPlans
      if (skipResolve) beautifyUnresolvedGPlan(stage3) else beautifyResolvedGPlan(stage3)
    }

    stage4
  }

  /**
    * Given an openCypher query, create its canonical, unresolved GPlan representation.
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    */
  def compileToGPlan(cypher: Cypher): gplan.GNode = {
    val statement = StatementBuilder.dispatchBuildStatement(cypher.getStatement)

    gplan.Production(statement)
  }

  /**
    * Given a raw GPlan, expand complex operations to a combination
    * of more simple operations, like filter criteria given as a map in a vertex pattern
    * is expanded into a selection node.
    *
    * @param rawQueryPlan
    * @return
    */
  def expandGPlan(rawQueryPlan: gplan.GNode): gplan.GNode = {
    GPlanExpander.expandGPlan(rawQueryPlan)
  }

  /**
    * Given an unresolved GPlan, resolve its attribute-references and
    * resolve the unresolved elements in the plan like UnresolvedFunction,
    * UnresolvedProjection to either Grouping with explicit aggregation criteria
    * or Projection.
    *
    * @param unresolvedQueryPlan
    * @return
    */
  def resolveGPlan(unresolvedQueryPlan: gplan.GNode): gplan.GNode = {
    GPlanResolver.resolveGPlan(unresolvedQueryPlan)
  }

  /**
    * Beautify a resolved query plan.
    *
    * For example this removes operations that are structurally idempotent, e.g. natural join with Dual.
    */
  def beautifyResolvedGPlan(resolvedQueryPlan: gplan.GNode): gplan.GNode = {
    GPlanBeautifier.beautifyResolvedGPlan(resolvedQueryPlan)
  }

  /**
    * Beautify an unresolved query plan.
    *
    * For example this removes operations that are structurally idempotent, e.g. natural join with Dual.
    */
  def beautifyUnresolvedGPlan(unresolvedQueryPlan: gplan.GNode): gplan.GNode = {
    GPlanBeautifier.beautifyUnresolvedGPlan(unresolvedQueryPlan)
  }

}
