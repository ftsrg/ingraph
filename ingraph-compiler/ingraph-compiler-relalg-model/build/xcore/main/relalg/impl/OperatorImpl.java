/**
 */
package relalg.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import relalg.Cardinality;
import relalg.Operator;
import relalg.RelalgPackage;
import relalg.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.OperatorImpl#getExternalSchema <em>External Schema</em>}</li>
 *   <li>{@link relalg.impl.OperatorImpl#getExtraVariables <em>Extra Variables</em>}</li>
 *   <li>{@link relalg.impl.OperatorImpl#getInternalSchema <em>Internal Schema</em>}</li>
 *   <li>{@link relalg.impl.OperatorImpl#getCardinality <em>Cardinality</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class OperatorImpl extends MinimalEObjectImpl.Container implements Operator {
	/**
	 * The cached value of the '{@link #getExternalSchema() <em>External Schema</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalSchema()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> externalSchema;

	/**
	 * The cached value of the '{@link #getExtraVariables() <em>Extra Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> extraVariables;

	/**
	 * The cached value of the '{@link #getInternalSchema() <em>Internal Schema</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalSchema()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> internalSchema;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected Cardinality cardinality;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.OPERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getExternalSchema() {
		if (externalSchema == null) {
			externalSchema = new EObjectResolvingEList<Variable>(Variable.class, this, RelalgPackage.OPERATOR__EXTERNAL_SCHEMA);
		}
		return externalSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getExtraVariables() {
		if (extraVariables == null) {
			extraVariables = new EObjectResolvingEList<Variable>(Variable.class, this, RelalgPackage.OPERATOR__EXTRA_VARIABLES);
		}
		return extraVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getInternalSchema() {
		if (internalSchema == null) {
			internalSchema = new EObjectResolvingEList<Variable>(Variable.class, this, RelalgPackage.OPERATOR__INTERNAL_SCHEMA);
		}
		return internalSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cardinality getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCardinality(Cardinality newCardinality, NotificationChain msgs) {
		Cardinality oldCardinality = cardinality;
		cardinality = newCardinality;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.OPERATOR__CARDINALITY, oldCardinality, newCardinality);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardinality(Cardinality newCardinality) {
		if (newCardinality != cardinality) {
			NotificationChain msgs = null;
			if (cardinality != null)
				msgs = ((InternalEObject)cardinality).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.OPERATOR__CARDINALITY, null, msgs);
			if (newCardinality != null)
				msgs = ((InternalEObject)newCardinality).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.OPERATOR__CARDINALITY, null, msgs);
			msgs = basicSetCardinality(newCardinality, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.OPERATOR__CARDINALITY, newCardinality, newCardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.OPERATOR__CARDINALITY:
				return basicSetCardinality(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelalgPackage.OPERATOR__EXTERNAL_SCHEMA:
				return getExternalSchema();
			case RelalgPackage.OPERATOR__EXTRA_VARIABLES:
				return getExtraVariables();
			case RelalgPackage.OPERATOR__INTERNAL_SCHEMA:
				return getInternalSchema();
			case RelalgPackage.OPERATOR__CARDINALITY:
				return getCardinality();
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
			case RelalgPackage.OPERATOR__EXTERNAL_SCHEMA:
				getExternalSchema().clear();
				getExternalSchema().addAll((Collection<? extends Variable>)newValue);
				return;
			case RelalgPackage.OPERATOR__EXTRA_VARIABLES:
				getExtraVariables().clear();
				getExtraVariables().addAll((Collection<? extends Variable>)newValue);
				return;
			case RelalgPackage.OPERATOR__INTERNAL_SCHEMA:
				getInternalSchema().clear();
				getInternalSchema().addAll((Collection<? extends Variable>)newValue);
				return;
			case RelalgPackage.OPERATOR__CARDINALITY:
				setCardinality((Cardinality)newValue);
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
			case RelalgPackage.OPERATOR__EXTERNAL_SCHEMA:
				getExternalSchema().clear();
				return;
			case RelalgPackage.OPERATOR__EXTRA_VARIABLES:
				getExtraVariables().clear();
				return;
			case RelalgPackage.OPERATOR__INTERNAL_SCHEMA:
				getInternalSchema().clear();
				return;
			case RelalgPackage.OPERATOR__CARDINALITY:
				setCardinality((Cardinality)null);
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
			case RelalgPackage.OPERATOR__EXTERNAL_SCHEMA:
				return externalSchema != null && !externalSchema.isEmpty();
			case RelalgPackage.OPERATOR__EXTRA_VARIABLES:
				return extraVariables != null && !extraVariables.isEmpty();
			case RelalgPackage.OPERATOR__INTERNAL_SCHEMA:
				return internalSchema != null && !internalSchema.isEmpty();
			case RelalgPackage.OPERATOR__CARDINALITY:
				return cardinality != null;
		}
		return super.eIsSet(featureID);
	}

} //OperatorImpl
