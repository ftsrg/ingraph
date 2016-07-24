package relalg_visualization

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import relalg.RelalgPackage
import relalg.RelalgFactory
import relalg.AlgebraExpression

class Hello {
	
	def static void main(String[] args) {
		RelalgPackage.eINSTANCE.eClass
		val factory = RelalgFactory.eINSTANCE

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("relalg", new XMIResourceFactoryImpl());
		
		val uri = URI.createFileURI("file:///tmp/temp.relalg")
		val resourceSet = new ResourceSetImpl()
		val resource = resourceSet.createResource(uri)
		
		// build expression
		val expression = factory.createAlgebraExpression
		resource.contents.add(expression);

		println(resource.contents)
		println expression.convertToLateX
	}
	
	def static convertToLateX(AlgebraExpression expression) {
		"expr"
	}
	
}