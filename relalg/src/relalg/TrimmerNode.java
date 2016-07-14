/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trimmer Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.TrimmerNode#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getTrimmerNode()
 * @model
 * @generated
 */
public interface TrimmerNode extends ReteNode {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(ReteNode)
	 * @see relalg.RelalgPackage#getTrimmerNode_Parent()
	 * @model
	 * @generated
	 */
	ReteNode getParent();

	/**
	 * Sets the value of the '{@link relalg.TrimmerNode#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ReteNode value);

} // TrimmerNode
