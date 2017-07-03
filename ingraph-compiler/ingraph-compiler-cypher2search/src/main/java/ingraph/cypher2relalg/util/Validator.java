package ingraph.cypher2relalg.util;

import com.google.common.base.Objects;
import ingraph.cypher2relalg.util.ExpressionNameInferencer;
import ingraph.cypher2relalg.util.UnaryOperator0or1Pattern;
import ingraph.logger.IngraphLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.slizaa.neo4j.opencypher.openCypher.Clause;
import org.slizaa.neo4j.opencypher.openCypher.Create;
import org.slizaa.neo4j.opencypher.openCypher.Delete;
import org.slizaa.neo4j.opencypher.openCypher.Match;
import org.slizaa.neo4j.opencypher.openCypher.Return;
import org.slizaa.neo4j.opencypher.openCypher.Unwind;
import org.slizaa.neo4j.opencypher.openCypher.With;
import relalg.DuplicateEliminationOperator;
import relalg.ExpressionVariable;
import relalg.Operator;
import relalg.ProjectionOperator;
import relalg.SelectionOperator;
import relalg.SortOperator;
import relalg.TopOperator;
import relalg.UnaryOperator;
import relalg.UnionOperator;

@SuppressWarnings("all")
public class Validator {
  @Extension
  private static IngraphLogger logger = new IngraphLogger(Validator.class.getName());
  
  /**
   * Some checks for a single subquery's MATCH clauses.
   * 
   * Note: pass all the MATCH clauses in sequence to allow for proper checking.
   * 
   * Checks performed:
   * - It is invalid to have MATCH after OPTIONAL MATCH.
   */
  public static void checkSubQueryMatchClauseSequence(final Iterable<Match> singleQuery_MatchList, final IngraphLogger logger) {
    Match _head = null;
    if (singleQuery_MatchList!=null) {
      _head=IterableExtensions.<Match>head(singleQuery_MatchList);
    }
    final Match head = _head;
    boolean _notEquals = (!Objects.equal(head, null));
    if (_notEquals) {
      boolean seenOptionalMatch = false;
      for (final Match o : singleQuery_MatchList) {
        boolean _isOptional = o.isOptional();
        if (_isOptional) {
          seenOptionalMatch = true;
        } else {
          if (seenOptionalMatch) {
            logger.unrecoverableError("It is invalid to have MATCH after OPTIONAL MATCH.");
          }
        }
      }
    }
  }
  
