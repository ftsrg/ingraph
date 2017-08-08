/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.NamedElement#getName <em>Name</em>}</li>
 *   <li>{@link relalg.NamedElement#getNamedElementContainer <em>Named Element Container</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getNamedElement()
 * @model abstract="true"
 * @generated
 */
public interface NamedElement extends RelalgModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see relalg.RelalgPackage#getNamedElement_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link relalg.NamedElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Named Element Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link relalg.RelalgContainer#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Named Element Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Named Element Container</em>' container reference.
	 * @see #setNamedElementContainer(RelalgContainer)
	 * @see relalg.RelalgPackage#getNamedElement_NamedElementContainer()
	 * @see relalg.RelalgContainer#getElements
	 * @model opposite="elements" transient="false"
	 * @generated
	 */
	RelalgContainer getNamedElementContainer();

	/**
	 * Sets the value of the '{@link relalg.NamedElement#getNamedElementContainer <em>Named Element Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Named Element Container</em>' container reference.
	 * @see #getNamedElementContainer()
	 * @generated
	 */
	void setNamedElementContainer(RelalgContainer value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getName();'"
	 * @generated
	 */
	String fullName();

} // NamedElement
