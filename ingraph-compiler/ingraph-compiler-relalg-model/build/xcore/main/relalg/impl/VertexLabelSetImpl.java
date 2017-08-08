/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.RelalgPackage;
import relalg.VertexLabel;
import relalg.VertexLabelSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex Label Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.VertexLabelSetImpl#getVertexLabels <em>Vertex Labels</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexLabelSetImpl extends LabelSetImpl implements VertexLabelSet {
	/**
	 * The cached value of the '{@link #getVertexLabels() <em>Vertex Labels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVertexLabels()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexLabel> vertexLabels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexLabelSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.VERTEX_LABEL_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VertexLabel> getVertexLabels() {
		if (vertexLabels == null) {
			vertexLabels = new EObjectResolvingEList<VertexLabel>(VertexLabel.class, this, RelalgPackage.VERTEX_LABEL_SET__VERTEX_LABELS);
		}
		return vertexLabels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.VERTEX_LABEL_SET__VERTEX_LABELS:
				return getVertexLabels();
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
			case RelalgPackage.VERTEX_LABEL_SET__VERTEX_LABELS:
				getVertexLabels().clear();
				getVertexLabels().addAll((Collection<? extends VertexLabel>)newValue);
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
			case RelalgPackage.VERTEX_LABEL_SET__VERTEX_LABELS:
				getVertexLabels().clear();
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
			case RelalgPackage.VERTEX_LABEL_SET__VERTEX_LABELS:
				return vertexLabels != null && !vertexLabels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VertexLabelSetImpl
