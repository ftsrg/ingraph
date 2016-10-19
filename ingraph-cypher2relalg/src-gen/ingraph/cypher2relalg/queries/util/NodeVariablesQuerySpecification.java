/**
 * Generated from platform:/resource/ingraph-cypher2relalg/src/ingraph/cypher2relalg/queries/PatternQueries.vql
 */
package ingraph.cypher2relalg.queries.util;

import com.google.common.collect.Sets;
import ingraph.cypher2relalg.queries.NodeVariablesMatch;
import ingraph.cypher2relalg.queries.NodeVariablesMatcher;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.context.IInputKey;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate NodeVariablesMatcher in a type-safe way.
 * 
 * @see NodeVariablesMatcher
 * @see NodeVariablesMatch
 * 
 */
@SuppressWarnings("all")
public final class NodeVariablesQuerySpecification extends BaseGeneratedEMFQuerySpecification<NodeVariablesMatcher> {
  private NodeVariablesQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static NodeVariablesQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected NodeVariablesMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NodeVariablesMatcher.on(engine);
  }
  
  @Override
  public NodeVariablesMatcher instantiate() throws ViatraQueryException {
    return NodeVariablesMatcher.create();
  }
  
  @Override
  public NodeVariablesMatch newEmptyMatch() {
    return NodeVariablesMatch.newEmptyMatch();
  }
  
  @Override
  public NodeVariablesMatch newMatch(final Object... parameters) {
    return NodeVariablesMatch.newMatch((org.slizaa.neo4j.opencypher.openCypher.Variable) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link NodeVariablesQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link NodeVariablesQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static NodeVariablesQuerySpecification INSTANCE = new NodeVariablesQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static NodeVariablesQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pVariable = new PParameter("variable", "org.slizaa.neo4j.opencypher.openCypher.Variable", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pVariable);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.cypher2relalg.queries.nodeVariables";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("variable");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      setEvaluationHints(new QueryEvaluationHint(null, Collections.<String,Object>emptyMap()));
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_variable = body.getOrCreateVariableByName("variable");
      		PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_variable, parameter_pVariable)
      		));
      		// 	NodePattern.variable(_, variable)
      		new TypeConstraint(body, new FlatTuple(var___0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.slizaa.org/neo4j/opencypher/OpenCypher", "NodePattern")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var___0_, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.slizaa.org/neo4j/opencypher/OpenCypher", "NodePattern", "variable")));
      		new Equality(body, var__virtual_0_, var_variable);
      		bodies.add(body);
      	}
      	// to silence compiler error
      	if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
