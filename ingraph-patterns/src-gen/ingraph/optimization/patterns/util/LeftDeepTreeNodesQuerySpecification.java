/**
 * Generated from platform:/resource/ingraph-patterns/src/ingraph/optimization/patterns/Relalg2Rete.vql
 */
package ingraph.optimization.patterns.util;

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

import com.google.common.collect.Sets;

import ingraph.optimization.patterns.LeftDeepTreeNodesMatch;
import ingraph.optimization.patterns.LeftDeepTreeNodesMatcher;

/**
 * A pattern-specific query specification that can instantiate LeftDeepTreeNodesMatcher in a type-safe way.
 * 
 * @see LeftDeepTreeNodesMatcher
 * @see LeftDeepTreeNodesMatch
 * 
 */
@SuppressWarnings("all")
public final class LeftDeepTreeNodesQuerySpecification extends BaseGeneratedEMFQuerySpecification<LeftDeepTreeNodesMatcher> {
  private LeftDeepTreeNodesQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static LeftDeepTreeNodesQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected LeftDeepTreeNodesMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LeftDeepTreeNodesMatcher.on(engine);
  }
  
  @Override
  public LeftDeepTreeNodesMatcher instantiate() throws ViatraQueryException {
    return LeftDeepTreeNodesMatcher.create();
  }
  
  @Override
  public LeftDeepTreeNodesMatch newEmptyMatch() {
    return LeftDeepTreeNodesMatch.newEmptyMatch();
  }
  
  @Override
  public LeftDeepTreeNodesMatch newMatch(final Object... parameters) {
    return LeftDeepTreeNodesMatch.newMatch((relalg.BinaryLogicalExpression) parameters[0], (relalg.BinaryLogicalExpression) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link LeftDeepTreeNodesQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link LeftDeepTreeNodesQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static LeftDeepTreeNodesQuerySpecification INSTANCE = new LeftDeepTreeNodesQuerySpecification();
    
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
    private final static LeftDeepTreeNodesQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pParent = new PParameter("parent", "relalg.BinaryLogicalExpression", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "BinaryLogicalExpression")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pChild = new PParameter("child", "relalg.BinaryLogicalExpression", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://ingraph/relalg", "BinaryLogicalExpression")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pParent, parameter_pChild);
    
    @Override
    public String getFullyQualifiedName() {
      return "ingraph.optimization.patterns.leftDeepTreeNodes";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("parent","child");
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
      		PVariable var_parent = body.getOrCreateVariableByName("parent");
      		PVariable var_child = body.getOrCreateVariableByName("child");
      		new TypeConstraint(body, new FlatTuple(var_parent), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		new TypeConstraint(body, new FlatTuple(var_child), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_parent, parameter_pParent),
      		   new ExportedParameter(body, var_child, parameter_pChild)
      		));
      		//   BinaryLogicalExpression.leftOperand(parent, child)
      		new TypeConstraint(body, new FlatTuple(var_parent), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_parent, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://ingraph/relalg", "BinaryLogicalExpression", "leftOperand")));
      		new Equality(body, var__virtual_0_, var_child);
      		//   BinaryLogicalExpression(child)
      		new TypeConstraint(body, new FlatTuple(var_child), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://ingraph/relalg", "BinaryLogicalExpression")));
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
