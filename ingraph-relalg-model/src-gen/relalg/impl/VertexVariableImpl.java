/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import relalg.AttributeVariable;
import relalg.RelalgPackage;
import relalg.VertexLabel;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.VertexVariableImpl#getVertexLabel <em>Vertex Label</em>}</li>
 *   <li>{@link relalg.impl.VertexVariableImpl#getAttributeVariable <em>Attribute Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexVariableImpl extends VariableImpl implements VertexVariable {
	/**
	 * The cached value of the '{@link #getVertexLabel() <em>Vertex Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexLabel()
	 * @generated
	 * @ordered
	 */
	protected VertexLabel vertexLabel;

	/**
	 * The cached value of the '{@link #getAttributeVariable() <em>Attribute Variable</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeVariable()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeVariable> attributeVariable;

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
	public VertexLabel getVertexLabel() {
		if (vertexLabel != null && vertexLabel.eIsProxy()) {
			InternalEObject oldVertexLabel = (InternalEObject)vertexLabel;
			vertexLabel = (VertexLabel)eResolveProxy(oldVertexLabel);
			if (vertexLabel != oldVertexLabel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL, oldVertexLabel, vertexLabel));
			}
		}
		return vertexLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexLabel basicGetVertexLabel() {
		return vertexLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVertexLabel(VertexLabel newVertexLabel) {
		VertexLabel oldVertexLabel = vertexLabel;
		vertexLabel = newVertexLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL, oldVertexLabel, vertexLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeVariable> getAttributeVariable() {
		if (attributeVariable == null) {
			attributeVariable = new EObjectWithInverseResolvingEList<AttributeVariable>(AttributeVariable.class, this, RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE, RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE);
		}
		return attributeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAttributeVariable()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				return ((InternalEList<?>)getAttributeVariable()).basicRemove(otherEnd, msgs);
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL:
				if (resolve) return getVertexLabel();
				return basicGetVertexLabel();
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				return getAttributeVariable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL:
				setVertexLabel((VertexLabel)newValue);
				return;
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				getAttributeVariable().clear();
				getAttributeVariable().addAll((Collection<? extends AttributeVariable>)newValue);
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL:
				setVertexLabel((VertexLabel)null);
				return;
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				getAttributeVariable().clear();
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
			case RelalgPackage.VERTEX_VARIABLE__VERTEX_LABEL:
				return vertexLabel != null;
			case RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE:
				return attributeVariable != null && !attributeVariable.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VertexVariableImpl
