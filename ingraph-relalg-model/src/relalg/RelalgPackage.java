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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGEBRA_EXPRESSION__NAME = 0;

	/**
	 * The number of structural features of the '<em>Algebra Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGEBRA_EXPRESSION_FEATURE_COUNT = 1;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATOR__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATOR__PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR__LEFT_PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATOR__RIGHT_PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__NAME = BETA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__LEFT_PARENT = BETA_OPERATOR__LEFT_PARENT;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__RIGHT_PARENT = BETA_OPERATOR__RIGHT_PARENT;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__NAME = BETA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__LEFT_PARENT = BETA_OPERATOR__LEFT_PARENT;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__RIGHT_PARENT = BETA_OPERATOR__RIGHT_PARENT;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

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
	 * The meta object id for the '{@link relalg.impl.FilterOperatorImpl <em>Filter Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.FilterOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getFilterOperator()
	 * @generated
	 */
	int FILTER_OPERATOR = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

	/**
	 * The number of structural features of the '<em>Filter Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATOR_FEATURE_COUNT = ALPHA_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Filter Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATOR_OPERATION_COUNT = ALPHA_OPERATOR_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__VERTEX_VARIABLE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Vertex Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__VERTEX_LABEL = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Get Vertices Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 2;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

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
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DONT_CARE = 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = 0;

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
	int VERTEX_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Vertex Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__VERTEX_LABEL = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

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
	int EDGE_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Edge Label</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__EDGE_LABEL = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

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
	int LABEL__NAME = 0;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__NAME = ALPHA_OPERATOR__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__PARENT = ALPHA_OPERATOR__PARENT;

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
	 * The meta object id for the '{@link relalg.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.Direction
	 * @see relalg.impl.RelalgPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 18;


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
	 * Returns the meta object for the attribute '{@link relalg.AlgebraExpression#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.AlgebraExpression#getName()
	 * @see #getAlgebraExpression()
	 * @generated
	 */
	EAttribute getAlgebraExpression_Name();

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
	 * Returns the meta object for the reference '{@link relalg.AlphaOperator#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see relalg.AlphaOperator#getParent()
	 * @see #getAlphaOperator()
	 * @generated
	 */
	EReference getAlphaOperator_Parent();

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
	 * Returns the meta object for the reference '{@link relalg.BetaOperator#getLeftParent <em>Left Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Parent</em>'.
	 * @see relalg.BetaOperator#getLeftParent()
	 * @see #getBetaOperator()
	 * @generated
	 */
	EReference getBetaOperator_LeftParent();

	/**
	 * Returns the meta object for the reference '{@link relalg.BetaOperator#getRightParent <em>Right Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Parent</em>'.
	 * @see relalg.BetaOperator#getRightParent()
	 * @see #getBetaOperator()
	 * @generated
	 */
	EReference getBetaOperator_RightParent();

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
	 * Returns the meta object for class '{@link relalg.FilterOperator <em>Filter Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Operator</em>'.
	 * @see relalg.FilterOperator
	 * @generated
	 */
	EClass getFilterOperator();

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
	 * Returns the meta object for the reference '{@link relalg.GetVerticesOperator#getVertexLabel <em>Vertex Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Vertex Label</em>'.
	 * @see relalg.GetVerticesOperator#getVertexLabel()
	 * @see #getGetVerticesOperator()
	 * @generated
	 */
	EReference getGetVerticesOperator_VertexLabel();

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
	 * Returns the meta object for the attribute '{@link relalg.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

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
	 * Returns the meta object for the attribute '{@link relalg.Label#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.Label#getName()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Name();

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
	 * Returns the meta object for enum '{@link relalg.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see relalg.Direction
	 * @generated
	 */
	EEnum getDirection();

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
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALGEBRA_EXPRESSION__NAME = eINSTANCE.getAlgebraExpression_Name();

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
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALPHA_OPERATOR__PARENT = eINSTANCE.getAlphaOperator_Parent();

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
		 * The meta object literal for the '<em><b>Left Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATOR__LEFT_PARENT = eINSTANCE.getBetaOperator_LeftParent();

		/**
		 * The meta object literal for the '<em><b>Right Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATOR__RIGHT_PARENT = eINSTANCE.getBetaOperator_RightParent();

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
		 * The meta object literal for the '{@link relalg.impl.FilterOperatorImpl <em>Filter Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.FilterOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getFilterOperator()
		 * @generated
		 */
		EClass FILTER_OPERATOR = eINSTANCE.getFilterOperator();

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
		 * The meta object literal for the '<em><b>Vertex Label</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GET_VERTICES_OPERATOR__VERTEX_LABEL = eINSTANCE.getGetVerticesOperator_VertexLabel();

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
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

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
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__NAME = eINSTANCE.getLabel_Name();

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
		 * The meta object literal for the '{@link relalg.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.Direction
		 * @see relalg.impl.RelalgPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

	}

} //RelalgPackage
