/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Operator#getSchema <em>Schema</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getOperator()
 * @model abstract="true"
 * @generated
 */
public interface Operator extends EObject {

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference list.
	 * @see relalg.RelalgPackage#getOperator_Schema()
	 * @model
	 * @generated
	 */
	EList<Variable> getSchema();
} // Operator
