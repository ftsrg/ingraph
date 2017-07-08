/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CUD Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents the create/update/delete operator.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.CUDOperator#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getCUDOperator()
 * @model abstract="true"
 * @generated
 */
public interface CUDOperator extends UnaryOperator {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link relalg.ExpressionVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see relalg.RelalgPackage#getCUDOperator_Elements()
	 * @model
	 * @generated
	 */
	EList<ExpressionVariable> getElements();

} // CUDOperator
