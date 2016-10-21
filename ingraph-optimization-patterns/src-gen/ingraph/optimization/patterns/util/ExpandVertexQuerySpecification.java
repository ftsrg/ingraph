/**
 * Generated from platform:/resource/ingraph-optimization-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.ExpandVertexMatch;
import ingraph.optimization.patterns.ExpandVertexMatcher;
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
 * A pattern-specific query specification that can instantiate ExpandVertexMatcher in a type-safe way.
 * 
 * @see ExpandVertexMatcher
 * @see ExpandVertexMatch
 * 
 */
@SuppressWarnings("all")
public final class ExpandVertexQuerySpecification extends BaseGeneratedEMFQuerySpecification<ExpandVertexMatcher> {
  private ExpandVertexQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExpandVertexQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExpandVertexMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExpandVertexMatcher.on(engine);
  }
  
  @Override
  public ExpandVertexMatcher instantiate() throws ViatraQueryException {
    return ExpandVertexMatcher.create();
  }
  
  @Override
  public ExpandVertexMatch newEmptyMatch() {
    return ExpandVertexMatch.newEmptyMatch();
  }
  
  @Override
  public ExpandVertexMatch newMatch(final Object... parameters) {
    return ExpandVertexMatch.newMatch((relalg.Operator) parameters[0], (relalg.GetVerticesOperator) parameters[1], (relalg.ExpandOperator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ExpandVertexQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link ExpandVertexQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ExpandVertexQuerySpecification INSTANCE = new ExpandVertexQuerySpecification();
    
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
    private final static ExpandVertexQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pGetVerticesOperator = new PParameter("getVerticesOperator", "relalg.GetVerticesOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "GetVerticesOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pExpandOperator = new PParameter("expandOperator", "relalg.ExpandOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "ExpandOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParentOperator, parameter_pGetVerticesOperator, parameter_pExpandOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.expandVertex";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parentOperator","getVerticesOperator","expandOperator");
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
      		PVariable var_getVerticesOperator = body.getOrCreateVariableByName("getVerticesOperator");
      		PVariable var_expandOperator = body.getOrCreateVariableByName("expandOperator");
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		new TypeConstraint(body, new FlatTuple(var_getVerticesOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GetVerticesOperator")));
      		new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator),
      		   new ExportedParameter(body, var_getVerticesOperator, parameter_pGetVerticesOperator),
      		   new ExportedParameter(body, var_expandOperator, parameter_pExpandOperator)
      		));
      		// 	find parentOperator(parentOperator, expandOperator)
      		new PositivePatternCall(body, new FlatTuple(var_parentOperator, var_expandOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		// 	ExpandOperator.input(expandOperator, getVerticesOperator)
      		new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_expandOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_0_, var_getVerticesOperator);
      		// 	GetVerticesOperator(getVerticesOperator)
      		new TypeConstraint(body, new FlatTuple(var_getVerticesOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GetVerticesOperator")));
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
