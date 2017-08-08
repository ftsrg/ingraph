/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.UnionOperator#isBag <em>Bag</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getUnionOperator()
 * @model
 * @generated
 */
public interface UnionOperator extends BinaryOperator, CommutativeBinaryOperator, AssociativeBinaryOperator {
	/**
	 * Returns the value of the '<em><b>Bag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bag</em>' attribute.
	 * @see #setBag(boolean)
	 * @see relalg.RelalgPackage#getUnionOperator_Bag()
	 * @model unique="false"
	 * @generated
	 */
	boolean isBag();

	/**
	 * Sets the value of the '{@link relalg.UnionOperator#isBag <em>Bag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bag</em>' attribute.
	 * @see #isBag()
	 * @generated
	 */
	void setBag(boolean value);

} // UnionOperator
