/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.EdgeLabel;
import relalg.EdgeLabelSet;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge Label Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.EdgeLabelSetImpl#getEdgeLabels <em>Edge Labels</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeLabelSetImpl extends LabelSetImpl implements EdgeLabelSet {
	/**
	 * The cached value of the '{@link #getEdgeLabels() <em>Edge Labels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeLabels()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeLabel> edgeLabels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeLabelSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.EDGE_LABEL_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EdgeLabel> getEdgeLabels() {
		if (edgeLabels == null) {
			edgeLabels = new EObjectResolvingEList<EdgeLabel>(EdgeLabel.class, this, RelalgPackage.EDGE_LABEL_SET__EDGE_LABELS);
		}
		return edgeLabels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.EDGE_LABEL_SET__EDGE_LABELS:
				return getEdgeLabels();
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
			case RelalgPackage.EDGE_LABEL_SET__EDGE_LABELS:
				getEdgeLabels().clear();
				getEdgeLabels().addAll((Collection<? extends EdgeLabel>)newValue);
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
			case RelalgPackage.EDGE_LABEL_SET__EDGE_LABELS:
				getEdgeLabels().clear();
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
			case RelalgPackage.EDGE_LABEL_SET__EDGE_LABELS:
				return edgeLabels != null && !edgeLabels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EdgeLabelSetImpl
