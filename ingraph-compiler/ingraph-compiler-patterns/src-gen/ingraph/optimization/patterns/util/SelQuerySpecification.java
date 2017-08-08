/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/MergeLeftOuterJoins.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.SelMatch;
import ingraph.optimization.patterns.SelMatcher;
import ingraph.optimization.patterns.util.LeftDeepTreeNodesQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.BinaryTransitiveClosure;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SelMatcher in a type-safe way.
 * 
 * @see SelMatcher
 * @see SelMatch
 * 
 */
@SuppressWarnings("all")
public final class SelQuerySpecification extends BaseGeneratedEMFQuerySpecification<SelMatcher> {
  private SelQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SelQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SelMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SelMatcher.on(engine);
  }
  
  @Override
  public SelMatcher instantiate() throws ViatraQueryException {
    return SelMatcher.create();
  }
  
  @Override
  public SelMatch newEmptyMatch() {
    return SelMatch.newEmptyMatch();
  }
  
  @Override
  public SelMatch newMatch(final Object... parameters) {
    return SelMatch.newMatch((relalg.LeftOuterJoinOperator) parameters[0], (relalg.SelectionOperator) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SelQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link SelQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SelQuerySpecification INSTANCE = new SelQuerySpecification();
    
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
    private final static SelQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pLojOperator = new PParameter("lojOperator", "relalg.LeftOuterJoinOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "LeftOuterJoinOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pSelectionOperator = new PParameter("selectionOperator", "relalg.SelectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "SelectionOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pLojOperator, parameter_pSelectionOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.sel";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("lojOperator","selectionOperator");
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
      		PVariable var_lojOperator = body.getOrCreateVariableByName("lojOperator");
      		PVariable var_selectionOperator = body.getOrCreateVariableByName("selectionOperator");
      		PVariable var_expression = body.getOrCreateVariableByName("expression");
      		PVariable var_expression2 = body.getOrCreateVariableByName("expression2");
      		PVariable var_notNull = body.getOrCreateVariableByName("notNull");
      		new TypeConstraint(body, new FlatTuple(var_lojOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_lojOperator, parameter_pLojOperator),
      		   new ExportedParameter(body, var_selectionOperator, parameter_pSelectionOperator)
      		));
      		//   SelectionOperator.condition(selectionOperator, expression)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "AbstractCondition", "condition")));
      		new Equality(body, var__virtual_0_, var_expression);
      		//   SelectionOperator.input(selectionOperator, lojOperator)
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "SelectionOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_selectionOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_1_, var_lojOperator);
      		//   BinaryLogicalExpression.operator(expression, ::AND)
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new ConstantValue(body, var__virtual_2_, getEnumLiteral("http://ingraph/relalg", "BinaryLogicalOperatorType", "AND").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_expression), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_expression, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "operator")));
      		new Equality(body, var__virtual_3_, var__virtual_2_);
      		//   find leftDeepTreeNodes+(expression, expression2)
      		new BinaryTransitiveClosure(body, new FlatTuple(var_expression, var_expression2), LeftDeepTreeNodesQuerySpecification.instance().getInternalQueryRepresentation());
      		//   BinaryLogicalExpression.rightOperand(expression2, notNull)
      		new TypeConstraint(body, new FlatTuple(var_expression2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		new TypeConstraint(body, new FlatTuple(var_expression2, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "rightOperand")));
      		new Equality(body, var__virtual_4_, var_notNull);
      		//   UnaryGraphObjectLogicalExpression.operator(notNull, ::IS_NOT_NULL)
      		PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      		new ConstantValue(body, var__virtual_5_, getEnumLiteral("http://ingraph/relalg", "UnaryGraphObjectLogicalOperatorType", "IS_NOT_NULL").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_notNull), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "UnaryGraphObjectLogicalExpression")));
      		PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
      		new TypeConstraint(body, new FlatTuple(var_notNull, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryGraphObjectLogicalExpression", "operator")));
      		new Equality(body, var__virtual_6_, var__virtual_5_);
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
