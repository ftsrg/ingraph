/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.AlgebraExpression;
import relalg.BetaOperator;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Beta Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.BetaOperatorImpl#getLeftInput <em>Left Input</em>}</li>
 *   <li>{@link relalg.impl.BetaOperatorImpl#getRightInput <em>Right Input</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BetaOperatorImpl extends AlgebraExpressionImpl implements BetaOperator {
	/**
	 * The cached value of the '{@link #getLeftInput() <em>Left Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftInput()
	 * @generated
	 * @ordered
	 */
	protected AlgebraExpression leftInput;

	/**
	 * The cached value of the '{@link #getRightInput() <em>Right Input</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightInput()
	 * @generated
	 * @ordered
	 */
	protected AlgebraExpression rightInput;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BetaOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.BETA_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraExpression getLeftInput() {
		if (leftInput != null && leftInput.eIsProxy()) {
			InternalEObject oldLeftInput = (InternalEObject)leftInput;
			leftInput = (AlgebraExpression)eResolveProxy(oldLeftInput);
			if (leftInput != oldLeftInput) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BETA_OPERATOR__LEFT_INPUT, oldLeftInput, leftInput));
			}
		}
		return leftInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraExpression basicGetLeftInput() {
		return leftInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftInput(AlgebraExpression newLeftInput) {
		AlgebraExpression oldLeftInput = leftInput;
		leftInput = newLeftInput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_INPUT, oldLeftInput, leftInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraExpression getRightInput() {
		if (rightInput != null && rightInput.eIsProxy()) {
			InternalEObject oldRightInput = (InternalEObject)rightInput;
			rightInput = (AlgebraExpression)eResolveProxy(oldRightInput);
			if (rightInput != oldRightInput) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, oldRightInput, rightInput));
			}
		}
		return rightInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraExpression basicGetRightInput() {
		return rightInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightInput(AlgebraExpression newRightInput) {
		AlgebraExpression oldRightInput = rightInput;
		rightInput = newRightInput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, oldRightInput, rightInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.BETA_OPERATOR__LEFT_INPUT:
				if (resolve) return getLeftInput();
				return basicGetLeftInput();
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				if (resolve) return getRightInput();
				return basicGetRightInput();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelalgPackage.BETA_OPERATOR__LEFT_INPUT:
				setLeftInput((AlgebraExpression)newValue);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				setRightInput((AlgebraExpression)newValue);
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
			case RelalgPackage.BETA_OPERATOR__LEFT_INPUT:
				setLeftInput((AlgebraExpression)null);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				setRightInput((AlgebraExpression)null);
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
			case RelalgPackage.BETA_OPERATOR__LEFT_INPUT:
				return leftInput != null;
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				return rightInput != null;
		}
		return super.eIsSet(featureID);
	}

} //BetaOperatorImpl
