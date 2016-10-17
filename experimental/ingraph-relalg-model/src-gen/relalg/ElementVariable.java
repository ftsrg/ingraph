/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ElementVariable#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getElementVariable()
 * @model abstract="true"
 * @generated
 */
public interface ElementVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' reference list.
	 * The list contents are of type {@link relalg.AttributeVariable}.
	 * It is bidirectional and its opposite is '{@link relalg.AttributeVariable#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' reference list.
	 * @see relalg.RelalgPackage#getElementVariable_Attributes()
	 * @see relalg.AttributeVariable#getElement
	 * @model opposite="element"
	 * @generated
	 */
	EList<AttributeVariable> getAttributes();

} // ElementVariable
