/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatch;
import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatcher;
import ingraph.optimization.patterns.util.EmptyOrSingleVariableAllDifferentOperatorQuerySpecification;
import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate UnnecessaryAllDifferentOperatorMatcher in a type-safe way.
 * 
 * @see UnnecessaryAllDifferentOperatorMatcher
 * @see UnnecessaryAllDifferentOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class UnnecessaryAllDifferentOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<UnnecessaryAllDifferentOperatorMatcher> {
  private UnnecessaryAllDifferentOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static UnnecessaryAllDifferentOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected UnnecessaryAllDifferentOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return UnnecessaryAllDifferentOperatorMatcher.on(engine);
  }
  
  @Override
  public UnnecessaryAllDifferentOperatorMatcher instantiate() throws ViatraQueryException {
    return UnnecessaryAllDifferentOperatorMatcher.create();
  }
  
  @Override
  public UnnecessaryAllDifferentOperatorMatch newEmptyMatch() {
    return UnnecessaryAllDifferentOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public UnnecessaryAllDifferentOperatorMatch newMatch(final Object... parameters) {
    return UnnecessaryAllDifferentOperatorMatch.newMatch((relalg.Operator) parameters[0], (relalg.AllDifferentOperator) parameters[1], (relalg.Operator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link UnnecessaryAllDifferentOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link UnnecessaryAllDifferentOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static UnnecessaryAllDifferentOperatorQuerySpecification INSTANCE = new UnnecessaryAllDifferentOperatorQuerySpecification();
    
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
    private final static UnnecessaryAllDifferentOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pInputOperator = new PParameter("inputOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pAllDifferentOperator = new PParameter("allDifferentOperator", "relalg.AllDifferentOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "AllDifferentOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pInputOperator, parameter_pAllDifferentOperator, parameter_pParentOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.unnecessaryAllDifferentOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("inputOperator","allDifferentOperator","parentOperator");
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
      		PVariable var_inputOperator = body.getOrCreateVariableByName("inputOperator");
      		PVariable var_allDifferentOperator = body.getOrCreateVariableByName("allDifferentOperator");
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		new TypeConstraint(body, new FlatTuple(var_inputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_inputOperator, parameter_pInputOperator),
      		   new ExportedParameter(body, var_allDifferentOperator, parameter_pAllDifferentOperator),
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator)
      		));
      		// 	find parentOperator(allDifferentOperator, parentOperator)
      		new PositivePatternCall(body, new FlatTuple(var_allDifferentOperator, var_parentOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	AllDifferentOperator.input(allDifferentOperator, inputOperator)
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_allDifferentOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_0_, var_inputOperator);
      		// 	find emptyOrSingleVariableAllDifferentOperator(allDifferentOperator)
      		new PositivePatternCall(body, new FlatTuple(var_allDifferentOperator), EmptyOrSingleVariableAllDifferentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
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
