/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge List Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This is the variable for r in the query below:
 * MATCH (m)-[r*2..5]->(n)
 * RETURN m, r, n
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.EdgeListVariable#getMinHops <em>Min Hops</em>}</li>
 *   <li>{@link relalg.EdgeListVariable#getMaxHops <em>Max Hops</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getEdgeListVariable()
 * @model
 * @generated
 */
public interface EdgeListVariable extends AbstractEdgeVariable {
	/**
	 * Returns the value of the '<em><b>Min Hops</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Hops</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Hops</em>' attribute.
	 * @see #setMinHops(int)
	 * @see relalg.RelalgPackage#getEdgeListVariable_MinHops()
	 * @model unique="false"
	 * @generated
	 */
	int getMinHops();

	/**
	 * Sets the value of the '{@link relalg.EdgeListVariable#getMinHops <em>Min Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Hops</em>' attribute.
	 * @see #getMinHops()
	 * @generated
	 */
	void setMinHops(int value);

	/**
	 * Returns the value of the '<em><b>Max Hops</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Hops</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Hops</em>' containment reference.
	 * @see #setMaxHops(MaxHops)
	 * @see relalg.RelalgPackage#getEdgeListVariable_MaxHops()
	 * @model containment="true"
	 * @generated
	 */
	MaxHops getMaxHops();

	/**
	 * Sets the value of the '{@link relalg.EdgeListVariable#getMaxHops <em>Max Hops</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Hops</em>' containment reference.
	 * @see #getMaxHops()
	 * @generated
	 */
	void setMaxHops(MaxHops value);

} // EdgeListVariable
