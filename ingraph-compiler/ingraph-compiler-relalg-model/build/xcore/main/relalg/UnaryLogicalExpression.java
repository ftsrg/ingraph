/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Logical Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.UnaryLogicalExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.UnaryLogicalExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getUnaryLogicalExpression()
 * @model
 * @generated
 */
public interface UnaryLogicalExpression extends UnaryExpression, LogicalExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.UnaryLogicalOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryLogicalOperatorType
	 * @see #setOperator(UnaryLogicalOperatorType)
	 * @see relalg.RelalgPackage#getUnaryLogicalExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	UnaryLogicalOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.UnaryLogicalExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryLogicalOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(UnaryLogicalOperatorType value);

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' reference.
	 * @see #setOperand(LogicalExpression)
	 * @see relalg.RelalgPackage#getUnaryLogicalExpression_Operand()
	 * @model
	 * @generated
	 */
	LogicalExpression getOperand();

	/**
	 * Sets the value of the '{@link relalg.UnaryLogicalExpression#getOperand <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(LogicalExpression value);

} // UnaryLogicalExpression
