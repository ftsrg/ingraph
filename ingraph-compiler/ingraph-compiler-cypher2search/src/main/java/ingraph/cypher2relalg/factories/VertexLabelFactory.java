package ingraph.cypher2relalg.factories;

import ingraph.cypher2relalg.factories.NamedElementFactory;
import relalg.RelalgContainer;
import relalg.VertexLabel;

@SuppressWarnings("all")
public class VertexLabelFactory extends NamedElementFactory<VertexLabel> {
  public VertexLabelFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public VertexLabel createSpecificNamedElement() {
    return this.factory.createVertexLabel();
  }
}
