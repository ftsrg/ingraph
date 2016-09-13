/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

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
 *   <li>{@link relalg.NamedElement#getContainer <em>Container</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getNamedElement()
 * @model abstract="true"
 * @generated
 */
public interface NamedElement extends EObject {
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
	 * @model
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
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link relalg.Container#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(Container)
	 * @see relalg.RelalgPackage#getNamedElement_Container()
	 * @see relalg.Container#getElements
	 * @model opposite="elements" required="true" transient="false"
	 * @generated
	 */
	Container getContainer();

	/**
	 * Sets the value of the '{@link relalg.NamedElement#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(Container value);

} // NamedElement
