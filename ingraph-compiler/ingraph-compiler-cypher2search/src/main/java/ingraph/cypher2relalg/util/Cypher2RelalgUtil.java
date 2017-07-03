package ingraph.cypher2relalg.util;

import com.google.common.base.Objects;
import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode;
import ingraph.logger.IngraphLogger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.AbstractEdgeVariable;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticExpression;
import relalg.BinaryArithmeticOperationExpression;
import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperatorType;
import relalg.BinaryOperator;
import relalg.Case;
import relalg.CaseExpression;
import relalg.ComparableExpression;
import relalg.EmptyListExpression;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.FunctionExpression;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.GraphObjectVariable;
import relalg.IndexAccessExpression;
import relalg.JoinOperator;
import relalg.LeftOuterJoinOperator;
import relalg.ListExpression;
import relalg.Literal;
import relalg.LogicalExpression;
import relalg.NullaryOperator;
import relalg.Operator;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.UnaryArithmeticOperationExpression;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.Variable;
import relalg.VariableExpression;
import relalg.VertexVariable;
import relalg.function.Function;

@SuppressWarnings("all")
public class Cypher2RelalgUtil {
  @Extension
  private RelalgFactory factory = RelalgFactory.eINSTANCE;
  
  @Extension
  private IngraphLogger logger;
  
  public Cypher2RelalgUtil(final IngraphLogger logger) {
    this.logger = logger;
  }
  
  public Operator buildLeftDeepTree(final Class<? extends BinaryOperator> binaryOperatorType, final Iterator<Operator> i) {
    Operator retVal = null;
    if (((!Objects.equal(i, null)) && i.hasNext())) {
      for (retVal = i.next(); i.hasNext();) {
        {
          BinaryOperator _switchResult = null;
          boolean _matched = false;
          if (Objects.equal(binaryOperatorType, JoinOperator.class)) {
            _matched=true;
            _switchResult = this.factory.createJoinOperator();
          }
          if (!_matched) {
            if (Objects.equal(binaryOperatorType, UnionOperator.class)) {
              _matched=true;
              _switchResult = this.factory.createUnionOperator();
            }
          }
          if (!_matched) {
            if (Objects.equal(binaryOperatorType, LeftOuterJoinOperator.class)) {
              _matched=true;
              _switchResult = this.factory.createLeftOuterJoinOperator();
            }
          }
          if (!_matched) {
            Object _xblockexpression = null;
            {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("Got unexpected BinaryOperator type ");
              String _name = binaryOperatorType.getName();
              _builder.append(_name, "");
              _builder.append(" to build left-deep-tree");
              this.logger.unsupported(_builder.toString());
              _xblockexpression = null;
            }
            _switchResult = ((BinaryOperator)_xblockexpression);
          }
          final BinaryOperator nextAE = _switchResult;
          Operator _next = i.next();
          nextAE.setRightInput(_next);
          nextAE.setLeftInput(retVal);
          retVal = nextAE;
        }
      }
    }
    return retVal;
  }
  
  public LogicalExpression buildLeftDeepTree(final BinaryLogicalOperatorType binaryLogicalOperator, final Iterator<? extends LogicalExpression> i, final RelalgContainer outerContainer) {
    LogicalExpression retVal = null;
    if (((!Objects.equal(i, null)) && i.hasNext())) {
      for (retVal = i.next(); i.hasNext();) {
        {
          BinaryLogicalExpression _createBinaryLogicalExpression = this.factory.createBinaryLogicalExpression();
          final Procedure1<BinaryLogicalExpression> _function = (BinaryLogicalExpression it) -> {
            it.setOperator(binaryLogicalOperator);
            it.setExpressionContainer(outerContainer);
          };
          final BinaryLogicalExpression nextAE = ObjectExtensions.<BinaryLogicalExpression>operator_doubleArrow(_createBinaryLogicalExpression, _function);
          LogicalExpression _next = i.next();
          nextAE.setRightOperand(_next);
          nextAE.setLeftOperand(retVal);
          retVal = nextAE;
        }
      }
    }
    return retVal;
  }
  
  /**
   * Chain expand operators together and add sourceVertexVariables
   */
  public Operator chainExpandOperators(final GetVerticesOperator gvo, final List<ExpandOperator> expandList) {
    Operator _xblockexpression = null;
    {
      VertexVariable lastVertexVariable = gvo.getVertexVariable();
      Operator lastAlgebraExpression = gvo;
      for (final ExpandOperator op : expandList) {
        {
          op.setSourceVertexVariable(lastVertexVariable);
          op.setInput(lastAlgebraExpression);
          VertexVariable _targetVertexVariable = op.getTargetVertexVariable();
          lastVertexVariable = _targetVertexVariable;
          lastAlgebraExpression = op;
        }
      }
      _xblockexpression = lastAlgebraExpression;
    }
    return _xblockexpression;
  }
  
