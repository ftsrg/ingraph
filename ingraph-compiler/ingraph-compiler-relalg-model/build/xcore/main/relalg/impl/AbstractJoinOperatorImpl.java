/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;
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
 *   <li>{@link relalg.impl.AbstractJoinOperatorImpl#getCommonVariables <em>Common Variables</em>}</li>
 *   <li>{@link relalg.impl.AbstractJoinOperatorImpl#getLeftMask <em>Left Mask</em>}</li>
 *   <li>{@link relalg.impl.AbstractJoinOperatorImpl#getRightMask <em>Right Mask</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractJoinOperatorImpl extends BinaryOperatorImpl implements AbstractJoinOperator {
	/**
	 * The cached value of the '{@link #getCommonVariables() <em>Common Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommonVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> commonVariables;

	/**
	 * The cached value of the '{@link #getLeftMask() <em>Left Mask</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftMask()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> leftMask;

	/**
	 * The cached value of the '{@link #getRightMask() <em>Right Mask</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightMask()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> rightMask;

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
	public EList<Variable> getCommonVariables() {
		if (commonVariables == null) {
			commonVariables = new EObjectResolvingEList<Variable>(Variable.class, this, RelalgPackage.ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES);
		}
		return commonVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getLeftMask() {
		if (leftMask == null) {
			leftMask = new EDataTypeEList<Integer>(Integer.class, this, RelalgPackage.ABSTRACT_JOIN_OPERATOR__LEFT_MASK);
		}
		return leftMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getRightMask() {
		if (rightMask == null) {
			rightMask = new EDataTypeEList<Integer>(Integer.class, this, RelalgPackage.ABSTRACT_JOIN_OPERATOR__RIGHT_MASK);
		}
		return rightMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES:
				return getCommonVariables();
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__LEFT_MASK:
				return getLeftMask();
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__RIGHT_MASK:
				return getRightMask();
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
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES:
				getCommonVariables().clear();
				getCommonVariables().addAll((Collection<? extends Variable>)newValue);
				return;
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__LEFT_MASK:
				getLeftMask().clear();
				getLeftMask().addAll((Collection<? extends Integer>)newValue);
				return;
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__RIGHT_MASK:
				getRightMask().clear();
				getRightMask().addAll((Collection<? extends Integer>)newValue);
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
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES:
				getCommonVariables().clear();
				return;
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__LEFT_MASK:
				getLeftMask().clear();
				return;
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__RIGHT_MASK:
				getRightMask().clear();
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
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES:
				return commonVariables != null && !commonVariables.isEmpty();
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__LEFT_MASK:
				return leftMask != null && !leftMask.isEmpty();
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR__RIGHT_MASK:
				return rightMask != null && !rightMask.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (leftMask: ");
		result.append(leftMask);
		result.append(", rightMask: ");
		result.append(rightMask);
		result.append(')');
		return result.toString();
	}

} //AbstractJoinOperatorImpl
