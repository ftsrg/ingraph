/**
 */
package relalg.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.Expression;
import relalg.ListExpression;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;
import relalg.VariableExpression;
import relalg.VariableListExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable List Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.VariableListExpressionImpl#getHead <em>Head</em>}</li>
 *   <li>{@link relalg.impl.VariableListExpressionImpl#getTail <em>Tail</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableListExpressionImpl extends VariableExpressionImpl implements VariableListExpression {
	/**
	 * The cached value of the '{@link #getHead() <em>Head</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHead()
	 * @generated
	 * @ordered
	 */
	protected Expression head;

	/**
	 * The cached value of the '{@link #getTail() <em>Tail</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTail()
	 * @generated
	 * @ordered
	 */
	protected ListExpression tail;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableListExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.VARIABLE_LIST_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getHead() {
		if (head != null && head.eIsProxy()) {
			InternalEObject oldHead = (InternalEObject)head;
			head = (Expression)eResolveProxy(oldHead);
			if (head != oldHead) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD, oldHead, head));
			}
		}
		return head;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetHead() {
		return head;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHead(Expression newHead) {
		Expression oldHead = head;
		head = newHead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD, oldHead, head));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListExpression getTail() {
		if (tail != null && tail.eIsProxy()) {
			InternalEObject oldTail = (InternalEObject)tail;
			tail = (ListExpression)eResolveProxy(oldTail);
			if (tail != oldTail) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL, oldTail, tail));
			}
		}
		return tail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListExpression basicGetTail() {
		return tail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTail(ListExpression newTail) {
		ListExpression oldTail = tail;
		tail = newTail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL, oldTail, tail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		Expression _head = this.getHead();
		String _fullName = null;
		if (_head!=null) {
			_fullName=_head.fullName();
		}
		String _plus = (_fullName + ", ");
		ListExpression _tail = this.getTail();
		String _fullName_1 = null;
		if (_tail!=null) {
			_fullName_1=_tail.fullName();
		}
		return (_plus + _fullName_1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD:
				if (resolve) return getHead();
				return basicGetHead();
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL:
				if (resolve) return getTail();
				return basicGetTail();
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
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD:
				setHead((Expression)newValue);
				return;
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL:
				setTail((ListExpression)newValue);
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
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD:
				setHead((Expression)null);
				return;
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL:
				setTail((ListExpression)null);
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
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD:
				return head != null;
			case RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL:
				return tail != null;
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
		if (baseClass == ListExpression.class) {
			switch (derivedFeatureID) {
				case RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD: return RelalgPackage.LIST_EXPRESSION__HEAD;
				case RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL: return RelalgPackage.LIST_EXPRESSION__TAIL;
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
		if (baseClass == ListExpression.class) {
			switch (baseFeatureID) {
				case RelalgPackage.LIST_EXPRESSION__HEAD: return RelalgPackage.VARIABLE_LIST_EXPRESSION__HEAD;
				case RelalgPackage.LIST_EXPRESSION__TAIL: return RelalgPackage.VARIABLE_LIST_EXPRESSION__TAIL;
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
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.VARIABLE_LIST_EXPRESSION___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == VariableExpression.class) {
			switch (baseOperationID) {
				case RelalgPackage.VARIABLE_EXPRESSION___FULL_NAME: return RelalgPackage.VARIABLE_LIST_EXPRESSION___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ListExpression.class) {
			switch (baseOperationID) {
				case RelalgPackage.LIST_EXPRESSION___FULL_NAME: return RelalgPackage.VARIABLE_LIST_EXPRESSION___FULL_NAME;
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
			case RelalgPackage.VARIABLE_LIST_EXPRESSION___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
	}

} //VariableListExpressionImpl
