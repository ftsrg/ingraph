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

import relalg.AttributeVariable;
import relalg.ElementVariable;
import relalg.ExpressionVariable;
import relalg.NamedElement;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;
import relalg.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.AttributeVariableImpl#getElement <em>Element</em>}</li>
 *   <li>{@link relalg.impl.AttributeVariableImpl#getExpVar <em>Exp Var</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeVariableImpl extends GraphObjectVariableImpl implements AttributeVariable {
	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected ElementVariable element;

	/**
	 * The cached value of the '{@link #getExpVar() <em>Exp Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpVar()
	 * @generated
	 * @ordered
	 */
	protected ExpressionVariable expVar;

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
	public ElementVariable getElement() {
		if (element != null && element.eIsProxy()) {
			InternalEObject oldElement = (InternalEObject)element;
			element = (ElementVariable)eResolveProxy(oldElement);
			if (element != oldElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT, oldElement, element));
			}
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementVariable basicGetElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElement(ElementVariable newElement, NotificationChain msgs) {
		ElementVariable oldElement = element;
		element = newElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT, oldElement, newElement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(ElementVariable newElement) {
		if (newElement != element) {
			NotificationChain msgs = null;
			if (element != null)
				msgs = ((InternalEObject)element).eInverseRemove(this, RelalgPackage.ELEMENT_VARIABLE__ATTRIBUTES, ElementVariable.class, msgs);
			if (newElement != null)
				msgs = ((InternalEObject)newElement).eInverseAdd(this, RelalgPackage.ELEMENT_VARIABLE__ATTRIBUTES, ElementVariable.class, msgs);
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT, newElement, newElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionVariable getExpVar() {
		if (expVar != null && expVar.eIsProxy()) {
			InternalEObject oldExpVar = (InternalEObject)expVar;
			expVar = (ExpressionVariable)eResolveProxy(oldExpVar);
			if (expVar != oldExpVar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR, oldExpVar, expVar));
			}
		}
		return expVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionVariable basicGetExpVar() {
		return expVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpVar(ExpressionVariable newExpVar) {
		ExpressionVariable oldExpVar = expVar;
		expVar = newExpVar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR, oldExpVar, expVar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable getBaseVariable() {
		if (((this.getElement() != null) && (this.getExpVar() == null))) {
			return this.getElement();
		}
		else {
			if (((this.getElement() == null) && (this.getExpVar() != null))) {
				return this.getExpVar();
			}
			else {
				if (((this.getElement() == null) && (this.getExpVar() == null))) {
					throw new IllegalStateException("AttributeVariable must have non-null value for either element or expVar, but both are null.");
				}
				else {
					throw new IllegalStateException("AttributeVariable must have non-null value for either element or expVar, but not for both.");
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		Variable _baseVariable = this.getBaseVariable();
		String _name = _baseVariable.getName();
		String _plus = (_name + ".");
		String _name_1 = this.getName();
		return (_plus + _name_1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				if (element != null)
					msgs = ((InternalEObject)element).eInverseRemove(this, RelalgPackage.ELEMENT_VARIABLE__ATTRIBUTES, ElementVariable.class, msgs);
				return basicSetElement((ElementVariable)otherEnd, msgs);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				return basicSetElement(null, msgs);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
			case RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR:
				if (resolve) return getExpVar();
				return basicGetExpVar();
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				setElement((ElementVariable)newValue);
				return;
			case RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR:
				setExpVar((ExpressionVariable)newValue);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				setElement((ElementVariable)null);
				return;
			case RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR:
				setExpVar((ExpressionVariable)null);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE__ELEMENT:
				return element != null;
			case RelalgPackage.ATTRIBUTE_VARIABLE__EXP_VAR:
				return expVar != null;
		}
		return super.eIsSet(featureID);
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
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.ATTRIBUTE_VARIABLE___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseOperationID) {
				case RelalgPackage.NAMED_ELEMENT___FULL_NAME: return RelalgPackage.ATTRIBUTE_VARIABLE___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
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
			case RelalgPackage.ATTRIBUTE_VARIABLE___GET_BASE_VARIABLE:
				return getBaseVariable();
			case RelalgPackage.ATTRIBUTE_VARIABLE___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
	}

} //AttributeVariableImpl
