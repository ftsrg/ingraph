/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selection Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see relalg.RelalgPackage#getSelectionOperator()
 * @model
 * @generated
 */
public interface SelectionOperator extends UnaryOperator, AbstractCondition {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _conditionString = this.getConditionString();\n<%java.lang.String%> _plus = (\"Selection { condition: \" + _conditionString);\nreturn (_plus + \" }\");'"
	 * @generated
	 */
	String toString();

} // SelectionOperator
