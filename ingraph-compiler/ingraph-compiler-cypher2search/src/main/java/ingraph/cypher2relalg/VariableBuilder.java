package ingraph.cypher2relalg;

import com.google.common.base.Objects;
import ingraph.cypher2relalg.factories.EdgeLabelFactory;
import ingraph.cypher2relalg.factories.EdgeListVariableFactory;
import ingraph.cypher2relalg.factories.EdgeVariableFactory;
import ingraph.cypher2relalg.factories.ExpressionVariableFactory;
import ingraph.cypher2relalg.factories.VertexLabelFactory;
import ingraph.cypher2relalg.factories.VertexVariableFactory;
import ingraph.cypher2relalg.util.EUtil;
import ingraph.cypher2relalg.util.ElementVariableUtil;
import ingraph.cypher2relalg.util.ExpressionNameInferencer;
import ingraph.logger.IngraphLogger;
import ingraph.relalg.util.ElementVariableExtractor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slizaa.neo4j.opencypher.openCypher.Cypher;
import org.slizaa.neo4j.opencypher.openCypher.Expression;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup;
import org.slizaa.neo4j.opencypher.openCypher.NodeLabel;
import org.slizaa.neo4j.opencypher.openCypher.NodeLabels;
import org.slizaa.neo4j.opencypher.openCypher.NodePattern;
import org.slizaa.neo4j.opencypher.openCypher.Order;
import org.slizaa.neo4j.opencypher.openCypher.PropertyLookup;
import org.slizaa.neo4j.opencypher.openCypher.RangeLiteral;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipTypes;
import org.slizaa.neo4j.opencypher.openCypher.Unwind;
import org.slizaa.neo4j.opencypher.openCypher.VariableRef;
import org.slizaa.neo4j.opencypher.openCypher.Where;
import org.slizaa.neo4j.opencypher.openCypher.With;
import relalg.AbstractEdgeVariable;
import relalg.AttributeVariable;
import relalg.EdgeLabel;
import relalg.EdgeLabelSet;
import relalg.EdgeListVariable;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.ExpressionVariable;
import relalg.LabelSetStatus;
import relalg.MaxHops;
import relalg.MaxHopsType;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.VertexLabel;
import relalg.VertexLabelSet;
import relalg.VertexVariable;

/**
 * This is the variable builder component of
 * the openCypher to relational algebra compiler.
 */
@SuppressWarnings("all")
public class VariableBuilder {
  @Extension
  private RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  @Extension
  private IngraphLogger logger = new IngraphLogger(VariableBuilder.class.getName());
  
  @Extension
  private ElementVariableExtractor elementVariableExtractor = new ElementVariableExtractor();
  
  private final RelalgContainer topLevelContainer;
  
  @Extension
  private ElementVariableUtil elementVariableUtil;
  
  private final VertexVariableFactory vertexVariableFactory;
  
  private final EdgeVariableFactory edgeVariableFactory;
  
  private final EdgeListVariableFactory edgeListVariableFactory;
  
  /**
   * Chain means that these are chained from the previous return-items.
   */
  private final Map<String, ExpressionVariable> expressionVariableChain;
  
  /**
   * Extended below practically means they are created from the aliased return items
   * or unwind item.
   */
  private final ExpressionVariableFactory expressionVariableFactoryExtended;
  
  private final VertexLabelFactory vertexLabelFactory;
  
  private final EdgeLabelFactory edgeLabelFactory;
  
