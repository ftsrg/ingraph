/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.SortAndTopOperatorMatch;
import ingraph.optimization.patterns.SortAndTopOperatorMatcher;
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
 * A pattern-specific query specification that can instantiate SortAndTopOperatorMatcher in a type-safe way.
 * 
 * @see SortAndTopOperatorMatcher
 * @see SortAndTopOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class SortAndTopOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<SortAndTopOperatorMatcher> {
  private SortAndTopOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SortAndTopOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SortAndTopOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SortAndTopOperatorMatcher.on(engine);
  }
  
  @Override
  public SortAndTopOperatorMatcher instantiate() throws ViatraQueryException {
    return SortAndTopOperatorMatcher.create();
  }
  
  @Override
  public SortAndTopOperatorMatch newEmptyMatch() {
    return SortAndTopOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public SortAndTopOperatorMatch newMatch(final Object... parameters) {
    return SortAndTopOperatorMatch.newMatch((relalg.SortOperator) parameters[0], (relalg.TopOperator) parameters[1], (relalg.RelalgContainer) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SortAndTopOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link SortAndTopOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SortAndTopOperatorQuerySpecification INSTANCE = new SortAndTopOperatorQuerySpecification();
    
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
    private final static SortAndTopOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSortOperator = new PParameter("sortOperator", "relalg.SortOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SortOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTopOperator = new PParameter("topOperator", "relalg.TopOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "TopOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTopLevelContainer = new PParameter("topLevelContainer", "relalg.RelalgContainer", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "RelalgContainer")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSortOperator, parameter_pTopOperator, parameter_pTopLevelContainer);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.sortAndTopOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sortOperator","topOperator","topLevelContainer");
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
      		PVariable var_sortOperator = body.getOrCreateVariableByName("sortOperator");
      		PVariable var_topOperator = body.getOrCreateVariableByName("topOperator");
      		PVariable var_topLevelContainer = body.getOrCreateVariableByName("topLevelContainer");
      		new TypeConstraint(body, new FlatTuple(var_sortOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SortOperator")));
      		new TypeConstraint(body, new FlatTuple(var_topOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "TopOperator")));
      		new TypeConstraint(body, new FlatTuple(var_topLevelContainer), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "RelalgContainer")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_sortOperator, parameter_pSortOperator),
      		   new ExportedParameter(body, var_topOperator, parameter_pTopOperator),
      		   new ExportedParameter(body, var_topLevelContainer, parameter_pTopLevelContainer)
      		));
      		//   RelalgContainer.rootExpression(topLevelContainer, topOperator)
      		new TypeConstraint(body, new FlatTuple(var_topLevelContainer), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "RelalgContainer")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_topLevelContainer, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "RelalgContainer", "rootExpression")));
      		new Equality(body, var__virtual_0_, var_topOperator);
      		//   TopOperator.input(topOperator, sortOperator)
      		new TypeConstraint(body, new FlatTuple(var_topOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "TopOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_topOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_1_, var_sortOperator);
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
