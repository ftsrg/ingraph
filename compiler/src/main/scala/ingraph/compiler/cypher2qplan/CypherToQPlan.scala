package ingraph.compiler

import ingraph.compiler.cypher2qplan.QPlanBeautifier
import ingraph.compiler.cypher2qplan.builders.StatementBuilder
import ingraph.model.qplan
import org.slizaa.neo4j.opencypher.openCypher.Cypher

object CypherToQPlan {
  /**
    * Compile an openCypher query to our QPlan representation.
    *
    * Compilation has 3 stages:
    *  1. create a canonical, unresolved query plan
    *  2. resolve attribute references
    *  3. beautify the plan by e.g. removing operations that are structurally idempotent, e.g. natural join with Dual
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    * @param queryName A name to identify the processed query.
    * @return The QPlan representation of the query.
    */
  def build(cypher: Cypher, queryName: String): qplan.QNode = {
    build_IKnowWhatImDoing(cypher, queryName, skipResolve = false, skipBeautify = false)
  }

  /**
    * Compile an openCypher query to our QPlan representation.
    * This version is for experimenting, testing and debugging purposes only.
    *
    * Compilation has 3 stages:
    *  1. create a canonical, unresolved query plan
    *  2. resolve attribute references
    *  3. beautify the plan by e.g. removing operations that are structurally idempotent, e.g. natural join with Dual
    * @param cypher The parsed openCypher query. Use CypherParser.parseXXX to create this representation.
    * @param queryName A name to identify the processed query.
    * @param skipResolve Whether to skip resolving the attribute references or not. For debugging and expreimenting only! Change value at your own risk!
    * @param skipBeautify Whether to skip beautifying the query plan or not. For debugging and expreimenting only! Change value at your own risk!
    * @return The QPlan representation of the query.
    */
  def build_IKnowWhatImDoing(cypher: Cypher, queryName: String, skipResolve: Boolean = false, skipBeautify: Boolean = false): qplan.QNode = {
    // compilation
    val stage1 = compileToQPlan(cypher)

    // resolve if requested
    val stage2 = if (skipResolve) {
      stage1
    } else {
      resolveQPlan(stage1)
    }

    // beautify if requested
    val stage3 = if (skipBeautify) {
      stage2
    } else {
      // use different beautifiers for resolved and unresolved QPlans
      if (skipResolve) beautifyUnresolvedQPlan(stage2) else beautifyResolvedQPlan(stage2)
    }

    stage3
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
    * Given an unresolved QPlan, resolve its attribute-references.
    *
    * FIXME: Currently this is unimplemented, the QPlan is returned unchanged.
    * @param unresolvedQueryPlan
    * @return
    */
  def resolveQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    //FIXME: dummy no-op implementation
    unresolvedQueryPlan
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
