/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comparison Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ComparisonExpression#getLeftOperand <em>Left Operand</em>}</li>
 *   <li>{@link relalg.ComparisonExpression#getRightOperand <em>Right Operand</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getComparisonExpression()
 * @model abstract="true"
 * @generated
 */
public interface ComparisonExpression extends BinaryExpression {

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
	 * @see relalg.RelalgPackage#getComparisonExpression_LeftOperand()
	 * @model containment="true" required="true"
	 * @generated
	 */
	relalg.Comparable getLeftOperand();

	/**
	 * Sets the value of the '{@link relalg.ComparisonExpression#getLeftOperand <em>Left Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Operand</em>' containment reference.
	 * @see #getLeftOperand()
	 * @generated
	 */
	void setLeftOperand(relalg.Comparable value);

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
	 * @see relalg.RelalgPackage#getComparisonExpression_RightOperand()
	 * @model containment="true" required="true"
	 * @generated
	 */
	relalg.Comparable getRightOperand();

	/**
	 * Sets the value of the '{@link relalg.ComparisonExpression#getRightOperand <em>Right Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Operand</em>' containment reference.
	 * @see #getRightOperand()
	 * @generated
	 */
	void setRightOperand(relalg.Comparable value);

} // ComparisonExpression
