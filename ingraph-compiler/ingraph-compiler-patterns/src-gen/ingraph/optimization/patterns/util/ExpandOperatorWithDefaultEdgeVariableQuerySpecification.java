/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.ExpandOperatorWithDefaultEdgeVariableMatch;
import ingraph.optimization.patterns.ExpandOperatorWithDefaultEdgeVariableMatcher;
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
 * A pattern-specific query specification that can instantiate ExpandOperatorWithDefaultEdgeVariableMatcher in a type-safe way.
 * 
 * @see ExpandOperatorWithDefaultEdgeVariableMatcher
 * @see ExpandOperatorWithDefaultEdgeVariableMatch
 * 
 */
@SuppressWarnings("all")
public final class ExpandOperatorWithDefaultEdgeVariableQuerySpecification extends BaseGeneratedEMFQuerySpecification<ExpandOperatorWithDefaultEdgeVariableMatcher> {
  private ExpandOperatorWithDefaultEdgeVariableQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExpandOperatorWithDefaultEdgeVariableQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExpandOperatorWithDefaultEdgeVariableMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandOperatorWithDefaultEdgeVariableMatcher.on(engine);
  }
  
  @Override
  public ExpandOperatorWithDefaultEdgeVariableMatcher instantiate() throws ViatraQueryException {
    return ExpandOperatorWithDefaultEdgeVariableMatcher.create();
  }
  
  @Override
  public ExpandOperatorWithDefaultEdgeVariableMatch newEmptyMatch() {
    return ExpandOperatorWithDefaultEdgeVariableMatch.newEmptyMatch();
  }
  
  @Override
  public ExpandOperatorWithDefaultEdgeVariableMatch newMatch(final Object... parameters) {
    return ExpandOperatorWithDefaultEdgeVariableMatch.newMatch((relalg.ExpandOperator) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ExpandOperatorWithDefaultEdgeVariableQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link ExpandOperatorWithDefaultEdgeVariableQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ExpandOperatorWithDefaultEdgeVariableQuerySpecification INSTANCE = new ExpandOperatorWithDefaultEdgeVariableQuerySpecification();
    
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
    private final static ExpandOperatorWithDefaultEdgeVariableQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pDefaultExpandOperator = new PParameter("defaultExpandOperator", "relalg.ExpandOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "ExpandOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pDefaultExpandOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.expandOperatorWithDefaultEdgeVariable";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("defaultExpandOperator");
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
      		PVariable var_defaultExpandOperator = body.getOrCreateVariableByName("defaultExpandOperator");
      		PVariable var_edgeVariable = body.getOrCreateVariableByName("edgeVariable");
      		new TypeConstraint(body, new FlatTuple(var_defaultExpandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_defaultExpandOperator, parameter_pDefaultExpandOperator)
      		));
      		// 	ExpandOperator.edgeVariable(defaultExpandOperator, edgeVariable)
      		new TypeConstraint(body, new FlatTuple(var_defaultExpandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_defaultExpandOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "NavigationDescriptor", "edgeVariable")));
      		new Equality(body, var__virtual_0_, var_edgeVariable);
      		// 	EdgeVariable(edgeVariable)
      		new TypeConstraint(body, new FlatTuple(var_edgeVariable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EdgeVariable")));
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
