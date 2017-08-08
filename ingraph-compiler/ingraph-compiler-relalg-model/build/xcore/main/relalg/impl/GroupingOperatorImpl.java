/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.Expression;
import relalg.GroupingOperator;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grouping Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.GroupingOperatorImpl#getAggregationCriteria <em>Aggregation Criteria</em>}</li>
 *   <li>{@link relalg.impl.GroupingOperatorImpl#getOrder <em>Order</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupingOperatorImpl extends ProjectionOperatorImpl implements GroupingOperator {
	/**
	 * The cached value of the '{@link #getAggregationCriteria() <em>Aggregation Criteria</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationCriteria()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> aggregationCriteria;

	/**
	 * The cached value of the '{@link #getOrder() <em>Order</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> order;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupingOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.GROUPING_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getAggregationCriteria() {
		if (aggregationCriteria == null) {
			aggregationCriteria = new EObjectResolvingEList<Expression>(Expression.class, this, RelalgPackage.GROUPING_OPERATOR__AGGREGATION_CRITERIA);
		}
		return aggregationCriteria;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getOrder() {
		if (order == null) {
			order = new EDataTypeEList<Integer>(Integer.class, this, RelalgPackage.GROUPING_OPERATOR__ORDER);
		}
		return order;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.GROUPING_OPERATOR__AGGREGATION_CRITERIA:
				return getAggregationCriteria();
			case RelalgPackage.GROUPING_OPERATOR__ORDER:
				return getOrder();
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
			case RelalgPackage.GROUPING_OPERATOR__AGGREGATION_CRITERIA:
				getAggregationCriteria().clear();
				getAggregationCriteria().addAll((Collection<? extends Expression>)newValue);
				return;
			case RelalgPackage.GROUPING_OPERATOR__ORDER:
				getOrder().clear();
				getOrder().addAll((Collection<? extends Integer>)newValue);
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
			case RelalgPackage.GROUPING_OPERATOR__AGGREGATION_CRITERIA:
				getAggregationCriteria().clear();
				return;
			case RelalgPackage.GROUPING_OPERATOR__ORDER:
				getOrder().clear();
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
			case RelalgPackage.GROUPING_OPERATOR__AGGREGATION_CRITERIA:
				return aggregationCriteria != null && !aggregationCriteria.isEmpty();
			case RelalgPackage.GROUPING_OPERATOR__ORDER:
				return order != null && !order.isEmpty();
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
		result.append(" (order: ");
		result.append(order);
		result.append(')');
		return result.toString();
	}

} //GroupingOperatorImpl