  /**
   * Chain binary operators together to build a left deep tree.
   * 
   * head is put on the leftInput for the 1st element of the tail list,
   * which in turn will be put on the leftInput on the 2nd element of the tail list
   * and so on.
   */
  public Operator chainBinaryOperatorsLeft(final Operator head, final Iterable<? extends BinaryOperator> tail) {
    return this.chainEncapsulatedBinaryOperatorsLeft(head, tail, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_TREE_ROOT);
  }
  
  /**
   * Chain operators to build a left deep tree.
   * 
   * Building a left deep tree requires binary operators, so tail should contain relalg trees that
   * under arbitrary number of UnaryOperator's, lead to a BinaryOperator, i.e. encapsulates a BinaryOperator.
   * These binary operators connect relalg trees together.
   * 
   * @param mode determines the matching mode. For possibilities, see EncapsulatedBinaryOperatorChainMode
   */
  public Operator chainEncapsulatedBinaryOperatorsLeft(final Operator head, final Iterable<? extends Operator> tail, final EncapsulatedBinaryOperatorChainMode mode) {
    Operator _xblockexpression = null;
    {
      Operator lastAlgebraExpression = head;
      for (final Operator op : tail) {
        {
          BinaryOperator _switchResult = null;
          if (mode != null) {
            switch (mode) {
              case CHAIN_AT_TREE_ROOT:
                BinaryOperator _xifexpression = null;
                if ((op instanceof BinaryOperator)) {
                  _xifexpression = ((BinaryOperator)op);
                } else {
                  Object _xblockexpression_1 = null;
                  {
                    StringConcatenation _builder = new StringConcatenation();
                    _builder.append("Chain mode ");
                    _builder.append(mode, "");
                    _builder.append(" requested on a tree having a root other than ");
                    _builder.append(BinaryOperator.class, "");
                    _builder.append(".");
                    this.logger.unrecoverableError(_builder.toString());
                    _xblockexpression_1 = null;
                  }
                  _xifexpression = ((BinaryOperator)_xblockexpression_1);
                }
                _switchResult = _xifexpression;
                break;
              case CHAIN_AT_FIRST_BINARY_OPERATOR:
                _switchResult = this.findBinaryOperator(op, false);
                break;
              case CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC:
                _switchResult = this.findBinaryOperator(op, true);
                break;
              default:
                break;
            }
          }
          final BinaryOperator binaryOp = _switchResult;
          Operator _leftInput = binaryOp.getLeftInput();
          boolean _tripleNotEquals = (_leftInput != null);
          if (_tripleNotEquals) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("During chaining, found ");
            Class<? extends BinaryOperator> _class = binaryOp.getClass();
            _builder.append(_class, "");
            _builder.append(" instance having its leftInput !== null.");
            this.logger.unrecoverableError(_builder.toString());
          }
          binaryOp.setLeftInput(lastAlgebraExpression);
          lastAlgebraExpression = op;
        }
      }
      _xblockexpression = lastAlgebraExpression;
    }
    return _xblockexpression;
  }
  
  /**
   * Finds and returns a BinaryOperator instance that can be reached from op.
   * Parameter travelToLeft determines which BinaryOperator to return:
   *  - if false, the first BinaryOperator found will be returned,
   *    i.e. the one that was found after traveling through zero or more UnaryOpretor instances.
   *    Note: If op itself is a BinaryOperator instance, it will be returned.
   *  - in true, we follow leftInput for BinaryOperator instances having a populated leftInput (i.e. !== null ),
   *    and return the first BinaryOperator instance w/ un-populated leftInput (i.e. === null )
   * 
   * 
   * In case we don't find the requested BinaryOperator instance,
   * an unrecoverable error will be raised.
   * 
   * @param travelToLeft specify if we want to travel through BinaryOperator instances w/ populated leftInput
   */
  protected BinaryOperator findBinaryOperator(final Operator op, final boolean travelToLeft) {
    BinaryOperator _switchResult = null;
    boolean _matched = false;
    if (op instanceof BinaryOperator) {
      _matched=true;
      BinaryOperator _xifexpression = null;
      if ((travelToLeft && (((BinaryOperator)op).getLeftInput() != null))) {
        Operator _leftInput = ((BinaryOperator)op).getLeftInput();
        _xifexpression = this.findBinaryOperator(_leftInput, travelToLeft);
      } else {
        _xifexpression = ((BinaryOperator)op);
      }
      _switchResult = _xifexpression;
    }
    if (!_matched) {
      if (op instanceof UnaryOperator) {
        _matched=true;
        Operator _input = ((UnaryOperator)op).getInput();
        _switchResult = this.findBinaryOperator(_input, travelToLeft);
      }
    }
    if (!_matched) {
      Object _xblockexpression = null;
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Found ");
        Class<? extends Operator> _class = op.getClass();
        _builder.append(_class, "");
        _builder.append(", expected a tree leading to a BinaryOperator through zero or more UnaryOperator\'s and in case of travelToLeft==true populated BinaryOperator.leftInput references.");
        this.logger.unrecoverableError(_builder.toString());
        _xblockexpression = null;
      }
      _switchResult = ((BinaryOperator)_xblockexpression);
    }
    return _switchResult;
  }
  
  /**
   * Finds the variables in expression e that are outside of grouping functions
   * and appends them to the groupingVariables set.
   * 
   * This is not multithreaded-safe, so don't put it in map, forEach etc. call.
   * 
   * @param e the expression to process
   * @param groupingVariables set holding the grouping variables. This set will be extended in-place.
   * @param seenAggregate indicates if previously we have seen any aggregate function
   * 
   * @return boolean value indicating if there were an aggregate function. It can be expressed as: "seenAggregate || (e contains aggregate function call)"
   */
  public boolean accumulateGroupingVariables(final Expression e, final Set<Variable> groupingVariables, final boolean seenAggregate) {
    boolean _xblockexpression = false;
    {
      boolean effectivelySeenAggregate = seenAggregate;
      final LinkedList<Expression> fifo = new LinkedList<Expression>();
      fifo.add(e);
      while ((!fifo.isEmpty())) {
        {
          final Expression el = fifo.removeFirst();
          boolean _matched = false;
          if (el instanceof VariableExpression) {
            _matched=true;
            Variable _variable = ((VariableExpression)el).getVariable();
            final Variable myVar = _variable;
            boolean _matched_1 = false;
            if (myVar instanceof GraphObjectVariable) {
              _matched_1=true;
              groupingVariables.add(myVar);
            }
            if (!_matched_1) {
              if (myVar instanceof ExpressionVariable) {
                _matched_1=true;
                groupingVariables.add(myVar);
              }
            }
            if (!_matched_1) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("Unexpected, yet unsupported variable type found while enumerating grouping variables, got ");
              Class<? extends Variable> _class = myVar.getClass();
              String _name = _class.getName();
              _builder.append(_name, "");
              this.logger.unsupported(_builder.toString());
            }
          }
          if (!_matched) {
            if (el instanceof UnaryArithmeticOperationExpression) {
              _matched=true;
              ArithmeticExpression _operand = ((UnaryArithmeticOperationExpression)el).getOperand();
              fifo.add(_operand);
            }
          }
          if (!_matched) {
            if (el instanceof BinaryArithmeticOperationExpression) {
              _matched=true;
              ArithmeticExpression _leftOperand = ((BinaryArithmeticOperationExpression)el).getLeftOperand();
              fifo.add(_leftOperand);
              ArithmeticExpression _rightOperand = ((BinaryArithmeticOperationExpression)el).getRightOperand();
              fifo.add(_rightOperand);
            }
          }
          if (!_matched) {
            if (el instanceof Literal) {
              _matched=true;
            }
          }
          if (!_matched) {
            if (el instanceof FunctionExpression) {
              _matched=true;
              Function _functor = ((FunctionExpression)el).getFunctor();
              boolean _isAggregation = _functor.isAggregation();
              if (_isAggregation) {
                effectivelySeenAggregate = true;
              } else {
                EList<Expression> _arguments = ((FunctionExpression)el).getArguments();
                fifo.addAll(_arguments);
              }
            }
          }
          if (!_matched) {
            if (el instanceof EmptyListExpression) {
              _matched=true;
            }
          }
          if (!_matched) {
            if (el instanceof ListExpression) {
              _matched=true;
              Expression _head = ((ListExpression)el).getHead();
              fifo.add(_head);
              ListExpression _tail = ((ListExpression)el).getTail();
              fifo.add(_tail);
            }
          }
          if (!_matched) {
            if (el instanceof IndexAccessExpression) {
              _matched=true;
              Expression _list = ((IndexAccessExpression)el).getList();
              fifo.add(_list);
            }
          }
          if (!_matched) {
            if (el instanceof BinaryLogicalExpression) {
              _matched=true;
              LogicalExpression _leftOperand = ((BinaryLogicalExpression)el).getLeftOperand();
              fifo.add(_leftOperand);
              LogicalExpression _rightOperand = ((BinaryLogicalExpression)el).getRightOperand();
              fifo.add(_rightOperand);
            }
          }
          if (!_matched) {
            if (el instanceof ArithmeticComparisonExpression) {
              _matched=true;
              ComparableExpression _leftOperand = ((ArithmeticComparisonExpression)el).getLeftOperand();
              fifo.add(_leftOperand);
              ComparableExpression _rightOperand = ((ArithmeticComparisonExpression)el).getRightOperand();
              fifo.add(_rightOperand);
            }
          }
          if (!_matched) {
            if (el instanceof CaseExpression) {
              _matched=true;
              boolean seenAggregateInCase = false;
              final HashSet<Variable> groupingVariablesInCase = new HashSet<Variable>();
              EList<Case> _cases = ((CaseExpression)el).getCases();
              for (final Case c : _cases) {
                {
                  Expression _when = c.getWhen();
                  boolean _accumulateGroupingVariables = this.accumulateGroupingVariables(_when, groupingVariablesInCase, seenAggregateInCase);
                  seenAggregateInCase = _accumulateGroupingVariables;
                  Expression _then = c.getThen();
                  boolean _accumulateGroupingVariables_1 = this.accumulateGroupingVariables(_then, groupingVariablesInCase, seenAggregateInCase);
                  seenAggregateInCase = _accumulateGroupingVariables_1;
                }
              }
              if (seenAggregateInCase) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("We don\'t support aggregation inside a CASE expression.");
                this.logger.unsupported(_builder.toString());
              } else {
                groupingVariables.addAll(groupingVariablesInCase);
              }
            }
          }
          if (!_matched) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Unexpected, yet unsupported expression type found while enumerating grouping variables, got ");
            Class<? extends Expression> _class = el.getClass();
            String _name = _class.getName();
            _builder.append(_name, "");
            this.logger.unsupported(_builder.toString());
          }
        }
      }
      _xblockexpression = effectivelySeenAggregate;
    }
    return _xblockexpression;
  }
  
  /**
   * Finds edge variables in the given relalg tree.
   * 
   * @param op the operator tree to process
   * 
   * @return set of edge variables found in the relalg tree
   */
  public Set<AbstractEdgeVariable> extractEdgeVariables(final Operator op) {
    HashSet<AbstractEdgeVariable> _xblockexpression = null;
    {
      final LinkedList<Operator> fifo = new LinkedList<Operator>();
      final HashSet<AbstractEdgeVariable> edgeVariables = new HashSet<AbstractEdgeVariable>();
      fifo.add(op);
      while ((!fifo.isEmpty())) {
        {
          final Operator el = fifo.removeFirst();
          boolean _matched = false;
          if (el instanceof ExpandOperator) {
            _matched=true;
            AbstractEdgeVariable _edgeVariable = ((ExpandOperator)el).getEdgeVariable();
            edgeVariables.add(_edgeVariable);
            Operator _input = ((ExpandOperator)el).getInput();
            fifo.add(_input);
          }
          if (!_matched) {
            if (el instanceof BinaryOperator) {
              _matched=true;
              Operator _leftInput = ((BinaryOperator)el).getLeftInput();
              fifo.add(_leftInput);
              Operator _rightInput = ((BinaryOperator)el).getRightInput();
              fifo.add(_rightInput);
            }
          }
          if (!_matched) {
            if (el instanceof UnaryOperator) {
              _matched=true;
              Operator _input = ((UnaryOperator)el).getInput();
              fifo.add(_input);
            }
          }
          if (!_matched) {
            if (el instanceof GetEdgesOperator) {
              _matched=true;
              AbstractEdgeVariable _edgeVariable = ((GetEdgesOperator)el).getEdgeVariable();
              edgeVariables.add(_edgeVariable);
            }
          }
          if (!_matched) {
            if (el instanceof NullaryOperator) {
              _matched=true;
            }
          }
          if (!_matched) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Unexpected, yet unsupported expression type found while enumerating grouping variables, got ");
            Class<? extends Operator> _class = el.getClass();
            String _name = _class.getName();
            _builder.append(_name, "");
            this.logger.unsupported(_builder.toString());
          }
        }
      }
      _xblockexpression = edgeVariables;
    }
    return _xblockexpression;
  }
}
