/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 *  expression
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Expression#getExpressionContainer <em>Expression Container</em>}</li>
 *   <li>{@link relalg.Expression#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getExpression()
 * @model abstract="true"
 * @generated
 */
public interface Expression extends RelalgModelElement {
	/**
	 * Returns the value of the '<em><b>Expression Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link relalg.RelalgContainer#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Container</em>' container reference.
	 * @see #setExpressionContainer(RelalgContainer)
	 * @see relalg.RelalgPackage#getExpression_ExpressionContainer()
	 * @see relalg.RelalgContainer#getExpressions
	 * @model opposite="expressions" transient="false"
	 * @generated
	 */
	RelalgContainer getExpressionContainer();

	/**
	 * Sets the value of the '{@link relalg.Expression#getExpressionContainer <em>Expression Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression Container</em>' container reference.
	 * @see #getExpressionContainer()
	 * @generated
	 */
	void setExpressionContainer(RelalgContainer value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see relalg.RelalgPackage#getExpression_Text()
	 * @model unique="false"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link relalg.Expression#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

} // Expression
