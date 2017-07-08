/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a named expression like returned elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ExpressionVariable#isHasInferredName <em>Has Inferred Name</em>}</li>
 *   <li>{@link relalg.ExpressionVariable#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getExpressionVariable()
 * @model
 * @generated
 */
public interface ExpressionVariable extends Variable {
	/**
	 * Returns the value of the '<em><b>Has Inferred Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Inferred Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Inferred Name</em>' attribute.
	 * @see #setHasInferredName(boolean)
	 * @see relalg.RelalgPackage#getExpressionVariable_HasInferredName()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasInferredName();

	/**
	 * Sets the value of the '{@link relalg.ExpressionVariable#isHasInferredName <em>Has Inferred Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Inferred Name</em>' attribute.
	 * @see #isHasInferredName()
	 * @generated
	 */
	void setHasInferredName(boolean value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' reference.
	 * @see #setExpression(Expression)
	 * @see relalg.RelalgPackage#getExpressionVariable_Expression()
	 * @model
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link relalg.ExpressionVariable#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _xifexpression = null;\nboolean _isHasInferredName = this.isHasInferredName();\nif (_isHasInferredName)\n{\n\t<%java.lang.String%> _xifexpression_1 = null;\n\t<%relalg.Expression%> _expression = this.getExpression();\n\tif ((_expression instanceof <%relalg.VariableExpression%>))\n\t{\n\t\t<%relalg.Expression%> _expression_1 = this.getExpression();\n\t\t<%relalg.Variable%> _variable = ((<%relalg.VariableExpression%>) _expression_1).getVariable();\n\t\t_xifexpression_1 = _variable.getName();\n\t}\n\telse\n\t{\n\t\t<%relalg.Expression%> _expression_2 = this.getExpression();\n\t\t_xifexpression_1 = _expression_2.fullName();\n\t}\n\t_xifexpression = _xifexpression_1;\n}\nelse\n{\n\t_xifexpression = this.getName();\n}\nreturn _xifexpression;'"
	 * @generated
	 */
	String fullName();

} // ExpressionVariable
