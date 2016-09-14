/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expand Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ExpandOperator#getDirection <em>Direction</em>}</li>
 *   <li>{@link relalg.ExpandOperator#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.ExpandOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.ExpandOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getExpandOperator()
 * @model
 * @generated
 */
public interface ExpandOperator extends AlphaOperator {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The default value is <code>"IN"</code>.
	 * The literals are from the enumeration {@link relalg.Direction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see relalg.Direction
	 * @see #setDirection(Direction)
	 * @see relalg.RelalgPackage#getExpandOperator_Direction()
	 * @model default="IN"
	 * @generated
	 */
	Direction getDirection();

	/**
	 * Sets the value of the '{@link relalg.ExpandOperator#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see relalg.Direction
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(Direction value);

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
	 * @see relalg.RelalgPackage#getExpandOperator_EdgeVariable()
	 * @model required="true"
	 * @generated
	 */
	EdgeVariable getEdgeVariable();

	/**
	 * Sets the value of the '{@link relalg.ExpandOperator#getEdgeVariable <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Variable</em>' reference.
	 * @see #getEdgeVariable()
	 * @generated
	 */
	void setEdgeVariable(EdgeVariable value);

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
	 * @see relalg.RelalgPackage#getExpandOperator_SourceVertexVariable()
	 * @model required="true"
	 * @generated
	 */
	VertexVariable getSourceVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.ExpandOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}' reference.
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
	 * @see relalg.RelalgPackage#getExpandOperator_TargetVertexVariable()
	 * @model required="true"
	 * @generated
	 */
	VertexVariable getTargetVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.ExpandOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Vertex Variable</em>' reference.
	 * @see #getTargetVertexVariable()
	 * @generated
	 */
	void setTargetVertexVariable(VertexVariable value);

} // ExpandOperator
