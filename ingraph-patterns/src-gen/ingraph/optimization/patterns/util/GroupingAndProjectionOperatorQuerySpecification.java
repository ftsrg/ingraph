/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.GroupingAndProjectionOperatorMatch;
import ingraph.optimization.patterns.GroupingAndProjectionOperatorMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.context.IInputKey;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate GroupingAndProjectionOperatorMatcher in a type-safe way.
 * 
 * @see GroupingAndProjectionOperatorMatcher
 * @see GroupingAndProjectionOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class GroupingAndProjectionOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<GroupingAndProjectionOperatorMatcher> {
  private GroupingAndProjectionOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static GroupingAndProjectionOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected GroupingAndProjectionOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GroupingAndProjectionOperatorMatcher.on(engine);
  }
  
  @Override
  public GroupingAndProjectionOperatorMatcher instantiate() throws ViatraQueryException {
    return GroupingAndProjectionOperatorMatcher.create();
  }
  
  @Override
  public GroupingAndProjectionOperatorMatch newEmptyMatch() {
    return GroupingAndProjectionOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public GroupingAndProjectionOperatorMatch newMatch(final Object... parameters) {
    return GroupingAndProjectionOperatorMatch.newMatch((relalg.GroupingAndProjectionOperator) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link GroupingAndProjectionOperatorQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link GroupingAndProjectionOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static GroupingAndProjectionOperatorQuerySpecification INSTANCE = new GroupingAndProjectionOperatorQuerySpecification();
    
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
    private final static GroupingAndProjectionOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pGroupingAndProjectionOperator = new PParameter("groupingAndProjectionOperator", "relalg.GroupingAndProjectionOperator", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pGroupingAndProjectionOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.groupingAndProjectionOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("groupingAndProjectionOperator");
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
      		PVariable var_groupingAndProjectionOperator = body.getOrCreateVariableByName("groupingAndProjectionOperator");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_groupingAndProjectionOperator, parameter_pGroupingAndProjectionOperator)
      		));
      		// 	GroupingAndProjectionOperator(groupingAndProjectionOperator)
      		new TypeConstraint(body, new FlatTuple(var_groupingAndProjectionOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GroupingAndProjectionOperator")));
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
