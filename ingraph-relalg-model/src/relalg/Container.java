/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Container#getRootExpression <em>Root Expression</em>}</li>
 *   <li>{@link relalg.Container#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>Root Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Expression</em>' containment reference.
	 * @see #setRootExpression(AlgebraExpression)
	 * @see relalg.RelalgPackage#getContainer_RootExpression()
	 * @model containment="true"
	 * @generated
	 */
	AlgebraExpression getRootExpression();

	/**
	 * Sets the value of the '{@link relalg.Container#getRootExpression <em>Root Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Expression</em>' containment reference.
	 * @see #getRootExpression()
	 * @generated
	 */
	void setRootExpression(AlgebraExpression value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.NamedElement}.
	 * It is bidirectional and its opposite is '{@link relalg.NamedElement#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see relalg.RelalgPackage#getContainer_Elements()
	 * @see relalg.NamedElement#getContainer
	 * @model opposite="container" containment="true"
	 * @generated
	 */
	EList<NamedElement> getElements();

} // Container
