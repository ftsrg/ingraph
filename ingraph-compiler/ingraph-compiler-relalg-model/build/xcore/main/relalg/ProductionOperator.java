/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Production Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Currently, the ProductionOperator is used to "trim" extra variables that are part of the internal schema
 * (as they are required for processing the query), but are not specified in the external schema.
 *  * TODO: In the future, it should also be able to collect attributes for a specific vertex/edge.
 * For example, if the user specifies the following query:
 *  * MATCH (p:Person)-[l:LIKES]->(m:Message)
 * RETURN p, l, m.content
 *  * We should return a vertex for Person p, an edge for LIKES l (both with all their attribute)
 * and the content attribute of Message m.
 * <!-- end-model-doc -->
 *
 *
 * @see relalg.RelalgPackage#getProductionOperator()
 * @model
 * @generated
 */
public interface ProductionOperator extends BeamerOperator {
} // ProductionOperator
