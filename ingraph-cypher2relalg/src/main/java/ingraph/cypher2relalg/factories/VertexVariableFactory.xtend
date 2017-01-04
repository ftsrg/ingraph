package ingraph.cypher2relalg.factories

import relalg.VertexVariable
import relalg.RelalgContainer
import org.slizaa.neo4j.opencypher.openCypher.NodePattern

class VertexVariableFactory extends VariableFactory<VertexVariable> {

  new(RelalgContainer container) {
    super(container)
  }

  override createSpecificNamedElement() {
    createVertexVariable
  }

  def createElement(NodePattern rd) {
    if (rd.variable !== null) {
      createElement(rd.variable.name)
    } else {
      createDontCareElement(rd)
    }
  }
}
