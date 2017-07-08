/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Graph Object Logical Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.UnaryGraphObjectLogicalExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.UnaryGraphObjectLogicalExpression#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getUnaryGraphObjectLogicalExpression()
 * @model
 * @generated
 */
public interface UnaryGraphObjectLogicalExpression extends UnaryExpression, LogicalExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.UnaryGraphObjectLogicalOperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryGraphObjectLogicalOperatorType
	 * @see #setOperator(UnaryGraphObjectLogicalOperatorType)
	 * @see relalg.RelalgPackage#getUnaryGraphObjectLogicalExpression_Operator()
	 * @model unique="false"
	 * @generated
	 */
	UnaryGraphObjectLogicalOperatorType getOperator();

	/**
	 * Sets the value of the '{@link relalg.UnaryGraphObjectLogicalExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.UnaryGraphObjectLogicalOperatorType
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(UnaryGraphObjectLogicalOperatorType value);

	/**
	 * Returns the value of the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' reference.
	 * @see #setOperand(Variable)
	 * @see relalg.RelalgPackage#getUnaryGraphObjectLogicalExpression_Operand()
	 * @model
	 * @generated
	 */
	Variable getOperand();

	/**
	 * Sets the value of the '{@link relalg.UnaryGraphObjectLogicalExpression#getOperand <em>Operand</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Variable value);

} // UnaryGraphObjectLogicalExpression
