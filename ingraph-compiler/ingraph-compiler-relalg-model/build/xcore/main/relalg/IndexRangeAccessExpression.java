/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Range Access Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 0-based, left inclusive, right excluded list range indexing.
 * list[0..2] returns a list of list[0] and list[1] if both exist.
 * If some of the list entries does not exist, it will be omitted,
 * and it list has 2 elements, list[10..20] should evaluate to the empty list.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.IndexRangeAccessExpression#getLower <em>Lower</em>}</li>
 *   <li>{@link relalg.IndexRangeAccessExpression#getUpper <em>Upper</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getIndexRangeAccessExpression()
 * @model
 * @generated
 */
public interface IndexRangeAccessExpression extends IndexAccessExpression {
	/**
	 * Returns the value of the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower</em>' attribute.
	 * @see #setLower(int)
	 * @see relalg.RelalgPackage#getIndexRangeAccessExpression_Lower()
	 * @model unique="false"
	 * @generated
	 */
	int getLower();

	/**
	 * Sets the value of the '{@link relalg.IndexRangeAccessExpression#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(int value);

	/**
	 * Returns the value of the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper</em>' attribute.
	 * @see #setUpper(int)
	 * @see relalg.RelalgPackage#getIndexRangeAccessExpression_Upper()
	 * @model unique="false"
	 * @generated
	 */
	int getUpper();

	/**
	 * Sets the value of the '{@link relalg.IndexRangeAccessExpression#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(int value);

} // IndexRangeAccessExpression
