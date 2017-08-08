/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Edge Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AbstractEdgeVariable#getEdgeLabelSet <em>Edge Label Set</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAbstractEdgeVariable()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEdgeVariable extends ElementVariable {
	/**
	 * Returns the value of the '<em><b>Edge Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Label Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Label Set</em>' containment reference.
	 * @see #setEdgeLabelSet(EdgeLabelSet)
	 * @see relalg.RelalgPackage#getAbstractEdgeVariable_EdgeLabelSet()
	 * @model containment="true"
	 * @generated
	 */
	EdgeLabelSet getEdgeLabelSet();

	/**
	 * Sets the value of the '{@link relalg.AbstractEdgeVariable#getEdgeLabelSet <em>Edge Label Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Label Set</em>' containment reference.
	 * @see #getEdgeLabelSet()
	 * @generated
	 */
	void setEdgeLabelSet(EdgeLabelSet value);

} // AbstractEdgeVariable
