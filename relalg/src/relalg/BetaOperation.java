/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beta Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BetaOperation#getLeftParent <em>Left Parent</em>}</li>
 *   <li>{@link relalg.BetaOperation#getRightParent <em>Right Parent</em>}</li>
 *   <li>{@link relalg.BetaOperation#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBetaOperation()
 * @model abstract="true"
 * @generated
 */
public interface BetaOperation extends AlgebraExpression {
	/**
	 * Returns the value of the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Parent</em>' reference.
	 * @see #setLeftParent(AlgebraExpression)
	 * @see relalg.RelalgPackage#getBetaOperation_LeftParent()
	 * @model required="true"
	 * @generated
	 */
	AlgebraExpression getLeftParent();

	/**
	 * Sets the value of the '{@link relalg.BetaOperation#getLeftParent <em>Left Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Parent</em>' reference.
	 * @see #getLeftParent()
	 * @generated
	 */
	void setLeftParent(AlgebraExpression value);

	/**
	 * Returns the value of the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Parent</em>' reference.
	 * @see #setRightParent(AlgebraExpression)
	 * @see relalg.RelalgPackage#getBetaOperation_RightParent()
	 * @model required="true"
	 * @generated
	 */
	AlgebraExpression getRightParent();

	/**
	 * Sets the value of the '{@link relalg.BetaOperation#getRightParent <em>Right Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Parent</em>' reference.
	 * @see #getRightParent()
	 * @generated
	 */
	void setRightParent(AlgebraExpression value);

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.JoinBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bindings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see relalg.RelalgPackage#getBetaOperation_Bindings()
	 * @model containment="true"
	 * @generated
	 */
	EList<JoinBinding> getBindings();

} // BetaOperation
