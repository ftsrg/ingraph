/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.AllDifferentOperatorEdgeVariablesMatch;
import ingraph.optimization.patterns.AllDifferentOperatorEdgeVariablesMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
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
 * A pattern-specific query specification that can instantiate AllDifferentOperatorEdgeVariablesMatcher in a type-safe way.
 * 
 * @see AllDifferentOperatorEdgeVariablesMatcher
 * @see AllDifferentOperatorEdgeVariablesMatch
 * 
 */
@SuppressWarnings("all")
public final class AllDifferentOperatorEdgeVariablesQuerySpecification extends BaseGeneratedEMFQuerySpecification<AllDifferentOperatorEdgeVariablesMatcher> {
  private AllDifferentOperatorEdgeVariablesQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static AllDifferentOperatorEdgeVariablesQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected AllDifferentOperatorEdgeVariablesMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AllDifferentOperatorEdgeVariablesMatcher.on(engine);
  }
  
  @Override
  public AllDifferentOperatorEdgeVariablesMatcher instantiate() throws ViatraQueryException {
    return AllDifferentOperatorEdgeVariablesMatcher.create();
  }
  
  @Override
  public AllDifferentOperatorEdgeVariablesMatch newEmptyMatch() {
    return AllDifferentOperatorEdgeVariablesMatch.newEmptyMatch();
  }
  
  @Override
  public AllDifferentOperatorEdgeVariablesMatch newMatch(final Object... parameters) {
    return AllDifferentOperatorEdgeVariablesMatch.newMatch((relalg.AllDifferentOperator) parameters[0], (relalg.EdgeVariable) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link AllDifferentOperatorEdgeVariablesQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link AllDifferentOperatorEdgeVariablesQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static AllDifferentOperatorEdgeVariablesQuerySpecification INSTANCE = new AllDifferentOperatorEdgeVariablesQuerySpecification();
    
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
    private final static AllDifferentOperatorEdgeVariablesQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pAllDifferentOperator = new PParameter("allDifferentOperator", "relalg.AllDifferentOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "AllDifferentOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pEdgeVariable = new PParameter("edgeVariable", "relalg.EdgeVariable", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "EdgeVariable")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pAllDifferentOperator, parameter_pEdgeVariable);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.allDifferentOperatorEdgeVariables";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("allDifferentOperator","edgeVariable");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      setEvaluationHints(new QueryEvaluationHint(null, (IQueryBackendFactory)null));
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_allDifferentOperator = body.getOrCreateVariableByName("allDifferentOperator");
      		PVariable var_edgeVariable = body.getOrCreateVariableByName("edgeVariable");
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
      		new TypeConstraint(body, new FlatTuple(var_edgeVariable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EdgeVariable")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_allDifferentOperator, parameter_pAllDifferentOperator),
      		   new ExportedParameter(body, var_edgeVariable, parameter_pEdgeVariable)
      		));
      		// 	AllDifferentOperator.edgeVariables(allDifferentOperator, edgeVariable)
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "AllDifferentOperator", "edgeVariables")));
      		new Equality(body, var__virtual_0_, var_edgeVariable);
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
