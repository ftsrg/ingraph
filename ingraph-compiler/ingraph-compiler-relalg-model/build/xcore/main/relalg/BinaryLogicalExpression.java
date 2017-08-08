/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Logical Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BinaryLogicalExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.BinaryLogicalExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link relalg.BinaryLogicalExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBinaryLogicalExpression()
 * @model
 * @generated
 */
public interface BinaryLogicalExpression extends BinaryExpression, LogicalExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.BinaryLogicalOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryLogicalOperatorType
	 * @see #setOperator(BinaryLogicalOperatorType)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	BinaryLogicalOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryLogicalOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BinaryLogicalOperatorType value);

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' reference.
	 * @see #setLeftOperand(LogicalExpression)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_LeftOperand()
	 * @model
	 * @generated
	 */
	LogicalExpression getLeftOperand();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getLeftOperand <em>Left Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(LogicalExpression value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' reference.
	 * @see #setRightOperand(LogicalExpression)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_RightOperand()
	 * @model
	 * @generated
	 */
	LogicalExpression getRightOperand();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getRightOperand <em>Right Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(LogicalExpression value);

} // BinaryLogicalExpression
