/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AttributeVariable#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.AttributeVariable#getVertexVariable <em>Vertex Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAttributeVariable()
 * @model
 * @generated
 */
public interface AttributeVariable extends Variable {

	/**
	 * Returns the value of the '<em><b>Edge Variable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link relalg.EdgeVariable#getAttributeVariable <em>Attribute Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Variable</em>' reference.
	 * @see #setEdgeVariable(EdgeVariable)
	 * @see relalg.RelalgPackage#getAttributeVariable_EdgeVariable()
	 * @see relalg.EdgeVariable#getAttributeVariable
	 * @model opposite="attributeVariable" required="true"
	 * @generated
	 */
	EdgeVariable getEdgeVariable();

	/**
	 * Sets the value of the '{@link relalg.AttributeVariable#getEdgeVariable <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Variable</em>' reference.
	 * @see #getEdgeVariable()
	 * @generated
	 */
	void setEdgeVariable(EdgeVariable value);

	/**
	 * Returns the value of the '<em><b>Vertex Variable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link relalg.VertexVariable#getAttributeVariable <em>Attribute Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertex Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex Variable</em>' reference.
	 * @see #setVertexVariable(VertexVariable)
	 * @see relalg.RelalgPackage#getAttributeVariable_VertexVariable()
	 * @see relalg.VertexVariable#getAttributeVariable
	 * @model opposite="attributeVariable" required="true"
	 * @generated
	 */
	VertexVariable getVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.AttributeVariable#getVertexVariable <em>Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertex Variable</em>' reference.
	 * @see #getVertexVariable()
	 * @generated
	 */
	void setVertexVariable(VertexVariable value);
} // AttributeVariable
