/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Arithmetic Operation Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.UnaryArithmeticOperationExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.UnaryArithmeticOperationExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getUnaryArithmeticOperationExpression()
 * @model
 * @generated
 */
public interface UnaryArithmeticOperationExpression extends UnaryExpression, ArithmeticExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.UnaryArithmeticOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryArithmeticOperatorType
	 * @see #setOperator(UnaryArithmeticOperatorType)
	 * @see relalg.RelalgPackage#getUnaryArithmeticOperationExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	UnaryArithmeticOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.UnaryArithmeticOperationExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryArithmeticOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(UnaryArithmeticOperatorType value);

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' reference.
	 * @see #setOperand(ArithmeticExpression)
	 * @see relalg.RelalgPackage#getUnaryArithmeticOperationExpression_Operand()
	 * @model
	 * @generated
	 */
	ArithmeticExpression getOperand();

	/**
	 * Sets the value of the '{@link relalg.UnaryArithmeticOperationExpression#getOperand <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(ArithmeticExpression value);

} // UnaryArithmeticOperationExpression
