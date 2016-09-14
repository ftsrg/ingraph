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

} // ArithmeticOperationExpression
