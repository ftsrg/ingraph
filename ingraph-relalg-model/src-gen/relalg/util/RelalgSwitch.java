/**
 */
package relalg.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import relalg.AlgebraExpression;
import relalg.AllDifferentOperator;
import relalg.AlphaOperator;
import relalg.AntiJoinOperator;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticOperationExpression;
import relalg.Atom;
import relalg.AttributeVariable;
import relalg.BetaOperator;
import relalg.BinaryExpression;
import relalg.BinaryLogicalExpression;
import relalg.ComparisonExpression;
import relalg.Container;
import relalg.DoubleLiteral;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeLabel;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.IntegerLiteral;
import relalg.JoinOperator;
import relalg.Label;
import relalg.Literal;
import relalg.NamedElement;
import relalg.NumberLiteral;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.RelalgPackage;
import relalg.ReturnableElement;
import relalg.SelectionOperator;
import relalg.StringComparisonExpression;
import relalg.StringLiteral;
import relalg.UnaryExpression;
import relalg.UnionOperator;
import relalg.Variable;
import relalg.VertexLabel;
import relalg.VertexVariable;

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
			case RelalgPackage.ALGEBRA_EXPRESSION: {
				AlgebraExpression algebraExpression = (AlgebraExpression)theEObject;
				T result = caseAlgebraExpression(algebraExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PROJECTION_OPERATOR: {
				ProjectionOperator projectionOperator = (ProjectionOperator)theEObject;
				T result = caseProjectionOperator(projectionOperator);
				if (result == null) result = caseAlphaOperator(projectionOperator);
				if (result == null) result = caseAlgebraExpression(projectionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.JOIN_OPERATOR: {
				JoinOperator joinOperator = (JoinOperator)theEObject;
				T result = caseJoinOperator(joinOperator);
				if (result == null) result = caseBetaOperator(joinOperator);
				if (result == null) result = caseAlgebraExpression(joinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ALPHA_OPERATOR: {
				AlphaOperator alphaOperator = (AlphaOperator)theEObject;
				T result = caseAlphaOperator(alphaOperator);
				if (result == null) result = caseAlgebraExpression(alphaOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BETA_OPERATOR: {
				BetaOperator betaOperator = (BetaOperator)theEObject;
				T result = caseBetaOperator(betaOperator);
				if (result == null) result = caseAlgebraExpression(betaOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ANTI_JOIN_OPERATOR: {
				AntiJoinOperator antiJoinOperator = (AntiJoinOperator)theEObject;
				T result = caseAntiJoinOperator(antiJoinOperator);
				if (result == null) result = caseBetaOperator(antiJoinOperator);
				if (result == null) result = caseAlgebraExpression(antiJoinOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PRODUCTION_OPERATOR: {
				ProductionOperator productionOperator = (ProductionOperator)theEObject;
				T result = caseProductionOperator(productionOperator);
				if (result == null) result = caseAlphaOperator(productionOperator);
				if (result == null) result = caseAlgebraExpression(productionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.SELECTION_OPERATOR: {
				SelectionOperator selectionOperator = (SelectionOperator)theEObject;
				T result = caseSelectionOperator(selectionOperator);
				if (result == null) result = caseAlphaOperator(selectionOperator);
				if (result == null) result = caseAlgebraExpression(selectionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EXPAND_OPERATOR: {
				ExpandOperator expandOperator = (ExpandOperator)theEObject;
				T result = caseExpandOperator(expandOperator);
				if (result == null) result = caseAlphaOperator(expandOperator);
				if (result == null) result = caseAlgebraExpression(expandOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GET_VERTICES_OPERATOR: {
				GetVerticesOperator getVerticesOperator = (GetVerticesOperator)theEObject;
				T result = caseGetVerticesOperator(getVerticesOperator);
				if (result == null) result = caseAlgebraExpression(getVerticesOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DUPLICATE_ELIMINATION_OPERATOR: {
				DuplicateEliminationOperator duplicateEliminationOperator = (DuplicateEliminationOperator)theEObject;
				T result = caseDuplicateEliminationOperator(duplicateEliminationOperator);
				if (result == null) result = caseAlphaOperator(duplicateEliminationOperator);
				if (result == null) result = caseAlgebraExpression(duplicateEliminationOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VARIABLE: {
				Variable variable = (Variable)theEObject;
				T result = caseVariable(variable);
				if (result == null) result = caseNamedElement(variable);
				if (result == null) result = caseReturnableElement(variable);
				if (result == null) result = caseComparable(variable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VERTEX_VARIABLE: {
				VertexVariable vertexVariable = (VertexVariable)theEObject;
				T result = caseVertexVariable(vertexVariable);
				if (result == null) result = caseElementVariable(vertexVariable);
				if (result == null) result = caseVariable(vertexVariable);
				if (result == null) result = caseNamedElement(vertexVariable);
				if (result == null) result = caseReturnableElement(vertexVariable);
				if (result == null) result = caseComparable(vertexVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_VARIABLE: {
				EdgeVariable edgeVariable = (EdgeVariable)theEObject;
				T result = caseEdgeVariable(edgeVariable);
				if (result == null) result = caseElementVariable(edgeVariable);
				if (result == null) result = caseVariable(edgeVariable);
				if (result == null) result = caseNamedElement(edgeVariable);
				if (result == null) result = caseReturnableElement(edgeVariable);
				if (result == null) result = caseComparable(edgeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LABEL: {
				Label label = (Label)theEObject;
				T result = caseLabel(label);
				if (result == null) result = caseNamedElement(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.VERTEX_LABEL: {
				VertexLabel vertexLabel = (VertexLabel)theEObject;
				T result = caseVertexLabel(vertexLabel);
				if (result == null) result = caseLabel(vertexLabel);
				if (result == null) result = caseNamedElement(vertexLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_LABEL: {
				EdgeLabel edgeLabel = (EdgeLabel)theEObject;
				T result = caseEdgeLabel(edgeLabel);
				if (result == null) result = caseLabel(edgeLabel);
				if (result == null) result = caseNamedElement(edgeLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ALL_DIFFERENT_OPERATOR: {
				AllDifferentOperator allDifferentOperator = (AllDifferentOperator)theEObject;
				T result = caseAllDifferentOperator(allDifferentOperator);
				if (result == null) result = caseAlphaOperator(allDifferentOperator);
				if (result == null) result = caseAlgebraExpression(allDifferentOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ATTRIBUTE_VARIABLE: {
				AttributeVariable attributeVariable = (AttributeVariable)theEObject;
				T result = caseAttributeVariable(attributeVariable);
				if (result == null) result = caseVariable(attributeVariable);
				if (result == null) result = caseNamedElement(attributeVariable);
				if (result == null) result = caseReturnableElement(attributeVariable);
				if (result == null) result = caseComparable(attributeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNION_OPERATOR: {
				UnionOperator unionOperator = (UnionOperator)theEObject;
				T result = caseUnionOperator(unionOperator);
				if (result == null) result = caseBetaOperator(unionOperator);
				if (result == null) result = caseAlgebraExpression(unionOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T result = caseContainer(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = caseReturnableElement(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.RETURNABLE_ELEMENT: {
				ReturnableElement returnableElement = (ReturnableElement)theEObject;
				T result = caseReturnableElement(returnableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_EXPRESSION: {
				BinaryExpression binaryExpression = (BinaryExpression)theEObject;
				T result = caseBinaryExpression(binaryExpression);
				if (result == null) result = caseExpression(binaryExpression);
				if (result == null) result = caseReturnableElement(binaryExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ARITHMETIC_OPERATION_EXPRESSION: {
				ArithmeticOperationExpression arithmeticOperationExpression = (ArithmeticOperationExpression)theEObject;
				T result = caseArithmeticOperationExpression(arithmeticOperationExpression);
				if (result == null) result = caseBinaryExpression(arithmeticOperationExpression);
				if (result == null) result = caseComparable(arithmeticOperationExpression);
				if (result == null) result = caseExpression(arithmeticOperationExpression);
				if (result == null) result = caseReturnableElement(arithmeticOperationExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION: {
				BinaryLogicalExpression binaryLogicalExpression = (BinaryLogicalExpression)theEObject;
				T result = caseBinaryLogicalExpression(binaryLogicalExpression);
				if (result == null) result = caseBinaryExpression(binaryLogicalExpression);
				if (result == null) result = caseExpression(binaryLogicalExpression);
				if (result == null) result = caseReturnableElement(binaryLogicalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ARITHMETIC_COMPARISON_EXPRESSION: {
				ArithmeticComparisonExpression arithmeticComparisonExpression = (ArithmeticComparisonExpression)theEObject;
				T result = caseArithmeticComparisonExpression(arithmeticComparisonExpression);
				if (result == null) result = caseComparisonExpression(arithmeticComparisonExpression);
				if (result == null) result = caseBinaryExpression(arithmeticComparisonExpression);
				if (result == null) result = caseExpression(arithmeticComparisonExpression);
				if (result == null) result = caseReturnableElement(arithmeticComparisonExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.UNARY_EXPRESSION: {
				UnaryExpression unaryExpression = (UnaryExpression)theEObject;
				T result = caseUnaryExpression(unaryExpression);
				if (result == null) result = caseExpression(unaryExpression);
				if (result == null) result = caseReturnableElement(unaryExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.STRING_COMPARISON_EXPRESSION: {
				StringComparisonExpression stringComparisonExpression = (StringComparisonExpression)theEObject;
				T result = caseStringComparisonExpression(stringComparisonExpression);
				if (result == null) result = caseComparisonExpression(stringComparisonExpression);
				if (result == null) result = caseBinaryExpression(stringComparisonExpression);
				if (result == null) result = caseExpression(stringComparisonExpression);
				if (result == null) result = caseReturnableElement(stringComparisonExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.LITERAL: {
				Literal literal = (Literal)theEObject;
				T result = caseLiteral(literal);
				if (result == null) result = caseAtom(literal);
				if (result == null) result = caseComparable(literal);
				if (result == null) result = caseExpression(literal);
				if (result == null) result = caseReturnableElement(literal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NUMBER_LITERAL: {
				NumberLiteral numberLiteral = (NumberLiteral)theEObject;
				T result = caseNumberLiteral(numberLiteral);
				if (result == null) result = caseLiteral(numberLiteral);
				if (result == null) result = caseAtom(numberLiteral);
				if (result == null) result = caseComparable(numberLiteral);
				if (result == null) result = caseExpression(numberLiteral);
				if (result == null) result = caseReturnableElement(numberLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.STRING_LITERAL: {
				StringLiteral stringLiteral = (StringLiteral)theEObject;
				T result = caseStringLiteral(stringLiteral);
				if (result == null) result = caseLiteral(stringLiteral);
				if (result == null) result = caseAtom(stringLiteral);
				if (result == null) result = caseComparable(stringLiteral);
				if (result == null) result = caseExpression(stringLiteral);
				if (result == null) result = caseReturnableElement(stringLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.DOUBLE_LITERAL: {
				DoubleLiteral doubleLiteral = (DoubleLiteral)theEObject;
				T result = caseDoubleLiteral(doubleLiteral);
				if (result == null) result = caseNumberLiteral(doubleLiteral);
				if (result == null) result = caseLiteral(doubleLiteral);
				if (result == null) result = caseAtom(doubleLiteral);
				if (result == null) result = caseComparable(doubleLiteral);
				if (result == null) result = caseExpression(doubleLiteral);
				if (result == null) result = caseReturnableElement(doubleLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INTEGER_LITERAL: {
				IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
				T result = caseIntegerLiteral(integerLiteral);
				if (result == null) result = caseNumberLiteral(integerLiteral);
				if (result == null) result = caseLiteral(integerLiteral);
				if (result == null) result = caseAtom(integerLiteral);
				if (result == null) result = caseComparable(integerLiteral);
				if (result == null) result = caseExpression(integerLiteral);
				if (result == null) result = caseReturnableElement(integerLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.GET_EDGES_OPERATOR: {
				GetEdgesOperator getEdgesOperator = (GetEdgesOperator)theEObject;
				T result = caseGetEdgesOperator(getEdgesOperator);
				if (result == null) result = caseAlgebraExpression(getEdgesOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.COMPARABLE: {
				relalg.Comparable comparable = (relalg.Comparable)theEObject;
				T result = caseComparable(comparable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ATOM: {
				Atom atom = (Atom)theEObject;
				T result = caseAtom(atom);
				if (result == null) result = caseExpression(atom);
				if (result == null) result = caseReturnableElement(atom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ELEMENT_VARIABLE: {
				ElementVariable elementVariable = (ElementVariable)theEObject;
				T result = caseElementVariable(elementVariable);
				if (result == null) result = caseVariable(elementVariable);
				if (result == null) result = caseNamedElement(elementVariable);
				if (result == null) result = caseReturnableElement(elementVariable);
				if (result == null) result = caseComparable(elementVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.COMPARISON_EXPRESSION: {
				ComparisonExpression comparisonExpression = (ComparisonExpression)theEObject;
				T result = caseComparisonExpression(comparisonExpression);
				if (result == null) result = caseBinaryExpression(comparisonExpression);
				if (result == null) result = caseExpression(comparisonExpression);
				if (result == null) result = caseReturnableElement(comparisonExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Algebra Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Algebra Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlgebraExpression(AlgebraExpression object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Alpha Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alpha Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlphaOperator(AlphaOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Beta Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Beta Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBetaOperator(BetaOperator object) {
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
	public T caseContainer(Container object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Returnable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Returnable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReturnableElement(ReturnableElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Arithmetic Operation Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arithmetic Operation Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArithmeticOperationExpression(ArithmeticOperationExpression object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>String Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Comparison Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringComparisonExpression(StringComparisonExpression object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Comparison Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparison Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparisonExpression(ComparisonExpression object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Comparable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparable(relalg.Comparable object) {
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
