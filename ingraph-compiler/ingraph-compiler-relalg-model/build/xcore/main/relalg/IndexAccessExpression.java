/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Access Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Common base to express the subscript operator,
 * i.e. list[0] or list[0..2] (where upper limit is excluded).
 *  * Indexing is 0-based.
 * List should have a runtime type of a list.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.IndexAccessExpression#getList <em>List</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getIndexAccessExpression()
 * @model abstract="true"
 * @generated
 */
public interface IndexAccessExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List</em>' reference.
	 * @see #setList(Expression)
	 * @see relalg.RelalgPackage#getIndexAccessExpression_List()
	 * @model
	 * @generated
	 */
	Expression getList();

	/**
	 * Sets the value of the '{@link relalg.IndexAccessExpression#getList <em>List</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List</em>' reference.
	 * @see #getList()
	 * @generated
	 */
	void setList(Expression value);

} // IndexAccessExpression
