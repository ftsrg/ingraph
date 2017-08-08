/**
 */
package relalg.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.NamedElement;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;
import relalg.Variable;
import relalg.VariableExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.ExpressionVariableImpl#isHasInferredName <em>Has Inferred Name</em>}</li>
 *   <li>{@link relalg.impl.ExpressionVariableImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpressionVariableImpl extends VariableImpl implements ExpressionVariable {
	/**
	 * The default value of the '{@link #isHasInferredName() <em>Has Inferred Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInferredName()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_INFERRED_NAME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasInferredName() <em>Has Inferred Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInferredName()
	 * @generated
	 * @ordered
	 */
	protected boolean hasInferredName = HAS_INFERRED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.EXPRESSION_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasInferredName() {
		return hasInferredName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasInferredName(boolean newHasInferredName) {
		boolean oldHasInferredName = hasInferredName;
		hasInferredName = newHasInferredName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPRESSION_VARIABLE__HAS_INFERRED_NAME, oldHasInferredName, hasInferredName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		if (expression != null && expression.eIsProxy()) {
			InternalEObject oldExpression = (InternalEObject)expression;
			expression = (Expression)eResolveProxy(oldExpression);
			if (expression != oldExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION, oldExpression, expression));
			}
		}
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		String _xifexpression = null;
		boolean _isHasInferredName = this.isHasInferredName();
		if (_isHasInferredName) {
			String _xifexpression_1 = null;
			Expression _expression = this.getExpression();
			if ((_expression instanceof VariableExpression)) {
				Expression _expression_1 = this.getExpression();
				Variable _variable = ((VariableExpression) _expression_1).getVariable();
				_xifexpression_1 = _variable.getName();
			}
			else {
				Expression _expression_2 = this.getExpression();
				_xifexpression_1 = _expression_2.fullName();
			}
			_xifexpression = _xifexpression_1;
		}
		else {
			_xifexpression = this.getName();
		}
		return _xifexpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.EXPRESSION_VARIABLE__HAS_INFERRED_NAME:
				return isHasInferredName();
			case RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION:
				if (resolve) return getExpression();
				return basicGetExpression();
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
			case RelalgPackage.EXPRESSION_VARIABLE__HAS_INFERRED_NAME:
				setHasInferredName((Boolean)newValue);
				return;
			case RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION:
				setExpression((Expression)newValue);
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
			case RelalgPackage.EXPRESSION_VARIABLE__HAS_INFERRED_NAME:
				setHasInferredName(HAS_INFERRED_NAME_EDEFAULT);
				return;
			case RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION:
				setExpression((Expression)null);
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
			case RelalgPackage.EXPRESSION_VARIABLE__HAS_INFERRED_NAME:
				return hasInferredName != HAS_INFERRED_NAME_EDEFAULT;
			case RelalgPackage.EXPRESSION_VARIABLE__EXPRESSION:
				return expression != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == RelalgModelElement.class) {
			switch (baseOperationID) {
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.EXPRESSION_VARIABLE___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseOperationID) {
				case RelalgPackage.NAMED_ELEMENT___FULL_NAME: return RelalgPackage.EXPRESSION_VARIABLE___FULL_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case RelalgPackage.EXPRESSION_VARIABLE___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (hasInferredName: ");
		result.append(hasInferredName);
		result.append(')');
		return result.toString();
	}

} //ExpressionVariableImpl
