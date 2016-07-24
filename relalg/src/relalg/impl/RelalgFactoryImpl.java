/**
 */
package relalg.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import relalg.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelalgFactoryImpl extends EFactoryImpl implements RelalgFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RelalgFactory init() {
		try {
			RelalgFactory theRelalgFactory = (RelalgFactory)EPackage.Registry.INSTANCE.getEFactory(RelalgPackage.eNS_URI);
			if (theRelalgFactory != null) {
				return theRelalgFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RelalgFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RelalgPackage.INPUT_RELATION: return createInputRelation();
			case RelalgPackage.TRIMMER_OPERATION: return createTrimmerOperation();
			case RelalgPackage.JOIN_OPERATION: return createJoinOperation();
			case RelalgPackage.ANTI_JOIN_OPERATION: return createAntiJoinOperation();
			case RelalgPackage.PRODUCTION_OPERATION: return createProductionOperation();
			case RelalgPackage.ATTRIBUTE: return createAttribute();
			case RelalgPackage.ATTRIBUTE_SET: return createAttributeSet();
			case RelalgPackage.JOIN_BINDING: return createJoinBinding();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputRelation createInputRelation() {
		InputRelationImpl inputRelation = new InputRelationImpl();
		return inputRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrimmerOperation createTrimmerOperation() {
		TrimmerOperationImpl trimmerOperation = new TrimmerOperationImpl();
		return trimmerOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinOperation createJoinOperation() {
		JoinOperationImpl joinOperation = new JoinOperationImpl();
		return joinOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AntiJoinOperation createAntiJoinOperation() {
		AntiJoinOperationImpl antiJoinOperation = new AntiJoinOperationImpl();
		return antiJoinOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductionOperation createProductionOperation() {
		ProductionOperationImpl productionOperation = new ProductionOperationImpl();
		return productionOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeSet createAttributeSet() {
		AttributeSetImpl attributeSet = new AttributeSetImpl();
		return attributeSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinBinding createJoinBinding() {
		JoinBindingImpl joinBinding = new JoinBindingImpl();
		return joinBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgPackage getRelalgPackage() {
		return (RelalgPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RelalgPackage getPackage() {
		return RelalgPackage.eINSTANCE;
	}

} //RelalgFactoryImpl
