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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.ListExpressionImpl#getHead <em>Head</em>}</li>
 *   <li>{@link relalg.impl.ListExpressionImpl#getTail <em>Tail</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListExpressionImpl extends ExpressionImpl implements ListExpression {
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
	protected ListExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.LIST_EXPRESSION;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.LIST_EXPRESSION__HEAD, oldHead, head));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.LIST_EXPRESSION__HEAD, oldHead, head));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.LIST_EXPRESSION__TAIL, oldTail, tail));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.LIST_EXPRESSION__TAIL, oldTail, tail));
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
			case RelalgPackage.LIST_EXPRESSION__HEAD:
				if (resolve) return getHead();
				return basicGetHead();
			case RelalgPackage.LIST_EXPRESSION__TAIL:
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
			case RelalgPackage.LIST_EXPRESSION__HEAD:
				setHead((Expression)newValue);
				return;
			case RelalgPackage.LIST_EXPRESSION__TAIL:
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
			case RelalgPackage.LIST_EXPRESSION__HEAD:
				setHead((Expression)null);
				return;
			case RelalgPackage.LIST_EXPRESSION__TAIL:
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
			case RelalgPackage.LIST_EXPRESSION__HEAD:
				return head != null;
			case RelalgPackage.LIST_EXPRESSION__TAIL:
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
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == RelalgModelElement.class) {
			switch (baseOperationID) {
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.LIST_EXPRESSION___FULL_NAME;
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
			case RelalgPackage.LIST_EXPRESSION___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ListExpressionImpl
