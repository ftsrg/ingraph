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
			case RelalgPackage.TRIMMER_OPERATION: {
				TrimmerOperation trimmerOperation = (TrimmerOperation)theEObject;
				T result = caseTrimmerOperation(trimmerOperation);
				if (result == null) result = caseAlphaOperation(trimmerOperation);
				if (result == null) result = caseAlgebraExpression(trimmerOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.JOIN_OPERATION: {
				JoinOperation joinOperation = (JoinOperation)theEObject;
				T result = caseJoinOperation(joinOperation);
				if (result == null) result = caseBetaOperation(joinOperation);
				if (result == null) result = caseAlgebraExpression(joinOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ALPHA_OPERATION: {
				AlphaOperation alphaOperation = (AlphaOperation)theEObject;
				T result = caseAlphaOperation(alphaOperation);
				if (result == null) result = caseAlgebraExpression(alphaOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.BETA_OPERATION: {
				BetaOperation betaOperation = (BetaOperation)theEObject;
				T result = caseBetaOperation(betaOperation);
				if (result == null) result = caseAlgebraExpression(betaOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.ANTI_JOIN_OPERATION: {
				AntiJoinOperation antiJoinOperation = (AntiJoinOperation)theEObject;
				T result = caseAntiJoinOperation(antiJoinOperation);
				if (result == null) result = caseBetaOperation(antiJoinOperation);
				if (result == null) result = caseAlgebraExpression(antiJoinOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RelalgPackage.PRODUCTION_OPERATION: {
				ProductionOperation productionOperation = (ProductionOperation)theEObject;
				T result = caseProductionOperation(productionOperation);
				if (result == null) result = caseAlphaOperation(productionOperation);
				if (result == null) result = caseAlgebraExpression(productionOperation);
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
			case RelalgPackage.FILTER_OPERATION: {
				FilterOperation filterOperation = (FilterOperation)theEObject;
				T result = caseFilterOperation(filterOperation);
				if (result == null) result = caseAlphaOperation(filterOperation);
				if (result == null) result = caseAlgebraExpression(filterOperation);
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
	 * Returns the result of interpreting the object as an instance of '<em>Trimmer Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trimmer Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrimmerOperation(TrimmerOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoinOperation(JoinOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alpha Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alpha Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlphaOperation(AlphaOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Beta Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Beta Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBetaOperation(BetaOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anti Join Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anti Join Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAntiJoinOperation(AntiJoinOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Production Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Production Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductionOperation(ProductionOperation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Filter Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterOperation(FilterOperation object) {
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
