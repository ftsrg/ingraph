/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.RelalgPackage;
import relalg.VertexLabelSet;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.VertexVariableImpl#getVertexLabelSet <em>Vertex Label Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexVariableImpl extends ElementVariableImpl implements VertexVariable {
	/**
	 * The cached value of the '{@link #getVertexLabelSet() <em>Vertex Label Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexLabelSet()
	 * @generated
	 * @ordered
	 */
	protected VertexLabelSet vertexLabelSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.VERTEX_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexLabelSet getVertexLabelSet() {
		return vertexLabelSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVertexLabelSet(VertexLabelSet newVertexLabelSet, NotificationChain msgs) {
		VertexLabelSet oldVertexLabelSet = vertexLabelSet;
		vertexLabelSet = newVertexLabelSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET, oldVertexLabelSet, newVertexLabelSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVertexLabelSet(VertexLabelSet newVertexLabelSet) {
		if (newVertexLabelSet != vertexLabelSet) {
			NotificationChain msgs = null;
			if (vertexLabelSet != null)
				msgs = ((InternalEObject)vertexLabelSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET, null, msgs);
			if (newVertexLabelSet != null)
				msgs = ((InternalEObject)newVertexLabelSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET, null, msgs);
			msgs = basicSetVertexLabelSet(newVertexLabelSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET, newVertexLabelSet, newVertexLabelSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET:
				return basicSetVertexLabelSet(null, msgs);
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET:
				return getVertexLabelSet();
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET:
				setVertexLabelSet((VertexLabelSet)newValue);
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET:
				setVertexLabelSet((VertexLabelSet)null);
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL_SET:
				return vertexLabelSet != null;
		}
		return super.eIsSet(featureID);
	}

} //VertexVariableImpl
