/**
 */
package relalg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Projection Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ProjectionOperator is the renaissance man of the Rete network.
 *  * It can:
 * - Project variables to a schema, i.e. only keeping a certain set of variables.
 * - Extract attributes from a vertex/edge, e.g. it can project to n.name, r.weight, etc. (other operators, such as the SelectionOperator are also capable of this feat)
 * - Evaluate metafunctions, such as labels(n), relationship(r), keys(e), properties(e), by relying on input from the NullaryOperators
 *   (i.e. the inferencer algorithm has to propagate these variables to the NullaryOperators)
 * - Evaluate other functions, e.g. sin(x), substring(s, from, to), toBoolean(s), etc.
 * <!-- end-model-doc -->
 *
 *
 * @see relalg.RelalgPackage#getProjectionOperator()
 * @model
 * @generated
 */
public interface ProjectionOperator extends BeamerOperator {
} // ProjectionOperator
