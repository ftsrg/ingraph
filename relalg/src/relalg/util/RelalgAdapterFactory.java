/**
 */
package relalg.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import relalg.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage
 * @generated
 */
public class RelalgAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RelalgPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RelalgPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelalgSwitch<Adapter> modelSwitch =
		new RelalgSwitch<Adapter>() {
			@Override
			public Adapter caseAlgebraNode(AlgebraNode object) {
				return createAlgebraNodeAdapter();
			}
			@Override
			public Adapter caseInputRelation(InputRelation object) {
				return createInputRelationAdapter();
			}
			@Override
			public Adapter caseTrimmerOperator(TrimmerOperator object) {
				return createTrimmerOperatorAdapter();
			}
			@Override
			public Adapter caseJoinOperator(JoinOperator object) {
				return createJoinOperatorAdapter();
			}
			@Override
			public Adapter caseAlgebraExpression(AlgebraExpression object) {
				return createAlgebraExpressionAdapter();
			}
			@Override
			public Adapter caseAlphaOperator(AlphaOperator object) {
				return createAlphaOperatorAdapter();
			}
			@Override
			public Adapter caseBetaOperator(BetaOperator object) {
				return createBetaOperatorAdapter();
			}
			@Override
			public Adapter caseAntiJoinOperator(AntiJoinOperator object) {
				return createAntiJoinOperatorAdapter();
			}
			@Override
			public Adapter caseProductionOperator(ProductionOperator object) {
				return createProductionOperatorAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseAttributeSet(AttributeSet object) {
				return createAttributeSetAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link relalg.AlgebraNode <em>Algebra Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AlgebraNode
	 * @generated
	 */
	public Adapter createAlgebraNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.InputRelation <em>Input Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.InputRelation
	 * @generated
	 */
	public Adapter createInputRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.TrimmerOperator <em>Trimmer Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.TrimmerOperator
	 * @generated
	 */
	public Adapter createTrimmerOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.JoinOperator <em>Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.JoinOperator
	 * @generated
	 */
	public Adapter createJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AlgebraExpression <em>Algebra Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AlgebraExpression
	 * @generated
	 */
	public Adapter createAlgebraExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AlphaOperator <em>Alpha Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AlphaOperator
	 * @generated
	 */
	public Adapter createAlphaOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BetaOperator <em>Beta Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BetaOperator
	 * @generated
	 */
	public Adapter createBetaOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AntiJoinOperator <em>Anti Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AntiJoinOperator
	 * @generated
	 */
	public Adapter createAntiJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ProductionOperator <em>Production Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ProductionOperator
	 * @generated
	 */
	public Adapter createProductionOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AttributeSet <em>Attribute Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AttributeSet
	 * @generated
	 */
	public Adapter createAttributeSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RelalgAdapterFactory
