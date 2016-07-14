/**
 * Generated from platform:/resource/hello-patterns/src/relalg/my.vql
 */
package relalg.util;

import com.google.common.collect.Sets;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;
import relalg.TrimmerUpMatch;
import relalg.TrimmerUpMatcher;

/**
 * A pattern-specific query specification that can instantiate TrimmerUpMatcher in a type-safe way.
 * 
 * @see TrimmerUpMatcher
 * @see TrimmerUpMatch
 * 
 */
@SuppressWarnings("all")
public final class TrimmerUpQuerySpecification extends BaseGeneratedEMFQuerySpecification<TrimmerUpMatcher> {
  private TrimmerUpQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static TrimmerUpQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected TrimmerUpMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TrimmerUpMatcher.on(engine);
  }
  
  @Override
  public TrimmerUpMatch newEmptyMatch() {
    return TrimmerUpMatch.newEmptyMatch();
  }
  
  @Override
  public TrimmerUpMatch newMatch(final Object... parameters) {
    return TrimmerUpMatch.newMatch((relalg.JoinNode) parameters[0], (relalg.TrimmerNode) parameters[1], (relalg.ReteNode) parameters[2], (relalg.ReteNode) parameters[3]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link TrimmerUpQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link TrimmerUpQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static TrimmerUpQuerySpecification INSTANCE = new TrimmerUpQuerySpecification();
    
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
    private final static TrimmerUpQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "relalg.trimmerUp";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("join","trimmer","rn1","rn2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(
      			 new PParameter("join", "relalg.JoinNode", null),
      			 new PParameter("trimmer", "relalg.TrimmerNode", null),
      			 new PParameter("rn1", "relalg.ReteNode", null),
      			 new PParameter("rn2", "relalg.ReteNode", null)
      			);
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_join = body.getOrCreateVariableByName("join");
      		PVariable var_trimmer = body.getOrCreateVariableByName("trimmer");
      		PVariable var_rn1 = body.getOrCreateVariableByName("rn1");
      		PVariable var_rn2 = body.getOrCreateVariableByName("rn2");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_join, "join"),
      		   new ExportedParameter(body, var_trimmer, "trimmer"),
      		   new ExportedParameter(body, var_rn1, "rn1"),
      		   new ExportedParameter(body, var_rn2, "rn2")
      		));
      		// 	JoinNode.leftParent(join, rn1)
      		new TypeConstraint(body, new FlatTuple(var_join), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.example.org/relalg", "JoinNode")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_join, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.example.org/relalg", "JoinNode", "leftParent")));
      		new Equality(body, var__virtual_0_, var_rn1);
      		// 	JoinNode.rightParent(join, rn2)
      		new TypeConstraint(body, new FlatTuple(var_join), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.example.org/relalg", "JoinNode")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_join, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.example.org/relalg", "JoinNode", "rightParent")));
      		new Equality(body, var__virtual_1_, var_rn2);
      		// 	TrimmerNode.parent(trimmer, join)
      		new TypeConstraint(body, new FlatTuple(var_trimmer), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.example.org/relalg", "TrimmerNode")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_trimmer, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.example.org/relalg", "TrimmerNode", "parent")));
      		new Equality(body, var__virtual_2_, var_join);
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
