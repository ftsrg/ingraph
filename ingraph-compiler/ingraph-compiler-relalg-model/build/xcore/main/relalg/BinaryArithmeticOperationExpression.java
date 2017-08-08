/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Arithmetic Operation Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BinaryArithmeticOperationExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.BinaryArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link relalg.BinaryArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBinaryArithmeticOperationExpression()
 * @model
 * @generated
 */
public interface BinaryArithmeticOperationExpression extends BinaryExpression, ArithmeticExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.BinaryArithmeticOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryArithmeticOperatorType
	 * @see #setOperator(BinaryArithmeticOperatorType)
	 * @see relalg.RelalgPackage#getBinaryArithmeticOperationExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	BinaryArithmeticOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.BinaryArithmeticOperationExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryArithmeticOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BinaryArithmeticOperatorType value);

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' reference.
	 * @see #setLeftOperand(ArithmeticExpression)
	 * @see relalg.RelalgPackage#getBinaryArithmeticOperationExpression_LeftOperand()
	 * @model
	 * @generated
	 */
	ArithmeticExpression getLeftOperand();

	/**
	 * Sets the value of the '{@link relalg.BinaryArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(ArithmeticExpression value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' reference.
	 * @see #setRightOperand(ArithmeticExpression)
	 * @see relalg.RelalgPackage#getBinaryArithmeticOperationExpression_RightOperand()
	 * @model
	 * @generated
	 */
	ArithmeticExpression getRightOperand();

	/**
	 * Sets the value of the '{@link relalg.BinaryArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(ArithmeticExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.ArithmeticExpression%> _leftOperand = this.getLeftOperand();\n<%java.lang.String%> _fullName = _leftOperand.fullName();\n<%relalg.BinaryArithmeticOperatorType%> _operator = this.getOperator();\n<%java.lang.String%> _plus = (_fullName + _operator);\n<%relalg.ArithmeticExpression%> _rightOperand = this.getRightOperand();\n<%java.lang.String%> _fullName_1 = _rightOperand.fullName();\nreturn (_plus + _fullName_1);'"
	 * @generated
	 */
	String fullName();

} // BinaryArithmeticOperationExpression
