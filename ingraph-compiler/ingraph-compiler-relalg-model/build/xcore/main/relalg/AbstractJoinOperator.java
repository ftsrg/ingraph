/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Join Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AbstractJoinOperator#getCommonVariables <em>Common Variables</em>}</li>
 *   <li>{@link relalg.AbstractJoinOperator#getLeftMask <em>Left Mask</em>}</li>
 *   <li>{@link relalg.AbstractJoinOperator#getRightMask <em>Right Mask</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAbstractJoinOperator()
 * @model abstract="true"
 * @generated
 */
public interface AbstractJoinOperator extends BinaryOperator {
	/**
	 * Returns the value of the '<em><b>Common Variables</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Common Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Common Variables</em>' reference list.
	 * @see relalg.RelalgPackage#getAbstractJoinOperator_CommonVariables()
	 * @model
	 * @generated
	 */
	EList<Variable> getCommonVariables();

	/**
	 * Returns the value of the '<em><b>Left Mask</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Mask</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Mask</em>' attribute list.
	 * @see relalg.RelalgPackage#getAbstractJoinOperator_LeftMask()
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getLeftMask();

	/**
	 * Returns the value of the '<em><b>Right Mask</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Mask</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Mask</em>' attribute list.
	 * @see relalg.RelalgPackage#getAbstractJoinOperator_RightMask()
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getRightMask();

} // AbstractJoinOperator
