/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Projection Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ProjectionOperator#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getProjectionOperator()
 * @model
 * @generated
 */
public interface ProjectionOperator extends AlphaOperator {
	/**
	 * Returns the value of the '<em><b>Variables</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' reference list.
	 * @see relalg.RelalgPackage#getProjectionOperator_Variables()
	 * @model
	 * @generated
	 */
	EList<Variable> getVariables();

} // ProjectionOperator