  /**
   * Constructor that allows propagating the topLevelContainer,
   * but creates new factories for variables and labels.
   * 
   * Use this to create separate builder for each SingleQuery.
   */
  public VariableBuilder(final RelalgContainer topLevelContainer, final IngraphLogger logger) {
    this.logger = logger;
    this.topLevelContainer = topLevelContainer;
    VertexVariableFactory _vertexVariableFactory = new VertexVariableFactory(this.topLevelContainer);
    this.vertexVariableFactory = _vertexVariableFactory;
    EdgeVariableFactory _edgeVariableFactory = new EdgeVariableFactory(this.topLevelContainer);
    this.edgeVariableFactory = _edgeVariableFactory;
    EdgeListVariableFactory _edgeListVariableFactory = new EdgeListVariableFactory(this.topLevelContainer);
    this.edgeListVariableFactory = _edgeListVariableFactory;
    HashMap<String, ExpressionVariable> _hashMap = new HashMap<String, ExpressionVariable>();
    this.expressionVariableChain = _hashMap;
    ExpressionVariableFactory _expressionVariableFactory = new ExpressionVariableFactory(this.topLevelContainer);
    this.expressionVariableFactoryExtended = _expressionVariableFactory;
    VertexLabelFactory _vertexLabelFactory = new VertexLabelFactory(this.topLevelContainer);
    this.vertexLabelFactory = _vertexLabelFactory;
    EdgeLabelFactory _edgeLabelFactory = new EdgeLabelFactory(this.topLevelContainer);
    this.edgeLabelFactory = _edgeLabelFactory;
    ElementVariableUtil _elementVariableUtil = new ElementVariableUtil(this.topLevelContainer);
    this.elementVariableUtil = _elementVariableUtil;
  }
  
  /**
   * Constructor that allows propagating the topLevelContainer
   * and the label factories, but creates new factories for variables.
   * 
   * This is used to create separate builder for each SingleQuery.
   * Outside of this class, you should to use the public helper
   * {@link #cloneBuilderWithNewVariableFactories cloneBuilderWithNewVariableFactories}
   * call instead.
   */
  protected VariableBuilder(final RelalgContainer topLevelContainer, final VertexLabelFactory vertexLabelFactory, final EdgeLabelFactory edgeLabelFactory, final Collection<ExpressionVariable> expressionVariableChain, final IngraphLogger logger) {
    this.logger = logger;
    this.topLevelContainer = topLevelContainer;
    VertexVariableFactory _vertexVariableFactory = new VertexVariableFactory(this.topLevelContainer);
    this.vertexVariableFactory = _vertexVariableFactory;
    EdgeVariableFactory _edgeVariableFactory = new EdgeVariableFactory(this.topLevelContainer);
    this.edgeVariableFactory = _edgeVariableFactory;
    EdgeListVariableFactory _edgeListVariableFactory = new EdgeListVariableFactory(this.topLevelContainer);
    this.edgeListVariableFactory = _edgeListVariableFactory;
    HashMap<String, ExpressionVariable> _hashMap = new HashMap<String, ExpressionVariable>();
    this.expressionVariableChain = _hashMap;
    final Consumer<ExpressionVariable> _function = (ExpressionVariable it) -> {
      final ExpressionVariable f_it = it;
      String _name = f_it.getName();
      this.expressionVariableChain.put(_name, f_it);
    };
    expressionVariableChain.forEach(_function);
    ExpressionVariableFactory _expressionVariableFactory = new ExpressionVariableFactory(this.topLevelContainer);
    this.expressionVariableFactoryExtended = _expressionVariableFactory;
    this.vertexLabelFactory = vertexLabelFactory;
    this.edgeLabelFactory = edgeLabelFactory;
    ElementVariableUtil _elementVariableUtil = new ElementVariableUtil(this.topLevelContainer);
    this.elementVariableUtil = _elementVariableUtil;
  }
  
  /**
   * Creates a new VariableBuilder instance that has new variable factory instances
   * but retains label factories and the topLevelContainer.
   * 
   * This also chains forward expression variables, i.e. those from a WITH or RETURN clause
   */
  public VariableBuilder cloneBuilderWithNewVariableFactories() {
    HashMap<String, ExpressionVariable> _elements = this.expressionVariableFactoryExtended.getElements();
    Collection<ExpressionVariable> _values = _elements.values();
    return new VariableBuilder(this.topLevelContainer, this.vertexLabelFactory, this.edgeLabelFactory, _values, this.logger);
  }
  
