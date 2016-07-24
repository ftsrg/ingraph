/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Join Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.JoinBinding#getLeftAttribute <em>Left Attribute</em>}</li>
 *   <li>{@link relalg.JoinBinding#getRightAttribute <em>Right Attribute</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getJoinBinding()
 * @model
 * @generated
 */
public interface JoinBinding extends EObject {
	/**
	 * Returns the value of the '<em><b>Left Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Attribute</em>' reference.
	 * @see #setLeftAttribute(Attribute)
	 * @see relalg.RelalgPackage#getJoinBinding_LeftAttribute()
	 * @model required="true"
	 * @generated
	 */
	Attribute getLeftAttribute();

	/**
	 * Sets the value of the '{@link relalg.JoinBinding#getLeftAttribute <em>Left Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Attribute</em>' reference.
	 * @see #getLeftAttribute()
	 * @generated
	 */
	void setLeftAttribute(Attribute value);

	/**
	 * Returns the value of the '<em><b>Right Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Attribute</em>' reference.
	 * @see #setRightAttribute(Attribute)
	 * @see relalg.RelalgPackage#getJoinBinding_RightAttribute()
	 * @model required="true"
	 * @generated
	 */
	Attribute getRightAttribute();

	/**
	 * Sets the value of the '{@link relalg.JoinBinding#getRightAttribute <em>Right Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Attribute</em>' reference.
	 * @see #getRightAttribute()
	 * @generated
	 */
	void setRightAttribute(Attribute value);

} // JoinBinding
