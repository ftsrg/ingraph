/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 *  Binary operators
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BinaryOperator#getLeftInput <em>Left Input</em>}</li>
 *   <li>{@link relalg.BinaryOperator#getRightInput <em>Right Input</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBinaryOperator()
 * @model abstract="true"
 * @generated
 */
public interface BinaryOperator extends Operator {
	/**
	 * Returns the value of the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Input</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Input</em>' containment reference.
	 * @see #setLeftInput(Operator)
	 * @see relalg.RelalgPackage#getBinaryOperator_LeftInput()
	 * @model containment="true"
	 * @generated
	 */
	Operator getLeftInput();

	/**
	 * Sets the value of the '{@link relalg.BinaryOperator#getLeftInput <em>Left Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Input</em>' containment reference.
	 * @see #getLeftInput()
	 * @generated
	 */
	void setLeftInput(Operator value);

	/**
	 * Returns the value of the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Input</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Input</em>' containment reference.
	 * @see #setRightInput(Operator)
	 * @see relalg.RelalgPackage#getBinaryOperator_RightInput()
	 * @model containment="true"
	 * @generated
	 */
	Operator getRightInput();

	/**
	 * Sets the value of the '{@link relalg.BinaryOperator#getRightInput <em>Right Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Input</em>' containment reference.
	 * @see #getRightInput()
	 * @generated
	 */
	void setRightInput(Operator value);

} // BinaryOperator
