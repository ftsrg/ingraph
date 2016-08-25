/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.GetVerticesOperator;
import relalg.RelalgPackage;
import relalg.VertexLabel;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Vertices Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.GetVerticesOperatorImpl#getVertexVariable <em>Vertex Variable</em>}</li>
 *   <li>{@link relalg.impl.GetVerticesOperatorImpl#getVertexLabel <em>Vertex Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GetVerticesOperatorImpl extends AlgebraExpressionImpl implements GetVerticesOperator {
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
	 * The cached value of the '{@link #getVertexLabel() <em>Vertex Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexLabel()
	 * @generated
	 * @ordered
	 */
	protected VertexLabel vertexLabel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GetVerticesOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.GET_VERTICES_OPERATOR;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE, oldVertexVariable, vertexVariable));
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
	public void setVertexVariable(VertexVariable newVertexVariable) {
		VertexVariable oldVertexVariable = vertexVariable;
		vertexVariable = newVertexVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE, oldVertexVariable, vertexVariable));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL, oldVertexLabel, vertexLabel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL, oldVertexLabel, vertexLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE:
				if (resolve) return getVertexVariable();
				return basicGetVertexVariable();
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL:
				if (resolve) return getVertexLabel();
				return basicGetVertexLabel();
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
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE:
				setVertexVariable((VertexVariable)newValue);
				return;
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL:
				setVertexLabel((VertexLabel)newValue);
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
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE:
				setVertexVariable((VertexVariable)null);
				return;
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL:
				setVertexLabel((VertexLabel)null);
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
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_VARIABLE:
				return vertexVariable != null;
			case RelalgPackage.GET_VERTICES_OPERATOR__VERTEX_LABEL:
				return vertexLabel != null;
		}
		return super.eIsSet(featureID);
	}

} //GetVerticesOperatorImpl
