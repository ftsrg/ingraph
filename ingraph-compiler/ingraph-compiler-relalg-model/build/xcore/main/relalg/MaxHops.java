/**
 */
package relalg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Max Hops</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.MaxHops#getMaxHopsType <em>Max Hops Type</em>}</li>
 *   <li>{@link relalg.MaxHops#getHops <em>Hops</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getMaxHops()
 * @model
 * @generated
 */
public interface MaxHops extends EObject {
	/**
	 * Returns the value of the '<em><b>Max Hops Type</b></em>' attribute.
	 * The literals are from the enumeration {@link relalg.MaxHopsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Hops Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Hops Type</em>' attribute.
	 * @see relalg.MaxHopsType
	 * @see #setMaxHopsType(MaxHopsType)
	 * @see relalg.RelalgPackage#getMaxHops_MaxHopsType()
	 * @model unique="false"
	 * @generated
	 */
	MaxHopsType getMaxHopsType();

	/**
	 * Sets the value of the '{@link relalg.MaxHops#getMaxHopsType <em>Max Hops Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Hops Type</em>' attribute.
	 * @see relalg.MaxHopsType
	 * @see #getMaxHopsType()
	 * @generated
	 */
	void setMaxHopsType(MaxHopsType value);

	/**
	 * Returns the value of the '<em><b>Hops</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hops</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hops</em>' attribute.
	 * @see #setHops(int)
	 * @see relalg.RelalgPackage#getMaxHops_Hops()
	 * @model unique="false"
	 * @generated
	 */
	int getHops();

	/**
	 * Sets the value of the '{@link relalg.MaxHops#getHops <em>Hops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hops</em>' attribute.
	 * @see #getHops()
	 * @generated
	 */
	void setHops(int value);

} // MaxHops
