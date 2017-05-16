/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.FoldableConstantExpressionMatch;
import ingraph.optimization.patterns.FoldableConstantExpressionMatcher;
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
 * A pattern-specific query specification that can instantiate FoldableConstantExpressionMatcher in a type-safe way.
 * 
 * @see FoldableConstantExpressionMatcher
 * @see FoldableConstantExpressionMatch
 * 
 */
@SuppressWarnings("all")
public final class FoldableConstantExpressionQuerySpecification extends BaseGeneratedEMFQuerySpecification<FoldableConstantExpressionMatcher> {
  private FoldableConstantExpressionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static FoldableConstantExpressionQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected FoldableConstantExpressionMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FoldableConstantExpressionMatcher.on(engine);
  }
  
  @Override
  public FoldableConstantExpressionMatcher instantiate() throws ViatraQueryException {
    return FoldableConstantExpressionMatcher.create();
  }
  
  @Override
  public FoldableConstantExpressionMatch newEmptyMatch() {
    return FoldableConstantExpressionMatch.newEmptyMatch();
  }
  
  @Override
  public FoldableConstantExpressionMatch newMatch(final Object... parameters) {
    return FoldableConstantExpressionMatch.newMatch((relalg.Expression) parameters[0], (relalg.Literal) parameters[1], (relalg.Literal) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link FoldableConstantExpressionQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link FoldableConstantExpressionQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static FoldableConstantExpressionQuerySpecification INSTANCE = new FoldableConstantExpressionQuerySpecification();
    
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
    private final static FoldableConstantExpressionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pE = new PParameter("e", "relalg.Expression", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pV1 = new PParameter("v1", "relalg.Literal", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Literal")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pV2 = new PParameter("v2", "relalg.Literal", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Literal")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pE, parameter_pV1, parameter_pV2);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.FoldableConstantExpression";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("e","v1","v2");
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
      		PVariable var_e = body.getOrCreateVariableByName("e");
      		PVariable var_v1 = body.getOrCreateVariableByName("v1");
      		PVariable var_v2 = body.getOrCreateVariableByName("v2");
      		new TypeConstraint(body, new FlatTuple(var_v1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		new TypeConstraint(body, new FlatTuple(var_v2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_e, parameter_pE),
      		   new ExportedParameter(body, var_v1, parameter_pV1),
      		   new ExportedParameter(body, var_v2, parameter_pV2)
      		));
      		// 	BinaryArithmeticOperationExpression.leftOperand(e, v1)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryArithmeticOperationExpression")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryArithmeticOperationExpression", "leftOperand")));
      		new Equality(body, var__virtual_0_, var_v1);
      		// 	BinaryArithmeticOperationExpression.rightOperand(e, v2)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryArithmeticOperationExpression")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryArithmeticOperationExpression", "rightOperand")));
      		new Equality(body, var__virtual_1_, var_v2);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_e = body.getOrCreateVariableByName("e");
      		PVariable var_v1 = body.getOrCreateVariableByName("v1");
      		PVariable var_v2 = body.getOrCreateVariableByName("v2");
      		new TypeConstraint(body, new FlatTuple(var_v1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		new TypeConstraint(body, new FlatTuple(var_v2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_e, parameter_pE),
      		   new ExportedParameter(body, var_v1, parameter_pV1),
      		   new ExportedParameter(body, var_v2, parameter_pV2)
      		));
      		// 	ArithmeticComparisonExpression.leftOperand(e, v1)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ArithmeticComparisonExpression")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "ArithmeticComparisonExpression", "leftOperand")));
      		new Equality(body, var__virtual_0_, var_v1);
      		// 	ArithmeticComparisonExpression.rightOperand(e, v2)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ArithmeticComparisonExpression")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "ArithmeticComparisonExpression", "rightOperand")));
      		new Equality(body, var__virtual_1_, var_v2);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_e = body.getOrCreateVariableByName("e");
      		PVariable var_v1 = body.getOrCreateVariableByName("v1");
      		PVariable var_v2 = body.getOrCreateVariableByName("v2");
      		new TypeConstraint(body, new FlatTuple(var_v1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		new TypeConstraint(body, new FlatTuple(var_v2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Literal")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_e, parameter_pE),
      		   new ExportedParameter(body, var_v1, parameter_pV1),
      		   new ExportedParameter(body, var_v2, parameter_pV2)
      		));
      		// 	BinaryLogicalExpression.leftOperand(e, v1)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "leftOperand")));
      		new Equality(body, var__virtual_0_, var_v1);
      		// 	BinaryLogicalExpression.rightOperand(e, v2)
      		new TypeConstraint(body, new FlatTuple(var_e), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_e, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "rightOperand")));
      		new Equality(body, var__virtual_1_, var_v2);
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
