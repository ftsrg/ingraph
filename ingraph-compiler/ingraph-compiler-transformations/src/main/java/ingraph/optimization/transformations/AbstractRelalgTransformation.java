package ingraph.optimization.transformations;

import ingraph.logger.IngraphLogger;
import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import ingraph.optimization.patterns.DefaultExpandOperatorMatcher;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatch;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.SortAndTopOperatorMatch;
import ingraph.optimization.patterns.SortAndTopOperatorMatcher;
import ingraph.optimization.patterns.TransitiveExpandOperatorMatch;
import ingraph.optimization.patterns.TransitiveExpandOperatorMatcher;
import java.io.Closeable;
import java.io.IOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.AbstractEdgeVariable;
import relalg.AntiJoinOperator;
import relalg.BinaryOperator;
import relalg.Direction;
import relalg.EdgeLabelSet;
import relalg.EdgeListVariable;
import relalg.EdgeVariable;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.GetEdgesOperator;
import relalg.JoinOperator;
import relalg.LeftOuterJoinOperator;
import relalg.Operator;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.SelectionOperator;
import relalg.SortAndTopOperator;
import relalg.SortEntry;
import relalg.SortOperator;
import relalg.TopOperator;
import relalg.TransitiveClosureJoinOperator;
import relalg.UnaryOperator;
import relalg.VertexVariable;

@SuppressWarnings("all")
public abstract class AbstractRelalgTransformation implements Closeable {
  @Extension
  protected IngraphLogger logger = new IngraphLogger(AbstractRelalgTransformation.class.getName());
  
  @Extension
  protected final RelalgFactory relalgFactory = RelalgFactory.eINSTANCE;
  
  @Extension
  protected final BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory();
  
  protected final RelalgContainer container;
  
  protected final AdvancedViatraQueryEngine engine;
  
  protected final BatchTransformationStatements statements;
  
