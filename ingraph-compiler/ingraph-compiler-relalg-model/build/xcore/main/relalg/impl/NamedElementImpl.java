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

import relalg.NamedElement;
import relalg.RelalgContainer;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.NamedElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link relalg.impl.NamedElementImpl#getNamedElementContainer <em>Named Element Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NamedElementImpl extends RelalgModelElementImpl implements NamedElement {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.NAMED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.NAMED_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgContainer getNamedElementContainer() {
		if (eContainerFeatureID() != RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER) return null;
		return (RelalgContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgContainer basicGetNamedElementContainer() {
		if (eContainerFeatureID() != RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER) return null;
		return (RelalgContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNamedElementContainer(RelalgContainer newNamedElementContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNamedElementContainer, RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamedElementContainer(RelalgContainer newNamedElementContainer) {
		if (newNamedElementContainer != eInternalContainer() || (eContainerFeatureID() != RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER && newNamedElementContainer != null)) {
			if (EcoreUtil.isAncestor(this, newNamedElementContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNamedElementContainer != null)
				msgs = ((InternalEObject)newNamedElementContainer).eInverseAdd(this, RelalgPackage.RELALG_CONTAINER__ELEMENTS, RelalgContainer.class, msgs);
			msgs = basicSetNamedElementContainer(newNamedElementContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER, newNamedElementContainer, newNamedElementContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		return this.getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetNamedElementContainer((RelalgContainer)otherEnd, msgs);
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
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				return basicSetNamedElementContainer(null, msgs);
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
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				return eInternalContainer().eInverseRemove(this, RelalgPackage.RELALG_CONTAINER__ELEMENTS, RelalgContainer.class, msgs);
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
			case RelalgPackage.NAMED_ELEMENT__NAME:
				return getName();
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				if (resolve) return getNamedElementContainer();
				return basicGetNamedElementContainer();
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
			case RelalgPackage.NAMED_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				setNamedElementContainer((RelalgContainer)newValue);
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
			case RelalgPackage.NAMED_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				setNamedElementContainer((RelalgContainer)null);
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
			case RelalgPackage.NAMED_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER:
				return basicGetNamedElementContainer() != null;
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
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.NAMED_ELEMENT___FULL_NAME;
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
			case RelalgPackage.NAMED_ELEMENT___FULL_NAME:
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //NamedElementImpl
