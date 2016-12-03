/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.AssociativeOperatorMatch;
import ingraph.optimization.patterns.AssociativeOperatorMatcher;
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
 * A pattern-specific query specification that can instantiate AssociativeOperatorMatcher in a type-safe way.
 * 
 * @see AssociativeOperatorMatcher
 * @see AssociativeOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class AssociativeOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<AssociativeOperatorMatcher> {
  private AssociativeOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static AssociativeOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected AssociativeOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AssociativeOperatorMatcher.on(engine);
  }
  
  @Override
  public AssociativeOperatorMatcher instantiate() throws ViatraQueryException {
    return AssociativeOperatorMatcher.create();
  }
  
  @Override
  public AssociativeOperatorMatch newEmptyMatch() {
    return AssociativeOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public AssociativeOperatorMatch newMatch(final Object... parameters) {
    return AssociativeOperatorMatch.newMatch((relalg.JoinOperator) parameters[0], (relalg.JoinOperator) parameters[1], (relalg.Operator) parameters[2], (relalg.Operator) parameters[3], (relalg.Operator) parameters[4]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link AssociativeOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link AssociativeOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static AssociativeOperatorQuerySpecification INSTANCE = new AssociativeOperatorQuerySpecification();
    
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
    private final static AssociativeOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pOp1 = new PParameter("op1", "relalg.JoinOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "JoinOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pOp2 = new PParameter("op2", "relalg.JoinOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "JoinOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pA = new PParameter("a", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pB = new PParameter("b", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pC = new PParameter("c", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pOp1, parameter_pOp2, parameter_pA, parameter_pB, parameter_pC);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.AssociativeOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("op1","op2","a","b","c");
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
      		PVariable var_op1 = body.getOrCreateVariableByName("op1");
      		PVariable var_op2 = body.getOrCreateVariableByName("op2");
      		PVariable var_a = body.getOrCreateVariableByName("a");
      		PVariable var_b = body.getOrCreateVariableByName("b");
      		PVariable var_c = body.getOrCreateVariableByName("c");
      		new TypeConstraint(body, new FlatTuple(var_op1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		new TypeConstraint(body, new FlatTuple(var_op2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		new TypeConstraint(body, new FlatTuple(var_a), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_b), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_c), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_op1, parameter_pOp1),
      		   new ExportedParameter(body, var_op2, parameter_pOp2),
      		   new ExportedParameter(body, var_a, parameter_pA),
      		   new ExportedParameter(body, var_b, parameter_pB),
      		   new ExportedParameter(body, var_c, parameter_pC)
      		));
      		// 	JoinOperator.leftInput(op2, op1)
      		new TypeConstraint(body, new FlatTuple(var_op2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_op2, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_op1);
      		// 	JoinOperator.rightInput(op2, c)
      		new TypeConstraint(body, new FlatTuple(var_op2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_op2, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_1_, var_c);
      		// 		JoinOperator.leftInput(op1, a)
      		new TypeConstraint(body, new FlatTuple(var_op1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_op1, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_2_, var_a);
      		// 	JoinOperator.rightInput(op1, b)
      		new TypeConstraint(body, new FlatTuple(var_op1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_op1, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_3_, var_b);
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
