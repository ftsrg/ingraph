/**
 */
package relalg.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.ArithmeticExpression;
import relalg.BinaryArithmeticOperationExpression;
import relalg.BinaryArithmeticOperatorType;
import relalg.ComparableExpression;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Arithmetic Operation Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.BinaryArithmeticOperationExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.impl.BinaryArithmeticOperationExpressionImpl#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link relalg.impl.BinaryArithmeticOperationExpressionImpl#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BinaryArithmeticOperationExpressionImpl extends BinaryExpressionImpl implements BinaryArithmeticOperationExpression {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final BinaryArithmeticOperatorType OPERATOR_EDEFAULT = BinaryArithmeticOperatorType.PLUS;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected BinaryArithmeticOperatorType operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftOperand() <em>Left Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftOperand()
	 * @generated
	 * @ordered
	 */
	protected ArithmeticExpression leftOperand;

	/**
	 * The cached value of the '{@link #getRightOperand() <em>Right Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightOperand()
	 * @generated
	 * @ordered
	 */
	protected ArithmeticExpression rightOperand;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryArithmeticOperationExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.BINARY_ARITHMETIC_OPERATION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryArithmeticOperatorType getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(BinaryArithmeticOperatorType newOperator) {
		BinaryArithmeticOperatorType oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression getLeftOperand() {
		if (leftOperand != null && leftOperand.eIsProxy()) {
			InternalEObject oldLeftOperand = (InternalEObject)leftOperand;
			leftOperand = (ArithmeticExpression)eResolveProxy(oldLeftOperand);
			if (leftOperand != oldLeftOperand) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND, oldLeftOperand, leftOperand));
			}
		}
		return leftOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression basicGetLeftOperand() {
		return leftOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftOperand(ArithmeticExpression newLeftOperand) {
		ArithmeticExpression oldLeftOperand = leftOperand;
		leftOperand = newLeftOperand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND, oldLeftOperand, leftOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression getRightOperand() {
		if (rightOperand != null && rightOperand.eIsProxy()) {
			InternalEObject oldRightOperand = (InternalEObject)rightOperand;
			rightOperand = (ArithmeticExpression)eResolveProxy(oldRightOperand);
			if (rightOperand != oldRightOperand) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND, oldRightOperand, rightOperand));
			}
		}
		return rightOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression basicGetRightOperand() {
		return rightOperand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightOperand(ArithmeticExpression newRightOperand) {
		ArithmeticExpression oldRightOperand = rightOperand;
		rightOperand = newRightOperand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND, oldRightOperand, rightOperand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		ArithmeticExpression _leftOperand = this.getLeftOperand();
		String _fullName = _leftOperand.fullName();
		BinaryArithmeticOperatorType _operator = this.getOperator();
		String _plus = (_fullName + _operator);
		ArithmeticExpression _rightOperand = this.getRightOperand();
		String _fullName_1 = _rightOperand.fullName();
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
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				return getOperator();
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND:
				if (resolve) return getLeftOperand();
				return basicGetLeftOperand();
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND:
				if (resolve) return getRightOperand();
				return basicGetRightOperand();
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
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				setOperator((BinaryArithmeticOperatorType)newValue);
				return;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((ArithmeticExpression)newValue);
				return;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((ArithmeticExpression)newValue);
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
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND:
				setLeftOperand((ArithmeticExpression)null);
				return;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND:
				setRightOperand((ArithmeticExpression)null);
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
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND:
				return leftOperand != null;
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND:
				return rightOperand != null;
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
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ComparableExpression.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == ArithmeticExpression.class) {
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
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME:
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //BinaryArithmeticOperationExpressionImpl
