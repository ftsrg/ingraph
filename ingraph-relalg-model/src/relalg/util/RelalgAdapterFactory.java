/**
 */
package relalg.util;

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
			public Adapter caseAlgebraExpression(AlgebraExpression object) {
				return createAlgebraExpressionAdapter();
			}
			@Override
			public Adapter caseProjectionOperator(ProjectionOperator object) {
				return createProjectionOperatorAdapter();
			}
			@Override
			public Adapter caseJoinOperator(JoinOperator object) {
				return createJoinOperatorAdapter();
			}
			@Override
			public Adapter caseAlphaOperator(AlphaOperator object) {
				return createAlphaOperatorAdapter();
			}
			@Override
			public Adapter caseBetaOperator(BetaOperator object) {
				return createBetaOperatorAdapter();
			}
			@Override
			public Adapter caseAntiJoinOperator(AntiJoinOperator object) {
				return createAntiJoinOperatorAdapter();
			}
			@Override
			public Adapter caseProductionOperator(ProductionOperator object) {
				return createProductionOperatorAdapter();
			}
			@Override
			public Adapter caseFilterOperator(FilterOperator object) {
				return createFilterOperatorAdapter();
			}
			@Override
			public Adapter caseExpandOperator(ExpandOperator object) {
				return createExpandOperatorAdapter();
			}
			@Override
			public Adapter caseGetVerticesOperator(GetVerticesOperator object) {
				return createGetVerticesOperatorAdapter();
			}
			@Override
			public Adapter caseDuplicateEliminationOperator(DuplicateEliminationOperator object) {
				return createDuplicateEliminationOperatorAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseVertexVariable(VertexVariable object) {
				return createVertexVariableAdapter();
			}
			@Override
			public Adapter caseEdgeVariable(EdgeVariable object) {
				return createEdgeVariableAdapter();
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
			public Adapter caseAllDifferentOperator(AllDifferentOperator object) {
				return createAllDifferentOperatorAdapter();
			}
			@Override
			public Adapter caseAttributeVariable(AttributeVariable object) {
				return createAttributeVariableAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseUnionOperator(UnionOperator object) {
				return createUnionOperatorAdapter();
			}
			@Override
			public Adapter caseContainer(Container object) {
				return createContainerAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseReturnableElement(ReturnableElement object) {
				return createReturnableElementAdapter();
			}
			@Override
			public Adapter caseBinaryExpression(BinaryExpression object) {
				return createBinaryExpressionAdapter();
			}
			@Override
			public Adapter caseArithmeticOperationExpression(ArithmeticOperationExpression object) {
				return createArithmeticOperationExpressionAdapter();
			}
			@Override
			public Adapter caseBinaryLogicalExpression(BinaryLogicalExpression object) {
				return createBinaryLogicalExpressionAdapter();
			}
			@Override
			public Adapter caseArithmeticComparisonExpression(ArithmeticComparisonExpression object) {
				return createArithmeticComparisonExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryExpression(UnaryExpression object) {
				return createUnaryExpressionAdapter();
			}
			@Override
			public Adapter caseStringComparisonExpression(StringComparisonExpression object) {
				return createStringComparisonExpressionAdapter();
			}
			@Override
			public Adapter caseAtom(Atom object) {
				return createAtomAdapter();
			}
			@Override
			public Adapter caseNumberLiteral(NumberLiteral object) {
				return createNumberLiteralAdapter();
			}
			@Override
			public Adapter caseStringLiteral(StringLiteral object) {
				return createStringLiteralAdapter();
			}
			@Override
			public Adapter caseDoubleLiteral(DoubleLiteral object) {
				return createDoubleLiteralAdapter();
			}
			@Override
			public Adapter caseIntegerLiteral(IntegerLiteral object) {
				return createIntegerLiteralAdapter();
			}
			@Override
			public Adapter caseGetEdgesOperator(GetEdgesOperator object) {
				return createGetEdgesOperatorAdapter();
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
	 * Creates a new adapter for an object of class '{@link relalg.AlgebraExpression <em>Algebra Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AlgebraExpression
	 * @generated
	 */
	public Adapter createAlgebraExpressionAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.AlphaOperator <em>Alpha Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.AlphaOperator
	 * @generated
	 */
	public Adapter createAlphaOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link relalg.BetaOperator <em>Beta Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.BetaOperator
	 * @generated
	 */
	public Adapter createBetaOperatorAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.FilterOperator <em>Filter Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.FilterOperator
	 * @generated
	 */
	public Adapter createFilterOperatorAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.Container
	 * @generated
	 */
	public Adapter createContainerAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.ReturnableElement <em>Returnable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ReturnableElement
	 * @generated
	 */
	public Adapter createReturnableElementAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.ArithmeticOperationExpression <em>Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.ArithmeticOperationExpression
	 * @generated
	 */
	public Adapter createArithmeticOperationExpressionAdapter() {
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
	 * Creates a new adapter for an object of class '{@link relalg.StringComparisonExpression <em>String Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see relalg.StringComparisonExpression
	 * @generated
	 */
	public Adapter createStringComparisonExpressionAdapter() {
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
