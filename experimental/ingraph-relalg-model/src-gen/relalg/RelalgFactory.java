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
	 * Returns a new object of class '<em>Selection Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Selection Operator</em>'.
	 * @generated
	 */
	SelectionOperator createSelectionOperator();

	/**
	 * Returns a new object of class '<em>Expand Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expand Operator</em>'.
	 * @generated
	 */
	ExpandOperator createExpandOperator();

	/**
	 * Returns a new object of class '<em>Get Vertices Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Vertices Operator</em>'.
	 * @generated
	 */
	GetVerticesOperator createGetVerticesOperator();

	/**
	 * Returns a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * @generated
	 */
	DuplicateEliminationOperator createDuplicateEliminationOperator();

	/**
	 * Returns a new object of class '<em>Vertex Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertex Variable</em>'.
	 * @generated
	 */
	VertexVariable createVertexVariable();

	/**
	 * Returns a new object of class '<em>Edge Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge Variable</em>'.
	 * @generated
	 */
	EdgeVariable createEdgeVariable();

	/**
	 * Returns a new object of class '<em>Vertex Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertex Label</em>'.
	 * @generated
	 */
	VertexLabel createVertexLabel();

	/**
	 * Returns a new object of class '<em>Edge Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge Label</em>'.
	 * @generated
	 */
	EdgeLabel createEdgeLabel();

	/**
	 * Returns a new object of class '<em>All Different Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>All Different Operator</em>'.
	 * @generated
	 */
	AllDifferentOperator createAllDifferentOperator();

	/**
	 * Returns a new object of class '<em>Attribute Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Variable</em>'.
	 * @generated
	 */
	AttributeVariable createAttributeVariable();

	/**
	 * Returns a new object of class '<em>Union Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union Operator</em>'.
	 * @generated
	 */
	UnionOperator createUnionOperator();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	Container createContainer();

	/**
	 * Returns a new object of class '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression</em>'.
	 * @generated
	 */
	Expression createExpression();

	/**
	 * Returns a new object of class '<em>Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arithmetic Operation Expression</em>'.
	 * @generated
	 */
	ArithmeticOperationExpression createArithmeticOperationExpression();

	/**
	 * Returns a new object of class '<em>Binary Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Logical Expression</em>'.
	 * @generated
	 */
	BinaryLogicalExpression createBinaryLogicalExpression();

	/**
	 * Returns a new object of class '<em>Arithmetic Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arithmetic Comparison Expression</em>'.
	 * @generated
	 */
	ArithmeticComparisonExpression createArithmeticComparisonExpression();

	/**
	 * Returns a new object of class '<em>Unary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unary Expression</em>'.
	 * @generated
	 */
	UnaryExpression createUnaryExpression();

	/**
	 * Returns a new object of class '<em>String Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Comparison Expression</em>'.
	 * @generated
	 */
	StringComparisonExpression createStringComparisonExpression();

	/**
	 * Returns a new object of class '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Literal</em>'.
	 * @generated
	 */
	StringLiteral createStringLiteral();

	/**
	 * Returns a new object of class '<em>Double Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Literal</em>'.
	 * @generated
	 */
	DoubleLiteral createDoubleLiteral();

	/**
	 * Returns a new object of class '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Literal</em>'.
	 * @generated
	 */
	IntegerLiteral createIntegerLiteral();

	/**
	 * Returns a new object of class '<em>Get Edges Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Edges Operator</em>'.
	 * @generated
	 */
	GetEdgesOperator createGetEdgesOperator();

	/**
	 * Returns a new object of class '<em>Atom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atom</em>'.
	 * @generated
	 */
	Atom createAtom();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelalgPackage getRelalgPackage();

} //RelalgFactory
