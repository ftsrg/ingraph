/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ListExpression represents a list of expressions (of type Expression).
 *  * The empty list is an instance of the EmptyListExpression.
 *  * The non-empty list is composed of a head (exactly one element, always non-null Java value)
 * and a tail, which, again is a list (of type ListExpression).
 *  * If we denote the empty list by [] (which, again, is of the type EmptyListExpression),
 * and the non-empty list by [ head | tail ],
 * then the list <1, 2, 3> is represented as:
 * [ 1 | [ 2 | [ 3 | [] ] ] ]
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ListExpression#getHead <em>Head</em>}</li>
 *   <li>{@link relalg.ListExpression#getTail <em>Tail</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getListExpression()
 * @model
 * @generated
 */
public interface ListExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Head</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Head</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Head</em>' reference.
	 * @see #setHead(Expression)
	 * @see relalg.RelalgPackage#getListExpression_Head()
	 * @model
	 * @generated
	 */
	Expression getHead();

	/**
	 * Sets the value of the '{@link relalg.ListExpression#getHead <em>Head</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Head</em>' reference.
	 * @see #getHead()
	 * @generated
	 */
	void setHead(Expression value);

	/**
	 * Returns the value of the '<em><b>Tail</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tail</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tail</em>' reference.
	 * @see #setTail(ListExpression)
	 * @see relalg.RelalgPackage#getListExpression_Tail()
	 * @model
	 * @generated
	 */
	ListExpression getTail();

	/**
	 * Sets the value of the '{@link relalg.ListExpression#getTail <em>Tail</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tail</em>' reference.
	 * @see #getTail()
	 * @generated
	 */
	void setTail(ListExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.Expression%> _head = this.getHead();\n<%java.lang.String%> _fullName = null;\nif (_head!=null)\n{\n\t_fullName=_head.fullName();\n}\n<%java.lang.String%> _plus = (_fullName + \", \");\n<%relalg.ListExpression%> _tail = this.getTail();\n<%java.lang.String%> _fullName_1 = null;\nif (_tail!=null)\n{\n\t_fullName_1=_tail.fullName();\n}\nreturn (_plus + _fullName_1);'"
	 * @generated
	 */
	String fullName();

} // ListExpression
