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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import relalg.Expression;
import relalg.NamedElement;
import relalg.Operator;
import relalg.RelalgContainer;
import relalg.RelalgPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link relalg.impl.RelalgContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#getRootExpression <em>Root Expression</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#getExpressions <em>Expressions</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#isSimplifiedPlan <em>Simplified Plan</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#isIncrementalPlan <em>Incremental Plan</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#isExternalSchemaInferred <em>External Schema Inferred</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#isExtraVariablesInferred <em>Extra Variables Inferred</em>}</li>
 *   <li>{@link relalg.impl.RelalgContainerImpl#isInternalSchemaInferred <em>Internal Schema Inferred</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelalgContainerImpl extends MinimalEObjectImpl.Container implements RelalgContainer {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRootExpression() <em>Root Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootExpression()
	 * @generated
	 * @ordered
	 */
	protected Operator rootExpression;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedElement> elements;

	/**
	 * The cached value of the '{@link #getExpressions() <em>Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> expressions;

	/**
	 * The default value of the '{@link #isSimplifiedPlan() <em>Simplified Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSimplifiedPlan()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SIMPLIFIED_PLAN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSimplifiedPlan() <em>Simplified Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSimplifiedPlan()
	 * @generated
	 * @ordered
	 */
	protected boolean simplifiedPlan = SIMPLIFIED_PLAN_EDEFAULT;

	/**
	 * The default value of the '{@link #isIncrementalPlan() <em>Incremental Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncrementalPlan()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INCREMENTAL_PLAN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIncrementalPlan() <em>Incremental Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncrementalPlan()
	 * @generated
	 * @ordered
	 */
	protected boolean incrementalPlan = INCREMENTAL_PLAN_EDEFAULT;

	/**
	 * The default value of the '{@link #isExternalSchemaInferred() <em>External Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalSchemaInferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTERNAL_SCHEMA_INFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExternalSchemaInferred() <em>External Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalSchemaInferred()
	 * @generated
	 * @ordered
	 */
	protected boolean externalSchemaInferred = EXTERNAL_SCHEMA_INFERRED_EDEFAULT;

	/**
	 * The default value of the '{@link #isExtraVariablesInferred() <em>Extra Variables Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtraVariablesInferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTRA_VARIABLES_INFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtraVariablesInferred() <em>Extra Variables Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtraVariablesInferred()
	 * @generated
	 * @ordered
	 */
	protected boolean extraVariablesInferred = EXTRA_VARIABLES_INFERRED_EDEFAULT;

	/**
	 * The default value of the '{@link #isInternalSchemaInferred() <em>Internal Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInternalSchemaInferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERNAL_SCHEMA_INFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInternalSchemaInferred() <em>Internal Schema Inferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInternalSchemaInferred()
	 * @generated
	 * @ordered
	 */
	protected boolean internalSchemaInferred = INTERNAL_SCHEMA_INFERRED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelalgContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelalgPackage.Literals.RELALG_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getRootExpression() {
		return rootExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRootExpression(Operator newRootExpression, NotificationChain msgs) {
		Operator oldRootExpression = rootExpression;
		rootExpression = newRootExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION, oldRootExpression, newRootExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootExpression(Operator newRootExpression) {
		if (newRootExpression != rootExpression) {
			NotificationChain msgs = null;
			if (rootExpression != null)
				msgs = ((InternalEObject)rootExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION, null, msgs);
			if (newRootExpression != null)
				msgs = ((InternalEObject)newRootExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION, null, msgs);
			msgs = basicSetRootExpression(newRootExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION, newRootExpression, newRootExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<NamedElement>(NamedElement.class, this, RelalgPackage.RELALG_CONTAINER__ELEMENTS, RelalgPackage.NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getExpressions() {
		if (expressions == null) {
			expressions = new EObjectContainmentWithInverseEList<Expression>(Expression.class, this, RelalgPackage.RELALG_CONTAINER__EXPRESSIONS, RelalgPackage.EXPRESSION__EXPRESSION_CONTAINER);
		}
		return expressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSimplifiedPlan() {
		return simplifiedPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimplifiedPlan(boolean newSimplifiedPlan) {
		boolean oldSimplifiedPlan = simplifiedPlan;
		simplifiedPlan = newSimplifiedPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__SIMPLIFIED_PLAN, oldSimplifiedPlan, simplifiedPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIncrementalPlan() {
		return incrementalPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncrementalPlan(boolean newIncrementalPlan) {
		boolean oldIncrementalPlan = incrementalPlan;
		incrementalPlan = newIncrementalPlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__INCREMENTAL_PLAN, oldIncrementalPlan, incrementalPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExternalSchemaInferred() {
		return externalSchemaInferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalSchemaInferred(boolean newExternalSchemaInferred) {
		boolean oldExternalSchemaInferred = externalSchemaInferred;
		externalSchemaInferred = newExternalSchemaInferred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED, oldExternalSchemaInferred, externalSchemaInferred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtraVariablesInferred() {
		return extraVariablesInferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtraVariablesInferred(boolean newExtraVariablesInferred) {
		boolean oldExtraVariablesInferred = extraVariablesInferred;
		extraVariablesInferred = newExtraVariablesInferred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED, oldExtraVariablesInferred, extraVariablesInferred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInternalSchemaInferred() {
		return internalSchemaInferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInternalSchemaInferred(boolean newInternalSchemaInferred) {
		boolean oldInternalSchemaInferred = internalSchemaInferred;
		internalSchemaInferred = newInternalSchemaInferred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelalgPackage.RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED, oldInternalSchemaInferred, internalSchemaInferred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElements()).basicAdd(otherEnd, msgs);
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExpressions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION:
				return basicSetRootExpression(null, msgs);
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				return ((InternalEList<?>)getExpressions()).basicRemove(otherEnd, msgs);
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
			case RelalgPackage.RELALG_CONTAINER__NAME:
				return getName();
			case RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION:
				return getRootExpression();
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				return getElements();
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				return getExpressions();
			case RelalgPackage.RELALG_CONTAINER__SIMPLIFIED_PLAN:
				return isSimplifiedPlan();
			case RelalgPackage.RELALG_CONTAINER__INCREMENTAL_PLAN:
				return isIncrementalPlan();
			case RelalgPackage.RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED:
				return isExternalSchemaInferred();
			case RelalgPackage.RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED:
				return isExtraVariablesInferred();
			case RelalgPackage.RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED:
				return isInternalSchemaInferred();
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
			case RelalgPackage.RELALG_CONTAINER__NAME:
				setName((String)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION:
				setRootExpression((Operator)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends NamedElement>)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				getExpressions().clear();
				getExpressions().addAll((Collection<? extends Expression>)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__SIMPLIFIED_PLAN:
				setSimplifiedPlan((Boolean)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__INCREMENTAL_PLAN:
				setIncrementalPlan((Boolean)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED:
				setExternalSchemaInferred((Boolean)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED:
				setExtraVariablesInferred((Boolean)newValue);
				return;
			case RelalgPackage.RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED:
				setInternalSchemaInferred((Boolean)newValue);
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
			case RelalgPackage.RELALG_CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION:
				setRootExpression((Operator)null);
				return;
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				getElements().clear();
				return;
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				getExpressions().clear();
				return;
			case RelalgPackage.RELALG_CONTAINER__SIMPLIFIED_PLAN:
				setSimplifiedPlan(SIMPLIFIED_PLAN_EDEFAULT);
				return;
			case RelalgPackage.RELALG_CONTAINER__INCREMENTAL_PLAN:
				setIncrementalPlan(INCREMENTAL_PLAN_EDEFAULT);
				return;
			case RelalgPackage.RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED:
				setExternalSchemaInferred(EXTERNAL_SCHEMA_INFERRED_EDEFAULT);
				return;
			case RelalgPackage.RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED:
				setExtraVariablesInferred(EXTRA_VARIABLES_INFERRED_EDEFAULT);
				return;
			case RelalgPackage.RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED:
				setInternalSchemaInferred(INTERNAL_SCHEMA_INFERRED_EDEFAULT);
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
			case RelalgPackage.RELALG_CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RelalgPackage.RELALG_CONTAINER__ROOT_EXPRESSION:
				return rootExpression != null;
			case RelalgPackage.RELALG_CONTAINER__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case RelalgPackage.RELALG_CONTAINER__EXPRESSIONS:
				return expressions != null && !expressions.isEmpty();
			case RelalgPackage.RELALG_CONTAINER__SIMPLIFIED_PLAN:
				return simplifiedPlan != SIMPLIFIED_PLAN_EDEFAULT;
			case RelalgPackage.RELALG_CONTAINER__INCREMENTAL_PLAN:
				return incrementalPlan != INCREMENTAL_PLAN_EDEFAULT;
			case RelalgPackage.RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED:
				return externalSchemaInferred != EXTERNAL_SCHEMA_INFERRED_EDEFAULT;
			case RelalgPackage.RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED:
				return extraVariablesInferred != EXTRA_VARIABLES_INFERRED_EDEFAULT;
			case RelalgPackage.RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED:
				return internalSchemaInferred != INTERNAL_SCHEMA_INFERRED_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", simplifiedPlan: ");
		result.append(simplifiedPlan);
		result.append(", incrementalPlan: ");
		result.append(incrementalPlan);
		result.append(", externalSchemaInferred: ");
		result.append(externalSchemaInferred);
		result.append(", extraVariablesInferred: ");
		result.append(extraVariablesInferred);
		result.append(", internalSchemaInferred: ");
		result.append(internalSchemaInferred);
		result.append(')');
		return result.toString();
	}

} //RelalgContainerImpl
