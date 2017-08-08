/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Describes an attribute lookup on a variable.
 * The base variable might be of type ElementVariable or ExpressionVariable,
 * and if so, element xor expVar is filled, respectively.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AttributeVariable#getElement <em>Element</em>}</li>
 *   <li>{@link relalg.AttributeVariable#getExpVar <em>Exp Var</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAttributeVariable()
 * @model
 * @generated
 */
public interface AttributeVariable extends GraphObjectVariable {
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
	 * @model opposite="attributes"
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

	/**
	 * Returns the value of the '<em><b>Exp Var</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exp Var</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exp Var</em>' reference.
	 * @see #setExpVar(ExpressionVariable)
	 * @see relalg.RelalgPackage#getAttributeVariable_ExpVar()
	 * @model
	 * @generated
	 */
	ExpressionVariable getExpVar();

	/**
	 * Sets the value of the '{@link relalg.AttributeVariable#getExpVar <em>Exp Var</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exp Var</em>' reference.
	 * @see #getExpVar()
	 * @generated
	 */
	void setExpVar(ExpressionVariable value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (((this.getElement() != null) && (this.getExpVar() == null)))\n{\n\treturn this.getElement();\n}\nelse\n{\n\tif (((this.getElement() == null) && (this.getExpVar() != null)))\n\t{\n\t\treturn this.getExpVar();\n\t}\n\telse\n\t{\n\t\tif (((this.getElement() == null) && (this.getExpVar() == null)))\n\t\t{\n\t\t\tthrow new <%java.lang.IllegalStateException%>(\"AttributeVariable must have non-null value for either element or expVar, but both are null.\");\n\t\t}\n\t\telse\n\t\t{\n\t\t\tthrow new <%java.lang.IllegalStateException%>(\"AttributeVariable must have non-null value for either element or expVar, but not for both.\");\n\t\t}\n\t}\n}'"
	 * @generated
	 */
	Variable getBaseVariable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.Variable%> _baseVariable = this.getBaseVariable();\n<%java.lang.String%> _name = _baseVariable.getName();\n<%java.lang.String%> _plus = (_name + \".\");\n<%java.lang.String%> _name_1 = this.getName();\nreturn (_plus + _name_1);'"
	 * @generated
	 */
	String fullName();

} // AttributeVariable
