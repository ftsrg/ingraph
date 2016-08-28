/**
 */
package relalg.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import relalg.AlgebraExpression;
import relalg.AllDifferentOperator;
import relalg.AlphaOperator;
import relalg.AntiJoinOperator;
import relalg.AttributeVariable;
import relalg.BetaOperator;
import relalg.Direction;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeLabel;
import relalg.EdgeVariable;
import relalg.ExpandOperator;
import relalg.FilterOperator;
import relalg.GetVerticesOperator;
import relalg.JoinOperator;
import relalg.Label;
import relalg.NamedElement;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.RelalgFactory;
import relalg.RelalgPackage;
import relalg.Variable;
import relalg.VertexLabel;
import relalg.VertexVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelalgPackageImpl extends EPackageImpl implements RelalgPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass algebraExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectionOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alphaOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass betaOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass antiJoinOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productionOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expandOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getVerticesOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass duplicateEliminationOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allDifferentOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum directionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see relalg.RelalgPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RelalgPackageImpl() {
		super(eNS_URI, RelalgFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link RelalgPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RelalgPackage init() {
		if (isInited) return (RelalgPackage)EPackage.Registry.INSTANCE.getEPackage(RelalgPackage.eNS_URI);

		// Obtain or create and register package
		RelalgPackageImpl theRelalgPackage = (RelalgPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RelalgPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RelalgPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theRelalgPackage.createPackageContents();

		// Initialize created meta-data
		theRelalgPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRelalgPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RelalgPackage.eNS_URI, theRelalgPackage);
		return theRelalgPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlgebraExpression() {
		return algebraExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlgebraExpression_Name() {
		return (EAttribute)algebraExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProjectionOperator() {
		return projectionOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProjectionOperator_Variables() {
		return (EReference)projectionOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoinOperator() {
		return joinOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlphaOperator() {
		return alphaOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlphaOperator_Input() {
		return (EReference)alphaOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBetaOperator() {
		return betaOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBetaOperator_LeftInput() {
		return (EReference)betaOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBetaOperator_RightInput() {
		return (EReference)betaOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAntiJoinOperator() {
		return antiJoinOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductionOperator() {
		return productionOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilterOperator() {
		return filterOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpandOperator() {
		return expandOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpandOperator_Direction() {
		return (EAttribute)expandOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpandOperator_EdgeVariable() {
		return (EReference)expandOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpandOperator_SourceVertexVariable() {
		return (EReference)expandOperatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpandOperator_TargetVertexVariable() {
		return (EReference)expandOperatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGetVerticesOperator() {
		return getVerticesOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetVerticesOperator_VertexVariable() {
		return (EReference)getVerticesOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDuplicateEliminationOperator() {
		return duplicateEliminationOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariable() {
		return variableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariable_DontCare() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertexVariable() {
		return vertexVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertexVariable_VertexLabel() {
		return (EReference)vertexVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertexVariable_AttributeVariables() {
		return (EReference)vertexVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdgeVariable() {
		return edgeVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeVariable_EdgeLabel() {
		return (EReference)edgeVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeVariable_AttributeVariables() {
		return (EReference)edgeVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertexLabel() {
		return vertexLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdgeLabel() {
		return edgeLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAllDifferentOperator() {
		return allDifferentOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAllDifferentOperator_EdgeVariables() {
		return (EReference)allDifferentOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeVariable() {
		return attributeVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDirection() {
		return directionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgFactory getRelalgFactory() {
		return (RelalgFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		algebraExpressionEClass = createEClass(ALGEBRA_EXPRESSION);
		createEAttribute(algebraExpressionEClass, ALGEBRA_EXPRESSION__NAME);

		projectionOperatorEClass = createEClass(PROJECTION_OPERATOR);
		createEReference(projectionOperatorEClass, PROJECTION_OPERATOR__VARIABLES);

		joinOperatorEClass = createEClass(JOIN_OPERATOR);

		alphaOperatorEClass = createEClass(ALPHA_OPERATOR);
		createEReference(alphaOperatorEClass, ALPHA_OPERATOR__INPUT);

		betaOperatorEClass = createEClass(BETA_OPERATOR);
		createEReference(betaOperatorEClass, BETA_OPERATOR__LEFT_INPUT);
		createEReference(betaOperatorEClass, BETA_OPERATOR__RIGHT_INPUT);

		antiJoinOperatorEClass = createEClass(ANTI_JOIN_OPERATOR);

		productionOperatorEClass = createEClass(PRODUCTION_OPERATOR);

		filterOperatorEClass = createEClass(FILTER_OPERATOR);

		expandOperatorEClass = createEClass(EXPAND_OPERATOR);
		createEAttribute(expandOperatorEClass, EXPAND_OPERATOR__DIRECTION);
		createEReference(expandOperatorEClass, EXPAND_OPERATOR__EDGE_VARIABLE);
		createEReference(expandOperatorEClass, EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE);
		createEReference(expandOperatorEClass, EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE);

		getVerticesOperatorEClass = createEClass(GET_VERTICES_OPERATOR);
		createEReference(getVerticesOperatorEClass, GET_VERTICES_OPERATOR__VERTEX_VARIABLE);

		duplicateEliminationOperatorEClass = createEClass(DUPLICATE_ELIMINATION_OPERATOR);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__DONT_CARE);

		vertexVariableEClass = createEClass(VERTEX_VARIABLE);
		createEReference(vertexVariableEClass, VERTEX_VARIABLE__VERTEX_LABEL);
		createEReference(vertexVariableEClass, VERTEX_VARIABLE__ATTRIBUTE_VARIABLES);

		edgeVariableEClass = createEClass(EDGE_VARIABLE);
		createEReference(edgeVariableEClass, EDGE_VARIABLE__EDGE_LABEL);
		createEReference(edgeVariableEClass, EDGE_VARIABLE__ATTRIBUTE_VARIABLES);

		labelEClass = createEClass(LABEL);

		vertexLabelEClass = createEClass(VERTEX_LABEL);

		edgeLabelEClass = createEClass(EDGE_LABEL);

		allDifferentOperatorEClass = createEClass(ALL_DIFFERENT_OPERATOR);
		createEReference(allDifferentOperatorEClass, ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES);

		attributeVariableEClass = createEClass(ATTRIBUTE_VARIABLE);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		// Create enums
		directionEEnum = createEEnum(DIRECTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		projectionOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		joinOperatorEClass.getESuperTypes().add(this.getBetaOperator());
		alphaOperatorEClass.getESuperTypes().add(this.getAlgebraExpression());
		betaOperatorEClass.getESuperTypes().add(this.getAlgebraExpression());
		antiJoinOperatorEClass.getESuperTypes().add(this.getBetaOperator());
		productionOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		filterOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		expandOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		getVerticesOperatorEClass.getESuperTypes().add(this.getAlgebraExpression());
		duplicateEliminationOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		variableEClass.getESuperTypes().add(this.getNamedElement());
		vertexVariableEClass.getESuperTypes().add(this.getVariable());
		edgeVariableEClass.getESuperTypes().add(this.getVariable());
		labelEClass.getESuperTypes().add(this.getNamedElement());
		vertexLabelEClass.getESuperTypes().add(this.getLabel());
		edgeLabelEClass.getESuperTypes().add(this.getLabel());
		allDifferentOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		attributeVariableEClass.getESuperTypes().add(this.getVariable());

		// Initialize classes, features, and operations; add parameters
		initEClass(algebraExpressionEClass, AlgebraExpression.class, "AlgebraExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlgebraExpression_Name(), ecorePackage.getEString(), "name", null, 0, 1, AlgebraExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(projectionOperatorEClass, ProjectionOperator.class, "ProjectionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProjectionOperator_Variables(), this.getVariable(), null, "variables", null, 0, -1, ProjectionOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinOperatorEClass, JoinOperator.class, "JoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(alphaOperatorEClass, AlphaOperator.class, "AlphaOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlphaOperator_Input(), this.getAlgebraExpression(), null, "input", null, 1, 1, AlphaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(betaOperatorEClass, BetaOperator.class, "BetaOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBetaOperator_LeftInput(), this.getAlgebraExpression(), null, "leftInput", null, 1, 1, BetaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBetaOperator_RightInput(), this.getAlgebraExpression(), null, "rightInput", null, 1, 1, BetaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(antiJoinOperatorEClass, AntiJoinOperator.class, "AntiJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productionOperatorEClass, ProductionOperator.class, "ProductionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(filterOperatorEClass, FilterOperator.class, "FilterOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expandOperatorEClass, ExpandOperator.class, "ExpandOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExpandOperator_Direction(), this.getDirection(), "direction", "IN", 0, 1, ExpandOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExpandOperator_EdgeVariable(), this.getEdgeVariable(), null, "edgeVariable", null, 1, 1, ExpandOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExpandOperator_SourceVertexVariable(), this.getVertexVariable(), null, "sourceVertexVariable", null, 1, 1, ExpandOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExpandOperator_TargetVertexVariable(), this.getVertexVariable(), null, "targetVertexVariable", null, 1, 1, ExpandOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getVerticesOperatorEClass, GetVerticesOperator.class, "GetVerticesOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGetVerticesOperator_VertexVariable(), this.getVertexVariable(), null, "vertexVariable", null, 1, 1, GetVerticesOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(duplicateEliminationOperatorEClass, DuplicateEliminationOperator.class, "DuplicateEliminationOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableEClass, Variable.class, "Variable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariable_DontCare(), ecorePackage.getEBoolean(), "dontCare", "false", 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexVariableEClass, VertexVariable.class, "VertexVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVertexVariable_VertexLabel(), this.getVertexLabel(), null, "vertexLabel", null, 1, 1, VertexVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexVariable_AttributeVariables(), this.getAttributeVariable(), null, "attributeVariables", null, 0, -1, VertexVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeVariableEClass, EdgeVariable.class, "EdgeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdgeVariable_EdgeLabel(), this.getEdgeLabel(), null, "edgeLabel", null, 1, 1, EdgeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeVariable_AttributeVariables(), this.getAttributeVariable(), null, "attributeVariables", null, 0, -1, EdgeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(vertexLabelEClass, VertexLabel.class, "VertexLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(edgeLabelEClass, EdgeLabel.class, "EdgeLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(allDifferentOperatorEClass, AllDifferentOperator.class, "AllDifferentOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAllDifferentOperator_EdgeVariables(), this.getEdgeVariable(), null, "edgeVariables", null, 0, -1, AllDifferentOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeVariableEClass, AttributeVariable.class, "AttributeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(directionEEnum, Direction.class, "Direction");
		addEEnumLiteral(directionEEnum, Direction.BOTH);
		addEEnumLiteral(directionEEnum, Direction.IN);
		addEEnumLiteral(directionEEnum, Direction.OUT);

		// Create resource
		createResource(eNS_URI);
	}

} //RelalgPackageImpl
