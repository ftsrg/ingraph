/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.NavigationDescriptor#getDirection <em>Direction</em>}</li>
 *   <li>{@link relalg.NavigationDescriptor#getSourceVertexVariable <em>Source Vertex Variable</em>}</li>
 *   <li>{@link relalg.NavigationDescriptor#getEdgeVariable <em>Edge Variable</em>}</li>
 *   <li>{@link relalg.NavigationDescriptor#getTargetVertexVariable <em>Target Vertex Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getNavigationDescriptor()
 * @model
 * @generated
 */
public interface NavigationDescriptor extends Expression {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
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
	 * @see relalg.RelalgPackage#getNavigationDescriptor_Direction()
	 * @model unique="false"
	 * @generated
	 */
	Direction getDirection();

	/**
	 * Sets the value of the '{@link relalg.NavigationDescriptor#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see relalg.Direction
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(Direction value);

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
	 * @see relalg.RelalgPackage#getNavigationDescriptor_SourceVertexVariable()
	 * @model
	 * @generated
	 */
	VertexVariable getSourceVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.NavigationDescriptor#getSourceVertexVariable <em>Source Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Vertex Variable</em>' reference.
	 * @see #getSourceVertexVariable()
	 * @generated
	 */
	void setSourceVertexVariable(VertexVariable value);

	/**
	 * Returns the value of the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Variable</em>' reference.
	 * @see #setEdgeVariable(AbstractEdgeVariable)
	 * @see relalg.RelalgPackage#getNavigationDescriptor_EdgeVariable()
	 * @model
	 * @generated
	 */
	AbstractEdgeVariable getEdgeVariable();

	/**
	 * Sets the value of the '{@link relalg.NavigationDescriptor#getEdgeVariable <em>Edge Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Variable</em>' reference.
	 * @see #getEdgeVariable()
	 * @generated
	 */
	void setEdgeVariable(AbstractEdgeVariable value);

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
	 * @see relalg.RelalgPackage#getNavigationDescriptor_TargetVertexVariable()
	 * @model
	 * @generated
	 */
	VertexVariable getTargetVertexVariable();

	/**
	 * Sets the value of the '{@link relalg.NavigationDescriptor#getTargetVertexVariable <em>Target Vertex Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Vertex Variable</em>' reference.
	 * @see #getTargetVertexVariable()
	 * @generated
	 */
	void setTargetVertexVariable(VertexVariable value);

} // NavigationDescriptor
