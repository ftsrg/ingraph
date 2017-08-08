/**
 */
package relalg.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import relalg.Case;
import relalg.CaseExpression;
import relalg.Expression;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Case Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.CaseExpressionImpl#getCases <em>Cases</em>}</li>
 *   <li>{@link relalg.impl.CaseExpressionImpl#getFallback <em>Fallback</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CaseExpressionImpl extends ExpressionImpl implements CaseExpression {
	/**
	 * The cached value of the '{@link #getCases() <em>Cases</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCases()
	 * @generated
	 * @ordered
	 */
	protected EList<Case> cases;

	/**
	 * The cached value of the '{@link #getFallback() <em>Fallback</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFallback()
	 * @generated
	 * @ordered
	 */
	protected Expression fallback;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CaseExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.CASE_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Case> getCases() {
		if (cases == null) {
			cases = new EObjectContainmentEList<Case>(Case.class, this, RelalgPackage.CASE_EXPRESSION__CASES);
		}
		return cases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getFallback() {
		if (fallback != null && fallback.eIsProxy()) {
			InternalEObject oldFallback = (InternalEObject)fallback;
			fallback = (Expression)eResolveProxy(oldFallback);
			if (fallback != oldFallback) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.CASE_EXPRESSION__FALLBACK, oldFallback, fallback));
			}
		}
		return fallback;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetFallback() {
		return fallback;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFallback(Expression newFallback) {
		Expression oldFallback = fallback;
		fallback = newFallback;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.CASE_EXPRESSION__FALLBACK, oldFallback, fallback));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fullName() {
		return "TODO";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.CASE_EXPRESSION__CASES:
				return ((InternalEList<?>)getCases()).basicRemove(otherEnd, msgs);
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
			case RelalgPackage.CASE_EXPRESSION__CASES:
				return getCases();
			case RelalgPackage.CASE_EXPRESSION__FALLBACK:
				if (resolve) return getFallback();
				return basicGetFallback();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RelalgPackage.CASE_EXPRESSION__CASES:
				getCases().clear();
				getCases().addAll((Collection<? extends Case>)newValue);
				return;
			case RelalgPackage.CASE_EXPRESSION__FALLBACK:
				setFallback((Expression)newValue);
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
			case RelalgPackage.CASE_EXPRESSION__CASES:
				getCases().clear();
				return;
			case RelalgPackage.CASE_EXPRESSION__FALLBACK:
				setFallback((Expression)null);
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
			case RelalgPackage.CASE_EXPRESSION__CASES:
				return cases != null && !cases.isEmpty();
			case RelalgPackage.CASE_EXPRESSION__FALLBACK:
				return fallback != null;
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
				case RelalgPackage.RELALG_MODEL_ELEMENT___FULL_NAME: return RelalgPackage.CASE_EXPRESSION___FULL_NAME;
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
			case RelalgPackage.CASE_EXPRESSION___FULL_NAME:
				return fullName();
		}
		return super.eInvoke(operationID, arguments);
	}

} //CaseExpressionImpl
