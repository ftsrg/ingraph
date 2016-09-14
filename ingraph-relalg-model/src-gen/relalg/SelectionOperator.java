/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selection Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.SelectionOperator#getConditionString <em>Condition String</em>}</li>
 *   <li>{@link relalg.SelectionOperator#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getSelectionOperator()
 * @model
 * @generated
 */
public interface SelectionOperator extends AlphaOperator {
	/**
	 * Returns the value of the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition String</em>' attribute.
	 * @see #setConditionString(String)
	 * @see relalg.RelalgPackage#getSelectionOperator_ConditionString()
	 * @model
	 * @generated
	 */
	String getConditionString();

	/**
	 * Sets the value of the '{@link relalg.SelectionOperator#getConditionString <em>Condition String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition String</em>' attribute.
	 * @see #getConditionString()
	 * @generated
	 */
	void setConditionString(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expression)
	 * @see relalg.RelalgPackage#getSelectionOperator_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link relalg.SelectionOperator#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

} // SelectionOperator
