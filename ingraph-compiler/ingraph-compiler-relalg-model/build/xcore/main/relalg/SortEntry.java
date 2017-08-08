/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sort Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.SortEntry#getExpression <em>Expression</em>}</li>
 *   <li>{@link relalg.SortEntry#getDirection <em>Direction</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getSortEntry()
 * @model
 * @generated
 */
public interface SortEntry extends EObject {
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
	 * @see relalg.RelalgPackage#getSortEntry_Expression()
	 * @model
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link relalg.SortEntry#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.OrderDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see relalg.OrderDirection
	 * @see #setDirection(OrderDirection)
	 * @see relalg.RelalgPackage#getSortEntry_Direction()
	 * @model unique="false"
	 * @generated
	 */
	OrderDirection getDirection();

	/**
	 * Sets the value of the '{@link relalg.SortEntry#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see relalg.OrderDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(OrderDirection value);

} // SortEntry
