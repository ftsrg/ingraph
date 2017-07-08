/**
 */
package relalg.impl;

import java.math.BigInteger;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import relalg.*;

import relalg.function.Function;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelalgFactoryImpl extends EFactoryImpl implements RelalgFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RelalgFactory init() {
		try {
			RelalgFactory theRelalgFactory = (RelalgFactory)EPackage.Registry.INSTANCE.getEFactory(RelalgPackage.eNS_URI);
			if (theRelalgFactory != null) {
				return theRelalgFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RelalgFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RelalgPackage.RELALG_CONTAINER: return createRelalgContainer();
			case RelalgPackage.VERTEX_LABEL: return createVertexLabel();
			case RelalgPackage.EDGE_LABEL: return createEdgeLabel();
			case RelalgPackage.VERTEX_LABEL_SET: return createVertexLabelSet();
			case RelalgPackage.EDGE_LABEL_SET: return createEdgeLabelSet();
			case RelalgPackage.VARIABLE_EXPRESSION: return createVariableExpression();
			case RelalgPackage.VARIABLE_COMPARABLE_EXPRESSION: return createVariableComparableExpression();
			case RelalgPackage.VARIABLE_STRING_EXPRESSION: return createVariableStringExpression();
			case RelalgPackage.VARIABLE_ARITHMETIC_EXPRESSION: return createVariableArithmeticExpression();
			case RelalgPackage.VARIABLE_LIST_EXPRESSION: return createVariableListExpression();
			case RelalgPackage.FUNCTION_EXPRESSION: return createFunctionExpression();
			case RelalgPackage.FUNCTION_COMPARABLE_EXPRESSION: return createFunctionComparableExpression();
			case RelalgPackage.FUNCTION_ARITHMETIC_EXPRESSION: return createFunctionArithmeticExpression();
			case RelalgPackage.FUNCTION_LOGICAL_EXPRESSION: return createFunctionLogicalExpression();
			case RelalgPackage.VERTEX_VARIABLE: return createVertexVariable();
			case RelalgPackage.EDGE_VARIABLE: return createEdgeVariable();
			case RelalgPackage.EDGE_LIST_VARIABLE: return createEdgeListVariable();
			case RelalgPackage.ATTRIBUTE_VARIABLE: return createAttributeVariable();
			case RelalgPackage.LIST_VARIABLE: return createListVariable();
			case RelalgPackage.PATH_VARIABLE: return createPathVariable();
			case RelalgPackage.EXPRESSION_VARIABLE: return createExpressionVariable();
			case RelalgPackage.CARDINALITY: return createCardinality();
			case RelalgPackage.GET_VERTICES_OPERATOR: return createGetVerticesOperator();
			case RelalgPackage.SINGULAR_OBJECT_SOURCE_OPERATOR: return createSingularObjectSourceOperator();
			case RelalgPackage.DUAL_OBJECT_SOURCE_OPERATOR: return createDualObjectSourceOperator();
			case RelalgPackage.GET_EDGES_OPERATOR: return createGetEdgesOperator();
			case RelalgPackage.PRODUCTION_OPERATOR: return createProductionOperator();
			case RelalgPackage.SELECTION_OPERATOR: return createSelectionOperator();
			case RelalgPackage.PROJECTION_OPERATOR: return createProjectionOperator();
			case RelalgPackage.GROUPING_OPERATOR: return createGroupingOperator();
			case RelalgPackage.CREATE_OPERATOR: return createCreateOperator();
			case RelalgPackage.DELETE_OPERATOR: return createDeleteOperator();
			case RelalgPackage.NAVIGATION_DESCRIPTOR: return createNavigationDescriptor();
			case RelalgPackage.MAX_HOPS: return createMaxHops();
			case RelalgPackage.EXPAND_OPERATOR: return createExpandOperator();
			case RelalgPackage.PATH_OPERATOR: return createPathOperator();
			case RelalgPackage.DUPLICATE_ELIMINATION_OPERATOR: return createDuplicateEliminationOperator();
			case RelalgPackage.ALL_DIFFERENT_OPERATOR: return createAllDifferentOperator();
			case RelalgPackage.SORT_OPERATOR: return createSortOperator();
			case RelalgPackage.SORT_ENTRY: return createSortEntry();
			case RelalgPackage.TOP_OPERATOR: return createTopOperator();
			case RelalgPackage.SORT_AND_TOP_OPERATOR: return createSortAndTopOperator();
			case RelalgPackage.UNWIND_OPERATOR: return createUnwindOperator();
			case RelalgPackage.LEFT_OUTER_JOIN_OPERATOR: return createLeftOuterJoinOperator();
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR: return createThetaLeftOuterJoinOperator();
			case RelalgPackage.JOIN_OPERATOR: return createJoinOperator();
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR: return createTransitiveClosureJoinOperator();
			case RelalgPackage.ANTI_JOIN_OPERATOR: return createAntiJoinOperator();
			case RelalgPackage.UNION_OPERATOR: return createUnionOperator();
			case RelalgPackage.GENERIC_CASE_EXPRESSION: return createGenericCaseExpression();
			case RelalgPackage.SIMPLE_CASE_EXPRESSION: return createSimpleCaseExpression();
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION: return createUnaryArithmeticOperationExpression();
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION: return createBinaryArithmeticOperationExpression();
			case RelalgPackage.ARITHMETIC_COMPARISON_EXPRESSION: return createArithmeticComparisonExpression();
			case RelalgPackage.LIST_EXPRESSION: return createListExpression();
			case RelalgPackage.EMPTY_LIST_EXPRESSION: return createEmptyListExpression();
			case RelalgPackage.INDEX_SIMPLE_ACCESS_EXPRESSION: return createIndexSimpleAccessExpression();
			case RelalgPackage.INDEX_RANGE_ACCESS_EXPRESSION: return createIndexRangeAccessExpression();
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION: return createBinaryLogicalExpression();
			case RelalgPackage.UNARY_LOGICAL_EXPRESSION: return createUnaryLogicalExpression();
			case RelalgPackage.UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION: return createUnaryGraphObjectLogicalExpression();
			case RelalgPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
			case RelalgPackage.STRING_LITERAL: return createStringLiteral();
			case RelalgPackage.DOUBLE_LITERAL: return createDoubleLiteral();
			case RelalgPackage.BIG_INTEGER_LITERAL: return createBigIntegerLiteral();
			case RelalgPackage.INTEGER_LITERAL: return createIntegerLiteral();
			case RelalgPackage.NULL_LITERAL: return createNullLiteral();
			case RelalgPackage.PARAMETER: return createParameter();
			case RelalgPackage.PARAMETER_COMPARABLE_EXPRESSION: return createParameterComparableExpression();
			case RelalgPackage.PROPERTY_LIST: return createPropertyList();
			case RelalgPackage.PROPERTY_LIST_ENTRY: return (EObject)createPropertyListEntry();
			case RelalgPackage.CASE: return createCase();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case RelalgPackage.MAX_HOPS_TYPE:
				return createMaxHopsTypeFromString(eDataType, initialValue);
			case RelalgPackage.DIRECTION:
				return createDirectionFromString(eDataType, initialValue);
			case RelalgPackage.ORDER_DIRECTION:
				return createOrderDirectionFromString(eDataType, initialValue);
			case RelalgPackage.BINARY_ARITHMETIC_OPERATOR_TYPE:
				return createBinaryArithmeticOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.ARITHMETIC_COMPARISON_OPERATOR_TYPE:
				return createArithmeticComparisonOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.UNARY_ARITHMETIC_OPERATOR_TYPE:
				return createUnaryArithmeticOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.BINARY_LOGICAL_OPERATOR_TYPE:
				return createBinaryLogicalOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.UNARY_LOGICAL_OPERATOR_TYPE:
				return createUnaryLogicalOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.UNARY_GRAPH_OBJECT_LOGICAL_OPERATOR_TYPE:
				return createUnaryGraphObjectLogicalOperatorTypeFromString(eDataType, initialValue);
			case RelalgPackage.LABEL_SET_STATUS:
				return createLabelSetStatusFromString(eDataType, initialValue);
			case RelalgPackage.PATH_SEMANTICS:
				return createPathSemanticsFromString(eDataType, initialValue);
			case RelalgPackage.FUNCTION:
				return createFunctionFromString(eDataType, initialValue);
			case RelalgPackage.BIGINT:
				return createBigintFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case RelalgPackage.MAX_HOPS_TYPE:
				return convertMaxHopsTypeToString(eDataType, instanceValue);
			case RelalgPackage.DIRECTION:
				return convertDirectionToString(eDataType, instanceValue);
			case RelalgPackage.ORDER_DIRECTION:
				return convertOrderDirectionToString(eDataType, instanceValue);
			case RelalgPackage.BINARY_ARITHMETIC_OPERATOR_TYPE:
				return convertBinaryArithmeticOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.ARITHMETIC_COMPARISON_OPERATOR_TYPE:
				return convertArithmeticComparisonOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.UNARY_ARITHMETIC_OPERATOR_TYPE:
				return convertUnaryArithmeticOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.BINARY_LOGICAL_OPERATOR_TYPE:
				return convertBinaryLogicalOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.UNARY_LOGICAL_OPERATOR_TYPE:
				return convertUnaryLogicalOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.UNARY_GRAPH_OBJECT_LOGICAL_OPERATOR_TYPE:
				return convertUnaryGraphObjectLogicalOperatorTypeToString(eDataType, instanceValue);
			case RelalgPackage.LABEL_SET_STATUS:
				return convertLabelSetStatusToString(eDataType, instanceValue);
			case RelalgPackage.PATH_SEMANTICS:
				return convertPathSemanticsToString(eDataType, instanceValue);
			case RelalgPackage.FUNCTION:
				return convertFunctionToString(eDataType, instanceValue);
			case RelalgPackage.BIGINT:
				return convertBigintToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgContainer createRelalgContainer() {
		RelalgContainerImpl relalgContainer = new RelalgContainerImpl();
		return relalgContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexLabel createVertexLabel() {
		VertexLabelImpl vertexLabel = new VertexLabelImpl();
		return vertexLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabel createEdgeLabel() {
		EdgeLabelImpl edgeLabel = new EdgeLabelImpl();
		return edgeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexLabelSet createVertexLabelSet() {
		VertexLabelSetImpl vertexLabelSet = new VertexLabelSetImpl();
		return vertexLabelSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabelSet createEdgeLabelSet() {
		EdgeLabelSetImpl edgeLabelSet = new EdgeLabelSetImpl();
		return edgeLabelSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableExpression createVariableExpression() {
		VariableExpressionImpl variableExpression = new VariableExpressionImpl();
		return variableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableComparableExpression createVariableComparableExpression() {
		VariableComparableExpressionImpl variableComparableExpression = new VariableComparableExpressionImpl();
		return variableComparableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableStringExpression createVariableStringExpression() {
		VariableStringExpressionImpl variableStringExpression = new VariableStringExpressionImpl();
		return variableStringExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableArithmeticExpression createVariableArithmeticExpression() {
		VariableArithmeticExpressionImpl variableArithmeticExpression = new VariableArithmeticExpressionImpl();
		return variableArithmeticExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableListExpression createVariableListExpression() {
		VariableListExpressionImpl variableListExpression = new VariableListExpressionImpl();
		return variableListExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionExpression createFunctionExpression() {
		FunctionExpressionImpl functionExpression = new FunctionExpressionImpl();
		return functionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionComparableExpression createFunctionComparableExpression() {
		FunctionComparableExpressionImpl functionComparableExpression = new FunctionComparableExpressionImpl();
		return functionComparableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionArithmeticExpression createFunctionArithmeticExpression() {
		FunctionArithmeticExpressionImpl functionArithmeticExpression = new FunctionArithmeticExpressionImpl();
		return functionArithmeticExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionLogicalExpression createFunctionLogicalExpression() {
		FunctionLogicalExpressionImpl functionLogicalExpression = new FunctionLogicalExpressionImpl();
		return functionLogicalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable createVertexVariable() {
		VertexVariableImpl vertexVariable = new VertexVariableImpl();
		return vertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeVariable createEdgeVariable() {
		EdgeVariableImpl edgeVariable = new EdgeVariableImpl();
		return edgeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeListVariable createEdgeListVariable() {
		EdgeListVariableImpl edgeListVariable = new EdgeListVariableImpl();
		return edgeListVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeVariable createAttributeVariable() {
		AttributeVariableImpl attributeVariable = new AttributeVariableImpl();
		return attributeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListVariable createListVariable() {
		ListVariableImpl listVariable = new ListVariableImpl();
		return listVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathVariable createPathVariable() {
		PathVariableImpl pathVariable = new PathVariableImpl();
		return pathVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionVariable createExpressionVariable() {
		ExpressionVariableImpl expressionVariable = new ExpressionVariableImpl();
		return expressionVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cardinality createCardinality() {
		CardinalityImpl cardinality = new CardinalityImpl();
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GetVerticesOperator createGetVerticesOperator() {
		GetVerticesOperatorImpl getVerticesOperator = new GetVerticesOperatorImpl();
		return getVerticesOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingularObjectSourceOperator createSingularObjectSourceOperator() {
		SingularObjectSourceOperatorImpl singularObjectSourceOperator = new SingularObjectSourceOperatorImpl();
		return singularObjectSourceOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DualObjectSourceOperator createDualObjectSourceOperator() {
		DualObjectSourceOperatorImpl dualObjectSourceOperator = new DualObjectSourceOperatorImpl();
		return dualObjectSourceOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GetEdgesOperator createGetEdgesOperator() {
		GetEdgesOperatorImpl getEdgesOperator = new GetEdgesOperatorImpl();
		return getEdgesOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductionOperator createProductionOperator() {
		ProductionOperatorImpl productionOperator = new ProductionOperatorImpl();
		return productionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectionOperator createSelectionOperator() {
		SelectionOperatorImpl selectionOperator = new SelectionOperatorImpl();
		return selectionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectionOperator createProjectionOperator() {
		ProjectionOperatorImpl projectionOperator = new ProjectionOperatorImpl();
		return projectionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupingOperator createGroupingOperator() {
		GroupingOperatorImpl groupingOperator = new GroupingOperatorImpl();
		return groupingOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateOperator createCreateOperator() {
		CreateOperatorImpl createOperator = new CreateOperatorImpl();
		return createOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeleteOperator createDeleteOperator() {
		DeleteOperatorImpl deleteOperator = new DeleteOperatorImpl();
		return deleteOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationDescriptor createNavigationDescriptor() {
		NavigationDescriptorImpl navigationDescriptor = new NavigationDescriptorImpl();
		return navigationDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaxHops createMaxHops() {
		MaxHopsImpl maxHops = new MaxHopsImpl();
		return maxHops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandOperator createExpandOperator() {
		ExpandOperatorImpl expandOperator = new ExpandOperatorImpl();
		return expandOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathOperator createPathOperator() {
		PathOperatorImpl pathOperator = new PathOperatorImpl();
		return pathOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DuplicateEliminationOperator createDuplicateEliminationOperator() {
		DuplicateEliminationOperatorImpl duplicateEliminationOperator = new DuplicateEliminationOperatorImpl();
		return duplicateEliminationOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllDifferentOperator createAllDifferentOperator() {
		AllDifferentOperatorImpl allDifferentOperator = new AllDifferentOperatorImpl();
		return allDifferentOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SortOperator createSortOperator() {
		SortOperatorImpl sortOperator = new SortOperatorImpl();
		return sortOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SortEntry createSortEntry() {
		SortEntryImpl sortEntry = new SortEntryImpl();
		return sortEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopOperator createTopOperator() {
		TopOperatorImpl topOperator = new TopOperatorImpl();
		return topOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SortAndTopOperator createSortAndTopOperator() {
		SortAndTopOperatorImpl sortAndTopOperator = new SortAndTopOperatorImpl();
		return sortAndTopOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnwindOperator createUnwindOperator() {
		UnwindOperatorImpl unwindOperator = new UnwindOperatorImpl();
		return unwindOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LeftOuterJoinOperator createLeftOuterJoinOperator() {
		LeftOuterJoinOperatorImpl leftOuterJoinOperator = new LeftOuterJoinOperatorImpl();
		return leftOuterJoinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThetaLeftOuterJoinOperator createThetaLeftOuterJoinOperator() {
		ThetaLeftOuterJoinOperatorImpl thetaLeftOuterJoinOperator = new ThetaLeftOuterJoinOperatorImpl();
		return thetaLeftOuterJoinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinOperator createJoinOperator() {
		JoinOperatorImpl joinOperator = new JoinOperatorImpl();
		return joinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitiveClosureJoinOperator createTransitiveClosureJoinOperator() {
		TransitiveClosureJoinOperatorImpl transitiveClosureJoinOperator = new TransitiveClosureJoinOperatorImpl();
		return transitiveClosureJoinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AntiJoinOperator createAntiJoinOperator() {
		AntiJoinOperatorImpl antiJoinOperator = new AntiJoinOperatorImpl();
		return antiJoinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionOperator createUnionOperator() {
		UnionOperatorImpl unionOperator = new UnionOperatorImpl();
		return unionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericCaseExpression createGenericCaseExpression() {
		GenericCaseExpressionImpl genericCaseExpression = new GenericCaseExpressionImpl();
		return genericCaseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleCaseExpression createSimpleCaseExpression() {
		SimpleCaseExpressionImpl simpleCaseExpression = new SimpleCaseExpressionImpl();
		return simpleCaseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryArithmeticOperationExpression createUnaryArithmeticOperationExpression() {
		UnaryArithmeticOperationExpressionImpl unaryArithmeticOperationExpression = new UnaryArithmeticOperationExpressionImpl();
		return unaryArithmeticOperationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryArithmeticOperationExpression createBinaryArithmeticOperationExpression() {
		BinaryArithmeticOperationExpressionImpl binaryArithmeticOperationExpression = new BinaryArithmeticOperationExpressionImpl();
		return binaryArithmeticOperationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticComparisonExpression createArithmeticComparisonExpression() {
		ArithmeticComparisonExpressionImpl arithmeticComparisonExpression = new ArithmeticComparisonExpressionImpl();
		return arithmeticComparisonExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListExpression createListExpression() {
		ListExpressionImpl listExpression = new ListExpressionImpl();
		return listExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyListExpression createEmptyListExpression() {
		EmptyListExpressionImpl emptyListExpression = new EmptyListExpressionImpl();
		return emptyListExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexSimpleAccessExpression createIndexSimpleAccessExpression() {
		IndexSimpleAccessExpressionImpl indexSimpleAccessExpression = new IndexSimpleAccessExpressionImpl();
		return indexSimpleAccessExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexRangeAccessExpression createIndexRangeAccessExpression() {
		IndexRangeAccessExpressionImpl indexRangeAccessExpression = new IndexRangeAccessExpressionImpl();
		return indexRangeAccessExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryLogicalExpression createBinaryLogicalExpression() {
		BinaryLogicalExpressionImpl binaryLogicalExpression = new BinaryLogicalExpressionImpl();
		return binaryLogicalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryLogicalExpression createUnaryLogicalExpression() {
		UnaryLogicalExpressionImpl unaryLogicalExpression = new UnaryLogicalExpressionImpl();
		return unaryLogicalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryGraphObjectLogicalExpression createUnaryGraphObjectLogicalExpression() {
		UnaryGraphObjectLogicalExpressionImpl unaryGraphObjectLogicalExpression = new UnaryGraphObjectLogicalExpressionImpl();
		return unaryGraphObjectLogicalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleLiteral createDoubleLiteral() {
		DoubleLiteralImpl doubleLiteral = new DoubleLiteralImpl();
		return doubleLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigIntegerLiteral createBigIntegerLiteral() {
		BigIntegerLiteralImpl bigIntegerLiteral = new BigIntegerLiteralImpl();
		return bigIntegerLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteral createIntegerLiteral() {
		IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
		return integerLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullLiteral createNullLiteral() {
		NullLiteralImpl nullLiteral = new NullLiteralImpl();
		return nullLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterComparableExpression createParameterComparableExpression() {
		ParameterComparableExpressionImpl parameterComparableExpression = new ParameterComparableExpressionImpl();
		return parameterComparableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyList createPropertyList() {
		PropertyListImpl propertyList = new PropertyListImpl();
		return propertyList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Expression> createPropertyListEntry() {
		PropertyListEntryImpl propertyListEntry = new PropertyListEntryImpl();
		return propertyListEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Case createCase() {
		CaseImpl case_ = new CaseImpl();
		return case_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MaxHopsType createMaxHopsTypeFromString(EDataType eDataType, String initialValue) {
		MaxHopsType result = MaxHopsType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaxHopsTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Direction createDirectionFromString(EDataType eDataType, String initialValue) {
		Direction result = Direction.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderDirection createOrderDirectionFromString(EDataType eDataType, String initialValue) {
		OrderDirection result = OrderDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrderDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryArithmeticOperatorType createBinaryArithmeticOperatorTypeFromString(EDataType eDataType, String initialValue) {
		BinaryArithmeticOperatorType result = BinaryArithmeticOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBinaryArithmeticOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticComparisonOperatorType createArithmeticComparisonOperatorTypeFromString(EDataType eDataType, String initialValue) {
		ArithmeticComparisonOperatorType result = ArithmeticComparisonOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArithmeticComparisonOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryArithmeticOperatorType createUnaryArithmeticOperatorTypeFromString(EDataType eDataType, String initialValue) {
		UnaryArithmeticOperatorType result = UnaryArithmeticOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryArithmeticOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryLogicalOperatorType createBinaryLogicalOperatorTypeFromString(EDataType eDataType, String initialValue) {
		BinaryLogicalOperatorType result = BinaryLogicalOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBinaryLogicalOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryLogicalOperatorType createUnaryLogicalOperatorTypeFromString(EDataType eDataType, String initialValue) {
		UnaryLogicalOperatorType result = UnaryLogicalOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryLogicalOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryGraphObjectLogicalOperatorType createUnaryGraphObjectLogicalOperatorTypeFromString(EDataType eDataType, String initialValue) {
		UnaryGraphObjectLogicalOperatorType result = UnaryGraphObjectLogicalOperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryGraphObjectLogicalOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelSetStatus createLabelSetStatusFromString(EDataType eDataType, String initialValue) {
		LabelSetStatus result = LabelSetStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLabelSetStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathSemantics createPathSemanticsFromString(EDataType eDataType, String initialValue) {
		PathSemantics result = PathSemantics.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPathSemanticsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunctionFromString(EDataType eDataType, String initialValue) {
		return (Function)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFunctionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createBigintFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBigintToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgPackage getRelalgPackage() {
		return (RelalgPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RelalgPackage getPackage() {
		return RelalgPackage.eINSTANCE;
	}

} //RelalgFactoryImpl
