/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arithmetic Comparison Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ArithmeticComparisonExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.ArithmeticComparisonExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link relalg.ArithmeticComparisonExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getArithmeticComparisonExpression()
 * @model
 * @generated
 */
public interface ArithmeticComparisonExpression extends BinaryExpression, LogicalExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.ArithmeticComparisonOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.ArithmeticComparisonOperatorType
	 * @see #setOperator(ArithmeticComparisonOperatorType)
	 * @see relalg.RelalgPackage#getArithmeticComparisonExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	ArithmeticComparisonOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticComparisonExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.ArithmeticComparisonOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ArithmeticComparisonOperatorType value);

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' reference.
	 * @see #setLeftOperand(ComparableExpression)
	 * @see relalg.RelalgPackage#getArithmeticComparisonExpression_LeftOperand()
	 * @model
	 * @generated
	 */
	ComparableExpression getLeftOperand();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticComparisonExpression#getLeftOperand <em>Left Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(ComparableExpression value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' reference.
	 * @see #setRightOperand(ComparableExpression)
	 * @see relalg.RelalgPackage#getArithmeticComparisonExpression_RightOperand()
	 * @model
	 * @generated
	 */
	ComparableExpression getRightOperand();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticComparisonExpression#getRightOperand <em>Right Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(ComparableExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.ComparableExpression%> _leftOperand = this.getLeftOperand();\n<%java.lang.String%> _fullName = _leftOperand.fullName();\n<%relalg.ArithmeticComparisonOperatorType%> _operator = this.getOperator();\n<%java.lang.String%> _plus = (_fullName + _operator);\n<%relalg.ComparableExpression%> _rightOperand = this.getRightOperand();\n<%java.lang.String%> _fullName_1 = _rightOperand.fullName();\nreturn (_plus + _fullName_1);'"
	 * @generated
	 */
	String fullName();

} // ArithmeticComparisonExpression
