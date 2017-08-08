/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.AbstractEdgeVariable;
import relalg.EdgeLabelSet;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Edge Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.AbstractEdgeVariableImpl#getEdgeLabelSet <em>Edge Label Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractEdgeVariableImpl extends ElementVariableImpl implements AbstractEdgeVariable {
	/**
	 * The cached value of the '{@link #getEdgeLabelSet() <em>Edge Label Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeLabelSet()
	 * @generated
	 * @ordered
	 */
	protected EdgeLabelSet edgeLabelSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractEdgeVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.ABSTRACT_EDGE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabelSet getEdgeLabelSet() {
		return edgeLabelSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEdgeLabelSet(EdgeLabelSet newEdgeLabelSet, NotificationChain msgs) {
		EdgeLabelSet oldEdgeLabelSet = edgeLabelSet;
		edgeLabelSet = newEdgeLabelSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET, oldEdgeLabelSet, newEdgeLabelSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeLabelSet(EdgeLabelSet newEdgeLabelSet) {
		if (newEdgeLabelSet != edgeLabelSet) {
			NotificationChain msgs = null;
			if (edgeLabelSet != null)
				msgs = ((InternalEObject)edgeLabelSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET, null, msgs);
			if (newEdgeLabelSet != null)
				msgs = ((InternalEObject)newEdgeLabelSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET, null, msgs);
			msgs = basicSetEdgeLabelSet(newEdgeLabelSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET, newEdgeLabelSet, newEdgeLabelSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET:
				return basicSetEdgeLabelSet(null, msgs);
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
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET:
				return getEdgeLabelSet();
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
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET:
				setEdgeLabelSet((EdgeLabelSet)newValue);
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
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET:
				setEdgeLabelSet((EdgeLabelSet)null);
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
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET:
				return edgeLabelSet != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractEdgeVariableImpl
