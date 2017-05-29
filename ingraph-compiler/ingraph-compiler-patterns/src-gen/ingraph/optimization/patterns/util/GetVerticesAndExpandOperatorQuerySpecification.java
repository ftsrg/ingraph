/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/Search2Rete.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatch;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher;
import ingraph.optimization.patterns.util.ExpandOperatorWithDefaultEdgeVariableQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate GetVerticesAndExpandOperatorMatcher in a type-safe way.
 * 
 * @see GetVerticesAndExpandOperatorMatcher
 * @see GetVerticesAndExpandOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class GetVerticesAndExpandOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<GetVerticesAndExpandOperatorMatcher> {
  private GetVerticesAndExpandOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static GetVerticesAndExpandOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected GetVerticesAndExpandOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GetVerticesAndExpandOperatorMatcher.on(engine);
  }
  
  @Override
  public GetVerticesAndExpandOperatorMatcher instantiate() throws ViatraQueryException {
    return GetVerticesAndExpandOperatorMatcher.create();
  }
  
  @Override
  public GetVerticesAndExpandOperatorMatch newEmptyMatch() {
    return GetVerticesAndExpandOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public GetVerticesAndExpandOperatorMatch newMatch(final Object... parameters) {
    return GetVerticesAndExpandOperatorMatch.newMatch((relalg.GetVerticesOperator) parameters[0], (relalg.ExpandOperator) parameters[1], (relalg.Operator) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link GetVerticesAndExpandOperatorQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link GetVerticesAndExpandOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static GetVerticesAndExpandOperatorQuerySpecification INSTANCE = new GetVerticesAndExpandOperatorQuerySpecification();
    
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
    private final static GetVerticesAndExpandOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pGetVerticesOperator = new PParameter("getVerticesOperator", "relalg.GetVerticesOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "GetVerticesOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pExpandOperator = new PParameter("expandOperator", "relalg.ExpandOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "ExpandOperator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pParentOperator = new PParameter("parentOperator", "relalg.Operator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "Operator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pGetVerticesOperator, parameter_pExpandOperator, parameter_pParentOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.getVerticesAndExpandOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("getVerticesOperator","expandOperator","parentOperator");
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
              PVariable var_getVerticesOperator = body.getOrCreateVariableByName("getVerticesOperator");
              PVariable var_expandOperator = body.getOrCreateVariableByName("expandOperator");
              PVariable var_parentOperator = body.getOrCreateVariableByName("parentOperator");
              new TypeConstraint(body, new FlatTuple(var_getVerticesOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "GetVerticesOperator")));
              new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
              new TypeConstraint(body, new FlatTuple(var_parentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_getVerticesOperator, parameter_pGetVerticesOperator),
                 new ExportedParameter(body, var_expandOperator, parameter_pExpandOperator),
                 new ExportedParameter(body, var_parentOperator, parameter_pParentOperator)
              ));
              //   find parentOperator(expandOperator, parentOperator)
              new PositivePatternCall(body, new FlatTuple(var_expandOperator, var_parentOperator), ParentOperatorQuerySpecification.instance().getInternalQueryRepresentation());
              // 	find expandOperatorWithDefaultEdgeVariable(expandOperator)
              new PositivePatternCall(body, new FlatTuple(var_expandOperator), ExpandOperatorWithDefaultEdgeVariableQuerySpecification.instance().getInternalQueryRepresentation());
              // 	ExpandOperator.input(expandOperator, getVerticesOperator)
              new TypeConstraint(body, new FlatTuple(var_expandOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "ExpandOperator")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_expandOperator, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "UnaryOperator", "input")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "Operator")));
              new Equality(body, var__virtual_0_, var_getVerticesOperator);
              //   GetVerticesOperator(getVerticesOperator)
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
