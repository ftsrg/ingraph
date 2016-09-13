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
 * </ul>
 *
 * @see relalg.RelalgPackage#getArithmeticComparisonExpression()
 * @model
 * @generated
 */
public interface ArithmeticComparisonExpression extends BinaryExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.ArithmeticComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.ArithmeticComparisonOperator
	 * @see #setOperator(ArithmeticComparisonOperator)
	 * @see relalg.RelalgPackage#getArithmeticComparisonExpression_Operator()
	 * @model
	 * @generated
	 */
	ArithmeticComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link relalg.ArithmeticComparisonExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.ArithmeticComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ArithmeticComparisonOperator value);

} // ArithmeticComparisonExpression
