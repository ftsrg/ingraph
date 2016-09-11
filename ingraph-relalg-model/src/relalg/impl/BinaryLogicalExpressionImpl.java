/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperator;
import relalg.Expression;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Logical Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.BinaryLogicalExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link relalg.impl.BinaryLogicalExpressionImpl#getRightExpression <em>Right Expression</em>}</li>
 *   <li>{@link relalg.impl.BinaryLogicalExpressionImpl#getLeftExpression <em>Left Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BinaryLogicalExpressionImpl extends BinaryExpressionImpl implements BinaryLogicalExpression {
	/**
	 * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected static final BinaryLogicalOperator OPERATOR_EDEFAULT = BinaryLogicalOperator.OR;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected BinaryLogicalOperator operator = OPERATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRightExpression() <em>Right Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression rightExpression;

	/**
	 * The cached value of the '{@link #getLeftExpression() <em>Left Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression leftExpression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BinaryLogicalExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.BINARY_LOGICAL_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryLogicalOperator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(BinaryLogicalOperator newOperator) {
		BinaryLogicalOperator oldOperator = operator;
		operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_LOGICAL_EXPRESSION__OPERATOR, oldOperator, operator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightExpression() {
		return rightExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightExpression(Expression newRightExpression, NotificationChain msgs) {
		Expression oldRightExpression = rightExpression;
		rightExpression = newRightExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION, oldRightExpression, newRightExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightExpression(Expression newRightExpression) {
		if (newRightExpression != rightExpression) {
			NotificationChain msgs = null;
			if (rightExpression != null)
				msgs = ((InternalEObject)rightExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION, null, msgs);
			if (newRightExpression != null)
				msgs = ((InternalEObject)newRightExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION, null, msgs);
			msgs = basicSetRightExpression(newRightExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION, newRightExpression, newRightExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLeftExpression() {
		return leftExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftExpression(Expression newLeftExpression, NotificationChain msgs) {
		Expression oldLeftExpression = leftExpression;
		leftExpression = newLeftExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION, oldLeftExpression, newLeftExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftExpression(Expression newLeftExpression) {
		if (newLeftExpression != leftExpression) {
			NotificationChain msgs = null;
			if (leftExpression != null)
				msgs = ((InternalEObject)leftExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION, null, msgs);
			if (newLeftExpression != null)
				msgs = ((InternalEObject)newLeftExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION, null, msgs);
			msgs = basicSetLeftExpression(newLeftExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION, newLeftExpression, newLeftExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION:
				return basicSetRightExpression(null, msgs);
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION:
				return basicSetLeftExpression(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__OPERATOR:
				return getOperator();
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION:
				return getRightExpression();
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION:
				return getLeftExpression();
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
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__OPERATOR:
				setOperator((BinaryLogicalOperator)newValue);
				return;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION:
				setRightExpression((Expression)newValue);
				return;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION:
				setLeftExpression((Expression)newValue);
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
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__OPERATOR:
				setOperator(OPERATOR_EDEFAULT);
				return;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION:
				setRightExpression((Expression)null);
				return;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION:
				setLeftExpression((Expression)null);
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
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__OPERATOR:
				return operator != OPERATOR_EDEFAULT;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__RIGHT_EXPRESSION:
				return rightExpression != null;
			case RelalgPackage.BINARY_LOGICAL_EXPRESSION__LEFT_EXPRESSION:
				return leftExpression != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (operator: ");
		result.append(operator);
		result.append(')');
		return result.toString();
	}

} //BinaryLogicalExpressionImpl