  /**
   * Some checks for a single subquery's clauses
   * 
   * A subquery has the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)
   * 
   * Checks performed:
   * - it consists of only MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses
   * - it ends with RETURN, CREATE, DELETE, WITH or UNWIND
   * - it has at most 1 of either RETURN, WITH
   * - it has at most 1 UNWIND
   * - it has at least one of WITH, CREATE, DELETE, UNWIND and RETURN
   * - before DELETE there must be at least one MATCH clause
   */
  public static void checkSubQueryClauseSequence(final List<Clause> clauses, final IngraphLogger logger) {
    int numReturnOrWith = 0;
    int numUnwind = 0;
    int numMatch = 0;
    int numCreate = 0;
    int numDelete = 0;
    for (final Clause c : clauses) {
      boolean _matched = false;
      if (c instanceof Match) {
        _matched=true;
        numMatch++;
      }
      if (!_matched) {
        if (c instanceof Create) {
          _matched=true;
          numCreate++;
        }
      }
      if (!_matched) {
        if (c instanceof Delete) {
          _matched=true;
          numDelete++;
        }
      }
      if (!_matched) {
        if (c instanceof Unwind) {
          _matched=true;
          numUnwind++;
        }
      }
      if (!_matched) {
        if (c instanceof With) {
          _matched=true;
        }
        if (!_matched) {
          if (c instanceof Return) {
            _matched=true;
          }
        }
        if (_matched) {
          numReturnOrWith++;
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Currently we only support MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses in a single subquery. Found: ");
        Class<? extends Clause> _class = c.getClass();
        String _name = _class.getName();
        _builder.append(_name, "");
        _builder.append(".");
        logger.unsupported(_builder.toString());
      }
    }
    boolean _not = (!(((((IterableExtensions.<Clause>last(clauses) instanceof Return) || (IterableExtensions.<Clause>last(clauses) instanceof With)) || (IterableExtensions.<Clause>last(clauses) instanceof Unwind)) || (IterableExtensions.<Clause>last(clauses) instanceof Create)) || (IterableExtensions.<Clause>last(clauses) instanceof Delete)));
    if (_not) {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Last clause of a single subquery must be RETURN, CREATE, DELETE, WITH or UNWIND, but found ");
      Clause _last = IterableExtensions.<Clause>last(clauses);
      Class<? extends Clause> _class_1 = _last.getClass();
      String _name_1 = _class_1.getName();
      _builder_1.append(_name_1, "");
      _builder_1.append(" instead.");
      logger.unsupported(_builder_1.toString());
    }
    if ((numUnwind > 1)) {
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("At most 1 Unwind clause allowed in a subquery. Found ");
      _builder_2.append(numUnwind, "");
      _builder_2.append(".");
      logger.unsupported(_builder_2.toString());
    }
    if ((numReturnOrWith > 1)) {
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("At most 1 Return or With clause allowed in a subquery. Found ");
      _builder_3.append(numReturnOrWith, "");
      _builder_3.append(".");
      logger.unsupported(_builder_3.toString());
    }
    if (((((numDelete + numCreate) + numUnwind) + numReturnOrWith) < 1)) {
      StringConcatenation _builder_4 = new StringConcatenation();
      _builder_4.append("There must be at least one of WITH, CREATE, DELETE, RETURN or UNWIND in a subquery, but none of them was found.");
      logger.unsupported(_builder_4.toString());
    }
    if (((numDelete > 1) && (numMatch < 1))) {
      StringConcatenation _builder_5 = new StringConcatenation();
      _builder_5.append("There must be at least one of MATCH before DELETE in a subquery, but no MATCH was found.");
      logger.unsupported(_builder_5.toString());
    }
  }
  
  /**
   * Some checks for a single query's clauses
   * 
   * Checks performed:
   * - we currently support only the following clauses: MATCH, CREATE, DELETE, WITH UNWIND?, UNWIND, RETURN
   * - last clause must be a RETURN clause
   */
  public static void checkSingleQueryClauseSequence(final List<Clause> clauses, final IngraphLogger logger) {
    for (final Clause c : clauses) {
      if ((!((((((c instanceof Match) || (c instanceof With)) || (c instanceof Unwind)) || (c instanceof Return)) || (c instanceof Create)) || (c instanceof Delete)))) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Currently we only support MATCH, CREATE, DELETE, WITH, UNWIND and RETURN clauses in a single query. Found: ");
        Class<? extends Clause> _class = c.getClass();
        String _name = _class.getName();
        _builder.append(_name, "");
        _builder.append(".");
        logger.unsupported(_builder.toString());
      }
    }
    boolean _not = (!(((IterableExtensions.<Clause>last(clauses) instanceof Return) || (IterableExtensions.<Clause>last(clauses) instanceof Create)) || (IterableExtensions.<Clause>last(clauses) instanceof Delete)));
    if (_not) {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Last clause of a single query must be RETURN, CREATE or DELETE but found ");
      Clause _last = IterableExtensions.<Clause>last(clauses);
      Class<? extends Clause> _class_1 = _last.getClass();
      String _name_1 = _class_1.getName();
      _builder_1.append(_name_1, "");
      _builder_1.append(" instead.");
      logger.unsupported(_builder_1.toString());
    }
  }
  
