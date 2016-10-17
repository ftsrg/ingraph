/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.Direction;
import relalg.EdgeVariable;
import relalg.ExpandOperator;
import relalg.RelalgPackage;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expand Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpandOperatorImpl extends AlphaOperatorImpl implements ExpandOperator {
	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final Direction DIRECTION_EDEFAULT = Direction.IN;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected Direction direction = DIRECTION_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpandOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.EXPAND_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(Direction newDirection) {
		Direction oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__DIRECTION, oldDirection, direction));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE, oldSourceVertexVariable, sourceVertexVariable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE, oldSourceVertexVariable, sourceVertexVariable));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE, oldTargetVertexVariable, targetVertexVariable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE, oldTargetVertexVariable, targetVertexVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				return getDirection();
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				if (resolve) return getEdgeVariable();
				return basicGetEdgeVariable();
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				if (resolve) return getSourceVertexVariable();
				return basicGetSourceVertexVariable();
			case RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE:
				if (resolve) return getTargetVertexVariable();
				return basicGetTargetVertexVariable();
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
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				setDirection((Direction)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE:
				setTargetVertexVariable((VertexVariable)newValue);
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
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((EdgeVariable)null);
				return;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)null);
				return;
			case RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE:
				setTargetVertexVariable((VertexVariable)null);
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
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				return edgeVariable != null;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				return sourceVertexVariable != null;
			case RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE:
				return targetVertexVariable != null;
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
		result.append(" (direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //ExpandOperatorImpl
