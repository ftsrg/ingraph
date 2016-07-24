/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import relalg.Attribute;
import relalg.JoinBinding;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Join Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.JoinBindingImpl#getLeftAttribute <em>Left Attribute</em>}</li>
 *   <li>{@link relalg.impl.JoinBindingImpl#getRightAttribute <em>Right Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JoinBindingImpl extends MinimalEObjectImpl.Container implements JoinBinding {
	/**
	 * The cached value of the '{@link #getLeftAttribute() <em>Left Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftAttribute()
	 * @generated
	 * @ordered
	 */
	protected Attribute leftAttribute;

	/**
	 * The cached value of the '{@link #getRightAttribute() <em>Right Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightAttribute()
	 * @generated
	 * @ordered
	 */
	protected Attribute rightAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.JOIN_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getLeftAttribute() {
		if (leftAttribute != null && leftAttribute.eIsProxy()) {
			InternalEObject oldLeftAttribute = (InternalEObject)leftAttribute;
			leftAttribute = (Attribute)eResolveProxy(oldLeftAttribute);
			if (leftAttribute != oldLeftAttribute) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE, oldLeftAttribute, leftAttribute));
			}
		}
		return leftAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute basicGetLeftAttribute() {
		return leftAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftAttribute(Attribute newLeftAttribute) {
		Attribute oldLeftAttribute = leftAttribute;
		leftAttribute = newLeftAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE, oldLeftAttribute, leftAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute getRightAttribute() {
		if (rightAttribute != null && rightAttribute.eIsProxy()) {
			InternalEObject oldRightAttribute = (InternalEObject)rightAttribute;
			rightAttribute = (Attribute)eResolveProxy(oldRightAttribute);
			if (rightAttribute != oldRightAttribute) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE, oldRightAttribute, rightAttribute));
			}
		}
		return rightAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute basicGetRightAttribute() {
		return rightAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightAttribute(Attribute newRightAttribute) {
		Attribute oldRightAttribute = rightAttribute;
		rightAttribute = newRightAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE, oldRightAttribute, rightAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE:
				if (resolve) return getLeftAttribute();
				return basicGetLeftAttribute();
			case RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE:
				if (resolve) return getRightAttribute();
				return basicGetRightAttribute();
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
			case RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE:
				setLeftAttribute((Attribute)newValue);
				return;
			case RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE:
				setRightAttribute((Attribute)newValue);
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
			case RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE:
				setLeftAttribute((Attribute)null);
				return;
			case RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE:
				setRightAttribute((Attribute)null);
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
			case RelalgPackage.JOIN_BINDING__LEFT_ATTRIBUTE:
				return leftAttribute != null;
			case RelalgPackage.JOIN_BINDING__RIGHT_ATTRIBUTE:
				return rightAttribute != null;
		}
		return super.eIsSet(featureID);
	}

} //JoinBindingImpl
