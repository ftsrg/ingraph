/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.AbstractCondition;
import relalg.LogicalExpression;
import relalg.RelalgPackage;
import relalg.ThetaLeftOuterJoinOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Theta Left Outer Join Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.ThetaLeftOuterJoinOperatorImpl#getConditionString <em>Condition String</em>}</li>
 *   <li>{@link relalg.impl.ThetaLeftOuterJoinOperatorImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ThetaLeftOuterJoinOperatorImpl extends LeftOuterJoinOperatorImpl implements ThetaLeftOuterJoinOperator {
	/**
	 * The default value of the '{@link #getConditionString() <em>Condition String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionString()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionString() <em>Condition String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionString()
	 * @generated
	 * @ordered
	 */
	protected String conditionString = CONDITION_STRING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected LogicalExpression condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThetaLeftOuterJoinOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.THETA_LEFT_OUTER_JOIN_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConditionString() {
		return conditionString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionString(String newConditionString) {
		String oldConditionString = conditionString;
		conditionString = newConditionString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING, oldConditionString, conditionString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalExpression getCondition() {
		if (condition != null && condition.eIsProxy()) {
			InternalEObject oldCondition = (InternalEObject)condition;
			condition = (LogicalExpression)eResolveProxy(oldCondition);
			if (condition != oldCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION, oldCondition, condition));
			}
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalExpression basicGetCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(LogicalExpression newCondition) {
		LogicalExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING:
				return getConditionString();
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION:
				if (resolve) return getCondition();
				return basicGetCondition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING:
				setConditionString((String)newValue);
				return;
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION:
				setCondition((LogicalExpression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING:
				setConditionString(CONDITION_STRING_EDEFAULT);
				return;
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION:
				setCondition((LogicalExpression)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING:
				return CONDITION_STRING_EDEFAULT == null ? conditionString != null : !CONDITION_STRING_EDEFAULT.equals(conditionString);
			case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractCondition.class) {
			switch (derivedFeatureID) {
				case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING: return RelalgPackage.ABSTRACT_CONDITION__CONDITION_STRING;
				case RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION: return RelalgPackage.ABSTRACT_CONDITION__CONDITION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractCondition.class) {
			switch (baseFeatureID) {
				case RelalgPackage.ABSTRACT_CONDITION__CONDITION_STRING: return RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING;
				case RelalgPackage.ABSTRACT_CONDITION__CONDITION: return RelalgPackage.THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (conditionString: ");
		result.append(conditionString);
		result.append(')');
		return result.toString();
	}

} //ThetaLeftOuterJoinOperatorImpl
