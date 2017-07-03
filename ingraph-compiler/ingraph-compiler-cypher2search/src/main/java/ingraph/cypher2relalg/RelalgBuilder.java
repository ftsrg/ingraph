package ingraph.cypher2relalg;

import com.google.common.collect.Iterables;
import ingraph.cypher2relalg.Cypher2RelalgConfig;
import ingraph.cypher2relalg.VariableBuilder;
import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode;
import ingraph.cypher2relalg.structures.RelalgMatchDescriptor;
import ingraph.cypher2relalg.util.Cypher2RelalgUtil;
import ingraph.cypher2relalg.util.ExpressionNameInferencer;
import ingraph.cypher2relalg.util.GrammarUtil;
import ingraph.cypher2relalg.util.StringUtil;
import ingraph.cypher2relalg.util.Validator;
import ingraph.emf.util.PrettyPrinter;
import ingraph.logger.IngraphLogger;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slizaa.neo4j.opencypher.openCypher.AllShortestPaths;
import org.slizaa.neo4j.opencypher.openCypher.AnonymousPatternPart;
import org.slizaa.neo4j.opencypher.openCypher.CaseAlternatives;
import org.slizaa.neo4j.opencypher.openCypher.CaseExpression;
import org.slizaa.neo4j.opencypher.openCypher.Clause;
import org.slizaa.neo4j.opencypher.openCypher.ContainsExpression;
import org.slizaa.neo4j.opencypher.openCypher.Count;
import org.slizaa.neo4j.opencypher.openCypher.Create;
import org.slizaa.neo4j.opencypher.openCypher.Cypher;
import org.slizaa.neo4j.opencypher.openCypher.Delete;
import org.slizaa.neo4j.opencypher.openCypher.EndsWithExpression;
import org.slizaa.neo4j.opencypher.openCypher.Expression;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionList;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower;
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor;
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation;
import org.slizaa.neo4j.opencypher.openCypher.FunctionName;
import org.slizaa.neo4j.opencypher.openCypher.InCollectionExpression;
import org.slizaa.neo4j.opencypher.openCypher.IndexExpression;
import org.slizaa.neo4j.opencypher.openCypher.IsNotNullExpression;
import org.slizaa.neo4j.opencypher.openCypher.IsNullExpression;
import org.slizaa.neo4j.opencypher.openCypher.Limit;
import org.slizaa.neo4j.opencypher.openCypher.MapLiteral;
import org.slizaa.neo4j.opencypher.openCypher.MapLiteralEntry;
import org.slizaa.neo4j.opencypher.openCypher.Match;
import org.slizaa.neo4j.opencypher.openCypher.NodePattern;
import org.slizaa.neo4j.opencypher.openCypher.NullConstant;
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant;
import org.slizaa.neo4j.opencypher.openCypher.Order;
import org.slizaa.neo4j.opencypher.openCypher.Parameter;
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression;
import org.slizaa.neo4j.opencypher.openCypher.Pattern;
import org.slizaa.neo4j.opencypher.openCypher.PatternElement;
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain;
import org.slizaa.neo4j.opencypher.openCypher.PatternPart;
import org.slizaa.neo4j.opencypher.openCypher.Properties;
import org.slizaa.neo4j.opencypher.openCypher.RegExpMatchingExpression;
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipPattern;
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern;
import org.slizaa.neo4j.opencypher.openCypher.Return;
import org.slizaa.neo4j.opencypher.openCypher.ReturnBody;
import org.slizaa.neo4j.opencypher.openCypher.ReturnItem;
import org.slizaa.neo4j.opencypher.openCypher.ReturnItems;
import org.slizaa.neo4j.opencypher.openCypher.ShortestPath;
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery;
import org.slizaa.neo4j.opencypher.openCypher.Skip;
import org.slizaa.neo4j.opencypher.openCypher.SortItem;
import org.slizaa.neo4j.opencypher.openCypher.StartsWithExpression;
import org.slizaa.neo4j.opencypher.openCypher.Statement;
import org.slizaa.neo4j.opencypher.openCypher.StringConstant;
import org.slizaa.neo4j.opencypher.openCypher.Union;
import org.slizaa.neo4j.opencypher.openCypher.Unwind;
import org.slizaa.neo4j.opencypher.openCypher.Variable;
import org.slizaa.neo4j.opencypher.openCypher.VariableRef;
import org.slizaa.neo4j.opencypher.openCypher.Where;
import org.slizaa.neo4j.opencypher.openCypher.With;
import relalg.AbstractEdgeVariable;
import relalg.AllDifferentOperator;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticComparisonOperatorType;
import relalg.ArithmeticExpression;
import relalg.Atom;
import relalg.AttributeVariable;
import relalg.BigIntegerLiteral;
import relalg.BinaryArithmeticOperationExpression;
import relalg.BinaryArithmeticOperatorType;
import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperatorType;
import relalg.CUDOperator;
import relalg.Case;
import relalg.ComparableExpression;
import relalg.CreateOperator;
import relalg.DeleteOperator;
import relalg.Direction;
import relalg.DoubleLiteral;
import relalg.DualObjectSourceOperator;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.EmptyListExpression;
import relalg.ExpandOperator;
import relalg.ExpressionVariable;
import relalg.FunctionArithmeticExpression;
import relalg.FunctionComparableExpression;
import relalg.FunctionExpression;
import relalg.FunctionLogicalExpression;
import relalg.GenericCaseExpression;
import relalg.GetVerticesOperator;
import relalg.GroupingOperator;
import relalg.IndexAccessExpression;
import relalg.IndexRangeAccessExpression;
import relalg.IndexSimpleAccessExpression;
import relalg.IntegerLiteral;
import relalg.JoinOperator;
import relalg.LeftOuterJoinOperator;
import relalg.ListExpression;
import relalg.LogicalExpression;
import relalg.NavigationDescriptor;
import relalg.NullLiteral;
import relalg.NumberLiteral;
import relalg.Operator;
import relalg.OrderDirection;
import relalg.ParameterComparableExpression;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.PropertyList;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.SelectionOperator;
import relalg.SimpleCaseExpression;
import relalg.SortEntry;
import relalg.SortOperator;
import relalg.StringLiteral;
import relalg.ThetaLeftOuterJoinOperator;
import relalg.TopOperator;
import relalg.UnaryGraphObjectLogicalExpression;
import relalg.UnaryGraphObjectLogicalOperatorType;
import relalg.UnaryLogicalExpression;
import relalg.UnaryLogicalOperatorType;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.UnwindOperator;
import relalg.VariableArithmeticExpression;
import relalg.VariableComparableExpression;
import relalg.VariableExpression;
import relalg.VertexVariable;
import relalg.function.CypherType;
import relalg.function.Function;

/**
 * This is the main class of the openCypher to relational algebra compiler.
 * 
 * Its main entry point is the {@link #build(Cypher) build} method.
 * As a helper class to manage the whole process of openCypher parsing and compilation,
 * you might want to see the helper methods in the {@link Cypher2Relalg} class.
 */
@SuppressWarnings("all")
public class RelalgBuilder {
  @Extension
  private RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  @Extension
  private IngraphLogger logger = new IngraphLogger(RelalgBuilder.class.getName());
  
  @Extension
  private Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(this.logger);
  
  private final RelalgContainer topLevelContainer;
  
  private final VariableBuilder variableBuilder;
  
  /**
   * Constructs a new RelalgBuilder object and initialize its state.
   * 
   * For the internals, it creates a new EMF container and a fresh variable builder
   * with new variable and label factories.
   */
  public RelalgBuilder() {
    RelalgContainer _createRelalgContainer = this.factory.createRelalgContainer();
    this.topLevelContainer = _createRelalgContainer;
    VariableBuilder _variableBuilder = new VariableBuilder(this.topLevelContainer, this.logger);
    this.variableBuilder = _variableBuilder;
  }
  
  /**
   * Constructor that allows passing the topLevelContainer and the
   * variable builder instance to be used.
   * 
   * Use this to create separate builder for each SingleQuery,
   * and usually you might want to pass a clone of your variable builder created using its
   * {@link VariableBuilder#cloneBuilderWithNewVariableFactories cloneBuilderWithNewVariableFactories}
   * call.
   */
  protected RelalgBuilder(final RelalgContainer topLevelContainer, final VariableBuilder variableBuilder) {
    this.topLevelContainer = topLevelContainer;
    this.variableBuilder = variableBuilder;
  }
  
  public RelalgContainer build(final Cypher cypher, final String queryName) {
    RelalgContainer _xblockexpression = null;
    {
      EcoreUtil.resolveAll(cypher);
      boolean _isDebugMode = Cypher2RelalgConfig.isDebugMode();
      if (_isDebugMode) {
        String _format = PrettyPrinter.format(cypher);
        InputOutput.<String>println(_format);
      }
      final Statement statement = cypher.getStatement();
      ProductionOperator _createProductionOperator = this.factory.createProductionOperator();
      final Procedure1<ProductionOperator> _function = (ProductionOperator it) -> {
        Operator _buildRelalg = this.buildRelalg(statement);
        it.setInput(_buildRelalg);
      };
      ProductionOperator _doubleArrow = ObjectExtensions.<ProductionOperator>operator_doubleArrow(_createProductionOperator, _function);
      this.topLevelContainer.setRootExpression(_doubleArrow);
      _xblockexpression = this.topLevelContainer;
    }
    return _xblockexpression;
  }
  
  @Deprecated
  public RelalgContainer build(final Cypher cypher) {
    return this.build(cypher, null);
  }
  
