/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>All Different Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AllDifferentOperator#getEdgeVariables <em>Edge Variables</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAllDifferentOperator()
 * @model
 * @generated
 */
public interface AllDifferentOperator extends AlphaOperator {

	/**
	 * Returns the value of the '<em><b>Edge Variables</b></em>' reference list.
	 * The list contents are of type {@link relalg.EdgeVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Variables</em>' reference list.
	 * @see relalg.RelalgPackage#getAllDifferentOperator_EdgeVariables()
	 * @model
	 * @generated
	 */
	EList<EdgeVariable> getEdgeVariables();
} // AllDifferentOperator
