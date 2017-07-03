package ingraph.cypher2relalg.factories;

import com.google.common.base.Objects;
import ingraph.cypher2relalg.factories.NamedElementFactory;
import java.util.HashMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.RelalgContainer;
import relalg.Variable;

@SuppressWarnings("all")
public abstract class VariableFactory<TVariable extends Variable> extends NamedElementFactory<TVariable> {
  /**
   * this will be used to lookup names generated for dontCare variables.
   * 
   * It is useful whenever a single node of the parse graph need to be processed consistently twice.
   * EMF guarantees that .equals() holds iff == holds on two EObject instances.
   */
  private final HashMap<EObject, String> dontCareElementNames = new HashMap<EObject, String>();
  
  public VariableFactory(final RelalgContainer container) {
    super(container);
  }
  
  @Override
  public TVariable createElement(final String elementName) {
    final TVariable e = super.createElement(elementName);
    boolean _equals = Objects.equal(elementName, null);
    if (_equals) {
      e.setDontCare(true);
    }
    return e;
  }
  
  public TVariable createDontCareElement(final EObject eo) {
    TVariable _xblockexpression = null;
    {
      String _get = this.dontCareElementNames.get(eo);
      boolean _equals = Objects.equal(_get, null);
      if (_equals) {
        String _generateName = this.generateName();
        this.dontCareElementNames.put(eo, _generateName);
      }
      String _get_1 = this.dontCareElementNames.get(eo);
      TVariable _createElement = this.createElement(_get_1);
      final Procedure1<TVariable> _function = (TVariable it) -> {
        it.setDontCare(true);
      };
      _xblockexpression = ObjectExtensions.<TVariable>operator_doubleArrow(_createElement, _function);
    }
    return _xblockexpression;
  }
}
