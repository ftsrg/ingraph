/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a condition which can be used for selection, theta-join.
 *  * Different operators building on this might have different semantics,
 * e.g. for a left outer join, this never filter on the left input
 *  * - condition holds the actual filtering condition.
 * - conditionString is for informational purposes only. TeX serialization might use this.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.AbstractCondition#getConditionString <em>Condition String</em>}</li>
 *   <li>{@link relalg.AbstractCondition#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getAbstractCondition()
 * @model abstract="true"
 * @generated
 */
public interface AbstractCondition extends EObject {
	/**
	 * Returns the value of the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition String</em>' attribute.
	 * @see #setConditionString(String)
	 * @see relalg.RelalgPackage#getAbstractCondition_ConditionString()
	 * @model unique="false"
	 * @generated
	 */
	String getConditionString();

	/**
	 * Sets the value of the '{@link relalg.AbstractCondition#getConditionString <em>Condition String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition String</em>' attribute.
	 * @see #getConditionString()
	 * @generated
	 */
	void setConditionString(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference.
	 * @see #setCondition(LogicalExpression)
	 * @see relalg.RelalgPackage#getAbstractCondition_Condition()
	 * @model
	 * @generated
	 */
	LogicalExpression getCondition();

	/**
	 * Sets the value of the '{@link relalg.AbstractCondition#getCondition <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(LogicalExpression value);

} // AbstractCondition
