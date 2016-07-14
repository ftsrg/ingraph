/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Algebra Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AlgebraExpression#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAlgebraExpression()
 * @model
 * @generated
 */
public interface AlgebraExpression extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.AlgebraNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see relalg.RelalgPackage#getAlgebraExpression_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AlgebraNode> getNodes();

} // AlgebraExpression