  protected AttributeVariable buildPropertyLookupHelper(final Variable ev, final ExpressionNodeLabelsAndPropertyLookup e) {
    AttributeVariable _xifexpression = null;
    EList<PropertyLookup> _propertyLookups = e.getPropertyLookups();
    int _length = ((Object[])Conversions.unwrapArray(_propertyLookups, Object.class)).length;
    boolean _equals = (_length == 1);
    if (_equals) {
      AttributeVariable _xblockexpression = null;
      {
        EList<PropertyLookup> _propertyLookups_1 = e.getPropertyLookups();
        PropertyLookup _get = _propertyLookups_1.get(0);
        final String attributeName = _get.getPropertyKeyName();
        if ((ev instanceof ExpressionVariable)) {
          final ElementVariable innerVariable = this.elementVariableExtractor.extractElementVariable(ev);
          if ((innerVariable == null)) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Can\'t find neither VertexVariable nor EdgeVariable wrapped into the ExpressionVariable, so can\'t build attribute variable.");
            this.logger.unrecoverableError(_builder.toString());
          }
        }
        AttributeVariable _switchResult = null;
        boolean _matched = false;
        if (ev instanceof VertexVariable) {
          _matched=true;
        }
        if (!_matched) {
          if (ev instanceof EdgeVariable) {
            _matched=true;
          }
        }
        if (!_matched) {
          if (ev instanceof ExpressionVariable) {
            _matched=true;
          }
        }
        if (_matched) {
          _switchResult = this.elementVariableUtil.createAttribute(ev, attributeName);
        }
        if (!_matched) {
          Object _xblockexpression_1 = null;
          {
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("Unsupported type received: ");
            Class<? extends Variable> _class = ev.getClass();
            String _name = _class.getName();
            _builder_1.append(_name, "");
            this.logger.unrecoverableError(_builder_1.toString());
            _xblockexpression_1 = null;
          }
          _switchResult = ((AttributeVariable)_xblockexpression_1);
        }
        _xblockexpression = _switchResult;
      }
      _xifexpression = _xblockexpression;
    } else {
      Object _xblockexpression_1 = null;
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("PropertyLookup count ");
        EList<PropertyLookup> _propertyLookups_1 = e.getPropertyLookups();
        int _length_1 = ((Object[])Conversions.unwrapArray(_propertyLookups_1, Object.class)).length;
        _builder.append(_length_1, "");
        _builder.append(" not supported.");
        this.logger.unrecoverableError(_builder.toString());
        _xblockexpression_1 = null;
      }
      _xifexpression = ((AttributeVariable)_xblockexpression_1);
    }
    return _xifexpression;
  }
  
  protected Variable _buildRelalgVariable(final ExpressionNodeLabelsAndPropertyLookup e) {
    AttributeVariable _xblockexpression = null;
    {
      Expression _left = e.getLeft();
      final Variable v = this.buildRelalgVariable(_left);
      _xblockexpression = this.buildPropertyLookupHelper(v, e);
    }
    return _xblockexpression;
  }
  
  protected Variable _buildRelalgVariable(final VariableRef varRef) {
    Variable _xblockexpression = null;
    {
      final boolean useExpressionVariables = this.findOutExpressionVariableUsageFromContext(varRef);
      _xblockexpression = this.buildRelalgVariableInternal(varRef, useExpressionVariables);
    }
    return _xblockexpression;
  }
  
  /**
   * Variable lookup scope depends on the clause in which the variable is being resolved.
   * 
   * UNWIND, ORDER BY and WITH...WHERE clauses use expression variables, others don't use them for name lookup
   */
  public boolean findOutExpressionVariableUsageFromContext(final EObject eo) {
    return ((EUtil.hasInContainerHierarchy(eo, Collections.<Class>unmodifiableList(CollectionLiterals.<Class>newArrayList(Unwind.class)), Cypher.class) || EUtil.hasInContainerHierarchy(eo, Collections.<Class>unmodifiableList(CollectionLiterals.<Class>newArrayList(Order.class)), Cypher.class)) || EUtil.hasInContainerHierarchy(eo, Collections.<Class>unmodifiableList(CollectionLiterals.<Class>newArrayList(Where.class, With.class)), Cypher.class));
  }
  
  @Deprecated
  protected Variable _buildRelalgVariableExtended(final VariableRef varRef) {
    return this.buildRelalgVariableInternal(varRef, true);
  }
  
  @Deprecated
  protected Variable _buildRelalgVariableExtended(final ExpressionNodeLabelsAndPropertyLookup e) {
    AttributeVariable _xblockexpression = null;
    {
      Expression _left = e.getLeft();
      final Variable v = this.buildRelalgVariableExtended(_left);
      _xblockexpression = this.buildPropertyLookupHelper(v, e);
    }
    return _xblockexpression;
  }
  
  /**
   * Resolves a variable by its name.
   * 
   * Expression variables from return has the highest priority in name resolution for order by
   * and UNWIND, but they don't play a role when building later return items.
   * 
   * @param useExpressionVariables indicate whether we should take expressionvariables into account for name resolution.
   */
  protected Variable buildRelalgVariableInternal(final VariableRef varRef, final boolean useExpressionVariables) {
    Variable _xblockexpression = null;
    {
      org.slizaa.neo4j.opencypher.openCypher.Variable _variableRef = varRef.getVariableRef();
      String _name = _variableRef.getName();
      final Variable v = this.findVariable(_name, useExpressionVariables);
      Variable _xifexpression = null;
      if ((v == null)) {
        Object _xblockexpression_1 = null;
        {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Variable name not found: ");
          org.slizaa.neo4j.opencypher.openCypher.Variable _variableRef_1 = varRef.getVariableRef();
          String _name_1 = _variableRef_1.getName();
          _builder.append(_name_1, "");
          this.logger.unrecoverableError(_builder.toString());
          _xblockexpression_1 = null;
        }
        _xifexpression = ((Variable)_xblockexpression_1);
      } else {
        _xifexpression = v;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public AbstractEdgeVariable buildEdgeVariable(final RelationshipDetail r) {
    AbstractEdgeVariable _xblockexpression = null;
    {
      AbstractEdgeVariable _xifexpression = null;
      RangeLiteral _range = null;
      if (r!=null) {
        _range=r.getRange();
      }
      boolean _tripleEquals = (_range == null);
      if (_tripleEquals) {
        _xifexpression = this.edgeVariableFactory.createElement(r);
      } else {
        EdgeListVariable _createElement = this.edgeListVariableFactory.createElement(r);
        final Procedure1<EdgeListVariable> _function = (EdgeListVariable it) -> {
          Integer _xifexpression_1 = null;
          RangeLiteral _range_1 = null;
          if (r!=null) {
            _range_1=r.getRange();
          }
          String _lower = _range_1.getLower();
          boolean _tripleEquals_1 = (_lower == null);
          if (_tripleEquals_1) {
            _xifexpression_1 = Integer.valueOf(1);
          } else {
            RangeLiteral _range_2 = null;
            if (r!=null) {
              _range_2=r.getRange();
            }
            String _lower_1 = _range_2.getLower();
            _xifexpression_1 = Integer.valueOf(_lower_1);
          }
          it.setMinHops((_xifexpression_1).intValue());
          MaxHops _xifexpression_2 = null;
          RangeLiteral _range_3 = null;
          if (r!=null) {
            _range_3=r.getRange();
          }
          String _upper = _range_3.getUpper();
          boolean _tripleEquals_2 = (_upper == null);
          if (_tripleEquals_2) {
            MaxHops _createMaxHops = this.factory.createMaxHops();
            final Procedure1<MaxHops> _function_1 = (MaxHops it_1) -> {
              it_1.setMaxHopsType(MaxHopsType.UNLIMITED);
            };
            _xifexpression_2 = ObjectExtensions.<MaxHops>operator_doubleArrow(_createMaxHops, _function_1);
          } else {
            MaxHops _createMaxHops_1 = this.factory.createMaxHops();
            final Procedure1<MaxHops> _function_2 = (MaxHops it_1) -> {
              it_1.setMaxHopsType(MaxHopsType.LIMITED);
              RangeLiteral _range_4 = null;
              if (r!=null) {
                _range_4=r.getRange();
              }
              String _upper_1 = _range_4.getUpper();
              Integer _valueOf = Integer.valueOf(_upper_1);
              it_1.setHops((_valueOf).intValue());
            };
            _xifexpression_2 = ObjectExtensions.<MaxHops>operator_doubleArrow(_createMaxHops_1, _function_2);
          }
          it.setMaxHops(_xifexpression_2);
        };
        _xifexpression = ObjectExtensions.<EdgeListVariable>operator_doubleArrow(_createElement, _function);
      }
      final AbstractEdgeVariable edgeVariable = _xifexpression;
      RelationshipTypes _types = null;
      if (r!=null) {
        _types=r.getTypes();
      }
      EList<String> _relTypeName = null;
      if (_types!=null) {
        _relTypeName=_types.getRelTypeName();
      }
      this.combineLabelSet(edgeVariable, _relTypeName, this.edgeLabelFactory);
      _xblockexpression = edgeVariable;
    }
    return _xblockexpression;
  }
  
  public VertexVariable buildVertexVariable(final NodePattern n) {
    VertexVariable _xblockexpression = null;
    {
      final VertexVariable vertexVariable = this.vertexVariableFactory.createElement(n);
      NodeLabels _nodeLabels = n.getNodeLabels();
      EList<NodeLabel> _nodeLabels_1 = null;
      if (_nodeLabels!=null) {
        _nodeLabels_1=_nodeLabels.getNodeLabels();
      }
      if (_nodeLabels_1!=null) {
        final Consumer<NodeLabel> _function = (NodeLabel it) -> {
          String _labelName = it.getLabelName();
          VertexLabel _createElement = this.vertexLabelFactory.createElement(_labelName);
          this.ensureLabel(vertexVariable, _createElement);
        };
        _nodeLabels_1.forEach(_function);
      }
      _xblockexpression = vertexVariable;
    }
    return _xblockexpression;
  }
  
  /**
   * Wraps an expression into an ExpressionVariable with its name given or inferred.
   * 
   * @param name the name, or null to have it inferred.
   * @param expression the expression to wrap
   * 
   * @return the ExpressionVariable itself.
   */
  public ExpressionVariable buildExpressionVariable(final String name, final relalg.Expression expression) {
    ExpressionVariable _xblockexpression = null;
    {
      String _xifexpression = null;
      if ((name == null)) {
        _xifexpression = ExpressionNameInferencer.inferName(expression, this.logger);
      } else {
        _xifexpression = name;
      }
      final String iName = _xifexpression;
      ExpressionVariable _createElement = this.expressionVariableFactoryExtended.createElement(iName, expression);
      final Procedure1<ExpressionVariable> _function = (ExpressionVariable it) -> {
        it.setHasInferredName((name == null));
      };
      _xblockexpression = ObjectExtensions.<ExpressionVariable>operator_doubleArrow(_createElement, _function);
    }
    return _xblockexpression;
  }
  
  protected VertexLabelSet ensureLabel(final VertexVariable vertexVariable, final VertexLabel label) {
    VertexLabelSet _xifexpression = null;
    VertexLabelSet _vertexLabelSet = vertexVariable.getVertexLabelSet();
    EList<VertexLabel> _vertexLabels = _vertexLabelSet.getVertexLabels();
    boolean _contains = _vertexLabels.contains(label);
    boolean _not = (!_contains);
    if (_not) {
      VertexLabelSet _vertexLabelSet_1 = vertexVariable.getVertexLabelSet();
      final Procedure1<VertexLabelSet> _function = (VertexLabelSet it) -> {
        EList<VertexLabel> _vertexLabels_1 = it.getVertexLabels();
        _vertexLabels_1.add(label);
        it.setStatus(LabelSetStatus.NON_EMPTY);
      };
      _xifexpression = ObjectExtensions.<VertexLabelSet>operator_doubleArrow(_vertexLabelSet_1, _function);
    }
    return _xifexpression;
  }
  
  public void combineLabelSet(final AbstractEdgeVariable edgeVariable, final EList<String> labels, final EdgeLabelFactory edgeLabelFactory) {
    if (((labels == null) || labels.isEmpty())) {
      return;
    }
    EdgeLabelSet _edgeLabelSet = edgeVariable.getEdgeLabelSet();
    LabelSetStatus _status = _edgeLabelSet.getStatus();
    boolean _equals = Objects.equal(_status, LabelSetStatus.EMPTY);
    if (_equals) {
      final Consumer<String> _function = (String it) -> {
        final EdgeLabel label = edgeLabelFactory.createElement(it);
        EdgeLabelSet _edgeLabelSet_1 = edgeVariable.getEdgeLabelSet();
        EList<EdgeLabel> _edgeLabels = _edgeLabelSet_1.getEdgeLabels();
        boolean _contains = _edgeLabels.contains(label);
        boolean _not = (!_contains);
        if (_not) {
          EdgeLabelSet _edgeLabelSet_2 = edgeVariable.getEdgeLabelSet();
          EList<EdgeLabel> _edgeLabels_1 = _edgeLabelSet_2.getEdgeLabels();
          _edgeLabels_1.add(label);
        }
      };
      labels.forEach(_function);
      EdgeLabelSet _edgeLabelSet_1 = edgeVariable.getEdgeLabelSet();
      LabelSetStatus _xifexpression = null;
      EdgeLabelSet _edgeLabelSet_2 = edgeVariable.getEdgeLabelSet();
      EList<EdgeLabel> _edgeLabels = _edgeLabelSet_2.getEdgeLabels();
      boolean _isEmpty = _edgeLabels.isEmpty();
      if (_isEmpty) {
        _xifexpression = LabelSetStatus.EMPTY;
      } else {
        _xifexpression = LabelSetStatus.NON_EMPTY;
      }
      _edgeLabelSet_1.setStatus(_xifexpression);
    } else {
      final List<EdgeLabel> intersection = new ArrayList<EdgeLabel>();
      final Consumer<String> _function_1 = (String it) -> {
        final EdgeLabel label = edgeLabelFactory.createElement(it);
        if (((!intersection.contains(label)) && edgeVariable.getEdgeLabelSet().getEdgeLabels().contains(label))) {
          intersection.add(label);
        }
      };
      labels.forEach(_function_1);
      EdgeLabelSet _edgeLabelSet_3 = edgeVariable.getEdgeLabelSet();
      EList<EdgeLabel> _edgeLabels_1 = _edgeLabelSet_3.getEdgeLabels();
      int _size = _edgeLabels_1.size();
      int _size_1 = intersection.size();
      boolean _notEquals = (_size != _size_1);
      if (_notEquals) {
        EdgeLabelSet _edgeLabelSet_4 = edgeVariable.getEdgeLabelSet();
        EList<EdgeLabel> _edgeLabels_2 = _edgeLabelSet_4.getEdgeLabels();
        _edgeLabels_2.clear();
        EdgeLabelSet _edgeLabelSet_5 = edgeVariable.getEdgeLabelSet();
        EList<EdgeLabel> _edgeLabels_3 = _edgeLabelSet_5.getEdgeLabels();
        _edgeLabels_3.addAll(intersection);
      }
      EdgeLabelSet _edgeLabelSet_6 = edgeVariable.getEdgeLabelSet();
      LabelSetStatus _xifexpression_1 = null;
      EdgeLabelSet _edgeLabelSet_7 = edgeVariable.getEdgeLabelSet();
      EList<EdgeLabel> _edgeLabels_4 = _edgeLabelSet_7.getEdgeLabels();
      boolean _isEmpty_1 = _edgeLabels_4.isEmpty();
      if (_isEmpty_1) {
        LabelSetStatus _xblockexpression = null;
        {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Contradicting labelset constraints found for edge variable ");
          String _name = edgeVariable.getName();
          _builder.append(_name, "");
          this.logger.warning(_builder.toString());
          _xblockexpression = LabelSetStatus.CONTRADICTING;
        }
        _xifexpression_1 = _xblockexpression;
      } else {
        _xifexpression_1 = LabelSetStatus.NON_EMPTY;
      }
      _edgeLabelSet_6.setStatus(_xifexpression_1);
    }
  }
  
  /**
   * Packs the appropriate variable into a VariableExpression.
   * 
   * This builder method ensures that the new VariableEpression instance
   * is registered to the container registered with this builder.
   */
  public VariableExpression buildVariableExpression(final Variable v) {
    VariableExpression _createVariableExpression = this.factory.createVariableExpression();
    final Procedure1<VariableExpression> _function = (VariableExpression it) -> {
      it.setVariable(v);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
  }
  
  /**
   * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
   * 
   * This builder method ensures that the new VariableEpression instance
   * is registered to the container registered with this builder.
   */
  protected VariableExpression _buildVariableExpression(final VariableRef v, final boolean useExpressionVariables) {
    VariableExpression _createVariableExpression = this.factory.createVariableExpression();
    final Procedure1<VariableExpression> _function = (VariableExpression it) -> {
      Variable _xifexpression = null;
      if (useExpressionVariables) {
        _xifexpression = this.buildRelalgVariableExtended(v);
      } else {
        _xifexpression = this.buildRelalgVariable(v);
      }
      it.setVariable(_xifexpression);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
  }
  
  /**
   * Builds or resolves the appropriate variable and then packs it into a VariableExpression.
   * 
   * This builder method ensures that the new eVariableEpression instance
   * is registered to the container registered with this builder.
   */
  protected VariableExpression _buildVariableExpression(final ExpressionNodeLabelsAndPropertyLookup v, final boolean useExpressionVariables) {
    VariableExpression _createVariableExpression = this.factory.createVariableExpression();
    final Procedure1<VariableExpression> _function = (VariableExpression it) -> {
      Variable _xifexpression = null;
      if (useExpressionVariables) {
        _xifexpression = this.buildRelalgVariableExtended(v);
      } else {
        _xifexpression = this.buildRelalgVariable(v);
      }
      it.setVariable(_xifexpression);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
  }
  
  public HashMap<String, VertexVariable> getVertexVariableFactoryElements() {
    return this.vertexVariableFactory.getElements();
  }
  
  public HashMap<String, EdgeVariable> getEdgeVariableFactoryElements() {
    return this.edgeVariableFactory.getElements();
  }
  
  /**
   * Finds and returns a variable by name in the variable registers,
   * i.e. in the factories or in the chained variables.
   * 
   * @param useExpressionVariables specifies whether to look into the expressionVariableFactory,
   *        i.e. if we are interested in variables from the WITH/RETURN/UNWIND clauses of the current subquery.
   *        Note, that chained variables are always looked up
   * 
   * If not found or null was passed for name, null is returned.
   */
  public Variable findVariable(final String name, final boolean useExpressionVariables) {
    Variable _xifexpression = null;
    if ((name == null)) {
      _xifexpression = null;
    } else {
      Variable _xifexpression_1 = null;
      if ((useExpressionVariables && this.expressionVariableFactoryExtended.hasElement(name))) {
        _xifexpression_1 = this.expressionVariableFactoryExtended.getElement(name);
      } else {
        Variable _xifexpression_2 = null;
        ExpressionVariable _get = this.expressionVariableChain.get(name);
        boolean _tripleNotEquals = (_get != null);
        if (_tripleNotEquals) {
          _xifexpression_2 = this.expressionVariableChain.get(name);
        } else {
          ElementVariable _xifexpression_3 = null;
          boolean _hasElement = this.vertexVariableFactory.hasElement(name);
          if (_hasElement) {
            _xifexpression_3 = this.vertexVariableFactory.getElement(name);
          } else {
            AbstractEdgeVariable _xifexpression_4 = null;
            boolean _hasElement_1 = this.edgeVariableFactory.hasElement(name);
            if (_hasElement_1) {
              _xifexpression_4 = this.edgeVariableFactory.getElement(name);
            } else {
              EdgeListVariable _xifexpression_5 = null;
              boolean _hasElement_2 = this.edgeListVariableFactory.hasElement(name);
              if (_hasElement_2) {
                _xifexpression_5 = this.edgeListVariableFactory.getElement(name);
              } else {
                _xifexpression_5 = null;
              }
              _xifexpression_4 = _xifexpression_5;
            }
            _xifexpression_3 = _xifexpression_4;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public Variable buildRelalgVariable(final Expression e) {
    if (e instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildRelalgVariable((ExpressionNodeLabelsAndPropertyLookup)e);
    } else if (e instanceof VariableRef) {
      return _buildRelalgVariable((VariableRef)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
  
  public Variable buildRelalgVariableExtended(final Expression e) {
    if (e instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildRelalgVariableExtended((ExpressionNodeLabelsAndPropertyLookup)e);
    } else if (e instanceof VariableRef) {
      return _buildRelalgVariableExtended((VariableRef)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
  
  public VariableExpression buildVariableExpression(final Expression v, final boolean useExpressionVariables) {
    if (v instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildVariableExpression((ExpressionNodeLabelsAndPropertyLookup)v, useExpressionVariables);
    } else if (v instanceof VariableRef) {
      return _buildVariableExpression((VariableRef)v, useExpressionVariables);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(v, useExpressionVariables).toString());
    }
  }
}
