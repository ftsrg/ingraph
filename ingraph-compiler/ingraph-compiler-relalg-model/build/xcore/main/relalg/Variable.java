/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Variable#isDontCare <em>Dont Care</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getVariable()
 * @model abstract="true"
 * @generated
 */
public interface Variable extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Dont Care</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dont Care</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dont Care</em>' attribute.
	 * @see #setDontCare(boolean)
	 * @see relalg.RelalgPackage#getVariable_DontCare()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isDontCare();

	/**
	 * Sets the value of the '{@link relalg.Variable#isDontCare <em>Dont Care</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dont Care</em>' attribute.
	 * @see #isDontCare()
	 * @generated
	 */
	void setDontCare(boolean value);

} // Variable
