/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import ingraph.optimization.patterns.DefaultExpandOperatorMatcher;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate DefaultExpandOperatorMatcher in a type-safe way.
 * 
 * @see DefaultExpandOperatorMatcher
 * @see DefaultExpandOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class DefaultExpandOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<DefaultExpandOperatorMatcher> {
  private DefaultExpandOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static DefaultExpandOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected DefaultExpandOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DefaultExpandOperatorMatcher.on(engine);
  }
  
  @Override
  public DefaultExpandOperatorMatcher instantiate() throws ViatraQueryException {
    return DefaultExpandOperatorMatcher.create();
  }
  
  @Override
  public DefaultExpandOperatorMatch newEmptyMatch() {
    return DefaultExpandOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public DefaultExpandOperatorMatch newMatch(final Object... parameters) {
    return DefaultExpandOperatorMatch.newMatch((relalg.ExpandOperator) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link DefaultExpandOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link DefaultExpandOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static DefaultExpandOperatorQuerySpecification INSTANCE = new DefaultExpandOperatorQuerySpecification();
    
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
    private final static DefaultExpandOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pExpandOperator = new PParameter("expandOperator", "relalg.ExpandOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "ExpandOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pExpandOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.defaultExpandOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("expandOperator");
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
      		PVariable var_expandOperator = body.getOrCreateVariableByName("expandOperator");
      		PVariable var_maxHops = body.getOrCreateVariableByName("maxHops");
      		new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_expandOperator, parameter_pExpandOperator)
      		));
      		//   ExpandOperator.minHops(expandOperator, 1)
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new ConstantValue(body, var__virtual_0_, 1);
      		new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_expandOperator, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "ExpandOperator", "minHops")));
      		new Equality(body, var__virtual_1_, var__virtual_0_);
      		//   ExpandOperator.maxHops(expandOperator, maxHops)
      		new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_expandOperator, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "ExpandOperator", "maxHops")));
      		new Equality(body, var__virtual_2_, var_maxHops);
      		//   MaxHops.maxHopsType(maxHops, ::LIMITED)
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new ConstantValue(body, var__virtual_3_, getEnumLiteral("http://ingraph/relalg", "MaxHopsType", "LIMITED").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_maxHops), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "MaxHops")));
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		new TypeConstraint(body, new FlatTuple(var_maxHops, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "MaxHops", "maxHopsType")));
      		new Equality(body, var__virtual_4_, var__virtual_3_);
      		//   MaxHops.hops(maxHops, 1)
      		PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      		new ConstantValue(body, var__virtual_5_, 1);
      		new TypeConstraint(body, new FlatTuple(var_maxHops), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "MaxHops")));
      		PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
      		new TypeConstraint(body, new FlatTuple(var_maxHops, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "MaxHops", "hops")));
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
  
  private static int evaluateExpression_1_1() {
    return 1;
  }
  
  private static int evaluateExpression_1_2() {
    return 1;
  }
}
