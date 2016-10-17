/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.EdgeVariable#getEdgeLabel <em>Edge Label</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getEdgeVariable()
 * @model
 * @generated
 */
public interface EdgeVariable extends ElementVariable {
	/**
	 * Returns the value of the '<em><b>Edge Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Label</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Label</em>' reference.
	 * @see #setEdgeLabel(EdgeLabel)
	 * @see relalg.RelalgPackage#getEdgeVariable_EdgeLabel()
	 * @model required="true"
	 * @generated
	 */
	EdgeLabel getEdgeLabel();

	/**
	 * Sets the value of the '{@link relalg.EdgeVariable#getEdgeLabel <em>Edge Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Label</em>' reference.
	 * @see #getEdgeLabel()
	 * @generated
	 */
	void setEdgeLabel(EdgeLabel value);

} // EdgeVariable
