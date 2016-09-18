/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Vertices Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.GetVerticesOperator#getVertexVariable <em>Vertex Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getGetVerticesOperator()
 * @model
 * @generated
 */
public interface GetVerticesOperator extends Operator {
	/**
	 * Returns the value of the '<em><b>Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertex Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex Variable</em>' reference.
	 * @see #setVertexVariable(VertexVariable)
	 * @see relalg.RelalgPackage#getGetVerticesOperator_VertexVariable()
	 * @model required="true"
	 * @generated
	 */
	VertexVariable getVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.GetVerticesOperator#getVertexVariable <em>Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vertex Variable</em>' reference.
	 * @see #getVertexVariable()
	 * @generated
	 */
	void setVertexVariable(VertexVariable value);

} // GetVerticesOperator
