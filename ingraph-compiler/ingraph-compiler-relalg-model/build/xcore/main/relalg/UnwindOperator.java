/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unwind Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.UnwindOperator#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getUnwindOperator()
 * @model
 * @generated
 */
public interface UnwindOperator extends UnaryOperator {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(ExpressionVariable)
	 * @see relalg.RelalgPackage#getUnwindOperator_Element()
	 * @model
	 * @generated
	 */
	ExpressionVariable getElement();

	/**
	 * Sets the value of the '{@link relalg.UnwindOperator#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(ExpressionVariable value);

} // UnwindOperator
