/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arithmetic Operation Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ArithmeticOperationExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.ArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}</li>
 *   <li>{@link relalg.ArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getArithmeticOperationExpression()
 * @model
 * @generated
 */
public interface ArithmeticOperationExpression extends BinaryExpression, relalg.Comparable {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.BinaryArithmeticOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryArithmeticOperator
	 * @see #setOperator(BinaryArithmeticOperator)
	 * @see relalg.RelalgPackage#getArithmeticOperationExpression_Operator()
	 * @model
	 * @generated
	 */
	BinaryArithmeticOperator getOperator();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticOperationExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryArithmeticOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BinaryArithmeticOperator value);

	/**
	 * Returns the value of the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Operand</em>' containment reference.
	 * @see #setRightOperand(relalg.Comparable)
	 * @see relalg.RelalgPackage#getArithmeticOperationExpression_RightOperand()
	 * @model containment="true" required="true"
	 * @generated
	 */
	relalg.Comparable getRightOperand();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(relalg.Comparable value);

	/**
	 * Returns the value of the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Operand</em>' containment reference.
	 * @see #setLeftOperand(relalg.Comparable)
	 * @see relalg.RelalgPackage#getArithmeticOperationExpression_LeftOperand()
	 * @model containment="true" required="true"
	 * @generated
	 */
	relalg.Comparable getLeftOperand();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(relalg.Comparable value);

} // ArithmeticOperationExpression
