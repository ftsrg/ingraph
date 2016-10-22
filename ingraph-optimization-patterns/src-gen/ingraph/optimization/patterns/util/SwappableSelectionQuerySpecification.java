/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.SwappableSelectionMatch;
import ingraph.optimization.patterns.SwappableSelectionMatcher;
import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
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
 * A pattern-specific query specification that can instantiate SwappableSelectionMatcher in a type-safe way.
 * 
 * @see SwappableSelectionMatcher
 * @see SwappableSelectionMatch
 * 
 */
@SuppressWarnings("all")
public final class SwappableSelectionQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwappableSelectionMatcher> {
  private SwappableSelectionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwappableSelectionQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwappableSelectionMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwappableSelectionMatcher.on(engine);
  }
  
  @Override
  public SwappableSelectionMatcher instantiate() throws ViatraQueryException {
    return SwappableSelectionMatcher.create();
  }
  
  @Override
  public SwappableSelectionMatch newEmptyMatch() {
    return SwappableSelectionMatch.newEmptyMatch();
  }
  
  @Override
  public SwappableSelectionMatch newMatch(final Object... parameters) {
    return SwappableSelectionMatch.newMatch((relalg.Operator) parameters[0], (relalg.SelectionOperator) parameters[1], (relalg.SelectionOperator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SwappableSelectionQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link SwappableSelectionQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SwappableSelectionQuerySpecification INSTANCE = new SwappableSelectionQuerySpecification();
    
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
    private final static SwappableSelectionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pSelectionOperator1 = new PParameter("selectionOperator1", "relalg.SelectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SelectionOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pSelectionOperator2 = new PParameter("selectionOperator2", "relalg.SelectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SelectionOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParentOperator, parameter_pSelectionOperator1, parameter_pSelectionOperator2);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.SwappableSelection";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parentOperator","selectionOperator1","selectionOperator2");
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
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		PVariable var_selectionOperator1 = body.getOrCreateVariableByName("selectionOperator1");
      		PVariable var_selectionOperator2 = body.getOrCreateVariableByName("selectionOperator2");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_selectionOperator1, parameter_pSelectionOperator1),
      		   new ExportedParameter(body, var_selectionOperator2, parameter_pSelectionOperator2)
      		));
      		// 	find parentOperator(parentOperator, selectionOperator1)
      		new PositivePatternCall(body, new FlatTuple(var_parentOperator, var_selectionOperator1), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	SelectionOperator.input(selectionOperator1, selectionOperator2)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator1, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_0_, var_selectionOperator2);
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
