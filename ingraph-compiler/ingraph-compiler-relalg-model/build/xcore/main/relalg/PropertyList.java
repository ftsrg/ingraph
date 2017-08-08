/**
 */
package relalg;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This will hold the map-like construct that is used
 * to represent vertex or edge constraints as well as
 * "values" clause for DML operations.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.PropertyList#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getPropertyList()
 * @model
 * @generated
 */
public interface PropertyList extends Expression {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link relalg.Expression},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' map.
	 * @see relalg.RelalgPackage#getPropertyList_Entries()
	 * @model mapType="relalg.PropertyListEntry<org.eclipse.emf.ecore.EString, relalg.Expression>"
	 * @generated
	 */
	EMap<String, Expression> getEntries();

} // PropertyList
