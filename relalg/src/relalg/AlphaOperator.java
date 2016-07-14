/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alpha Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AlphaOperator#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAlphaOperator()
 * @model abstract="true"
 * @generated
 */
public interface AlphaOperator extends EObject {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(AlgebraNode)
	 * @see relalg.RelalgPackage#getAlphaOperator_Parent()
	 * @model
	 * @generated
	 */
	AlgebraNode getParent();

	/**
	 * Sets the value of the '{@link relalg.AlphaOperator#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(AlgebraNode value);

} // AlphaOperator
