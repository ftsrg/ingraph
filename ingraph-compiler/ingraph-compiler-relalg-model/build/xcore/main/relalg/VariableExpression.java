/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * VariableExpression is a generic container for a variable to make it easy
 * to include into expressions.
 *  * Various sub-types like VariableComparableExpression, VariableArithmeticExpression
 * exists to allow type-safe building of expressions. However keep in mind, that
 * it is not guaranteed that the variable will hold a value that is e.g.
 * comparable in case of a VariableComparableExpression.
 * Type check is always needed in query execution time!
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.VariableExpression#getVariable <em>Variable</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getVariableExpression()
 * @model
 * @generated
 */
public interface VariableExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference.
	 * @see #setVariable(Variable)
	 * @see relalg.RelalgPackage#getVariableExpression_Variable()
	 * @model
	 * @generated
	 */
	Variable getVariable();

	/**
	 * Sets the value of the '{@link relalg.VariableExpression#getVariable <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(Variable value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.Variable%> _variable = this.getVariable();\nreturn _variable.fullName();'"
	 * @generated
	 */
	String fullName();

} // VariableExpression
