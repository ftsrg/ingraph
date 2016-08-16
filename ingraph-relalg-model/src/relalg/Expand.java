/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Expand#getDirection <em>Direction</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getExpand()
 * @model
 * @generated
 */
public interface Expand extends AlphaOperation {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The default value is <code>"IN"</code>.
	 * The literals are from the enumeration {@link relalg.Direction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see relalg.Direction
	 * @see #setDirection(Direction)
	 * @see relalg.RelalgPackage#getExpand_Direction()
	 * @model default="IN"
	 * @generated
	 */
	Direction getDirection();

	/**
	 * Sets the value of the '{@link relalg.Expand#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see relalg.Direction
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(Direction value);

} // Expand
