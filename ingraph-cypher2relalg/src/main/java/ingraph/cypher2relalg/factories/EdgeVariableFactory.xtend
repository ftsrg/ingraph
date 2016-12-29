package ingraph.cypher2relalg.factories

import relalg.EdgeVariable
import relalg.RelalgContainer
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {

  new(RelalgContainer container) {
    super(container)
  }

  override createSpecificNamedElement() {
    createEdgeVariable
  }

  def createElement(RelationshipDetail rd) {
    if (rd.variable != null) {
      createElement(rd.variable.name)
    } else {
      createDontCareElement(rd)
    }
  }
}
