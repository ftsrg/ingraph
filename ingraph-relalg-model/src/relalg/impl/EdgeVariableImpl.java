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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import relalg.AttributeVariable;
import relalg.EdgeLabel;
import relalg.EdgeVariable;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.EdgeVariableImpl#getEdgeLabel <em>Edge Label</em>}</li>
 *   <li>{@link relalg.impl.EdgeVariableImpl#getAttributeVariables <em>Attribute Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeVariableImpl extends VariableImpl implements EdgeVariable {
	/**
	 * The cached value of the '{@link #getEdgeLabel() <em>Edge Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeLabel()
	 * @generated
	 * @ordered
	 */
	protected EdgeLabel edgeLabel;

	/**
	 * The cached value of the '{@link #getAttributeVariables() <em>Attribute Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeVariable> attributeVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.EDGE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabel getEdgeLabel() {
		if (edgeLabel != null && edgeLabel.eIsProxy()) {
			InternalEObject oldEdgeLabel = (InternalEObject)edgeLabel;
			edgeLabel = (EdgeLabel)eResolveProxy(oldEdgeLabel);
			if (edgeLabel != oldEdgeLabel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.EDGE_VARIABLE__EDGE_LABEL, oldEdgeLabel, edgeLabel));
			}
		}
		return edgeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabel basicGetEdgeLabel() {
		return edgeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeLabel(EdgeLabel newEdgeLabel) {
		EdgeLabel oldEdgeLabel = edgeLabel;
		edgeLabel = newEdgeLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EDGE_VARIABLE__EDGE_LABEL, oldEdgeLabel, edgeLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeVariable> getAttributeVariables() {
		if (attributeVariables == null) {
			attributeVariables = new EObjectContainmentEList<AttributeVariable>(AttributeVariable.class, this, RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES);
		}
		return attributeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES:
				return ((InternalEList<?>)getAttributeVariables()).basicRemove(otherEnd, msgs);
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
			case RelalgPackage.EDGE_VARIABLE__EDGE_LABEL:
				if (resolve) return getEdgeLabel();
				return basicGetEdgeLabel();
			case RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES:
				return getAttributeVariables();
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
			case RelalgPackage.EDGE_VARIABLE__EDGE_LABEL:
				setEdgeLabel((EdgeLabel)newValue);
				return;
			case RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES:
				getAttributeVariables().clear();
				getAttributeVariables().addAll((Collection<? extends AttributeVariable>)newValue);
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
			case RelalgPackage.EDGE_VARIABLE__EDGE_LABEL:
				setEdgeLabel((EdgeLabel)null);
				return;
			case RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES:
				getAttributeVariables().clear();
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
			case RelalgPackage.EDGE_VARIABLE__EDGE_LABEL:
				return edgeLabel != null;
			case RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLES:
				return attributeVariables != null && !attributeVariables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EdgeVariableImpl
