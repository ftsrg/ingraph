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
 *   <li>{@link relalg.SelectionOperator#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getSelectionOperator()
 * @model
 * @generated
 */
public interface SelectionOperator extends AlphaOperator {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see relalg.RelalgPackage#getSelectionOperator_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link relalg.SelectionOperator#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

} // SelectionOperator
