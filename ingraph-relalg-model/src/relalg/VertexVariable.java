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
 *   <li>{@link relalg.VertexVariable#getVertexLabel <em>Vertex Label</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getVertexVariable()
 * @model
 * @generated
 */
public interface VertexVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Vertex Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertex Label</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex Label</em>' reference.
	 * @see #setVertexLabel(VertexLabel)
	 * @see relalg.RelalgPackage#getVertexVariable_VertexLabel()
	 * @model required="true"
	 * @generated
	 */
	VertexLabel getVertexLabel();

	/**
	 * Sets the value of the '{@link relalg.VertexVariable#getVertexLabel <em>Vertex Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertex Label</em>' reference.
	 * @see #getVertexLabel()
	 * @generated
	 */
	void setVertexLabel(VertexLabel value);

} // VertexVariable
