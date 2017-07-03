package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.NamedElementFactory;
import relalg.EdgeLabel;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class EdgeLabelFactory extends NamedElementFactory<EdgeLabel> {
  public EdgeLabelFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public EdgeLabel createSpecificNamedElement() {
    return this.factory.createEdgeLabel();
  }
}
