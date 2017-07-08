/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.EdgeListVariable;
import relalg.MaxHops;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge List Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.EdgeListVariableImpl#getMinHops <em>Min Hops</em>}</li>
 *   <li>{@link relalg.impl.EdgeListVariableImpl#getMaxHops <em>Max Hops</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeListVariableImpl extends AbstractEdgeVariableImpl implements EdgeListVariable {
	/**
	 * The default value of the '{@link #getMinHops() <em>Min Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinHops()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_HOPS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinHops() <em>Min Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinHops()
	 * @generated
	 * @ordered
	 */
	protected int minHops = MIN_HOPS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMaxHops() <em>Max Hops</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxHops()
	 * @generated
	 * @ordered
	 */
	protected MaxHops maxHops;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeListVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.EDGE_LIST_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinHops() {
		return minHops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinHops(int newMinHops) {
		int oldMinHops = minHops;
		minHops = newMinHops;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EDGE_LIST_VARIABLE__MIN_HOPS, oldMinHops, minHops));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaxHops getMaxHops() {
		return maxHops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaxHops(MaxHops newMaxHops, NotificationChain msgs) {
		MaxHops oldMaxHops = maxHops;
		maxHops = newMaxHops;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS, oldMaxHops, newMaxHops);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxHops(MaxHops newMaxHops) {
		if (newMaxHops != maxHops) {
			NotificationChain msgs = null;
			if (maxHops != null)
				msgs = ((InternalEObject)maxHops).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS, null, msgs);
			if (newMaxHops != null)
				msgs = ((InternalEObject)newMaxHops).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS, null, msgs);
			msgs = basicSetMaxHops(newMaxHops, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS, newMaxHops, newMaxHops));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS:
				return basicSetMaxHops(null, msgs);
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
			case RelalgPackage.EDGE_LIST_VARIABLE__MIN_HOPS:
				return getMinHops();
			case RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS:
				return getMaxHops();
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
			case RelalgPackage.EDGE_LIST_VARIABLE__MIN_HOPS:
				setMinHops((Integer)newValue);
				return;
			case RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS:
				setMaxHops((MaxHops)newValue);
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
			case RelalgPackage.EDGE_LIST_VARIABLE__MIN_HOPS:
				setMinHops(MIN_HOPS_EDEFAULT);
				return;
			case RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS:
				setMaxHops((MaxHops)null);
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
			case RelalgPackage.EDGE_LIST_VARIABLE__MIN_HOPS:
				return minHops != MIN_HOPS_EDEFAULT;
			case RelalgPackage.EDGE_LIST_VARIABLE__MAX_HOPS:
				return maxHops != null;
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
		result.append(" (minHops: ");
		result.append(minHops);
		result.append(')');
		return result.toString();
	}

} //EdgeListVariableImpl
