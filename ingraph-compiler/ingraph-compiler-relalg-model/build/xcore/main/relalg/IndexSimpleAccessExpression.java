/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Simple Access Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 0-based list indexing, e.g. list[0] refers to the first element,
 * list[1] refers to the second, or null if no such element exists in the list.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.IndexSimpleAccessExpression#getIdx <em>Idx</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getIndexSimpleAccessExpression()
 * @model
 * @generated
 */
public interface IndexSimpleAccessExpression extends IndexAccessExpression {
	/**
	 * Returns the value of the '<em><b>Idx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idx</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idx</em>' attribute.
	 * @see #setIdx(int)
	 * @see relalg.RelalgPackage#getIndexSimpleAccessExpression_Idx()
	 * @model unique="false"
	 * @generated
	 */
	int getIdx();

	/**
	 * Sets the value of the '{@link relalg.IndexSimpleAccessExpression#getIdx <em>Idx</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Idx</em>' attribute.
	 * @see #getIdx()
	 * @generated
	 */
	void setIdx(int value);

} // IndexSimpleAccessExpression
