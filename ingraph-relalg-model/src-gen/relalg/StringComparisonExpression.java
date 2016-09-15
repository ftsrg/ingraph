/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Comparison Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.StringComparisonExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getStringComparisonExpression()
 * @model
 * @generated
 */
public interface StringComparisonExpression extends ComparisonExpression {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.StringComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see relalg.StringComparisonOperator
	 * @see #setOperator(StringComparisonOperator)
	 * @see relalg.RelalgPackage#getStringComparisonExpression_Operator()
	 * @model
	 * @generated
	 */
	StringComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link relalg.StringComparisonExpression#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see relalg.StringComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(StringComparisonOperator value);

} // StringComparisonExpression