  protected Operator _buildRelalg(final RegularQuery q) {
    Operator _xblockexpression = null;
    {
      RegularQuery _singleQuery = q.getSingleQuery();
      final Operator queryListHead = this.buildRelalg(_singleQuery);
      EList<Union> _union = q.getUnion();
      List<UnionOperator> _map = null;
      if (_union!=null) {
        final Function1<Union, UnionOperator> _function = (Union it) -> {
          UnionOperator _xblockexpression_1 = null;
          {
            final Union mapIt = it;
            UnionOperator _createUnionOperator = this.factory.createUnionOperator();
            final Procedure1<UnionOperator> _function_1 = (UnionOperator it_1) -> {
              boolean _isAll = mapIt.isAll();
              it_1.setBag(_isAll);
              SingleQuery _singleQuery_1 = mapIt.getSingleQuery();
              Operator _buildRelalg = this.buildRelalg(_singleQuery_1);
              it_1.setRightInput(_buildRelalg);
            };
            _xblockexpression_1 = ObjectExtensions.<UnionOperator>operator_doubleArrow(_createUnionOperator, _function_1);
          }
          return _xblockexpression_1;
        };
        _map=ListExtensions.<Union, UnionOperator>map(_union, _function);
      }
      final List<UnionOperator> queryListTail = _map;
      final Operator result = this.cypher2RelalgUtil.chainBinaryOperatorsLeft(queryListHead, queryListTail);
      boolean _checkIfUnionQueryColumnNamesMatch = Validator.checkIfUnionQueryColumnNamesMatch(result, this.logger);
      boolean _not = (!_checkIfUnionQueryColumnNamesMatch);
      if (_not) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("All sub queries of a UNION query must have the same column aliases.");
        this.logger.unrecoverableError(_builder.toString());
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * Builds the relational algebra expression for a single query by spawning a new builder
   * with the same top-level container, but with own factories for variables.
   */
  protected Operator _buildRelalg(final SingleQuery q) {
    Operator _xblockexpression = null;
    {
      final EList<Clause> clauses = q.getClauses();
      final EList<Operator> ops = new BasicEList<Operator>();
      Validator.checkSingleQueryClauseSequence(clauses, this.logger);
      int from = 0;
      VariableBuilder variableBuilderChain = this.variableBuilder;
      for (int i = 0; (i < ((Object[])Conversions.unwrapArray(clauses, Object.class)).length); i++) {
        {
          final Clause current = clauses.get(i);
          Clause _xifexpression = null;
          int _length = ((Object[])Conversions.unwrapArray(clauses, Object.class)).length;
          boolean _lessThan = ((i + 1) < _length);
          if (_lessThan) {
            _xifexpression = clauses.get((i + 1));
          }
          final Clause next = _xifexpression;
          if ((((((GrammarUtil.isCudClause(current) && (!GrammarUtil.isCudClause(next))) && (!(next instanceof Return))) || ((current instanceof With) && (!(next instanceof Unwind)))) || (current instanceof Unwind)) || (current instanceof Return))) {
            final int fromX = from;
            final int toX = (i + 1);
            VariableBuilder _cloneBuilderWithNewVariableFactories = variableBuilderChain.cloneBuilderWithNewVariableFactories();
            variableBuilderChain = _cloneBuilderWithNewVariableFactories;
            final RelalgBuilder builder = new RelalgBuilder(this.topLevelContainer, variableBuilderChain);
            List<Clause> _subList = clauses.subList(fromX, toX);
            Operator __buildRelalgSubQuery = builder._buildRelalgSubQuery(_subList);
            ops.add(__buildRelalgSubQuery);
            from = (i + 1);
          }
        }
      }
      DualObjectSourceOperator _createDualObjectSourceOperator = this.factory.createDualObjectSourceOperator();
      _xblockexpression = this.cypher2RelalgUtil.chainEncapsulatedBinaryOperatorsLeft(_createDualObjectSourceOperator, ops, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC);
    }
    return _xblockexpression;
  }
  
  /**
   * This is the workhorse for building the relational algebra expression for a single subquery.
   * 
   * It should not be called to build more than one single subquery as
   * the variables produced for re-used names would collide.
   */
  protected Operator _buildRelalgSubQuery(final List<Clause> clauses) {
    Operator _xblockexpression = null;
    {
      Iterable<Match> _filter = Iterables.<Match>filter(clauses, Match.class);
      Validator.checkSubQueryMatchClauseSequence(_filter, this.logger);
      Validator.checkSubQueryClauseSequence(clauses, this.logger);
      Iterable<Match> _filter_1 = Iterables.<Match>filter(clauses, Match.class);
      final Function1<Match, Operator> _function = (Match it) -> {
        Operator _xblockexpression_1 = null;
        {
          final Match mapIt = it;
          final RelalgMatchDescriptor relalgMatchDescriptor = this.buildRelalgMatch(mapIt);
          Operator _xifexpression = null;
          boolean _isOptional = mapIt.isOptional();
          if (_isOptional) {
            LeftOuterJoinOperator _xblockexpression_2 = null;
            {
              LeftOuterJoinOperator _xifexpression_1 = null;
              LogicalExpression _condition = relalgMatchDescriptor.getCondition();
              boolean _tripleEquals = (_condition == null);
              if (_tripleEquals) {
                ThetaLeftOuterJoinOperator _createThetaLeftOuterJoinOperator = this.factory.createThetaLeftOuterJoinOperator();
                final Procedure1<ThetaLeftOuterJoinOperator> _function_1 = (ThetaLeftOuterJoinOperator it_1) -> {
                  LogicalExpression _condition_1 = relalgMatchDescriptor.getCondition();
                  it_1.setCondition(_condition_1);
                };
                _xifexpression_1 = ObjectExtensions.<ThetaLeftOuterJoinOperator>operator_doubleArrow(_createThetaLeftOuterJoinOperator, _function_1);
              } else {
                _xifexpression_1 = this.factory.createLeftOuterJoinOperator();
              }
              final LeftOuterJoinOperator lojo = _xifexpression_1;
              final Procedure1<LeftOuterJoinOperator> _function_2 = (LeftOuterJoinOperator it_1) -> {
                Operator _op = relalgMatchDescriptor.getOp();
                it_1.setRightInput(_op);
              };
              _xblockexpression_2 = ObjectExtensions.<LeftOuterJoinOperator>operator_doubleArrow(lojo, _function_2);
            }
            _xifexpression = _xblockexpression_2;
          } else {
            Operator _xblockexpression_3 = null;
            {
              JoinOperator _createJoinOperator = this.factory.createJoinOperator();
              final Procedure1<JoinOperator> _function_1 = (JoinOperator it_1) -> {
                Operator _op = relalgMatchDescriptor.getOp();
                it_1.setRightInput(_op);
              };
              final JoinOperator join = ObjectExtensions.<JoinOperator>operator_doubleArrow(_createJoinOperator, _function_1);
              Operator _xifexpression_1 = null;
              LogicalExpression _condition = relalgMatchDescriptor.getCondition();
              boolean _tripleEquals = (_condition == null);
              if (_tripleEquals) {
                _xifexpression_1 = join;
              } else {
                SelectionOperator _createSelectionOperator = this.factory.createSelectionOperator();
                final Procedure1<SelectionOperator> _function_2 = (SelectionOperator it_1) -> {
                  it_1.setInput(join);
                  LogicalExpression _condition_1 = relalgMatchDescriptor.getCondition();
                  it_1.setCondition(_condition_1);
                };
                _xifexpression_1 = ObjectExtensions.<SelectionOperator>operator_doubleArrow(_createSelectionOperator, _function_2);
              }
              _xblockexpression_3 = _xifexpression_1;
            }
            _xifexpression = _xblockexpression_3;
          }
          _xblockexpression_1 = _xifexpression;
        }
        return _xblockexpression_1;
      };
      final Iterable<Operator> singleQuery_MatchList = IterableExtensions.<Match, Operator>map(_filter_1, _function);
      Operator _xifexpression = null;
      boolean _isEmpty = IterableExtensions.isEmpty(singleQuery_MatchList);
      if (_isEmpty) {
        JoinOperator _createJoinOperator = this.factory.createJoinOperator();
        final Procedure1<JoinOperator> _function_1 = (JoinOperator it) -> {
          DualObjectSourceOperator _createDualObjectSourceOperator = this.factory.createDualObjectSourceOperator();
          it.setRightInput(_createDualObjectSourceOperator);
        };
        _xifexpression = ObjectExtensions.<JoinOperator>operator_doubleArrow(_createJoinOperator, _function_1);
      } else {
        _xifexpression = this.cypher2RelalgUtil.chainEncapsulatedBinaryOperatorsLeft(null, singleQuery_MatchList, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_BINARY_OPERATOR);
      }
      final Operator content = _xifexpression;
      final Function1<Clause, Boolean> _function_2 = (Clause it) -> {
        return Boolean.valueOf(((it instanceof With) || (it instanceof Return)));
      };
      Iterable<Clause> _filter_2 = IterableExtensions.<Clause>filter(clauses, _function_2);
      final Clause singleQuery_returnOrWithClause = IterableExtensions.<Clause>head(_filter_2);
      Operator _xifexpression_1 = null;
      if ((singleQuery_returnOrWithClause != null)) {
        _xifexpression_1 = this.buildRelalgReturn(singleQuery_returnOrWithClause, content);
      } else {
        _xifexpression_1 = content;
      }
      final Operator afterReturn = _xifexpression_1;
      Iterable<Unwind> _filter_3 = Iterables.<Unwind>filter(clauses, Unwind.class);
      final Unwind singleQuery_unwindClauseList = IterableExtensions.<Unwind>head(_filter_3);
      Operator _xifexpression_2 = null;
      if ((singleQuery_unwindClauseList != null)) {
        UnwindOperator _xblockexpression_1 = null;
        {
          final Unwind u0 = singleQuery_unwindClauseList;
          UnwindOperator _createUnwindOperator = this.factory.createUnwindOperator();
          final Procedure1<UnwindOperator> _function_3 = (UnwindOperator it) -> {
            Variable _variable = u0.getVariable();
            String _name = _variable.getName();
            Expression _expression = u0.getExpression();
            relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_expression);
            ExpressionVariable _buildExpressionVariable = this.variableBuilder.buildExpressionVariable(_name, _buildRelalgExpression);
            it.setElement(_buildExpressionVariable);
            it.setInput(afterReturn);
          };
          _xblockexpression_1 = ObjectExtensions.<UnwindOperator>operator_doubleArrow(_createUnwindOperator, _function_3);
        }
        _xifexpression_2 = _xblockexpression_1;
      } else {
        _xifexpression_2 = afterReturn;
      }
      final Operator afterUnwind = _xifexpression_2;
      Operator _xblockexpression_2 = null;
      {
        Operator op = afterUnwind;
        final Function1<Clause, Boolean> _function_3 = (Clause it) -> {
          return Boolean.valueOf(GrammarUtil.isCudClause(it));
        };
        Iterable<Clause> _filter_4 = IterableExtensions.<Clause>filter(clauses, _function_3);
        for (final Clause cudClause : _filter_4) {
          CUDOperator _switchResult = null;
          boolean _matched = false;
          if (cudClause instanceof Create) {
            _matched=true;
            _switchResult = this.buildCreateOperator(((Create)cudClause), op);
          }
          if (!_matched) {
            if (cudClause instanceof Delete) {
              _matched=true;
              _switchResult = this.buildDeleteOperator(((Delete)cudClause), op);
            }
          }
          if (!_matched) {
            Object _xblockexpression_3 = null;
            {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("Currently we only support CREATE and DELETE of the possible CUD operations. Found: ");
              Class<? extends Clause> _class = cudClause.getClass();
              String _name = _class.getName();
              _builder.append(_name, "");
              _builder.append(".");
              this.logger.unsupported(_builder.toString());
              _xblockexpression_3 = null;
            }
            _switchResult = ((CUDOperator)_xblockexpression_3);
          }
          op = _switchResult;
        }
        _xblockexpression_2 = op;
      }
      final Operator afterCud = _xblockexpression_2;
      _xblockexpression = afterCud;
    }
    return _xblockexpression;
  }
  
  /**
   * Build and return a create operator from the CREATE clause and attach p_input to its input.
   */
  protected CreateOperator buildCreateOperator(final Create u0, final Operator p_input) {
    CreateOperator _xblockexpression = null;
    {
      CreateOperator _createCreateOperator = this.factory.createCreateOperator();
      final Procedure1<CreateOperator> _function = (CreateOperator it) -> {
        it.setInput(p_input);
      };
      final CreateOperator u1 = ObjectExtensions.<CreateOperator>operator_doubleArrow(_createCreateOperator, _function);
      Pattern _pattern = u0.getPattern();
      EList<PatternPart> _patterns = _pattern.getPatterns();
      for (final PatternPart _u2 : _patterns) {
        {
          final PatternElement u2 = ((PatternElement) _u2);
          if ((u2 == null)) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("PatternElement expected at create, but received ");
            Class<? extends PatternPart> _class = _u2.getClass();
            String _name = _class.getName();
            _builder.append(_name, "");
            this.logger.unrecoverableError(_builder.toString());
          }
          HashMap<String, VertexVariable> _vertexVariableFactoryElements = this.variableBuilder.getVertexVariableFactoryElements();
          NodePattern _nodepattern = u2.getNodepattern();
          Variable _variable = _nodepattern.getVariable();
          String _name_1 = null;
          if (_variable!=null) {
            _name_1=_variable.getName();
          }
          final boolean t0 = _vertexVariableFactoryElements.containsKey(_name_1);
          NodePattern _nodepattern_1 = u2.getNodepattern();
          final ExpressionVariable u4 = this.buildCreateNodePattern(_nodepattern_1);
          if ((!t0)) {
            EList<ExpressionVariable> _elements = u1.getElements();
            _elements.add(u4);
          }
          relalg.Expression _expression = u4.getExpression();
          relalg.Variable _variable_1 = ((VariableExpression) _expression).getVariable();
          VertexVariable lastVertexVariable = ((VertexVariable) _variable_1);
          EList<PatternElementChain> _chain = u2.getChain();
          for (final PatternElementChain element : _chain) {
            {
              HashMap<String, VertexVariable> _vertexVariableFactoryElements_1 = this.variableBuilder.getVertexVariableFactoryElements();
              NodePattern _nodePattern = element.getNodePattern();
              Variable _variable_2 = _nodePattern.getVariable();
              String _name_2 = null;
              if (_variable_2!=null) {
                _name_2=_variable_2.getName();
              }
              final boolean t1 = _vertexVariableFactoryElements_1.containsKey(_name_2);
              NodePattern _nodePattern_1 = element.getNodePattern();
              final ExpressionVariable u5 = this.buildCreateNodePattern(_nodePattern_1);
              if ((!t1)) {
                EList<ExpressionVariable> _elements_1 = u1.getElements();
                _elements_1.add(u5);
              }
              HashMap<String, EdgeVariable> _edgeVariableFactoryElements = this.variableBuilder.getEdgeVariableFactoryElements();
              RelationshipPattern _relationshipPattern = element.getRelationshipPattern();
              RelationshipDetail _detail = _relationshipPattern.getDetail();
              Variable _variable_3 = null;
              if (_detail!=null) {
                _variable_3=_detail.getVariable();
              }
              String _name_3 = null;
              if (_variable_3!=null) {
                _name_3=_variable_3.getName();
              }
              final boolean t2 = _edgeVariableFactoryElements.containsKey(_name_3);
              RelationshipPattern _relationshipPattern_1 = element.getRelationshipPattern();
              relalg.Expression _expression_1 = u5.getExpression();
              relalg.Variable _variable_4 = ((VariableExpression) _expression_1).getVariable();
              final ExpressionVariable u6 = this.buildCreateRelationshipPattern(_relationshipPattern_1, lastVertexVariable, ((VertexVariable) _variable_4));
              if ((!t2)) {
                EList<ExpressionVariable> _elements_2 = u1.getElements();
                _elements_2.add(u6);
              }
              relalg.Expression _expression_2 = u5.getExpression();
              relalg.Variable _variable_5 = ((VariableExpression) _expression_2).getVariable();
              lastVertexVariable = ((VertexVariable) _variable_5);
            }
          }
        }
      }
      _xblockexpression = u1;
    }
    return _xblockexpression;
  }
  
