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
 *   <li>{@link relalg.AttributeVariable#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAttributeVariable()
 * @model
 * @generated
 */
public interface AttributeVariable extends Variable {

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link relalg.ElementVariable#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(ElementVariable)
	 * @see relalg.RelalgPackage#getAttributeVariable_Element()
	 * @see relalg.ElementVariable#getAttributes
	 * @model opposite="attributes" required="true"
	 * @generated
	 */
	ElementVariable getElement();

	/**
	 * Sets the value of the '{@link relalg.AttributeVariable#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(ElementVariable value);
} // AttributeVariable
