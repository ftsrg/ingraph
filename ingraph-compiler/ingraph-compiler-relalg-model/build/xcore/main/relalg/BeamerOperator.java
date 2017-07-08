/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beamer Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A projection-like operator that is only capable of performing a projection operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.BeamerOperator#getElements <em>Elements</em>}</li>
 *   <li>{@link relalg.BeamerOperator#getInternalElements <em>Internal Elements</em>}</li>
 *   <li>{@link relalg.BeamerOperator#getTupleIndices <em>Tuple Indices</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getBeamerOperator()
 * @model abstract="true"
 * @generated
 */
public interface BeamerOperator extends UnaryOperator {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link relalg.ExpressionVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see relalg.RelalgPackage#getBeamerOperator_Elements()
	 * @model
	 * @generated
	 */
	EList<ExpressionVariable> getElements();

	/**
	 * Returns the value of the '<em><b>Internal Elements</b></em>' reference list.
	 * The list contents are of type {@link relalg.ExpressionVariable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Elements</em>' reference list.
	 * @see relalg.RelalgPackage#getBeamerOperator_InternalElements()
	 * @model
	 * @generated
	 */
	EList<ExpressionVariable> getInternalElements();

	/**
	 * Returns the value of the '<em><b>Tuple Indices</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple Indices</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuple Indices</em>' attribute list.
	 * @see relalg.RelalgPackage#getBeamerOperator_TupleIndices()
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getTupleIndices();

} // BeamerOperator
