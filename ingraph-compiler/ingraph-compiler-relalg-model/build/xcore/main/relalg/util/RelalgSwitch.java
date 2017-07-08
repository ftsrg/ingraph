/**
 */
package relalg.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import relalg.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage
 * @generated
 */
public class RelalgSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RelalgPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgSwitch() {
		if (modelPackage == null) {
			modelPackage = RelalgPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RelalgPackage.RELALG_CONTAINER: {
				RelalgContainer relalgContainer = (RelalgContainer)theEObject;
				T result = caseRelalgContainer(relalgContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.RELALG_MODEL_ELEMENT: {
				RelalgModelElement relalgModelElement = (RelalgModelElement)theEObject;
				T result = caseRelalgModelElement(relalgModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseRelalgModelElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LABEL: {
				Label label = (Label)theEObject;
				T result = caseLabel(label);
				if (result == null) result = caseNamedElement(label);
				if (result == null) result = caseRelalgModelElement(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VERTEX_LABEL: {
				VertexLabel vertexLabel = (VertexLabel)theEObject;
				T result = caseVertexLabel(vertexLabel);
				if (result == null) result = caseLabel(vertexLabel);
				if (result == null) result = caseNamedElement(vertexLabel);
				if (result == null) result = caseRelalgModelElement(vertexLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_LABEL: {
				EdgeLabel edgeLabel = (EdgeLabel)theEObject;
				T result = caseEdgeLabel(edgeLabel);
				if (result == null) result = caseLabel(edgeLabel);
				if (result == null) result = caseNamedElement(edgeLabel);
				if (result == null) result = caseRelalgModelElement(edgeLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LABEL_SET: {
				LabelSet labelSet = (LabelSet)theEObject;
				T result = caseLabelSet(labelSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VERTEX_LABEL_SET: {
				VertexLabelSet vertexLabelSet = (VertexLabelSet)theEObject;
				T result = caseVertexLabelSet(vertexLabelSet);
				if (result == null) result = caseLabelSet(vertexLabelSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_LABEL_SET: {
				EdgeLabelSet edgeLabelSet = (EdgeLabelSet)theEObject;
				T result = caseEdgeLabelSet(edgeLabelSet);
				if (result == null) result = caseLabelSet(edgeLabelSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE_EXPRESSION: {
				VariableExpression variableExpression = (VariableExpression)theEObject;
				T result = caseVariableExpression(variableExpression);
				if (result == null) result = caseExpression(variableExpression);
				if (result == null) result = caseRelalgModelElement(variableExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE_COMPARABLE_EXPRESSION: {
				VariableComparableExpression variableComparableExpression = (VariableComparableExpression)theEObject;
				T result = caseVariableComparableExpression(variableComparableExpression);
				if (result == null) result = caseVariableExpression(variableComparableExpression);
				if (result == null) result = caseComparableExpression(variableComparableExpression);
				if (result == null) result = caseExpression(variableComparableExpression);
				if (result == null) result = caseRelalgModelElement(variableComparableExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE_STRING_EXPRESSION: {
				VariableStringExpression variableStringExpression = (VariableStringExpression)theEObject;
				T result = caseVariableStringExpression(variableStringExpression);
				if (result == null) result = caseVariableExpression(variableStringExpression);
				if (result == null) result = caseStringExpression(variableStringExpression);
				if (result == null) result = caseComparableExpression(variableStringExpression);
				if (result == null) result = caseExpression(variableStringExpression);
				if (result == null) result = caseRelalgModelElement(variableStringExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE_ARITHMETIC_EXPRESSION: {
				VariableArithmeticExpression variableArithmeticExpression = (VariableArithmeticExpression)theEObject;
				T result = caseVariableArithmeticExpression(variableArithmeticExpression);
				if (result == null) result = caseVariableExpression(variableArithmeticExpression);
				if (result == null) result = caseArithmeticExpression(variableArithmeticExpression);
				if (result == null) result = caseComparableExpression(variableArithmeticExpression);
				if (result == null) result = caseExpression(variableArithmeticExpression);
				if (result == null) result = caseRelalgModelElement(variableArithmeticExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE_LIST_EXPRESSION: {
				VariableListExpression variableListExpression = (VariableListExpression)theEObject;
				T result = caseVariableListExpression(variableListExpression);
				if (result == null) result = caseVariableExpression(variableListExpression);
				if (result == null) result = caseListExpression(variableListExpression);
				if (result == null) result = caseExpression(variableListExpression);
				if (result == null) result = caseRelalgModelElement(variableListExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.FUNCTION_EXPRESSION: {
				FunctionExpression functionExpression = (FunctionExpression)theEObject;
				T result = caseFunctionExpression(functionExpression);
				if (result == null) result = caseExpression(functionExpression);
				if (result == null) result = caseRelalgModelElement(functionExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.FUNCTION_COMPARABLE_EXPRESSION: {
				FunctionComparableExpression functionComparableExpression = (FunctionComparableExpression)theEObject;
				T result = caseFunctionComparableExpression(functionComparableExpression);
				if (result == null) result = caseFunctionExpression(functionComparableExpression);
				if (result == null) result = caseComparableExpression(functionComparableExpression);
				if (result == null) result = caseExpression(functionComparableExpression);
				if (result == null) result = caseRelalgModelElement(functionComparableExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.FUNCTION_ARITHMETIC_EXPRESSION: {
				FunctionArithmeticExpression functionArithmeticExpression = (FunctionArithmeticExpression)theEObject;
				T result = caseFunctionArithmeticExpression(functionArithmeticExpression);
				if (result == null) result = caseFunctionExpression(functionArithmeticExpression);
				if (result == null) result = caseArithmeticExpression(functionArithmeticExpression);
				if (result == null) result = caseComparableExpression(functionArithmeticExpression);
				if (result == null) result = caseExpression(functionArithmeticExpression);
				if (result == null) result = caseRelalgModelElement(functionArithmeticExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.FUNCTION_LOGICAL_EXPRESSION: {
				FunctionLogicalExpression functionLogicalExpression = (FunctionLogicalExpression)theEObject;
				T result = caseFunctionLogicalExpression(functionLogicalExpression);
				if (result == null) result = caseFunctionExpression(functionLogicalExpression);
				if (result == null) result = caseLogicalExpression(functionLogicalExpression);
				if (result == null) result = caseExpression(functionLogicalExpression);
				if (result == null) result = caseRelalgModelElement(functionLogicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE: {
				Variable variable = (Variable)theEObject;
				T result = caseVariable(variable);
				if (result == null) result = caseNamedElement(variable);
				if (result == null) result = caseRelalgModelElement(variable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GRAPH_OBJECT_VARIABLE: {
				GraphObjectVariable graphObjectVariable = (GraphObjectVariable)theEObject;
				T result = caseGraphObjectVariable(graphObjectVariable);
				if (result == null) result = caseVariable(graphObjectVariable);
				if (result == null) result = caseNamedElement(graphObjectVariable);
				if (result == null) result = caseRelalgModelElement(graphObjectVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ELEMENT_VARIABLE: {
				ElementVariable elementVariable = (ElementVariable)theEObject;
				T result = caseElementVariable(elementVariable);
				if (result == null) result = caseGraphObjectVariable(elementVariable);
				if (result == null) result = caseVariable(elementVariable);
				if (result == null) result = caseNamedElement(elementVariable);
				if (result == null) result = caseRelalgModelElement(elementVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VERTEX_VARIABLE: {
				VertexVariable vertexVariable = (VertexVariable)theEObject;
				T result = caseVertexVariable(vertexVariable);
				if (result == null) result = caseElementVariable(vertexVariable);
				if (result == null) result = caseGraphObjectVariable(vertexVariable);
				if (result == null) result = caseVariable(vertexVariable);
				if (result == null) result = caseNamedElement(vertexVariable);
				if (result == null) result = caseRelalgModelElement(vertexVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ABSTRACT_EDGE_VARIABLE: {
				AbstractEdgeVariable abstractEdgeVariable = (AbstractEdgeVariable)theEObject;
				T result = caseAbstractEdgeVariable(abstractEdgeVariable);
				if (result == null) result = caseElementVariable(abstractEdgeVariable);
				if (result == null) result = caseGraphObjectVariable(abstractEdgeVariable);
				if (result == null) result = caseVariable(abstractEdgeVariable);
				if (result == null) result = caseNamedElement(abstractEdgeVariable);
				if (result == null) result = caseRelalgModelElement(abstractEdgeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_VARIABLE: {
				EdgeVariable edgeVariable = (EdgeVariable)theEObject;
				T result = caseEdgeVariable(edgeVariable);
				if (result == null) result = caseAbstractEdgeVariable(edgeVariable);
				if (result == null) result = caseElementVariable(edgeVariable);
				if (result == null) result = caseGraphObjectVariable(edgeVariable);
				if (result == null) result = caseVariable(edgeVariable);
				if (result == null) result = caseNamedElement(edgeVariable);
				if (result == null) result = caseRelalgModelElement(edgeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_LIST_VARIABLE: {
				EdgeListVariable edgeListVariable = (EdgeListVariable)theEObject;
				T result = caseEdgeListVariable(edgeListVariable);
				if (result == null) result = caseAbstractEdgeVariable(edgeListVariable);
				if (result == null) result = caseElementVariable(edgeListVariable);
				if (result == null) result = caseGraphObjectVariable(edgeListVariable);
				if (result == null) result = caseVariable(edgeListVariable);
				if (result == null) result = caseNamedElement(edgeListVariable);
				if (result == null) result = caseRelalgModelElement(edgeListVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ATTRIBUTE_VARIABLE: {
				AttributeVariable attributeVariable = (AttributeVariable)theEObject;
				T result = caseAttributeVariable(attributeVariable);
				if (result == null) result = caseGraphObjectVariable(attributeVariable);
				if (result == null) result = caseVariable(attributeVariable);
				if (result == null) result = caseNamedElement(attributeVariable);
				if (result == null) result = caseRelalgModelElement(attributeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LIST_VARIABLE: {
				ListVariable listVariable = (ListVariable)theEObject;
				T result = caseListVariable(listVariable);
				if (result == null) result = caseVariable(listVariable);
				if (result == null) result = caseNamedElement(listVariable);
				if (result == null) result = caseRelalgModelElement(listVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PATH_VARIABLE: {
				PathVariable pathVariable = (PathVariable)theEObject;
				T result = casePathVariable(pathVariable);
				if (result == null) result = caseVariable(pathVariable);
				if (result == null) result = caseNamedElement(pathVariable);
				if (result == null) result = caseRelalgModelElement(pathVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EXPRESSION_VARIABLE: {
				ExpressionVariable expressionVariable = (ExpressionVariable)theEObject;
				T result = caseExpressionVariable(expressionVariable);
				if (result == null) result = caseVariable(expressionVariable);
				if (result == null) result = caseNamedElement(expressionVariable);
				if (result == null) result = caseRelalgModelElement(expressionVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.OPERATOR: {
				Operator operator = (Operator)theEObject;
				T result = caseOperator(operator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CARDINALITY: {
				Cardinality cardinality = (Cardinality)theEObject;
				T result = caseCardinality(cardinality);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NULLARY_OPERATOR: {
				NullaryOperator nullaryOperator = (NullaryOperator)theEObject;
				T result = caseNullaryOperator(nullaryOperator);
				if (result == null) result = caseOperator(nullaryOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GET_VERTICES_OPERATOR: {
				GetVerticesOperator getVerticesOperator = (GetVerticesOperator)theEObject;
				T result = caseGetVerticesOperator(getVerticesOperator);
				if (result == null) result = caseNullaryOperator(getVerticesOperator);
				if (result == null) result = caseOperator(getVerticesOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SINGULAR_OBJECT_SOURCE_OPERATOR: {
				SingularObjectSourceOperator singularObjectSourceOperator = (SingularObjectSourceOperator)theEObject;
				T result = caseSingularObjectSourceOperator(singularObjectSourceOperator);
				if (result == null) result = caseNullaryOperator(singularObjectSourceOperator);
				if (result == null) result = caseOperator(singularObjectSourceOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DUAL_OBJECT_SOURCE_OPERATOR: {
				DualObjectSourceOperator dualObjectSourceOperator = (DualObjectSourceOperator)theEObject;
				T result = caseDualObjectSourceOperator(dualObjectSourceOperator);
				if (result == null) result = caseNullaryOperator(dualObjectSourceOperator);
				if (result == null) result = caseOperator(dualObjectSourceOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GET_EDGES_OPERATOR: {
				GetEdgesOperator getEdgesOperator = (GetEdgesOperator)theEObject;
				T result = caseGetEdgesOperator(getEdgesOperator);
				if (result == null) result = caseNullaryOperator(getEdgesOperator);
				if (result == null) result = caseNavigationDescriptor(getEdgesOperator);
				if (result == null) result = caseOperator(getEdgesOperator);
				if (result == null) result = caseExpression(getEdgesOperator);
				if (result == null) result = caseRelalgModelElement(getEdgesOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_OPERATOR: {
				UnaryOperator unaryOperator = (UnaryOperator)theEObject;
				T result = caseUnaryOperator(unaryOperator);
				if (result == null) result = caseOperator(unaryOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BEAMER_OPERATOR: {
				BeamerOperator beamerOperator = (BeamerOperator)theEObject;
				T result = caseBeamerOperator(beamerOperator);
				if (result == null) result = caseUnaryOperator(beamerOperator);
				if (result == null) result = caseOperator(beamerOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PRODUCTION_OPERATOR: {
				ProductionOperator productionOperator = (ProductionOperator)theEObject;
				T result = caseProductionOperator(productionOperator);
				if (result == null) result = caseBeamerOperator(productionOperator);
				if (result == null) result = caseUnaryOperator(productionOperator);
				if (result == null) result = caseOperator(productionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ABSTRACT_CONDITION: {
				AbstractCondition abstractCondition = (AbstractCondition)theEObject;
				T result = caseAbstractCondition(abstractCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SELECTION_OPERATOR: {
				SelectionOperator selectionOperator = (SelectionOperator)theEObject;
				T result = caseSelectionOperator(selectionOperator);
				if (result == null) result = caseUnaryOperator(selectionOperator);
				if (result == null) result = caseAbstractCondition(selectionOperator);
				if (result == null) result = caseOperator(selectionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PROJECTION_OPERATOR: {
				ProjectionOperator projectionOperator = (ProjectionOperator)theEObject;
				T result = caseProjectionOperator(projectionOperator);
				if (result == null) result = caseBeamerOperator(projectionOperator);
				if (result == null) result = caseUnaryOperator(projectionOperator);
				if (result == null) result = caseOperator(projectionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GROUPING_OPERATOR: {
				GroupingOperator groupingOperator = (GroupingOperator)theEObject;
				T result = caseGroupingOperator(groupingOperator);
				if (result == null) result = caseProjectionOperator(groupingOperator);
				if (result == null) result = caseBeamerOperator(groupingOperator);
				if (result == null) result = caseUnaryOperator(groupingOperator);
				if (result == null) result = caseOperator(groupingOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CUD_OPERATOR: {
				CUDOperator cudOperator = (CUDOperator)theEObject;
				T result = caseCUDOperator(cudOperator);
				if (result == null) result = caseUnaryOperator(cudOperator);
				if (result == null) result = caseOperator(cudOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CREATE_OPERATOR: {
				CreateOperator createOperator = (CreateOperator)theEObject;
				T result = caseCreateOperator(createOperator);
				if (result == null) result = caseCUDOperator(createOperator);
				if (result == null) result = caseUnaryOperator(createOperator);
				if (result == null) result = caseOperator(createOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DELETE_OPERATOR: {
				DeleteOperator deleteOperator = (DeleteOperator)theEObject;
				T result = caseDeleteOperator(deleteOperator);
				if (result == null) result = caseCUDOperator(deleteOperator);
				if (result == null) result = caseUnaryOperator(deleteOperator);
				if (result == null) result = caseOperator(deleteOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NAVIGATION_DESCRIPTOR: {
				NavigationDescriptor navigationDescriptor = (NavigationDescriptor)theEObject;
				T result = caseNavigationDescriptor(navigationDescriptor);
				if (result == null) result = caseExpression(navigationDescriptor);
				if (result == null) result = caseRelalgModelElement(navigationDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.MAX_HOPS: {
				MaxHops maxHops = (MaxHops)theEObject;
				T result = caseMaxHops(maxHops);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EXPAND_OPERATOR: {
				ExpandOperator expandOperator = (ExpandOperator)theEObject;
				T result = caseExpandOperator(expandOperator);
				if (result == null) result = caseUnaryOperator(expandOperator);
				if (result == null) result = caseNavigationDescriptor(expandOperator);
				if (result == null) result = caseOperator(expandOperator);
				if (result == null) result = caseExpression(expandOperator);
				if (result == null) result = caseRelalgModelElement(expandOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PATH_OPERATOR: {
				PathOperator pathOperator = (PathOperator)theEObject;
				T result = casePathOperator(pathOperator);
				if (result == null) result = caseExpandOperator(pathOperator);
				if (result == null) result = caseUnaryOperator(pathOperator);
				if (result == null) result = caseNavigationDescriptor(pathOperator);
				if (result == null) result = caseOperator(pathOperator);
				if (result == null) result = caseExpression(pathOperator);
				if (result == null) result = caseRelalgModelElement(pathOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DUPLICATE_ELIMINATION_OPERATOR: {
				DuplicateEliminationOperator duplicateEliminationOperator = (DuplicateEliminationOperator)theEObject;
				T result = caseDuplicateEliminationOperator(duplicateEliminationOperator);
				if (result == null) result = caseUnaryOperator(duplicateEliminationOperator);
				if (result == null) result = caseOperator(duplicateEliminationOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ALL_DIFFERENT_OPERATOR: {
				AllDifferentOperator allDifferentOperator = (AllDifferentOperator)theEObject;
				T result = caseAllDifferentOperator(allDifferentOperator);
				if (result == null) result = caseUnaryOperator(allDifferentOperator);
				if (result == null) result = caseOperator(allDifferentOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SORT_OPERATOR: {
				SortOperator sortOperator = (SortOperator)theEObject;
				T result = caseSortOperator(sortOperator);
				if (result == null) result = caseUnaryOperator(sortOperator);
				if (result == null) result = caseOperator(sortOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SORT_ENTRY: {
				SortEntry sortEntry = (SortEntry)theEObject;
				T result = caseSortEntry(sortEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.TOP_OPERATOR: {
				TopOperator topOperator = (TopOperator)theEObject;
				T result = caseTopOperator(topOperator);
				if (result == null) result = caseUnaryOperator(topOperator);
				if (result == null) result = caseOperator(topOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SORT_AND_TOP_OPERATOR: {
				SortAndTopOperator sortAndTopOperator = (SortAndTopOperator)theEObject;
				T result = caseSortAndTopOperator(sortAndTopOperator);
				if (result == null) result = caseSortOperator(sortAndTopOperator);
				if (result == null) result = caseTopOperator(sortAndTopOperator);
				if (result == null) result = caseUnaryOperator(sortAndTopOperator);
				if (result == null) result = caseOperator(sortAndTopOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNWIND_OPERATOR: {
				UnwindOperator unwindOperator = (UnwindOperator)theEObject;
				T result = caseUnwindOperator(unwindOperator);
				if (result == null) result = caseUnaryOperator(unwindOperator);
				if (result == null) result = caseOperator(unwindOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_OPERATOR: {
				BinaryOperator binaryOperator = (BinaryOperator)theEObject;
				T result = caseBinaryOperator(binaryOperator);
				if (result == null) result = caseOperator(binaryOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.COMMUTATIVE_BINARY_OPERATOR: {
				CommutativeBinaryOperator commutativeBinaryOperator = (CommutativeBinaryOperator)theEObject;
				T result = caseCommutativeBinaryOperator(commutativeBinaryOperator);
				if (result == null) result = caseBinaryOperator(commutativeBinaryOperator);
				if (result == null) result = caseOperator(commutativeBinaryOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ASSOCIATIVE_BINARY_OPERATOR: {
				AssociativeBinaryOperator associativeBinaryOperator = (AssociativeBinaryOperator)theEObject;
				T result = caseAssociativeBinaryOperator(associativeBinaryOperator);
				if (result == null) result = caseBinaryOperator(associativeBinaryOperator);
				if (result == null) result = caseOperator(associativeBinaryOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ABSTRACT_JOIN_OPERATOR: {
				AbstractJoinOperator abstractJoinOperator = (AbstractJoinOperator)theEObject;
				T result = caseAbstractJoinOperator(abstractJoinOperator);
				if (result == null) result = caseBinaryOperator(abstractJoinOperator);
				if (result == null) result = caseOperator(abstractJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EQUI_JOIN_LIKE_OPERATOR: {
				EquiJoinLikeOperator equiJoinLikeOperator = (EquiJoinLikeOperator)theEObject;
				T result = caseEquiJoinLikeOperator(equiJoinLikeOperator);
				if (result == null) result = caseAbstractJoinOperator(equiJoinLikeOperator);
				if (result == null) result = caseBinaryOperator(equiJoinLikeOperator);
				if (result == null) result = caseOperator(equiJoinLikeOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LEFT_OUTER_JOIN_OPERATOR: {
				LeftOuterJoinOperator leftOuterJoinOperator = (LeftOuterJoinOperator)theEObject;
				T result = caseLeftOuterJoinOperator(leftOuterJoinOperator);
				if (result == null) result = caseEquiJoinLikeOperator(leftOuterJoinOperator);
				if (result == null) result = caseAbstractJoinOperator(leftOuterJoinOperator);
				if (result == null) result = caseBinaryOperator(leftOuterJoinOperator);
				if (result == null) result = caseOperator(leftOuterJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR: {
				ThetaLeftOuterJoinOperator thetaLeftOuterJoinOperator = (ThetaLeftOuterJoinOperator)theEObject;
				T result = caseThetaLeftOuterJoinOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = caseLeftOuterJoinOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = caseAbstractCondition(thetaLeftOuterJoinOperator);
				if (result == null) result = caseEquiJoinLikeOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = caseAbstractJoinOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = caseBinaryOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = caseOperator(thetaLeftOuterJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.JOIN_OPERATOR: {
				JoinOperator joinOperator = (JoinOperator)theEObject;
				T result = caseJoinOperator(joinOperator);
				if (result == null) result = caseEquiJoinLikeOperator(joinOperator);
				if (result == null) result = caseCommutativeBinaryOperator(joinOperator);
				if (result == null) result = caseAssociativeBinaryOperator(joinOperator);
				if (result == null) result = caseAbstractJoinOperator(joinOperator);
				if (result == null) result = caseBinaryOperator(joinOperator);
				if (result == null) result = caseOperator(joinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.TRANSITIVE_CLOSURE_JOIN_OPERATOR: {
				TransitiveClosureJoinOperator transitiveClosureJoinOperator = (TransitiveClosureJoinOperator)theEObject;
				T result = caseTransitiveClosureJoinOperator(transitiveClosureJoinOperator);
				if (result == null) result = caseEquiJoinLikeOperator(transitiveClosureJoinOperator);
				if (result == null) result = caseAbstractJoinOperator(transitiveClosureJoinOperator);
				if (result == null) result = caseBinaryOperator(transitiveClosureJoinOperator);
				if (result == null) result = caseOperator(transitiveClosureJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ANTI_JOIN_OPERATOR: {
				AntiJoinOperator antiJoinOperator = (AntiJoinOperator)theEObject;
				T result = caseAntiJoinOperator(antiJoinOperator);
				if (result == null) result = caseAbstractJoinOperator(antiJoinOperator);
				if (result == null) result = caseBinaryOperator(antiJoinOperator);
				if (result == null) result = caseOperator(antiJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNION_OPERATOR: {
				UnionOperator unionOperator = (UnionOperator)theEObject;
				T result = caseUnionOperator(unionOperator);
				if (result == null) result = caseCommutativeBinaryOperator(unionOperator);
				if (result == null) result = caseAssociativeBinaryOperator(unionOperator);
				if (result == null) result = caseBinaryOperator(unionOperator);
				if (result == null) result = caseOperator(unionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = caseRelalgModelElement(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CASE_EXPRESSION: {
				CaseExpression caseExpression = (CaseExpression)theEObject;
				T result = caseCaseExpression(caseExpression);
				if (result == null) result = caseExpression(caseExpression);
				if (result == null) result = caseRelalgModelElement(caseExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GENERIC_CASE_EXPRESSION: {
				GenericCaseExpression genericCaseExpression = (GenericCaseExpression)theEObject;
				T result = caseGenericCaseExpression(genericCaseExpression);
				if (result == null) result = caseCaseExpression(genericCaseExpression);
				if (result == null) result = caseExpression(genericCaseExpression);
				if (result == null) result = caseRelalgModelElement(genericCaseExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SIMPLE_CASE_EXPRESSION: {
				SimpleCaseExpression simpleCaseExpression = (SimpleCaseExpression)theEObject;
				T result = caseSimpleCaseExpression(simpleCaseExpression);
				if (result == null) result = caseCaseExpression(simpleCaseExpression);
				if (result == null) result = caseExpression(simpleCaseExpression);
				if (result == null) result = caseRelalgModelElement(simpleCaseExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ARITHMETIC_EXPRESSION: {
				ArithmeticExpression arithmeticExpression = (ArithmeticExpression)theEObject;
				T result = caseArithmeticExpression(arithmeticExpression);
				if (result == null) result = caseComparableExpression(arithmeticExpression);
				if (result == null) result = caseExpression(arithmeticExpression);
				if (result == null) result = caseRelalgModelElement(arithmeticExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_EXPRESSION: {
				UnaryExpression unaryExpression = (UnaryExpression)theEObject;
				T result = caseUnaryExpression(unaryExpression);
				if (result == null) result = caseExpression(unaryExpression);
				if (result == null) result = caseRelalgModelElement(unaryExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_EXPRESSION: {
				BinaryExpression binaryExpression = (BinaryExpression)theEObject;
				T result = caseBinaryExpression(binaryExpression);
				if (result == null) result = caseExpression(binaryExpression);
				if (result == null) result = caseRelalgModelElement(binaryExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_ARITHMETIC_OPERATION_EXPRESSION: {
				UnaryArithmeticOperationExpression unaryArithmeticOperationExpression = (UnaryArithmeticOperationExpression)theEObject;
				T result = caseUnaryArithmeticOperationExpression(unaryArithmeticOperationExpression);
				if (result == null) result = caseUnaryExpression(unaryArithmeticOperationExpression);
				if (result == null) result = caseArithmeticExpression(unaryArithmeticOperationExpression);
				if (result == null) result = caseComparableExpression(unaryArithmeticOperationExpression);
				if (result == null) result = caseExpression(unaryArithmeticOperationExpression);
				if (result == null) result = caseRelalgModelElement(unaryArithmeticOperationExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_ARITHMETIC_OPERATION_EXPRESSION: {
				BinaryArithmeticOperationExpression binaryArithmeticOperationExpression = (BinaryArithmeticOperationExpression)theEObject;
				T result = caseBinaryArithmeticOperationExpression(binaryArithmeticOperationExpression);
				if (result == null) result = caseBinaryExpression(binaryArithmeticOperationExpression);
				if (result == null) result = caseArithmeticExpression(binaryArithmeticOperationExpression);
				if (result == null) result = caseComparableExpression(binaryArithmeticOperationExpression);
				if (result == null) result = caseExpression(binaryArithmeticOperationExpression);
				if (result == null) result = caseRelalgModelElement(binaryArithmeticOperationExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ARITHMETIC_COMPARISON_EXPRESSION: {
				ArithmeticComparisonExpression arithmeticComparisonExpression = (ArithmeticComparisonExpression)theEObject;
				T result = caseArithmeticComparisonExpression(arithmeticComparisonExpression);
				if (result == null) result = caseBinaryExpression(arithmeticComparisonExpression);
				if (result == null) result = caseLogicalExpression(arithmeticComparisonExpression);
				if (result == null) result = caseExpression(arithmeticComparisonExpression);
				if (result == null) result = caseRelalgModelElement(arithmeticComparisonExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.STRING_EXPRESSION: {
				StringExpression stringExpression = (StringExpression)theEObject;
				T result = caseStringExpression(stringExpression);
				if (result == null) result = caseComparableExpression(stringExpression);
				if (result == null) result = caseExpression(stringExpression);
				if (result == null) result = caseRelalgModelElement(stringExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LOGICAL_EXPRESSION: {
				LogicalExpression logicalExpression = (LogicalExpression)theEObject;
				T result = caseLogicalExpression(logicalExpression);
				if (result == null) result = caseExpression(logicalExpression);
				if (result == null) result = caseRelalgModelElement(logicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LIST_EXPRESSION: {
				ListExpression listExpression = (ListExpression)theEObject;
				T result = caseListExpression(listExpression);
				if (result == null) result = caseExpression(listExpression);
				if (result == null) result = caseRelalgModelElement(listExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EMPTY_LIST_EXPRESSION: {
				EmptyListExpression emptyListExpression = (EmptyListExpression)theEObject;
				T result = caseEmptyListExpression(emptyListExpression);
				if (result == null) result = caseListExpression(emptyListExpression);
				if (result == null) result = caseExpression(emptyListExpression);
				if (result == null) result = caseRelalgModelElement(emptyListExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INDEX_ACCESS_EXPRESSION: {
				IndexAccessExpression indexAccessExpression = (IndexAccessExpression)theEObject;
				T result = caseIndexAccessExpression(indexAccessExpression);
				if (result == null) result = caseExpression(indexAccessExpression);
				if (result == null) result = caseRelalgModelElement(indexAccessExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INDEX_SIMPLE_ACCESS_EXPRESSION: {
				IndexSimpleAccessExpression indexSimpleAccessExpression = (IndexSimpleAccessExpression)theEObject;
				T result = caseIndexSimpleAccessExpression(indexSimpleAccessExpression);
				if (result == null) result = caseIndexAccessExpression(indexSimpleAccessExpression);
				if (result == null) result = caseExpression(indexSimpleAccessExpression);
				if (result == null) result = caseRelalgModelElement(indexSimpleAccessExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INDEX_RANGE_ACCESS_EXPRESSION: {
				IndexRangeAccessExpression indexRangeAccessExpression = (IndexRangeAccessExpression)theEObject;
				T result = caseIndexRangeAccessExpression(indexRangeAccessExpression);
				if (result == null) result = caseIndexAccessExpression(indexRangeAccessExpression);
				if (result == null) result = caseExpression(indexRangeAccessExpression);
				if (result == null) result = caseRelalgModelElement(indexRangeAccessExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION: {
				BinaryLogicalExpression binaryLogicalExpression = (BinaryLogicalExpression)theEObject;
				T result = caseBinaryLogicalExpression(binaryLogicalExpression);
				if (result == null) result = caseBinaryExpression(binaryLogicalExpression);
				if (result == null) result = caseLogicalExpression(binaryLogicalExpression);
				if (result == null) result = caseExpression(binaryLogicalExpression);
				if (result == null) result = caseRelalgModelElement(binaryLogicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_LOGICAL_EXPRESSION: {
				UnaryLogicalExpression unaryLogicalExpression = (UnaryLogicalExpression)theEObject;
				T result = caseUnaryLogicalExpression(unaryLogicalExpression);
				if (result == null) result = caseUnaryExpression(unaryLogicalExpression);
				if (result == null) result = caseLogicalExpression(unaryLogicalExpression);
				if (result == null) result = caseExpression(unaryLogicalExpression);
				if (result == null) result = caseRelalgModelElement(unaryLogicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION: {
				UnaryGraphObjectLogicalExpression unaryGraphObjectLogicalExpression = (UnaryGraphObjectLogicalExpression)theEObject;
				T result = caseUnaryGraphObjectLogicalExpression(unaryGraphObjectLogicalExpression);
				if (result == null) result = caseUnaryExpression(unaryGraphObjectLogicalExpression);
				if (result == null) result = caseLogicalExpression(unaryGraphObjectLogicalExpression);
				if (result == null) result = caseExpression(unaryGraphObjectLogicalExpression);
				if (result == null) result = caseRelalgModelElement(unaryGraphObjectLogicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.COMPARABLE_EXPRESSION: {
				ComparableExpression comparableExpression = (ComparableExpression)theEObject;
				T result = caseComparableExpression(comparableExpression);
				if (result == null) result = caseExpression(comparableExpression);
				if (result == null) result = caseRelalgModelElement(comparableExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ATOM: {
				Atom atom = (Atom)theEObject;
				T result = caseAtom(atom);
				if (result == null) result = caseExpression(atom);
				if (result == null) result = caseRelalgModelElement(atom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LITERAL: {
				Literal literal = (Literal)theEObject;
				T result = caseLiteral(literal);
				if (result == null) result = caseAtom(literal);
				if (result == null) result = caseExpression(literal);
				if (result == null) result = caseRelalgModelElement(literal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BOOLEAN_LITERAL: {
				BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
				T result = caseBooleanLiteral(booleanLiteral);
				if (result == null) result = caseLiteral(booleanLiteral);
				if (result == null) result = caseLogicalExpression(booleanLiteral);
				if (result == null) result = caseAtom(booleanLiteral);
				if (result == null) result = caseExpression(booleanLiteral);
				if (result == null) result = caseRelalgModelElement(booleanLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.STRING_LITERAL: {
				StringLiteral stringLiteral = (StringLiteral)theEObject;
				T result = caseStringLiteral(stringLiteral);
				if (result == null) result = caseLiteral(stringLiteral);
				if (result == null) result = caseStringExpression(stringLiteral);
				if (result == null) result = caseAtom(stringLiteral);
				if (result == null) result = caseComparableExpression(stringLiteral);
				if (result == null) result = caseExpression(stringLiteral);
				if (result == null) result = caseRelalgModelElement(stringLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NUMBER_LITERAL: {
				NumberLiteral numberLiteral = (NumberLiteral)theEObject;
				T result = caseNumberLiteral(numberLiteral);
				if (result == null) result = caseLiteral(numberLiteral);
				if (result == null) result = caseArithmeticExpression(numberLiteral);
				if (result == null) result = caseAtom(numberLiteral);
				if (result == null) result = caseComparableExpression(numberLiteral);
				if (result == null) result = caseExpression(numberLiteral);
				if (result == null) result = caseRelalgModelElement(numberLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DOUBLE_LITERAL: {
				DoubleLiteral doubleLiteral = (DoubleLiteral)theEObject;
				T result = caseDoubleLiteral(doubleLiteral);
				if (result == null) result = caseNumberLiteral(doubleLiteral);
				if (result == null) result = caseLiteral(doubleLiteral);
				if (result == null) result = caseArithmeticExpression(doubleLiteral);
				if (result == null) result = caseAtom(doubleLiteral);
				if (result == null) result = caseComparableExpression(doubleLiteral);
				if (result == null) result = caseExpression(doubleLiteral);
				if (result == null) result = caseRelalgModelElement(doubleLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BIG_INTEGER_LITERAL: {
				BigIntegerLiteral bigIntegerLiteral = (BigIntegerLiteral)theEObject;
				T result = caseBigIntegerLiteral(bigIntegerLiteral);
				if (result == null) result = caseNumberLiteral(bigIntegerLiteral);
				if (result == null) result = caseLiteral(bigIntegerLiteral);
				if (result == null) result = caseArithmeticExpression(bigIntegerLiteral);
				if (result == null) result = caseAtom(bigIntegerLiteral);
				if (result == null) result = caseComparableExpression(bigIntegerLiteral);
				if (result == null) result = caseExpression(bigIntegerLiteral);
				if (result == null) result = caseRelalgModelElement(bigIntegerLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INTEGER_LITERAL: {
				IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
				T result = caseIntegerLiteral(integerLiteral);
				if (result == null) result = caseNumberLiteral(integerLiteral);
				if (result == null) result = caseLiteral(integerLiteral);
				if (result == null) result = caseArithmeticExpression(integerLiteral);
				if (result == null) result = caseAtom(integerLiteral);
				if (result == null) result = caseComparableExpression(integerLiteral);
				if (result == null) result = caseExpression(integerLiteral);
				if (result == null) result = caseRelalgModelElement(integerLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NULL_LITERAL: {
				NullLiteral nullLiteral = (NullLiteral)theEObject;
				T result = caseNullLiteral(nullLiteral);
				if (result == null) result = caseNumberLiteral(nullLiteral);
				if (result == null) result = caseStringLiteral(nullLiteral);
				if (result == null) result = caseLiteral(nullLiteral);
				if (result == null) result = caseArithmeticExpression(nullLiteral);
				if (result == null) result = caseStringExpression(nullLiteral);
				if (result == null) result = caseAtom(nullLiteral);
				if (result == null) result = caseComparableExpression(nullLiteral);
				if (result == null) result = caseExpression(nullLiteral);
				if (result == null) result = caseRelalgModelElement(nullLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = caseNamedElement(parameter);
				if (result == null) result = caseAtom(parameter);
				if (result == null) result = caseExpression(parameter);
				if (result == null) result = caseRelalgModelElement(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PARAMETER_COMPARABLE_EXPRESSION: {
				ParameterComparableExpression parameterComparableExpression = (ParameterComparableExpression)theEObject;
				T result = caseParameterComparableExpression(parameterComparableExpression);
				if (result == null) result = caseComparableExpression(parameterComparableExpression);
				if (result == null) result = caseExpression(parameterComparableExpression);
				if (result == null) result = caseRelalgModelElement(parameterComparableExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PROPERTY_LIST: {
				PropertyList propertyList = (PropertyList)theEObject;
				T result = casePropertyList(propertyList);
				if (result == null) result = caseExpression(propertyList);
				if (result == null) result = caseRelalgModelElement(propertyList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PROPERTY_LIST_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Expression> propertyListEntry = (Map.Entry<String, Expression>)theEObject;
				T result = casePropertyListEntry(propertyListEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CASE: {
				Case case_ = (Case)theEObject;
				T result = caseCase(case_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelalgContainer(RelalgContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelalgModelElement(RelalgModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabel(Label object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVertexLabel(VertexLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeLabel(EdgeLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelSet(LabelSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex Label Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex Label Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVertexLabelSet(VertexLabelSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge Label Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge Label Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeLabelSet(EdgeLabelSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableExpression(VariableExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Comparable Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableComparableExpression(VariableComparableExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable String Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableStringExpression(VariableStringExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Arithmetic Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Arithmetic Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableArithmeticExpression(VariableArithmeticExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable List Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableListExpression(VariableListExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionExpression(FunctionExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Comparable Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionComparableExpression(FunctionComparableExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Arithmetic Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Arithmetic Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionArithmeticExpression(FunctionArithmeticExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Logical Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionLogicalExpression(FunctionLogicalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariable(Variable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Object Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Object Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphObjectVariable(GraphObjectVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementVariable(ElementVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVertexVariable(VertexVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Edge Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Edge Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEdgeVariable(AbstractEdgeVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeVariable(EdgeVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge List Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge List Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeListVariable(EdgeListVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeVariable(AttributeVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListVariable(ListVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathVariable(PathVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpressionVariable(ExpressionVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperator(Operator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cardinality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cardinality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCardinality(Cardinality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nullary Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nullary Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNullaryOperator(NullaryOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get Vertices Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get Vertices Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetVerticesOperator(GetVerticesOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Singular Object Source Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Singular Object Source Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingularObjectSourceOperator(SingularObjectSourceOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dual Object Source Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dual Object Source Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDualObjectSourceOperator(DualObjectSourceOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get Edges Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get Edges Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetEdgesOperator(GetEdgesOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryOperator(UnaryOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Beamer Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Beamer Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBeamerOperator(BeamerOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Production Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Production Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductionOperator(ProductionOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractCondition(AbstractCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Selection Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Selection Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelectionOperator(SelectionOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Projection Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Projection Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProjectionOperator(ProjectionOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Grouping Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Grouping Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupingOperator(GroupingOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CUD Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CUD Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCUDOperator(CUDOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Create Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateOperator(CreateOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delete Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delete Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeleteOperator(DeleteOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationDescriptor(NavigationDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Max Hops</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Max Hops</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMaxHops(MaxHops object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expand Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expand Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpandOperator(ExpandOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathOperator(PathOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Duplicate Elimination Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Duplicate Elimination Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDuplicateEliminationOperator(DuplicateEliminationOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All Different Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Different Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAllDifferentOperator(AllDifferentOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sort Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sort Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSortOperator(SortOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sort Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sort Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSortEntry(SortEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Top Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Top Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTopOperator(TopOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sort And Top Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sort And Top Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSortAndTopOperator(SortAndTopOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unwind Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unwind Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnwindOperator(UnwindOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryOperator(BinaryOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Commutative Binary Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Commutative Binary Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommutativeBinaryOperator(CommutativeBinaryOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Associative Binary Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Associative Binary Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociativeBinaryOperator(AssociativeBinaryOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractJoinOperator(AbstractJoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equi Join Like Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equi Join Like Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquiJoinLikeOperator(EquiJoinLikeOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Left Outer Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Left Outer Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLeftOuterJoinOperator(LeftOuterJoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Theta Left Outer Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Theta Left Outer Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThetaLeftOuterJoinOperator(ThetaLeftOuterJoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoinOperator(JoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transitive Closure Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transitive Closure Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitiveClosureJoinOperator(TransitiveClosureJoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anti Join Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anti Join Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAntiJoinOperator(AntiJoinOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionOperator(UnionOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Case Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Case Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCaseExpression(CaseExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Case Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Case Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericCaseExpression(GenericCaseExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Case Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Case Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimpleCaseExpression(SimpleCaseExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arithmetic Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arithmetic Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArithmeticExpression(ArithmeticExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryExpression(UnaryExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryExpression(BinaryExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Arithmetic Operation Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryArithmeticOperationExpression(UnaryArithmeticOperationExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Arithmetic Operation Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryArithmeticOperationExpression(BinaryArithmeticOperationExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arithmetic Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arithmetic Comparison Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArithmeticComparisonExpression(ArithmeticComparisonExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringExpression(StringExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logical Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogicalExpression(LogicalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListExpression(ListExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Empty List Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Empty List Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEmptyListExpression(EmptyListExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Access Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Access Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIndexAccessExpression(IndexAccessExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Simple Access Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Simple Access Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIndexSimpleAccessExpression(IndexSimpleAccessExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Range Access Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Range Access Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIndexRangeAccessExpression(IndexRangeAccessExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Logical Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryLogicalExpression(BinaryLogicalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Logical Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryLogicalExpression(UnaryLogicalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Graph Object Logical Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Graph Object Logical Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryGraphObjectLogicalExpression(UnaryGraphObjectLogicalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparable Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparableExpression(ComparableExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Atom</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Atom</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAtom(Atom object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteral(Literal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanLiteral(BooleanLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringLiteral(StringLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberLiteral(NumberLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleLiteral(DoubleLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Big Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Big Integer Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBigIntegerLiteral(BigIntegerLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerLiteral(IntegerLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Null Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Null Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNullLiteral(NullLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Comparable Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Comparable Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterComparableExpression(ParameterComparableExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyList(PropertyList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property List Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property List Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyListEntry(Map.Entry<String, Expression> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCase(Case object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //RelalgSwitch
