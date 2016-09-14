/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beta Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BetaOperator#getLeftInput <em>Left Input</em>}</li>
 *   <li>{@link relalg.BetaOperator#getRightInput <em>Right Input</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBetaOperator()
 * @model abstract="true"
 * @generated
 */
public interface BetaOperator extends AlgebraExpression {
	/**
	 * Returns the value of the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Input</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Input</em>' containment reference.
	 * @see #setLeftInput(AlgebraExpression)
	 * @see relalg.RelalgPackage#getBetaOperator_LeftInput()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AlgebraExpression getLeftInput();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getLeftInput <em>Left Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Input</em>' containment reference.
	 * @see #getLeftInput()
	 * @generated
	 */
	void setLeftInput(AlgebraExpression value);

	/**
	 * Returns the value of the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Input</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Input</em>' containment reference.
	 * @see #setRightInput(AlgebraExpression)
	 * @see relalg.RelalgPackage#getBetaOperator_RightInput()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AlgebraExpression getRightInput();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getRightInput <em>Right Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Input</em>' containment reference.
	 * @see #getRightInput()
	 * @generated
	 */
	void setRightInput(AlgebraExpression value);

} // BetaOperator
