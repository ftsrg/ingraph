/**
 */
package relalg.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import relalg.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage
 * @generated
 */
public class RelalgAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RelalgPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RelalgPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelalgSwitch<Adapter> modelSwitch =
		new RelalgSwitch<Adapter>() {
			@Override
			public Adapter caseRelalgContainer(RelalgContainer object) {
				return createRelalgContainerAdapter();
			}
			@Override
			public Adapter caseRelalgModelElement(RelalgModelElement object) {
				return createRelalgModelElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseLabel(Label object) {
				return createLabelAdapter();
			}
			@Override
			public Adapter caseVertexLabel(VertexLabel object) {
				return createVertexLabelAdapter();
			}
			@Override
			public Adapter caseEdgeLabel(EdgeLabel object) {
				return createEdgeLabelAdapter();
			}
			@Override
			public Adapter caseLabelSet(LabelSet object) {
				return createLabelSetAdapter();
			}
			@Override
			public Adapter caseVertexLabelSet(VertexLabelSet object) {
				return createVertexLabelSetAdapter();
			}
			@Override
			public Adapter caseEdgeLabelSet(EdgeLabelSet object) {
				return createEdgeLabelSetAdapter();
			}
			@Override
			public Adapter caseVariableExpression(VariableExpression object) {
				return createVariableExpressionAdapter();
			}
			@Override
			public Adapter caseVariableComparableExpression(VariableComparableExpression object) {
				return createVariableComparableExpressionAdapter();
			}
			@Override
			public Adapter caseVariableStringExpression(VariableStringExpression object) {
				return createVariableStringExpressionAdapter();
			}
			@Override
			public Adapter caseVariableArithmeticExpression(VariableArithmeticExpression object) {
				return createVariableArithmeticExpressionAdapter();
			}
			@Override
			public Adapter caseVariableListExpression(VariableListExpression object) {
				return createVariableListExpressionAdapter();
			}
			@Override
			public Adapter caseFunctionExpression(FunctionExpression object) {
				return createFunctionExpressionAdapter();
			}
			@Override
			public Adapter caseFunctionComparableExpression(FunctionComparableExpression object) {
				return createFunctionComparableExpressionAdapter();
			}
			@Override
			public Adapter caseFunctionArithmeticExpression(FunctionArithmeticExpression object) {
				return createFunctionArithmeticExpressionAdapter();
			}
			@Override
			public Adapter caseFunctionLogicalExpression(FunctionLogicalExpression object) {
				return createFunctionLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseGraphObjectVariable(GraphObjectVariable object) {
				return createGraphObjectVariableAdapter();
			}
			@Override
			public Adapter caseElementVariable(ElementVariable object) {
				return createElementVariableAdapter();
			}
			@Override
			public Adapter caseVertexVariable(VertexVariable object) {
				return createVertexVariableAdapter();
			}
			@Override
			public Adapter caseAbstractEdgeVariable(AbstractEdgeVariable object) {
				return createAbstractEdgeVariableAdapter();
			}
			@Override
			public Adapter caseEdgeVariable(EdgeVariable object) {
				return createEdgeVariableAdapter();
			}
			@Override
			public Adapter caseEdgeListVariable(EdgeListVariable object) {
				return createEdgeListVariableAdapter();
			}
			@Override
			public Adapter caseAttributeVariable(AttributeVariable object) {
				return createAttributeVariableAdapter();
			}
			@Override
			public Adapter caseListVariable(ListVariable object) {
				return createListVariableAdapter();
			}
			@Override
			public Adapter casePathVariable(PathVariable object) {
				return createPathVariableAdapter();
			}
			@Override
			public Adapter caseExpressionVariable(ExpressionVariable object) {
				return createExpressionVariableAdapter();
			}
			@Override
			public Adapter caseOperator(Operator object) {
				return createOperatorAdapter();
			}
			@Override
			public Adapter caseCardinality(Cardinality object) {
				return createCardinalityAdapter();
			}
			@Override
			public Adapter caseNullaryOperator(NullaryOperator object) {
				return createNullaryOperatorAdapter();
			}
			@Override
			public Adapter caseGetVerticesOperator(GetVerticesOperator object) {
				return createGetVerticesOperatorAdapter();
			}
			@Override
			public Adapter caseSingularObjectSourceOperator(SingularObjectSourceOperator object) {
				return createSingularObjectSourceOperatorAdapter();
			}
			@Override
			public Adapter caseDualObjectSourceOperator(DualObjectSourceOperator object) {
				return createDualObjectSourceOperatorAdapter();
			}
			@Override
			public Adapter caseGetEdgesOperator(GetEdgesOperator object) {
				return createGetEdgesOperatorAdapter();
			}
			@Override
			public Adapter caseUnaryOperator(UnaryOperator object) {
				return createUnaryOperatorAdapter();
			}
			@Override
			public Adapter caseBeamerOperator(BeamerOperator object) {
				return createBeamerOperatorAdapter();
			}
			@Override
			public Adapter caseProductionOperator(ProductionOperator object) {
				return createProductionOperatorAdapter();
			}
			@Override
			public Adapter caseAbstractCondition(AbstractCondition object) {
				return createAbstractConditionAdapter();
			}
			@Override
			public Adapter caseSelectionOperator(SelectionOperator object) {
				return createSelectionOperatorAdapter();
			}
			@Override
			public Adapter caseProjectionOperator(ProjectionOperator object) {
				return createProjectionOperatorAdapter();
			}
			@Override
			public Adapter caseGroupingOperator(GroupingOperator object) {
				return createGroupingOperatorAdapter();
			}
			@Override
			public Adapter caseCUDOperator(CUDOperator object) {
				return createCUDOperatorAdapter();
			}
			@Override
			public Adapter caseCreateOperator(CreateOperator object) {
				return createCreateOperatorAdapter();
			}
			@Override
			public Adapter caseDeleteOperator(DeleteOperator object) {
				return createDeleteOperatorAdapter();
			}
			@Override
			public Adapter caseNavigationDescriptor(NavigationDescriptor object) {
				return createNavigationDescriptorAdapter();
			}
			@Override
			public Adapter caseMaxHops(MaxHops object) {
				return createMaxHopsAdapter();
			}
			@Override
			public Adapter caseExpandOperator(ExpandOperator object) {
				return createExpandOperatorAdapter();
			}
			@Override
			public Adapter casePathOperator(PathOperator object) {
				return createPathOperatorAdapter();
			}
			@Override
			public Adapter caseDuplicateEliminationOperator(DuplicateEliminationOperator object) {
				return createDuplicateEliminationOperatorAdapter();
			}
			@Override
			public Adapter caseAllDifferentOperator(AllDifferentOperator object) {
				return createAllDifferentOperatorAdapter();
			}
			@Override
			public Adapter caseSortOperator(SortOperator object) {
				return createSortOperatorAdapter();
			}
			@Override
			public Adapter caseSortEntry(SortEntry object) {
				return createSortEntryAdapter();
			}
			@Override
			public Adapter caseTopOperator(TopOperator object) {
				return createTopOperatorAdapter();
			}
			@Override
			public Adapter caseSortAndTopOperator(SortAndTopOperator object) {
				return createSortAndTopOperatorAdapter();
			}
			@Override
			public Adapter caseUnwindOperator(UnwindOperator object) {
				return createUnwindOperatorAdapter();
			}
			@Override
			public Adapter caseBinaryOperator(BinaryOperator object) {
				return createBinaryOperatorAdapter();
			}
			@Override
			public Adapter caseCommutativeBinaryOperator(CommutativeBinaryOperator object) {
				return createCommutativeBinaryOperatorAdapter();
			}
			@Override
			public Adapter caseAssociativeBinaryOperator(AssociativeBinaryOperator object) {
				return createAssociativeBinaryOperatorAdapter();
			}
			@Override
			public Adapter caseAbstractJoinOperator(AbstractJoinOperator object) {
				return createAbstractJoinOperatorAdapter();
			}
			@Override
			public Adapter caseEquiJoinLikeOperator(EquiJoinLikeOperator object) {
				return createEquiJoinLikeOperatorAdapter();
			}
			@Override
			public Adapter caseLeftOuterJoinOperator(LeftOuterJoinOperator object) {
				return createLeftOuterJoinOperatorAdapter();
			}
			@Override
			public Adapter caseThetaLeftOuterJoinOperator(ThetaLeftOuterJoinOperator object) {
				return createThetaLeftOuterJoinOperatorAdapter();
			}
			@Override
			public Adapter caseJoinOperator(JoinOperator object) {
				return createJoinOperatorAdapter();
			}
			@Override
			public Adapter caseTransitiveClosureJoinOperator(TransitiveClosureJoinOperator object) {
				return createTransitiveClosureJoinOperatorAdapter();
			}
			@Override
			public Adapter caseAntiJoinOperator(AntiJoinOperator object) {
				return createAntiJoinOperatorAdapter();
			}
			@Override
			public Adapter caseUnionOperator(UnionOperator object) {
				return createUnionOperatorAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseCaseExpression(CaseExpression object) {
				return createCaseExpressionAdapter();
			}
			@Override
			public Adapter caseGenericCaseExpression(GenericCaseExpression object) {
				return createGenericCaseExpressionAdapter();
			}
			@Override
			public Adapter caseSimpleCaseExpression(SimpleCaseExpression object) {
				return createSimpleCaseExpressionAdapter();
			}
			@Override
			public Adapter caseArithmeticExpression(ArithmeticExpression object) {
				return createArithmeticExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryExpression(UnaryExpression object) {
				return createUnaryExpressionAdapter();
			}
			@Override
			public Adapter caseBinaryExpression(BinaryExpression object) {
				return createBinaryExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryArithmeticOperationExpression(UnaryArithmeticOperationExpression object) {
				return createUnaryArithmeticOperationExpressionAdapter();
			}
			@Override
			public Adapter caseBinaryArithmeticOperationExpression(BinaryArithmeticOperationExpression object) {
				return createBinaryArithmeticOperationExpressionAdapter();
			}
			@Override
			public Adapter caseArithmeticComparisonExpression(ArithmeticComparisonExpression object) {
				return createArithmeticComparisonExpressionAdapter();
			}
			@Override
			public Adapter caseStringExpression(StringExpression object) {
				return createStringExpressionAdapter();
			}
			@Override
			public Adapter caseLogicalExpression(LogicalExpression object) {
				return createLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseListExpression(ListExpression object) {
				return createListExpressionAdapter();
			}
			@Override
			public Adapter caseEmptyListExpression(EmptyListExpression object) {
				return createEmptyListExpressionAdapter();
			}
			@Override
			public Adapter caseIndexAccessExpression(IndexAccessExpression object) {
				return createIndexAccessExpressionAdapter();
			}
			@Override
			public Adapter caseIndexSimpleAccessExpression(IndexSimpleAccessExpression object) {
				return createIndexSimpleAccessExpressionAdapter();
			}
			@Override
			public Adapter caseIndexRangeAccessExpression(IndexRangeAccessExpression object) {
				return createIndexRangeAccessExpressionAdapter();
			}
			@Override
			public Adapter caseBinaryLogicalExpression(BinaryLogicalExpression object) {
				return createBinaryLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryLogicalExpression(UnaryLogicalExpression object) {
				return createUnaryLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryGraphObjectLogicalExpression(UnaryGraphObjectLogicalExpression object) {
				return createUnaryGraphObjectLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseComparableExpression(ComparableExpression object) {
				return createComparableExpressionAdapter();
			}
			@Override
			public Adapter caseAtom(Atom object) {
				return createAtomAdapter();
			}
			@Override
			public Adapter caseLiteral(Literal object) {
				return createLiteralAdapter();
			}
			@Override
			public Adapter caseBooleanLiteral(BooleanLiteral object) {
				return createBooleanLiteralAdapter();
			}
			@Override
			public Adapter caseStringLiteral(StringLiteral object) {
				return createStringLiteralAdapter();
			}
			@Override
			public Adapter caseNumberLiteral(NumberLiteral object) {
				return createNumberLiteralAdapter();
			}
			@Override
			public Adapter caseDoubleLiteral(DoubleLiteral object) {
				return createDoubleLiteralAdapter();
			}
			@Override
			public Adapter caseBigIntegerLiteral(BigIntegerLiteral object) {
				return createBigIntegerLiteralAdapter();
			}
			@Override
			public Adapter caseIntegerLiteral(IntegerLiteral object) {
				return createIntegerLiteralAdapter();
			}
			@Override
			public Adapter caseNullLiteral(NullLiteral object) {
				return createNullLiteralAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			@Override
			public Adapter caseParameterComparableExpression(ParameterComparableExpression object) {
				return createParameterComparableExpressionAdapter();
			}
			@Override
			public Adapter casePropertyList(PropertyList object) {
				return createPropertyListAdapter();
			}
			@Override
			public Adapter casePropertyListEntry(Map.Entry<String, Expression> object) {
				return createPropertyListEntryAdapter();
			}
			@Override
			public Adapter caseCase(Case object) {
				return createCaseAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link relalg.RelalgContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.RelalgContainer
	 * @generated
	 */
	public Adapter createRelalgContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.RelalgModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.RelalgModelElement
	 * @generated
	 */
	public Adapter createRelalgModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VertexLabel <em>Vertex Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VertexLabel
	 * @generated
	 */
	public Adapter createVertexLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EdgeLabel <em>Edge Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EdgeLabel
	 * @generated
	 */
	public Adapter createEdgeLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.LabelSet <em>Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.LabelSet
	 * @generated
	 */
	public Adapter createLabelSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VertexLabelSet <em>Vertex Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VertexLabelSet
	 * @generated
	 */
	public Adapter createVertexLabelSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EdgeLabelSet <em>Edge Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EdgeLabelSet
	 * @generated
	 */
	public Adapter createEdgeLabelSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VariableExpression
	 * @generated
	 */
	public Adapter createVariableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VariableComparableExpression <em>Variable Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VariableComparableExpression
	 * @generated
	 */
	public Adapter createVariableComparableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VariableStringExpression <em>Variable String Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VariableStringExpression
	 * @generated
	 */
	public Adapter createVariableStringExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VariableArithmeticExpression <em>Variable Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VariableArithmeticExpression
	 * @generated
	 */
	public Adapter createVariableArithmeticExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VariableListExpression <em>Variable List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VariableListExpression
	 * @generated
	 */
	public Adapter createVariableListExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.FunctionExpression <em>Function Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.FunctionExpression
	 * @generated
	 */
	public Adapter createFunctionExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.FunctionComparableExpression <em>Function Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.FunctionComparableExpression
	 * @generated
	 */
	public Adapter createFunctionComparableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.FunctionArithmeticExpression <em>Function Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.FunctionArithmeticExpression
	 * @generated
	 */
	public Adapter createFunctionArithmeticExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.FunctionLogicalExpression <em>Function Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.FunctionLogicalExpression
	 * @generated
	 */
	public Adapter createFunctionLogicalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.GraphObjectVariable <em>Graph Object Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.GraphObjectVariable
	 * @generated
	 */
	public Adapter createGraphObjectVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ElementVariable <em>Element Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ElementVariable
	 * @generated
	 */
	public Adapter createElementVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.VertexVariable <em>Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.VertexVariable
	 * @generated
	 */
	public Adapter createVertexVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AbstractEdgeVariable <em>Abstract Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AbstractEdgeVariable
	 * @generated
	 */
	public Adapter createAbstractEdgeVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EdgeVariable
	 * @generated
	 */
	public Adapter createEdgeVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EdgeListVariable <em>Edge List Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EdgeListVariable
	 * @generated
	 */
	public Adapter createEdgeListVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AttributeVariable <em>Attribute Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AttributeVariable
	 * @generated
	 */
	public Adapter createAttributeVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ListVariable <em>List Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ListVariable
	 * @generated
	 */
	public Adapter createListVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.PathVariable <em>Path Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.PathVariable
	 * @generated
	 */
	public Adapter createPathVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ExpressionVariable <em>Expression Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ExpressionVariable
	 * @generated
	 */
	public Adapter createExpressionVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Operator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Operator
	 * @generated
	 */
	public Adapter createOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Cardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Cardinality
	 * @generated
	 */
	public Adapter createCardinalityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.NullaryOperator <em>Nullary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.NullaryOperator
	 * @generated
	 */
	public Adapter createNullaryOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.GetVerticesOperator <em>Get Vertices Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.GetVerticesOperator
	 * @generated
	 */
	public Adapter createGetVerticesOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SingularObjectSourceOperator <em>Singular Object Source Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SingularObjectSourceOperator
	 * @generated
	 */
	public Adapter createSingularObjectSourceOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.DualObjectSourceOperator <em>Dual Object Source Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.DualObjectSourceOperator
	 * @generated
	 */
	public Adapter createDualObjectSourceOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.GetEdgesOperator <em>Get Edges Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.GetEdgesOperator
	 * @generated
	 */
	public Adapter createGetEdgesOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnaryOperator <em>Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnaryOperator
	 * @generated
	 */
	public Adapter createUnaryOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BeamerOperator <em>Beamer Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BeamerOperator
	 * @generated
	 */
	public Adapter createBeamerOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ProductionOperator <em>Production Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ProductionOperator
	 * @generated
	 */
	public Adapter createProductionOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AbstractCondition <em>Abstract Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AbstractCondition
	 * @generated
	 */
	public Adapter createAbstractConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SelectionOperator <em>Selection Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SelectionOperator
	 * @generated
	 */
	public Adapter createSelectionOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ProjectionOperator <em>Projection Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ProjectionOperator
	 * @generated
	 */
	public Adapter createProjectionOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.GroupingOperator <em>Grouping Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.GroupingOperator
	 * @generated
	 */
	public Adapter createGroupingOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.CUDOperator <em>CUD Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.CUDOperator
	 * @generated
	 */
	public Adapter createCUDOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.CreateOperator <em>Create Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.CreateOperator
	 * @generated
	 */
	public Adapter createCreateOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.DeleteOperator <em>Delete Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.DeleteOperator
	 * @generated
	 */
	public Adapter createDeleteOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.NavigationDescriptor <em>Navigation Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.NavigationDescriptor
	 * @generated
	 */
	public Adapter createNavigationDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.MaxHops <em>Max Hops</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.MaxHops
	 * @generated
	 */
	public Adapter createMaxHopsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ExpandOperator <em>Expand Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ExpandOperator
	 * @generated
	 */
	public Adapter createExpandOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.PathOperator <em>Path Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.PathOperator
	 * @generated
	 */
	public Adapter createPathOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.DuplicateEliminationOperator <em>Duplicate Elimination Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.DuplicateEliminationOperator
	 * @generated
	 */
	public Adapter createDuplicateEliminationOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AllDifferentOperator <em>All Different Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AllDifferentOperator
	 * @generated
	 */
	public Adapter createAllDifferentOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SortOperator <em>Sort Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SortOperator
	 * @generated
	 */
	public Adapter createSortOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SortEntry <em>Sort Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SortEntry
	 * @generated
	 */
	public Adapter createSortEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.TopOperator <em>Top Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.TopOperator
	 * @generated
	 */
	public Adapter createTopOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SortAndTopOperator <em>Sort And Top Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SortAndTopOperator
	 * @generated
	 */
	public Adapter createSortAndTopOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnwindOperator <em>Unwind Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnwindOperator
	 * @generated
	 */
	public Adapter createUnwindOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BinaryOperator <em>Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BinaryOperator
	 * @generated
	 */
	public Adapter createBinaryOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.CommutativeBinaryOperator <em>Commutative Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.CommutativeBinaryOperator
	 * @generated
	 */
	public Adapter createCommutativeBinaryOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AssociativeBinaryOperator <em>Associative Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AssociativeBinaryOperator
	 * @generated
	 */
	public Adapter createAssociativeBinaryOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AbstractJoinOperator <em>Abstract Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AbstractJoinOperator
	 * @generated
	 */
	public Adapter createAbstractJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EquiJoinLikeOperator <em>Equi Join Like Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EquiJoinLikeOperator
	 * @generated
	 */
	public Adapter createEquiJoinLikeOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.LeftOuterJoinOperator <em>Left Outer Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.LeftOuterJoinOperator
	 * @generated
	 */
	public Adapter createLeftOuterJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ThetaLeftOuterJoinOperator <em>Theta Left Outer Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ThetaLeftOuterJoinOperator
	 * @generated
	 */
	public Adapter createThetaLeftOuterJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.JoinOperator <em>Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.JoinOperator
	 * @generated
	 */
	public Adapter createJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.TransitiveClosureJoinOperator <em>Transitive Closure Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.TransitiveClosureJoinOperator
	 * @generated
	 */
	public Adapter createTransitiveClosureJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.AntiJoinOperator <em>Anti Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AntiJoinOperator
	 * @generated
	 */
	public Adapter createAntiJoinOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnionOperator <em>Union Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnionOperator
	 * @generated
	 */
	public Adapter createUnionOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.CaseExpression <em>Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.CaseExpression
	 * @generated
	 */
	public Adapter createCaseExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.GenericCaseExpression <em>Generic Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.GenericCaseExpression
	 * @generated
	 */
	public Adapter createGenericCaseExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.SimpleCaseExpression <em>Simple Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.SimpleCaseExpression
	 * @generated
	 */
	public Adapter createSimpleCaseExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ArithmeticExpression <em>Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ArithmeticExpression
	 * @generated
	 */
	public Adapter createArithmeticExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnaryExpression <em>Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnaryExpression
	 * @generated
	 */
	public Adapter createUnaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BinaryExpression <em>Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BinaryExpression
	 * @generated
	 */
	public Adapter createBinaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnaryArithmeticOperationExpression <em>Unary Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnaryArithmeticOperationExpression
	 * @generated
	 */
	public Adapter createUnaryArithmeticOperationExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BinaryArithmeticOperationExpression <em>Binary Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BinaryArithmeticOperationExpression
	 * @generated
	 */
	public Adapter createBinaryArithmeticOperationExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ArithmeticComparisonExpression <em>Arithmetic Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ArithmeticComparisonExpression
	 * @generated
	 */
	public Adapter createArithmeticComparisonExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.StringExpression <em>String Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.StringExpression
	 * @generated
	 */
	public Adapter createStringExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.LogicalExpression <em>Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.LogicalExpression
	 * @generated
	 */
	public Adapter createLogicalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ListExpression <em>List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ListExpression
	 * @generated
	 */
	public Adapter createListExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.EmptyListExpression <em>Empty List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.EmptyListExpression
	 * @generated
	 */
	public Adapter createEmptyListExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.IndexAccessExpression <em>Index Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.IndexAccessExpression
	 * @generated
	 */
	public Adapter createIndexAccessExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.IndexSimpleAccessExpression <em>Index Simple Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.IndexSimpleAccessExpression
	 * @generated
	 */
	public Adapter createIndexSimpleAccessExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.IndexRangeAccessExpression <em>Index Range Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.IndexRangeAccessExpression
	 * @generated
	 */
	public Adapter createIndexRangeAccessExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BinaryLogicalExpression <em>Binary Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BinaryLogicalExpression
	 * @generated
	 */
	public Adapter createBinaryLogicalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnaryLogicalExpression <em>Unary Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnaryLogicalExpression
	 * @generated
	 */
	public Adapter createUnaryLogicalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.UnaryGraphObjectLogicalExpression <em>Unary Graph Object Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.UnaryGraphObjectLogicalExpression
	 * @generated
	 */
	public Adapter createUnaryGraphObjectLogicalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ComparableExpression <em>Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ComparableExpression
	 * @generated
	 */
	public Adapter createComparableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Atom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Atom
	 * @generated
	 */
	public Adapter createAtomAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Literal
	 * @generated
	 */
	public Adapter createLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BooleanLiteral
	 * @generated
	 */
	public Adapter createBooleanLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.StringLiteral
	 * @generated
	 */
	public Adapter createStringLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.NumberLiteral
	 * @generated
	 */
	public Adapter createNumberLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.DoubleLiteral
	 * @generated
	 */
	public Adapter createDoubleLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BigIntegerLiteral <em>Big Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BigIntegerLiteral
	 * @generated
	 */
	public Adapter createBigIntegerLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.IntegerLiteral
	 * @generated
	 */
	public Adapter createIntegerLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.NullLiteral
	 * @generated
	 */
	public Adapter createNullLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.ParameterComparableExpression <em>Parameter Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ParameterComparableExpression
	 * @generated
	 */
	public Adapter createParameterComparableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.PropertyList <em>Property List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.PropertyList
	 * @generated
	 */
	public Adapter createPropertyListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Property List Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createPropertyListEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.Case <em>Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Case
	 * @generated
	 */
	public Adapter createCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RelalgAdapterFactory
