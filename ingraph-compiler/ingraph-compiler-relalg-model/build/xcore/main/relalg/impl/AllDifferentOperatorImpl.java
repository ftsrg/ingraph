/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.AbstractEdgeVariable;
import relalg.AllDifferentOperator;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>All Different Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.AllDifferentOperatorImpl#getEdgeVariables <em>Edge Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AllDifferentOperatorImpl extends UnaryOperatorImpl implements AllDifferentOperator {
	/**
	 * The cached value of the '{@link #getEdgeVariables() <em>Edge Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractEdgeVariable> edgeVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AllDifferentOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.ALL_DIFFERENT_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractEdgeVariable> getEdgeVariables() {
		if (edgeVariables == null) {
			edgeVariables = new EObjectResolvingEList<AbstractEdgeVariable>(AbstractEdgeVariable.class, this, RelalgPackage.ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES);
		}
		return edgeVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES:
				return getEdgeVariables();
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
			case RelalgPackage.ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES:
				getEdgeVariables().clear();
				getEdgeVariables().addAll((Collection<? extends AbstractEdgeVariable>)newValue);
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
			case RelalgPackage.ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES:
				getEdgeVariables().clear();
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
			case RelalgPackage.ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES:
				return edgeVariables != null && !edgeVariables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AllDifferentOperatorImpl
