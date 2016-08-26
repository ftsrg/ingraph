/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.EdgeVariable#getEdgeLabel <em>Edge Label</em>}</li>
 *   <li>{@link relalg.EdgeVariable#getAttributeVariables <em>Attribute Variables</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getEdgeVariable()
 * @model
 * @generated
 */
public interface EdgeVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Edge Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Label</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Label</em>' reference.
	 * @see #setEdgeLabel(EdgeLabel)
	 * @see relalg.RelalgPackage#getEdgeVariable_EdgeLabel()
	 * @model required="true"
	 * @generated
	 */
	EdgeLabel getEdgeLabel();

	/**
	 * Sets the value of the '{@link relalg.EdgeVariable#getEdgeLabel <em>Edge Label</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edge Label</em>' reference.
	 * @see #getEdgeLabel()
	 * @generated
	 */
	void setEdgeLabel(EdgeLabel value);

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
	 * @see relalg.RelalgPackage#getEdgeVariable_AttributeVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeVariable> getAttributeVariables();

} // EdgeVariable
