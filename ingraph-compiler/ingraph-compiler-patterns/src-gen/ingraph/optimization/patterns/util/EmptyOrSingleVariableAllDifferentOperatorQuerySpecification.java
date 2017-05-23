/**
 * Generated from platform:/resource/ingraph-compiler-patterns/src/ingraph/optimization/patterns/RelalgSimplifier.vql
 */
package ingraph.optimization.patterns.util;

import com.google.common.collect.Sets;
import ingraph.optimization.patterns.EmptyOrSingleVariableAllDifferentOperatorMatch;
import ingraph.optimization.patterns.EmptyOrSingleVariableAllDifferentOperatorMatcher;
import ingraph.optimization.patterns.util.AllDifferentOperatorEdgeVariablesQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.PatternMatchCounter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate EmptyOrSingleVariableAllDifferentOperatorMatcher in a type-safe way.
 * 
 * @see EmptyOrSingleVariableAllDifferentOperatorMatcher
 * @see EmptyOrSingleVariableAllDifferentOperatorMatch
 * 
 */
@SuppressWarnings("all")
public final class EmptyOrSingleVariableAllDifferentOperatorQuerySpecification extends BaseGeneratedEMFQuerySpecification<EmptyOrSingleVariableAllDifferentOperatorMatcher> {
  private EmptyOrSingleVariableAllDifferentOperatorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static EmptyOrSingleVariableAllDifferentOperatorQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected EmptyOrSingleVariableAllDifferentOperatorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EmptyOrSingleVariableAllDifferentOperatorMatcher.on(engine);
  }
  
  @Override
  public EmptyOrSingleVariableAllDifferentOperatorMatcher instantiate() throws ViatraQueryException {
    return EmptyOrSingleVariableAllDifferentOperatorMatcher.create();
  }
  
  @Override
  public EmptyOrSingleVariableAllDifferentOperatorMatch newEmptyMatch() {
    return EmptyOrSingleVariableAllDifferentOperatorMatch.newEmptyMatch();
  }
  
  @Override
  public EmptyOrSingleVariableAllDifferentOperatorMatch newMatch(final Object... parameters) {
    return EmptyOrSingleVariableAllDifferentOperatorMatch.newMatch((relalg.AllDifferentOperator) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link EmptyOrSingleVariableAllDifferentOperatorQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link EmptyOrSingleVariableAllDifferentOperatorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static EmptyOrSingleVariableAllDifferentOperatorQuerySpecification INSTANCE = new EmptyOrSingleVariableAllDifferentOperatorQuerySpecification();
    
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
    private final static EmptyOrSingleVariableAllDifferentOperatorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pAllDifferentOperator = new PParameter("allDifferentOperator", "relalg.AllDifferentOperator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "AllDifferentOperator")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pAllDifferentOperator);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.emptyOrSingleVariableAllDifferentOperator";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("allDifferentOperator");
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
              PVariable var_allDifferentOperator = body.getOrCreateVariableByName("allDifferentOperator");
              PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
              new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_allDifferentOperator, parameter_pAllDifferentOperator)
              ));
              // 	// empty	0 == count find allDifferentOperatorEdgeVariables(allDifferentOperator, _)
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new ConstantValue(body, var__virtual_0_, 0);
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new PatternMatchCounter(body, new FlatTuple(var_allDifferentOperator, var___0_), AllDifferentOperatorEdgeVariablesQuerySpecification.instance().getInternalQueryRepresentation(), var__virtual_1_);
              new Equality(body, var__virtual_0_, var__virtual_1_);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_allDifferentOperator = body.getOrCreateVariableByName("allDifferentOperator");
              PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
              PVariable var_edgeVariable = body.getOrCreateVariableByName("edgeVariable");
              new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_allDifferentOperator, parameter_pAllDifferentOperator)
              ));
              // 	1 == count find allDifferentOperatorEdgeVariables(allDifferentOperator, _)
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new ConstantValue(body, var__virtual_0_, 1);
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new PatternMatchCounter(body, new FlatTuple(var_allDifferentOperator, var___0_), AllDifferentOperatorEdgeVariablesQuerySpecification.instance().getInternalQueryRepresentation(), var__virtual_1_);
              new Equality(body, var__virtual_0_, var__virtual_1_);
              // 	AllDifferentOperator.edgeVariables(allDifferentOperator, edgeVariable)
              new TypeConstraint(body, new FlatTuple(var_allDifferentOperator), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AllDifferentOperator")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_allDifferentOperator, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "AllDifferentOperator", "edgeVariables")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "AbstractEdgeVariable")));
              new Equality(body, var__virtual_2_, var_edgeVariable);
              // 	// EdgeVariable and *not* an EdgeListVariable	EdgeVariable(edgeVariable)
              new TypeConstraint(body, new FlatTuple(var_edgeVariable), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "EdgeVariable")));
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
    return 0;
  }
  
  private static int evaluateExpression_2_1() {
    return 1;
  }
}
