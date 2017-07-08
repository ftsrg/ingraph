/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.DeleteOperator#isDetach <em>Detach</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getDeleteOperator()
 * @model
 * @generated
 */
public interface DeleteOperator extends CUDOperator {
	/**
	 * Returns the value of the '<em><b>Detach</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detach</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detach</em>' attribute.
	 * @see #setDetach(boolean)
	 * @see relalg.RelalgPackage#getDeleteOperator_Detach()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDetach();

	/**
	 * Sets the value of the '{@link relalg.DeleteOperator#isDetach <em>Detach</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Detach</em>' attribute.
	 * @see #isDetach()
	 * @generated
	 */
	void setDetach(boolean value);

} // DeleteOperator
