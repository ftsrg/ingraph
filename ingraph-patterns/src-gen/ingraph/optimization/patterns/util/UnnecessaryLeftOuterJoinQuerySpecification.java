/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.UnnecessaryLeftOuterJoinMatch;
import ingraph.optimization.patterns.UnnecessaryLeftOuterJoinMatcher;
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
 * A pattern-specific query specification that can instantiate UnnecessaryLeftOuterJoinMatcher in a type-safe way.
 * 
 * @see UnnecessaryLeftOuterJoinMatcher
 * @see UnnecessaryLeftOuterJoinMatch
 * 
 */
@SuppressWarnings("all")
public final class UnnecessaryLeftOuterJoinQuerySpecification extends BaseGeneratedEMFQuerySpecification<UnnecessaryLeftOuterJoinMatcher> {
  private UnnecessaryLeftOuterJoinQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static UnnecessaryLeftOuterJoinQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected UnnecessaryLeftOuterJoinMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return UnnecessaryLeftOuterJoinMatcher.on(engine);
  }
  
  @Override
  public UnnecessaryLeftOuterJoinMatcher instantiate() throws ViatraQueryException {
    return UnnecessaryLeftOuterJoinMatcher.create();
  }
  
  @Override
  public UnnecessaryLeftOuterJoinMatch newEmptyMatch() {
    return UnnecessaryLeftOuterJoinMatch.newEmptyMatch();
  }
  
  @Override
  public UnnecessaryLeftOuterJoinMatch newMatch(final Object... parameters) {
    return UnnecessaryLeftOuterJoinMatch.newMatch((relalg.Operator) parameters[0], (relalg.LeftOuterJoinOperator) parameters[1], (relalg.Operator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link UnnecessaryLeftOuterJoinQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link UnnecessaryLeftOuterJoinQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static UnnecessaryLeftOuterJoinQuerySpecification INSTANCE = new UnnecessaryLeftOuterJoinQuerySpecification();
    
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
    private final static UnnecessaryLeftOuterJoinQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pInputOperator = new PParameter("inputOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pLeftOuterJoinOperator = new PParameter("leftOuterJoinOperator", "relalg.LeftOuterJoinOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "LeftOuterJoinOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pInputOperator, parameter_pLeftOuterJoinOperator, parameter_pParentOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.unnecessaryLeftOuterJoin";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("inputOperator","leftOuterJoinOperator","parentOperator");
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
      		PVariable var_leftOuterJoinOperator = body.getOrCreateVariableByName("leftOuterJoinOperator");
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		PVariable var_dualOperator = body.getOrCreateVariableByName("dualOperator");
      		new TypeConstraint(body, new FlatTuple(var_inputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_inputOperator, parameter_pInputOperator),
      		   new ExportedParameter(body, var_leftOuterJoinOperator, parameter_pLeftOuterJoinOperator),
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator)
      		));
      		// 	find parentOperator(leftOuterJoinOperator, parentOperator)
      		new PositivePatternCall(body, new FlatTuple(var_leftOuterJoinOperator, var_parentOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	LeftOuterJoinOperator.leftInput(leftOuterJoinOperator, inputOperator)
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_inputOperator);
      		// 	LeftOuterJoinOperator.rightInput(leftOuterJoinOperator, dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_1_, var_dualOperator);
      		// 	DualObjectSourceOperator(dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_dualOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "DualObjectSourceOperator")));
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
