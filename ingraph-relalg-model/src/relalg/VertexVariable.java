/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;


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
 *   <li>{@link relalg.VertexVariable#getAttributeVariables <em>Attribute Variables</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Attribute Variables</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.AttributeVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Variables</em>' containment reference list.
	 * @see relalg.RelalgPackage#getVertexVariable_AttributeVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeVariable> getAttributeVariables();

} // VertexVariable
