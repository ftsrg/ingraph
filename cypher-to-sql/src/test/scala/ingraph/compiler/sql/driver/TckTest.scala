package ingraph.compiler.sql.driver

import ingraph.tck.TckTestRunner
import org.scalatest.FunSuite

class TckTest extends FunSuite with TckTestRunner {

  val selectedFeatures = Set("MatchAcceptance", "MatchAcceptance2", "Local")
  val selectedScenarios = Set(
    // Local
    "Return vertices and edges with integer properties",
    "Return vertices and edges",

    // MatchAcceptance
    "Cope with shadowed variables",
    "Filter based on rel prop name",
    "Filter out based on node prop name",
    "Get neighbours",
    "Get related to related to",
    "Get two related nodes",
    "Handle OR in the WHERE clause",
    "Handle comparison between node properties",
    "Honour the column name for RETURN items",
    "Rel type function works as expected",
    "Return two subgraphs with bound undirected relationship and optional relationship",
    "Return two subgraphs with bound undirected relationship",
    "Use multiple MATCH clauses to do a Cartesian product",
    "Use params in pattern matching predicates",
    "Walk alternative relationships",

    // MatchAcceptance2
    "Comparing nodes for equality",
    "Do not fail when evaluating predicates with illegal operations if the AND'ed predicate evaluates to false",
    "Do not fail when predicates on optionally matched and missed nodes are invalid",
    "Handling cyclic patterns when separated into two parts",
    "Handling cyclic patterns",
    "MATCH with OPTIONAL MATCH in longer pattern",
    "Matching all nodes",
    "Matching and returning ordered results, with LIMIT",
    "Matching disconnected patterns",
    "Matching from null nodes should return no results owing to matches being filtered out",
    "Matching nodes using multiple labels",
    "Matching nodes with many labels",
    "Matching using a relationship that is already bound",
    "Matching using a simple pattern with label predicate",
    "Matching using relationship predicate with multiples of the same type",
    "Matching with aggregation",
    "Matching with many predicates and larger pattern",
    "Missing node property should become null",
    "Missing relationship property should become null",
    "Multiple anonymous nodes in a pattern",
    "Non-optional matches should not return nulls",
    "OPTIONAL MATCH with previously bound nodes",
    "Projecting nodes and relationships",
    "Returning a node property value",
    "Returning a relationship property value",
    "Returning bound nodes that are not part of the pattern",
    "Returning multiple node property values",
    "Simple node property predicate",
    "Simple variable length pattern",
    "Three bound nodes pointing to the same node with extra connections",
    "Three bound nodes pointing to the same node",
    "Two bound nodes pointing to the same node",
    "Zero-length variable length pattern in the middle of the pattern",

    // placeholder
    ""
  ).filter(!_.isEmpty)

  runTckTests(() => new TckAdapter, selectedFeatures, ignoredScenarios = Set(), selectedScenarios = selectedScenarios)
}