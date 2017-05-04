/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.JoinOnDualMatch;
import ingraph.optimization.patterns.JoinOnDualMatcher;
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
 * A pattern-specific query specification that can instantiate JoinOnDualMatcher in a type-safe way.
 * 
 * @see JoinOnDualMatcher
 * @see JoinOnDualMatch
 * 
 */
@SuppressWarnings("all")
public final class JoinOnDualQuerySpecification extends BaseGeneratedEMFQuerySpecification<JoinOnDualMatcher> {
  private JoinOnDualQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static JoinOnDualQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected JoinOnDualMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return JoinOnDualMatcher.on(engine);
  }
  
  @Override
  public JoinOnDualMatcher instantiate() throws ViatraQueryException {
    return JoinOnDualMatcher.create();
  }
  
  @Override
  public JoinOnDualMatch newEmptyMatch() {
    return JoinOnDualMatch.newEmptyMatch();
  }
  
  @Override
  public JoinOnDualMatch newMatch(final Object... parameters) {
    return JoinOnDualMatch.newMatch((relalg.Operator) parameters[0], (relalg.EquiJoinLikeOperator) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link JoinOnDualQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link JoinOnDualQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static JoinOnDualQuerySpecification INSTANCE = new JoinOnDualQuerySpecification();
    
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
    private final static JoinOnDualQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pOtherInputOperator = new PParameter("otherInputOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pEquiJoinLikeOperator = new PParameter("equiJoinLikeOperator", "relalg.EquiJoinLikeOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "EquiJoinLikeOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pOtherInputOperator, parameter_pEquiJoinLikeOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.joinOnDual";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("otherInputOperator","equiJoinLikeOperator");
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
      		PVariable var_otherInputOperator = body.getOrCreateVariableByName("otherInputOperator");
      		PVariable var_equiJoinLikeOperator = body.getOrCreateVariableByName("equiJoinLikeOperator");
      		PVariable var_dualOperator = body.getOrCreateVariableByName("dualOperator");
      		new TypeConstraint(body, new FlatTuple(var_otherInputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EquiJoinLikeOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_otherInputOperator, parameter_pOtherInputOperator),
      		   new ExportedParameter(body, var_equiJoinLikeOperator, parameter_pEquiJoinLikeOperator)
      		));
      		// 	DualObjectSourceOperator(dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_dualOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "DualObjectSourceOperator")));
      		// 	JoinOperator.leftInput(equiJoinLikeOperator, dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_dualOperator);
      		// 	JoinOperator.rightInput(equiJoinLikeOperator, otherInputOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_1_, var_otherInputOperator);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_otherInputOperator = body.getOrCreateVariableByName("otherInputOperator");
      		PVariable var_equiJoinLikeOperator = body.getOrCreateVariableByName("equiJoinLikeOperator");
      		PVariable var_dualOperator = body.getOrCreateVariableByName("dualOperator");
      		new TypeConstraint(body, new FlatTuple(var_otherInputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EquiJoinLikeOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_otherInputOperator, parameter_pOtherInputOperator),
      		   new ExportedParameter(body, var_equiJoinLikeOperator, parameter_pEquiJoinLikeOperator)
      		));
      		//   DualObjectSourceOperator(dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_dualOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "DualObjectSourceOperator")));
      		//   JoinOperator.leftInput(equiJoinLikeOperator, otherInputOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_otherInputOperator);
      		//   JoinOperator.rightInput(equiJoinLikeOperator, dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "JoinOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_1_, var_dualOperator);
      		bodies.add(body);
      	}
      	{
      		PBody body = new PBody(this);
      		PVariable var_otherInputOperator = body.getOrCreateVariableByName("otherInputOperator");
      		PVariable var_equiJoinLikeOperator = body.getOrCreateVariableByName("equiJoinLikeOperator");
      		PVariable var_dualOperator = body.getOrCreateVariableByName("dualOperator");
      		new TypeConstraint(body, new FlatTuple(var_otherInputOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EquiJoinLikeOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_otherInputOperator, parameter_pOtherInputOperator),
      		   new ExportedParameter(body, var_equiJoinLikeOperator, parameter_pEquiJoinLikeOperator)
      		));
      		//   DualObjectSourceOperator(dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_dualOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "DualObjectSourceOperator")));
      		//   LeftOuterJoinOperator.leftInput(equiJoinLikeOperator, otherInputOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "leftInput")));
      		new Equality(body, var__virtual_0_, var_otherInputOperator);
      		//   LeftOuterJoinOperator.rightInput(equiJoinLikeOperator, dualOperator)
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "LeftOuterJoinOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_equiJoinLikeOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryOperator", "rightInput")));
      		new Equality(body, var__virtual_1_, var_dualOperator);
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
