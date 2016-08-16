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
	 * Returns a new object of class '<em>Input Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input Relation</em>'.
	 * @generated
	 */
	InputRelation createInputRelation();

	/**
	 * Returns a new object of class '<em>Trimmer Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trimmer Operation</em>'.
	 * @generated
	 */
	TrimmerOperation createTrimmerOperation();

	/**
	 * Returns a new object of class '<em>Join Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Operation</em>'.
	 * @generated
	 */
	JoinOperation createJoinOperation();

	/**
	 * Returns a new object of class '<em>Anti Join Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Anti Join Operation</em>'.
	 * @generated
	 */
	AntiJoinOperation createAntiJoinOperation();

	/**
	 * Returns a new object of class '<em>Production Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Production Operation</em>'.
	 * @generated
	 */
	ProductionOperation createProductionOperation();

	/**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	Attribute createAttribute();

	/**
	 * Returns a new object of class '<em>Attribute Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Set</em>'.
	 * @generated
	 */
	AttributeSet createAttributeSet();

	/**
	 * Returns a new object of class '<em>Join Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Binding</em>'.
	 * @generated
	 */
	JoinBinding createJoinBinding();

	/**
	 * Returns a new object of class '<em>Filter Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Operation</em>'.
	 * @generated
	 */
	FilterOperation createFilterOperation();

	/**
	 * Returns a new object of class '<em>Expand</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expand</em>'.
	 * @generated
	 */
	Expand createExpand();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelalgPackage getRelalgPackage();

} //RelalgFactory
