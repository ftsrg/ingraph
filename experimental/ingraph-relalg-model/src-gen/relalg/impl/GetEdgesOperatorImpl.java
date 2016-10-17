/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.EdgeVariable;
import relalg.GetEdgesOperator;
import relalg.RelalgPackage;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Edges Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.GetEdgesOperatorImpl#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.impl.GetEdgesOperatorImpl#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 *   <li>{@link relalg.impl.GetEdgesOperatorImpl#getEdgeVariable <em>Edge Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GetEdgesOperatorImpl extends OperatorImpl implements GetEdgesOperator {
	/**
	 * The cached value of the '{@link #getSourceVertexVariable() <em>Source Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceVertexVariable()
	 * @generated
	 * @ordered
	 */
	protected VertexVariable sourceVertexVariable;

	/**
	 * The cached value of the '{@link #getTargetVertexVariable() <em>Target Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetVertexVariable()
	 * @generated
	 * @ordered
	 */
	protected VertexVariable targetVertexVariable;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GetEdgesOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.GET_EDGES_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable getSourceVertexVariable() {
		if (sourceVertexVariable != null && sourceVertexVariable.eIsProxy()) {
			InternalEObject oldSourceVertexVariable = (InternalEObject)sourceVertexVariable;
			sourceVertexVariable = (VertexVariable)eResolveProxy(oldSourceVertexVariable);
			if (sourceVertexVariable != oldSourceVertexVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE, oldSourceVertexVariable, sourceVertexVariable));
			}
		}
		return sourceVertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable basicGetSourceVertexVariable() {
		return sourceVertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceVertexVariable(VertexVariable newSourceVertexVariable) {
		VertexVariable oldSourceVertexVariable = sourceVertexVariable;
		sourceVertexVariable = newSourceVertexVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE, oldSourceVertexVariable, sourceVertexVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable getTargetVertexVariable() {
		if (targetVertexVariable != null && targetVertexVariable.eIsProxy()) {
			InternalEObject oldTargetVertexVariable = (InternalEObject)targetVertexVariable;
			targetVertexVariable = (VertexVariable)eResolveProxy(oldTargetVertexVariable);
			if (targetVertexVariable != oldTargetVertexVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE, oldTargetVertexVariable, targetVertexVariable));
			}
		}
		return targetVertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable basicGetTargetVertexVariable() {
		return targetVertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetVertexVariable(VertexVariable newTargetVertexVariable) {
		VertexVariable oldTargetVertexVariable = targetVertexVariable;
		targetVertexVariable = newTargetVertexVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE, oldTargetVertexVariable, targetVertexVariable));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
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
	public void setEdgeVariable(EdgeVariable newEdgeVariable) {
		EdgeVariable oldEdgeVariable = edgeVariable;
		edgeVariable = newEdgeVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE:
				if (resolve) return getSourceVertexVariable();
				return basicGetSourceVertexVariable();
			case RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE:
				if (resolve) return getTargetVertexVariable();
				return basicGetTargetVertexVariable();
			case RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE:
				if (resolve) return getEdgeVariable();
				return basicGetEdgeVariable();
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
			case RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)newValue);
				return;
			case RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE:
				setTargetVertexVariable((VertexVariable)newValue);
				return;
			case RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)newValue);
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
			case RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)null);
				return;
			case RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE:
				setTargetVertexVariable((VertexVariable)null);
				return;
			case RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)null);
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
			case RelalgPackage.GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE:
				return sourceVertexVariable != null;
			case RelalgPackage.GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE:
				return targetVertexVariable != null;
			case RelalgPackage.GET_EDGES_OPERATOR__EDGE_VARIABLE:
				return edgeVariable != null;
		}
		return super.eIsSet(featureID);
	}

} //GetEdgesOperatorImpl
