/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.BinaryOperator;
import relalg.Operator;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.BinaryOperatorImpl#getLeftInput <em>Left Input</em>}</li>
 *   <li>{@link relalg.impl.BinaryOperatorImpl#getRightInput <em>Right Input</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BinaryOperatorImpl extends OperatorImpl implements BinaryOperator {
	/**
	 * The cached value of the '{@link #getLeftInput() <em>Left Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftInput()
	 * @generated
	 * @ordered
	 */
	protected Operator leftInput;

	/**
	 * The cached value of the '{@link #getRightInput() <em>Right Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightInput()
	 * @generated
	 * @ordered
	 */
	protected Operator rightInput;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.BINARY_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getLeftInput() {
		return leftInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftInput(Operator newLeftInput, NotificationChain msgs) {
		Operator oldLeftInput = leftInput;
		leftInput = newLeftInput;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_OPERATOR__LEFT_INPUT, oldLeftInput, newLeftInput);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftInput(Operator newLeftInput) {
		if (newLeftInput != leftInput) {
			NotificationChain msgs = null;
			if (leftInput != null)
				msgs = ((InternalEObject)leftInput).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_OPERATOR__LEFT_INPUT, null, msgs);
			if (newLeftInput != null)
				msgs = ((InternalEObject)newLeftInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_OPERATOR__LEFT_INPUT, null, msgs);
			msgs = basicSetLeftInput(newLeftInput, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_OPERATOR__LEFT_INPUT, newLeftInput, newLeftInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getRightInput() {
		return rightInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightInput(Operator newRightInput, NotificationChain msgs) {
		Operator oldRightInput = rightInput;
		rightInput = newRightInput;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT, oldRightInput, newRightInput);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightInput(Operator newRightInput) {
		if (newRightInput != rightInput) {
			NotificationChain msgs = null;
			if (rightInput != null)
				msgs = ((InternalEObject)rightInput).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT, null, msgs);
			if (newRightInput != null)
				msgs = ((InternalEObject)newRightInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT, null, msgs);
			msgs = basicSetRightInput(newRightInput, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT, newRightInput, newRightInput));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.BINARY_OPERATOR__LEFT_INPUT:
				return basicSetLeftInput(null, msgs);
			case RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT:
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
			case RelalgPackage.BINARY_OPERATOR__LEFT_INPUT:
				return getLeftInput();
			case RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT:
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
			case RelalgPackage.BINARY_OPERATOR__LEFT_INPUT:
				setLeftInput((Operator)newValue);
				return;
			case RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT:
				setRightInput((Operator)newValue);
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
			case RelalgPackage.BINARY_OPERATOR__LEFT_INPUT:
				setLeftInput((Operator)null);
				return;
			case RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT:
				setRightInput((Operator)null);
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
			case RelalgPackage.BINARY_OPERATOR__LEFT_INPUT:
				return leftInput != null;
			case RelalgPackage.BINARY_OPERATOR__RIGHT_INPUT:
				return rightInput != null;
		}
		return super.eIsSet(featureID);
	}

} //BinaryOperatorImpl