  /**
   * Provide the edges for CREATE operator.
   */
  protected ExpressionVariable buildCreateRelationshipPattern(final RelationshipPattern relationshippattern, final VertexVariable source, final VertexVariable target) {
    ExpressionVariable _xblockexpression = null;
    {
      NavigationDescriptor _createNavigationDescriptor = this.factory.createNavigationDescriptor();
      final Procedure1<NavigationDescriptor> _function = (NavigationDescriptor it) -> {
        RelationshipDetail _detail = relationshippattern.getDetail();
        AbstractEdgeVariable _buildEdgeVariable = this.variableBuilder.buildEdgeVariable(_detail);
        it.setEdgeVariable(_buildEdgeVariable);
        RelationshipDetail _detail_1 = relationshippattern.getDetail();
        Properties _properties = _detail_1.getProperties();
        AbstractEdgeVariable _edgeVariable = it.getEdgeVariable();
        this.buildRelalgProperties(_properties, _edgeVariable);
        it.setSourceVertexVariable(source);
        it.setTargetVertexVariable(target);
        Direction _convertToDirection = this.convertToDirection(relationshippattern);
        it.setDirection(_convertToDirection);
        it.setExpressionContainer(this.topLevelContainer);
      };
      final NavigationDescriptor u0 = ObjectExtensions.<NavigationDescriptor>operator_doubleArrow(_createNavigationDescriptor, _function);
      AbstractEdgeVariable _edgeVariable = u0.getEdgeVariable();
      String _name = _edgeVariable.getName();
      final ExpressionVariable u1 = this.variableBuilder.buildExpressionVariable(_name, u0);
      _xblockexpression = u1;
    }
    return _xblockexpression;
  }
  
  /**
   * Provide the vertices for CREATE operator.
   */
  protected ExpressionVariable buildCreateNodePattern(final NodePattern nodepattern) {
    ExpressionVariable _xblockexpression = null;
    {
      VariableExpression _createVariableExpression = this.factory.createVariableExpression();
      final Procedure1<VariableExpression> _function = (VariableExpression it) -> {
        final VertexVariable vertexVariable = this.variableBuilder.buildVertexVariable(nodepattern);
        Properties _properties = nodepattern.getProperties();
        this.buildRelalgProperties(_properties, vertexVariable);
        it.setVariable(vertexVariable);
        it.setExpressionContainer(this.topLevelContainer);
      };
      final VariableExpression u0 = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
      relalg.Variable _variable = u0.getVariable();
      String _name = _variable.getName();
      final ExpressionVariable u1 = this.variableBuilder.buildExpressionVariable(_name, u0);
      _xblockexpression = u1;
    }
    return _xblockexpression;
  }
  
  /**
   * Build and return a delete operator from the DELETE clause and attach p_input to its input.
   */
  protected DeleteOperator buildDeleteOperator(final Delete element, final Operator p_input) {
    DeleteOperator _xblockexpression = null;
    {
      DeleteOperator _createDeleteOperator = this.factory.createDeleteOperator();
      final Procedure1<DeleteOperator> _function = (DeleteOperator it) -> {
        boolean _isDetach = element.isDetach();
        it.setDetach(_isDetach);
      };
      final DeleteOperator u1 = ObjectExtensions.<DeleteOperator>operator_doubleArrow(_createDeleteOperator, _function);
      u1.setInput(p_input);
      EList<Expression> _expressions = element.getExpressions();
      for (final Expression element2 : _expressions) {
        {
          final VariableRef u2 = ((VariableRef) element2);
          final ExpressionVariable u4 = this.buildDeleteVariableRef(u2);
          EList<ExpressionVariable> _elements = u1.getElements();
          _elements.add(u4);
        }
      }
      _xblockexpression = u1;
    }
    return _xblockexpression;
  }
  
