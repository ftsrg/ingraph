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
 *   <li>{@link relalg.EdgeVariable#getAttributeVariable <em>Attribute Variable</em>}</li>
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
	 * Returns the value of the '<em><b>Attribute Variable</b></em>' reference list.
	 * The list contents are of type {@link relalg.AttributeVariable}.
	 * It is bidirectional and its opposite is '{@link relalg.AttributeVariable#getEdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Variable</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Variable</em>' reference list.
	 * @see relalg.RelalgPackage#getEdgeVariable_AttributeVariable()
	 * @see relalg.AttributeVariable#getEdgeVariable
	 * @model opposite="edgeVariable"
	 * @generated
	 */
	EList<AttributeVariable> getAttributeVariable();

} // EdgeVariable
