/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.util.ParentOperatorQuerySpecification;
import ingraph.optimization.patterns.util.VariablesInLogicalExpressionQuerySpecification;
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
 * A pattern-specific query specification that can instantiate LeftOuterJoinAndSelectionMatcher in a type-safe way.
 * 
 * @see LeftOuterJoinAndSelectionMatcher
 * @see LeftOuterJoinAndSelectionMatch
 * 
 */
@SuppressWarnings("all")
public final class LeftOuterJoinAndSelectionQuerySpecification extends BaseGeneratedEMFQuerySpecification<LeftOuterJoinAndSelectionMatcher> {
  private LeftOuterJoinAndSelectionQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static LeftOuterJoinAndSelectionQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected LeftOuterJoinAndSelectionMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftOuterJoinAndSelectionMatcher.on(engine);
  }
  
  @Override
  public LeftOuterJoinAndSelectionMatcher instantiate() throws ViatraQueryException {
    return LeftOuterJoinAndSelectionMatcher.create();
  }
  
  @Override
  public LeftOuterJoinAndSelectionMatch newEmptyMatch() {
    return LeftOuterJoinAndSelectionMatch.newEmptyMatch();
  }
  
  @Override
  public LeftOuterJoinAndSelectionMatch newMatch(final Object... parameters) {
    return LeftOuterJoinAndSelectionMatch.newMatch((relalg.Operator) parameters[0], (relalg.SelectionOperator) parameters[1], (relalg.LeftOuterJoinOperator) parameters[2], (relalg.Operator) parameters[3], (relalg.GetEdgesOperator) parameters[4]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link LeftOuterJoinAndSelectionQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link LeftOuterJoinAndSelectionQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static LeftOuterJoinAndSelectionQuerySpecification INSTANCE = new LeftOuterJoinAndSelectionQuerySpecification();
    
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
    private final static LeftOuterJoinAndSelectionQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pSelectionOperator = new PParameter("selectionOperator", "relalg.SelectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SelectionOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pLeftOuterJoinOperator = new PParameter("leftOuterJoinOperator", "relalg.LeftOuterJoinOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "LeftOuterJoinOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pLeftInputOperator = new PParameter("leftInputOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pGetEdgesOperator = new PParameter("getEdgesOperator", "relalg.GetEdgesOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "GetEdgesOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParentOperator, parameter_pSelectionOperator, parameter_pLeftOuterJoinOperator, parameter_pLeftInputOperator, parameter_pGetEdgesOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.leftOuterJoinAndSelection";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parentOperator","selectionOperator","leftOuterJoinOperator","leftInputOperator","getEdgesOperator");
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
      		PVariable var_leftOuterJoinOperator = body.getOrCreateVariableByName("leftOuterJoinOperator");
      		PVariable var_leftInputOperator = body.getOrCreateVariableByName("leftInputOperator");
      		PVariable var_getEdgesOperator = body.getOrCreateVariableByName("getEdgesOperator");
      		PVariable var_condition = body.getOrCreateVariableByName("condition");
      		PVariable var_conditionInternalExpression = body.getOrCreateVariableByName("conditionInternalExpression");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		new TypeConstraint(body, new FlatTuple(var_leftInputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_getEdgesOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GetEdgesOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_selectionOperator, parameter_pSelectionOperator),
      		   new ExportedParameter(body, var_leftOuterJoinOperator, parameter_pLeftOuterJoinOperator),
      		   new ExportedParameter(body, var_leftInputOperator, parameter_pLeftInputOperator),
      		   new ExportedParameter(body, var_getEdgesOperator, parameter_pGetEdgesOperator)
      		));
      		//   find parentOperator(selectionOperator, parentOperator)
      		new PositivePatternCall(body, new FlatTuple(var_selectionOperator, var_parentOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		//   SelectionOperator.input(selectionOperator, leftOuterJoinOperator)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_0_, var_leftOuterJoinOperator);
      		//   SelectionOperator.condition(selectionOperator, condition)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "AbstractCondition", "condition")));
      		new Equality(body, var__virtual_1_, var_condition);
      		//   UnaryLogicalExpression.operator(condition, ::NOT)
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new ConstantValue(body, var__virtual_2_, getEnumLiteral("http://ingraph/relalg", "UnaryLogicalOperatorType", "NOT").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_condition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "UnaryLogicalExpression")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_condition, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryLogicalExpression", "operator")));
      		new Equality(body, var__virtual_3_, var__virtual_2_);
      		//   UnaryLogicalExpression.operand(condition, conditionInternalExpression)
      		new TypeConstraint(body, new FlatTuple(var_condition), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "UnaryLogicalExpression")));
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		new TypeConstraint(body, new FlatTuple(var_condition, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryLogicalExpression", "operand")));
      		new Equality(body, var__virtual_4_, var_conditionInternalExpression);
      		//     find variablesInLogicalExpression(conditionInternalExpression)
      		new PositivePatternCall(body, new FlatTuple(var_conditionInternalExpression), VariablesInLogicalExpressionQuerySpecification.instance().getInternalQueryRepresentation());
      		//     LeftOuterJoinOperator.leftInput(leftOuterJoinOperator, leftInputOperator)
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator, var__virtual_5_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_5_, var_leftInputOperator);
      		//   LeftOuterJoinOperator.rightInput(leftOuterJoinOperator, getEdgesOperator)
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
      		new TypeConstraint(body, new FlatTuple(var_leftOuterJoinOperator, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_6_, var_getEdgesOperator);
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
