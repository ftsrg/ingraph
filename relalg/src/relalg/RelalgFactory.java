/**
 */
package relalg;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage
 * @generated
 */
public interface RelalgFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RelalgFactory eINSTANCE = relalg.impl.RelalgFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Input Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Node</em>'.
	 * @generated
	 */
	InputNode createInputNode();

	/**
	 * Returns a new object of class '<em>Trimmer Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trimmer Node</em>'.
	 * @generated
	 */
	TrimmerNode createTrimmerNode();

	/**
	 * Returns a new object of class '<em>Join Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Node</em>'.
	 * @generated
	 */
	JoinNode createJoinNode();

	/**
	 * Returns a new object of class '<em>Rete Network</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rete Network</em>'.
	 * @generated
	 */
	ReteNetwork createReteNetwork();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelalgPackage getRelalgPackage();

} //RelalgFactory
