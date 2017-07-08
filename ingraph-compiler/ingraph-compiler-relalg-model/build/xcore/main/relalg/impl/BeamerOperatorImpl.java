/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.BeamerOperator;
import relalg.ExpressionVariable;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Beamer Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.BeamerOperatorImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link relalg.impl.BeamerOperatorImpl#getInternalElements <em>Internal Elements</em>}</li>
 *   <li>{@link relalg.impl.BeamerOperatorImpl#getTupleIndices <em>Tuple Indices</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BeamerOperatorImpl extends UnaryOperatorImpl implements BeamerOperator {
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpressionVariable> elements;

	/**
	 * The cached value of the '{@link #getInternalElements() <em>Internal Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpressionVariable> internalElements;

	/**
	 * The cached value of the '{@link #getTupleIndices() <em>Tuple Indices</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTupleIndices()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> tupleIndices;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BeamerOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.BEAMER_OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpressionVariable> getElements() {
		if (elements == null) {
			elements = new EObjectResolvingEList<ExpressionVariable>(ExpressionVariable.class, this, RelalgPackage.BEAMER_OPERATOR__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpressionVariable> getInternalElements() {
		if (internalElements == null) {
			internalElements = new EObjectResolvingEList<ExpressionVariable>(ExpressionVariable.class, this, RelalgPackage.BEAMER_OPERATOR__INTERNAL_ELEMENTS);
		}
		return internalElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getTupleIndices() {
		if (tupleIndices == null) {
			tupleIndices = new EDataTypeEList<Integer>(Integer.class, this, RelalgPackage.BEAMER_OPERATOR__TUPLE_INDICES);
		}
		return tupleIndices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.BEAMER_OPERATOR__ELEMENTS:
				return getElements();
			case RelalgPackage.BEAMER_OPERATOR__INTERNAL_ELEMENTS:
				return getInternalElements();
			case RelalgPackage.BEAMER_OPERATOR__TUPLE_INDICES:
				return getTupleIndices();
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
			case RelalgPackage.BEAMER_OPERATOR__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends ExpressionVariable>)newValue);
				return;
			case RelalgPackage.BEAMER_OPERATOR__INTERNAL_ELEMENTS:
				getInternalElements().clear();
				getInternalElements().addAll((Collection<? extends ExpressionVariable>)newValue);
				return;
			case RelalgPackage.BEAMER_OPERATOR__TUPLE_INDICES:
				getTupleIndices().clear();
				getTupleIndices().addAll((Collection<? extends Integer>)newValue);
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
			case RelalgPackage.BEAMER_OPERATOR__ELEMENTS:
				getElements().clear();
				return;
			case RelalgPackage.BEAMER_OPERATOR__INTERNAL_ELEMENTS:
				getInternalElements().clear();
				return;
			case RelalgPackage.BEAMER_OPERATOR__TUPLE_INDICES:
				getTupleIndices().clear();
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
			case RelalgPackage.BEAMER_OPERATOR__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case RelalgPackage.BEAMER_OPERATOR__INTERNAL_ELEMENTS:
				return internalElements != null && !internalElements.isEmpty();
			case RelalgPackage.BEAMER_OPERATOR__TUPLE_INDICES:
				return tupleIndices != null && !tupleIndices.isEmpty();
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
		result.append(" (tupleIndices: ");
		result.append(tupleIndices);
		result.append(')');
		return result.toString();
	}

} //BeamerOperatorImpl
