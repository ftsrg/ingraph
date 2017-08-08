/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.Operator#getExternalSchema <em>External Schema</em>}</li>
 *   <li>{@link relalg.Operator#getExtraVariables <em>Extra Variables</em>}</li>
 *   <li>{@link relalg.Operator#getInternalSchema <em>Internal Schema</em>}</li>
 *   <li>{@link relalg.Operator#getCardinality <em>Cardinality</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getOperator()
 * @model abstract="true"
 * @generated
 */
public interface Operator extends EObject {
	/**
	 * Returns the value of the '<em><b>External Schema</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Schema</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Schema</em>' reference list.
	 * @see relalg.RelalgPackage#getOperator_ExternalSchema()
	 * @model
	 * @generated
	 */
	EList<Variable> getExternalSchema();

	/**
	 * Returns the value of the '<em><b>Extra Variables</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extra Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extra Variables</em>' reference list.
	 * @see relalg.RelalgPackage#getOperator_ExtraVariables()
	 * @model
	 * @generated
	 */
	EList<Variable> getExtraVariables();

	/**
	 * Returns the value of the '<em><b>Internal Schema</b></em>' reference list.
	 * The list contents are of type {@link relalg.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Schema</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Schema</em>' reference list.
	 * @see relalg.RelalgPackage#getOperator_InternalSchema()
	 * @model
	 * @generated
	 */
	EList<Variable> getInternalSchema();

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardinality</em>' containment reference.
	 * @see #setCardinality(Cardinality)
	 * @see relalg.RelalgPackage#getOperator_Cardinality()
	 * @model containment="true"
	 * @generated
	 */
	Cardinality getCardinality();

	/**
	 * Sets the value of the '{@link relalg.Operator#getCardinality <em>Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' containment reference.
	 * @see #getCardinality()
	 * @generated
	 */
	void setCardinality(Cardinality value);

} // Operator
