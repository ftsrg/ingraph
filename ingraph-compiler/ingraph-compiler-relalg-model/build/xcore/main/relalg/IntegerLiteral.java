/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.IntegerLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getIntegerLiteral()
 * @model
 * @generated
 */
public interface IntegerLiteral extends NumberLiteral {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(long)
	 * @see relalg.RelalgPackage#getIntegerLiteral_Value()
	 * @model unique="false"
	 * @generated
	 */
	long getValue();

	/**
	 * Sets the value of the '{@link relalg.IntegerLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(long value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='long _value = this.getValue();\nreturn <%java.lang.Long%>.valueOf(_value).toString();'"
	 * @generated
	 */
	String fullName();

} // IntegerLiteral
