/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.RelalgContainer#getName <em>Name</em>}</li>
 *   <li>{@link relalg.RelalgContainer#getRootExpression <em>Root Expression</em>}</li>
 *   <li>{@link relalg.RelalgContainer#getElements <em>Elements</em>}</li>
 *   <li>{@link relalg.RelalgContainer#getExpressions <em>Expressions</em>}</li>
 *   <li>{@link relalg.RelalgContainer#isSimplifiedPlan <em>Simplified Plan</em>}</li>
 *   <li>{@link relalg.RelalgContainer#isIncrementalPlan <em>Incremental Plan</em>}</li>
 *   <li>{@link relalg.RelalgContainer#isExternalSchemaInferred <em>External Schema Inferred</em>}</li>
 *   <li>{@link relalg.RelalgContainer#isExtraVariablesInferred <em>Extra Variables Inferred</em>}</li>
 *   <li>{@link relalg.RelalgContainer#isInternalSchemaInferred <em>Internal Schema Inferred</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getRelalgContainer()
 * @model
 * @generated
 */
public interface RelalgContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see relalg.RelalgPackage#getRelalgContainer_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Root Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Expression</em>' containment reference.
	 * @see #setRootExpression(Operator)
	 * @see relalg.RelalgPackage#getRelalgContainer_RootExpression()
	 * @model containment="true"
	 * @generated
	 */
	Operator getRootExpression();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#getRootExpression <em>Root Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Expression</em>' containment reference.
	 * @see #getRootExpression()
	 * @generated
	 */
	void setRootExpression(Operator value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.NamedElement}.
	 * It is bidirectional and its opposite is '{@link relalg.NamedElement#getNamedElementContainer <em>Named Element Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see relalg.RelalgPackage#getRelalgContainer_Elements()
	 * @see relalg.NamedElement#getNamedElementContainer
	 * @model opposite="namedElementContainer" containment="true"
	 * @generated
	 */
	EList<NamedElement> getElements();

	/**
	 * Returns the value of the '<em><b>Expressions</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.Expression}.
	 * It is bidirectional and its opposite is '{@link relalg.Expression#getExpressionContainer <em>Expression Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expressions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expressions</em>' containment reference list.
	 * @see relalg.RelalgPackage#getRelalgContainer_Expressions()
	 * @see relalg.Expression#getExpressionContainer
	 * @model opposite="expressionContainer" containment="true"
	 * @generated
	 */
	EList<Expression> getExpressions();

	/**
	 * Returns the value of the '<em><b>Simplified Plan</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simplified Plan</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simplified Plan</em>' attribute.
	 * @see #setSimplifiedPlan(boolean)
	 * @see relalg.RelalgPackage#getRelalgContainer_SimplifiedPlan()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isSimplifiedPlan();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#isSimplifiedPlan <em>Simplified Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simplified Plan</em>' attribute.
	 * @see #isSimplifiedPlan()
	 * @generated
	 */
	void setSimplifiedPlan(boolean value);

	/**
	 * Returns the value of the '<em><b>Incremental Plan</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incremental Plan</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incremental Plan</em>' attribute.
	 * @see #setIncrementalPlan(boolean)
	 * @see relalg.RelalgPackage#getRelalgContainer_IncrementalPlan()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isIncrementalPlan();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#isIncrementalPlan <em>Incremental Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incremental Plan</em>' attribute.
	 * @see #isIncrementalPlan()
	 * @generated
	 */
	void setIncrementalPlan(boolean value);

	/**
	 * Returns the value of the '<em><b>External Schema Inferred</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Schema Inferred</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Schema Inferred</em>' attribute.
	 * @see #setExternalSchemaInferred(boolean)
	 * @see relalg.RelalgPackage#getRelalgContainer_ExternalSchemaInferred()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isExternalSchemaInferred();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#isExternalSchemaInferred <em>External Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Schema Inferred</em>' attribute.
	 * @see #isExternalSchemaInferred()
	 * @generated
	 */
	void setExternalSchemaInferred(boolean value);

	/**
	 * Returns the value of the '<em><b>Extra Variables Inferred</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extra Variables Inferred</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extra Variables Inferred</em>' attribute.
	 * @see #setExtraVariablesInferred(boolean)
	 * @see relalg.RelalgPackage#getRelalgContainer_ExtraVariablesInferred()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isExtraVariablesInferred();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#isExtraVariablesInferred <em>Extra Variables Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extra Variables Inferred</em>' attribute.
	 * @see #isExtraVariablesInferred()
	 * @generated
	 */
	void setExtraVariablesInferred(boolean value);

	/**
	 * Returns the value of the '<em><b>Internal Schema Inferred</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Schema Inferred</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Schema Inferred</em>' attribute.
	 * @see #setInternalSchemaInferred(boolean)
	 * @see relalg.RelalgPackage#getRelalgContainer_InternalSchemaInferred()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isInternalSchemaInferred();

	/**
	 * Sets the value of the '{@link relalg.RelalgContainer#isInternalSchemaInferred <em>Internal Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Internal Schema Inferred</em>' attribute.
	 * @see #isInternalSchemaInferred()
	 * @generated
	 */
	void setInternalSchemaInferred(boolean value);

} // RelalgContainer
