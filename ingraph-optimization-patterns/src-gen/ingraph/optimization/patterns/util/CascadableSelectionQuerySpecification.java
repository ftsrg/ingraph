/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/TrivialOptimizations.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.CascadableSelectionMatch;
import ingraph.optimization.patterns.CascadableSelectionMatcher;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate CascadableSelectionMatcher in a type-safe way.
 * 
 * @see CascadableSelectionMatcher
 * @see CascadableSelectionMatch
 * 
 */
@SuppressWarnings("all")
public final class CascadableSelectionQuerySpecification extends BaseGeneratedEMFQuerySpecification<CascadableSelectionMatcher> {
  private CascadableSelectionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static CascadableSelectionQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected CascadableSelectionMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CascadableSelectionMatcher.on(engine);
  }
  
  @Override
  public CascadableSelectionMatcher instantiate() throws ViatraQueryException {
    return CascadableSelectionMatcher.create();
  }
  
  @Override
  public CascadableSelectionMatch newEmptyMatch() {
    return CascadableSelectionMatch.newEmptyMatch();
  }
  
  @Override
  public CascadableSelectionMatch newMatch(final Object... parameters) {
    return CascadableSelectionMatch.newMatch((relalg.Operator) parameters[0], (relalg.SelectionOperator) parameters[1], (relalg.LogicalExpression) parameters[2], (relalg.LogicalExpression) parameters[3]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link CascadableSelectionQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link CascadableSelectionQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static CascadableSelectionQuerySpecification INSTANCE = new CascadableSelectionQuerySpecification();
    
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
    private final static CascadableSelectionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pSelectionOperator = new PParameter("selectionOperator", "relalg.SelectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SelectionOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pLeftOperand = new PParameter("leftOperand", "relalg.LogicalExpression", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "LogicalExpression")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pRightOperand = new PParameter("rightOperand", "relalg.LogicalExpression", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "LogicalExpression")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParentOperator, parameter_pSelectionOperator, parameter_pLeftOperand, parameter_pRightOperand);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.CascadableSelection";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parentOperator","selectionOperator","leftOperand","rightOperand");
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
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		PVariable var_selectionOperator = body.getOrCreateVariableByName("selectionOperator");
      		PVariable var_leftOperand = body.getOrCreateVariableByName("leftOperand");
      		PVariable var_rightOperand = body.getOrCreateVariableByName("rightOperand");
      		PVariable var_condition = body.getOrCreateVariableByName("condition");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		new TypeConstraint(body, new FlatTuple(var_leftOperand), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LogicalExpression")));
      		new TypeConstraint(body, new FlatTuple(var_rightOperand), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LogicalExpression")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_selectionOperator, parameter_pSelectionOperator),
      		   new ExportedParameter(body, var_leftOperand, parameter_pLeftOperand),
      		   new ExportedParameter(body, var_rightOperand, parameter_pRightOperand)
      		));
      		// 	find parentOperator(parentOperator, selectionOperator)
      		new PositivePatternCall(body, new FlatTuple(var_parentOperator, var_selectionOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	SelectionOperator.condition(selectionOperator, condition)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "SelectionOperator", "condition")));
      		new Equality(body, var__virtual_0_, var_condition);
      		// 	// condition: leftOperand AND rightOperand	BinaryLogicalExpression.operator(condition, ::AND)
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new ConstantValue(body, var__virtual_1_, getEnumLiteral("http://ingraph/relalg", "BinaryLogicalOperator", "AND").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_condition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_condition, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "operator")));
      		new Equality(body, var__virtual_2_, var__virtual_1_);
      		// 	BinaryLogicalExpression.leftOperand(condition, leftOperand)
      		new TypeConstraint(body, new FlatTuple(var_condition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_condition, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "leftOperand")));
      		new Equality(body, var__virtual_3_, var_leftOperand);
      		// 	BinaryLogicalExpression.rightOperand(condition, rightOperand)
      		new TypeConstraint(body, new FlatTuple(var_condition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		new TypeConstraint(body, new FlatTuple(var_condition, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "rightOperand")));
      		new Equality(body, var__virtual_4_, var_rightOperand);
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