  /**
   * Provide the vertices for DELETE operator.
   */
  protected ExpressionVariable buildDeleteVariableRef(final VariableRef variableref) {
    ExpressionVariable _xblockexpression = null;
    {
      VariableExpression _createVariableExpression = this.factory.createVariableExpression();
      final Procedure1<VariableExpression> _function = (VariableExpression it) -> {
        relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(variableref);
        it.setVariable(_buildRelalgVariable);
        it.setExpressionContainer(this.topLevelContainer);
      };
      final VariableExpression u0 = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function);
      relalg.Variable _variable = u0.getVariable();
      String _name = _variable.getName();
      final ExpressionVariable u1 = this.variableBuilder.buildExpressionVariable(_name, u0);
      _xblockexpression = u1;
    }
    return _xblockexpression;
  }
  
  /**
   * Process the common part of a RETURN and a WITH clause,
   * i.e. the distinct flag and the ReturnBody.
   */
  public UnaryOperator buildRelalgReturnBody(final boolean distinct, final ReturnBody returnBody, final Operator content) {
    UnaryOperator _xblockexpression = null;
    {
      final BasicEList<ExpressionVariable> _elements = new BasicEList<ExpressionVariable>();
      EList<ReturnItems> _returnItems = returnBody.getReturnItems();
      ReturnItems _get = _returnItems.get(0);
      String _all = _get.getAll();
      boolean _equals = "*".equals(_all);
      if (_equals) {
        final HashMap<String, VertexVariable> vEl = this.variableBuilder.getVertexVariableFactoryElements();
        Set<String> _keySet = vEl.keySet();
        List<String> _sort = IterableExtensions.<String>sort(_keySet);
        final Consumer<String> _function = (String key) -> {
          final VertexVariable variable = vEl.get(key);
          boolean _isDontCare = variable.isDontCare();
          boolean _not = (!_isDontCare);
          if (_not) {
            VariableExpression _buildVariableExpression = this.variableBuilder.buildVariableExpression(variable);
            ExpressionVariable _buildExpressionVariable = this.variableBuilder.buildExpressionVariable(
              null, _buildVariableExpression);
            _elements.add(_buildExpressionVariable);
          }
        };
        _sort.forEach(_function);
        final HashMap<String, EdgeVariable> eEl = this.variableBuilder.getEdgeVariableFactoryElements();
        Set<String> _keySet_1 = eEl.keySet();
        List<String> _sort_1 = IterableExtensions.<String>sort(_keySet_1);
        final Consumer<String> _function_1 = (String key) -> {
          final EdgeVariable variable = eEl.get(key);
          boolean _isDontCare = variable.isDontCare();
          boolean _not = (!_isDontCare);
          if (_not) {
            VariableExpression _buildVariableExpression = this.variableBuilder.buildVariableExpression(variable);
            ExpressionVariable _buildExpressionVariable = this.variableBuilder.buildExpressionVariable(
              null, _buildVariableExpression);
            _elements.add(_buildExpressionVariable);
          }
        };
        _sort_1.forEach(_function_1);
        boolean _isEmpty = _elements.isEmpty();
        if (_isEmpty) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("RETURN * encountered but no vertexvariable nor edgevariable found in the query");
          this.logger.warning(_builder.toString());
        }
      }
      EList<ReturnItems> _returnItems_1 = returnBody.getReturnItems();
      ReturnItems _get_1 = _returnItems_1.get(0);
      EList<ReturnItem> _items = _get_1.getItems();
      for (final ReturnItem returnItem : _items) {
        Variable _alias = returnItem.getAlias();
        String _name = null;
        if (_alias!=null) {
          _name=_alias.getName();
        }
        Expression _expression = returnItem.getExpression();
        relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_expression);
        ExpressionVariable _buildExpressionVariable = this.variableBuilder.buildExpressionVariable(_name, _buildRelalgExpression);
        _elements.add(_buildExpressionVariable);
      }
      boolean _isEmpty_1 = _elements.isEmpty();
      if (_isEmpty_1) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("RETURN items processed and resulted in no columns values to return");
        this.logger.unrecoverableError(_builder_1.toString());
      }
      boolean seenAggregate = false;
      final HashSet<relalg.Variable> groupingVariables = new HashSet<relalg.Variable>();
      for (final ExpressionVariable el : _elements) {
        relalg.Expression _expression_1 = el.getExpression();
        boolean _accumulateGroupingVariables = this.cypher2RelalgUtil.accumulateGroupingVariables(_expression_1, groupingVariables, seenAggregate);
        seenAggregate = _accumulateGroupingVariables;
      }
      ProjectionOperator _xifexpression = null;
      if (seenAggregate) {
        GroupingOperator _createGroupingOperator = this.factory.createGroupingOperator();
        final Procedure1<GroupingOperator> _function_2 = (GroupingOperator it) -> {
          EList<relalg.Expression> _aggregationCriteria = it.getAggregationCriteria();
          final Function1<relalg.Variable, VariableExpression> _function_3 = (relalg.Variable it_1) -> {
            VariableExpression _xblockexpression_1 = null;
            {
              final relalg.Variable mapIt = it_1;
              VariableExpression _createVariableExpression = this.factory.createVariableExpression();
              final Procedure1<VariableExpression> _function_4 = (VariableExpression it_2) -> {
                it_2.setVariable(mapIt);
                it_2.setExpressionContainer(this.topLevelContainer);
              };
              _xblockexpression_1 = ObjectExtensions.<VariableExpression>operator_doubleArrow(_createVariableExpression, _function_4);
            }
            return _xblockexpression_1;
          };
          Iterable<VariableExpression> _map = IterableExtensions.<relalg.Variable, VariableExpression>map(groupingVariables, _function_3);
          final Function1<VariableExpression, String> _function_4 = (VariableExpression it_1) -> {
            String _inferName = ExpressionNameInferencer.inferName(it_1, this.logger);
            String _plus = (_inferName + "##");
            Class<? extends VariableExpression> _class = it_1.getClass();
            String _name_1 = _class.getName();
            return (_plus + _name_1);
          };
          List<VariableExpression> _sortBy = IterableExtensions.<VariableExpression, String>sortBy(_map, _function_4);
          _aggregationCriteria.addAll(_sortBy);
        };
        _xifexpression = ObjectExtensions.<GroupingOperator>operator_doubleArrow(_createGroupingOperator, _function_2);
      } else {
        ProjectionOperator _createProjectionOperator = this.factory.createProjectionOperator();
        final Procedure1<ProjectionOperator> _function_3 = (ProjectionOperator it) -> {
          it.setInput(content);
        };
        _xifexpression = ObjectExtensions.<ProjectionOperator>operator_doubleArrow(_createProjectionOperator, _function_3);
      }
      final ProjectionOperator projection = _xifexpression;
      final Procedure1<ProjectionOperator> _function_4 = (ProjectionOperator it) -> {
        it.setInput(content);
        EList<ExpressionVariable> _elements_1 = it.getElements();
        _elements_1.addAll(_elements);
      };
      ObjectExtensions.<ProjectionOperator>operator_doubleArrow(projection, _function_4);
      UnaryOperator _xifexpression_1 = null;
      if (distinct) {
        DuplicateEliminationOperator _createDuplicateEliminationOperator = this.factory.createDuplicateEliminationOperator();
        final Procedure1<DuplicateEliminationOperator> _function_5 = (DuplicateEliminationOperator it) -> {
          it.setInput(projection);
        };
        _xifexpression_1 = ObjectExtensions.<DuplicateEliminationOperator>operator_doubleArrow(_createDuplicateEliminationOperator, _function_5);
      } else {
        _xifexpression_1 = projection;
      }
      final UnaryOperator op1 = _xifexpression_1;
      final Order order = returnBody.getOrder();
      UnaryOperator _xifexpression_2 = null;
      if ((order != null)) {
        SortOperator _xblockexpression_1 = null;
        {
          EList<SortItem> _orderBy = order.getOrderBy();
          final Function1<SortItem, SortEntry> _function_6 = (SortItem it) -> {
            SortEntry _xblockexpression_2 = null;
            {
              OrderDirection _xifexpression_3 = null;
              if (((it.getSort() != null) && it.getSort().startsWith("DESC"))) {
                _xifexpression_3 = OrderDirection.DESCENDING;
              } else {
                _xifexpression_3 = OrderDirection.ASCENDING;
              }
              final OrderDirection sortDirection = _xifexpression_3;
              relalg.Expression _switchResult = null;
              Expression _expression_2 = it.getExpression();
              boolean _matched = false;
              if (_expression_2 instanceof ExpressionNodeLabelsAndPropertyLookup) {
                _matched=true;
                Expression _expression_3 = it.getExpression();
                _switchResult = this.variableBuilder.buildVariableExpression(_expression_3, true);
              }
              if (!_matched) {
                if (_expression_2 instanceof VariableRef) {
                  _matched=true;
                  Expression _expression_3 = it.getExpression();
                  _switchResult = this.variableBuilder.buildVariableExpression(_expression_3, true);
                }
              }
              if (!_matched) {
                Expression _expression_3 = it.getExpression();
                _switchResult = this.buildRelalgExpression(_expression_3);
              }
              final relalg.Expression sortExpression = _switchResult;
              SortEntry _createSortEntry = this.factory.createSortEntry();
              final Procedure1<SortEntry> _function_7 = (SortEntry it_1) -> {
                it_1.setDirection(sortDirection);
                it_1.setExpression(sortExpression);
              };
              _xblockexpression_2 = ObjectExtensions.<SortEntry>operator_doubleArrow(_createSortEntry, _function_7);
            }
            return _xblockexpression_2;
          };
          final List<SortEntry> sortEntries = ListExtensions.<SortItem, SortEntry>map(_orderBy, _function_6);
          SortOperator _createSortOperator = this.factory.createSortOperator();
          final Procedure1<SortOperator> _function_7 = (SortOperator it) -> {
            EList<SortEntry> _entries = it.getEntries();
            _entries.addAll(sortEntries);
            it.setInput(op1);
          };
          final SortOperator sortOperator = ObjectExtensions.<SortOperator>operator_doubleArrow(_createSortOperator, _function_7);
          _xblockexpression_1 = sortOperator;
        }
        _xifexpression_2 = _xblockexpression_1;
      } else {
        _xifexpression_2 = op1;
      }
      final UnaryOperator op2 = _xifexpression_2;
      final Skip skip = returnBody.getSkip();
      final Limit limit = returnBody.getLimit();
      UnaryOperator _xifexpression_3 = null;
      if (((skip != null) || (limit != null))) {
        TopOperator _createTopOperator = this.factory.createTopOperator();
        final Procedure1<TopOperator> _function_6 = (TopOperator it) -> {
          Expression _skip = null;
          if (skip!=null) {
            _skip=skip.getSkip();
          }
          Atom _expressionToSkipLimitConstant = null;
          if (_skip!=null) {
            _expressionToSkipLimitConstant=this.expressionToSkipLimitConstant(_skip);
          }
          it.setSkip(_expressionToSkipLimitConstant);
          Expression _limit = null;
          if (limit!=null) {
            _limit=limit.getLimit();
          }
          Atom _expressionToSkipLimitConstant_1 = null;
          if (_limit!=null) {
            _expressionToSkipLimitConstant_1=this.expressionToSkipLimitConstant(_limit);
          }
          it.setLimit(_expressionToSkipLimitConstant_1);
          it.setInput(op2);
        };
        _xifexpression_3 = ObjectExtensions.<TopOperator>operator_doubleArrow(_createTopOperator, _function_6);
      } else {
        _xifexpression_3 = op2;
      }
      final UnaryOperator op3 = _xifexpression_3;
      _xblockexpression = op3;
    }
    return _xblockexpression;
  }
  
  protected UnaryOperator _buildRelalgReturn(final Return r, final Operator content) {
    boolean _isDistinct = r.isDistinct();
    ReturnBody _body = r.getBody();
    return this.buildRelalgReturnBody(_isDistinct, _body, content);
  }
  
  protected UnaryOperator _buildRelalgReturn(final With w, final Operator content) {
    UnaryOperator _xblockexpression = null;
    {
      boolean _isDistint = w.isDistint();
      ReturnBody _returnBody = w.getReturnBody();
      final UnaryOperator rb = this.buildRelalgReturnBody(_isDistint, _returnBody, content);
      UnaryOperator _xifexpression = null;
      Where _where = w.getWhere();
      boolean _tripleEquals = (_where == null);
      if (_tripleEquals) {
        _xifexpression = rb;
      } else {
        SelectionOperator _xblockexpression_1 = null;
        {
          final EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>();
          SelectionOperator _createSelectionOperator = this.factory.createSelectionOperator();
          final Procedure1<SelectionOperator> _function = (SelectionOperator it) -> {
            it.setInput(rb);
            Where _where_1 = w.getWhere();
            Expression _expression = _where_1.getExpression();
            LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_expression, joinOperationsOfWhereClause);
            it.setCondition(_buildRelalgLogicalExpression);
          };
          final SelectionOperator selectionOperator = ObjectExtensions.<SelectionOperator>operator_doubleArrow(_createSelectionOperator, _function);
          int _length = ((Object[])Conversions.unwrapArray(joinOperationsOfWhereClause, Object.class)).length;
          boolean _tripleNotEquals = (_length != 0);
          if (_tripleNotEquals) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Pattern expression found in WITH ... WHERE, which is unsupported. Consider moveing this expression to MATCH...WHERE.");
            this.logger.unsupported(_builder.toString());
          }
          _xblockexpression_1 = selectionOperator;
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public Atom expressionToSkipLimitConstant(final Expression expression) {
    Atom _switchResult = null;
    boolean _matched = false;
    if (expression instanceof NumberConstant) {
      _matched=true;
      _switchResult = this.buildRelalgNumberLiteral(((NumberConstant)expression));
    }
    if (!_matched) {
      if (expression instanceof Parameter) {
        _matched=true;
        _switchResult = this.buildRelalgParameter(((Parameter)expression));
      }
    }
    if (!_matched) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Only NumberConstants are supported as SKIP/LIMIT values, got ");
      _builder.append(expression, "");
      this.logger.unsupported(_builder.toString());
    }
    return _switchResult;
  }
  
  /**
   * MATCH clause is compiled as follows:
   * (the lower elements being the input for the upper ones)
   * 
   * - Left outer join of the patterns extracted from the where clause (is any)
   * - AllDifferentOperator on the edges in the patternParts
   * - natural join of comma-separated patternParts in the MATCH clause
   * 
   * Also a filter boolean condition is built from the where clause.
   * In case no WHERE condition applies, contiion is null meaning no filtering,
   * i.e. pass through all records.
   * 
   * @returns a RelalgMatchDescriptor,
   *          whose op attribute is the compiled form of the MATCH without the WHERE,
   *          and condition attribute holds the filter condition.
   */
  public RelalgMatchDescriptor buildRelalgMatch(final Match m) {
    RelalgMatchDescriptor _xblockexpression = null;
    {
      final RelalgMatchDescriptor result = new RelalgMatchDescriptor();
      final Set<AbstractEdgeVariable> edgeVariablesOfMatchClause = new HashSet<AbstractEdgeVariable>();
      final EList<Operator> pattern_PatternPartList = new BasicEList<Operator>();
      Pattern _pattern = m.getPattern();
      EList<PatternPart> _patterns = _pattern.getPatterns();
      for (final PatternPart pattern : _patterns) {
        {
          final Operator op = this.buildRelalg(pattern);
          Set<AbstractEdgeVariable> _extractEdgeVariables = this.cypher2RelalgUtil.extractEdgeVariables(op);
          edgeVariablesOfMatchClause.addAll(_extractEdgeVariables);
          pattern_PatternPartList.add(op);
        }
      }
      AllDifferentOperator _createAllDifferentOperator = this.factory.createAllDifferentOperator();
      final Procedure1<AllDifferentOperator> _function = (AllDifferentOperator it) -> {
        Iterator<Operator> _iterator = null;
        if (pattern_PatternPartList!=null) {
          _iterator=pattern_PatternPartList.iterator();
        }
        Operator _buildLeftDeepTree = this.cypher2RelalgUtil.buildLeftDeepTree(JoinOperator.class, _iterator);
        it.setInput(_buildLeftDeepTree);
        EList<AbstractEdgeVariable> _edgeVariables = it.getEdgeVariables();
        _edgeVariables.addAll(edgeVariablesOfMatchClause);
      };
      final AllDifferentOperator allDifferentOperator = ObjectExtensions.<AllDifferentOperator>operator_doubleArrow(_createAllDifferentOperator, _function);
      Where _where = m.getWhere();
      boolean _tripleNotEquals = (_where != null);
      if (_tripleNotEquals) {
        final EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>();
        Where _where_1 = m.getWhere();
        Expression _expression = _where_1.getExpression();
        LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_expression, joinOperationsOfWhereClause);
        result.setCondition(_buildRelalgLogicalExpression);
        Operator _xifexpression = null;
        boolean _isEmpty = joinOperationsOfWhereClause.isEmpty();
        if (_isEmpty) {
          _xifexpression = allDifferentOperator;
        } else {
          Operator _xblockexpression_1 = null;
          {
            final EList<Operator> h = new BasicEList<Operator>();
            h.add(allDifferentOperator);
            h.addAll(joinOperationsOfWhereClause);
            Iterator<Operator> _iterator = h.iterator();
            _xblockexpression_1 = this.cypher2RelalgUtil.buildLeftDeepTree(LeftOuterJoinOperator.class, _iterator);
          }
          _xifexpression = _xblockexpression_1;
        }
        result.setOp(_xifexpression);
      } else {
        result.setOp(allDifferentOperator);
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * This is a wrapper around the buildRelalgLogicalExpression to be used
   * in contexts where no join clauses should be generated.
   * 
   * We use this outside of WHERE clauses.
   */
  public LogicalExpression buildRelalgLogicalExpressionNoJoinAllowed(final Expression e) {
    LogicalExpression _xblockexpression = null;
    {
      final BasicEList<Operator> dummyJoins = new BasicEList<Operator>();
      final LogicalExpression logicalExp = this.buildRelalgLogicalExpression(e, dummyJoins);
      int _size = dummyJoins.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Joins found when building a logical expression in generic expression position.");
        this.logger.unrecoverableError(_builder.toString());
      }
      _xblockexpression = logicalExp;
    }
    return _xblockexpression;
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final ExpressionAnd e, final EList<Operator> joins) {
    BinaryLogicalExpression _createBinaryLogicalExpression = this.factory.createBinaryLogicalExpression();
    final Procedure1<BinaryLogicalExpression> _function = (BinaryLogicalExpression it) -> {
      it.setOperator(BinaryLogicalOperatorType.AND);
      Expression _left = e.getLeft();
      LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_left, joins);
      it.setLeftOperand(_buildRelalgLogicalExpression);
      Expression _right = e.getRight();
      LogicalExpression _buildRelalgLogicalExpression_1 = this.buildRelalgLogicalExpression(_right, joins);
      it.setRightOperand(_buildRelalgLogicalExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryLogicalExpression>operator_doubleArrow(_createBinaryLogicalExpression, _function);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final ExpressionOr e, final EList<Operator> joins) {
    BinaryLogicalExpression _createBinaryLogicalExpression = this.factory.createBinaryLogicalExpression();
    final Procedure1<BinaryLogicalExpression> _function = (BinaryLogicalExpression it) -> {
      it.setOperator(BinaryLogicalOperatorType.OR);
      Expression _left = e.getLeft();
      LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_left, joins);
      it.setLeftOperand(_buildRelalgLogicalExpression);
      Expression _right = e.getRight();
      LogicalExpression _buildRelalgLogicalExpression_1 = this.buildRelalgLogicalExpression(_right, joins);
      it.setRightOperand(_buildRelalgLogicalExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryLogicalExpression>operator_doubleArrow(_createBinaryLogicalExpression, _function);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final ExpressionXor e, final EList<Operator> joins) {
    BinaryLogicalExpression _createBinaryLogicalExpression = this.factory.createBinaryLogicalExpression();
    final Procedure1<BinaryLogicalExpression> _function = (BinaryLogicalExpression it) -> {
      it.setOperator(BinaryLogicalOperatorType.XOR);
      Expression _left = e.getLeft();
      LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_left, joins);
      it.setLeftOperand(_buildRelalgLogicalExpression);
      Expression _right = e.getRight();
      LogicalExpression _buildRelalgLogicalExpression_1 = this.buildRelalgLogicalExpression(_right, joins);
      it.setRightOperand(_buildRelalgLogicalExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryLogicalExpression>operator_doubleArrow(_createBinaryLogicalExpression, _function);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final IsNotNullExpression e, final EList<Operator> joins) {
    UnaryGraphObjectLogicalExpression _createUnaryGraphObjectLogicalExpression = this.factory.createUnaryGraphObjectLogicalExpression();
    final Procedure1<UnaryGraphObjectLogicalExpression> _function = (UnaryGraphObjectLogicalExpression it) -> {
      it.setOperator(UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL);
      Expression _left = e.getLeft();
      relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(_left);
      it.setOperand(_buildRelalgVariable);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<UnaryGraphObjectLogicalExpression>operator_doubleArrow(_createUnaryGraphObjectLogicalExpression, _function);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final IsNullExpression e, final EList<Operator> joins) {
    UnaryGraphObjectLogicalExpression _createUnaryGraphObjectLogicalExpression = this.factory.createUnaryGraphObjectLogicalExpression();
    final Procedure1<UnaryGraphObjectLogicalExpression> _function = (UnaryGraphObjectLogicalExpression it) -> {
      it.setOperator(UnaryGraphObjectLogicalOperatorType.IS_NULL);
      Expression _left = e.getLeft();
      relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(_left);
      it.setOperand(_buildRelalgVariable);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<UnaryGraphObjectLogicalExpression>operator_doubleArrow(_createUnaryGraphObjectLogicalExpression, _function);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final RegExpMatchingExpression e, final EList<Operator> joins) {
    FunctionLogicalExpression _xblockexpression = null;
    {
      FunctionLogicalExpression _createFunctionLogicalExpression = this.factory.createFunctionLogicalExpression();
      final Procedure1<FunctionLogicalExpression> _function = (FunctionLogicalExpression it) -> {
        it.setExpressionContainer(this.topLevelContainer);
      };
      final FunctionLogicalExpression fe = ObjectExtensions.<FunctionLogicalExpression>operator_doubleArrow(_createFunctionLogicalExpression, _function);
      fe.setFunctor(Function.REGEX_LIKE);
      EList<relalg.Expression> _arguments = fe.getArguments();
      Expression _left = e.getLeft();
      relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
      _arguments.add(_buildRelalgExpression);
      EList<relalg.Expression> _arguments_1 = fe.getArguments();
      Expression _right = e.getRight();
      relalg.Expression _buildRelalgExpression_1 = this.buildRelalgExpression(_right);
      _arguments_1.add(_buildRelalgExpression_1);
      _xblockexpression = fe;
    }
    return _xblockexpression;
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final Expression e, final EList<Operator> joins) {
    UnaryLogicalExpression _switchResult = null;
    String _operator = e.getOperator();
    String _lowerCase = _operator.toLowerCase();
    switch (_lowerCase) {
      case "not":
        UnaryLogicalExpression _createUnaryLogicalExpression = this.factory.createUnaryLogicalExpression();
        final Procedure1<UnaryLogicalExpression> _function = (UnaryLogicalExpression it) -> {
          it.setOperator(UnaryLogicalOperatorType.NOT);
          Expression _left = e.getLeft();
          LogicalExpression _buildRelalgLogicalExpression = this.buildRelalgLogicalExpression(_left, joins);
          it.setOperand(_buildRelalgLogicalExpression);
          it.setExpressionContainer(this.topLevelContainer);
        };
        _switchResult = ObjectExtensions.<UnaryLogicalExpression>operator_doubleArrow(_createUnaryLogicalExpression, _function);
        break;
      default:
        Object _xblockexpression = null;
        {
          String _operator_1 = e.getOperator();
          String _plus = ("TODO: " + _operator_1);
          this.logger.unsupported(_plus);
          _xblockexpression = null;
        }
        _switchResult = ((UnaryLogicalExpression)_xblockexpression);
        break;
    }
    return _switchResult;
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final ParenthesizedExpression e, final EList<Operator> joins) {
    Expression _expression = e.getExpression();
    return this.buildRelalgLogicalExpression(_expression, joins);
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final RelationshipsPattern e, final EList<Operator> joins) {
    LogicalExpression _xblockexpression = null;
    {
      final EList<LogicalExpression> relationshipVariableExpressions = new BasicEList<LogicalExpression>();
      UnaryGraphObjectLogicalExpression _createUnaryGraphObjectLogicalExpression = this.factory.createUnaryGraphObjectLogicalExpression();
      final Procedure1<UnaryGraphObjectLogicalExpression> _function = (UnaryGraphObjectLogicalExpression it) -> {
        it.setOperator(UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL);
        NodePattern _nodePattern = e.getNodePattern();
        VertexVariable _buildVertexVariable = this.variableBuilder.buildVertexVariable(_nodePattern);
        it.setOperand(_buildVertexVariable);
        it.setExpressionContainer(this.topLevelContainer);
      };
      UnaryGraphObjectLogicalExpression _doubleArrow = ObjectExtensions.<UnaryGraphObjectLogicalExpression>operator_doubleArrow(_createUnaryGraphObjectLogicalExpression, _function);
      relationshipVariableExpressions.add(_doubleArrow);
      EList<PatternElementChain> _chain = e.getChain();
      final Function1<PatternElementChain, UnaryGraphObjectLogicalExpression> _function_1 = (PatternElementChain it) -> {
        UnaryGraphObjectLogicalExpression _xblockexpression_1 = null;
        {
          final PatternElementChain mapIt = it;
          UnaryGraphObjectLogicalExpression _createUnaryGraphObjectLogicalExpression_1 = this.factory.createUnaryGraphObjectLogicalExpression();
          final Procedure1<UnaryGraphObjectLogicalExpression> _function_2 = (UnaryGraphObjectLogicalExpression it_1) -> {
            it_1.setOperator(UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL);
            RelationshipPattern _relationshipPattern = mapIt.getRelationshipPattern();
            RelationshipDetail _detail = _relationshipPattern.getDetail();
            AbstractEdgeVariable _buildEdgeVariable = this.variableBuilder.buildEdgeVariable(_detail);
            it_1.setOperand(_buildEdgeVariable);
            it_1.setExpressionContainer(this.topLevelContainer);
          };
          _xblockexpression_1 = ObjectExtensions.<UnaryGraphObjectLogicalExpression>operator_doubleArrow(_createUnaryGraphObjectLogicalExpression_1, _function_2);
        }
        return _xblockexpression_1;
      };
      List<UnaryGraphObjectLogicalExpression> _map = ListExtensions.<PatternElementChain, UnaryGraphObjectLogicalExpression>map(_chain, _function_1);
      relationshipVariableExpressions.addAll(_map);
      EList<PatternElementChain> _chain_1 = e.getChain();
      final Function1<PatternElementChain, UnaryGraphObjectLogicalExpression> _function_2 = (PatternElementChain it) -> {
        UnaryGraphObjectLogicalExpression _xblockexpression_1 = null;
        {
          final PatternElementChain mapIt = it;
          UnaryGraphObjectLogicalExpression _createUnaryGraphObjectLogicalExpression_1 = this.factory.createUnaryGraphObjectLogicalExpression();
          final Procedure1<UnaryGraphObjectLogicalExpression> _function_3 = (UnaryGraphObjectLogicalExpression it_1) -> {
            it_1.setOperator(UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL);
            NodePattern _nodePattern = mapIt.getNodePattern();
            VertexVariable _buildVertexVariable = this.variableBuilder.buildVertexVariable(_nodePattern);
            it_1.setOperand(_buildVertexVariable);
            it_1.setExpressionContainer(this.topLevelContainer);
          };
          _xblockexpression_1 = ObjectExtensions.<UnaryGraphObjectLogicalExpression>operator_doubleArrow(_createUnaryGraphObjectLogicalExpression_1, _function_3);
        }
        return _xblockexpression_1;
      };
      List<UnaryGraphObjectLogicalExpression> _map_1 = ListExtensions.<PatternElementChain, UnaryGraphObjectLogicalExpression>map(_chain_1, _function_2);
      relationshipVariableExpressions.addAll(_map_1);
      Operator _buildRelalg = this.buildRelalg(e);
      joins.add(_buildRelalg);
      Iterator<LogicalExpression> _iterator = relationshipVariableExpressions.iterator();
      _xblockexpression = this.cypher2RelalgUtil.buildLeftDeepTree(BinaryLogicalOperatorType.AND, _iterator, this.topLevelContainer);
    }
    return _xblockexpression;
  }
  
  protected LogicalExpression _buildRelalgLogicalExpression(final ExpressionComparison e, final EList<Operator> joins) {
    ArithmeticComparisonExpression _createArithmeticComparisonExpression = this.factory.createArithmeticComparisonExpression();
    final Procedure1<ArithmeticComparisonExpression> _function = (ArithmeticComparisonExpression it) -> {
      ArithmeticComparisonOperatorType _switchResult = null;
      String _operator = e.getOperator();
      switch (_operator) {
        case "=":
          _switchResult = ArithmeticComparisonOperatorType.EQUAL_TO;
          break;
        case "<>":
          _switchResult = ArithmeticComparisonOperatorType.NOT_EQUAL_TO;
          break;
        case "<":
          _switchResult = ArithmeticComparisonOperatorType.LESS_THAN;
          break;
        case "<=":
          _switchResult = ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL;
          break;
        case ">":
          _switchResult = ArithmeticComparisonOperatorType.GREATER_THAN;
          break;
        case ">=":
          _switchResult = ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL;
          break;
      }
      it.setOperator(_switchResult);
      Expression _left = e.getLeft();
      ComparableExpression _buildRelalgComparableElement = this.buildRelalgComparableElement(_left);
      it.setLeftOperand(_buildRelalgComparableElement);
      Expression _right = e.getRight();
      ComparableExpression _buildRelalgComparableElement_1 = this.buildRelalgComparableElement(_right);
      it.setRightOperand(_buildRelalgComparableElement_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<ArithmeticComparisonExpression>operator_doubleArrow(_createArithmeticComparisonExpression, _function);
  }
  
  /**
   * Processes STARTS WITH create a function invocation: STARTS_WITH(string, prefixString)
   */
  protected LogicalExpression _buildRelalgLogicalExpression(final StartsWithExpression e, final EList<Operator> joins) {
    FunctionLogicalExpression _createFunctionLogicalExpression = this.factory.createFunctionLogicalExpression();
    final Procedure1<FunctionLogicalExpression> _function = (FunctionLogicalExpression it) -> {
      it.setFunctor(Function.STARTS_WITH);
      EList<relalg.Expression> _arguments = it.getArguments();
      Expression _left = e.getLeft();
      relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
      _arguments.add(_buildRelalgExpression);
      EList<relalg.Expression> _arguments_1 = it.getArguments();
      Expression _right = e.getRight();
      relalg.Expression _buildRelalgExpression_1 = this.buildRelalgExpression(_right);
      _arguments_1.add(_buildRelalgExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionLogicalExpression>operator_doubleArrow(_createFunctionLogicalExpression, _function);
  }
  
  /**
   * Processes ENDS WITH create a function invocation: ENDS_WITH(string, postfixString)
   */
  protected LogicalExpression _buildRelalgLogicalExpression(final EndsWithExpression e, final EList<Operator> joins) {
    FunctionLogicalExpression _createFunctionLogicalExpression = this.factory.createFunctionLogicalExpression();
    final Procedure1<FunctionLogicalExpression> _function = (FunctionLogicalExpression it) -> {
      it.setFunctor(Function.ENDS_WITH);
      EList<relalg.Expression> _arguments = it.getArguments();
      Expression _left = e.getLeft();
      relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
      _arguments.add(_buildRelalgExpression);
      EList<relalg.Expression> _arguments_1 = it.getArguments();
      Expression _right = e.getRight();
      relalg.Expression _buildRelalgExpression_1 = this.buildRelalgExpression(_right);
      _arguments_1.add(_buildRelalgExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionLogicalExpression>operator_doubleArrow(_createFunctionLogicalExpression, _function);
  }
  
  /**
   * Processes CONTAINS by creating a function invocation: CONTAINS(string, middleString)
   */
  protected LogicalExpression _buildRelalgLogicalExpression(final ContainsExpression e, final EList<Operator> joins) {
    FunctionLogicalExpression _createFunctionLogicalExpression = this.factory.createFunctionLogicalExpression();
    final Procedure1<FunctionLogicalExpression> _function = (FunctionLogicalExpression it) -> {
      it.setFunctor(Function.CONTAINS);
      EList<relalg.Expression> _arguments = it.getArguments();
      Expression _left = e.getLeft();
      relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
      _arguments.add(_buildRelalgExpression);
      EList<relalg.Expression> _arguments_1 = it.getArguments();
      Expression _right = e.getRight();
      relalg.Expression _buildRelalgExpression_1 = this.buildRelalgExpression(_right);
      _arguments_1.add(_buildRelalgExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionLogicalExpression>operator_doubleArrow(_createFunctionLogicalExpression, _function);
  }
  
  /**
   * Processes IN by creating a function invocation: IN_COLLECTION(ANY, LIST expression)
   */
  protected LogicalExpression _buildRelalgLogicalExpression(final InCollectionExpression e, final EList<Operator> joins) {
    FunctionLogicalExpression _createFunctionLogicalExpression = this.factory.createFunctionLogicalExpression();
    final Procedure1<FunctionLogicalExpression> _function = (FunctionLogicalExpression it) -> {
      it.setFunctor(Function.IN_COLLECTION);
      EList<relalg.Expression> _arguments = it.getArguments();
      Expression _left = e.getLeft();
      relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
      _arguments.add(_buildRelalgExpression);
      EList<relalg.Expression> _arguments_1 = it.getArguments();
      Expression _right = e.getRight();
      relalg.Expression _buildRelalgExpression_1 = this.buildRelalgExpression(_right);
      _arguments_1.add(_buildRelalgExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionLogicalExpression>operator_doubleArrow(_createFunctionLogicalExpression, _function);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final Parameter e) {
    ParameterComparableExpression _createParameterComparableExpression = this.factory.createParameterComparableExpression();
    final Procedure1<ParameterComparableExpression> _function = (ParameterComparableExpression it) -> {
      relalg.Parameter _createParameter = this.factory.createParameter();
      final Procedure1<relalg.Parameter> _function_1 = (relalg.Parameter it_1) -> {
        String _parameter = e.getParameter();
        it_1.setName(_parameter);
        it_1.setExpressionContainer(this.topLevelContainer);
      };
      relalg.Parameter _doubleArrow = ObjectExtensions.<relalg.Parameter>operator_doubleArrow(_createParameter, _function_1);
      it.setParameter(_doubleArrow);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<ParameterComparableExpression>operator_doubleArrow(_createParameterComparableExpression, _function);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final NumberConstant e) {
    return this.buildRelalgNumberLiteral(e);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final StringConstant e) {
    return this.buildRelalgStringLiteral(e);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final VariableRef e) {
    VariableComparableExpression _createVariableComparableExpression = this.factory.createVariableComparableExpression();
    final Procedure1<VariableComparableExpression> _function = (VariableComparableExpression it) -> {
      relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(e);
      it.setVariable(_buildRelalgVariable);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<VariableComparableExpression>operator_doubleArrow(_createVariableComparableExpression, _function);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final ExpressionPlusMinus e) {
    return this.buildRelalgArithmeticExpression(e);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final ExpressionMulDiv e) {
    return this.buildRelalgArithmeticExpression(e);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final ExpressionPower e) {
    return this.buildRelalgArithmeticExpression(e);
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final ExpressionNodeLabelsAndPropertyLookup e) {
    VariableComparableExpression _xblockexpression = null;
    {
      final relalg.Variable x = this.variableBuilder.buildRelalgVariable(e);
      VariableComparableExpression _xifexpression = null;
      if ((x instanceof AttributeVariable)) {
        VariableComparableExpression _createVariableComparableExpression = this.factory.createVariableComparableExpression();
        final Procedure1<VariableComparableExpression> _function = (VariableComparableExpression it) -> {
          it.setVariable(x);
          it.setExpressionContainer(this.topLevelContainer);
        };
        _xifexpression = ObjectExtensions.<VariableComparableExpression>operator_doubleArrow(_createVariableComparableExpression, _function);
      } else {
        Object _xblockexpression_1 = null;
        {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Unsupported type received: ");
          Class<? extends relalg.Variable> _class = x.getClass();
          String _name = _class.getName();
          _builder.append(_name, "");
          this.logger.unsupported(_builder.toString());
          _xblockexpression_1 = null;
        }
        _xifexpression = ((VariableComparableExpression)_xblockexpression_1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected ComparableExpression _buildRelalgComparableElement(final FunctionInvocation fi) {
    FunctionComparableExpression _xblockexpression = null;
    {
      FunctionComparableExpression _createFunctionComparableExpression = this.factory.createFunctionComparableExpression();
      final Procedure1<FunctionComparableExpression> _function = (FunctionComparableExpression it) -> {
        it.setExpressionContainer(this.topLevelContainer);
      };
      final FunctionComparableExpression fe = ObjectExtensions.<FunctionComparableExpression>operator_doubleArrow(_createFunctionComparableExpression, _function);
      this.populateFunctionExpression(fe, fi);
      _xblockexpression = fe;
    }
    return _xblockexpression;
  }
  
  protected relalg.Expression _buildRelalgExpression(final VariableRef e) {
    return this.variableBuilder.buildVariableExpression(e, false);
  }
  
  protected relalg.Expression _buildRelalgExpression(final StringConstant e) {
    return this.buildRelalgStringLiteral(e);
  }
  
  protected relalg.Expression _buildRelalgExpression(final CaseExpression e) {
    relalg.CaseExpression _xblockexpression = null;
    {
      CaseExpression _expression = e.getExpression();
      boolean _not = (!(_expression instanceof CaseExpression));
      if (_not) {
        this.logger.unrecoverableError("Outer CaseExpressions should contain a CaseExpression");
      }
      CaseExpression _expression_1 = e.getExpression();
      final CaseExpression ce = ((CaseExpression) _expression_1);
      boolean isSimple = false;
      relalg.CaseExpression _xifexpression = null;
      Expression _caseExpression = ce.getCaseExpression();
      boolean _tripleEquals = (_caseExpression == null);
      if (_tripleEquals) {
        GenericCaseExpression _createGenericCaseExpression = this.factory.createGenericCaseExpression();
        final Procedure1<GenericCaseExpression> _function = (GenericCaseExpression it) -> {
          it.setExpressionContainer(this.topLevelContainer);
        };
        _xifexpression = ObjectExtensions.<GenericCaseExpression>operator_doubleArrow(_createGenericCaseExpression, _function);
      } else {
        SimpleCaseExpression _xblockexpression_1 = null;
        {
          isSimple = true;
          SimpleCaseExpression _createSimpleCaseExpression = this.factory.createSimpleCaseExpression();
          final Procedure1<SimpleCaseExpression> _function_1 = (SimpleCaseExpression it) -> {
            it.setExpressionContainer(this.topLevelContainer);
            Expression _caseExpression_1 = ce.getCaseExpression();
            ComparableExpression _buildRelalgComparableElement = this.buildRelalgComparableElement(_caseExpression_1);
            it.setTest(_buildRelalgComparableElement);
          };
          _xblockexpression_1 = ObjectExtensions.<SimpleCaseExpression>operator_doubleArrow(_createSimpleCaseExpression, _function_1);
        }
        _xifexpression = _xblockexpression_1;
      }
      final relalg.CaseExpression retVal = _xifexpression;
      EList<CaseAlternatives> _caseAlternatives = ce.getCaseAlternatives();
      for (final CaseAlternatives ca : _caseAlternatives) {
        {
          Case _createCase = this.factory.createCase();
          final Procedure1<Case> _function_1 = (Case it) -> {
            Expression _then = ca.getThen();
            relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_then);
            it.setThen(_buildRelalgExpression);
          };
          final Case case_ = ObjectExtensions.<Case>operator_doubleArrow(_createCase, _function_1);
          if (isSimple) {
            Expression _when = ca.getWhen();
            ComparableExpression _buildRelalgComparableElement = this.buildRelalgComparableElement(_when);
            case_.setWhen(_buildRelalgComparableElement);
          } else {
            Expression _when_1 = ca.getWhen();
            LogicalExpression _buildRelalgLogicalExpressionNoJoinAllowed = this.buildRelalgLogicalExpressionNoJoinAllowed(_when_1);
            case_.setWhen(_buildRelalgLogicalExpressionNoJoinAllowed);
          }
          EList<Case> _cases = retVal.getCases();
          _cases.add(case_);
        }
      }
      Expression _elseExpression = ce.getElseExpression();
      boolean _tripleNotEquals = (_elseExpression != null);
      if (_tripleNotEquals) {
        Expression _elseExpression_1 = ce.getElseExpression();
        relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_elseExpression_1);
        retVal.setFallback(_buildRelalgExpression);
      }
      _xblockexpression = retVal;
    }
    return _xblockexpression;
  }
  
  protected relalg.Expression _buildRelalgExpression(final FunctionInvocation fi) {
    FunctionExpression _xblockexpression = null;
    {
      FunctionExpression _createFunctionExpression = this.factory.createFunctionExpression();
      final Procedure1<FunctionExpression> _function = (FunctionExpression it) -> {
        it.setExpressionContainer(this.topLevelContainer);
      };
      final FunctionExpression fe = ObjectExtensions.<FunctionExpression>operator_doubleArrow(_createFunctionExpression, _function);
      this.populateFunctionExpression(fe, fi);
      _xblockexpression = fe;
    }
    return _xblockexpression;
  }
  
  protected relalg.Expression _buildRelalgExpression(final Count fi) {
    FunctionExpression _createFunctionExpression = this.factory.createFunctionExpression();
    final Procedure1<FunctionExpression> _function = (FunctionExpression it) -> {
      it.setFunctor(Function.COUNT_ALL);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionExpression>operator_doubleArrow(_createFunctionExpression, _function);
  }
  
  protected relalg.Expression _buildRelalgExpression(final ExpressionNodeLabelsAndPropertyLookup e) {
    return this.variableBuilder.buildVariableExpression(e, false);
  }
  
  protected relalg.Expression _buildRelalgExpression(final IndexExpression ie) {
    IndexAccessExpression _xblockexpression = null;
    {
      IndexAccessExpression retVal = null;
      Expression _expression = ie.getExpression();
      boolean _tripleEquals = (_expression == null);
      if (_tripleEquals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Index lookup expression found having null as subscript.");
        this.logger.unrecoverableError(_builder.toString());
      }
      Expression _expression_1 = ie.getExpression();
      if ((_expression_1 instanceof NumberConstant)) {
        Expression _upper = ie.getUpper();
        boolean _tripleEquals_1 = (_upper == null);
        if (_tripleEquals_1) {
          IndexSimpleAccessExpression _createIndexSimpleAccessExpression = this.factory.createIndexSimpleAccessExpression();
          final Procedure1<IndexSimpleAccessExpression> _function = (IndexSimpleAccessExpression it) -> {
            Expression _expression_2 = ie.getExpression();
            int _buildRelalgNumber = this.buildRelalgNumber(((NumberConstant) _expression_2));
            it.setIdx(_buildRelalgNumber);
          };
          IndexSimpleAccessExpression _doubleArrow = ObjectExtensions.<IndexSimpleAccessExpression>operator_doubleArrow(_createIndexSimpleAccessExpression, _function);
          retVal = _doubleArrow;
        } else {
          Expression _upper_1 = ie.getUpper();
          if ((_upper_1 instanceof NumberConstant)) {
            IndexRangeAccessExpression _createIndexRangeAccessExpression = this.factory.createIndexRangeAccessExpression();
            final Procedure1<IndexRangeAccessExpression> _function_1 = (IndexRangeAccessExpression it) -> {
              Expression _expression_2 = ie.getExpression();
              int _buildRelalgNumber = this.buildRelalgNumber(((NumberConstant) _expression_2));
              it.setLower(_buildRelalgNumber);
              Expression _upper_2 = ie.getUpper();
              int _buildRelalgNumber_1 = this.buildRelalgNumber(((NumberConstant) _upper_2));
              it.setUpper(_buildRelalgNumber_1);
            };
            IndexRangeAccessExpression _doubleArrow_1 = ObjectExtensions.<IndexRangeAccessExpression>operator_doubleArrow(_createIndexRangeAccessExpression, _function_1);
            retVal = _doubleArrow_1;
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("Index lookup expression should have numeric subscript but found ");
            Expression _upper_2 = ie.getUpper();
            Class<? extends Expression> _class = _upper_2.getClass();
            String _name = _class.getName();
            _builder_1.append(_name, "");
            _builder_1.append(".");
            this.logger.unrecoverableError(_builder_1.toString());
          }
        }
      } else {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("Index lookup expression should have numeric subscript but found ");
        Expression _expression_2 = ie.getExpression();
        Class<? extends Expression> _class_1 = _expression_2.getClass();
        String _name_1 = _class_1.getName();
        _builder_2.append(_name_1, "");
        _builder_2.append(".");
        this.logger.unrecoverableError(_builder_2.toString());
      }
      final Procedure1<IndexAccessExpression> _function_2 = (IndexAccessExpression it) -> {
        Expression _left = ie.getLeft();
        relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(_left);
        it.setList(_buildRelalgExpression);
        it.setExpressionContainer(this.topLevelContainer);
      };
      _xblockexpression = ObjectExtensions.<IndexAccessExpression>operator_doubleArrow(retVal, _function_2);
    }
    return _xblockexpression;
  }
  
  protected relalg.Expression _buildRelalgExpression(final ExpressionList el) {
    ListExpression _xblockexpression = null;
    {
      EmptyListExpression _createEmptyListExpression = this.factory.createEmptyListExpression();
      final Procedure1<EmptyListExpression> _function = (EmptyListExpression it) -> {
        it.setHead(null);
        it.setTail(null);
        it.setExpressionContainer(this.topLevelContainer);
      };
      final EmptyListExpression emptyList = ObjectExtensions.<EmptyListExpression>operator_doubleArrow(_createEmptyListExpression, _function);
      ListExpression _createListExpression = this.factory.createListExpression();
      final Procedure1<ListExpression> _function_1 = (ListExpression it) -> {
        it.setTail(emptyList);
      };
      final ListExpression first = ObjectExtensions.<ListExpression>operator_doubleArrow(_createListExpression, _function_1);
      ListExpression recent = first;
      EList<Expression> _expressions = el.getExpressions();
      for (final Expression e : _expressions) {
        {
          ListExpression _createListExpression_1 = this.factory.createListExpression();
          final Procedure1<ListExpression> _function_2 = (ListExpression it) -> {
            relalg.Expression _buildRelalgExpression = this.buildRelalgExpression(e);
            it.setHead(_buildRelalgExpression);
            it.setTail(emptyList);
            it.setExpressionContainer(this.topLevelContainer);
          };
          ListExpression _doubleArrow = ObjectExtensions.<ListExpression>operator_doubleArrow(_createListExpression_1, _function_2);
          recent.setTail(_doubleArrow);
          ListExpression _tail = recent.getTail();
          recent = _tail;
        }
      }
      _xblockexpression = first.getTail();
    }
    return _xblockexpression;
  }
  
  protected relalg.Expression _buildRelalgExpression(final ExpressionComparison e) {
    return this.buildRelalgLogicalExpressionNoJoinAllowed(e);
  }
  
  /**
   * Catch-all to pass on calls to more-specific methods
   */
  protected relalg.Expression _buildRelalgExpression(final Expression e) {
    ArithmeticExpression _switchResult = null;
    boolean _matched = false;
    if (e instanceof NumberConstant) {
      _matched=true;
    }
    if (!_matched) {
      if (e instanceof ExpressionPlusMinus) {
        _matched=true;
      }
    }
    if (!_matched) {
      if (e instanceof ExpressionMulDiv) {
        _matched=true;
      }
    }
    if (!_matched) {
      if (e instanceof ExpressionPower) {
        _matched=true;
      }
    }
    if (_matched) {
      _switchResult = this.buildRelalgArithmeticExpression(e);
    }
    if (!_matched) {
      if (e instanceof NullConstant) {
        _matched=true;
        NullLiteral _createNullLiteral = this.factory.createNullLiteral();
        final Procedure1<NullLiteral> _function = (NullLiteral it) -> {
          it.setExpressionContainer(this.topLevelContainer);
        };
        _switchResult = ObjectExtensions.<NullLiteral>operator_doubleArrow(_createNullLiteral, _function);
      }
    }
    if (!_matched) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Unhandled parameter types: ");
      List<Object> _asList = Arrays.<Object>asList(e);
      _builder.append(_asList, "");
      throw new IllegalArgumentException(_builder.toString());
    }
    return _switchResult;
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final ExpressionPlusMinus e) {
    BinaryArithmeticOperationExpression _createBinaryArithmeticOperationExpression = this.factory.createBinaryArithmeticOperationExpression();
    final Procedure1<BinaryArithmeticOperationExpression> _function = (BinaryArithmeticOperationExpression it) -> {
      BinaryArithmeticOperatorType _switchResult = null;
      String _operator = e.getOperator();
      switch (_operator) {
        case "+":
          _switchResult = BinaryArithmeticOperatorType.PLUS;
          break;
        case "-":
          _switchResult = BinaryArithmeticOperatorType.MINUS;
          break;
      }
      it.setOperator(_switchResult);
      Expression _left = e.getLeft();
      ArithmeticExpression _buildRelalgArithmeticExpression = this.buildRelalgArithmeticExpression(_left);
      it.setLeftOperand(_buildRelalgArithmeticExpression);
      Expression _right = e.getRight();
      ArithmeticExpression _buildRelalgArithmeticExpression_1 = this.buildRelalgArithmeticExpression(_right);
      it.setRightOperand(_buildRelalgArithmeticExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryArithmeticOperationExpression>operator_doubleArrow(_createBinaryArithmeticOperationExpression, _function);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final ParenthesizedExpression e) {
    Expression _expression = e.getExpression();
    return this.buildRelalgArithmeticExpression(_expression);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final ExpressionMulDiv e) {
    BinaryArithmeticOperationExpression _createBinaryArithmeticOperationExpression = this.factory.createBinaryArithmeticOperationExpression();
    final Procedure1<BinaryArithmeticOperationExpression> _function = (BinaryArithmeticOperationExpression it) -> {
      BinaryArithmeticOperatorType _switchResult = null;
      String _operator = e.getOperator();
      switch (_operator) {
        case "*":
          _switchResult = BinaryArithmeticOperatorType.MULTIPLICATION;
          break;
        case "/":
          _switchResult = BinaryArithmeticOperatorType.DIVISION;
          break;
        case "%":
          _switchResult = BinaryArithmeticOperatorType.MOD;
          break;
      }
      it.setOperator(_switchResult);
      Expression _left = e.getLeft();
      ArithmeticExpression _buildRelalgArithmeticExpression = this.buildRelalgArithmeticExpression(_left);
      it.setLeftOperand(_buildRelalgArithmeticExpression);
      Expression _right = e.getRight();
      ArithmeticExpression _buildRelalgArithmeticExpression_1 = this.buildRelalgArithmeticExpression(_right);
      it.setRightOperand(_buildRelalgArithmeticExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryArithmeticOperationExpression>operator_doubleArrow(_createBinaryArithmeticOperationExpression, _function);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final ExpressionPower e) {
    BinaryArithmeticOperationExpression _createBinaryArithmeticOperationExpression = this.factory.createBinaryArithmeticOperationExpression();
    final Procedure1<BinaryArithmeticOperationExpression> _function = (BinaryArithmeticOperationExpression it) -> {
      BinaryArithmeticOperatorType _switchResult = null;
      String _operator = e.getOperator();
      switch (_operator) {
        case "^":
          _switchResult = BinaryArithmeticOperatorType.POWER;
          break;
      }
      it.setOperator(_switchResult);
      Expression _left = e.getLeft();
      ArithmeticExpression _buildRelalgArithmeticExpression = this.buildRelalgArithmeticExpression(_left);
      it.setLeftOperand(_buildRelalgArithmeticExpression);
      Expression _right = e.getRight();
      ArithmeticExpression _buildRelalgArithmeticExpression_1 = this.buildRelalgArithmeticExpression(_right);
      it.setRightOperand(_buildRelalgArithmeticExpression_1);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<BinaryArithmeticOperationExpression>operator_doubleArrow(_createBinaryArithmeticOperationExpression, _function);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final NumberConstant e) {
    return this.buildRelalgNumberLiteral(e);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final ExpressionNodeLabelsAndPropertyLookup e) {
    VariableArithmeticExpression _createVariableArithmeticExpression = this.factory.createVariableArithmeticExpression();
    final Procedure1<VariableArithmeticExpression> _function = (VariableArithmeticExpression it) -> {
      relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(e);
      it.setVariable(_buildRelalgVariable);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<VariableArithmeticExpression>operator_doubleArrow(_createVariableArithmeticExpression, _function);
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final VariableRef e) {
    VariableArithmeticExpression _xblockexpression = null;
    {
      VariableArithmeticExpression _createVariableArithmeticExpression = this.factory.createVariableArithmeticExpression();
      final Procedure1<VariableArithmeticExpression> _function = (VariableArithmeticExpression it) -> {
        relalg.Variable _buildRelalgVariable = this.variableBuilder.buildRelalgVariable(e);
        it.setVariable(_buildRelalgVariable);
        it.setExpressionContainer(this.topLevelContainer);
      };
      final VariableArithmeticExpression ae = ObjectExtensions.<VariableArithmeticExpression>operator_doubleArrow(_createVariableArithmeticExpression, _function);
      relalg.Variable _variable = ae.getVariable();
      if ((_variable instanceof ElementVariable)) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Unsupported variable of type ");
        Class<? extends VariableArithmeticExpression> _class = ae.getClass();
        String _name = _class.getName();
        _builder.append(_name, "");
        _builder.append(" found in an arithmetic expression.");
        this.logger.unsupported(_builder.toString());
      }
      _xblockexpression = ae;
    }
    return _xblockexpression;
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final FunctionInvocation fi) {
    FunctionArithmeticExpression _xblockexpression = null;
    {
      FunctionArithmeticExpression _createFunctionArithmeticExpression = this.factory.createFunctionArithmeticExpression();
      final Procedure1<FunctionArithmeticExpression> _function = (FunctionArithmeticExpression it) -> {
        it.setExpressionContainer(this.topLevelContainer);
      };
      final FunctionArithmeticExpression fe = ObjectExtensions.<FunctionArithmeticExpression>operator_doubleArrow(_createFunctionArithmeticExpression, _function);
      this.populateFunctionExpression(fe, fi);
      Function _functor = fe.getFunctor();
      boolean _mightBeNumericValued = _functor.mightBeNumericValued();
      boolean _not = (!_mightBeNumericValued);
      if (_not) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Expected numeric valued function, found ");
        Function _functor_1 = fe.getFunctor();
        _builder.append(_functor_1, "");
        _builder.append(" with output type ");
        Function _functor_2 = fe.getFunctor();
        CypherType _outputType = _functor_2.getOutputType();
        _builder.append(_outputType, "");
        this.logger.warning(_builder.toString());
      }
      _xblockexpression = fe;
    }
    return _xblockexpression;
  }
  
  protected ArithmeticExpression _buildRelalgArithmeticExpression(final Count fi) {
    FunctionArithmeticExpression _createFunctionArithmeticExpression = this.factory.createFunctionArithmeticExpression();
    final Procedure1<FunctionArithmeticExpression> _function = (FunctionArithmeticExpression it) -> {
      it.setFunctor(Function.COUNT_ALL);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<FunctionArithmeticExpression>operator_doubleArrow(_createFunctionArithmeticExpression, _function);
  }
  
  public int buildRelalgNumber(final NumberConstant e) {
    int _xblockexpression = (int) 0;
    {
      int n = 0;
      try {
        String _value = e.getValue();
        int _parseInt = Integer.parseInt(_value);
        n = _parseInt;
      } catch (final Throwable _t) {
        if (_t instanceof NumberFormatException) {
          final NumberFormatException ex1 = (NumberFormatException)_t;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Unable to parse ");
          String _value_1 = e.getValue();
          _builder.append(_value_1, "");
          _builder.append(" as integer.");
          this.logger.unrecoverableError(_builder.toString());
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = n;
    }
    return _xblockexpression;
  }
  
  public NumberLiteral buildRelalgNumberLiteral(final NumberConstant e) {
    NumberLiteral _xtrycatchfinallyexpression = null;
    try {
      IntegerLiteral _xblockexpression = null;
      {
        String _value = e.getValue();
        final long n = Long.parseLong(_value);
        IntegerLiteral _createIntegerLiteral = this.factory.createIntegerLiteral();
        final Procedure1<IntegerLiteral> _function = (IntegerLiteral it) -> {
          it.setValue(n);
          it.setExpressionContainer(this.topLevelContainer);
        };
        _xblockexpression = ObjectExtensions.<IntegerLiteral>operator_doubleArrow(_createIntegerLiteral, _function);
      }
      _xtrycatchfinallyexpression = _xblockexpression;
    } catch (final Throwable _t) {
      if (_t instanceof NumberFormatException) {
        final NumberFormatException ex1 = (NumberFormatException)_t;
        NumberLiteral _xtrycatchfinallyexpression_1 = null;
        try {
          BigIntegerLiteral _xblockexpression_1 = null;
          {
            String _value = e.getValue();
            final BigInteger n = new BigInteger(_value);
            BigIntegerLiteral _createBigIntegerLiteral = this.factory.createBigIntegerLiteral();
            final Procedure1<BigIntegerLiteral> _function = (BigIntegerLiteral it) -> {
              it.setValue(n);
              it.setExpressionContainer(this.topLevelContainer);
            };
            _xblockexpression_1 = ObjectExtensions.<BigIntegerLiteral>operator_doubleArrow(_createBigIntegerLiteral, _function);
          }
          _xtrycatchfinallyexpression_1 = _xblockexpression_1;
        } catch (final Throwable _t_1) {
          if (_t_1 instanceof NumberFormatException) {
            final NumberFormatException ex2 = (NumberFormatException)_t_1;
            DoubleLiteral _createDoubleLiteral = this.factory.createDoubleLiteral();
            final Procedure1<DoubleLiteral> _function = (DoubleLiteral it) -> {
              String _value = e.getValue();
              double _parseDouble = Double.parseDouble(_value);
              it.setValue(_parseDouble);
              it.setExpressionContainer(this.topLevelContainer);
            };
            _xtrycatchfinallyexpression_1 = ObjectExtensions.<DoubleLiteral>operator_doubleArrow(_createDoubleLiteral, _function);
          } else {
            throw Exceptions.sneakyThrow(_t_1);
          }
        }
        _xtrycatchfinallyexpression = _xtrycatchfinallyexpression_1;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return _xtrycatchfinallyexpression;
  }
  
  public StringLiteral buildRelalgStringLiteral(final StringConstant e) {
    StringLiteral _createStringLiteral = this.factory.createStringLiteral();
    final Procedure1<StringLiteral> _function = (StringLiteral it) -> {
      String _value = e.getValue();
      String _unescapeCypherString = StringUtil.unescapeCypherString(_value, this.logger);
      it.setValue(_unescapeCypherString);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<StringLiteral>operator_doubleArrow(_createStringLiteral, _function);
  }
  
  public relalg.Parameter buildRelalgParameter(final Parameter expression) {
    relalg.Parameter _createParameter = this.factory.createParameter();
    final Procedure1<relalg.Parameter> _function = (relalg.Parameter it) -> {
      String _parameter = expression.getParameter();
      it.setName(_parameter);
      it.setExpressionContainer(this.topLevelContainer);
    };
    return ObjectExtensions.<relalg.Parameter>operator_doubleArrow(_createParameter, _function);
  }
  
  protected Operator _buildRelalg(final PatternPart p) {
    Operator _xblockexpression = null;
    {
      AnonymousPatternPart _part = p.getPart();
      if ((_part instanceof ShortestPath)) {
      }
      AnonymousPatternPart _part_1 = p.getPart();
      if ((_part_1 instanceof AllShortestPaths)) {
      }
      Variable _var = p.getVar();
      boolean _tripleNotEquals = (_var != null);
      if (_tripleNotEquals) {
        this.logger.unsupported("Variable assignment not supported for PatternPart (in MATCH clause)");
      }
      AnonymousPatternPart _part_2 = p.getPart();
      _xblockexpression = this.buildRelalg(_part_2);
    }
    return _xblockexpression;
  }
  
  protected Operator _buildRelalg(final PatternElement e) {
    NodePattern _nodepattern = e.getNodepattern();
    EList<PatternElementChain> _chain = e.getChain();
    return this.buildRelalgFromPattern(_nodepattern, _chain);
  }
  
  protected Operator _buildRelalg(final RelationshipsPattern e) {
    NodePattern _nodePattern = e.getNodePattern();
    EList<PatternElementChain> _chain = e.getChain();
    return this.buildRelalgFromPattern(_nodePattern, _chain);
  }
  
  /**
   * This will create the relational algebraic representation of a patternElement.
   * 
   * This was factored out to handle PatternElement and RelationshipsPattern in the same code
   */
  public Operator buildRelalgFromPattern(final NodePattern n, final EList<PatternElementChain> chain) {
    Operator _xblockexpression = null;
    {
      GetVerticesOperator _createGetVerticesOperator = this.factory.createGetVerticesOperator();
      final Procedure1<GetVerticesOperator> _function = (GetVerticesOperator it) -> {
        VertexVariable _buildVertexVariable = this.variableBuilder.buildVertexVariable(n);
        it.setVertexVariable(_buildVertexVariable);
        Properties _properties = n.getProperties();
        VertexVariable _vertexVariable = it.getVertexVariable();
        this.buildRelalgProperties(_properties, _vertexVariable);
      };
      final GetVerticesOperator patternElement_GetVerticesOperator = ObjectExtensions.<GetVerticesOperator>operator_doubleArrow(_createGetVerticesOperator, _function);
      final Function1<PatternElementChain, ExpandOperator> _function_1 = (PatternElementChain it) -> {
        Operator _buildRelalg = this.buildRelalg(it);
        return ((ExpandOperator) _buildRelalg);
      };
      final List<ExpandOperator> patternElement_ExpandList = ListExtensions.<PatternElementChain, ExpandOperator>map(chain, _function_1);
      _xblockexpression = this.cypher2RelalgUtil.chainExpandOperators(patternElement_GetVerticesOperator, patternElement_ExpandList);
    }
    return _xblockexpression;
  }
  
  protected Operator _buildRelalg(final PatternElementChain ec) {
    ExpandOperator _xblockexpression = null;
    {
      NodePattern _nodePattern = ec.getNodePattern();
      VertexVariable _buildVertexVariable = this.variableBuilder.buildVertexVariable(_nodePattern);
      final Procedure1<VertexVariable> _function = (VertexVariable it) -> {
        NodePattern _nodePattern_1 = ec.getNodePattern();
        Properties _properties = _nodePattern_1.getProperties();
        this.buildRelalgProperties(_properties, it);
      };
      final VertexVariable patternElementChain_VertexVariable = ObjectExtensions.<VertexVariable>operator_doubleArrow(_buildVertexVariable, _function);
      RelationshipPattern _relationshipPattern = ec.getRelationshipPattern();
      RelationshipDetail _detail = _relationshipPattern.getDetail();
      AbstractEdgeVariable _buildEdgeVariable = this.variableBuilder.buildEdgeVariable(_detail);
      final Procedure1<AbstractEdgeVariable> _function_1 = (AbstractEdgeVariable it) -> {
        RelationshipPattern _relationshipPattern_1 = ec.getRelationshipPattern();
        RelationshipDetail _detail_1 = _relationshipPattern_1.getDetail();
        Properties _properties = null;
        if (_detail_1!=null) {
          _properties=_detail_1.getProperties();
        }
        this.buildRelalgProperties(_properties, it);
      };
      final AbstractEdgeVariable patternElementChain_EdgeVariable = ObjectExtensions.<AbstractEdgeVariable>operator_doubleArrow(_buildEdgeVariable, _function_1);
      ExpandOperator _createExpandOperator = this.factory.createExpandOperator();
      final Procedure1<ExpandOperator> _function_2 = (ExpandOperator it) -> {
        it.setEdgeVariable(patternElementChain_EdgeVariable);
        RelationshipPattern _relationshipPattern_1 = ec.getRelationshipPattern();
        Direction _convertToDirection = this.convertToDirection(_relationshipPattern_1);
        it.setDirection(_convertToDirection);
        it.setTargetVertexVariable(patternElementChain_VertexVariable);
      };
      _xblockexpression = ObjectExtensions.<ExpandOperator>operator_doubleArrow(_createExpandOperator, _function_2);
    }
    return _xblockexpression;
  }
  
  public void populateFunctionExpression(final FunctionExpression fe, final FunctionInvocation fi) {
    FunctionName _functionName = fi.getFunctionName();
    String _name = _functionName.getName();
    String _upperCase = _name.toUpperCase();
    Function _valueOf = Function.valueOf(_upperCase);
    fe.setFunctor(_valueOf);
    EList<relalg.Expression> _arguments = fe.getArguments();
    EList<Expression> _parameter = fi.getParameter();
    final Function1<Expression, relalg.Expression> _function = (Expression it) -> {
      return this.buildRelalgExpression(it);
    };
    List<relalg.Expression> _map = ListExtensions.<Expression, relalg.Expression>map(_parameter, _function);
    _arguments.addAll(_map);
  }
  
  /**
   * Parse map-like constraints if given
   * and attach to the ElementVariable in certain cases.
   * 
   * FIXME: attach to the VertexVariable if in a MATCH or CREATE context
   * otherwise, selection operators should be created, see #67
   */
  protected Object _buildRelalgProperties(final MapLiteral properties, final ElementVariable ev) {
    if ((properties != null)) {
      PropertyList _createPropertyList = this.factory.createPropertyList();
      final Procedure1<PropertyList> _function = (PropertyList it) -> {
        it.setExpressionContainer(this.topLevelContainer);
      };
      final PropertyList pList = ObjectExtensions.<PropertyList>operator_doubleArrow(_createPropertyList, _function);
      EList<MapLiteralEntry> _entries = properties.getEntries();
      final Consumer<MapLiteralEntry> _function_1 = (MapLiteralEntry e) -> {
        final String key = e.getKey();
        Expression _value = e.getValue();
        final relalg.Expression value = this.buildRelalgExpression(_value);
        EMap<String, relalg.Expression> _entries_1 = pList.getEntries();
        _entries_1.put(key, value);
      };
      _entries.forEach(_function_1);
      ev.setProperties(pList);
    }
    return null;
  }
  
  protected Object _buildRelalgProperties(final Properties properties, final ElementVariable ev) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Parsing Properties type is unsupported.");
    this.logger.unsupported(_builder.toString());
    return null;
  }
  
  protected Object _buildRelalgProperties(final Void p, final ElementVariable ev) {
    return null;
  }
  
  /**
   * Given a RelationshipPattern instance, it's direction information
   * is mapped to the relalg model's Direction type.
   * 
   * @param pattern the relationship pattern
   * @return the appropriate direction descriptor
   */
  protected Direction convertToDirection(final RelationshipPattern pattern) {
    Direction _xblockexpression = null;
    {
      final boolean isLeftArrow = pattern.isIncoming();
      final boolean isRightArrow = pattern.isOutgoing();
      Direction _xifexpression = null;
      if (((isLeftArrow && isRightArrow) || (!(isLeftArrow || isRightArrow)))) {
        _xifexpression = Direction.BOTH;
      } else {
        Direction _xifexpression_1 = null;
        if (isLeftArrow) {
          _xifexpression_1 = Direction.IN;
        } else {
          _xifexpression_1 = Direction.OUT;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public Operator buildRelalg(final EObject e) {
    if (e instanceof RelationshipsPattern) {
      return _buildRelalg((RelationshipsPattern)e);
    } else if (e instanceof SingleQuery) {
      return _buildRelalg((SingleQuery)e);
    } else if (e instanceof PatternElement) {
      return _buildRelalg((PatternElement)e);
    } else if (e instanceof RegularQuery) {
      return _buildRelalg((RegularQuery)e);
    } else if (e instanceof PatternElementChain) {
      return _buildRelalg((PatternElementChain)e);
    } else if (e instanceof PatternPart) {
      return _buildRelalg((PatternPart)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
  
  public UnaryOperator buildRelalgReturn(final Clause r, final Operator content) {
    if (r instanceof Return) {
      return _buildRelalgReturn((Return)r, content);
    } else if (r instanceof With) {
      return _buildRelalgReturn((With)r, content);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(r, content).toString());
    }
  }
  
  public LogicalExpression buildRelalgLogicalExpression(final Expression e, final EList<Operator> joins) {
    if (e instanceof ContainsExpression) {
      return _buildRelalgLogicalExpression((ContainsExpression)e, joins);
    } else if (e instanceof EndsWithExpression) {
      return _buildRelalgLogicalExpression((EndsWithExpression)e, joins);
    } else if (e instanceof ExpressionAnd) {
      return _buildRelalgLogicalExpression((ExpressionAnd)e, joins);
    } else if (e instanceof ExpressionComparison) {
      return _buildRelalgLogicalExpression((ExpressionComparison)e, joins);
    } else if (e instanceof ExpressionOr) {
      return _buildRelalgLogicalExpression((ExpressionOr)e, joins);
    } else if (e instanceof ExpressionXor) {
      return _buildRelalgLogicalExpression((ExpressionXor)e, joins);
    } else if (e instanceof InCollectionExpression) {
      return _buildRelalgLogicalExpression((InCollectionExpression)e, joins);
    } else if (e instanceof IsNotNullExpression) {
      return _buildRelalgLogicalExpression((IsNotNullExpression)e, joins);
    } else if (e instanceof IsNullExpression) {
      return _buildRelalgLogicalExpression((IsNullExpression)e, joins);
    } else if (e instanceof ParenthesizedExpression) {
      return _buildRelalgLogicalExpression((ParenthesizedExpression)e, joins);
    } else if (e instanceof RegExpMatchingExpression) {
      return _buildRelalgLogicalExpression((RegExpMatchingExpression)e, joins);
    } else if (e instanceof RelationshipsPattern) {
      return _buildRelalgLogicalExpression((RelationshipsPattern)e, joins);
    } else if (e instanceof StartsWithExpression) {
      return _buildRelalgLogicalExpression((StartsWithExpression)e, joins);
    } else if (e != null) {
      return _buildRelalgLogicalExpression(e, joins);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e, joins).toString());
    }
  }
  
  public ComparableExpression buildRelalgComparableElement(final Expression e) {
    if (e instanceof ExpressionMulDiv) {
      return _buildRelalgComparableElement((ExpressionMulDiv)e);
    } else if (e instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildRelalgComparableElement((ExpressionNodeLabelsAndPropertyLookup)e);
    } else if (e instanceof ExpressionPlusMinus) {
      return _buildRelalgComparableElement((ExpressionPlusMinus)e);
    } else if (e instanceof ExpressionPower) {
      return _buildRelalgComparableElement((ExpressionPower)e);
    } else if (e instanceof FunctionInvocation) {
      return _buildRelalgComparableElement((FunctionInvocation)e);
    } else if (e instanceof NumberConstant) {
      return _buildRelalgComparableElement((NumberConstant)e);
    } else if (e instanceof Parameter) {
      return _buildRelalgComparableElement((Parameter)e);
    } else if (e instanceof StringConstant) {
      return _buildRelalgComparableElement((StringConstant)e);
    } else if (e instanceof VariableRef) {
      return _buildRelalgComparableElement((VariableRef)e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
  
  public relalg.Expression buildRelalgExpression(final Expression e) {
    if (e instanceof CaseExpression) {
      return _buildRelalgExpression((CaseExpression)e);
    } else if (e instanceof Count) {
      return _buildRelalgExpression((Count)e);
    } else if (e instanceof ExpressionComparison) {
      return _buildRelalgExpression((ExpressionComparison)e);
    } else if (e instanceof ExpressionList) {
      return _buildRelalgExpression((ExpressionList)e);
    } else if (e instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildRelalgExpression((ExpressionNodeLabelsAndPropertyLookup)e);
    } else if (e instanceof FunctionInvocation) {
      return _buildRelalgExpression((FunctionInvocation)e);
    } else if (e instanceof IndexExpression) {
      return _buildRelalgExpression((IndexExpression)e);
    } else if (e instanceof StringConstant) {
      return _buildRelalgExpression((StringConstant)e);
    } else if (e instanceof VariableRef) {
      return _buildRelalgExpression((VariableRef)e);
    } else if (e != null) {
      return _buildRelalgExpression(e);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(e).toString());
    }
  }
  
  public ArithmeticExpression buildRelalgArithmeticExpression(final Expression fi) {
    if (fi instanceof Count) {
      return _buildRelalgArithmeticExpression((Count)fi);
    } else if (fi instanceof ExpressionMulDiv) {
      return _buildRelalgArithmeticExpression((ExpressionMulDiv)fi);
    } else if (fi instanceof ExpressionNodeLabelsAndPropertyLookup) {
      return _buildRelalgArithmeticExpression((ExpressionNodeLabelsAndPropertyLookup)fi);
    } else if (fi instanceof ExpressionPlusMinus) {
      return _buildRelalgArithmeticExpression((ExpressionPlusMinus)fi);
    } else if (fi instanceof ExpressionPower) {
      return _buildRelalgArithmeticExpression((ExpressionPower)fi);
    } else if (fi instanceof FunctionInvocation) {
      return _buildRelalgArithmeticExpression((FunctionInvocation)fi);
    } else if (fi instanceof NumberConstant) {
      return _buildRelalgArithmeticExpression((NumberConstant)fi);
    } else if (fi instanceof ParenthesizedExpression) {
      return _buildRelalgArithmeticExpression((ParenthesizedExpression)fi);
    } else if (fi instanceof VariableRef) {
      return _buildRelalgArithmeticExpression((VariableRef)fi);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(fi).toString());
    }
  }
  
  public Object buildRelalgProperties(final Properties properties, final ElementVariable ev) {
    if (properties instanceof MapLiteral) {
      return _buildRelalgProperties((MapLiteral)properties, ev);
    } else if (properties != null) {
      return _buildRelalgProperties(properties, ev);
    } else if (properties == null) {
      return _buildRelalgProperties((Void)null, ev);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(properties, ev).toString());
    }
  }
}
