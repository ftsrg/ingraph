/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Case#getWhen <em>When</em>}</li>
 *   <li>{@link relalg.Case#getThen <em>Then</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getCase()
 * @model
 * @generated
 */
public interface Case extends EObject {
	/**
	 * Returns the value of the '<em><b>When</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>When</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' reference.
	 * @see #setWhen(Expression)
	 * @see relalg.RelalgPackage#getCase_When()
	 * @model
	 * @generated
	 */
	Expression getWhen();

	/**
	 * Sets the value of the '{@link relalg.Case#getWhen <em>When</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' reference.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(Expression value);

	/**
	 * Returns the value of the '<em><b>Then</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then</em>' reference.
	 * @see #setThen(Expression)
	 * @see relalg.RelalgPackage#getCase_Then()
	 * @model
	 * @generated
	 */
	Expression getThen();

	/**
	 * Sets the value of the '{@link relalg.Case#getThen <em>Then</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then</em>' reference.
	 * @see #getThen()
	 * @generated
	 */
	void setThen(Expression value);

} // Case
