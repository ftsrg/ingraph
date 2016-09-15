/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/optimization.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.ExpandVertexMatch;
import ingraph.optimization.patterns.ExpandVertexMatcher;
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
 * A pattern-specific query specification that can instantiate ExpandVertexMatcher in a type-safe way.
 * 
 * @see ExpandVertexMatcher
 * @see ExpandVertexMatch
 * 
 */
@SuppressWarnings("all")
public final class ExpandVertexQuerySpecification extends BaseGeneratedEMFQuerySpecification<ExpandVertexMatcher> {
  private ExpandVertexQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExpandVertexQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExpandVertexMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandVertexMatcher.on(engine);
  }
  
  @Override
  public ExpandVertexMatcher instantiate() throws ViatraQueryException {
    return ExpandVertexMatcher.create();
  }
  
  @Override
  public ExpandVertexMatch newEmptyMatch() {
    return ExpandVertexMatch.newEmptyMatch();
  }
  
  @Override
  public ExpandVertexMatch newMatch(final Object... parameters) {
    return ExpandVertexMatch.newMatch((relalg.AlgebraExpression) parameters[0], (relalg.ExpandOperator) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ExpandVertexQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link ExpandVertexQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ExpandVertexQuerySpecification INSTANCE = new ExpandVertexQuerySpecification();
    
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
    private final static ExpandVertexQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pV = new PParameter("v", "relalg.AlgebraExpression", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pE = new PParameter("e", "relalg.ExpandOperator", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pV, parameter_pE);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.expandVertex";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("v","e");
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
      		PVariable var_v = body.getOrCreateVariableByName("v");
      		PVariable var_e = body.getOrCreateVariableByName("e");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_v, parameter_pV),
      		   new ExportedParameter(body, var_e, parameter_pE)
      		));
      		// 	ExpandOperator.input(e, v)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.example.org/relalg", "ExpandOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.example.org/relalg", "AlphaOperator", "input")));
      		new Equality(body, var__virtual_0_, var_v);
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
