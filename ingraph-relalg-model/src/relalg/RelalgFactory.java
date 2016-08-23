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
	 * Returns a new object of class '<em>Projection Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Projection Operator</em>'.
	 * @generated
	 */
	ProjectionOperator createProjectionOperator();

	/**
	 * Returns a new object of class '<em>Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Operator</em>'.
	 * @generated
	 */
	JoinOperator createJoinOperator();

	/**
	 * Returns a new object of class '<em>Anti Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Anti Join Operator</em>'.
	 * @generated
	 */
	AntiJoinOperator createAntiJoinOperator();

	/**
	 * Returns a new object of class '<em>Production Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Production Operator</em>'.
	 * @generated
	 */
	ProductionOperator createProductionOperator();

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
	 * Returns a new object of class '<em>Filter Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Operator</em>'.
	 * @generated
	 */
	FilterOperator createFilterOperator();

	/**
	 * Returns a new object of class '<em>Expand Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expand Operator</em>'.
	 * @generated
	 */
	ExpandOperator createExpandOperator();

	/**
	 * Returns a new object of class '<em>Get Nodes Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Nodes Operator</em>'.
	 * @generated
	 */
	GetNodesOperator createGetNodesOperator();

	/**
	 * Returns a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * @generated
	 */
	DuplicateEliminationOperator createDuplicateEliminationOperator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelalgPackage getRelalgPackage();

} //RelalgFactory
