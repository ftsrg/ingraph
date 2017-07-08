/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.VertexVariable#getVertexLabelSet <em>Vertex Label Set</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getVertexVariable()
 * @model
 * @generated
 */
public interface VertexVariable extends ElementVariable {
	/**
	 * Returns the value of the '<em><b>Vertex Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set of labels for the vertex. The vertex must have *all* of these labels.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vertex Label Set</em>' containment reference.
	 * @see #setVertexLabelSet(VertexLabelSet)
	 * @see relalg.RelalgPackage#getVertexVariable_VertexLabelSet()
	 * @model containment="true"
	 * @generated
	 */
	VertexLabelSet getVertexLabelSet();

	/**
	 * Sets the value of the '{@link relalg.VertexVariable#getVertexLabelSet <em>Vertex Label Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertex Label Set</em>' containment reference.
	 * @see #getVertexLabelSet()
	 * @generated
	 */
	void setVertexLabelSet(VertexLabelSet value);

} // VertexVariable
