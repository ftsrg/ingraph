/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import relalg.AttributeVariable;
import relalg.EdgeVariable;
import relalg.RelalgPackage;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.AttributeVariableImpl#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.impl.AttributeVariableImpl#getVertexVariable <em>Vertex Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeVariableImpl extends VariableImpl implements AttributeVariable {
	/**
	 * The cached value of the '{@link #getEdgeVariable() <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeVariable()
	 * @generated
	 * @ordered
	 */
	protected EdgeVariable edgeVariable;
	/**
	 * The cached value of the '{@link #getVertexVariable() <em>Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexVariable()
	 * @generated
	 * @ordered
	 */
	protected VertexVariable vertexVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.ATTRIBUTE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeVariable getEdgeVariable() {
		if (edgeVariable != null && edgeVariable.eIsProxy()) {
			InternalEObject oldEdgeVariable = (InternalEObject)edgeVariable;
			edgeVariable = (EdgeVariable)eResolveProxy(oldEdgeVariable);
			if (edgeVariable != oldEdgeVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
			}
		}
		return edgeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeVariable basicGetEdgeVariable() {
		return edgeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEdgeVariable(EdgeVariable newEdgeVariable, NotificationChain msgs) {
		EdgeVariable oldEdgeVariable = edgeVariable;
		edgeVariable = newEdgeVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE, oldEdgeVariable, newEdgeVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeVariable(EdgeVariable newEdgeVariable) {
		if (newEdgeVariable != edgeVariable) {
			NotificationChain msgs = null;
			if (edgeVariable != null)
				msgs = ((InternalEObject)edgeVariable).eInverseRemove(this, RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLE, EdgeVariable.class, msgs);
			if (newEdgeVariable != null)
				msgs = ((InternalEObject)newEdgeVariable).eInverseAdd(this, RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLE, EdgeVariable.class, msgs);
			msgs = basicSetEdgeVariable(newEdgeVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE, newEdgeVariable, newEdgeVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable getVertexVariable() {
		if (vertexVariable != null && vertexVariable.eIsProxy()) {
			InternalEObject oldVertexVariable = (InternalEObject)vertexVariable;
			vertexVariable = (VertexVariable)eResolveProxy(oldVertexVariable);
			if (vertexVariable != oldVertexVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE, oldVertexVariable, vertexVariable));
			}
		}
		return vertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable basicGetVertexVariable() {
		return vertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVertexVariable(VertexVariable newVertexVariable, NotificationChain msgs) {
		VertexVariable oldVertexVariable = vertexVariable;
		vertexVariable = newVertexVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE, oldVertexVariable, newVertexVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVertexVariable(VertexVariable newVertexVariable) {
		if (newVertexVariable != vertexVariable) {
			NotificationChain msgs = null;
			if (vertexVariable != null)
				msgs = ((InternalEObject)vertexVariable).eInverseRemove(this, RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE, VertexVariable.class, msgs);
			if (newVertexVariable != null)
				msgs = ((InternalEObject)newVertexVariable).eInverseAdd(this, RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE, VertexVariable.class, msgs);
			msgs = basicSetVertexVariable(newVertexVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE, newVertexVariable, newVertexVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				if (edgeVariable != null)
					msgs = ((InternalEObject)edgeVariable).eInverseRemove(this, RelalgPackage.EDGE_VARIABLE__ATTRIBUTE_VARIABLE, EdgeVariable.class, msgs);
				return basicSetEdgeVariable((EdgeVariable)otherEnd, msgs);
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				if (vertexVariable != null)
					msgs = ((InternalEObject)vertexVariable).eInverseRemove(this, RelalgPackage.VERTEX_VARIABLE__ATTRIBUTE_VARIABLE, VertexVariable.class, msgs);
				return basicSetVertexVariable((VertexVariable)otherEnd, msgs);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				return basicSetEdgeVariable(null, msgs);
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				return basicSetVertexVariable(null, msgs);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				if (resolve) return getEdgeVariable();
				return basicGetEdgeVariable();
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				if (resolve) return getVertexVariable();
				return basicGetVertexVariable();
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)newValue);
				return;
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				setVertexVariable((VertexVariable)newValue);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)null);
				return;
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				setVertexVariable((VertexVariable)null);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__EDGE_VARIABLE:
				return edgeVariable != null;
			case RelalgPackage.ATTRIBUTE_VARIABLE__VERTEX_VARIABLE:
				return vertexVariable != null;
		}
		return super.eIsSet(featureID);
	}

} //AttributeVariableImpl
