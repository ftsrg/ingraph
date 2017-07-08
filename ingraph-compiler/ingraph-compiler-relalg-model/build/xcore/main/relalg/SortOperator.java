/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sort Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.SortOperator#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getSortOperator()
 * @model
 * @generated
 */
public interface SortOperator extends UnaryOperator {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.SortEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see relalg.RelalgPackage#getSortOperator_Entries()
	 * @model containment="true"
	 * @generated
	 */
	EList<SortEntry> getEntries();

} // SortOperator
