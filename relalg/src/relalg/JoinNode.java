/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Join Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.JoinNode#getLeftParent <em>Left Parent</em>}</li>
 *   <li>{@link relalg.JoinNode#getRightParent <em>Right Parent</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getJoinNode()
 * @model
 * @generated
 */
public interface JoinNode extends ReteNode {
	/**
	 * Returns the value of the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Parent</em>' reference.
	 * @see #setLeftParent(ReteNode)
	 * @see relalg.RelalgPackage#getJoinNode_LeftParent()
	 * @model
	 * @generated
	 */
	ReteNode getLeftParent();

	/**
	 * Sets the value of the '{@link relalg.JoinNode#getLeftParent <em>Left Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Parent</em>' reference.
	 * @see #getLeftParent()
	 * @generated
	 */
	void setLeftParent(ReteNode value);

	/**
	 * Returns the value of the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Parent</em>' reference.
	 * @see #setRightParent(ReteNode)
	 * @see relalg.RelalgPackage#getJoinNode_RightParent()
	 * @model
	 * @generated
	 */
	ReteNode getRightParent();

	/**
	 * Sets the value of the '{@link relalg.JoinNode#getRightParent <em>Right Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Parent</em>' reference.
	 * @see #getRightParent()
	 * @generated
	 */
	void setRightParent(ReteNode value);

} // JoinNode
