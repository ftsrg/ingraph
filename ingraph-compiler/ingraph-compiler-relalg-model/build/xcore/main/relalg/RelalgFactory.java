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
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	RelalgContainer createRelalgContainer();

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
	 * Returns a new object of class '<em>Vertex Label Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertex Label Set</em>'.
	 * @generated
	 */
	VertexLabelSet createVertexLabelSet();

	/**
	 * Returns a new object of class '<em>Edge Label Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge Label Set</em>'.
	 * @generated
	 */
	EdgeLabelSet createEdgeLabelSet();

	/**
	 * Returns a new object of class '<em>Variable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Expression</em>'.
	 * @generated
	 */
	VariableExpression createVariableExpression();

	/**
	 * Returns a new object of class '<em>Variable Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Comparable Expression</em>'.
	 * @generated
	 */
	VariableComparableExpression createVariableComparableExpression();

	/**
	 * Returns a new object of class '<em>Variable String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable String Expression</em>'.
	 * @generated
	 */
	VariableStringExpression createVariableStringExpression();

	/**
	 * Returns a new object of class '<em>Variable Arithmetic Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Arithmetic Expression</em>'.
	 * @generated
	 */
	VariableArithmeticExpression createVariableArithmeticExpression();

	/**
	 * Returns a new object of class '<em>Variable List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable List Expression</em>'.
	 * @generated
	 */
	VariableListExpression createVariableListExpression();

	/**
	 * Returns a new object of class '<em>Function Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Expression</em>'.
	 * @generated
	 */
	FunctionExpression createFunctionExpression();

	/**
	 * Returns a new object of class '<em>Function Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Comparable Expression</em>'.
	 * @generated
	 */
	FunctionComparableExpression createFunctionComparableExpression();

	/**
	 * Returns a new object of class '<em>Function Arithmetic Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Arithmetic Expression</em>'.
	 * @generated
	 */
	FunctionArithmeticExpression createFunctionArithmeticExpression();

	/**
	 * Returns a new object of class '<em>Function Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Logical Expression</em>'.
	 * @generated
	 */
	FunctionLogicalExpression createFunctionLogicalExpression();

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
	 * Returns a new object of class '<em>Edge List Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge List Variable</em>'.
	 * @generated
	 */
	EdgeListVariable createEdgeListVariable();

	/**
	 * Returns a new object of class '<em>Attribute Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Variable</em>'.
	 * @generated
	 */
	AttributeVariable createAttributeVariable();

	/**
	 * Returns a new object of class '<em>List Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Variable</em>'.
	 * @generated
	 */
	ListVariable createListVariable();

	/**
	 * Returns a new object of class '<em>Path Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path Variable</em>'.
	 * @generated
	 */
	PathVariable createPathVariable();

	/**
	 * Returns a new object of class '<em>Expression Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expression Variable</em>'.
	 * @generated
	 */
	ExpressionVariable createExpressionVariable();

	/**
	 * Returns a new object of class '<em>Cardinality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cardinality</em>'.
	 * @generated
	 */
	Cardinality createCardinality();

	/**
	 * Returns a new object of class '<em>Get Vertices Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Vertices Operator</em>'.
	 * @generated
	 */
	GetVerticesOperator createGetVerticesOperator();

	/**
	 * Returns a new object of class '<em>Singular Object Source Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Singular Object Source Operator</em>'.
	 * @generated
	 */
	SingularObjectSourceOperator createSingularObjectSourceOperator();

	/**
	 * Returns a new object of class '<em>Dual Object Source Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dual Object Source Operator</em>'.
	 * @generated
	 */
	DualObjectSourceOperator createDualObjectSourceOperator();

	/**
	 * Returns a new object of class '<em>Get Edges Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Edges Operator</em>'.
	 * @generated
	 */
	GetEdgesOperator createGetEdgesOperator();

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
	 * Returns a new object of class '<em>Projection Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Projection Operator</em>'.
	 * @generated
	 */
	ProjectionOperator createProjectionOperator();

	/**
	 * Returns a new object of class '<em>Grouping Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grouping Operator</em>'.
	 * @generated
	 */
	GroupingOperator createGroupingOperator();

	/**
	 * Returns a new object of class '<em>Create Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Operator</em>'.
	 * @generated
	 */
	CreateOperator createCreateOperator();

	/**
	 * Returns a new object of class '<em>Delete Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delete Operator</em>'.
	 * @generated
	 */
	DeleteOperator createDeleteOperator();

	/**
	 * Returns a new object of class '<em>Navigation Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Descriptor</em>'.
	 * @generated
	 */
	NavigationDescriptor createNavigationDescriptor();

	/**
	 * Returns a new object of class '<em>Max Hops</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Max Hops</em>'.
	 * @generated
	 */
	MaxHops createMaxHops();

	/**
	 * Returns a new object of class '<em>Expand Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expand Operator</em>'.
	 * @generated
	 */
	ExpandOperator createExpandOperator();

	/**
	 * Returns a new object of class '<em>Path Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path Operator</em>'.
	 * @generated
	 */
	PathOperator createPathOperator();

	/**
	 * Returns a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Duplicate Elimination Operator</em>'.
	 * @generated
	 */
	DuplicateEliminationOperator createDuplicateEliminationOperator();

	/**
	 * Returns a new object of class '<em>All Different Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>All Different Operator</em>'.
	 * @generated
	 */
	AllDifferentOperator createAllDifferentOperator();

	/**
	 * Returns a new object of class '<em>Sort Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sort Operator</em>'.
	 * @generated
	 */
	SortOperator createSortOperator();

	/**
	 * Returns a new object of class '<em>Sort Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sort Entry</em>'.
	 * @generated
	 */
	SortEntry createSortEntry();

	/**
	 * Returns a new object of class '<em>Top Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Top Operator</em>'.
	 * @generated
	 */
	TopOperator createTopOperator();

	/**
	 * Returns a new object of class '<em>Sort And Top Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sort And Top Operator</em>'.
	 * @generated
	 */
	SortAndTopOperator createSortAndTopOperator();

	/**
	 * Returns a new object of class '<em>Unwind Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unwind Operator</em>'.
	 * @generated
	 */
	UnwindOperator createUnwindOperator();

	/**
	 * Returns a new object of class '<em>Left Outer Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Left Outer Join Operator</em>'.
	 * @generated
	 */
	LeftOuterJoinOperator createLeftOuterJoinOperator();

	/**
	 * Returns a new object of class '<em>Theta Left Outer Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Theta Left Outer Join Operator</em>'.
	 * @generated
	 */
	ThetaLeftOuterJoinOperator createThetaLeftOuterJoinOperator();

	/**
	 * Returns a new object of class '<em>Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Operator</em>'.
	 * @generated
	 */
	JoinOperator createJoinOperator();

	/**
	 * Returns a new object of class '<em>Transitive Closure Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transitive Closure Join Operator</em>'.
	 * @generated
	 */
	TransitiveClosureJoinOperator createTransitiveClosureJoinOperator();

	/**
	 * Returns a new object of class '<em>Anti Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Anti Join Operator</em>'.
	 * @generated
	 */
	AntiJoinOperator createAntiJoinOperator();

	/**
	 * Returns a new object of class '<em>Union Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union Operator</em>'.
	 * @generated
	 */
	UnionOperator createUnionOperator();

	/**
	 * Returns a new object of class '<em>Generic Case Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Case Expression</em>'.
	 * @generated
	 */
	GenericCaseExpression createGenericCaseExpression();

	/**
	 * Returns a new object of class '<em>Simple Case Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Case Expression</em>'.
	 * @generated
	 */
	SimpleCaseExpression createSimpleCaseExpression();

	/**
	 * Returns a new object of class '<em>Unary Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unary Arithmetic Operation Expression</em>'.
	 * @generated
	 */
	UnaryArithmeticOperationExpression createUnaryArithmeticOperationExpression();

	/**
	 * Returns a new object of class '<em>Binary Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Arithmetic Operation Expression</em>'.
	 * @generated
	 */
	BinaryArithmeticOperationExpression createBinaryArithmeticOperationExpression();

	/**
	 * Returns a new object of class '<em>Arithmetic Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arithmetic Comparison Expression</em>'.
	 * @generated
	 */
	ArithmeticComparisonExpression createArithmeticComparisonExpression();

	/**
	 * Returns a new object of class '<em>List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Expression</em>'.
	 * @generated
	 */
	ListExpression createListExpression();

	/**
	 * Returns a new object of class '<em>Empty List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Empty List Expression</em>'.
	 * @generated
	 */
	EmptyListExpression createEmptyListExpression();

	/**
	 * Returns a new object of class '<em>Index Simple Access Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Index Simple Access Expression</em>'.
	 * @generated
	 */
	IndexSimpleAccessExpression createIndexSimpleAccessExpression();

	/**
	 * Returns a new object of class '<em>Index Range Access Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Index Range Access Expression</em>'.
	 * @generated
	 */
	IndexRangeAccessExpression createIndexRangeAccessExpression();

	/**
	 * Returns a new object of class '<em>Binary Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary Logical Expression</em>'.
	 * @generated
	 */
	BinaryLogicalExpression createBinaryLogicalExpression();

	/**
	 * Returns a new object of class '<em>Unary Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unary Logical Expression</em>'.
	 * @generated
	 */
	UnaryLogicalExpression createUnaryLogicalExpression();

	/**
	 * Returns a new object of class '<em>Unary Graph Object Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unary Graph Object Logical Expression</em>'.
	 * @generated
	 */
	UnaryGraphObjectLogicalExpression createUnaryGraphObjectLogicalExpression();

	/**
	 * Returns a new object of class '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Literal</em>'.
	 * @generated
	 */
	BooleanLiteral createBooleanLiteral();

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
	 * Returns a new object of class '<em>Big Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Big Integer Literal</em>'.
	 * @generated
	 */
	BigIntegerLiteral createBigIntegerLiteral();

	/**
	 * Returns a new object of class '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Literal</em>'.
	 * @generated
	 */
	IntegerLiteral createIntegerLiteral();

	/**
	 * Returns a new object of class '<em>Null Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Null Literal</em>'.
	 * @generated
	 */
	NullLiteral createNullLiteral();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Parameter Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Comparable Expression</em>'.
	 * @generated
	 */
	ParameterComparableExpression createParameterComparableExpression();

	/**
	 * Returns a new object of class '<em>Property List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property List</em>'.
	 * @generated
	 */
	PropertyList createPropertyList();

	/**
	 * Returns a new object of class '<em>Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Case</em>'.
	 * @generated
	 */
	Case createCase();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RelalgPackage getRelalgPackage();

} //RelalgFactory
