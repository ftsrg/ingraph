/**
 */
package relalg.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import relalg.Expression;
import relalg.RelalgPackage;
import relalg.SortAndTopOperator;
import relalg.TopOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sort And Top Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.SortAndTopOperatorImpl#getSkip <em>Skip</em>}</li>
 *   <li>{@link relalg.impl.SortAndTopOperatorImpl#getLimit <em>Limit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SortAndTopOperatorImpl extends SortOperatorImpl implements SortAndTopOperator {
	/**
	 * The cached value of the '{@link #getSkip() <em>Skip</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkip()
	 * @generated
	 * @ordered
	 */
	protected Expression skip;

	/**
	 * The cached value of the '{@link #getLimit() <em>Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected Expression limit;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SortAndTopOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.SORT_AND_TOP_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getSkip() {
		if (skip != null && skip.eIsProxy()) {
			InternalEObject oldSkip = (InternalEObject)skip;
			skip = (Expression)eResolveProxy(oldSkip);
			if (skip != oldSkip) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP, oldSkip, skip));
			}
		}
		return skip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetSkip() {
		return skip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkip(Expression newSkip) {
		Expression oldSkip = skip;
		skip = newSkip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP, oldSkip, skip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getLimit() {
		if (limit != null && limit.eIsProxy()) {
			InternalEObject oldLimit = (InternalEObject)limit;
			limit = (Expression)eResolveProxy(oldLimit);
			if (limit != oldLimit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT, oldLimit, limit));
			}
		}
		return limit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetLimit() {
		return limit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLimit(Expression newLimit) {
		Expression oldLimit = limit;
		limit = newLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT, oldLimit, limit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP:
				if (resolve) return getSkip();
				return basicGetSkip();
			case RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT:
				if (resolve) return getLimit();
				return basicGetLimit();
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
			case RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP:
				setSkip((Expression)newValue);
				return;
			case RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT:
				setLimit((Expression)newValue);
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
			case RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP:
				setSkip((Expression)null);
				return;
			case RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT:
				setLimit((Expression)null);
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
			case RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP:
				return skip != null;
			case RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT:
				return limit != null;
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
		if (baseClass == TopOperator.class) {
			switch (derivedFeatureID) {
				case RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP: return RelalgPackage.TOP_OPERATOR__SKIP;
				case RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT: return RelalgPackage.TOP_OPERATOR__LIMIT;
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
		if (baseClass == TopOperator.class) {
			switch (baseFeatureID) {
				case RelalgPackage.TOP_OPERATOR__SKIP: return RelalgPackage.SORT_AND_TOP_OPERATOR__SKIP;
				case RelalgPackage.TOP_OPERATOR__LIMIT: return RelalgPackage.SORT_AND_TOP_OPERATOR__LIMIT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SortAndTopOperatorImpl
