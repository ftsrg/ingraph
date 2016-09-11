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
 *   <li>{@link relalg.BinaryLogicalExpression#getRightExpression <em>Right Expression</em>}</li>
 *   <li>{@link relalg.BinaryLogicalExpression#getLeftExpression <em>Left Expression</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBinaryLogicalExpression()
 * @model
 * @generated
 */
public interface BinaryLogicalExpression extends BinaryExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.BinaryLogicalOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryLogicalOperator
	 * @see #setOperator(BinaryLogicalOperator)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_Operator()
	 * @model
	 * @generated
	 */
	BinaryLogicalOperator getOperator();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.BinaryLogicalOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BinaryLogicalOperator value);

	/**
	 * Returns the value of the '<em><b>Right Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Expression</em>' containment reference.
	 * @see #setRightExpression(Expression)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_RightExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRightExpression();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getRightExpression <em>Right Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Expression</em>' containment reference.
	 * @see #getRightExpression()
	 * @generated
	 */
	void setRightExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Left Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Expression</em>' containment reference.
	 * @see #setLeftExpression(Expression)
	 * @see relalg.RelalgPackage#getBinaryLogicalExpression_LeftExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getLeftExpression();

	/**
	 * Sets the value of the '{@link relalg.BinaryLogicalExpression#getLeftExpression <em>Left Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Expression</em>' containment reference.
	 * @see #getLeftExpression()
	 * @generated
	 */
	void setLeftExpression(Expression value);

} // BinaryLogicalExpression
