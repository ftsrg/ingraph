/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Top Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This stands for the SKIP n LIMIT m clause.
 *  * Expressions in skip and limit may not contain variable references.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.TopOperator#getSkip <em>Skip</em>}</li>
 *   <li>{@link relalg.TopOperator#getLimit <em>Limit</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getTopOperator()
 * @model
 * @generated
 */
public interface TopOperator extends UnaryOperator {
	/**
	 * Returns the value of the '<em><b>Skip</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip</em>' reference.
	 * @see #setSkip(Expression)
	 * @see relalg.RelalgPackage#getTopOperator_Skip()
	 * @model
	 * @generated
	 */
	Expression getSkip();

	/**
	 * Sets the value of the '{@link relalg.TopOperator#getSkip <em>Skip</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip</em>' reference.
	 * @see #getSkip()
	 * @generated
	 */
	void setSkip(Expression value);

	/**
	 * Returns the value of the '<em><b>Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Limit</em>' reference.
	 * @see #setLimit(Expression)
	 * @see relalg.RelalgPackage#getTopOperator_Limit()
	 * @model
	 * @generated
	 */
	Expression getLimit();

	/**
	 * Sets the value of the '{@link relalg.TopOperator#getLimit <em>Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Limit</em>' reference.
	 * @see #getLimit()
	 * @generated
	 */
	void setLimit(Expression value);

} // TopOperator
