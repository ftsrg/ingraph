/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex Label Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A vertex satisfies a label set constraint iff it has all the labels in the label set.
 *  * The vertex itself might has more labels, so in case of more than one label set constraint
 * for a single vertex variable, it is always satisfiable, i.e.
 * LabelSet.status should never be LabelSetStatus.CONTRADICTING.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.VertexLabelSet#getVertexLabels <em>Vertex Labels</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getVertexLabelSet()
 * @model
 * @generated
 */
public interface VertexLabelSet extends LabelSet {
	/**
	 * Returns the value of the '<em><b>Vertex Labels</b></em>' reference list.
	 * The list contents are of type {@link relalg.VertexLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertex Labels</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vertex Labels</em>' reference list.
	 * @see relalg.RelalgPackage#getVertexLabelSet_VertexLabels()
	 * @model
	 * @generated
	 */
	EList<VertexLabel> getVertexLabels();

} // VertexLabelSet
