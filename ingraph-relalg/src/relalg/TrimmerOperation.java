/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trimmer Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.TrimmerOperation#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getTrimmerOperation()
 * @model
 * @generated
 */
public interface TrimmerOperation extends AlphaOperation {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference.
	 * @see #setAttributes(AttributeSet)
	 * @see relalg.RelalgPackage#getTrimmerOperation_Attributes()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AttributeSet getAttributes();

	/**
	 * Sets the value of the '{@link relalg.TrimmerOperation#getAttributes <em>Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attributes</em>' containment reference.
	 * @see #getAttributes()
	 * @generated
	 */
	void setAttributes(AttributeSet value);

} // TrimmerOperation
