package ingraph.cypher2relalg.factories;

import java.util.HashMap;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import relalg.NamedElement;
import relalg.RelalgContainer;
import relalg.RelalgFactory;

@SuppressWarnings("all")
public abstract class NamedElementFactory<TNamedElement extends NamedElement> {
  @Extension
  protected RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  protected final RelalgContainer container;
  
  public NamedElementFactory(final RelalgContainer container) {
    this.container = container;
  }
  
  private static int n = 1;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final HashMap<String, TNamedElement> elements = new HashMap<String, TNamedElement>();
  
  public TNamedElement createElement(final String elementName) {
    TNamedElement _xblockexpression = null;
    {
      String _elvis = null;
      if (elementName != null) {
        _elvis = elementName;
      } else {
        String _generateName = this.generateName();
        _elvis = _generateName;
      }
      final String variableName = _elvis;
      TNamedElement _get = this.elements.get(variableName);
      boolean _tripleEquals = (_get == null);
      if (_tripleEquals) {
        TNamedElement _createSpecificNamedElement = this.createSpecificNamedElement();
        final Procedure1<TNamedElement> _function = (TNamedElement it) -> {
          it.setName(variableName);
          it.setNamedElementContainer(this.container);
        };
        final TNamedElement variable = ObjectExtensions.<TNamedElement>operator_doubleArrow(_createSpecificNamedElement, _function);
        this.elements.put(variableName, variable);
      }
      _xblockexpression = this.elements.get(variableName);
    }
    return _xblockexpression;
  }
  
  /**
   * Indicates if this factory is aware of the element by its name,
   * i.e. the element has ever been created by this factory instance.
   */
  public boolean hasElement(final String elementName) {
    TNamedElement _get = this.elements.get(elementName);
    return (_get != null);
  }
  
  /**
   * Returns the element by name this factory is aware of it,
   * i.e. the element has ever been created by this factory instance.
   * 
   * Otherwise returns null.
   */
  public TNamedElement getElement(final String elementName) {
    return this.elements.get(elementName);
  }
  
  public String generateName() {
    int _plusPlus = NamedElementFactory.n++;
    return ("_e" + Integer.valueOf(_plusPlus));
  }
  
  public abstract TNamedElement createSpecificNamedElement();
  
  @Pure
  public HashMap<String, TNamedElement> getElements() {
    return this.elements;
  }
}
