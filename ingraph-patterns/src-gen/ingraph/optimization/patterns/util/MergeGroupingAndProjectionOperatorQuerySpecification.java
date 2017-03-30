/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.MergeGroupingAndProjectionOperatorMatch;
import ingraph.optimization.patterns.MergeGroupingAndProjectionOperatorMatcher;
import ingraph.optimization.patterns.util.GroupingAndProjectionOperatorQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate MergeGroupingAndProjectionOperatorMatcher in a type-safe way.
 * 
 * @see MergeGroupingAndProjectionOperatorMatcher
 * @see MergeGroupingAndProjectionOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class MergeGroupingAndProjectionOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<MergeGroupingAndProjectionOperatorMatcher> {
  private MergeGroupingAndProjectionOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static MergeGroupingAndProjectionOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected MergeGroupingAndProjectionOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MergeGroupingAndProjectionOperatorMatcher.on(engine);
  }
  
  @Override
  public MergeGroupingAndProjectionOperatorMatcher instantiate() throws ViatraQueryException {
    return MergeGroupingAndProjectionOperatorMatcher.create();
  }
  
  @Override
  public MergeGroupingAndProjectionOperatorMatch newEmptyMatch() {
    return MergeGroupingAndProjectionOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public MergeGroupingAndProjectionOperatorMatch newMatch(final Object... parameters) {
    return MergeGroupingAndProjectionOperatorMatch.newMatch((relalg.ProjectionOperator) parameters[0], (relalg.GroupingOperator) parameters[1], (relalg.Operator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link MergeGroupingAndProjectionOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link MergeGroupingAndProjectionOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static MergeGroupingAndProjectionOperatorQuerySpecification INSTANCE = new MergeGroupingAndProjectionOperatorQuerySpecification();
    
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
    private final static MergeGroupingAndProjectionOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pProjectionOperator = new PParameter("projectionOperator", "relalg.ProjectionOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "ProjectionOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pGroupingOperator = new PParameter("groupingOperator", "relalg.GroupingOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "GroupingOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pProjectionOperator, parameter_pGroupingOperator, parameter_pParentOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.mergeGroupingAndProjectionOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("projectionOperator","groupingOperator","parentOperator");
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
      		PVariable var_projectionOperator = body.getOrCreateVariableByName("projectionOperator");
      		PVariable var_groupingOperator = body.getOrCreateVariableByName("groupingOperator");
      		PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
      		new TypeConstraint(body, new FlatTuple(var_projectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ProjectionOperator")));
      		new TypeConstraint(body, new FlatTuple(var_groupingOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GroupingOperator")));
      		new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_projectionOperator, parameter_pProjectionOperator),
      		   new ExportedParameter(body, var_groupingOperator, parameter_pGroupingOperator),
      		   new ExportedParameter(body, var_parentOperator, parameter_pParentOperator)
      		));
      		//   find parentOperator(projectionOperator, parentOperator)
      		new PositivePatternCall(body, new FlatTuple(var_projectionOperator, var_parentOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		//   ProjectionOperator.input(projectionOperator, groupingOperator)
      		new TypeConstraint(body, new FlatTuple(var_projectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ProjectionOperator")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_projectionOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
      		new Equality(body, var__virtual_0_, var_groupingOperator);
      		//   // do not merge operators that are already GroupingAndProjectionOperators  neg find groupingAndProjectionOperator(projectionOperator)
      		new NegativePatternCall(body, new FlatTuple(var_projectionOperator), GroupingAndProjectionOperatorQuerySpecification.instance().getInternalQueryRepresentation());
      		//   neg find groupingAndProjectionOperator(groupingOperator)
      		new NegativePatternCall(body, new FlatTuple(var_groupingOperator), GroupingAndProjectionOperatorQuerySpecification.instance().getInternalQueryRepresentation());
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
