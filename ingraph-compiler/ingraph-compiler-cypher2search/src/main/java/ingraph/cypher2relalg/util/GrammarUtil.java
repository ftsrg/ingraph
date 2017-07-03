package ingraph.cypher2relalg.util;

import org.eclipse.emf.ecore.EObject;
import org.slizaa.neo4j.opencypher.openCypher.Create;
import org.slizaa.neo4j.opencypher.openCypher.Delete;

/**
 * Utility class to use for the slizaa openCypher Xtext grammar.
 */
@SuppressWarnings("all")
public class GrammarUtil {
  /**
   * Determines whether a clause is a CREATE/UPDATE/DELETE or not.
   * 
   * FIXME: UPDATE is not handled yet.
   */
  public static boolean isCudClause(final EObject o) {
    boolean _switchResult = false;
    boolean _matched = false;
    if (o instanceof Create) {
      _matched=true;
      _switchResult = true;
    }
    if (!_matched) {
      if (o instanceof Delete) {
        _matched=true;
        _switchResult = true;
      }
    }
    if (!_matched) {
      _switchResult = false;
    }
    return _switchResult;
  }
}
