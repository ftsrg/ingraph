/**
 */
package relalg.impl;

import org.eclipse.emf.ecore.EClass;

import relalg.LeftOuterJoinOperator;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Left Outer Join Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class LeftOuterJoinOperatorImpl extends EquiJoinLikeOperatorImpl implements LeftOuterJoinOperator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LeftOuterJoinOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.LEFT_OUTER_JOIN_OPERATOR;
	}

} //LeftOuterJoinOperatorImpl
