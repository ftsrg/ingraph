/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rete Network</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.ReteNetwork#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getReteNetwork()
 * @model
 * @generated
 */
public interface ReteNetwork extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.ReteNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see relalg.RelalgPackage#getReteNetwork_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ReteNode> getNodes();

} // ReteNetwork
