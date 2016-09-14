/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.RelalgPackage;
import relalg.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.VariableImpl#isDontCare <em>Dont Care</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class VariableImpl extends NamedElementImpl implements Variable {
	/**
	 * The default value of the '{@link #isDontCare() <em>Dont Care</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDontCare()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DONT_CARE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDontCare() <em>Dont Care</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDontCare()
	 * @generated
	 * @ordered
	 */
	protected boolean dontCare = DONT_CARE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDontCare() {
		return dontCare;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDontCare(boolean newDontCare) {
		boolean oldDontCare = dontCare;
		dontCare = newDontCare;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.VARIABLE__DONT_CARE, oldDontCare, dontCare));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.VARIABLE__DONT_CARE:
				return isDontCare();
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
			case RelalgPackage.VARIABLE__DONT_CARE:
				setDontCare((Boolean)newValue);
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
			case RelalgPackage.VARIABLE__DONT_CARE:
				setDontCare(DONT_CARE_EDEFAULT);
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
			case RelalgPackage.VARIABLE__DONT_CARE:
				return dontCare != DONT_CARE_EDEFAULT;
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
		result.append(" (dontCare: ");
		result.append(dontCare);
		result.append(')');
		return result.toString();
	}

} //VariableImpl