  public AbstractRelalgTransformation(final RelalgContainer container) {
    try {
      final Logger logger = Logger.getRootLogger();
      logger.setLevel(Level.ERROR);
      ViatraQueryLoggingUtil.setExternalLogger(logger);
      this.container = container;
      final EMFScope scope = new EMFScope(container);
      AdvancedViatraQueryEngine _createUnmanagedEngine = AdvancedViatraQueryEngine.createUnmanagedEngine(scope);
      this.engine = _createUnmanagedEngine;
      BatchTransformation.BatchTransformationBuilder _forEngine = BatchTransformation.forEngine(this.engine);
      BatchTransformation _build = _forEngine.build();
      BatchTransformationStatements _transformationStatements = _build.getTransformationStatements();
      this.statements = _transformationStatements;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void changeChildOperator(final Operator parentOperator, final Operator currentOperator, final Operator newOperator) {
    boolean _matched = false;
    if (parentOperator instanceof UnaryOperator) {
      _matched=true;
      ((UnaryOperator)parentOperator).setInput(newOperator);
    }
    if (!_matched) {
      if (parentOperator instanceof BinaryOperator) {
        _matched=true;
        Operator _leftInput = ((BinaryOperator)parentOperator).getLeftInput();
        boolean _equals = _leftInput.equals(currentOperator);
        if (_equals) {
          ((BinaryOperator)parentOperator).setLeftInput(newOperator);
        }
        Operator _rightInput = ((BinaryOperator)parentOperator).getRightInput();
        boolean _equals_1 = _rightInput.equals(currentOperator);
        if (_equals_1) {
          ((BinaryOperator)parentOperator).setRightInput(newOperator);
        }
      }
    }
    if ((currentOperator instanceof ExpandOperator)) {
      EcoreUtil2.delete(currentOperator);
    }
  }
  
  protected VertexVariable source(final ExpandOperator expandOperator) {
    Direction _direction = expandOperator.getDirection();
    if (_direction != null) {
      switch (_direction) {
        case BOTH:
        case IN:
          return expandOperator.getTargetVertexVariable();
        case OUT:
          return expandOperator.getSourceVertexVariable();
        default:
          break;
      }
    }
    return null;
  }
  
  protected VertexVariable target(final ExpandOperator expandOperator) {
    Direction _direction = expandOperator.getDirection();
    if (_direction != null) {
      switch (_direction) {
        case BOTH:
        case IN:
          return expandOperator.getSourceVertexVariable();
        case OUT:
          return expandOperator.getTargetVertexVariable();
        default:
          break;
      }
    }
    return null;
  }
  
  @Override
  public void close() throws IOException {
    if (this.engine!=null) {
      this.engine.dispose();
    }
  }
  
  protected Direction normalizeDirection(final Direction direction) {
    if (direction != null) {
      switch (direction) {
        case BOTH:
          return Direction.BOTH;
        default:
          return Direction.OUT;
      }
    } else {
      return Direction.OUT;
    }
  }
  
  /**
   * [1] Replace the GetVerticesOperator + default 1..1 ExpandOperator pairs with a single GetEdgesOperator
   */
  protected BatchTransformationRule<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> getVerticesAndExpandOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> _createRule = this.ruleFactory.<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher>createRule();
      IQuerySpecification<GetVerticesAndExpandOperatorMatcher> _querySpecification = GetVerticesAndExpandOperatorMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<GetVerticesAndExpandOperatorMatch> _function = (GetVerticesAndExpandOperatorMatch it) -> {
        final ExpandOperator expandOperator = it.getExpandOperator();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("getVerticesAndExpandOperatorRule fired for ");
        AbstractEdgeVariable _edgeVariable = expandOperator.getEdgeVariable();
        String _name = _edgeVariable.getName();
        _builder.append(_name, "");
        this.logger.info(_builder.toString());
        GetEdgesOperator _createGetEdgesOperator = this.relalgFactory.createGetEdgesOperator();
        final Procedure1<GetEdgesOperator> _function_1 = (GetEdgesOperator it_1) -> {
          Direction _direction = expandOperator.getDirection();
          Direction _normalizeDirection = this.normalizeDirection(_direction);
          it_1.setDirection(_normalizeDirection);
          VertexVariable _source = this.source(expandOperator);
          it_1.setSourceVertexVariable(_source);
          VertexVariable _target = this.target(expandOperator);
          it_1.setTargetVertexVariable(_target);
          AbstractEdgeVariable _edgeVariable_1 = expandOperator.getEdgeVariable();
          it_1.setEdgeVariable(_edgeVariable_1);
        };
        final GetEdgesOperator getEdgesOperator = ObjectExtensions.<GetEdgesOperator>operator_doubleArrow(_createGetEdgesOperator, _function_1);
        Operator _parentOperator = it.getParentOperator();
        this.changeChildOperator(_parentOperator, expandOperator, getEdgesOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * [2] Replace an 1..1 expand operator with a JoinOperator and a GetEdgesOperator
   */
  protected BatchTransformationRule<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher> defaultExpandOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher> _createRule = this.ruleFactory.<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher>createRule();
      IQuerySpecification<DefaultExpandOperatorMatcher> _querySpecification = DefaultExpandOperatorMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<DefaultExpandOperatorMatch> _function = (DefaultExpandOperatorMatch it) -> {
        final ExpandOperator expandOperator = it.getExpandOperator();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("defaultExpandOperatorRule fired for ");
        AbstractEdgeVariable _edgeVariable = expandOperator.getEdgeVariable();
        String _name = _edgeVariable.getName();
        _builder.append(_name, "");
        this.logger.info(_builder.toString());
        GetEdgesOperator _createGetEdgesOperator = this.relalgFactory.createGetEdgesOperator();
        final Procedure1<GetEdgesOperator> _function_1 = (GetEdgesOperator it_1) -> {
          Direction _direction = expandOperator.getDirection();
          Direction _normalizeDirection = this.normalizeDirection(_direction);
          it_1.setDirection(_normalizeDirection);
          VertexVariable _source = this.source(expandOperator);
          it_1.setSourceVertexVariable(_source);
          VertexVariable _target = this.target(expandOperator);
          it_1.setTargetVertexVariable(_target);
          AbstractEdgeVariable _edgeVariable_1 = expandOperator.getEdgeVariable();
          it_1.setEdgeVariable(_edgeVariable_1);
        };
        final GetEdgesOperator getEdgesOperator = ObjectExtensions.<GetEdgesOperator>operator_doubleArrow(_createGetEdgesOperator, _function_1);
        JoinOperator _createJoinOperator = this.relalgFactory.createJoinOperator();
        final Procedure1<JoinOperator> _function_2 = (JoinOperator it_1) -> {
          Operator _input = expandOperator.getInput();
          it_1.setLeftInput(_input);
          it_1.setRightInput(getEdgesOperator);
        };
        final JoinOperator joinOperator = ObjectExtensions.<JoinOperator>operator_doubleArrow(_createJoinOperator, _function_2);
        Operator _parentOperator = it.getParentOperator();
        this.changeChildOperator(_parentOperator, expandOperator, joinOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * [3] Replace a transitive expand oeprator with a TransitiveClosureJoin and a GetEdgesOperator
   */
  protected BatchTransformationRule<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher> transitiveExpandOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher> _createRule = this.ruleFactory.<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher>createRule();
      IQuerySpecification<TransitiveExpandOperatorMatcher> _querySpecification = TransitiveExpandOperatorMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<TransitiveExpandOperatorMatch> _function = (TransitiveExpandOperatorMatch it) -> {
        final Operator inputOperator = it.getInputOperator();
        final ExpandOperator expandOperator = it.getExpandOperator();
        final EdgeListVariable mEdgeListVariable = it.getEdgeListVariable();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("transitiveExpandOperatorRule fired for ");
        AbstractEdgeVariable _edgeVariable = expandOperator.getEdgeVariable();
        String _name = _edgeVariable.getName();
        _builder.append(_name, "");
        this.logger.info(_builder.toString());
        GetEdgesOperator _createGetEdgesOperator = this.relalgFactory.createGetEdgesOperator();
        final Procedure1<GetEdgesOperator> _function_1 = (GetEdgesOperator it_1) -> {
          Direction _direction = expandOperator.getDirection();
          Direction _normalizeDirection = this.normalizeDirection(_direction);
          it_1.setDirection(_normalizeDirection);
          VertexVariable _source = this.source(expandOperator);
          it_1.setSourceVertexVariable(_source);
          VertexVariable _target = this.target(expandOperator);
          it_1.setTargetVertexVariable(_target);
          EdgeVariable _createEdgeVariable = this.relalgFactory.createEdgeVariable();
          final Procedure1<EdgeVariable> _function_2 = (EdgeVariable it_2) -> {
            AbstractEdgeVariable _edgeVariable_1 = expandOperator.getEdgeVariable();
            RelalgContainer _namedElementContainer = _edgeVariable_1.getNamedElementContainer();
            it_2.setNamedElementContainer(_namedElementContainer);
            AbstractEdgeVariable _edgeVariable_2 = expandOperator.getEdgeVariable();
            String _name_1 = _edgeVariable_2.getName();
            it_2.setName(_name_1);
            AbstractEdgeVariable _edgeVariable_3 = expandOperator.getEdgeVariable();
            EdgeLabelSet _edgeLabelSet = _edgeVariable_3.getEdgeLabelSet();
            it_2.setEdgeLabelSet(_edgeLabelSet);
          };
          EdgeVariable _doubleArrow = ObjectExtensions.<EdgeVariable>operator_doubleArrow(_createEdgeVariable, _function_2);
          it_1.setEdgeVariable(_doubleArrow);
        };
        final GetEdgesOperator getEdgesOperator = ObjectExtensions.<GetEdgesOperator>operator_doubleArrow(_createGetEdgesOperator, _function_1);
        TransitiveClosureJoinOperator _createTransitiveClosureJoinOperator = this.relalgFactory.createTransitiveClosureJoinOperator();
        final Procedure1<TransitiveClosureJoinOperator> _function_2 = (TransitiveClosureJoinOperator it_1) -> {
          it_1.setLeftInput(inputOperator);
          it_1.setRightInput(getEdgesOperator);
          it_1.setEdgeListVariable(mEdgeListVariable);
        };
        final TransitiveClosureJoinOperator tcJoinOperator = ObjectExtensions.<TransitiveClosureJoinOperator>operator_doubleArrow(_createTransitiveClosureJoinOperator, _function_2);
        Operator _parentOperator = it.getParentOperator();
        this.changeChildOperator(_parentOperator, expandOperator, tcJoinOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * [4] Replace an adjacent pair of SortOperator and TopOperator to a single SortAndTopOperator
   */
  protected BatchTransformationRule<SortAndTopOperatorMatch, SortAndTopOperatorMatcher> sortAndTopOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<SortAndTopOperatorMatch, SortAndTopOperatorMatcher> _createRule = this.ruleFactory.<SortAndTopOperatorMatch, SortAndTopOperatorMatcher>createRule();
      IQuerySpecification<SortAndTopOperatorMatcher> _querySpecification = SortAndTopOperatorMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<SortAndTopOperatorMatch, SortAndTopOperatorMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<SortAndTopOperatorMatch> _function = (SortAndTopOperatorMatch it) -> {
        final SortOperator sortOperator = it.getSortOperator();
        final TopOperator topOperator = it.getTopOperator();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("sortAndTopOperatorRule fired for ");
        _builder.append(sortOperator, "");
        _builder.append(" and ");
        _builder.append(topOperator, "");
        this.logger.info(_builder.toString());
        SortAndTopOperator _createSortAndTopOperator = this.relalgFactory.createSortAndTopOperator();
        final Procedure1<SortAndTopOperator> _function_1 = (SortAndTopOperator it_1) -> {
          EList<SortEntry> _entries = it_1.getEntries();
          EList<SortEntry> _entries_1 = sortOperator.getEntries();
          _entries.addAll(_entries_1);
          Expression _skip = topOperator.getSkip();
          it_1.setSkip(_skip);
          Expression _limit = topOperator.getLimit();
          it_1.setLimit(_limit);
          Operator _input = sortOperator.getInput();
          it_1.setInput(_input);
        };
        final SortAndTopOperator sortAndTopOperator = ObjectExtensions.<SortAndTopOperator>operator_doubleArrow(_createSortAndTopOperator, _function_1);
        Operator _parentOperator = it.getParentOperator();
        this.changeChildOperator(_parentOperator, topOperator, sortAndTopOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<SortAndTopOperatorMatch, SortAndTopOperatorMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * [5] Replace a pair of SelectionOperator and LeftOuterJoinOperator to a single AntiJoinOperator
   */
  protected BatchTransformationRule<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> leftOuterJoinAndSelectionRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> _createRule = this.ruleFactory.<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher>createRule();
      IQuerySpecification<LeftOuterJoinAndSelectionMatcher> _querySpecification = LeftOuterJoinAndSelectionMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<LeftOuterJoinAndSelectionMatch> _function = (LeftOuterJoinAndSelectionMatch it) -> {
        final Operator parentOperator = it.getParentOperator();
        final SelectionOperator selectionOperator = it.getSelectionOperator();
        final LeftOuterJoinOperator leftOuterJoinOperator = it.getLeftOuterJoinOperator();
        final Operator leftInputOperator = it.getLeftInputOperator();
        final Operator rightInputOperator = it.getRightInputOperator();
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("leftOuterAndSelectionRule fired for ");
        _builder.append(selectionOperator, "");
        _builder.append(" and ");
        _builder.append(leftOuterJoinOperator, "");
        this.logger.info(_builder.toString());
        AntiJoinOperator _createAntiJoinOperator = this.relalgFactory.createAntiJoinOperator();
        final Procedure1<AntiJoinOperator> _function_1 = (AntiJoinOperator it_1) -> {
          it_1.setLeftInput(leftInputOperator);
          it_1.setRightInput(rightInputOperator);
        };
        final AntiJoinOperator antiJoinOperator = ObjectExtensions.<AntiJoinOperator>operator_doubleArrow(_createAntiJoinOperator, _function_1);
        this.changeChildOperator(parentOperator, selectionOperator, antiJoinOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
