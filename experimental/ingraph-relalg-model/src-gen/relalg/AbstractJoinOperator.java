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
 *   <li>{@link relalg.AbstractJoinOperator#getMutualVariables <em>Mutual Variables</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAbstractJoinOperator()
 * @model abstract="true"
 * @generated
 */
public interface AbstractJoinOperator extends BetaOperator {
	/**
	 * Returns the value of the '<em><b>Mutual Variables</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutual Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutual Variables</em>' reference list.
	 * @see relalg.RelalgPackage#getAbstractJoinOperator_MutualVariables()
	 * @model
	 * @generated
	 */
	EList<Variable> getMutualVariables();

} // AbstractJoinOperator
