/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.PathOperator#getSemantics <em>Semantics</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getPathOperator()
 * @model
 * @generated
 */
public interface PathOperator extends ExpandOperator {
	/**
	 * Returns the value of the '<em><b>Semantics</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.PathSemantics}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semantics</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semantics</em>' attribute.
	 * @see relalg.PathSemantics
	 * @see #setSemantics(PathSemantics)
	 * @see relalg.RelalgPackage#getPathOperator_Semantics()
	 * @model unique="false"
	 * @generated
	 */
	PathSemantics getSemantics();

	/**
	 * Sets the value of the '{@link relalg.PathOperator#getSemantics <em>Semantics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Semantics</em>' attribute.
	 * @see relalg.PathSemantics
	 * @see #getSemantics()
	 * @generated
	 */
	void setSemantics(PathSemantics value);

} // PathOperator
