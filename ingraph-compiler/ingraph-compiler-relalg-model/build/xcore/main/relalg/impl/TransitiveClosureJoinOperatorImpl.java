/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.EdgeListVariable;
import relalg.RelalgPackage;
import relalg.TransitiveClosureJoinOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transitive Closure Join Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.TransitiveClosureJoinOperatorImpl#getEdgeListVariable <em>Edge List Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitiveClosureJoinOperatorImpl extends EquiJoinLikeOperatorImpl implements TransitiveClosureJoinOperator {
	/**
	 * The cached value of the '{@link #getEdgeListVariable() <em>Edge List Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeListVariable()
	 * @generated
	 * @ordered
	 */
	protected EdgeListVariable edgeListVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitiveClosureJoinOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.TRANSITIVE_CLOSURE_JOIN_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeListVariable getEdgeListVariable() {
		if (edgeListVariable != null && edgeListVariable.eIsProxy()) {
			InternalEObject oldEdgeListVariable = (InternalEObject)edgeListVariable;
			edgeListVariable = (EdgeListVariable)eResolveProxy(oldEdgeListVariable);
			if (edgeListVariable != oldEdgeListVariable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE, oldEdgeListVariable, edgeListVariable));
			}
		}
		return edgeListVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeListVariable basicGetEdgeListVariable() {
		return edgeListVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeListVariable(EdgeListVariable newEdgeListVariable) {
		EdgeListVariable oldEdgeListVariable = edgeListVariable;
		edgeListVariable = newEdgeListVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE, oldEdgeListVariable, edgeListVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE:
				if (resolve) return getEdgeListVariable();
				return basicGetEdgeListVariable();
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
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE:
				setEdgeListVariable((EdgeListVariable)newValue);
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
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE:
				setEdgeListVariable((EdgeListVariable)null);
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
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE:
				return edgeListVariable != null;
		}
		return super.eIsSet(featureID);
	}

} //TransitiveClosureJoinOperatorImpl
