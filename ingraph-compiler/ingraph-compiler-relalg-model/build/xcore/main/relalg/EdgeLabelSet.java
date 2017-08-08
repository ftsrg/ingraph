/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Label Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An edge satisfies a label set constraint iff it has any of the labels in the label set.
 * An edge might has at most one label.
 *  * For an edge variable, the combination of the following label set constraints
 * is contradicting, thus un-satisfiable:
 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
 *  2. [edge1:C], so edge1 needs to have label C.
 * These are contradicting as a single edge instance can have at most one label.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.EdgeLabelSet#getEdgeLabels <em>Edge Labels</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getEdgeLabelSet()
 * @model
 * @generated
 */
public interface EdgeLabelSet extends LabelSet {
	/**
	 * Returns the value of the '<em><b>Edge Labels</b></em>' reference list.
	 * The list contents are of type {@link relalg.EdgeLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge Labels</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Labels</em>' reference list.
	 * @see relalg.RelalgPackage#getEdgeLabelSet_EdgeLabels()
	 * @model
	 * @generated
	 */
	EList<EdgeLabel> getEdgeLabels();

} // EdgeLabelSet
