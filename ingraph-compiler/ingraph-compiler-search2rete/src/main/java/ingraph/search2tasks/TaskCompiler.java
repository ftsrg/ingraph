package ingraph.search2tasks;

import com.google.common.base.Objects;
import ingraph.search2tasks.tasks.Task;
import ingraph.search2tasks.tasks.nullary.GetEdgesTask;
import ingraph.search2tasks.tasks.nullary.GetVerticesTask;
import ingraph.search2tasks.tasks.unary.AllDifferentTask;
import ingraph.search2tasks.tasks.unary.DuplicateEliminationTask;
import ingraph.search2tasks.tasks.unary.ExpandBothTask;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import relalg.AbstractEdgeVariable;
import relalg.AllDifferentOperator;
import relalg.Direction;
import relalg.DuplicateEliminationOperator;
import relalg.ExpandOperator;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.Operator;
import relalg.VertexVariable;

/**
 * Utility class for compiling relational algebra operators into search tasks.
 * For operators with no compilation logic (missing dispatch method) an <code>IllegalArgumentException</code> is thrown.
 */
@SuppressWarnings("all")
public class TaskCompiler {
  protected static List<Task> _compile(final GetVerticesOperator getVerticesOp) {
    List<Task> _xblockexpression = null;
    {
      final VertexVariable variable = getVerticesOp.getVertexVariable();
      GetVerticesTask _getVerticesTask = new GetVerticesTask(variable);
      _xblockexpression = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getVerticesTask));
    }
    return _xblockexpression;
  }
  
  protected static List<Task> _compile(final GetEdgesOperator getEdgesOp) {
    List<Task> _xifexpression = null;
    Direction _direction = getEdgesOp.getDirection();
    boolean _equals = Objects.equal(_direction, Direction.OUT);
    if (_equals) {
      List<Task> _xblockexpression = null;
      {
        final VertexVariable sourceVar = getEdgesOp.getSourceVertexVariable();
        final AbstractEdgeVariable edgeVar = getEdgesOp.getEdgeVariable();
        final VertexVariable targetVar = getEdgesOp.getTargetVertexVariable();
        GetEdgesTask _getEdgesTask = new GetEdgesTask(sourceVar, edgeVar, targetVar);
        _xblockexpression = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getEdgesTask));
      }
      _xifexpression = _xblockexpression;
    } else {
      List<Task> _xifexpression_1 = null;
      Direction _direction_1 = getEdgesOp.getDirection();
      boolean _equals_1 = Objects.equal(_direction_1, Direction.IN);
      if (_equals_1) {
        List<Task> _xblockexpression_1 = null;
        {
          final VertexVariable sourceVar = getEdgesOp.getSourceVertexVariable();
          final AbstractEdgeVariable edgeVar = getEdgesOp.getEdgeVariable();
          final VertexVariable targetVar = getEdgesOp.getTargetVertexVariable();
          GetEdgesTask _getEdgesTask = new GetEdgesTask(targetVar, edgeVar, sourceVar);
          _xblockexpression_1 = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getEdgesTask));
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        List<Task> _xifexpression_2 = null;
        Direction _direction_2 = getEdgesOp.getDirection();
        boolean _equals_2 = Objects.equal(_direction_2, Direction.BOTH);
        if (_equals_2) {
          List<Task> _xblockexpression_2 = null;
          {
            final VertexVariable sourceVar = getEdgesOp.getSourceVertexVariable();
            final AbstractEdgeVariable edgeVar = getEdgesOp.getEdgeVariable();
            final VertexVariable targetVar = getEdgesOp.getTargetVertexVariable();
            GetEdgesTask _getEdgesTask = new GetEdgesTask(sourceVar, edgeVar, targetVar);
            GetEdgesTask _getEdgesTask_1 = new GetEdgesTask(targetVar, edgeVar, sourceVar);
            _xblockexpression_2 = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getEdgesTask, _getEdgesTask_1));
          }
          _xifexpression_2 = _xblockexpression_2;
        } else {
          Direction _direction_3 = getEdgesOp.getDirection();
          String _plus = ("Direction " + _direction_3);
          String _plus_1 = (_plus + " is not supported");
          throw new IllegalArgumentException(_plus_1);
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected static List<Task> _compile(final AllDifferentOperator allDiffOp) {
    AllDifferentTask _allDifferentTask = new AllDifferentTask();
    return Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_allDifferentTask));
  }
  
  protected static List<Task> _compile(final DuplicateEliminationOperator dupElimOp) {
    DuplicateEliminationTask _duplicateEliminationTask = new DuplicateEliminationTask();
    return Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_duplicateEliminationTask));
  }
  
  protected static List<Task> _compile(final ExpandOperator expandOp) {
    List<Task> _xifexpression = null;
    Direction _direction = expandOp.getDirection();
    boolean _equals = Objects.equal(_direction, Direction.OUT);
    if (_equals) {
      List<Task> _xblockexpression = null;
      {
        final VertexVariable sourceVar = expandOp.getSourceVertexVariable();
        final AbstractEdgeVariable edgeVar = expandOp.getEdgeVariable();
        final VertexVariable targetVar = expandOp.getTargetVertexVariable();
        ExpandBothTask _expandBothTask = new ExpandBothTask(sourceVar, edgeVar, targetVar);
        _xblockexpression = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_expandBothTask));
      }
      _xifexpression = _xblockexpression;
    } else {
      List<Task> _xifexpression_1 = null;
      Direction _direction_1 = expandOp.getDirection();
      boolean _equals_1 = Objects.equal(_direction_1, Direction.IN);
      if (_equals_1) {
        List<Task> _xblockexpression_1 = null;
        {
          final VertexVariable sourceVar = expandOp.getSourceVertexVariable();
          final AbstractEdgeVariable edgeVar = expandOp.getEdgeVariable();
          final VertexVariable targetVar = expandOp.getTargetVertexVariable();
          GetEdgesTask _getEdgesTask = new GetEdgesTask(targetVar, edgeVar, sourceVar);
          _xblockexpression_1 = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getEdgesTask));
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        List<Task> _xifexpression_2 = null;
        Direction _direction_2 = expandOp.getDirection();
        boolean _equals_2 = Objects.equal(_direction_2, Direction.BOTH);
        if (_equals_2) {
          List<Task> _xblockexpression_2 = null;
          {
            final VertexVariable sourceVar = expandOp.getSourceVertexVariable();
            final AbstractEdgeVariable edgeVar = expandOp.getEdgeVariable();
            final VertexVariable targetVar = expandOp.getTargetVertexVariable();
            GetEdgesTask _getEdgesTask = new GetEdgesTask(sourceVar, edgeVar, targetVar);
            GetEdgesTask _getEdgesTask_1 = new GetEdgesTask(targetVar, edgeVar, sourceVar);
            _xblockexpression_2 = Collections.<Task>unmodifiableList(CollectionLiterals.<Task>newArrayList(_getEdgesTask, _getEdgesTask_1));
          }
          _xifexpression_2 = _xblockexpression_2;
        } else {
          Direction _direction_3 = expandOp.getDirection();
          String _plus = ("Direction " + _direction_3);
          String _plus_1 = (_plus + " is not supported");
          throw new IllegalArgumentException(_plus_1);
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public static List<Task> compile(final Operator expandOp) {
    if (expandOp instanceof ExpandOperator) {
      return _compile((ExpandOperator)expandOp);
    } else if (expandOp instanceof GetEdgesOperator) {
      return _compile((GetEdgesOperator)expandOp);
    } else if (expandOp instanceof AllDifferentOperator) {
      return _compile((AllDifferentOperator)expandOp);
    } else if (expandOp instanceof DuplicateEliminationOperator) {
      return _compile((DuplicateEliminationOperator)expandOp);
    } else if (expandOp instanceof GetVerticesOperator) {
      return _compile((GetVerticesOperator)expandOp);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(expandOp).toString());
    }
  }
}
