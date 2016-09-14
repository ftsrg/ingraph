/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Edges Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.GetEdgesOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.GetEdgesOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 *   <li>{@link relalg.GetEdgesOperator#getEdgeVariable <em>Edge Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getGetEdgesOperator()
 * @model
 * @generated
 */
public interface GetEdgesOperator extends AlgebraExpression {
	/**
	 * Returns the value of the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Vertex Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Vertex Variable</em>' reference.
	 * @see #setSourceVertexVariable(VertexVariable)
	 * @see relalg.RelalgPackage#getGetEdgesOperator_SourceVertexVariable()
	 * @model required="true"
	 * @generated
	 */
	VertexVariable getSourceVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.GetEdgesOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Vertex Variable</em>' reference.
	 * @see #getSourceVertexVariable()
	 * @generated
	 */
	void setSourceVertexVariable(VertexVariable value);

	/**
	 * Returns the value of the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Vertex Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Vertex Variable</em>' reference.
	 * @see #setTargetVertexVariable(VertexVariable)
	 * @see relalg.RelalgPackage#getGetEdgesOperator_TargetVertexVariable()
	 * @model required="true"
	 * @generated
	 */
	VertexVariable getTargetVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.GetEdgesOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Vertex Variable</em>' reference.
	 * @see #getTargetVertexVariable()
	 * @generated
	 */
	void setTargetVertexVariable(VertexVariable value);

	/**
	 * Returns the value of the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Variable</em>' reference.
	 * @see #setEdgeVariable(EdgeVariable)
	 * @see relalg.RelalgPackage#getGetEdgesOperator_EdgeVariable()
	 * @model required="true"
	 * @generated
	 */
	EdgeVariable getEdgeVariable();

	/**
	 * Sets the value of the '{@link relalg.GetEdgesOperator#getEdgeVariable <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Variable</em>' reference.
	 * @see #getEdgeVariable()
	 * @generated
	 */
	void setEdgeVariable(EdgeVariable value);

} // GetEdgesOperator
