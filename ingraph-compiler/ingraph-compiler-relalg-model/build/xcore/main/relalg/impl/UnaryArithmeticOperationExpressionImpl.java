/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.ArithmeticExpression;
import relalg.RelalgPackage;
import relalg.UnaryArithmeticOperationExpression;
import relalg.UnaryArithmeticOperatorType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unary Arithmetic Operation Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.UnaryArithmeticOperationExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.impl.UnaryArithmeticOperationExpressionImpl#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnaryArithmeticOperationExpressionImpl extends UnaryExpressionImpl implements UnaryArithmeticOperationExpression {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final UnaryArithmeticOperatorType OPERATOR_EDEFAULT = UnaryArithmeticOperatorType.MINUS;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected UnaryArithmeticOperatorType operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOperand() <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperand()
	 * @generated
	 * @ordered
	 */
	protected ArithmeticExpression operand;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnaryArithmeticOperationExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.UNARY_ARITHMETIC_OPERATION_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryArithmeticOperatorType getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(UnaryArithmeticOperatorType newOperator) {
		UnaryArithmeticOperatorType oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression getOperand() {
		if (operand != null && operand.eIsProxy()) {
			InternalEObject oldOperand = (InternalEObject)operand;
			operand = (ArithmeticExpression)eResolveProxy(oldOperand);
			if (operand != oldOperand) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND, oldOperand, operand));
			}
		}
		return operand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpression basicGetOperand() {
		return operand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperand(ArithmeticExpression newOperand) {
		ArithmeticExpression oldOperand = operand;
		operand = newOperand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND, oldOperand, operand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				return getOperator();
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND:
				if (resolve) return getOperand();
				return basicGetOperand();
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
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				setOperator((UnaryArithmeticOperatorType)newValue);
				return;
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND:
				setOperand((ArithmeticExpression)newValue);
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
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND:
				setOperand((ArithmeticExpression)null);
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
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND:
				return operand != null;
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //UnaryArithmeticOperationExpressionImpl
