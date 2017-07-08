/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import relalg.MaxHops;
import relalg.MaxHopsType;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Max Hops</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.MaxHopsImpl#getMaxHopsType <em>Max Hops Type</em>}</li>
 *   <li>{@link relalg.impl.MaxHopsImpl#getHops <em>Hops</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MaxHopsImpl extends MinimalEObjectImpl.Container implements MaxHops {
	/**
	 * The default value of the '{@link #getMaxHopsType() <em>Max Hops Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxHopsType()
	 * @generated
	 * @ordered
	 */
	protected static final MaxHopsType MAX_HOPS_TYPE_EDEFAULT = MaxHopsType.LIMITED;

	/**
	 * The cached value of the '{@link #getMaxHopsType() <em>Max Hops Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxHopsType()
	 * @generated
	 * @ordered
	 */
	protected MaxHopsType maxHopsType = MAX_HOPS_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHops() <em>Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHops()
	 * @generated
	 * @ordered
	 */
	protected static final int HOPS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHops() <em>Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHops()
	 * @generated
	 * @ordered
	 */
	protected int hops = HOPS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MaxHopsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.MAX_HOPS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaxHopsType getMaxHopsType() {
		return maxHopsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxHopsType(MaxHopsType newMaxHopsType) {
		MaxHopsType oldMaxHopsType = maxHopsType;
		maxHopsType = newMaxHopsType == null ? MAX_HOPS_TYPE_EDEFAULT : newMaxHopsType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.MAX_HOPS__MAX_HOPS_TYPE, oldMaxHopsType, maxHopsType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHops() {
		return hops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHops(int newHops) {
		int oldHops = hops;
		hops = newHops;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.MAX_HOPS__HOPS, oldHops, hops));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.MAX_HOPS__MAX_HOPS_TYPE:
				return getMaxHopsType();
			case RelalgPackage.MAX_HOPS__HOPS:
				return getHops();
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
			case RelalgPackage.MAX_HOPS__MAX_HOPS_TYPE:
				setMaxHopsType((MaxHopsType)newValue);
				return;
			case RelalgPackage.MAX_HOPS__HOPS:
				setHops((Integer)newValue);
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
			case RelalgPackage.MAX_HOPS__MAX_HOPS_TYPE:
				setMaxHopsType(MAX_HOPS_TYPE_EDEFAULT);
				return;
			case RelalgPackage.MAX_HOPS__HOPS:
				setHops(HOPS_EDEFAULT);
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
			case RelalgPackage.MAX_HOPS__MAX_HOPS_TYPE:
				return maxHopsType != MAX_HOPS_TYPE_EDEFAULT;
			case RelalgPackage.MAX_HOPS__HOPS:
				return hops != HOPS_EDEFAULT;
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
		result.append(" (maxHopsType: ");
		result.append(maxHopsType);
		result.append(", hops: ");
		result.append(hops);
		result.append(')');
		return result.toString();
	}

} //MaxHopsImpl
