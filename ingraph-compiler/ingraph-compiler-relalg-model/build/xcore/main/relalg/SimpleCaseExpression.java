/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Case Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.SimpleCaseExpression#getTest <em>Test</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getSimpleCaseExpression()
 * @model
 * @generated
 */
public interface SimpleCaseExpression extends CaseExpression {
	/**
	 * Returns the value of the '<em><b>Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test</em>' reference.
	 * @see #setTest(Expression)
	 * @see relalg.RelalgPackage#getSimpleCaseExpression_Test()
	 * @model
	 * @generated
	 */
	Expression getTest();

	/**
	 * Sets the value of the '{@link relalg.SimpleCaseExpression#getTest <em>Test</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test</em>' reference.
	 * @see #getTest()
	 * @generated
	 */
	void setTest(Expression value);

} // SimpleCaseExpression
