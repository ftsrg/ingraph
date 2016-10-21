/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Optimization.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.ParentOperatorMatch;
import ingraph.optimization.patterns.ParentOperatorMatcher;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ParentOperatorMatcher in a type-safe way.
 * 
 * @see ParentOperatorMatcher
 * @see ParentOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class ParentOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<ParentOperatorMatcher> {
  private ParentOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ParentOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ParentOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ParentOperatorMatcher.on(engine);
  }
  
  @Override
  public ParentOperatorMatcher instantiate() throws ViatraQueryException {
    return ParentOperatorMatcher.create();
  }
  
  @Override
  public ParentOperatorMatch newEmptyMatch() {
    return ParentOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public ParentOperatorMatch newMatch(final Object... parameters) {
    return ParentOperatorMatch.newMatch((relalg.Operator) parameters[0], (relalg.Operator) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ParentOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link ParentOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ParentOperatorQuerySpecification INSTANCE = new ParentOperatorQuerySpecification();
    
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
    private final static ParentOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pOperator = new PParameter("operator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParentOperator, parameter_pOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.parentOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parentOperator","operator");
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
      		PVariable var_operator = body.getOrCreateVariableByName("operator");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_operator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_operator, parameter_pOperator)
      		));
      		// 	AlphaOperator.input(parentOperator, operator)
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AlphaOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "AlphaOperator", "input")));
      		new Equality(body, var__virtual_0_, var_operator);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		PVariable var_operator = body.getOrCreateVariableByName("operator");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_operator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_operator, parameter_pOperator)
      		));
      		// 	BetaOperator.leftInput(parentOperator, operator)
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BetaOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BetaOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_operator);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		PVariable var_operator = body.getOrCreateVariableByName("operator");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_operator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_operator, parameter_pOperator)
      		));
      		// 	BetaOperator.rightInput(parentOperator, operator)
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BetaOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BetaOperator", "rightInput")));
      		new Equality(body, var__virtual_0_, var_operator);
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
