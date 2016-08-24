/**
 */
package relalg.util;

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
			case RelalgPackage.ALGEBRA_EXPRESSION: {
				AlgebraExpression algebraExpression = (AlgebraExpression)theEObject;
				T result = caseAlgebraExpression(algebraExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.INPUT_RELATION: {
				InputRelation inputRelation = (InputRelation)theEObject;
				T result = caseInputRelation(inputRelation);
				if (result == null) result = caseAlgebraExpression(inputRelation);
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
			case RelalgPackage.ATTRIBUTE: {
				Attribute attribute = (Attribute)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ATTRIBUTE_SET: {
				AttributeSet attributeSet = (AttributeSet)theEObject;
				T result = caseAttributeSet(attributeSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.JOIN_BINDING: {
				JoinBinding joinBinding = (JoinBinding)theEObject;
				T result = caseJoinBinding(joinBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.FILTER_OPERATOR: {
				FilterOperator filterOperator = (FilterOperator)theEObject;
				T result = caseFilterOperator(filterOperator);
				if (result == null) result = caseAlphaOperator(filterOperator);
				if (result == null) result = caseAlgebraExpression(filterOperator);
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
			case RelalgPackage.GET_NODES_OPERATOR: {
				GetNodesOperator getNodesOperator = (GetNodesOperator)theEObject;
				T result = caseGetNodesOperator(getNodesOperator);
				if (result == null) result = caseAlgebraExpression(getNodesOperator);
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
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.NODE_VARIABLE: {
				NodeVariable nodeVariable = (NodeVariable)theEObject;
				T result = caseNodeVariable(nodeVariable);
				if (result == null) result = caseVariable(nodeVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.EDGE_VARIABLE: {
				EdgeVariable edgeVariable = (EdgeVariable)theEObject;
				T result = caseEdgeVariable(edgeVariable);
				if (result == null) result = caseVariable(edgeVariable);
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
	 * Returns the result of interpreting the object as an instance of '<em>Input Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputRelation(InputRelation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeSet(AttributeSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoinBinding(JoinBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterOperator(FilterOperator object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Get Nodes Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get Nodes Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetNodesOperator(GetNodesOperator object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Node Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeVariable(NodeVariable object) {
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
