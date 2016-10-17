/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.AbstractJoinOperator;
import relalg.RelalgPackage;
import relalg.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Join Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.AbstractJoinOperatorImpl#getMutualVariables <em>Mutual Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractJoinOperatorImpl extends BetaOperatorImpl implements AbstractJoinOperator {
	/**
	 * The cached value of the '{@link #getMutualVariables() <em>Mutual Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMutualVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> mutualVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractJoinOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.ABSTRACT_JOIN_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getMutualVariables() {
		if (mutualVariables == null) {
			mutualVariables = new EObjectResolvingEList<Variable>(Variable.class, this, RelalgPackage.ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES);
		}
		return mutualVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES:
				return getMutualVariables();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES:
				getMutualVariables().clear();
				getMutualVariables().addAll((Collection<? extends Variable>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES:
				getMutualVariables().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES:
				return mutualVariables != null && !mutualVariables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AbstractJoinOperatorImpl
