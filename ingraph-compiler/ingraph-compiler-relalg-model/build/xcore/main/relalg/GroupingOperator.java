/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grouping Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The GroupingOperator adds grouping functionality to the basic ProjectionOperator.
 *  * If ProjectionOperator was the renaissance man, this is Baroque of the relational graph algebra model.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.GroupingOperator#getAggregationCriteria <em>Aggregation Criteria</em>}</li>
 *   <li>{@link relalg.GroupingOperator#getOrder <em>Order</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getGroupingOperator()
 * @model
 * @generated
 */
public interface GroupingOperator extends ProjectionOperator {
	/**
	 * Returns the value of the '<em><b>Aggregation Criteria</b></em>' reference list.
	 * The list contents are of type {@link relalg.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation Criteria</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation Criteria</em>' reference list.
	 * @see relalg.RelalgPackage#getGroupingOperator_AggregationCriteria()
	 * @model
	 * @generated
	 */
	EList<Expression> getAggregationCriteria();

	/**
	 * Returns the value of the '<em><b>Order</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order</em>' attribute list.
	 * @see relalg.RelalgPackage#getGroupingOperator_Order()
	 * @model unique="false"
	 * @generated
	 */
	EList<Integer> getOrder();

} // GroupingOperator
