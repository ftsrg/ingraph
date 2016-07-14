/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import relalg.AlgebraNode;
import relalg.AttributeSet;
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
 *   <li>{@link relalg.impl.BetaOperatorImpl#getLeftParent <em>Left Parent</em>}</li>
 *   <li>{@link relalg.impl.BetaOperatorImpl#getRightParent <em>Right Parent</em>}</li>
 *   <li>{@link relalg.impl.BetaOperatorImpl#getLeftMask <em>Left Mask</em>}</li>
 *   <li>{@link relalg.impl.BetaOperatorImpl#getRightMask <em>Right Mask</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BetaOperatorImpl extends MinimalEObjectImpl.Container implements BetaOperator {
	/**
	 * The cached value of the '{@link #getLeftParent() <em>Left Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftParent()
	 * @generated
	 * @ordered
	 */
	protected AlgebraNode leftParent;

	/**
	 * The cached value of the '{@link #getRightParent() <em>Right Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightParent()
	 * @generated
	 * @ordered
	 */
	protected AlgebraNode rightParent;

	/**
	 * The cached value of the '{@link #getLeftMask() <em>Left Mask</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftMask()
	 * @generated
	 * @ordered
	 */
	protected AttributeSet leftMask;

	/**
	 * The cached value of the '{@link #getRightMask() <em>Right Mask</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightMask()
	 * @generated
	 * @ordered
	 */
	protected AttributeSet rightMask;

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
	public AlgebraNode getLeftParent() {
		if (leftParent != null && leftParent.eIsProxy()) {
			InternalEObject oldLeftParent = (InternalEObject)leftParent;
			leftParent = (AlgebraNode)eResolveProxy(oldLeftParent);
			if (leftParent != oldLeftParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BETA_OPERATOR__LEFT_PARENT, oldLeftParent, leftParent));
			}
		}
		return leftParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraNode basicGetLeftParent() {
		return leftParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftParent(AlgebraNode newLeftParent) {
		AlgebraNode oldLeftParent = leftParent;
		leftParent = newLeftParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_PARENT, oldLeftParent, leftParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraNode getRightParent() {
		if (rightParent != null && rightParent.eIsProxy()) {
			InternalEObject oldRightParent = (InternalEObject)rightParent;
			rightParent = (AlgebraNode)eResolveProxy(oldRightParent);
			if (rightParent != oldRightParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BETA_OPERATOR__RIGHT_PARENT, oldRightParent, rightParent));
			}
		}
		return rightParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebraNode basicGetRightParent() {
		return rightParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightParent(AlgebraNode newRightParent) {
		AlgebraNode oldRightParent = rightParent;
		rightParent = newRightParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_PARENT, oldRightParent, rightParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeSet getLeftMask() {
		return leftMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftMask(AttributeSet newLeftMask, NotificationChain msgs) {
		AttributeSet oldLeftMask = leftMask;
		leftMask = newLeftMask;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_MASK, oldLeftMask, newLeftMask);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftMask(AttributeSet newLeftMask) {
		if (newLeftMask != leftMask) {
			NotificationChain msgs = null;
			if (leftMask != null)
				msgs = ((InternalEObject)leftMask).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__LEFT_MASK, null, msgs);
			if (newLeftMask != null)
				msgs = ((InternalEObject)newLeftMask).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__LEFT_MASK, null, msgs);
			msgs = basicSetLeftMask(newLeftMask, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__LEFT_MASK, newLeftMask, newLeftMask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeSet getRightMask() {
		return rightMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightMask(AttributeSet newRightMask, NotificationChain msgs) {
		AttributeSet oldRightMask = rightMask;
		rightMask = newRightMask;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_MASK, oldRightMask, newRightMask);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightMask(AttributeSet newRightMask) {
		if (newRightMask != rightMask) {
			NotificationChain msgs = null;
			if (rightMask != null)
				msgs = ((InternalEObject)rightMask).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__RIGHT_MASK, null, msgs);
			if (newRightMask != null)
				msgs = ((InternalEObject)newRightMask).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BETA_OPERATOR__RIGHT_MASK, null, msgs);
			msgs = basicSetRightMask(newRightMask, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BETA_OPERATOR__RIGHT_MASK, newRightMask, newRightMask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.BETA_OPERATOR__LEFT_MASK:
				return basicSetLeftMask(null, msgs);
			case RelalgPackage.BETA_OPERATOR__RIGHT_MASK:
				return basicSetRightMask(null, msgs);
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
			case RelalgPackage.BETA_OPERATOR__LEFT_PARENT:
				if (resolve) return getLeftParent();
				return basicGetLeftParent();
			case RelalgPackage.BETA_OPERATOR__RIGHT_PARENT:
				if (resolve) return getRightParent();
				return basicGetRightParent();
			case RelalgPackage.BETA_OPERATOR__LEFT_MASK:
				return getLeftMask();
			case RelalgPackage.BETA_OPERATOR__RIGHT_MASK:
				return getRightMask();
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
			case RelalgPackage.BETA_OPERATOR__LEFT_PARENT:
				setLeftParent((AlgebraNode)newValue);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_PARENT:
				setRightParent((AlgebraNode)newValue);
				return;
			case RelalgPackage.BETA_OPERATOR__LEFT_MASK:
				setLeftMask((AttributeSet)newValue);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_MASK:
				setRightMask((AttributeSet)newValue);
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
			case RelalgPackage.BETA_OPERATOR__LEFT_PARENT:
				setLeftParent((AlgebraNode)null);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_PARENT:
				setRightParent((AlgebraNode)null);
				return;
			case RelalgPackage.BETA_OPERATOR__LEFT_MASK:
				setLeftMask((AttributeSet)null);
				return;
			case RelalgPackage.BETA_OPERATOR__RIGHT_MASK:
				setRightMask((AttributeSet)null);
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
			case RelalgPackage.BETA_OPERATOR__LEFT_PARENT:
				return leftParent != null;
			case RelalgPackage.BETA_OPERATOR__RIGHT_PARENT:
				return rightParent != null;
			case RelalgPackage.BETA_OPERATOR__LEFT_MASK:
				return leftMask != null;
			case RelalgPackage.BETA_OPERATOR__RIGHT_MASK:
				return rightMask != null;
		}
		return super.eIsSet(featureID);
	}

} //BetaOperatorImpl
