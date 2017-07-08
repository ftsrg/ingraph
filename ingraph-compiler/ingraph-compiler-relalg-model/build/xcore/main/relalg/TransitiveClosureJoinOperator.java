/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transitive Closure Join Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.TransitiveClosureJoinOperator#getEdgeListVariable <em>Edge List Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getTransitiveClosureJoinOperator()
 * @model
 * @generated
 */
public interface TransitiveClosureJoinOperator extends EquiJoinLikeOperator {
	/**
	 * Returns the value of the '<em><b>Edge List Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge List Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge List Variable</em>' reference.
	 * @see #setEdgeListVariable(EdgeListVariable)
	 * @see relalg.RelalgPackage#getTransitiveClosureJoinOperator_EdgeListVariable()
	 * @model
	 * @generated
	 */
	EdgeListVariable getEdgeListVariable();

	/**
	 * Sets the value of the '{@link relalg.TransitiveClosureJoinOperator#getEdgeListVariable <em>Edge List Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge List Variable</em>' reference.
	 * @see #getEdgeListVariable()
	 * @generated
	 */
	void setEdgeListVariable(EdgeListVariable value);

} // TransitiveClosureJoinOperator
