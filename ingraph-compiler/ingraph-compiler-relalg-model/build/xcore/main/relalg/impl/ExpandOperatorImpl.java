/**
 */
package relalg.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import relalg.AbstractEdgeVariable;
import relalg.Direction;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.NavigationDescriptor;
import relalg.RelalgContainer;
import relalg.RelalgModelElement;
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
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getExpressionContainer <em>Expression Container</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getText <em>Text</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.impl.ExpandOperatorImpl#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpandOperatorImpl extends UnaryOperatorImpl implements ExpandOperator {
	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final Direction DIRECTION_EDEFAULT = Direction.BOTH;

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
	 * The cached value of the '{@link #getSourceVertexVariable() <em>Source Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceVertexVariable()
	 * @generated
	 * @ordered
	 */
	protected VertexVariable sourceVertexVariable;

	/**
	 * The cached value of the '{@link #getEdgeVariable() <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeVariable()
	 * @generated
	 * @ordered
	 */
	protected AbstractEdgeVariable edgeVariable;

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
	public RelalgContainer getExpressionContainer() {
		if (eContainerFeatureID() != RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER) return null;
		return (RelalgContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgContainer basicGetExpressionContainer() {
		if (eContainerFeatureID() != RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER) return null;
		return (RelalgContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpressionContainer(RelalgContainer newExpressionContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExpressionContainer, RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpressionContainer(RelalgContainer newExpressionContainer) {
		if (newExpressionContainer != eInternalContainer() || (eContainerFeatureID() != RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER && newExpressionContainer != null)) {
			if (EcoreUtil.isAncestor(this, newExpressionContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExpressionContainer != null)
				msgs = ((InternalEObject)newExpressionContainer).eInverseAdd(this, RelalgPackage.RELALG_CONTAINER__EXPRESSIONS, RelalgContainer.class, msgs);
			msgs = basicSetExpressionContainer(newExpressionContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER, newExpressionContainer, newExpressionContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__TEXT, oldText, text));
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
	public AbstractEdgeVariable getEdgeVariable() {
		if (edgeVariable != null && edgeVariable.eIsProxy()) {
			InternalEObject oldEdgeVariable = (InternalEObject)edgeVariable;
			edgeVariable = (AbstractEdgeVariable)eResolveProxy(oldEdgeVariable);
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
	public AbstractEdgeVariable basicGetEdgeVariable() {
		return edgeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEdgeVariable(AbstractEdgeVariable newEdgeVariable) {
		AbstractEdgeVariable oldEdgeVariable = edgeVariable;
		edgeVariable = newEdgeVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE, oldEdgeVariable, edgeVariable));
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
	public String fullName() {
		Class<? extends RelalgModelElement> _class = this.getClass();
		String _name = _class.getName();
		String _plus = ("fullName() method not implemented for class " + _name);
		throw new UnsupportedOperationException(_plus);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExpressionContainer((RelalgContainer)otherEnd, msgs);
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
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				return basicSetExpressionContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				return eInternalContainer().eInverseRemove(this, RelalgPackage.RELALG_CONTAINER__EXPRESSIONS, RelalgContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				if (resolve) return getExpressionContainer();
				return basicGetExpressionContainer();
			case RelalgPackage.EXPAND_OPERATOR__TEXT:
				return getText();
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				return getDirection();
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				if (resolve) return getSourceVertexVariable();
				return basicGetSourceVertexVariable();
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				if (resolve) return getEdgeVariable();
				return basicGetEdgeVariable();
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
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				setExpressionContainer((RelalgContainer)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__TEXT:
				setText((String)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				setDirection((Direction)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)newValue);
				return;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((AbstractEdgeVariable)newValue);
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
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				setExpressionContainer((RelalgContainer)null);
				return;
			case RelalgPackage.EXPAND_OPERATOR__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				setSourceVertexVariable((VertexVariable)null);
				return;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				setEdgeVariable((AbstractEdgeVariable)null);
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
			case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER:
				return basicGetExpressionContainer() != null;
			case RelalgPackage.EXPAND_OPERATOR__TEXT:
				return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
			case RelalgPackage.EXPAND_OPERATOR__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE:
				return sourceVertexVariable != null;
			case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE:
				return edgeVariable != null;
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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == RelalgModelElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Expression.class) {
			switch (derivedFeatureID) {
				case RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER: return RelalgPackage.EXPRESSION__EXPRESSION_CONTAINER;
				case RelalgPackage.EXPAND_OPERATOR__TEXT: return RelalgPackage.EXPRESSION__TEXT;
				default: return -1;
			}
		}
		if (baseClass == NavigationDescriptor.class) {
			switch (derivedFeatureID) {
				case RelalgPackage.EXPAND_OPERATOR__DIRECTION: return RelalgPackage.NAVIGATION_DESCRIPTOR__DIRECTION;
				case RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE: return RelalgPackage.NAVIGATION_DESCRIPTOR__SOURCE_VERTEX_VARIABLE;
				case RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE: return RelalgPackage.NAVIGATION_DESCRIPTOR__EDGE_VARIABLE;
				case RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE: return RelalgPackage.NAVIGATION_DESCRIPTOR__TARGET_VERTEX_VARIABLE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == RelalgModelElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Expression.class) {
			switch (baseFeatureID) {
				case RelalgPackage.EXPRESSION__EXPRESSION_CONTAINER: return RelalgPackage.EXPAND_OPERATOR__EXPRESSION_CONTAINER;
				case RelalgPackage.EXPRESSION__TEXT: return RelalgPackage.EXPAND_OPERATOR__TEXT;
				default: return -1;
			}
		}
		if (baseClass == NavigationDescriptor.class) {
			switch (baseFeatureID) {
				case RelalgPackage.NAVIGATION_DESCRIPTOR__DIRECTION: return RelalgPackage.EXPAND_OPERATOR__DIRECTION;
				case RelalgPackage.NAVIGATION_DESCRIPTOR__SOURCE_VERTEX_VARIABLE: return RelalgPackage.EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE;
				case RelalgPackage.NAVIGATION_DESCRIPTOR__EDGE_VARIABLE: return RelalgPackage.EXPAND_OPERATOR__EDGE_VARIABLE;
				case RelalgPackage.NAVIGATION_DESCRIPTOR__TARGET_VERTEX_VARIABLE: return RelalgPackage.EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == RelalgModelElement.class) {
			switch (baseOperationID) {
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.EXPAND_OPERATOR___FULL_NAME;
				default: return -1;
			}
		}
		if (baseClass == Expression.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == NavigationDescriptor.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case RelalgPackage.EXPAND_OPERATOR___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (text: ");
		result.append(text);
		result.append(", direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //ExpandOperatorImpl
