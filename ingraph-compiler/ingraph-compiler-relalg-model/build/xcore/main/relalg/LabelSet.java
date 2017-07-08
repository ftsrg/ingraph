/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A set of labels.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.LabelSet#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getLabelSet()
 * @model abstract="true"
 * @generated
 */
public interface LabelSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"EMPTY"</code>.
	 * The literals are from the enumeration {@link relalg.LabelSetStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see relalg.LabelSetStatus
	 * @see #setStatus(LabelSetStatus)
	 * @see relalg.RelalgPackage#getLabelSet_Status()
	 * @model default="EMPTY" unique="false"
	 * @generated
	 */
	LabelSetStatus getStatus();

	/**
	 * Sets the value of the '{@link relalg.LabelSet#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see relalg.LabelSetStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(LabelSetStatus value);

} // LabelSet
