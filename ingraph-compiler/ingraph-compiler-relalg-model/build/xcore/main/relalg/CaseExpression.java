/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.CaseExpression#getCases <em>Cases</em>}</li>
 *   <li>{@link relalg.CaseExpression#getFallback <em>Fallback</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getCaseExpression()
 * @model abstract="true"
 * @generated
 */
public interface CaseExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Cases</b></em>' containment reference list.
	 * The list contents are of type {@link relalg.Case}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cases</em>' containment reference list.
	 * @see relalg.RelalgPackage#getCaseExpression_Cases()
	 * @model containment="true"
	 * @generated
	 */
	EList<Case> getCases();

	/**
	 * Returns the value of the '<em><b>Fallback</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fallback</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fallback</em>' reference.
	 * @see #setFallback(Expression)
	 * @see relalg.RelalgPackage#getCaseExpression_Fallback()
	 * @model
	 * @generated
	 */
	Expression getFallback();

	/**
	 * Sets the value of the '{@link relalg.CaseExpression#getFallback <em>Fallback</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fallback</em>' reference.
	 * @see #getFallback()
	 * @generated
	 */
	void setFallback(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return \"TODO\";'"
	 * @generated
	 */
	String fullName();

} // CaseExpression
