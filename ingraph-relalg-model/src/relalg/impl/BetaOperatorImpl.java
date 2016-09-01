/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
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
	 * The cached value of the '{@link #getLeftInput() <em>Left Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftInput()
	 * @generated
	 * @ordered
	 */
	protected AlgebraExpression leftInput;

	/**
	 * The cached value of the '{@link #getRightInput() <em>Right Input</em>}' containment reference.
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
		return leftInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftInput(AlgebraExpression newLeftInput, NotificationChain msgs) {
		AlgebraExpression oldLeftInput = leftInput;
		leftInput = newLeftInput;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_INPUT, oldLeftInput, newLeftInput);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftInput(AlgebraExpression newLeftInput) {
		if (newLeftInput != leftInput) {
			NotificationChain msgs = null;
			if (leftInput != null)
				msgs = ((InternalEObject)leftInput).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__LEFT_INPUT, null, msgs);
			if (newLeftInput != null)
				msgs = ((InternalEObject)newLeftInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__LEFT_INPUT, null, msgs);
			msgs = basicSetLeftInput(newLeftInput, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_INPUT, newLeftInput, newLeftInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraExpression getRightInput() {
		return rightInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightInput(AlgebraExpression newRightInput, NotificationChain msgs) {
		AlgebraExpression oldRightInput = rightInput;
		rightInput = newRightInput;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, oldRightInput, newRightInput);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightInput(AlgebraExpression newRightInput) {
		if (newRightInput != rightInput) {
			NotificationChain msgs = null;
			if (rightInput != null)
				msgs = ((InternalEObject)rightInput).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, null, msgs);
			if (newRightInput != null)
				msgs = ((InternalEObject)newRightInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, null, msgs);
			msgs = basicSetRightInput(newRightInput, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_INPUT, newRightInput, newRightInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.BETA_OPERATOR__LEFT_INPUT:
				return basicSetLeftInput(null, msgs);
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				return basicSetRightInput(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
				return getLeftInput();
			case RelalgPackage.BETA_OPERATOR__RIGHT_INPUT:
				return getRightInput();
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
