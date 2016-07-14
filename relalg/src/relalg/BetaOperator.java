/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beta Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BetaOperator#getLeftParent <em>Left Parent</em>}</li>
 *   <li>{@link relalg.BetaOperator#getRightParent <em>Right Parent</em>}</li>
 *   <li>{@link relalg.BetaOperator#getLeftMask <em>Left Mask</em>}</li>
 *   <li>{@link relalg.BetaOperator#getRightMask <em>Right Mask</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBetaOperator()
 * @model abstract="true"
 * @generated
 */
public interface BetaOperator extends EObject {
	/**
	 * Returns the value of the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Parent</em>' reference.
	 * @see #setLeftParent(AlgebraNode)
	 * @see relalg.RelalgPackage#getBetaOperator_LeftParent()
	 * @model
	 * @generated
	 */
	AlgebraNode getLeftParent();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getLeftParent <em>Left Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Parent</em>' reference.
	 * @see #getLeftParent()
	 * @generated
	 */
	void setLeftParent(AlgebraNode value);

	/**
	 * Returns the value of the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Parent</em>' reference.
	 * @see #setRightParent(AlgebraNode)
	 * @see relalg.RelalgPackage#getBetaOperator_RightParent()
	 * @model
	 * @generated
	 */
	AlgebraNode getRightParent();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getRightParent <em>Right Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Parent</em>' reference.
	 * @see #getRightParent()
	 * @generated
	 */
	void setRightParent(AlgebraNode value);

	/**
	 * Returns the value of the '<em><b>Left Mask</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Mask</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Mask</em>' containment reference.
	 * @see #setLeftMask(AttributeSet)
	 * @see relalg.RelalgPackage#getBetaOperator_LeftMask()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AttributeSet getLeftMask();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getLeftMask <em>Left Mask</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Mask</em>' containment reference.
	 * @see #getLeftMask()
	 * @generated
	 */
	void setLeftMask(AttributeSet value);

	/**
	 * Returns the value of the '<em><b>Right Mask</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Mask</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Mask</em>' containment reference.
	 * @see #setRightMask(AttributeSet)
	 * @see relalg.RelalgPackage#getBetaOperator_RightMask()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AttributeSet getRightMask();

	/**
	 * Sets the value of the '{@link relalg.BetaOperator#getRightMask <em>Right Mask</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Mask</em>' containment reference.
	 * @see #getRightMask()
	 * @generated
	 */
	void setRightMask(AttributeSet value);

} // BetaOperator