  /**
   * For a union query, this checks if all operands of the union has the same column names
   * as required by the openCypher.
   * 
   * We expect a tree that on each branches from root, the following pattern matches:
   * <pre>UnionOperator* (UnaryOperator not ProjectionOperator)* ProjectionOperator</pre>
   * 
   * When the root is not a UnionOperator, we assume it is valid, and return true.
   */
  public static boolean checkIfUnionQueryColumnNamesMatch(final Operator query, final IngraphLogger logger) {
    boolean _xifexpression = false;
    if ((query instanceof UnionOperator)) {
      boolean _xblockexpression = false;
      {
        final LinkedList<List<String>> columnNamesList = new LinkedList<List<String>>();
        final LinkedList<Operator> fifo = new LinkedList<Operator>();
        fifo.add(query);
        while ((!fifo.isEmpty())) {
          {
            final Operator el = fifo.removeFirst();
            if ((el instanceof UnionOperator)) {
              Operator _leftInput = ((UnionOperator)el).getLeftInput();
              fifo.add(_leftInput);
              Operator _rightInput = ((UnionOperator)el).getRightInput();
              fifo.add(_rightInput);
            } else {
              if ((el instanceof UnaryOperator)) {
                Operator p = el;
                while (((p instanceof UnaryOperator) && (!(p instanceof ProjectionOperator)))) {
                  Operator _input = ((UnaryOperator) p).getInput();
                  p = _input;
                }
                if ((p instanceof ProjectionOperator)) {
                  List<String> _columnNames = Validator.getColumnNames(((ProjectionOperator)p), logger);
                  columnNamesList.add(_columnNames);
                } else {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("Expected to find a projection operator, but found ");
                  Class<? extends Operator> _class = p.getClass();
                  String _name = _class.getName();
                  _builder.append(_name, "");
                  _builder.append(" instead.");
                  logger.unrecoverableError(_builder.toString());
                }
              } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("Unexpected operator received when checking column names in a union query: ");
                Class<? extends Operator> _class_1 = el.getClass();
                String _name_1 = _class_1.getName();
                _builder_1.append(_name_1, "");
                logger.unrecoverableError(_builder_1.toString());
              }
            }
          }
        }
        int _size = columnNamesList.size();
        boolean _lessThan = (_size < 2);
        if (_lessThan) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("It is strange that we found only ");
          int _size_1 = columnNamesList.size();
          _builder.append(_size_1, "");
          _builder.append(" column names list under a union operator tree. We expected at least 2.");
          logger.unrecoverableError(_builder.toString());
        }
        final List<String> refList = columnNamesList.removeFirst();
        boolean result = true;
        for (final List<String> currList : columnNamesList) {
          int _size_2 = refList.size();
          int _size_3 = currList.size();
          boolean _notEquals = (_size_2 != _size_3);
          if (_notEquals) {
            result = false;
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("All sub queries of a UNION must have the same number of output columns.");
            logger.warning(_builder_1.toString());
            return result;
          } else {
            for (int i = 0; (i < refList.size()); i++) {
              String _get = refList.get(i);
              String _get_1 = currList.get(i);
              boolean _equals = _get.equals(_get_1);
              boolean _not = (!_equals);
              if (_not) {
                result = false;
                return result;
              }
            }
          }
        }
        _xblockexpression = result;
      }
      _xifexpression = _xblockexpression;
    } else {
      return true;
    }
    return _xifexpression;
  }
  
  /**
   * Validate compiled form of a subquery.
   * 
   * @param op A operator tree of the form
   *        SelectionOperator? TopOperator? SortOperator? DuplicateEliminationOperator? ProjectionOperator content
   *        Note that GroupingOperator is a subclass of ProjectionOperator, so it might appear instead of a plain old ProjectionOperator
   */
  public static void checkSubqueryCompilation(final Operator op) {
    UnaryOperator parent = null;
    Operator currentOperator = op;
    final ArrayList<String> unaryOperatorTreeSeen = new ArrayList<String>();
    UnaryOperator0or1Pattern _unaryOperator0or1Pattern = new UnaryOperator0or1Pattern(SelectionOperator.class, true);
    UnaryOperator0or1Pattern _unaryOperator0or1Pattern_1 = new UnaryOperator0or1Pattern(TopOperator.class, true);
    UnaryOperator0or1Pattern _unaryOperator0or1Pattern_2 = new UnaryOperator0or1Pattern(SortOperator.class, true);
    UnaryOperator0or1Pattern _unaryOperator0or1Pattern_3 = new UnaryOperator0or1Pattern(DuplicateEliminationOperator.class, true);
    UnaryOperator0or1Pattern _unaryOperator0or1Pattern_4 = new UnaryOperator0or1Pattern(ProjectionOperator.class, false);
    final List<UnaryOperator0or1Pattern> pattern = Collections.<UnaryOperator0or1Pattern>unmodifiableList(CollectionLiterals.<UnaryOperator0or1Pattern>newArrayList(_unaryOperator0or1Pattern, _unaryOperator0or1Pattern_1, _unaryOperator0or1Pattern_2, _unaryOperator0or1Pattern_3, _unaryOperator0or1Pattern_4));
    for (final UnaryOperator0or1Pattern p : pattern) {
      Class<? extends UnaryOperator> _opc = p.getOpc();
      boolean _isInstance = _opc.isInstance(currentOperator);
      if (_isInstance) {
        if ((currentOperator instanceof UnaryOperator)) {
          Class<? extends UnaryOperator> _class = ((UnaryOperator)currentOperator).getClass();
          String _name = _class.getName();
          unaryOperatorTreeSeen.add(_name);
          parent = ((UnaryOperator)currentOperator);
          Operator _input = ((UnaryOperator)currentOperator).getInput();
          currentOperator = _input;
        } else {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Static error in the compiler!");
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append(" ");
          _builder_1.append("We support a chain of ");
          String _name_1 = UnaryOperator.class.getName();
          _builder_1.append(_name_1, " ");
          _builder_1.append(" for pattern matching.");
          String _plus = (_builder.toString() + _builder_1);
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(" ");
          _builder_2.append("So far we have seen a chain of ");
          String _join = IterableExtensions.join(unaryOperatorTreeSeen, ",");
          _builder_2.append(_join, " ");
          _builder_2.append(", and now we reached ");
          Class<? extends Operator> _class_1 = currentOperator.getClass();
          String _name_2 = _class_1.getName();
          _builder_2.append(_name_2, " ");
          _builder_2.append(".");
          String _plus_1 = (_plus + _builder_2);
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append(" ");
          _builder_3.append("This does not match what we expected: ");
          String _join_1 = IterableExtensions.join(pattern, " ");
          _builder_3.append(_join_1, " ");
          String _plus_2 = (_plus_1 + _builder_3);
          Validator.logger.unrecoverableError(_plus_2);
        }
      } else {
        boolean _isMandatory = p.isMandatory();
        if (_isMandatory) {
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("So far we have seen a chain of ");
          String _join_2 = IterableExtensions.join(unaryOperatorTreeSeen, ",");
          _builder_4.append(_join_2, "");
          _builder_4.append(", and now we reached ");
          Class<? extends Operator> _class_2 = currentOperator.getClass();
          String _name_3 = _class_2.getName();
          _builder_4.append(_name_3, "");
          _builder_4.append(".");
          StringConcatenation _builder_5 = new StringConcatenation();
          _builder_5.append(" ");
          _builder_5.append("This does not match what we expected: ");
          String _join_3 = IterableExtensions.join(pattern, " ");
          _builder_5.append(_join_3, " ");
          String _plus_3 = (_builder_4.toString() + _builder_5);
          Validator.logger.unrecoverableError(_plus_3);
        }
      }
    }
    if ((parent == null)) {
      StringConcatenation _builder_6 = new StringConcatenation();
      _builder_6.append("This should never happen: after validation, we found parent to be null, but reached this point...");
      Validator.logger.unrecoverableError(_builder_6.toString());
    }
  }
  
  /**
   * Get column names as returned by a projection operator.
   */
  private static List<String> getColumnNames(final ProjectionOperator p, final IngraphLogger logger) {
    EList<ExpressionVariable> _elements = p.getElements();
    final Function1<ExpressionVariable, String> _function = (ExpressionVariable it) -> {
      return ExpressionNameInferencer.inferName(it, logger);
    };
    return ListExtensions.<ExpressionVariable, String>map(_elements, _function);
  }
}
