/**
 */
package relalg;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see relalg.RelalgFactory
 * @model kind="package"
 * @generated
 */
public interface RelalgPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "relalg";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/relalg";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "relalg";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RelalgPackage eINSTANCE = relalg.impl.RelalgPackageImpl.init();

	/**
	 * The meta object id for the '{@link relalg.impl.AlgebraExpressionImpl <em>Algebra Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AlgebraExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getAlgebraExpression()
	 * @generated
	 */
	int ALGEBRA_EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Algebra Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGEBRA_EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Algebra Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGEBRA_EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AlphaOperatorImpl <em>Alpha Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AlphaOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAlphaOperator()
	 * @generated
	 */
	int ALPHA_OPERATOR = 3;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATOR__INPUT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Alpha Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATOR_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Alpha Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATOR_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ProjectionOperatorImpl <em>Projection Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ProjectionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getProjectionOperator()
	 * @generated
	 */
	int PROJECTION_OPERATOR = 1;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__VARIABLES = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Projection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Projection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BetaOperatorImpl <em>Beta Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BetaOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getBetaOperator()
	 * @generated
	 */
	int BETA_OPERATOR = 4;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR__LEFT_INPUT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR__RIGHT_INPUT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Beta Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Beta Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.JoinOperatorImpl <em>Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.JoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getJoinOperator()
	 * @generated
	 */
	int JOIN_OPERATOR = 2;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__LEFT_INPUT = BETA_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__RIGHT_INPUT = BETA_OPERATOR__RIGHT_INPUT;

	/**
	 * The number of structural features of the '<em>Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR_FEATURE_COUNT = BETA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR_OPERATION_COUNT = BETA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AntiJoinOperatorImpl <em>Anti Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AntiJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAntiJoinOperator()
	 * @generated
	 */
	int ANTI_JOIN_OPERATOR = 5;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__LEFT_INPUT = BETA_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__RIGHT_INPUT = BETA_OPERATOR__RIGHT_INPUT;

	/**
	 * The number of structural features of the '<em>Anti Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR_FEATURE_COUNT = BETA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Anti Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR_OPERATION_COUNT = BETA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ProductionOperatorImpl <em>Production Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ProductionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getProductionOperator()
	 * @generated
	 */
	int PRODUCTION_OPERATOR = 6;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The number of structural features of the '<em>Production Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Production Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SelectionOperatorImpl <em>Selection Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SelectionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getSelectionOperator()
	 * @generated
	 */
	int SELECTION_OPERATOR = 7;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__CONDITION_STRING = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__CONDITION = ALPHA_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Selection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Selection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ExpandOperatorImpl <em>Expand Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ExpandOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getExpandOperator()
	 * @generated
	 */
	int EXPAND_OPERATOR = 8;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__DIRECTION = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__EDGE_VARIABLE = ALPHA_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE = ALPHA_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE = ALPHA_OPERATOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Expand Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Expand Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.GetVerticesOperatorImpl <em>Get Vertices Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GetVerticesOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getGetVerticesOperator()
	 * @generated
	 */
	int GET_VERTICES_OPERATOR = 9;

	/**
	 * The feature id for the '<em><b>Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__VERTEX_VARIABLE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Get Vertices Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Get Vertices Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DuplicateEliminationOperatorImpl <em>Duplicate Elimination Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DuplicateEliminationOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getDuplicateEliminationOperator()
	 * @generated
	 */
	int DUPLICATE_ELIMINATION_OPERATOR = 10;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The number of structural features of the '<em>Duplicate Elimination Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Duplicate Elimination Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NamedElementImpl
	 * @see relalg.impl.RelalgPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__CONTAINER = 1;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__CONTAINER = NAMED_ELEMENT__CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DONT_CARE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ElementVariableImpl <em>Element Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ElementVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getElementVariable()
	 * @generated
	 */
	int ELEMENT_VARIABLE = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__CONTAINER = VARIABLE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__ATTRIBUTES = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Element Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VertexVariableImpl <em>Vertex Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VertexVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getVertexVariable()
	 * @generated
	 */
	int VERTEX_VARIABLE = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__NAME = ELEMENT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__CONTAINER = ELEMENT_VARIABLE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__DONT_CARE = ELEMENT_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__ATTRIBUTES = ELEMENT_VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Vertex Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__VERTEX_LABEL = ELEMENT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_FEATURE_COUNT = ELEMENT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_OPERATION_COUNT = ELEMENT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EdgeVariableImpl <em>Edge Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EdgeVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getEdgeVariable()
	 * @generated
	 */
	int EDGE_VARIABLE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__NAME = ELEMENT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__CONTAINER = ELEMENT_VARIABLE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__DONT_CARE = ELEMENT_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__ATTRIBUTES = ELEMENT_VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Edge Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__EDGE_LABEL = ELEMENT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_FEATURE_COUNT = ELEMENT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_OPERATION_COUNT = ELEMENT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LabelImpl
	 * @see relalg.impl.RelalgPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__CONTAINER = NAMED_ELEMENT__CONTAINER;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VertexLabelImpl <em>Vertex Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VertexLabelImpl
	 * @see relalg.impl.RelalgPackageImpl#getVertexLabel()
	 * @generated
	 */
	int VERTEX_LABEL = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL__NAME = LABEL__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL__CONTAINER = LABEL__CONTAINER;

	/**
	 * The number of structural features of the '<em>Vertex Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Vertex Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EdgeLabelImpl <em>Edge Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EdgeLabelImpl
	 * @see relalg.impl.RelalgPackageImpl#getEdgeLabel()
	 * @generated
	 */
	int EDGE_LABEL = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL__NAME = LABEL__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL__CONTAINER = LABEL__CONTAINER;

	/**
	 * The number of structural features of the '<em>Edge Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Edge Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AllDifferentOperatorImpl <em>All Different Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AllDifferentOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAllDifferentOperator()
	 * @generated
	 */
	int ALL_DIFFERENT_OPERATOR = 17;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__INPUT = ALPHA_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Edge Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>All Different Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>All Different Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AttributeVariableImpl <em>Attribute Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AttributeVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getAttributeVariable()
	 * @generated
	 */
	int ATTRIBUTE_VARIABLE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__CONTAINER = VARIABLE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__ELEMENT = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnionOperatorImpl <em>Union Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnionOperator()
	 * @generated
	 */
	int UNION_OPERATOR = 20;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__LEFT_INPUT = BETA_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__RIGHT_INPUT = BETA_OPERATOR__RIGHT_INPUT;

	/**
	 * The number of structural features of the '<em>Union Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR_FEATURE_COUNT = BETA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Union Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR_OPERATION_COUNT = BETA_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ContainerImpl
	 * @see relalg.impl.RelalgPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 21;

	/**
	 * The feature id for the '<em><b>Root Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ROOT_EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ELEMENTS = 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ReturnableElementImpl <em>Returnable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ReturnableElementImpl
	 * @see relalg.impl.RelalgPackageImpl#getReturnableElement()
	 * @generated
	 */
	int RETURNABLE_ELEMENT = 23;

	/**
	 * The number of structural features of the '<em>Returnable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURNABLE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Returnable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURNABLE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 22;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TEXT = RETURNABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = RETURNABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = RETURNABLE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryExpression()
	 * @generated
	 */
	int BINARY_EXPRESSION = 24;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ArithmeticOperationExpressionImpl <em>Arithmetic Operation Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ArithmeticOperationExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticOperationExpression()
	 * @generated
	 */
	int ARITHMETIC_OPERATION_EXPRESSION = 25;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION__TEXT = BINARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_OPERATION_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryLogicalExpressionImpl <em>Binary Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryLogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalExpression()
	 * @generated
	 */
	int BINARY_LOGICAL_EXPRESSION = 26;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__TEXT = BINARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__OPERATOR = BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Binary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ComparisonExpressionImpl <em>Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ComparisonExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getComparisonExpression()
	 * @generated
	 */
	int COMPARISON_EXPRESSION = 39;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_EXPRESSION__TEXT = BINARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ArithmeticComparisonExpressionImpl <em>Arithmetic Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ArithmeticComparisonExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonExpression()
	 * @generated
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION = 27;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__TEXT = COMPARISON_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND = COMPARISON_EXPRESSION__LEFT_OPERAND;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND = COMPARISON_EXPRESSION__RIGHT_OPERAND;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__OPERATOR = COMPARISON_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND = COMPARISON_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND = COMPARISON_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Arithmetic Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION_FEATURE_COUNT = COMPARISON_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Arithmetic Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION_OPERATION_COUNT = COMPARISON_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryExpression()
	 * @generated
	 */
	int UNARY_EXPRESSION = 28;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__NEGATED = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__OPERAND = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.StringComparisonExpressionImpl <em>String Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.StringComparisonExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getStringComparisonExpression()
	 * @generated
	 */
	int STRING_COMPARISON_EXPRESSION = 29;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__TEXT = COMPARISON_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__LEFT_OPERAND = COMPARISON_EXPRESSION__LEFT_OPERAND;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__RIGHT_OPERAND = COMPARISON_EXPRESSION__RIGHT_OPERAND;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION__OPERATOR = COMPARISON_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION_FEATURE_COUNT = COMPARISON_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_COMPARISON_EXPRESSION_OPERATION_COUNT = COMPARISON_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AtomImpl <em>Atom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AtomImpl
	 * @see relalg.impl.RelalgPackageImpl#getAtom()
	 * @generated
	 */
	int ATOM = 37;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Atom</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Atom</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.LiteralImpl <em>Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getLiteral()
	 * @generated
	 */
	int LITERAL = 30;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL__TEXT = ATOM__TEXT;

	/**
	 * The number of structural features of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_FEATURE_COUNT = ATOM_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_OPERATION_COUNT = ATOM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NumberLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getNumberLiteral()
	 * @generated
	 */
	int NUMBER_LITERAL = 31;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__TEXT = LITERAL__TEXT;

	/**
	 * The number of structural features of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.StringLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 32;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__TEXT = LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DoubleLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getDoubleLiteral()
	 * @generated
	 */
	int DOUBLE_LITERAL = 33;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__TEXT = NUMBER_LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__VALUE = NUMBER_LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_FEATURE_COUNT = NUMBER_LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.IntegerLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 34;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__TEXT = NUMBER_LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__VALUE = NUMBER_LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_FEATURE_COUNT = NUMBER_LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.GetEdgesOperatorImpl <em>Get Edges Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GetEdgesOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getGetEdgesOperator()
	 * @generated
	 */
	int GET_EDGES_OPERATOR = 35;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__EDGE_VARIABLE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Get Edges Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Get Edges Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ComparableImpl <em>Comparable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ComparableImpl
	 * @see relalg.impl.RelalgPackageImpl#getComparable()
	 * @generated
	 */
	int COMPARABLE = 36;

	/**
	 * The number of structural features of the '<em>Comparable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Comparable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.Direction
	 * @see relalg.impl.RelalgPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 40;

	/**
	 * The meta object id for the '{@link relalg.ArithmeticComparisonOperator <em>Arithmetic Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.ArithmeticComparisonOperator
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonOperator()
	 * @generated
	 */
	int ARITHMETIC_COMPARISON_OPERATOR = 41;

	/**
	 * The meta object id for the '{@link relalg.StringComparisonOperator <em>String Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.StringComparisonOperator
	 * @see relalg.impl.RelalgPackageImpl#getStringComparisonOperator()
	 * @generated
	 */
	int STRING_COMPARISON_OPERATOR = 42;

	/**
	 * The meta object id for the '{@link relalg.BinaryLogicalOperator <em>Binary Logical Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.BinaryLogicalOperator
	 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalOperator()
	 * @generated
	 */
	int BINARY_LOGICAL_OPERATOR = 43;

	/**
	 * The meta object id for the '{@link relalg.BinaryArithmeticOperator <em>Binary Arithmetic Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.BinaryArithmeticOperator
	 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperator()
	 * @generated
	 */
	int BINARY_ARITHMETIC_OPERATOR = 44;

	/**
	 * The meta object id for the '{@link relalg.UnaryArithmeticOperator <em>Unary Arithmetic Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.UnaryArithmeticOperator
	 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperator()
	 * @generated
	 */
	int UNARY_ARITHMETIC_OPERATOR = 45;

	/**
	 * The meta object id for the '{@link relalg.Order <em>Order</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.Order
	 * @see relalg.impl.RelalgPackageImpl#getOrder()
	 * @generated
	 */
	int ORDER = 46;


	/**
	 * Returns the meta object for class '{@link relalg.AlgebraExpression <em>Algebra Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Algebra Expression</em>'.
	 * @see relalg.AlgebraExpression
	 * @generated
	 */
	EClass getAlgebraExpression();

	/**
	 * Returns the meta object for class '{@link relalg.ProjectionOperator <em>Projection Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Projection Operator</em>'.
	 * @see relalg.ProjectionOperator
	 * @generated
	 */
	EClass getProjectionOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.ProjectionOperator#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variables</em>'.
	 * @see relalg.ProjectionOperator#getVariables()
	 * @see #getProjectionOperator()
	 * @generated
	 */
	EReference getProjectionOperator_Variables();

	/**
	 * Returns the meta object for class '{@link relalg.JoinOperator <em>Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Operator</em>'.
	 * @see relalg.JoinOperator
	 * @generated
	 */
	EClass getJoinOperator();

	/**
	 * Returns the meta object for class '{@link relalg.AlphaOperator <em>Alpha Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alpha Operator</em>'.
	 * @see relalg.AlphaOperator
	 * @generated
	 */
	EClass getAlphaOperator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.AlphaOperator#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input</em>'.
	 * @see relalg.AlphaOperator#getInput()
	 * @see #getAlphaOperator()
	 * @generated
	 */
	EReference getAlphaOperator_Input();

	/**
	 * Returns the meta object for class '{@link relalg.BetaOperator <em>Beta Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Beta Operator</em>'.
	 * @see relalg.BetaOperator
	 * @generated
	 */
	EClass getBetaOperator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BetaOperator#getLeftInput <em>Left Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Input</em>'.
	 * @see relalg.BetaOperator#getLeftInput()
	 * @see #getBetaOperator()
	 * @generated
	 */
	EReference getBetaOperator_LeftInput();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BetaOperator#getRightInput <em>Right Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Input</em>'.
	 * @see relalg.BetaOperator#getRightInput()
	 * @see #getBetaOperator()
	 * @generated
	 */
	EReference getBetaOperator_RightInput();

	/**
	 * Returns the meta object for class '{@link relalg.AntiJoinOperator <em>Anti Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anti Join Operator</em>'.
	 * @see relalg.AntiJoinOperator
	 * @generated
	 */
	EClass getAntiJoinOperator();

	/**
	 * Returns the meta object for class '{@link relalg.ProductionOperator <em>Production Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Production Operator</em>'.
	 * @see relalg.ProductionOperator
	 * @generated
	 */
	EClass getProductionOperator();

	/**
	 * Returns the meta object for class '{@link relalg.SelectionOperator <em>Selection Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Selection Operator</em>'.
	 * @see relalg.SelectionOperator
	 * @generated
	 */
	EClass getSelectionOperator();

	/**
	 * Returns the meta object for the attribute '{@link relalg.SelectionOperator#getConditionString <em>Condition String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition String</em>'.
	 * @see relalg.SelectionOperator#getConditionString()
	 * @see #getSelectionOperator()
	 * @generated
	 */
	EAttribute getSelectionOperator_ConditionString();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.SelectionOperator#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see relalg.SelectionOperator#getCondition()
	 * @see #getSelectionOperator()
	 * @generated
	 */
	EReference getSelectionOperator_Condition();

	/**
	 * Returns the meta object for class '{@link relalg.ExpandOperator <em>Expand Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expand Operator</em>'.
	 * @see relalg.ExpandOperator
	 * @generated
	 */
	EClass getExpandOperator();

	/**
	 * Returns the meta object for the attribute '{@link relalg.ExpandOperator#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see relalg.ExpandOperator#getDirection()
	 * @see #getExpandOperator()
	 * @generated
	 */
	EAttribute getExpandOperator_Direction();

	/**
	 * Returns the meta object for the reference '{@link relalg.ExpandOperator#getEdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Edge Variable</em>'.
	 * @see relalg.ExpandOperator#getEdgeVariable()
	 * @see #getExpandOperator()
	 * @generated
	 */
	EReference getExpandOperator_EdgeVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.ExpandOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Vertex Variable</em>'.
	 * @see relalg.ExpandOperator#getSourceVertexVariable()
	 * @see #getExpandOperator()
	 * @generated
	 */
	EReference getExpandOperator_SourceVertexVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.ExpandOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Vertex Variable</em>'.
	 * @see relalg.ExpandOperator#getTargetVertexVariable()
	 * @see #getExpandOperator()
	 * @generated
	 */
	EReference getExpandOperator_TargetVertexVariable();

	/**
	 * Returns the meta object for class '{@link relalg.GetVerticesOperator <em>Get Vertices Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Vertices Operator</em>'.
	 * @see relalg.GetVerticesOperator
	 * @generated
	 */
	EClass getGetVerticesOperator();

	/**
	 * Returns the meta object for the reference '{@link relalg.GetVerticesOperator#getVertexVariable <em>Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Vertex Variable</em>'.
	 * @see relalg.GetVerticesOperator#getVertexVariable()
	 * @see #getGetVerticesOperator()
	 * @generated
	 */
	EReference getGetVerticesOperator_VertexVariable();

	/**
	 * Returns the meta object for class '{@link relalg.DuplicateEliminationOperator <em>Duplicate Elimination Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Duplicate Elimination Operator</em>'.
	 * @see relalg.DuplicateEliminationOperator
	 * @generated
	 */
	EClass getDuplicateEliminationOperator();

	/**
	 * Returns the meta object for class '{@link relalg.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see relalg.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link relalg.Variable#isDontCare <em>Dont Care</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dont Care</em>'.
	 * @see relalg.Variable#isDontCare()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_DontCare();

	/**
	 * Returns the meta object for class '{@link relalg.VertexVariable <em>Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Variable</em>'.
	 * @see relalg.VertexVariable
	 * @generated
	 */
	EClass getVertexVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.VertexVariable#getVertexLabel <em>Vertex Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Vertex Label</em>'.
	 * @see relalg.VertexVariable#getVertexLabel()
	 * @see #getVertexVariable()
	 * @generated
	 */
	EReference getVertexVariable_VertexLabel();

	/**
	 * Returns the meta object for class '{@link relalg.EdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Variable</em>'.
	 * @see relalg.EdgeVariable
	 * @generated
	 */
	EClass getEdgeVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.EdgeVariable#getEdgeLabel <em>Edge Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Edge Label</em>'.
	 * @see relalg.EdgeVariable#getEdgeLabel()
	 * @see #getEdgeVariable()
	 * @generated
	 */
	EReference getEdgeVariable_EdgeLabel();

	/**
	 * Returns the meta object for class '{@link relalg.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see relalg.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for class '{@link relalg.VertexLabel <em>Vertex Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Label</em>'.
	 * @see relalg.VertexLabel
	 * @generated
	 */
	EClass getVertexLabel();

	/**
	 * Returns the meta object for class '{@link relalg.EdgeLabel <em>Edge Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Label</em>'.
	 * @see relalg.EdgeLabel
	 * @generated
	 */
	EClass getEdgeLabel();

	/**
	 * Returns the meta object for class '{@link relalg.AllDifferentOperator <em>All Different Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Different Operator</em>'.
	 * @see relalg.AllDifferentOperator
	 * @generated
	 */
	EClass getAllDifferentOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.AllDifferentOperator#getEdgeVariables <em>Edge Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Edge Variables</em>'.
	 * @see relalg.AllDifferentOperator#getEdgeVariables()
	 * @see #getAllDifferentOperator()
	 * @generated
	 */
	EReference getAllDifferentOperator_EdgeVariables();

	/**
	 * Returns the meta object for class '{@link relalg.AttributeVariable <em>Attribute Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Variable</em>'.
	 * @see relalg.AttributeVariable
	 * @generated
	 */
	EClass getAttributeVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.AttributeVariable#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see relalg.AttributeVariable#getElement()
	 * @see #getAttributeVariable()
	 * @generated
	 */
	EReference getAttributeVariable_Element();

	/**
	 * Returns the meta object for class '{@link relalg.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see relalg.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link relalg.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for the container reference '{@link relalg.NamedElement#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see relalg.NamedElement#getContainer()
	 * @see #getNamedElement()
	 * @generated
	 */
	EReference getNamedElement_Container();

	/**
	 * Returns the meta object for class '{@link relalg.UnionOperator <em>Union Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Operator</em>'.
	 * @see relalg.UnionOperator
	 * @generated
	 */
	EClass getUnionOperator();

	/**
	 * Returns the meta object for class '{@link relalg.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see relalg.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.Container#getRootExpression <em>Root Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Expression</em>'.
	 * @see relalg.Container#getRootExpression()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_RootExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.Container#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see relalg.Container#getElements()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Elements();

	/**
	 * Returns the meta object for class '{@link relalg.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see relalg.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.Expression#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see relalg.Expression#getText()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_Text();

	/**
	 * Returns the meta object for class '{@link relalg.ReturnableElement <em>Returnable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Returnable Element</em>'.
	 * @see relalg.ReturnableElement
	 * @generated
	 */
	EClass getReturnableElement();

	/**
	 * Returns the meta object for class '{@link relalg.BinaryExpression <em>Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Expression</em>'.
	 * @see relalg.BinaryExpression
	 * @generated
	 */
	EClass getBinaryExpression();

	/**
	 * Returns the meta object for class '{@link relalg.ArithmeticOperationExpression <em>Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arithmetic Operation Expression</em>'.
	 * @see relalg.ArithmeticOperationExpression
	 * @generated
	 */
	EClass getArithmeticOperationExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.ArithmeticOperationExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.ArithmeticOperationExpression#getOperator()
	 * @see #getArithmeticOperationExpression()
	 * @generated
	 */
	EAttribute getArithmeticOperationExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see relalg.ArithmeticOperationExpression#getRightOperand()
	 * @see #getArithmeticOperationExpression()
	 * @generated
	 */
	EReference getArithmeticOperationExpression_RightOperand();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see relalg.ArithmeticOperationExpression#getLeftOperand()
	 * @see #getArithmeticOperationExpression()
	 * @generated
	 */
	EReference getArithmeticOperationExpression_LeftOperand();

	/**
	 * Returns the meta object for class '{@link relalg.BinaryLogicalExpression <em>Binary Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Logical Expression</em>'.
	 * @see relalg.BinaryLogicalExpression
	 * @generated
	 */
	EClass getBinaryLogicalExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.BinaryLogicalExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.BinaryLogicalExpression#getOperator()
	 * @see #getBinaryLogicalExpression()
	 * @generated
	 */
	EAttribute getBinaryLogicalExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BinaryLogicalExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see relalg.BinaryLogicalExpression#getRightOperand()
	 * @see #getBinaryLogicalExpression()
	 * @generated
	 */
	EReference getBinaryLogicalExpression_RightOperand();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BinaryLogicalExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see relalg.BinaryLogicalExpression#getLeftOperand()
	 * @see #getBinaryLogicalExpression()
	 * @generated
	 */
	EReference getBinaryLogicalExpression_LeftOperand();

	/**
	 * Returns the meta object for class '{@link relalg.ArithmeticComparisonExpression <em>Arithmetic Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arithmetic Comparison Expression</em>'.
	 * @see relalg.ArithmeticComparisonExpression
	 * @generated
	 */
	EClass getArithmeticComparisonExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.ArithmeticComparisonExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.ArithmeticComparisonExpression#getOperator()
	 * @see #getArithmeticComparisonExpression()
	 * @generated
	 */
	EAttribute getArithmeticComparisonExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ArithmeticComparisonExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see relalg.ArithmeticComparisonExpression#getRightOperand()
	 * @see #getArithmeticComparisonExpression()
	 * @generated
	 */
	EReference getArithmeticComparisonExpression_RightOperand();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ArithmeticComparisonExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see relalg.ArithmeticComparisonExpression#getLeftOperand()
	 * @see #getArithmeticComparisonExpression()
	 * @generated
	 */
	EReference getArithmeticComparisonExpression_LeftOperand();

	/**
	 * Returns the meta object for class '{@link relalg.UnaryExpression <em>Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Expression</em>'.
	 * @see relalg.UnaryExpression
	 * @generated
	 */
	EClass getUnaryExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.UnaryExpression#isNegated <em>Negated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Negated</em>'.
	 * @see relalg.UnaryExpression#isNegated()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EAttribute getUnaryExpression_Negated();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.UnaryExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see relalg.UnaryExpression#getOperand()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EReference getUnaryExpression_Operand();

	/**
	 * Returns the meta object for class '{@link relalg.StringComparisonExpression <em>String Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Comparison Expression</em>'.
	 * @see relalg.StringComparisonExpression
	 * @generated
	 */
	EClass getStringComparisonExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.StringComparisonExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.StringComparisonExpression#getOperator()
	 * @see #getStringComparisonExpression()
	 * @generated
	 */
	EAttribute getStringComparisonExpression_Operator();

	/**
	 * Returns the meta object for class '{@link relalg.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see relalg.Literal
	 * @generated
	 */
	EClass getLiteral();

	/**
	 * Returns the meta object for class '{@link relalg.Atom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atom</em>'.
	 * @see relalg.Atom
	 * @generated
	 */
	EClass getAtom();

	/**
	 * Returns the meta object for class '{@link relalg.ElementVariable <em>Element Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Variable</em>'.
	 * @see relalg.ElementVariable
	 * @generated
	 */
	EClass getElementVariable();

	/**
	 * Returns the meta object for the reference list '{@link relalg.ElementVariable#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attributes</em>'.
	 * @see relalg.ElementVariable#getAttributes()
	 * @see #getElementVariable()
	 * @generated
	 */
	EReference getElementVariable_Attributes();

	/**
	 * Returns the meta object for class '{@link relalg.ComparisonExpression <em>Comparison Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison Expression</em>'.
	 * @see relalg.ComparisonExpression
	 * @generated
	 */
	EClass getComparisonExpression();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ComparisonExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see relalg.ComparisonExpression#getLeftOperand()
	 * @see #getComparisonExpression()
	 * @generated
	 */
	EReference getComparisonExpression_LeftOperand();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.ComparisonExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see relalg.ComparisonExpression#getRightOperand()
	 * @see #getComparisonExpression()
	 * @generated
	 */
	EReference getComparisonExpression_RightOperand();

	/**
	 * Returns the meta object for class '{@link relalg.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Literal</em>'.
	 * @see relalg.NumberLiteral
	 * @generated
	 */
	EClass getNumberLiteral();

	/**
	 * Returns the meta object for class '{@link relalg.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see relalg.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link relalg.StringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.StringLiteral#getValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link relalg.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Literal</em>'.
	 * @see relalg.DoubleLiteral
	 * @generated
	 */
	EClass getDoubleLiteral();

	/**
	 * Returns the meta object for the attribute '{@link relalg.DoubleLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.DoubleLiteral#getValue()
	 * @see #getDoubleLiteral()
	 * @generated
	 */
	EAttribute getDoubleLiteral_Value();

	/**
	 * Returns the meta object for class '{@link relalg.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal</em>'.
	 * @see relalg.IntegerLiteral
	 * @generated
	 */
	EClass getIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link relalg.IntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.IntegerLiteral#getValue()
	 * @see #getIntegerLiteral()
	 * @generated
	 */
	EAttribute getIntegerLiteral_Value();

	/**
	 * Returns the meta object for class '{@link relalg.GetEdgesOperator <em>Get Edges Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Edges Operator</em>'.
	 * @see relalg.GetEdgesOperator
	 * @generated
	 */
	EClass getGetEdgesOperator();

	/**
	 * Returns the meta object for the reference '{@link relalg.GetEdgesOperator#getSourceVertexVariable <em>Source Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Vertex Variable</em>'.
	 * @see relalg.GetEdgesOperator#getSourceVertexVariable()
	 * @see #getGetEdgesOperator()
	 * @generated
	 */
	EReference getGetEdgesOperator_SourceVertexVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.GetEdgesOperator#getTargetVertexVariable <em>Target Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Vertex Variable</em>'.
	 * @see relalg.GetEdgesOperator#getTargetVertexVariable()
	 * @see #getGetEdgesOperator()
	 * @generated
	 */
	EReference getGetEdgesOperator_TargetVertexVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.GetEdgesOperator#getEdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Edge Variable</em>'.
	 * @see relalg.GetEdgesOperator#getEdgeVariable()
	 * @see #getGetEdgesOperator()
	 * @generated
	 */
	EReference getGetEdgesOperator_EdgeVariable();

	/**
	 * Returns the meta object for class '{@link relalg.Comparable <em>Comparable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparable</em>'.
	 * @see relalg.Comparable
	 * @generated
	 */
	EClass getComparable();

	/**
	 * Returns the meta object for enum '{@link relalg.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see relalg.Direction
	 * @generated
	 */
	EEnum getDirection();

	/**
	 * Returns the meta object for enum '{@link relalg.ArithmeticComparisonOperator <em>Arithmetic Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arithmetic Comparison Operator</em>'.
	 * @see relalg.ArithmeticComparisonOperator
	 * @generated
	 */
	EEnum getArithmeticComparisonOperator();

	/**
	 * Returns the meta object for enum '{@link relalg.StringComparisonOperator <em>String Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>String Comparison Operator</em>'.
	 * @see relalg.StringComparisonOperator
	 * @generated
	 */
	EEnum getStringComparisonOperator();

	/**
	 * Returns the meta object for enum '{@link relalg.BinaryLogicalOperator <em>Binary Logical Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binary Logical Operator</em>'.
	 * @see relalg.BinaryLogicalOperator
	 * @generated
	 */
	EEnum getBinaryLogicalOperator();

	/**
	 * Returns the meta object for enum '{@link relalg.BinaryArithmeticOperator <em>Binary Arithmetic Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binary Arithmetic Operator</em>'.
	 * @see relalg.BinaryArithmeticOperator
	 * @generated
	 */
	EEnum getBinaryArithmeticOperator();

	/**
	 * Returns the meta object for enum '{@link relalg.UnaryArithmeticOperator <em>Unary Arithmetic Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Arithmetic Operator</em>'.
	 * @see relalg.UnaryArithmeticOperator
	 * @generated
	 */
	EEnum getUnaryArithmeticOperator();

	/**
	 * Returns the meta object for enum '{@link relalg.Order <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Order</em>'.
	 * @see relalg.Order
	 * @generated
	 */
	EEnum getOrder();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RelalgFactory getRelalgFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link relalg.impl.AlgebraExpressionImpl <em>Algebra Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AlgebraExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getAlgebraExpression()
		 * @generated
		 */
		EClass ALGEBRA_EXPRESSION = eINSTANCE.getAlgebraExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.ProjectionOperatorImpl <em>Projection Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ProjectionOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getProjectionOperator()
		 * @generated
		 */
		EClass PROJECTION_OPERATOR = eINSTANCE.getProjectionOperator();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECTION_OPERATOR__VARIABLES = eINSTANCE.getProjectionOperator_Variables();

		/**
		 * The meta object literal for the '{@link relalg.impl.JoinOperatorImpl <em>Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.JoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getJoinOperator()
		 * @generated
		 */
		EClass JOIN_OPERATOR = eINSTANCE.getJoinOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.AlphaOperatorImpl <em>Alpha Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AlphaOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getAlphaOperator()
		 * @generated
		 */
		EClass ALPHA_OPERATOR = eINSTANCE.getAlphaOperator();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALPHA_OPERATOR__INPUT = eINSTANCE.getAlphaOperator_Input();

		/**
		 * The meta object literal for the '{@link relalg.impl.BetaOperatorImpl <em>Beta Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BetaOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getBetaOperator()
		 * @generated
		 */
		EClass BETA_OPERATOR = eINSTANCE.getBetaOperator();

		/**
		 * The meta object literal for the '<em><b>Left Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATOR__LEFT_INPUT = eINSTANCE.getBetaOperator_LeftInput();

		/**
		 * The meta object literal for the '<em><b>Right Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATOR__RIGHT_INPUT = eINSTANCE.getBetaOperator_RightInput();

		/**
		 * The meta object literal for the '{@link relalg.impl.AntiJoinOperatorImpl <em>Anti Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AntiJoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getAntiJoinOperator()
		 * @generated
		 */
		EClass ANTI_JOIN_OPERATOR = eINSTANCE.getAntiJoinOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.ProductionOperatorImpl <em>Production Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ProductionOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getProductionOperator()
		 * @generated
		 */
		EClass PRODUCTION_OPERATOR = eINSTANCE.getProductionOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.SelectionOperatorImpl <em>Selection Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SelectionOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getSelectionOperator()
		 * @generated
		 */
		EClass SELECTION_OPERATOR = eINSTANCE.getSelectionOperator();

		/**
		 * The meta object literal for the '<em><b>Condition String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SELECTION_OPERATOR__CONDITION_STRING = eINSTANCE.getSelectionOperator_ConditionString();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SELECTION_OPERATOR__CONDITION = eINSTANCE.getSelectionOperator_Condition();

		/**
		 * The meta object literal for the '{@link relalg.impl.ExpandOperatorImpl <em>Expand Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ExpandOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getExpandOperator()
		 * @generated
		 */
		EClass EXPAND_OPERATOR = eINSTANCE.getExpandOperator();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPAND_OPERATOR__DIRECTION = eINSTANCE.getExpandOperator_Direction();

		/**
		 * The meta object literal for the '<em><b>Edge Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPAND_OPERATOR__EDGE_VARIABLE = eINSTANCE.getExpandOperator_EdgeVariable();

		/**
		 * The meta object literal for the '<em><b>Source Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE = eINSTANCE.getExpandOperator_SourceVertexVariable();

		/**
		 * The meta object literal for the '<em><b>Target Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE = eINSTANCE.getExpandOperator_TargetVertexVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.GetVerticesOperatorImpl <em>Get Vertices Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.GetVerticesOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getGetVerticesOperator()
		 * @generated
		 */
		EClass GET_VERTICES_OPERATOR = eINSTANCE.getGetVerticesOperator();

		/**
		 * The meta object literal for the '<em><b>Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_VERTICES_OPERATOR__VERTEX_VARIABLE = eINSTANCE.getGetVerticesOperator_VertexVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.DuplicateEliminationOperatorImpl <em>Duplicate Elimination Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.DuplicateEliminationOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getDuplicateEliminationOperator()
		 * @generated
		 */
		EClass DUPLICATE_ELIMINATION_OPERATOR = eINSTANCE.getDuplicateEliminationOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Dont Care</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__DONT_CARE = eINSTANCE.getVariable_DontCare();

		/**
		 * The meta object literal for the '{@link relalg.impl.VertexVariableImpl <em>Vertex Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VertexVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getVertexVariable()
		 * @generated
		 */
		EClass VERTEX_VARIABLE = eINSTANCE.getVertexVariable();

		/**
		 * The meta object literal for the '<em><b>Vertex Label</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX_VARIABLE__VERTEX_LABEL = eINSTANCE.getVertexVariable_VertexLabel();

		/**
		 * The meta object literal for the '{@link relalg.impl.EdgeVariableImpl <em>Edge Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EdgeVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getEdgeVariable()
		 * @generated
		 */
		EClass EDGE_VARIABLE = eINSTANCE.getEdgeVariable();

		/**
		 * The meta object literal for the '<em><b>Edge Label</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE_VARIABLE__EDGE_LABEL = eINSTANCE.getEdgeVariable_EdgeLabel();

		/**
		 * The meta object literal for the '{@link relalg.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LabelImpl
		 * @see relalg.impl.RelalgPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '{@link relalg.impl.VertexLabelImpl <em>Vertex Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VertexLabelImpl
		 * @see relalg.impl.RelalgPackageImpl#getVertexLabel()
		 * @generated
		 */
		EClass VERTEX_LABEL = eINSTANCE.getVertexLabel();

		/**
		 * The meta object literal for the '{@link relalg.impl.EdgeLabelImpl <em>Edge Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EdgeLabelImpl
		 * @see relalg.impl.RelalgPackageImpl#getEdgeLabel()
		 * @generated
		 */
		EClass EDGE_LABEL = eINSTANCE.getEdgeLabel();

		/**
		 * The meta object literal for the '{@link relalg.impl.AllDifferentOperatorImpl <em>All Different Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AllDifferentOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getAllDifferentOperator()
		 * @generated
		 */
		EClass ALL_DIFFERENT_OPERATOR = eINSTANCE.getAllDifferentOperator();

		/**
		 * The meta object literal for the '<em><b>Edge Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES = eINSTANCE.getAllDifferentOperator_EdgeVariables();

		/**
		 * The meta object literal for the '{@link relalg.impl.AttributeVariableImpl <em>Attribute Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AttributeVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getAttributeVariable()
		 * @generated
		 */
		EClass ATTRIBUTE_VARIABLE = eINSTANCE.getAttributeVariable();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_VARIABLE__ELEMENT = eINSTANCE.getAttributeVariable_Element();

		/**
		 * The meta object literal for the '{@link relalg.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.NamedElementImpl
		 * @see relalg.impl.RelalgPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAMED_ELEMENT__CONTAINER = eINSTANCE.getNamedElement_Container();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnionOperatorImpl <em>Union Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnionOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnionOperator()
		 * @generated
		 */
		EClass UNION_OPERATOR = eINSTANCE.getUnionOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ContainerImpl
		 * @see relalg.impl.RelalgPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Root Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ROOT_EXPRESSION = eINSTANCE.getContainer_RootExpression();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ELEMENTS = eINSTANCE.getContainer_Elements();

		/**
		 * The meta object literal for the '{@link relalg.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__TEXT = eINSTANCE.getExpression_Text();

		/**
		 * The meta object literal for the '{@link relalg.impl.ReturnableElementImpl <em>Returnable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ReturnableElementImpl
		 * @see relalg.impl.RelalgPackageImpl#getReturnableElement()
		 * @generated
		 */
		EClass RETURNABLE_ELEMENT = eINSTANCE.getReturnableElement();

		/**
		 * The meta object literal for the '{@link relalg.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BinaryExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getBinaryExpression()
		 * @generated
		 */
		EClass BINARY_EXPRESSION = eINSTANCE.getBinaryExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.ArithmeticOperationExpressionImpl <em>Arithmetic Operation Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ArithmeticOperationExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getArithmeticOperationExpression()
		 * @generated
		 */
		EClass ARITHMETIC_OPERATION_EXPRESSION = eINSTANCE.getArithmeticOperationExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = eINSTANCE.getArithmeticOperationExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getArithmeticOperationExpression_RightOperand();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND = eINSTANCE.getArithmeticOperationExpression_LeftOperand();

		/**
		 * The meta object literal for the '{@link relalg.impl.BinaryLogicalExpressionImpl <em>Binary Logical Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BinaryLogicalExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalExpression()
		 * @generated
		 */
		EClass BINARY_LOGICAL_EXPRESSION = eINSTANCE.getBinaryLogicalExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_LOGICAL_EXPRESSION__OPERATOR = eINSTANCE.getBinaryLogicalExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getBinaryLogicalExpression_RightOperand();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND = eINSTANCE.getBinaryLogicalExpression_LeftOperand();

		/**
		 * The meta object literal for the '{@link relalg.impl.ArithmeticComparisonExpressionImpl <em>Arithmetic Comparison Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ArithmeticComparisonExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonExpression()
		 * @generated
		 */
		EClass ARITHMETIC_COMPARISON_EXPRESSION = eINSTANCE.getArithmeticComparisonExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARITHMETIC_COMPARISON_EXPRESSION__OPERATOR = eINSTANCE.getArithmeticComparisonExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getArithmeticComparisonExpression_RightOperand();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND = eINSTANCE.getArithmeticComparisonExpression_LeftOperand();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnaryExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnaryExpression()
		 * @generated
		 */
		EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();

		/**
		 * The meta object literal for the '<em><b>Negated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_EXPRESSION__NEGATED = eINSTANCE.getUnaryExpression_Negated();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_EXPRESSION__OPERAND = eINSTANCE.getUnaryExpression_Operand();

		/**
		 * The meta object literal for the '{@link relalg.impl.StringComparisonExpressionImpl <em>String Comparison Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.StringComparisonExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getStringComparisonExpression()
		 * @generated
		 */
		EClass STRING_COMPARISON_EXPRESSION = eINSTANCE.getStringComparisonExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_COMPARISON_EXPRESSION__OPERATOR = eINSTANCE.getStringComparisonExpression_Operator();

		/**
		 * The meta object literal for the '{@link relalg.impl.LiteralImpl <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getLiteral()
		 * @generated
		 */
		EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '{@link relalg.impl.AtomImpl <em>Atom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AtomImpl
		 * @see relalg.impl.RelalgPackageImpl#getAtom()
		 * @generated
		 */
		EClass ATOM = eINSTANCE.getAtom();

		/**
		 * The meta object literal for the '{@link relalg.impl.ElementVariableImpl <em>Element Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ElementVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getElementVariable()
		 * @generated
		 */
		EClass ELEMENT_VARIABLE = eINSTANCE.getElementVariable();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_VARIABLE__ATTRIBUTES = eINSTANCE.getElementVariable_Attributes();

		/**
		 * The meta object literal for the '{@link relalg.impl.ComparisonExpressionImpl <em>Comparison Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ComparisonExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getComparisonExpression()
		 * @generated
		 */
		EClass COMPARISON_EXPRESSION = eINSTANCE.getComparisonExpression();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_EXPRESSION__LEFT_OPERAND = eINSTANCE.getComparisonExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getComparisonExpression_RightOperand();

		/**
		 * The meta object literal for the '{@link relalg.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.NumberLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getNumberLiteral()
		 * @generated
		 */
		EClass NUMBER_LITERAL = eINSTANCE.getNumberLiteral();

		/**
		 * The meta object literal for the '{@link relalg.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.StringLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getStringLiteral()
		 * @generated
		 */
		EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__VALUE = eINSTANCE.getStringLiteral_Value();

		/**
		 * The meta object literal for the '{@link relalg.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.DoubleLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getDoubleLiteral()
		 * @generated
		 */
		EClass DOUBLE_LITERAL = eINSTANCE.getDoubleLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_LITERAL__VALUE = eINSTANCE.getDoubleLiteral_Value();

		/**
		 * The meta object literal for the '{@link relalg.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.IntegerLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getIntegerLiteral()
		 * @generated
		 */
		EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_LITERAL__VALUE = eINSTANCE.getIntegerLiteral_Value();

		/**
		 * The meta object literal for the '{@link relalg.impl.GetEdgesOperatorImpl <em>Get Edges Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.GetEdgesOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getGetEdgesOperator()
		 * @generated
		 */
		EClass GET_EDGES_OPERATOR = eINSTANCE.getGetEdgesOperator();

		/**
		 * The meta object literal for the '<em><b>Source Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE = eINSTANCE.getGetEdgesOperator_SourceVertexVariable();

		/**
		 * The meta object literal for the '<em><b>Target Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE = eINSTANCE.getGetEdgesOperator_TargetVertexVariable();

		/**
		 * The meta object literal for the '<em><b>Edge Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_EDGES_OPERATOR__EDGE_VARIABLE = eINSTANCE.getGetEdgesOperator_EdgeVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.ComparableImpl <em>Comparable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ComparableImpl
		 * @see relalg.impl.RelalgPackageImpl#getComparable()
		 * @generated
		 */
		EClass COMPARABLE = eINSTANCE.getComparable();

		/**
		 * The meta object literal for the '{@link relalg.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.Direction
		 * @see relalg.impl.RelalgPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

		/**
		 * The meta object literal for the '{@link relalg.ArithmeticComparisonOperator <em>Arithmetic Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.ArithmeticComparisonOperator
		 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonOperator()
		 * @generated
		 */
		EEnum ARITHMETIC_COMPARISON_OPERATOR = eINSTANCE.getArithmeticComparisonOperator();

		/**
		 * The meta object literal for the '{@link relalg.StringComparisonOperator <em>String Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.StringComparisonOperator
		 * @see relalg.impl.RelalgPackageImpl#getStringComparisonOperator()
		 * @generated
		 */
		EEnum STRING_COMPARISON_OPERATOR = eINSTANCE.getStringComparisonOperator();

		/**
		 * The meta object literal for the '{@link relalg.BinaryLogicalOperator <em>Binary Logical Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.BinaryLogicalOperator
		 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalOperator()
		 * @generated
		 */
		EEnum BINARY_LOGICAL_OPERATOR = eINSTANCE.getBinaryLogicalOperator();

		/**
		 * The meta object literal for the '{@link relalg.BinaryArithmeticOperator <em>Binary Arithmetic Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.BinaryArithmeticOperator
		 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperator()
		 * @generated
		 */
		EEnum BINARY_ARITHMETIC_OPERATOR = eINSTANCE.getBinaryArithmeticOperator();

		/**
		 * The meta object literal for the '{@link relalg.UnaryArithmeticOperator <em>Unary Arithmetic Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.UnaryArithmeticOperator
		 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperator()
		 * @generated
		 */
		EEnum UNARY_ARITHMETIC_OPERATOR = eINSTANCE.getUnaryArithmeticOperator();

		/**
		 * The meta object literal for the '{@link relalg.Order <em>Order</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.Order
		 * @see relalg.impl.RelalgPackageImpl#getOrder()
		 * @generated
		 */
		EEnum ORDER = eINSTANCE.getOrder();

	}

} //RelalgPackage
