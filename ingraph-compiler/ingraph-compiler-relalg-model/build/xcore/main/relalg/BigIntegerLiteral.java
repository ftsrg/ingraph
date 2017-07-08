/**
 */
package relalg;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Big Integer Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BigIntegerLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBigIntegerLiteral()
 * @model
 * @generated
 */
public interface BigIntegerLiteral extends NumberLiteral {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(BigInteger)
	 * @see relalg.RelalgPackage#getBigIntegerLiteral_Value()
	 * @model unique="false" dataType="relalg.Bigint"
	 * @generated
	 */
	BigInteger getValue();

	/**
	 * Sets the value of the '{@link relalg.BigIntegerLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(BigInteger value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.math.BigInteger%> _value = this.getValue();\nreturn _value.toString();'"
	 * @generated
	 */
	String fullName();

} // BigIntegerLiteral
