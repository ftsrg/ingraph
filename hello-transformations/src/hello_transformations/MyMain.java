package hello_transformations;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.ViatraQueryMatcher;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.RuleEngine;
import org.eclipse.viatra.transformation.evm.specific.RuleEngines;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;

import relalg.RelalgPackage;
import relalg.util.TrimmerUpQuerySpecification;

public class MyMain {
	public static void main(String[] args) throws ViatraQueryException {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl());
		RelalgPackage.eINSTANCE.eClass();

		final URI resourceURI = URI.createFileURI("/home/szarnyasg/runtime-New_configuration/hello/My.relalg");
		final ResourceSetImpl resourceSet = new ResourceSetImpl();
		final Resource resource = resourceSet.getResource(resourceURI, true);

		System.out.println(resource);

		final ViatraQueryEngine queryEngine = ViatraQueryEngine.on(new EMFScope(resourceSet));
		final RuleEngine ruleEngine = RuleEngines.createViatraQueryRuleEngine(queryEngine);		

		BatchTransformationRuleFactory f = new BatchTransformationRuleFactory();
		IQuerySpecification instance = TrimmerUpQuerySpecification.instance();
		IMatchProcessor<IPatternMatch> action = new MyAction();
		BatchTransformationRule<IPatternMatch, ViatraQueryMatcher<IPatternMatch>> rule = f.createRule().name("hello").precondition(instance).action(action).build();
		
		BatchTransformation tr = BatchTransformation.forEngine(queryEngine).addRule(rule).build();
		BatchTransformationStatements statements = tr.getTransformationStatements();
		
		Context ctx = Context.create();
		ruleEngine.addRule(rule.getRuleSpecification());
		ruleEngine.getNextActivation().fire(ctx);
		
		
	}
}
