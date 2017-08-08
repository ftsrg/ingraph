/**
 */
package relalg;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
 *        annotation="http://www.eclipse.org/emf/2002/GenModel bundleManifest='false' modelDirectory='ingraph-compiler-relalg-model/build/xcore/main' complianceLevel='8.0'"
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
	String eNS_URI = "http://ingraph/relalg";

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
	 * The meta object id for the '{@link relalg.impl.RelalgContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.RelalgContainerImpl
	 * @see relalg.impl.RelalgPackageImpl#getRelalgContainer()
	 * @generated
	 */
	int RELALG_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Root Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__ROOT_EXPRESSION = 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__ELEMENTS = 2;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__EXPRESSIONS = 3;

	/**
	 * The feature id for the '<em><b>Simplified Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__SIMPLIFIED_PLAN = 4;

	/**
	 * The feature id for the '<em><b>Incremental Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__INCREMENTAL_PLAN = 5;

	/**
	 * The feature id for the '<em><b>External Schema Inferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED = 6;

	/**
	 * The feature id for the '<em><b>Extra Variables Inferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED = 7;

	/**
	 * The feature id for the '<em><b>Internal Schema Inferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED = 8;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.RelalgModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.RelalgModelElementImpl
	 * @see relalg.impl.RelalgPackageImpl#getRelalgModelElement()
	 * @generated
	 */
	int RELALG_MODEL_ELEMENT = 1;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_MODEL_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_MODEL_ELEMENT___FULL_NAME = 0;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELALG_MODEL_ELEMENT_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link relalg.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NamedElementImpl
	 * @see relalg.impl.RelalgPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT___FULL_NAME = RELALG_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = RELALG_MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LabelImpl
	 * @see relalg.impl.RelalgPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NAMED_ELEMENT_CONTAINER = NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL___FULL_NAME = NAMED_ELEMENT___FULL_NAME;

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
	int VERTEX_LABEL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL__NAME = LABEL__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL__NAMED_ELEMENT_CONTAINER = LABEL__NAMED_ELEMENT_CONTAINER;

	/**
	 * The number of structural features of the '<em>Vertex Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL___FULL_NAME = LABEL___FULL_NAME;

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
	int EDGE_LABEL = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL__NAME = LABEL__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL__NAMED_ELEMENT_CONTAINER = LABEL__NAMED_ELEMENT_CONTAINER;

	/**
	 * The number of structural features of the '<em>Edge Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL___FULL_NAME = LABEL___FULL_NAME;

	/**
	 * The number of operations of the '<em>Edge Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.LabelSetImpl <em>Label Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LabelSetImpl
	 * @see relalg.impl.RelalgPackageImpl#getLabelSet()
	 * @generated
	 */
	int LABEL_SET = 6;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_SET__STATUS = 0;

	/**
	 * The number of structural features of the '<em>Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_SET_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VertexLabelSetImpl <em>Vertex Label Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VertexLabelSetImpl
	 * @see relalg.impl.RelalgPackageImpl#getVertexLabelSet()
	 * @generated
	 */
	int VERTEX_LABEL_SET = 7;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_SET__STATUS = LABEL_SET__STATUS;

	/**
	 * The feature id for the '<em><b>Vertex Labels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_SET__VERTEX_LABELS = LABEL_SET_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vertex Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_SET_FEATURE_COUNT = LABEL_SET_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Vertex Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_LABEL_SET_OPERATION_COUNT = LABEL_SET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EdgeLabelSetImpl <em>Edge Label Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EdgeLabelSetImpl
	 * @see relalg.impl.RelalgPackageImpl#getEdgeLabelSet()
	 * @generated
	 */
	int EDGE_LABEL_SET = 8;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_SET__STATUS = LABEL_SET__STATUS;

	/**
	 * The feature id for the '<em><b>Edge Labels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_SET__EDGE_LABELS = LABEL_SET_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Edge Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_SET_FEATURE_COUNT = LABEL_SET_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Edge Label Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LABEL_SET_OPERATION_COUNT = LABEL_SET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 68;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__EXPRESSION_CONTAINER = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TEXT = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = RELALG_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION___FULL_NAME = RELALG_MODEL_ELEMENT___FULL_NAME;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = RELALG_MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariableExpression()
	 * @generated
	 */
	int VARIABLE_EXPRESSION = 9;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__VARIABLE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION___FULL_NAME = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Variable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableComparableExpressionImpl <em>Variable Comparable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableComparableExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariableComparableExpression()
	 * @generated
	 */
	int VARIABLE_COMPARABLE_EXPRESSION = 10;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER = VARIABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION__TEXT = VARIABLE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION__VARIABLE = VARIABLE_EXPRESSION__VARIABLE;

	/**
	 * The number of structural features of the '<em>Variable Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION_FEATURE_COUNT = VARIABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION___FULL_NAME = VARIABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Variable Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_COMPARABLE_EXPRESSION_OPERATION_COUNT = VARIABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableStringExpressionImpl <em>Variable String Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableStringExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariableStringExpression()
	 * @generated
	 */
	int VARIABLE_STRING_EXPRESSION = 11;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION__EXPRESSION_CONTAINER = VARIABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION__TEXT = VARIABLE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION__VARIABLE = VARIABLE_EXPRESSION__VARIABLE;

	/**
	 * The number of structural features of the '<em>Variable String Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION_FEATURE_COUNT = VARIABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION___FULL_NAME = VARIABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Variable String Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_STRING_EXPRESSION_OPERATION_COUNT = VARIABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableArithmeticExpressionImpl <em>Variable Arithmetic Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableArithmeticExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariableArithmeticExpression()
	 * @generated
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION = 12;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION__EXPRESSION_CONTAINER = VARIABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION__TEXT = VARIABLE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION__VARIABLE = VARIABLE_EXPRESSION__VARIABLE;

	/**
	 * The number of structural features of the '<em>Variable Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION_FEATURE_COUNT = VARIABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION___FULL_NAME = VARIABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Variable Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ARITHMETIC_EXPRESSION_OPERATION_COUNT = VARIABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableListExpressionImpl <em>Variable List Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableListExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariableListExpression()
	 * @generated
	 */
	int VARIABLE_LIST_EXPRESSION = 13;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION__EXPRESSION_CONTAINER = VARIABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION__TEXT = VARIABLE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION__VARIABLE = VARIABLE_EXPRESSION__VARIABLE;

	/**
	 * The feature id for the '<em><b>Head</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION__HEAD = VARIABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tail</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION__TAIL = VARIABLE_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION_FEATURE_COUNT = VARIABLE_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION___FULL_NAME = VARIABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Variable List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_LIST_EXPRESSION_OPERATION_COUNT = VARIABLE_EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.FunctionExpressionImpl <em>Function Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.FunctionExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getFunctionExpression()
	 * @generated
	 */
	int FUNCTION_EXPRESSION = 14;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Functor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION__FUNCTOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION___FULL_NAME = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Function Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.FunctionComparableExpressionImpl <em>Function Comparable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.FunctionComparableExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getFunctionComparableExpression()
	 * @generated
	 */
	int FUNCTION_COMPARABLE_EXPRESSION = 15;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER = FUNCTION_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION__TEXT = FUNCTION_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Functor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION__FUNCTOR = FUNCTION_EXPRESSION__FUNCTOR;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION__ARGUMENTS = FUNCTION_EXPRESSION__ARGUMENTS;

	/**
	 * The number of structural features of the '<em>Function Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION_FEATURE_COUNT = FUNCTION_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION___FULL_NAME = FUNCTION_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Function Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_COMPARABLE_EXPRESSION_OPERATION_COUNT = FUNCTION_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.FunctionArithmeticExpressionImpl <em>Function Arithmetic Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.FunctionArithmeticExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getFunctionArithmeticExpression()
	 * @generated
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION = 16;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION__EXPRESSION_CONTAINER = FUNCTION_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION__TEXT = FUNCTION_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Functor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION__FUNCTOR = FUNCTION_EXPRESSION__FUNCTOR;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION__ARGUMENTS = FUNCTION_EXPRESSION__ARGUMENTS;

	/**
	 * The number of structural features of the '<em>Function Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION_FEATURE_COUNT = FUNCTION_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION___FULL_NAME = FUNCTION_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Function Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_ARITHMETIC_EXPRESSION_OPERATION_COUNT = FUNCTION_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.FunctionLogicalExpressionImpl <em>Function Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.FunctionLogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getFunctionLogicalExpression()
	 * @generated
	 */
	int FUNCTION_LOGICAL_EXPRESSION = 17;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION__EXPRESSION_CONTAINER = FUNCTION_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION__TEXT = FUNCTION_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Functor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION__FUNCTOR = FUNCTION_EXPRESSION__FUNCTOR;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION__ARGUMENTS = FUNCTION_EXPRESSION__ARGUMENTS;

	/**
	 * The number of structural features of the '<em>Function Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION_FEATURE_COUNT = FUNCTION_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION___FULL_NAME = FUNCTION_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Function Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_LOGICAL_EXPRESSION_OPERATION_COUNT = FUNCTION_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAMED_ELEMENT_CONTAINER = NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE___FULL_NAME = NAMED_ELEMENT___FULL_NAME;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.GraphObjectVariableImpl <em>Graph Object Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GraphObjectVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getGraphObjectVariable()
	 * @generated
	 */
	int GRAPH_OBJECT_VARIABLE = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE__NAMED_ELEMENT_CONTAINER = VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The number of structural features of the '<em>Graph Object Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE___FULL_NAME = VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Graph Object Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OBJECT_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ElementVariableImpl <em>Element Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ElementVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getElementVariable()
	 * @generated
	 */
	int ELEMENT_VARIABLE = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__NAME = GRAPH_OBJECT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__NAMED_ELEMENT_CONTAINER = GRAPH_OBJECT_VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__DONT_CARE = GRAPH_OBJECT_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__ATTRIBUTES = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE__PROPERTIES = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Element Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE_FEATURE_COUNT = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE___FULL_NAME = GRAPH_OBJECT_VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Element Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_VARIABLE_OPERATION_COUNT = GRAPH_OBJECT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.VertexVariableImpl <em>Vertex Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.VertexVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getVertexVariable()
	 * @generated
	 */
	int VERTEX_VARIABLE = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__NAME = ELEMENT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__NAMED_ELEMENT_CONTAINER = ELEMENT_VARIABLE__NAMED_ELEMENT_CONTAINER;

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
	 * The feature id for the '<em><b>Properties</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__PROPERTIES = ELEMENT_VARIABLE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Vertex Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE__VERTEX_LABEL_SET = ELEMENT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_FEATURE_COUNT = ELEMENT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE___FULL_NAME = ELEMENT_VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Vertex Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_VARIABLE_OPERATION_COUNT = ELEMENT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AbstractEdgeVariableImpl <em>Abstract Edge Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AbstractEdgeVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getAbstractEdgeVariable()
	 * @generated
	 */
	int ABSTRACT_EDGE_VARIABLE = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__NAME = ELEMENT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__NAMED_ELEMENT_CONTAINER = ELEMENT_VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__DONT_CARE = ELEMENT_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__ATTRIBUTES = ELEMENT_VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__PROPERTIES = ELEMENT_VARIABLE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Edge Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET = ELEMENT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE_FEATURE_COUNT = ELEMENT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE___FULL_NAME = ELEMENT_VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Abstract Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDGE_VARIABLE_OPERATION_COUNT = ELEMENT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EdgeVariableImpl <em>Edge Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EdgeVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getEdgeVariable()
	 * @generated
	 */
	int EDGE_VARIABLE = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__NAME = ABSTRACT_EDGE_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__NAMED_ELEMENT_CONTAINER = ABSTRACT_EDGE_VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__DONT_CARE = ABSTRACT_EDGE_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__ATTRIBUTES = ABSTRACT_EDGE_VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__PROPERTIES = ABSTRACT_EDGE_VARIABLE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Edge Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE__EDGE_LABEL_SET = ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET;

	/**
	 * The number of structural features of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_FEATURE_COUNT = ABSTRACT_EDGE_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE___FULL_NAME = ABSTRACT_EDGE_VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Edge Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_VARIABLE_OPERATION_COUNT = ABSTRACT_EDGE_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EdgeListVariableImpl <em>Edge List Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EdgeListVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getEdgeListVariable()
	 * @generated
	 */
	int EDGE_LIST_VARIABLE = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__NAME = ABSTRACT_EDGE_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__NAMED_ELEMENT_CONTAINER = ABSTRACT_EDGE_VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__DONT_CARE = ABSTRACT_EDGE_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__ATTRIBUTES = ABSTRACT_EDGE_VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__PROPERTIES = ABSTRACT_EDGE_VARIABLE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Edge Label Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__EDGE_LABEL_SET = ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET;

	/**
	 * The feature id for the '<em><b>Min Hops</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__MIN_HOPS = ABSTRACT_EDGE_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max Hops</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE__MAX_HOPS = ABSTRACT_EDGE_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Edge List Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE_FEATURE_COUNT = ABSTRACT_EDGE_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE___FULL_NAME = ABSTRACT_EDGE_VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Edge List Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_LIST_VARIABLE_OPERATION_COUNT = ABSTRACT_EDGE_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AttributeVariableImpl <em>Attribute Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AttributeVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getAttributeVariable()
	 * @generated
	 */
	int ATTRIBUTE_VARIABLE = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__NAME = GRAPH_OBJECT_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__NAMED_ELEMENT_CONTAINER = GRAPH_OBJECT_VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__DONT_CARE = GRAPH_OBJECT_VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__ELEMENT = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exp Var</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE__EXP_VAR = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE_FEATURE_COUNT = GRAPH_OBJECT_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Base Variable</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE___GET_BASE_VARIABLE = GRAPH_OBJECT_VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE___FULL_NAME = GRAPH_OBJECT_VARIABLE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VARIABLE_OPERATION_COUNT = GRAPH_OBJECT_VARIABLE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link relalg.impl.ListVariableImpl <em>List Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ListVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getListVariable()
	 * @generated
	 */
	int LIST_VARIABLE = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE__NAMED_ELEMENT_CONTAINER = VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The number of structural features of the '<em>List Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE___FULL_NAME = VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>List Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.PathVariableImpl <em>Path Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.PathVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getPathVariable()
	 * @generated
	 */
	int PATH_VARIABLE = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE__NAMED_ELEMENT_CONTAINER = VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The number of structural features of the '<em>Path Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE___FULL_NAME = VARIABLE___FULL_NAME;

	/**
	 * The number of operations of the '<em>Path Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ExpressionVariableImpl <em>Expression Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ExpressionVariableImpl
	 * @see relalg.impl.RelalgPackageImpl#getExpressionVariable()
	 * @generated
	 */
	int EXPRESSION_VARIABLE = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE__NAMED_ELEMENT_CONTAINER = VARIABLE__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Dont Care</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE__DONT_CARE = VARIABLE__DONT_CARE;

	/**
	 * The feature id for the '<em><b>Has Inferred Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE__HAS_INFERRED_NAME = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE__EXPRESSION = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Expression Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE___FULL_NAME = VARIABLE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Expression Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_VARIABLE_OPERATION_COUNT = VARIABLE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.OperatorImpl <em>Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.OperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getOperator()
	 * @generated
	 */
	int OPERATOR = 29;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR__EXTERNAL_SCHEMA = 0;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR__EXTRA_VARIABLES = 1;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR__INTERNAL_SCHEMA = 2;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR__CARDINALITY = 3;

	/**
	 * The number of structural features of the '<em>Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CardinalityImpl <em>Cardinality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CardinalityImpl
	 * @see relalg.impl.RelalgPackageImpl#getCardinality()
	 * @generated
	 */
	int CARDINALITY = 30;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.NullaryOperatorImpl <em>Nullary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NullaryOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getNullaryOperator()
	 * @generated
	 */
	int NULLARY_OPERATOR = 31;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR__EXTERNAL_SCHEMA = OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR__EXTRA_VARIABLES = OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR__INTERNAL_SCHEMA = OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR__CARDINALITY = OPERATOR__CARDINALITY;

	/**
	 * The number of structural features of the '<em>Nullary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR_FEATURE_COUNT = OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Nullary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULLARY_OPERATOR_OPERATION_COUNT = OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.GetVerticesOperatorImpl <em>Get Vertices Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GetVerticesOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getGetVerticesOperator()
	 * @generated
	 */
	int GET_VERTICES_OPERATOR = 32;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__EXTERNAL_SCHEMA = NULLARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__EXTRA_VARIABLES = NULLARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__INTERNAL_SCHEMA = NULLARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__CARDINALITY = NULLARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR__VERTEX_VARIABLE = NULLARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Get Vertices Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR_FEATURE_COUNT = NULLARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Get Vertices Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_VERTICES_OPERATOR_OPERATION_COUNT = NULLARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SingularObjectSourceOperatorImpl <em>Singular Object Source Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SingularObjectSourceOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getSingularObjectSourceOperator()
	 * @generated
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR = 33;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR__EXTERNAL_SCHEMA = NULLARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR__EXTRA_VARIABLES = NULLARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR__INTERNAL_SCHEMA = NULLARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR__CARDINALITY = NULLARY_OPERATOR__CARDINALITY;

	/**
	 * The number of structural features of the '<em>Singular Object Source Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR_FEATURE_COUNT = NULLARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Singular Object Source Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGULAR_OBJECT_SOURCE_OPERATOR_OPERATION_COUNT = NULLARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DualObjectSourceOperatorImpl <em>Dual Object Source Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DualObjectSourceOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getDualObjectSourceOperator()
	 * @generated
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR = 34;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR__EXTERNAL_SCHEMA = NULLARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR__EXTRA_VARIABLES = NULLARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR__INTERNAL_SCHEMA = NULLARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR__CARDINALITY = NULLARY_OPERATOR__CARDINALITY;

	/**
	 * The number of structural features of the '<em>Dual Object Source Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR_FEATURE_COUNT = NULLARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Dual Object Source Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUAL_OBJECT_SOURCE_OPERATOR_OPERATION_COUNT = NULLARY_OPERATOR_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__EXTERNAL_SCHEMA = NULLARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__EXTRA_VARIABLES = NULLARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__INTERNAL_SCHEMA = NULLARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__CARDINALITY = NULLARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__EXPRESSION_CONTAINER = NULLARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__TEXT = NULLARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__DIRECTION = NULLARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE = NULLARY_OPERATOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__EDGE_VARIABLE = NULLARY_OPERATOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE = NULLARY_OPERATOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Get Edges Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR_FEATURE_COUNT = NULLARY_OPERATOR_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR___FULL_NAME = NULLARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Get Edges Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_EDGES_OPERATOR_OPERATION_COUNT = NULLARY_OPERATOR_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryOperatorImpl <em>Unary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryOperator()
	 * @generated
	 */
	int UNARY_OPERATOR = 36;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR__EXTERNAL_SCHEMA = OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR__EXTRA_VARIABLES = OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR__INTERNAL_SCHEMA = OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR__CARDINALITY = OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR__INPUT = OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR_FEATURE_COUNT = OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Unary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR_OPERATION_COUNT = OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BeamerOperatorImpl <em>Beamer Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BeamerOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getBeamerOperator()
	 * @generated
	 */
	int BEAMER_OPERATOR = 37;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__ELEMENTS = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Internal Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__INTERNAL_ELEMENTS = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tuple Indices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR__TUPLE_INDICES = UNARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Beamer Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Beamer Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEAMER_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ProductionOperatorImpl <em>Production Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ProductionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getProductionOperator()
	 * @generated
	 */
	int PRODUCTION_OPERATOR = 38;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__EXTERNAL_SCHEMA = BEAMER_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__EXTRA_VARIABLES = BEAMER_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__INTERNAL_SCHEMA = BEAMER_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__CARDINALITY = BEAMER_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__INPUT = BEAMER_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__ELEMENTS = BEAMER_OPERATOR__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Internal Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__INTERNAL_ELEMENTS = BEAMER_OPERATOR__INTERNAL_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Tuple Indices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR__TUPLE_INDICES = BEAMER_OPERATOR__TUPLE_INDICES;

	/**
	 * The number of structural features of the '<em>Production Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR_FEATURE_COUNT = BEAMER_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Production Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATOR_OPERATION_COUNT = BEAMER_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AbstractConditionImpl <em>Abstract Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AbstractConditionImpl
	 * @see relalg.impl.RelalgPackageImpl#getAbstractCondition()
	 * @generated
	 */
	int ABSTRACT_CONDITION = 39;

	/**
	 * The feature id for the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION__CONDITION_STRING = 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION__CONDITION = 1;

	/**
	 * The number of structural features of the '<em>Abstract Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SelectionOperatorImpl <em>Selection Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SelectionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getSelectionOperator()
	 * @generated
	 */
	int SELECTION_OPERATOR = 40;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__CONDITION_STRING = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR__CONDITION = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Selection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>To String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR___TO_STRING = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Selection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.ProjectionOperatorImpl <em>Projection Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ProjectionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getProjectionOperator()
	 * @generated
	 */
	int PROJECTION_OPERATOR = 41;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__EXTERNAL_SCHEMA = BEAMER_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__EXTRA_VARIABLES = BEAMER_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__INTERNAL_SCHEMA = BEAMER_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__CARDINALITY = BEAMER_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__INPUT = BEAMER_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__ELEMENTS = BEAMER_OPERATOR__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Internal Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__INTERNAL_ELEMENTS = BEAMER_OPERATOR__INTERNAL_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Tuple Indices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR__TUPLE_INDICES = BEAMER_OPERATOR__TUPLE_INDICES;

	/**
	 * The number of structural features of the '<em>Projection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR_FEATURE_COUNT = BEAMER_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Projection Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECTION_OPERATOR_OPERATION_COUNT = BEAMER_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.GroupingOperatorImpl <em>Grouping Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GroupingOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getGroupingOperator()
	 * @generated
	 */
	int GROUPING_OPERATOR = 42;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__EXTERNAL_SCHEMA = PROJECTION_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__EXTRA_VARIABLES = PROJECTION_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__INTERNAL_SCHEMA = PROJECTION_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__CARDINALITY = PROJECTION_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__INPUT = PROJECTION_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__ELEMENTS = PROJECTION_OPERATOR__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Internal Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__INTERNAL_ELEMENTS = PROJECTION_OPERATOR__INTERNAL_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Tuple Indices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__TUPLE_INDICES = PROJECTION_OPERATOR__TUPLE_INDICES;

	/**
	 * The feature id for the '<em><b>Aggregation Criteria</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__AGGREGATION_CRITERIA = PROJECTION_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Order</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR__ORDER = PROJECTION_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Grouping Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR_FEATURE_COUNT = PROJECTION_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Grouping Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUPING_OPERATOR_OPERATION_COUNT = PROJECTION_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CUDOperatorImpl <em>CUD Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CUDOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getCUDOperator()
	 * @generated
	 */
	int CUD_OPERATOR = 43;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR__ELEMENTS = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CUD Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>CUD Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUD_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CreateOperatorImpl <em>Create Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CreateOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getCreateOperator()
	 * @generated
	 */
	int CREATE_OPERATOR = 44;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__EXTERNAL_SCHEMA = CUD_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__EXTRA_VARIABLES = CUD_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__INTERNAL_SCHEMA = CUD_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__CARDINALITY = CUD_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__INPUT = CUD_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR__ELEMENTS = CUD_OPERATOR__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Create Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR_FEATURE_COUNT = CUD_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Create Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OPERATOR_OPERATION_COUNT = CUD_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DeleteOperatorImpl <em>Delete Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DeleteOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getDeleteOperator()
	 * @generated
	 */
	int DELETE_OPERATOR = 45;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__EXTERNAL_SCHEMA = CUD_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__EXTRA_VARIABLES = CUD_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__INTERNAL_SCHEMA = CUD_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__CARDINALITY = CUD_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__INPUT = CUD_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__ELEMENTS = CUD_OPERATOR__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Detach</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR__DETACH = CUD_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Delete Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR_FEATURE_COUNT = CUD_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Delete Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_OPERATOR_OPERATION_COUNT = CUD_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.NavigationDescriptorImpl <em>Navigation Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NavigationDescriptorImpl
	 * @see relalg.impl.RelalgPackageImpl#getNavigationDescriptor()
	 * @generated
	 */
	int NAVIGATION_DESCRIPTOR = 46;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__DIRECTION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__SOURCE_VERTEX_VARIABLE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__EDGE_VARIABLE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR__TARGET_VERTEX_VARIABLE = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Navigation Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Navigation Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_DESCRIPTOR_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.MaxHopsImpl <em>Max Hops</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.MaxHopsImpl
	 * @see relalg.impl.RelalgPackageImpl#getMaxHops()
	 * @generated
	 */
	int MAX_HOPS = 47;

	/**
	 * The feature id for the '<em><b>Max Hops Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAX_HOPS__MAX_HOPS_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Hops</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAX_HOPS__HOPS = 1;

	/**
	 * The number of structural features of the '<em>Max Hops</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAX_HOPS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Max Hops</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAX_HOPS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ExpandOperatorImpl <em>Expand Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ExpandOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getExpandOperator()
	 * @generated
	 */
	int EXPAND_OPERATOR = 48;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__EXPRESSION_CONTAINER = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__TEXT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__DIRECTION = UNARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE = UNARY_OPERATOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__EDGE_VARIABLE = UNARY_OPERATOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE = UNARY_OPERATOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Expand Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR___FULL_NAME = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Expand Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPAND_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.PathOperatorImpl <em>Path Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.PathOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getPathOperator()
	 * @generated
	 */
	int PATH_OPERATOR = 49;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__EXTERNAL_SCHEMA = EXPAND_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__EXTRA_VARIABLES = EXPAND_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__INTERNAL_SCHEMA = EXPAND_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__CARDINALITY = EXPAND_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__INPUT = EXPAND_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__EXPRESSION_CONTAINER = EXPAND_OPERATOR__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__TEXT = EXPAND_OPERATOR__TEXT;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__DIRECTION = EXPAND_OPERATOR__DIRECTION;

	/**
	 * The feature id for the '<em><b>Source Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__SOURCE_VERTEX_VARIABLE = EXPAND_OPERATOR__SOURCE_VERTEX_VARIABLE;

	/**
	 * The feature id for the '<em><b>Edge Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__EDGE_VARIABLE = EXPAND_OPERATOR__EDGE_VARIABLE;

	/**
	 * The feature id for the '<em><b>Target Vertex Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__TARGET_VERTEX_VARIABLE = EXPAND_OPERATOR__TARGET_VERTEX_VARIABLE;

	/**
	 * The feature id for the '<em><b>Semantics</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR__SEMANTICS = EXPAND_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Path Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR_FEATURE_COUNT = EXPAND_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR___FULL_NAME = EXPAND_OPERATOR___FULL_NAME;

	/**
	 * The number of operations of the '<em>Path Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATOR_OPERATION_COUNT = EXPAND_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DuplicateEliminationOperatorImpl <em>Duplicate Elimination Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DuplicateEliminationOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getDuplicateEliminationOperator()
	 * @generated
	 */
	int DUPLICATE_ELIMINATION_OPERATOR = 50;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The number of structural features of the '<em>Duplicate Elimination Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Duplicate Elimination Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUPLICATE_ELIMINATION_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AllDifferentOperatorImpl <em>All Different Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AllDifferentOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAllDifferentOperator()
	 * @generated
	 */
	int ALL_DIFFERENT_OPERATOR = 51;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Edge Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>All Different Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>All Different Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_DIFFERENT_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SortOperatorImpl <em>Sort Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SortOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getSortOperator()
	 * @generated
	 */
	int SORT_OPERATOR = 52;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR__ENTRIES = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sort Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Sort Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SortEntryImpl <em>Sort Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SortEntryImpl
	 * @see relalg.impl.RelalgPackageImpl#getSortEntry()
	 * @generated
	 */
	int SORT_ENTRY = 53;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_ENTRY__EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_ENTRY__DIRECTION = 1;

	/**
	 * The number of structural features of the '<em>Sort Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Sort Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.TopOperatorImpl <em>Top Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.TopOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getTopOperator()
	 * @generated
	 */
	int TOP_OPERATOR = 54;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Skip</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__SKIP = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR__LIMIT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Top Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Top Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOP_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SortAndTopOperatorImpl <em>Sort And Top Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SortAndTopOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getSortAndTopOperator()
	 * @generated
	 */
	int SORT_AND_TOP_OPERATOR = 55;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__EXTERNAL_SCHEMA = SORT_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__EXTRA_VARIABLES = SORT_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__INTERNAL_SCHEMA = SORT_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__CARDINALITY = SORT_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__INPUT = SORT_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__ENTRIES = SORT_OPERATOR__ENTRIES;

	/**
	 * The feature id for the '<em><b>Skip</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__SKIP = SORT_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR__LIMIT = SORT_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sort And Top Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR_FEATURE_COUNT = SORT_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Sort And Top Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORT_AND_TOP_OPERATOR_OPERATION_COUNT = SORT_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnwindOperatorImpl <em>Unwind Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnwindOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnwindOperator()
	 * @generated
	 */
	int UNWIND_OPERATOR = 56;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__EXTERNAL_SCHEMA = UNARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__EXTRA_VARIABLES = UNARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__INTERNAL_SCHEMA = UNARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__CARDINALITY = UNARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__INPUT = UNARY_OPERATOR__INPUT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR__ELEMENT = UNARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unwind Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR_FEATURE_COUNT = UNARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Unwind Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNWIND_OPERATOR_OPERATION_COUNT = UNARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryOperatorImpl <em>Binary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryOperator()
	 * @generated
	 */
	int BINARY_OPERATOR = 57;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__EXTERNAL_SCHEMA = OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__EXTRA_VARIABLES = OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__INTERNAL_SCHEMA = OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__CARDINALITY = OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__LEFT_INPUT = OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR__RIGHT_INPUT = OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_FEATURE_COUNT = OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_OPERATION_COUNT = OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CommutativeBinaryOperatorImpl <em>Commutative Binary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CommutativeBinaryOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getCommutativeBinaryOperator()
	 * @generated
	 */
	int COMMUTATIVE_BINARY_OPERATOR = 58;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__EXTERNAL_SCHEMA = BINARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__EXTRA_VARIABLES = BINARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__INTERNAL_SCHEMA = BINARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__CARDINALITY = BINARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__LEFT_INPUT = BINARY_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR__RIGHT_INPUT = BINARY_OPERATOR__RIGHT_INPUT;

	/**
	 * The number of structural features of the '<em>Commutative Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR_FEATURE_COUNT = BINARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Commutative Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUTATIVE_BINARY_OPERATOR_OPERATION_COUNT = BINARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AssociativeBinaryOperatorImpl <em>Associative Binary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AssociativeBinaryOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAssociativeBinaryOperator()
	 * @generated
	 */
	int ASSOCIATIVE_BINARY_OPERATOR = 59;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__EXTERNAL_SCHEMA = BINARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__EXTRA_VARIABLES = BINARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__INTERNAL_SCHEMA = BINARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__CARDINALITY = BINARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__LEFT_INPUT = BINARY_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR__RIGHT_INPUT = BINARY_OPERATOR__RIGHT_INPUT;

	/**
	 * The number of structural features of the '<em>Associative Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR_FEATURE_COUNT = BINARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Associative Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIVE_BINARY_OPERATOR_OPERATION_COUNT = BINARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AbstractJoinOperatorImpl <em>Abstract Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AbstractJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAbstractJoinOperator()
	 * @generated
	 */
	int ABSTRACT_JOIN_OPERATOR = 60;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__EXTERNAL_SCHEMA = BINARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__EXTRA_VARIABLES = BINARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__INTERNAL_SCHEMA = BINARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__CARDINALITY = BINARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__LEFT_INPUT = BINARY_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__RIGHT_INPUT = BINARY_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES = BINARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__LEFT_MASK = BINARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR__RIGHT_MASK = BINARY_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Abstract Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR_FEATURE_COUNT = BINARY_OPERATOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Abstract Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_JOIN_OPERATOR_OPERATION_COUNT = BINARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.EquiJoinLikeOperatorImpl <em>Equi Join Like Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EquiJoinLikeOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getEquiJoinLikeOperator()
	 * @generated
	 */
	int EQUI_JOIN_LIKE_OPERATOR = 61;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__EXTERNAL_SCHEMA = ABSTRACT_JOIN_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__EXTRA_VARIABLES = ABSTRACT_JOIN_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__INTERNAL_SCHEMA = ABSTRACT_JOIN_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__CARDINALITY = ABSTRACT_JOIN_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__LEFT_INPUT = ABSTRACT_JOIN_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__RIGHT_INPUT = ABSTRACT_JOIN_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__COMMON_VARIABLES = ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__LEFT_MASK = ABSTRACT_JOIN_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR__RIGHT_MASK = ABSTRACT_JOIN_OPERATOR__RIGHT_MASK;

	/**
	 * The number of structural features of the '<em>Equi Join Like Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR_FEATURE_COUNT = ABSTRACT_JOIN_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Equi Join Like Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUI_JOIN_LIKE_OPERATOR_OPERATION_COUNT = ABSTRACT_JOIN_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.LeftOuterJoinOperatorImpl <em>Left Outer Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LeftOuterJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getLeftOuterJoinOperator()
	 * @generated
	 */
	int LEFT_OUTER_JOIN_OPERATOR = 62;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__EXTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__EXTRA_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__INTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__CARDINALITY = EQUI_JOIN_LIKE_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__LEFT_INPUT = EQUI_JOIN_LIKE_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__RIGHT_INPUT = EQUI_JOIN_LIKE_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__COMMON_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__LEFT_MASK = EQUI_JOIN_LIKE_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR__RIGHT_MASK = EQUI_JOIN_LIKE_OPERATOR__RIGHT_MASK;

	/**
	 * The number of structural features of the '<em>Left Outer Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR_FEATURE_COUNT = EQUI_JOIN_LIKE_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Left Outer Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEFT_OUTER_JOIN_OPERATOR_OPERATION_COUNT = EQUI_JOIN_LIKE_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ThetaLeftOuterJoinOperatorImpl <em>Theta Left Outer Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ThetaLeftOuterJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getThetaLeftOuterJoinOperator()
	 * @generated
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR = 63;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__EXTERNAL_SCHEMA = LEFT_OUTER_JOIN_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__EXTRA_VARIABLES = LEFT_OUTER_JOIN_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__INTERNAL_SCHEMA = LEFT_OUTER_JOIN_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__CARDINALITY = LEFT_OUTER_JOIN_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__LEFT_INPUT = LEFT_OUTER_JOIN_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__RIGHT_INPUT = LEFT_OUTER_JOIN_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__COMMON_VARIABLES = LEFT_OUTER_JOIN_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__LEFT_MASK = LEFT_OUTER_JOIN_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__RIGHT_MASK = LEFT_OUTER_JOIN_OPERATOR__RIGHT_MASK;

	/**
	 * The feature id for the '<em><b>Condition String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION_STRING = LEFT_OUTER_JOIN_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR__CONDITION = LEFT_OUTER_JOIN_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Theta Left Outer Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR_FEATURE_COUNT = LEFT_OUTER_JOIN_OPERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Theta Left Outer Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THETA_LEFT_OUTER_JOIN_OPERATOR_OPERATION_COUNT = LEFT_OUTER_JOIN_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.JoinOperatorImpl <em>Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.JoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getJoinOperator()
	 * @generated
	 */
	int JOIN_OPERATOR = 64;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__EXTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__EXTRA_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__INTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__CARDINALITY = EQUI_JOIN_LIKE_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__LEFT_INPUT = EQUI_JOIN_LIKE_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__RIGHT_INPUT = EQUI_JOIN_LIKE_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__COMMON_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__LEFT_MASK = EQUI_JOIN_LIKE_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR__RIGHT_MASK = EQUI_JOIN_LIKE_OPERATOR__RIGHT_MASK;

	/**
	 * The number of structural features of the '<em>Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR_FEATURE_COUNT = EQUI_JOIN_LIKE_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATOR_OPERATION_COUNT = EQUI_JOIN_LIKE_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.TransitiveClosureJoinOperatorImpl <em>Transitive Closure Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.TransitiveClosureJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getTransitiveClosureJoinOperator()
	 * @generated
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR = 65;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__EXTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__EXTRA_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__INTERNAL_SCHEMA = EQUI_JOIN_LIKE_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__CARDINALITY = EQUI_JOIN_LIKE_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__LEFT_INPUT = EQUI_JOIN_LIKE_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__RIGHT_INPUT = EQUI_JOIN_LIKE_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__COMMON_VARIABLES = EQUI_JOIN_LIKE_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__LEFT_MASK = EQUI_JOIN_LIKE_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__RIGHT_MASK = EQUI_JOIN_LIKE_OPERATOR__RIGHT_MASK;

	/**
	 * The feature id for the '<em><b>Edge List Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE = EQUI_JOIN_LIKE_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transitive Closure Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR_FEATURE_COUNT = EQUI_JOIN_LIKE_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Transitive Closure Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIVE_CLOSURE_JOIN_OPERATOR_OPERATION_COUNT = EQUI_JOIN_LIKE_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AntiJoinOperatorImpl <em>Anti Join Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AntiJoinOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getAntiJoinOperator()
	 * @generated
	 */
	int ANTI_JOIN_OPERATOR = 66;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__EXTERNAL_SCHEMA = ABSTRACT_JOIN_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__EXTRA_VARIABLES = ABSTRACT_JOIN_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__INTERNAL_SCHEMA = ABSTRACT_JOIN_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__CARDINALITY = ABSTRACT_JOIN_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__LEFT_INPUT = ABSTRACT_JOIN_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__RIGHT_INPUT = ABSTRACT_JOIN_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Common Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__COMMON_VARIABLES = ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES;

	/**
	 * The feature id for the '<em><b>Left Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__LEFT_MASK = ABSTRACT_JOIN_OPERATOR__LEFT_MASK;

	/**
	 * The feature id for the '<em><b>Right Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR__RIGHT_MASK = ABSTRACT_JOIN_OPERATOR__RIGHT_MASK;

	/**
	 * The number of structural features of the '<em>Anti Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR_FEATURE_COUNT = ABSTRACT_JOIN_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Anti Join Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATOR_OPERATION_COUNT = ABSTRACT_JOIN_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnionOperatorImpl <em>Union Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnionOperatorImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnionOperator()
	 * @generated
	 */
	int UNION_OPERATOR = 67;

	/**
	 * The feature id for the '<em><b>External Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__EXTERNAL_SCHEMA = BINARY_OPERATOR__EXTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Extra Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__EXTRA_VARIABLES = BINARY_OPERATOR__EXTRA_VARIABLES;

	/**
	 * The feature id for the '<em><b>Internal Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__INTERNAL_SCHEMA = BINARY_OPERATOR__INTERNAL_SCHEMA;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__CARDINALITY = BINARY_OPERATOR__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Left Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__LEFT_INPUT = BINARY_OPERATOR__LEFT_INPUT;

	/**
	 * The feature id for the '<em><b>Right Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__RIGHT_INPUT = BINARY_OPERATOR__RIGHT_INPUT;

	/**
	 * The feature id for the '<em><b>Bag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR__BAG = BINARY_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Union Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR_FEATURE_COUNT = BINARY_OPERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Union Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATOR_OPERATION_COUNT = BINARY_OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CaseExpressionImpl <em>Case Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CaseExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getCaseExpression()
	 * @generated
	 */
	int CASE_EXPRESSION = 69;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__CASES = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fallback</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__FALLBACK = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION___FULL_NAME = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.GenericCaseExpressionImpl <em>Generic Case Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.GenericCaseExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getGenericCaseExpression()
	 * @generated
	 */
	int GENERIC_CASE_EXPRESSION = 70;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION__EXPRESSION_CONTAINER = CASE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION__TEXT = CASE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION__CASES = CASE_EXPRESSION__CASES;

	/**
	 * The feature id for the '<em><b>Fallback</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION__FALLBACK = CASE_EXPRESSION__FALLBACK;

	/**
	 * The number of structural features of the '<em>Generic Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION_FEATURE_COUNT = CASE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION___FULL_NAME = CASE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Generic Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_CASE_EXPRESSION_OPERATION_COUNT = CASE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.SimpleCaseExpressionImpl <em>Simple Case Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.SimpleCaseExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getSimpleCaseExpression()
	 * @generated
	 */
	int SIMPLE_CASE_EXPRESSION = 71;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION__EXPRESSION_CONTAINER = CASE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION__TEXT = CASE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION__CASES = CASE_EXPRESSION__CASES;

	/**
	 * The feature id for the '<em><b>Fallback</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION__FALLBACK = CASE_EXPRESSION__FALLBACK;

	/**
	 * The feature id for the '<em><b>Test</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION__TEST = CASE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simple Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION_FEATURE_COUNT = CASE_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION___FULL_NAME = CASE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Simple Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_CASE_EXPRESSION_OPERATION_COUNT = CASE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ComparableExpressionImpl <em>Comparable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ComparableExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getComparableExpression()
	 * @generated
	 */
	int COMPARABLE_EXPRESSION = 88;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_EXPRESSION___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ArithmeticExpressionImpl <em>Arithmetic Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ArithmeticExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticExpression()
	 * @generated
	 */
	int ARITHMETIC_EXPRESSION = 72;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_EXPRESSION__EXPRESSION_CONTAINER = COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_EXPRESSION__TEXT = COMPARABLE_EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_EXPRESSION_FEATURE_COUNT = COMPARABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_EXPRESSION___FULL_NAME = COMPARABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Arithmetic Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_EXPRESSION_OPERATION_COUNT = COMPARABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryExpression()
	 * @generated
	 */
	int UNARY_EXPRESSION = 73;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryExpression()
	 * @generated
	 */
	int BINARY_EXPRESSION = 74;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryArithmeticOperationExpressionImpl <em>Unary Arithmetic Operation Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryArithmeticOperationExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperationExpression()
	 * @generated
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION = 75;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION__EXPRESSION_CONTAINER = UNARY_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION__TEXT = UNARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = UNARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND = UNARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION_FEATURE_COUNT = UNARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME = UNARY_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Unary Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_ARITHMETIC_OPERATION_EXPRESSION_OPERATION_COUNT = UNARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryArithmeticOperationExpressionImpl <em>Binary Arithmetic Operation Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryArithmeticOperationExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperationExpression()
	 * @generated
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION = 76;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION__EXPRESSION_CONTAINER = BINARY_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION__TEXT = BINARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Binary Arithmetic Operation Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_ARITHMETIC_OPERATION_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.ArithmeticComparisonExpressionImpl <em>Arithmetic Comparison Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ArithmeticComparisonExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonExpression()
	 * @generated
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION = 77;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__EXPRESSION_CONTAINER = BINARY_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__TEXT = BINARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__OPERATOR = BINARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Arithmetic Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION___FULL_NAME = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Arithmetic Comparison Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARITHMETIC_COMPARISON_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.StringExpressionImpl <em>String Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.StringExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getStringExpression()
	 * @generated
	 */
	int STRING_EXPRESSION = 78;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXPRESSION__EXPRESSION_CONTAINER = COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXPRESSION__TEXT = COMPARABLE_EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>String Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXPRESSION_FEATURE_COUNT = COMPARABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXPRESSION___FULL_NAME = COMPARABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>String Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_EXPRESSION_OPERATION_COUNT = COMPARABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.LogicalExpressionImpl <em>Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.LogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getLogicalExpression()
	 * @generated
	 */
	int LOGICAL_EXPRESSION = 79;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_EXPRESSION___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ListExpressionImpl <em>List Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ListExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getListExpression()
	 * @generated
	 */
	int LIST_EXPRESSION = 80;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Head</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__HEAD = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tail</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__TAIL = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION___FULL_NAME = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.EmptyListExpressionImpl <em>Empty List Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.EmptyListExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getEmptyListExpression()
	 * @generated
	 */
	int EMPTY_LIST_EXPRESSION = 81;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION__EXPRESSION_CONTAINER = LIST_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION__TEXT = LIST_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Head</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION__HEAD = LIST_EXPRESSION__HEAD;

	/**
	 * The feature id for the '<em><b>Tail</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION__TAIL = LIST_EXPRESSION__TAIL;

	/**
	 * The number of structural features of the '<em>Empty List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION_FEATURE_COUNT = LIST_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION___FULL_NAME = LIST_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Empty List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_LIST_EXPRESSION_OPERATION_COUNT = LIST_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.IndexAccessExpressionImpl <em>Index Access Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.IndexAccessExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getIndexAccessExpression()
	 * @generated
	 */
	int INDEX_ACCESS_EXPRESSION = 82;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION__LIST = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Index Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Index Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_ACCESS_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.IndexSimpleAccessExpressionImpl <em>Index Simple Access Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.IndexSimpleAccessExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getIndexSimpleAccessExpression()
	 * @generated
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION = 83;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION__EXPRESSION_CONTAINER = INDEX_ACCESS_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION__TEXT = INDEX_ACCESS_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION__LIST = INDEX_ACCESS_EXPRESSION__LIST;

	/**
	 * The feature id for the '<em><b>Idx</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION__IDX = INDEX_ACCESS_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Index Simple Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION_FEATURE_COUNT = INDEX_ACCESS_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION___FULL_NAME = INDEX_ACCESS_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Index Simple Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_SIMPLE_ACCESS_EXPRESSION_OPERATION_COUNT = INDEX_ACCESS_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.IndexRangeAccessExpressionImpl <em>Index Range Access Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.IndexRangeAccessExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getIndexRangeAccessExpression()
	 * @generated
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION = 84;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION__EXPRESSION_CONTAINER = INDEX_ACCESS_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION__TEXT = INDEX_ACCESS_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION__LIST = INDEX_ACCESS_EXPRESSION__LIST;

	/**
	 * The feature id for the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION__LOWER = INDEX_ACCESS_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION__UPPER = INDEX_ACCESS_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Index Range Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION_FEATURE_COUNT = INDEX_ACCESS_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION___FULL_NAME = INDEX_ACCESS_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Index Range Access Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_RANGE_ACCESS_EXPRESSION_OPERATION_COUNT = INDEX_ACCESS_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BinaryLogicalExpressionImpl <em>Binary Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BinaryLogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalExpression()
	 * @generated
	 */
	int BINARY_LOGICAL_EXPRESSION = 85;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__EXPRESSION_CONTAINER = BINARY_EXPRESSION__EXPRESSION_CONTAINER;

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
	 * The feature id for the '<em><b>Left Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND = BINARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION_FEATURE_COUNT = BINARY_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION___FULL_NAME = BINARY_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Binary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_LOGICAL_EXPRESSION_OPERATION_COUNT = BINARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryLogicalExpressionImpl <em>Unary Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryLogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryLogicalExpression()
	 * @generated
	 */
	int UNARY_LOGICAL_EXPRESSION = 86;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION__EXPRESSION_CONTAINER = UNARY_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION__TEXT = UNARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION__OPERATOR = UNARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION__OPERAND = UNARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION_FEATURE_COUNT = UNARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION___FULL_NAME = UNARY_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Unary Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_LOGICAL_EXPRESSION_OPERATION_COUNT = UNARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.UnaryGraphObjectLogicalExpressionImpl <em>Unary Graph Object Logical Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.UnaryGraphObjectLogicalExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getUnaryGraphObjectLogicalExpression()
	 * @generated
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION = 87;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__EXPRESSION_CONTAINER = UNARY_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__TEXT = UNARY_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERATOR = UNARY_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERAND = UNARY_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Graph Object Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION_FEATURE_COUNT = UNARY_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION___FULL_NAME = UNARY_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Unary Graph Object Logical Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION_OPERATION_COUNT = UNARY_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AtomImpl <em>Atom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AtomImpl
	 * @see relalg.impl.RelalgPackageImpl#getAtom()
	 * @generated
	 */
	int ATOM = 89;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOM___FULL_NAME = EXPRESSION___FULL_NAME;

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
	int LITERAL = 90;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL__EXPRESSION_CONTAINER = ATOM__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL___FULL_NAME = ATOM___FULL_NAME;

	/**
	 * The number of operations of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_OPERATION_COUNT = ATOM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BooleanLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 91;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__EXPRESSION_CONTAINER = LITERAL__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__TEXT = LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL___FULL_NAME = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.StringLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 92;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__EXPRESSION_CONTAINER = LITERAL__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL___FULL_NAME = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NumberLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getNumberLiteral()
	 * @generated
	 */
	int NUMBER_LITERAL = 93;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__EXPRESSION_CONTAINER = LITERAL__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL___FULL_NAME = LITERAL___FULL_NAME;

	/**
	 * The number of operations of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.DoubleLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getDoubleLiteral()
	 * @generated
	 */
	int DOUBLE_LITERAL = 94;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__EXPRESSION_CONTAINER = NUMBER_LITERAL__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL___FULL_NAME = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.BigIntegerLiteralImpl <em>Big Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BigIntegerLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getBigIntegerLiteral()
	 * @generated
	 */
	int BIG_INTEGER_LITERAL = 95;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL__EXPRESSION_CONTAINER = NUMBER_LITERAL__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL__TEXT = NUMBER_LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL__VALUE = NUMBER_LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Big Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL_FEATURE_COUNT = NUMBER_LITERAL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL___FULL_NAME = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Big Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_INTEGER_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.IntegerLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 96;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__EXPRESSION_CONTAINER = NUMBER_LITERAL__EXPRESSION_CONTAINER;

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
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL___FULL_NAME = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.NullLiteralImpl <em>Null Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.NullLiteralImpl
	 * @see relalg.impl.RelalgPackageImpl#getNullLiteral()
	 * @generated
	 */
	int NULL_LITERAL = 97;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__EXPRESSION_CONTAINER = NUMBER_LITERAL__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__TEXT = NUMBER_LITERAL__TEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__VALUE = NUMBER_LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL_FEATURE_COUNT = NUMBER_LITERAL_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL___FULL_NAME = NUMBER_LITERAL_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL_OPERATION_COUNT = NUMBER_LITERAL_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link relalg.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ParameterImpl
	 * @see relalg.impl.RelalgPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 98;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Named Element Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAMED_ELEMENT_CONTAINER = NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__EXPRESSION_CONTAINER = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TEXT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER___FULL_NAME = NAMED_ELEMENT___FULL_NAME;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ParameterComparableExpressionImpl <em>Parameter Comparable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ParameterComparableExpressionImpl
	 * @see relalg.impl.RelalgPackageImpl#getParameterComparableExpression()
	 * @generated
	 */
	int PARAMETER_COMPARABLE_EXPRESSION = 99;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER = COMPARABLE_EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION__TEXT = COMPARABLE_EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION__PARAMETER = COMPARABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parameter Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION_FEATURE_COUNT = COMPARABLE_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION___FULL_NAME = COMPARABLE_EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Parameter Comparable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_COMPARABLE_EXPRESSION_OPERATION_COUNT = COMPARABLE_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.PropertyListImpl <em>Property List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.PropertyListImpl
	 * @see relalg.impl.RelalgPackageImpl#getPropertyList()
	 * @generated
	 */
	int PROPERTY_LIST = 100;

	/**
	 * The feature id for the '<em><b>Expression Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST__EXPRESSION_CONTAINER = EXPRESSION__EXPRESSION_CONTAINER;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST__ENTRIES = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Full Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST___FULL_NAME = EXPRESSION___FULL_NAME;

	/**
	 * The number of operations of the '<em>Property List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.PropertyListEntryImpl <em>Property List Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.PropertyListEntryImpl
	 * @see relalg.impl.RelalgPackageImpl#getPropertyListEntry()
	 * @generated
	 */
	int PROPERTY_LIST_ENTRY = 101;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property List Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Property List Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_LIST_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.CaseImpl <em>Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.CaseImpl
	 * @see relalg.impl.RelalgPackageImpl#getCase()
	 * @generated
	 */
	int CASE = 102;

	/**
	 * The feature id for the '<em><b>When</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__WHEN = 0;

	/**
	 * The feature id for the '<em><b>Then</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__THEN = 1;

	/**
	 * The number of structural features of the '<em>Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.MaxHopsType <em>Max Hops Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.MaxHopsType
	 * @see relalg.impl.RelalgPackageImpl#getMaxHopsType()
	 * @generated
	 */
	int MAX_HOPS_TYPE = 103;

	/**
	 * The meta object id for the '{@link relalg.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.Direction
	 * @see relalg.impl.RelalgPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 104;

	/**
	 * The meta object id for the '{@link relalg.OrderDirection <em>Order Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.OrderDirection
	 * @see relalg.impl.RelalgPackageImpl#getOrderDirection()
	 * @generated
	 */
	int ORDER_DIRECTION = 105;

	/**
	 * The meta object id for the '{@link relalg.BinaryArithmeticOperatorType <em>Binary Arithmetic Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.BinaryArithmeticOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperatorType()
	 * @generated
	 */
	int BINARY_ARITHMETIC_OPERATOR_TYPE = 106;

	/**
	 * The meta object id for the '{@link relalg.ArithmeticComparisonOperatorType <em>Arithmetic Comparison Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.ArithmeticComparisonOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonOperatorType()
	 * @generated
	 */
	int ARITHMETIC_COMPARISON_OPERATOR_TYPE = 107;

	/**
	 * The meta object id for the '{@link relalg.UnaryArithmeticOperatorType <em>Unary Arithmetic Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.UnaryArithmeticOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperatorType()
	 * @generated
	 */
	int UNARY_ARITHMETIC_OPERATOR_TYPE = 108;

	/**
	 * The meta object id for the '{@link relalg.BinaryLogicalOperatorType <em>Binary Logical Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.BinaryLogicalOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalOperatorType()
	 * @generated
	 */
	int BINARY_LOGICAL_OPERATOR_TYPE = 109;

	/**
	 * The meta object id for the '{@link relalg.UnaryLogicalOperatorType <em>Unary Logical Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.UnaryLogicalOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getUnaryLogicalOperatorType()
	 * @generated
	 */
	int UNARY_LOGICAL_OPERATOR_TYPE = 110;

	/**
	 * The meta object id for the '{@link relalg.UnaryGraphObjectLogicalOperatorType <em>Unary Graph Object Logical Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.UnaryGraphObjectLogicalOperatorType
	 * @see relalg.impl.RelalgPackageImpl#getUnaryGraphObjectLogicalOperatorType()
	 * @generated
	 */
	int UNARY_GRAPH_OBJECT_LOGICAL_OPERATOR_TYPE = 111;

	/**
	 * The meta object id for the '{@link relalg.LabelSetStatus <em>Label Set Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.LabelSetStatus
	 * @see relalg.impl.RelalgPackageImpl#getLabelSetStatus()
	 * @generated
	 */
	int LABEL_SET_STATUS = 112;

	/**
	 * The meta object id for the '{@link relalg.PathSemantics <em>Path Semantics</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.PathSemantics
	 * @see relalg.impl.RelalgPackageImpl#getPathSemantics()
	 * @generated
	 */
	int PATH_SEMANTICS = 113;

	/**
	 * The meta object id for the '<em>Function</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.function.Function
	 * @see relalg.impl.RelalgPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 114;

	/**
	 * The meta object id for the '<em>Bigint</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see relalg.impl.RelalgPackageImpl#getBigint()
	 * @generated
	 */
	int BIGINT = 115;


	/**
	 * Returns the meta object for class '{@link relalg.RelalgContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see relalg.RelalgContainer
	 * @generated
	 */
	EClass getRelalgContainer();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.RelalgContainer#getName()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_Name();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.RelalgContainer#getRootExpression <em>Root Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Expression</em>'.
	 * @see relalg.RelalgContainer#getRootExpression()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EReference getRelalgContainer_RootExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.RelalgContainer#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see relalg.RelalgContainer#getElements()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EReference getRelalgContainer_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.RelalgContainer#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see relalg.RelalgContainer#getExpressions()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EReference getRelalgContainer_Expressions();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#isSimplifiedPlan <em>Simplified Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simplified Plan</em>'.
	 * @see relalg.RelalgContainer#isSimplifiedPlan()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_SimplifiedPlan();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#isIncrementalPlan <em>Incremental Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Incremental Plan</em>'.
	 * @see relalg.RelalgContainer#isIncrementalPlan()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_IncrementalPlan();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#isExternalSchemaInferred <em>External Schema Inferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Schema Inferred</em>'.
	 * @see relalg.RelalgContainer#isExternalSchemaInferred()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_ExternalSchemaInferred();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#isExtraVariablesInferred <em>Extra Variables Inferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extra Variables Inferred</em>'.
	 * @see relalg.RelalgContainer#isExtraVariablesInferred()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_ExtraVariablesInferred();

	/**
	 * Returns the meta object for the attribute '{@link relalg.RelalgContainer#isInternalSchemaInferred <em>Internal Schema Inferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Internal Schema Inferred</em>'.
	 * @see relalg.RelalgContainer#isInternalSchemaInferred()
	 * @see #getRelalgContainer()
	 * @generated
	 */
	EAttribute getRelalgContainer_InternalSchemaInferred();

	/**
	 * Returns the meta object for class '{@link relalg.RelalgModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see relalg.RelalgModelElement
	 * @generated
	 */
	EClass getRelalgModelElement();

	/**
	 * Returns the meta object for the '{@link relalg.RelalgModelElement#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.RelalgModelElement#fullName()
	 * @generated
	 */
	EOperation getRelalgModelElement__FullName();

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
	 * Returns the meta object for the container reference '{@link relalg.NamedElement#getNamedElementContainer <em>Named Element Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Named Element Container</em>'.
	 * @see relalg.NamedElement#getNamedElementContainer()
	 * @see #getNamedElement()
	 * @generated
	 */
	EReference getNamedElement_NamedElementContainer();

	/**
	 * Returns the meta object for the '{@link relalg.NamedElement#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.NamedElement#fullName()
	 * @generated
	 */
	EOperation getNamedElement__FullName();

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
	 * Returns the meta object for class '{@link relalg.LabelSet <em>Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Set</em>'.
	 * @see relalg.LabelSet
	 * @generated
	 */
	EClass getLabelSet();

	/**
	 * Returns the meta object for the attribute '{@link relalg.LabelSet#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see relalg.LabelSet#getStatus()
	 * @see #getLabelSet()
	 * @generated
	 */
	EAttribute getLabelSet_Status();

	/**
	 * Returns the meta object for class '{@link relalg.VertexLabelSet <em>Vertex Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Label Set</em>'.
	 * @see relalg.VertexLabelSet
	 * @generated
	 */
	EClass getVertexLabelSet();

	/**
	 * Returns the meta object for the reference list '{@link relalg.VertexLabelSet#getVertexLabels <em>Vertex Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Vertex Labels</em>'.
	 * @see relalg.VertexLabelSet#getVertexLabels()
	 * @see #getVertexLabelSet()
	 * @generated
	 */
	EReference getVertexLabelSet_VertexLabels();

	/**
	 * Returns the meta object for class '{@link relalg.EdgeLabelSet <em>Edge Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Label Set</em>'.
	 * @see relalg.EdgeLabelSet
	 * @generated
	 */
	EClass getEdgeLabelSet();

	/**
	 * Returns the meta object for the reference list '{@link relalg.EdgeLabelSet#getEdgeLabels <em>Edge Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Edge Labels</em>'.
	 * @see relalg.EdgeLabelSet#getEdgeLabels()
	 * @see #getEdgeLabelSet()
	 * @generated
	 */
	EReference getEdgeLabelSet_EdgeLabels();

	/**
	 * Returns the meta object for class '{@link relalg.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Expression</em>'.
	 * @see relalg.VariableExpression
	 * @generated
	 */
	EClass getVariableExpression();

	/**
	 * Returns the meta object for the reference '{@link relalg.VariableExpression#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable</em>'.
	 * @see relalg.VariableExpression#getVariable()
	 * @see #getVariableExpression()
	 * @generated
	 */
	EReference getVariableExpression_Variable();

	/**
	 * Returns the meta object for the '{@link relalg.VariableExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.VariableExpression#fullName()
	 * @generated
	 */
	EOperation getVariableExpression__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.VariableComparableExpression <em>Variable Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Comparable Expression</em>'.
	 * @see relalg.VariableComparableExpression
	 * @generated
	 */
	EClass getVariableComparableExpression();

	/**
	 * Returns the meta object for class '{@link relalg.VariableStringExpression <em>Variable String Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable String Expression</em>'.
	 * @see relalg.VariableStringExpression
	 * @generated
	 */
	EClass getVariableStringExpression();

	/**
	 * Returns the meta object for class '{@link relalg.VariableArithmeticExpression <em>Variable Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Arithmetic Expression</em>'.
	 * @see relalg.VariableArithmeticExpression
	 * @generated
	 */
	EClass getVariableArithmeticExpression();

	/**
	 * Returns the meta object for class '{@link relalg.VariableListExpression <em>Variable List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable List Expression</em>'.
	 * @see relalg.VariableListExpression
	 * @generated
	 */
	EClass getVariableListExpression();

	/**
	 * Returns the meta object for class '{@link relalg.FunctionExpression <em>Function Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Expression</em>'.
	 * @see relalg.FunctionExpression
	 * @generated
	 */
	EClass getFunctionExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.FunctionExpression#getFunctor <em>Functor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Functor</em>'.
	 * @see relalg.FunctionExpression#getFunctor()
	 * @see #getFunctionExpression()
	 * @generated
	 */
	EAttribute getFunctionExpression_Functor();

	/**
	 * Returns the meta object for the reference list '{@link relalg.FunctionExpression#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Arguments</em>'.
	 * @see relalg.FunctionExpression#getArguments()
	 * @see #getFunctionExpression()
	 * @generated
	 */
	EReference getFunctionExpression_Arguments();

	/**
	 * Returns the meta object for the '{@link relalg.FunctionExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.FunctionExpression#fullName()
	 * @generated
	 */
	EOperation getFunctionExpression__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.FunctionComparableExpression <em>Function Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Comparable Expression</em>'.
	 * @see relalg.FunctionComparableExpression
	 * @generated
	 */
	EClass getFunctionComparableExpression();

	/**
	 * Returns the meta object for class '{@link relalg.FunctionArithmeticExpression <em>Function Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Arithmetic Expression</em>'.
	 * @see relalg.FunctionArithmeticExpression
	 * @generated
	 */
	EClass getFunctionArithmeticExpression();

	/**
	 * Returns the meta object for class '{@link relalg.FunctionLogicalExpression <em>Function Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Logical Expression</em>'.
	 * @see relalg.FunctionLogicalExpression
	 * @generated
	 */
	EClass getFunctionLogicalExpression();

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
	 * Returns the meta object for class '{@link relalg.GraphObjectVariable <em>Graph Object Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Object Variable</em>'.
	 * @see relalg.GraphObjectVariable
	 * @generated
	 */
	EClass getGraphObjectVariable();

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
	 * Returns the meta object for the reference '{@link relalg.ElementVariable#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Properties</em>'.
	 * @see relalg.ElementVariable#getProperties()
	 * @see #getElementVariable()
	 * @generated
	 */
	EReference getElementVariable_Properties();

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
	 * Returns the meta object for the containment reference '{@link relalg.VertexVariable#getVertexLabelSet <em>Vertex Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Vertex Label Set</em>'.
	 * @see relalg.VertexVariable#getVertexLabelSet()
	 * @see #getVertexVariable()
	 * @generated
	 */
	EReference getVertexVariable_VertexLabelSet();

	/**
	 * Returns the meta object for class '{@link relalg.AbstractEdgeVariable <em>Abstract Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Edge Variable</em>'.
	 * @see relalg.AbstractEdgeVariable
	 * @generated
	 */
	EClass getAbstractEdgeVariable();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.AbstractEdgeVariable#getEdgeLabelSet <em>Edge Label Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Edge Label Set</em>'.
	 * @see relalg.AbstractEdgeVariable#getEdgeLabelSet()
	 * @see #getAbstractEdgeVariable()
	 * @generated
	 */
	EReference getAbstractEdgeVariable_EdgeLabelSet();

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
	 * Returns the meta object for class '{@link relalg.EdgeListVariable <em>Edge List Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge List Variable</em>'.
	 * @see relalg.EdgeListVariable
	 * @generated
	 */
	EClass getEdgeListVariable();

	/**
	 * Returns the meta object for the attribute '{@link relalg.EdgeListVariable#getMinHops <em>Min Hops</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Hops</em>'.
	 * @see relalg.EdgeListVariable#getMinHops()
	 * @see #getEdgeListVariable()
	 * @generated
	 */
	EAttribute getEdgeListVariable_MinHops();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.EdgeListVariable#getMaxHops <em>Max Hops</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Max Hops</em>'.
	 * @see relalg.EdgeListVariable#getMaxHops()
	 * @see #getEdgeListVariable()
	 * @generated
	 */
	EReference getEdgeListVariable_MaxHops();

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
	 * Returns the meta object for the reference '{@link relalg.AttributeVariable#getExpVar <em>Exp Var</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exp Var</em>'.
	 * @see relalg.AttributeVariable#getExpVar()
	 * @see #getAttributeVariable()
	 * @generated
	 */
	EReference getAttributeVariable_ExpVar();

	/**
	 * Returns the meta object for the '{@link relalg.AttributeVariable#getBaseVariable() <em>Get Base Variable</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Base Variable</em>' operation.
	 * @see relalg.AttributeVariable#getBaseVariable()
	 * @generated
	 */
	EOperation getAttributeVariable__GetBaseVariable();

	/**
	 * Returns the meta object for the '{@link relalg.AttributeVariable#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.AttributeVariable#fullName()
	 * @generated
	 */
	EOperation getAttributeVariable__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.ListVariable <em>List Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Variable</em>'.
	 * @see relalg.ListVariable
	 * @generated
	 */
	EClass getListVariable();

	/**
	 * Returns the meta object for class '{@link relalg.PathVariable <em>Path Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path Variable</em>'.
	 * @see relalg.PathVariable
	 * @generated
	 */
	EClass getPathVariable();

	/**
	 * Returns the meta object for class '{@link relalg.ExpressionVariable <em>Expression Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Variable</em>'.
	 * @see relalg.ExpressionVariable
	 * @generated
	 */
	EClass getExpressionVariable();

	/**
	 * Returns the meta object for the attribute '{@link relalg.ExpressionVariable#isHasInferredName <em>Has Inferred Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Inferred Name</em>'.
	 * @see relalg.ExpressionVariable#isHasInferredName()
	 * @see #getExpressionVariable()
	 * @generated
	 */
	EAttribute getExpressionVariable_HasInferredName();

	/**
	 * Returns the meta object for the reference '{@link relalg.ExpressionVariable#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see relalg.ExpressionVariable#getExpression()
	 * @see #getExpressionVariable()
	 * @generated
	 */
	EReference getExpressionVariable_Expression();

	/**
	 * Returns the meta object for the '{@link relalg.ExpressionVariable#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.ExpressionVariable#fullName()
	 * @generated
	 */
	EOperation getExpressionVariable__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.Operator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operator</em>'.
	 * @see relalg.Operator
	 * @generated
	 */
	EClass getOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.Operator#getExternalSchema <em>External Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>External Schema</em>'.
	 * @see relalg.Operator#getExternalSchema()
	 * @see #getOperator()
	 * @generated
	 */
	EReference getOperator_ExternalSchema();

	/**
	 * Returns the meta object for the reference list '{@link relalg.Operator#getExtraVariables <em>Extra Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extra Variables</em>'.
	 * @see relalg.Operator#getExtraVariables()
	 * @see #getOperator()
	 * @generated
	 */
	EReference getOperator_ExtraVariables();

	/**
	 * Returns the meta object for the reference list '{@link relalg.Operator#getInternalSchema <em>Internal Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Internal Schema</em>'.
	 * @see relalg.Operator#getInternalSchema()
	 * @see #getOperator()
	 * @generated
	 */
	EReference getOperator_InternalSchema();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.Operator#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cardinality</em>'.
	 * @see relalg.Operator#getCardinality()
	 * @see #getOperator()
	 * @generated
	 */
	EReference getOperator_Cardinality();

	/**
	 * Returns the meta object for class '{@link relalg.Cardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cardinality</em>'.
	 * @see relalg.Cardinality
	 * @generated
	 */
	EClass getCardinality();

	/**
	 * Returns the meta object for the attribute '{@link relalg.Cardinality#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.Cardinality#getValue()
	 * @see #getCardinality()
	 * @generated
	 */
	EAttribute getCardinality_Value();

	/**
	 * Returns the meta object for class '{@link relalg.NullaryOperator <em>Nullary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nullary Operator</em>'.
	 * @see relalg.NullaryOperator
	 * @generated
	 */
	EClass getNullaryOperator();

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
	 * Returns the meta object for class '{@link relalg.SingularObjectSourceOperator <em>Singular Object Source Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Singular Object Source Operator</em>'.
	 * @see relalg.SingularObjectSourceOperator
	 * @generated
	 */
	EClass getSingularObjectSourceOperator();

	/**
	 * Returns the meta object for class '{@link relalg.DualObjectSourceOperator <em>Dual Object Source Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dual Object Source Operator</em>'.
	 * @see relalg.DualObjectSourceOperator
	 * @generated
	 */
	EClass getDualObjectSourceOperator();

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
	 * Returns the meta object for class '{@link relalg.UnaryOperator <em>Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Operator</em>'.
	 * @see relalg.UnaryOperator
	 * @generated
	 */
	EClass getUnaryOperator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.UnaryOperator#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input</em>'.
	 * @see relalg.UnaryOperator#getInput()
	 * @see #getUnaryOperator()
	 * @generated
	 */
	EReference getUnaryOperator_Input();

	/**
	 * Returns the meta object for class '{@link relalg.BeamerOperator <em>Beamer Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Beamer Operator</em>'.
	 * @see relalg.BeamerOperator
	 * @generated
	 */
	EClass getBeamerOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.BeamerOperator#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see relalg.BeamerOperator#getElements()
	 * @see #getBeamerOperator()
	 * @generated
	 */
	EReference getBeamerOperator_Elements();

	/**
	 * Returns the meta object for the reference list '{@link relalg.BeamerOperator#getInternalElements <em>Internal Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Internal Elements</em>'.
	 * @see relalg.BeamerOperator#getInternalElements()
	 * @see #getBeamerOperator()
	 * @generated
	 */
	EReference getBeamerOperator_InternalElements();

	/**
	 * Returns the meta object for the attribute list '{@link relalg.BeamerOperator#getTupleIndices <em>Tuple Indices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tuple Indices</em>'.
	 * @see relalg.BeamerOperator#getTupleIndices()
	 * @see #getBeamerOperator()
	 * @generated
	 */
	EAttribute getBeamerOperator_TupleIndices();

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
	 * Returns the meta object for class '{@link relalg.AbstractCondition <em>Abstract Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Condition</em>'.
	 * @see relalg.AbstractCondition
	 * @generated
	 */
	EClass getAbstractCondition();

	/**
	 * Returns the meta object for the attribute '{@link relalg.AbstractCondition#getConditionString <em>Condition String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition String</em>'.
	 * @see relalg.AbstractCondition#getConditionString()
	 * @see #getAbstractCondition()
	 * @generated
	 */
	EAttribute getAbstractCondition_ConditionString();

	/**
	 * Returns the meta object for the reference '{@link relalg.AbstractCondition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Condition</em>'.
	 * @see relalg.AbstractCondition#getCondition()
	 * @see #getAbstractCondition()
	 * @generated
	 */
	EReference getAbstractCondition_Condition();

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
	 * Returns the meta object for the '{@link relalg.SelectionOperator#toString() <em>To String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>To String</em>' operation.
	 * @see relalg.SelectionOperator#toString()
	 * @generated
	 */
	EOperation getSelectionOperator__ToString();

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
	 * Returns the meta object for class '{@link relalg.GroupingOperator <em>Grouping Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grouping Operator</em>'.
	 * @see relalg.GroupingOperator
	 * @generated
	 */
	EClass getGroupingOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.GroupingOperator#getAggregationCriteria <em>Aggregation Criteria</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Aggregation Criteria</em>'.
	 * @see relalg.GroupingOperator#getAggregationCriteria()
	 * @see #getGroupingOperator()
	 * @generated
	 */
	EReference getGroupingOperator_AggregationCriteria();

	/**
	 * Returns the meta object for the attribute list '{@link relalg.GroupingOperator#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Order</em>'.
	 * @see relalg.GroupingOperator#getOrder()
	 * @see #getGroupingOperator()
	 * @generated
	 */
	EAttribute getGroupingOperator_Order();

	/**
	 * Returns the meta object for class '{@link relalg.CUDOperator <em>CUD Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CUD Operator</em>'.
	 * @see relalg.CUDOperator
	 * @generated
	 */
	EClass getCUDOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.CUDOperator#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see relalg.CUDOperator#getElements()
	 * @see #getCUDOperator()
	 * @generated
	 */
	EReference getCUDOperator_Elements();

	/**
	 * Returns the meta object for class '{@link relalg.CreateOperator <em>Create Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Operator</em>'.
	 * @see relalg.CreateOperator
	 * @generated
	 */
	EClass getCreateOperator();

	/**
	 * Returns the meta object for class '{@link relalg.DeleteOperator <em>Delete Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Operator</em>'.
	 * @see relalg.DeleteOperator
	 * @generated
	 */
	EClass getDeleteOperator();

	/**
	 * Returns the meta object for the attribute '{@link relalg.DeleteOperator#isDetach <em>Detach</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Detach</em>'.
	 * @see relalg.DeleteOperator#isDetach()
	 * @see #getDeleteOperator()
	 * @generated
	 */
	EAttribute getDeleteOperator_Detach();

	/**
	 * Returns the meta object for class '{@link relalg.NavigationDescriptor <em>Navigation Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Descriptor</em>'.
	 * @see relalg.NavigationDescriptor
	 * @generated
	 */
	EClass getNavigationDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link relalg.NavigationDescriptor#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see relalg.NavigationDescriptor#getDirection()
	 * @see #getNavigationDescriptor()
	 * @generated
	 */
	EAttribute getNavigationDescriptor_Direction();

	/**
	 * Returns the meta object for the reference '{@link relalg.NavigationDescriptor#getSourceVertexVariable <em>Source Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Vertex Variable</em>'.
	 * @see relalg.NavigationDescriptor#getSourceVertexVariable()
	 * @see #getNavigationDescriptor()
	 * @generated
	 */
	EReference getNavigationDescriptor_SourceVertexVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.NavigationDescriptor#getEdgeVariable <em>Edge Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Edge Variable</em>'.
	 * @see relalg.NavigationDescriptor#getEdgeVariable()
	 * @see #getNavigationDescriptor()
	 * @generated
	 */
	EReference getNavigationDescriptor_EdgeVariable();

	/**
	 * Returns the meta object for the reference '{@link relalg.NavigationDescriptor#getTargetVertexVariable <em>Target Vertex Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Vertex Variable</em>'.
	 * @see relalg.NavigationDescriptor#getTargetVertexVariable()
	 * @see #getNavigationDescriptor()
	 * @generated
	 */
	EReference getNavigationDescriptor_TargetVertexVariable();

	/**
	 * Returns the meta object for class '{@link relalg.MaxHops <em>Max Hops</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Max Hops</em>'.
	 * @see relalg.MaxHops
	 * @generated
	 */
	EClass getMaxHops();

	/**
	 * Returns the meta object for the attribute '{@link relalg.MaxHops#getMaxHopsType <em>Max Hops Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Hops Type</em>'.
	 * @see relalg.MaxHops#getMaxHopsType()
	 * @see #getMaxHops()
	 * @generated
	 */
	EAttribute getMaxHops_MaxHopsType();

	/**
	 * Returns the meta object for the attribute '{@link relalg.MaxHops#getHops <em>Hops</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hops</em>'.
	 * @see relalg.MaxHops#getHops()
	 * @see #getMaxHops()
	 * @generated
	 */
	EAttribute getMaxHops_Hops();

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
	 * Returns the meta object for class '{@link relalg.PathOperator <em>Path Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path Operator</em>'.
	 * @see relalg.PathOperator
	 * @generated
	 */
	EClass getPathOperator();

	/**
	 * Returns the meta object for the attribute '{@link relalg.PathOperator#getSemantics <em>Semantics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Semantics</em>'.
	 * @see relalg.PathOperator#getSemantics()
	 * @see #getPathOperator()
	 * @generated
	 */
	EAttribute getPathOperator_Semantics();

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
	 * Returns the meta object for class '{@link relalg.SortOperator <em>Sort Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sort Operator</em>'.
	 * @see relalg.SortOperator
	 * @generated
	 */
	EClass getSortOperator();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.SortOperator#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see relalg.SortOperator#getEntries()
	 * @see #getSortOperator()
	 * @generated
	 */
	EReference getSortOperator_Entries();

	/**
	 * Returns the meta object for class '{@link relalg.SortEntry <em>Sort Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sort Entry</em>'.
	 * @see relalg.SortEntry
	 * @generated
	 */
	EClass getSortEntry();

	/**
	 * Returns the meta object for the reference '{@link relalg.SortEntry#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see relalg.SortEntry#getExpression()
	 * @see #getSortEntry()
	 * @generated
	 */
	EReference getSortEntry_Expression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.SortEntry#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see relalg.SortEntry#getDirection()
	 * @see #getSortEntry()
	 * @generated
	 */
	EAttribute getSortEntry_Direction();

	/**
	 * Returns the meta object for class '{@link relalg.TopOperator <em>Top Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Top Operator</em>'.
	 * @see relalg.TopOperator
	 * @generated
	 */
	EClass getTopOperator();

	/**
	 * Returns the meta object for the reference '{@link relalg.TopOperator#getSkip <em>Skip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Skip</em>'.
	 * @see relalg.TopOperator#getSkip()
	 * @see #getTopOperator()
	 * @generated
	 */
	EReference getTopOperator_Skip();

	/**
	 * Returns the meta object for the reference '{@link relalg.TopOperator#getLimit <em>Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Limit</em>'.
	 * @see relalg.TopOperator#getLimit()
	 * @see #getTopOperator()
	 * @generated
	 */
	EReference getTopOperator_Limit();

	/**
	 * Returns the meta object for class '{@link relalg.SortAndTopOperator <em>Sort And Top Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sort And Top Operator</em>'.
	 * @see relalg.SortAndTopOperator
	 * @generated
	 */
	EClass getSortAndTopOperator();

	/**
	 * Returns the meta object for class '{@link relalg.UnwindOperator <em>Unwind Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unwind Operator</em>'.
	 * @see relalg.UnwindOperator
	 * @generated
	 */
	EClass getUnwindOperator();

	/**
	 * Returns the meta object for the reference '{@link relalg.UnwindOperator#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see relalg.UnwindOperator#getElement()
	 * @see #getUnwindOperator()
	 * @generated
	 */
	EReference getUnwindOperator_Element();

	/**
	 * Returns the meta object for class '{@link relalg.BinaryOperator <em>Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Operator</em>'.
	 * @see relalg.BinaryOperator
	 * @generated
	 */
	EClass getBinaryOperator();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BinaryOperator#getLeftInput <em>Left Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Input</em>'.
	 * @see relalg.BinaryOperator#getLeftInput()
	 * @see #getBinaryOperator()
	 * @generated
	 */
	EReference getBinaryOperator_LeftInput();

	/**
	 * Returns the meta object for the containment reference '{@link relalg.BinaryOperator#getRightInput <em>Right Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Input</em>'.
	 * @see relalg.BinaryOperator#getRightInput()
	 * @see #getBinaryOperator()
	 * @generated
	 */
	EReference getBinaryOperator_RightInput();

	/**
	 * Returns the meta object for class '{@link relalg.CommutativeBinaryOperator <em>Commutative Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Commutative Binary Operator</em>'.
	 * @see relalg.CommutativeBinaryOperator
	 * @generated
	 */
	EClass getCommutativeBinaryOperator();

	/**
	 * Returns the meta object for class '{@link relalg.AssociativeBinaryOperator <em>Associative Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Associative Binary Operator</em>'.
	 * @see relalg.AssociativeBinaryOperator
	 * @generated
	 */
	EClass getAssociativeBinaryOperator();

	/**
	 * Returns the meta object for class '{@link relalg.AbstractJoinOperator <em>Abstract Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Join Operator</em>'.
	 * @see relalg.AbstractJoinOperator
	 * @generated
	 */
	EClass getAbstractJoinOperator();

	/**
	 * Returns the meta object for the reference list '{@link relalg.AbstractJoinOperator#getCommonVariables <em>Common Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Common Variables</em>'.
	 * @see relalg.AbstractJoinOperator#getCommonVariables()
	 * @see #getAbstractJoinOperator()
	 * @generated
	 */
	EReference getAbstractJoinOperator_CommonVariables();

	/**
	 * Returns the meta object for the attribute list '{@link relalg.AbstractJoinOperator#getLeftMask <em>Left Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Left Mask</em>'.
	 * @see relalg.AbstractJoinOperator#getLeftMask()
	 * @see #getAbstractJoinOperator()
	 * @generated
	 */
	EAttribute getAbstractJoinOperator_LeftMask();

	/**
	 * Returns the meta object for the attribute list '{@link relalg.AbstractJoinOperator#getRightMask <em>Right Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Right Mask</em>'.
	 * @see relalg.AbstractJoinOperator#getRightMask()
	 * @see #getAbstractJoinOperator()
	 * @generated
	 */
	EAttribute getAbstractJoinOperator_RightMask();

	/**
	 * Returns the meta object for class '{@link relalg.EquiJoinLikeOperator <em>Equi Join Like Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equi Join Like Operator</em>'.
	 * @see relalg.EquiJoinLikeOperator
	 * @generated
	 */
	EClass getEquiJoinLikeOperator();

	/**
	 * Returns the meta object for class '{@link relalg.LeftOuterJoinOperator <em>Left Outer Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Left Outer Join Operator</em>'.
	 * @see relalg.LeftOuterJoinOperator
	 * @generated
	 */
	EClass getLeftOuterJoinOperator();

	/**
	 * Returns the meta object for class '{@link relalg.ThetaLeftOuterJoinOperator <em>Theta Left Outer Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Theta Left Outer Join Operator</em>'.
	 * @see relalg.ThetaLeftOuterJoinOperator
	 * @generated
	 */
	EClass getThetaLeftOuterJoinOperator();

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
	 * Returns the meta object for class '{@link relalg.TransitiveClosureJoinOperator <em>Transitive Closure Join Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transitive Closure Join Operator</em>'.
	 * @see relalg.TransitiveClosureJoinOperator
	 * @generated
	 */
	EClass getTransitiveClosureJoinOperator();

	/**
	 * Returns the meta object for the reference '{@link relalg.TransitiveClosureJoinOperator#getEdgeListVariable <em>Edge List Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Edge List Variable</em>'.
	 * @see relalg.TransitiveClosureJoinOperator#getEdgeListVariable()
	 * @see #getTransitiveClosureJoinOperator()
	 * @generated
	 */
	EReference getTransitiveClosureJoinOperator_EdgeListVariable();

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
	 * Returns the meta object for class '{@link relalg.UnionOperator <em>Union Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Operator</em>'.
	 * @see relalg.UnionOperator
	 * @generated
	 */
	EClass getUnionOperator();

	/**
	 * Returns the meta object for the attribute '{@link relalg.UnionOperator#isBag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bag</em>'.
	 * @see relalg.UnionOperator#isBag()
	 * @see #getUnionOperator()
	 * @generated
	 */
	EAttribute getUnionOperator_Bag();

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
	 * Returns the meta object for the container reference '{@link relalg.Expression#getExpressionContainer <em>Expression Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Expression Container</em>'.
	 * @see relalg.Expression#getExpressionContainer()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_ExpressionContainer();

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
	 * Returns the meta object for class '{@link relalg.CaseExpression <em>Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case Expression</em>'.
	 * @see relalg.CaseExpression
	 * @generated
	 */
	EClass getCaseExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.CaseExpression#getCases <em>Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cases</em>'.
	 * @see relalg.CaseExpression#getCases()
	 * @see #getCaseExpression()
	 * @generated
	 */
	EReference getCaseExpression_Cases();

	/**
	 * Returns the meta object for the reference '{@link relalg.CaseExpression#getFallback <em>Fallback</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Fallback</em>'.
	 * @see relalg.CaseExpression#getFallback()
	 * @see #getCaseExpression()
	 * @generated
	 */
	EReference getCaseExpression_Fallback();

	/**
	 * Returns the meta object for the '{@link relalg.CaseExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.CaseExpression#fullName()
	 * @generated
	 */
	EOperation getCaseExpression__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.GenericCaseExpression <em>Generic Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Case Expression</em>'.
	 * @see relalg.GenericCaseExpression
	 * @generated
	 */
	EClass getGenericCaseExpression();

	/**
	 * Returns the meta object for class '{@link relalg.SimpleCaseExpression <em>Simple Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Case Expression</em>'.
	 * @see relalg.SimpleCaseExpression
	 * @generated
	 */
	EClass getSimpleCaseExpression();

	/**
	 * Returns the meta object for the reference '{@link relalg.SimpleCaseExpression#getTest <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test</em>'.
	 * @see relalg.SimpleCaseExpression#getTest()
	 * @see #getSimpleCaseExpression()
	 * @generated
	 */
	EReference getSimpleCaseExpression_Test();

	/**
	 * Returns the meta object for class '{@link relalg.ArithmeticExpression <em>Arithmetic Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arithmetic Expression</em>'.
	 * @see relalg.ArithmeticExpression
	 * @generated
	 */
	EClass getArithmeticExpression();

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
	 * Returns the meta object for class '{@link relalg.BinaryExpression <em>Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Expression</em>'.
	 * @see relalg.BinaryExpression
	 * @generated
	 */
	EClass getBinaryExpression();

	/**
	 * Returns the meta object for class '{@link relalg.UnaryArithmeticOperationExpression <em>Unary Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Arithmetic Operation Expression</em>'.
	 * @see relalg.UnaryArithmeticOperationExpression
	 * @generated
	 */
	EClass getUnaryArithmeticOperationExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.UnaryArithmeticOperationExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.UnaryArithmeticOperationExpression#getOperator()
	 * @see #getUnaryArithmeticOperationExpression()
	 * @generated
	 */
	EAttribute getUnaryArithmeticOperationExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link relalg.UnaryArithmeticOperationExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see relalg.UnaryArithmeticOperationExpression#getOperand()
	 * @see #getUnaryArithmeticOperationExpression()
	 * @generated
	 */
	EReference getUnaryArithmeticOperationExpression_Operand();

	/**
	 * Returns the meta object for class '{@link relalg.BinaryArithmeticOperationExpression <em>Binary Arithmetic Operation Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Arithmetic Operation Expression</em>'.
	 * @see relalg.BinaryArithmeticOperationExpression
	 * @generated
	 */
	EClass getBinaryArithmeticOperationExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.BinaryArithmeticOperationExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.BinaryArithmeticOperationExpression#getOperator()
	 * @see #getBinaryArithmeticOperationExpression()
	 * @generated
	 */
	EAttribute getBinaryArithmeticOperationExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link relalg.BinaryArithmeticOperationExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Operand</em>'.
	 * @see relalg.BinaryArithmeticOperationExpression#getLeftOperand()
	 * @see #getBinaryArithmeticOperationExpression()
	 * @generated
	 */
	EReference getBinaryArithmeticOperationExpression_LeftOperand();

	/**
	 * Returns the meta object for the reference '{@link relalg.BinaryArithmeticOperationExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Operand</em>'.
	 * @see relalg.BinaryArithmeticOperationExpression#getRightOperand()
	 * @see #getBinaryArithmeticOperationExpression()
	 * @generated
	 */
	EReference getBinaryArithmeticOperationExpression_RightOperand();

	/**
	 * Returns the meta object for the '{@link relalg.BinaryArithmeticOperationExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.BinaryArithmeticOperationExpression#fullName()
	 * @generated
	 */
	EOperation getBinaryArithmeticOperationExpression__FullName();

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
	 * Returns the meta object for the reference '{@link relalg.ArithmeticComparisonExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Operand</em>'.
	 * @see relalg.ArithmeticComparisonExpression#getLeftOperand()
	 * @see #getArithmeticComparisonExpression()
	 * @generated
	 */
	EReference getArithmeticComparisonExpression_LeftOperand();

	/**
	 * Returns the meta object for the reference '{@link relalg.ArithmeticComparisonExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Operand</em>'.
	 * @see relalg.ArithmeticComparisonExpression#getRightOperand()
	 * @see #getArithmeticComparisonExpression()
	 * @generated
	 */
	EReference getArithmeticComparisonExpression_RightOperand();

	/**
	 * Returns the meta object for the '{@link relalg.ArithmeticComparisonExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.ArithmeticComparisonExpression#fullName()
	 * @generated
	 */
	EOperation getArithmeticComparisonExpression__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.StringExpression <em>String Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Expression</em>'.
	 * @see relalg.StringExpression
	 * @generated
	 */
	EClass getStringExpression();

	/**
	 * Returns the meta object for class '{@link relalg.LogicalExpression <em>Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Expression</em>'.
	 * @see relalg.LogicalExpression
	 * @generated
	 */
	EClass getLogicalExpression();

	/**
	 * Returns the meta object for class '{@link relalg.ListExpression <em>List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Expression</em>'.
	 * @see relalg.ListExpression
	 * @generated
	 */
	EClass getListExpression();

	/**
	 * Returns the meta object for the reference '{@link relalg.ListExpression#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Head</em>'.
	 * @see relalg.ListExpression#getHead()
	 * @see #getListExpression()
	 * @generated
	 */
	EReference getListExpression_Head();

	/**
	 * Returns the meta object for the reference '{@link relalg.ListExpression#getTail <em>Tail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tail</em>'.
	 * @see relalg.ListExpression#getTail()
	 * @see #getListExpression()
	 * @generated
	 */
	EReference getListExpression_Tail();

	/**
	 * Returns the meta object for the '{@link relalg.ListExpression#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.ListExpression#fullName()
	 * @generated
	 */
	EOperation getListExpression__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.EmptyListExpression <em>Empty List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Empty List Expression</em>'.
	 * @see relalg.EmptyListExpression
	 * @generated
	 */
	EClass getEmptyListExpression();

	/**
	 * Returns the meta object for class '{@link relalg.IndexAccessExpression <em>Index Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Access Expression</em>'.
	 * @see relalg.IndexAccessExpression
	 * @generated
	 */
	EClass getIndexAccessExpression();

	/**
	 * Returns the meta object for the reference '{@link relalg.IndexAccessExpression#getList <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>List</em>'.
	 * @see relalg.IndexAccessExpression#getList()
	 * @see #getIndexAccessExpression()
	 * @generated
	 */
	EReference getIndexAccessExpression_List();

	/**
	 * Returns the meta object for class '{@link relalg.IndexSimpleAccessExpression <em>Index Simple Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Simple Access Expression</em>'.
	 * @see relalg.IndexSimpleAccessExpression
	 * @generated
	 */
	EClass getIndexSimpleAccessExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.IndexSimpleAccessExpression#getIdx <em>Idx</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idx</em>'.
	 * @see relalg.IndexSimpleAccessExpression#getIdx()
	 * @see #getIndexSimpleAccessExpression()
	 * @generated
	 */
	EAttribute getIndexSimpleAccessExpression_Idx();

	/**
	 * Returns the meta object for class '{@link relalg.IndexRangeAccessExpression <em>Index Range Access Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Range Access Expression</em>'.
	 * @see relalg.IndexRangeAccessExpression
	 * @generated
	 */
	EClass getIndexRangeAccessExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.IndexRangeAccessExpression#getLower <em>Lower</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower</em>'.
	 * @see relalg.IndexRangeAccessExpression#getLower()
	 * @see #getIndexRangeAccessExpression()
	 * @generated
	 */
	EAttribute getIndexRangeAccessExpression_Lower();

	/**
	 * Returns the meta object for the attribute '{@link relalg.IndexRangeAccessExpression#getUpper <em>Upper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper</em>'.
	 * @see relalg.IndexRangeAccessExpression#getUpper()
	 * @see #getIndexRangeAccessExpression()
	 * @generated
	 */
	EAttribute getIndexRangeAccessExpression_Upper();

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
	 * Returns the meta object for the reference '{@link relalg.BinaryLogicalExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Operand</em>'.
	 * @see relalg.BinaryLogicalExpression#getLeftOperand()
	 * @see #getBinaryLogicalExpression()
	 * @generated
	 */
	EReference getBinaryLogicalExpression_LeftOperand();

	/**
	 * Returns the meta object for the reference '{@link relalg.BinaryLogicalExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Operand</em>'.
	 * @see relalg.BinaryLogicalExpression#getRightOperand()
	 * @see #getBinaryLogicalExpression()
	 * @generated
	 */
	EReference getBinaryLogicalExpression_RightOperand();

	/**
	 * Returns the meta object for class '{@link relalg.UnaryLogicalExpression <em>Unary Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Logical Expression</em>'.
	 * @see relalg.UnaryLogicalExpression
	 * @generated
	 */
	EClass getUnaryLogicalExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.UnaryLogicalExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.UnaryLogicalExpression#getOperator()
	 * @see #getUnaryLogicalExpression()
	 * @generated
	 */
	EAttribute getUnaryLogicalExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link relalg.UnaryLogicalExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see relalg.UnaryLogicalExpression#getOperand()
	 * @see #getUnaryLogicalExpression()
	 * @generated
	 */
	EReference getUnaryLogicalExpression_Operand();

	/**
	 * Returns the meta object for class '{@link relalg.UnaryGraphObjectLogicalExpression <em>Unary Graph Object Logical Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Graph Object Logical Expression</em>'.
	 * @see relalg.UnaryGraphObjectLogicalExpression
	 * @generated
	 */
	EClass getUnaryGraphObjectLogicalExpression();

	/**
	 * Returns the meta object for the attribute '{@link relalg.UnaryGraphObjectLogicalExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see relalg.UnaryGraphObjectLogicalExpression#getOperator()
	 * @see #getUnaryGraphObjectLogicalExpression()
	 * @generated
	 */
	EAttribute getUnaryGraphObjectLogicalExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link relalg.UnaryGraphObjectLogicalExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see relalg.UnaryGraphObjectLogicalExpression#getOperand()
	 * @see #getUnaryGraphObjectLogicalExpression()
	 * @generated
	 */
	EReference getUnaryGraphObjectLogicalExpression_Operand();

	/**
	 * Returns the meta object for class '{@link relalg.ComparableExpression <em>Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparable Expression</em>'.
	 * @see relalg.ComparableExpression
	 * @generated
	 */
	EClass getComparableExpression();

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
	 * Returns the meta object for class '{@link relalg.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see relalg.Literal
	 * @generated
	 */
	EClass getLiteral();

	/**
	 * Returns the meta object for class '{@link relalg.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see relalg.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link relalg.BooleanLiteral#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.BooleanLiteral#isValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_Value();

	/**
	 * Returns the meta object for the '{@link relalg.BooleanLiteral#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.BooleanLiteral#fullName()
	 * @generated
	 */
	EOperation getBooleanLiteral__FullName();

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
	 * Returns the meta object for the '{@link relalg.StringLiteral#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.StringLiteral#fullName()
	 * @generated
	 */
	EOperation getStringLiteral__FullName();

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
	 * Returns the meta object for the '{@link relalg.DoubleLiteral#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.DoubleLiteral#fullName()
	 * @generated
	 */
	EOperation getDoubleLiteral__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.BigIntegerLiteral <em>Big Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Big Integer Literal</em>'.
	 * @see relalg.BigIntegerLiteral
	 * @generated
	 */
	EClass getBigIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link relalg.BigIntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see relalg.BigIntegerLiteral#getValue()
	 * @see #getBigIntegerLiteral()
	 * @generated
	 */
	EAttribute getBigIntegerLiteral_Value();

	/**
	 * Returns the meta object for the '{@link relalg.BigIntegerLiteral#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.BigIntegerLiteral#fullName()
	 * @generated
	 */
	EOperation getBigIntegerLiteral__FullName();

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
	 * Returns the meta object for the '{@link relalg.IntegerLiteral#fullName() <em>Full Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Full Name</em>' operation.
	 * @see relalg.IntegerLiteral#fullName()
	 * @generated
	 */
	EOperation getIntegerLiteral__FullName();

	/**
	 * Returns the meta object for class '{@link relalg.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Literal</em>'.
	 * @see relalg.NullLiteral
	 * @generated
	 */
	EClass getNullLiteral();

	/**
	 * Returns the meta object for class '{@link relalg.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see relalg.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for class '{@link relalg.ParameterComparableExpression <em>Parameter Comparable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Comparable Expression</em>'.
	 * @see relalg.ParameterComparableExpression
	 * @generated
	 */
	EClass getParameterComparableExpression();

	/**
	 * Returns the meta object for the reference '{@link relalg.ParameterComparableExpression#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see relalg.ParameterComparableExpression#getParameter()
	 * @see #getParameterComparableExpression()
	 * @generated
	 */
	EReference getParameterComparableExpression_Parameter();

	/**
	 * Returns the meta object for class '{@link relalg.PropertyList <em>Property List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property List</em>'.
	 * @see relalg.PropertyList
	 * @generated
	 */
	EClass getPropertyList();

	/**
	 * Returns the meta object for the map '{@link relalg.PropertyList#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Entries</em>'.
	 * @see relalg.PropertyList#getEntries()
	 * @see #getPropertyList()
	 * @generated
	 */
	EReference getPropertyList_Entries();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Property List Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property List Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyUnique="false" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="relalg.Expression"
	 * @generated
	 */
	EClass getPropertyListEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPropertyListEntry()
	 * @generated
	 */
	EAttribute getPropertyListEntry_Key();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPropertyListEntry()
	 * @generated
	 */
	EReference getPropertyListEntry_Value();

	/**
	 * Returns the meta object for class '{@link relalg.Case <em>Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case</em>'.
	 * @see relalg.Case
	 * @generated
	 */
	EClass getCase();

	/**
	 * Returns the meta object for the reference '{@link relalg.Case#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>When</em>'.
	 * @see relalg.Case#getWhen()
	 * @see #getCase()
	 * @generated
	 */
	EReference getCase_When();

	/**
	 * Returns the meta object for the reference '{@link relalg.Case#getThen <em>Then</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Then</em>'.
	 * @see relalg.Case#getThen()
	 * @see #getCase()
	 * @generated
	 */
	EReference getCase_Then();

	/**
	 * Returns the meta object for enum '{@link relalg.MaxHopsType <em>Max Hops Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Max Hops Type</em>'.
	 * @see relalg.MaxHopsType
	 * @generated
	 */
	EEnum getMaxHopsType();

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
	 * Returns the meta object for enum '{@link relalg.OrderDirection <em>Order Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Order Direction</em>'.
	 * @see relalg.OrderDirection
	 * @generated
	 */
	EEnum getOrderDirection();

	/**
	 * Returns the meta object for enum '{@link relalg.BinaryArithmeticOperatorType <em>Binary Arithmetic Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binary Arithmetic Operator Type</em>'.
	 * @see relalg.BinaryArithmeticOperatorType
	 * @generated
	 */
	EEnum getBinaryArithmeticOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.ArithmeticComparisonOperatorType <em>Arithmetic Comparison Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arithmetic Comparison Operator Type</em>'.
	 * @see relalg.ArithmeticComparisonOperatorType
	 * @generated
	 */
	EEnum getArithmeticComparisonOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.UnaryArithmeticOperatorType <em>Unary Arithmetic Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Arithmetic Operator Type</em>'.
	 * @see relalg.UnaryArithmeticOperatorType
	 * @generated
	 */
	EEnum getUnaryArithmeticOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.BinaryLogicalOperatorType <em>Binary Logical Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binary Logical Operator Type</em>'.
	 * @see relalg.BinaryLogicalOperatorType
	 * @generated
	 */
	EEnum getBinaryLogicalOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.UnaryLogicalOperatorType <em>Unary Logical Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Logical Operator Type</em>'.
	 * @see relalg.UnaryLogicalOperatorType
	 * @generated
	 */
	EEnum getUnaryLogicalOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.UnaryGraphObjectLogicalOperatorType <em>Unary Graph Object Logical Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Graph Object Logical Operator Type</em>'.
	 * @see relalg.UnaryGraphObjectLogicalOperatorType
	 * @generated
	 */
	EEnum getUnaryGraphObjectLogicalOperatorType();

	/**
	 * Returns the meta object for enum '{@link relalg.LabelSetStatus <em>Label Set Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Label Set Status</em>'.
	 * @see relalg.LabelSetStatus
	 * @generated
	 */
	EEnum getLabelSetStatus();

	/**
	 * Returns the meta object for enum '{@link relalg.PathSemantics <em>Path Semantics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Path Semantics</em>'.
	 * @see relalg.PathSemantics
	 * @generated
	 */
	EEnum getPathSemantics();

	/**
	 * Returns the meta object for data type '{@link relalg.function.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Function</em>'.
	 * @see relalg.function.Function
	 * @model instanceClass="relalg.function.Function"
	 * @generated
	 */
	EDataType getFunction();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Bigint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Bigint</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 * @generated
	 */
	EDataType getBigint();

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
		 * The meta object literal for the '{@link relalg.impl.RelalgContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.RelalgContainerImpl
		 * @see relalg.impl.RelalgPackageImpl#getRelalgContainer()
		 * @generated
		 */
		EClass RELALG_CONTAINER = eINSTANCE.getRelalgContainer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__NAME = eINSTANCE.getRelalgContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Root Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELALG_CONTAINER__ROOT_EXPRESSION = eINSTANCE.getRelalgContainer_RootExpression();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELALG_CONTAINER__ELEMENTS = eINSTANCE.getRelalgContainer_Elements();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELALG_CONTAINER__EXPRESSIONS = eINSTANCE.getRelalgContainer_Expressions();

		/**
		 * The meta object literal for the '<em><b>Simplified Plan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__SIMPLIFIED_PLAN = eINSTANCE.getRelalgContainer_SimplifiedPlan();

		/**
		 * The meta object literal for the '<em><b>Incremental Plan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__INCREMENTAL_PLAN = eINSTANCE.getRelalgContainer_IncrementalPlan();

		/**
		 * The meta object literal for the '<em><b>External Schema Inferred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED = eINSTANCE.getRelalgContainer_ExternalSchemaInferred();

		/**
		 * The meta object literal for the '<em><b>Extra Variables Inferred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED = eINSTANCE.getRelalgContainer_ExtraVariablesInferred();

		/**
		 * The meta object literal for the '<em><b>Internal Schema Inferred</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED = eINSTANCE.getRelalgContainer_InternalSchemaInferred();

		/**
		 * The meta object literal for the '{@link relalg.impl.RelalgModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.RelalgModelElementImpl
		 * @see relalg.impl.RelalgPackageImpl#getRelalgModelElement()
		 * @generated
		 */
		EClass RELALG_MODEL_ELEMENT = eINSTANCE.getRelalgModelElement();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RELALG_MODEL_ELEMENT___FULL_NAME = eINSTANCE.getRelalgModelElement__FullName();

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
		 * The meta object literal for the '<em><b>Named Element Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER = eINSTANCE.getNamedElement_NamedElementContainer();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NAMED_ELEMENT___FULL_NAME = eINSTANCE.getNamedElement__FullName();

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
		 * The meta object literal for the '{@link relalg.impl.LabelSetImpl <em>Label Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LabelSetImpl
		 * @see relalg.impl.RelalgPackageImpl#getLabelSet()
		 * @generated
		 */
		EClass LABEL_SET = eINSTANCE.getLabelSet();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_SET__STATUS = eINSTANCE.getLabelSet_Status();

		/**
		 * The meta object literal for the '{@link relalg.impl.VertexLabelSetImpl <em>Vertex Label Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VertexLabelSetImpl
		 * @see relalg.impl.RelalgPackageImpl#getVertexLabelSet()
		 * @generated
		 */
		EClass VERTEX_LABEL_SET = eINSTANCE.getVertexLabelSet();

		/**
		 * The meta object literal for the '<em><b>Vertex Labels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX_LABEL_SET__VERTEX_LABELS = eINSTANCE.getVertexLabelSet_VertexLabels();

		/**
		 * The meta object literal for the '{@link relalg.impl.EdgeLabelSetImpl <em>Edge Label Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EdgeLabelSetImpl
		 * @see relalg.impl.RelalgPackageImpl#getEdgeLabelSet()
		 * @generated
		 */
		EClass EDGE_LABEL_SET = eINSTANCE.getEdgeLabelSet();

		/**
		 * The meta object literal for the '<em><b>Edge Labels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE_LABEL_SET__EDGE_LABELS = eINSTANCE.getEdgeLabelSet_EdgeLabels();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariableExpression()
		 * @generated
		 */
		EClass VARIABLE_EXPRESSION = eINSTANCE.getVariableExpression();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXPRESSION__VARIABLE = eINSTANCE.getVariableExpression_Variable();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation VARIABLE_EXPRESSION___FULL_NAME = eINSTANCE.getVariableExpression__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableComparableExpressionImpl <em>Variable Comparable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableComparableExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariableComparableExpression()
		 * @generated
		 */
		EClass VARIABLE_COMPARABLE_EXPRESSION = eINSTANCE.getVariableComparableExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableStringExpressionImpl <em>Variable String Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableStringExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariableStringExpression()
		 * @generated
		 */
		EClass VARIABLE_STRING_EXPRESSION = eINSTANCE.getVariableStringExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableArithmeticExpressionImpl <em>Variable Arithmetic Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableArithmeticExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariableArithmeticExpression()
		 * @generated
		 */
		EClass VARIABLE_ARITHMETIC_EXPRESSION = eINSTANCE.getVariableArithmeticExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.VariableListExpressionImpl <em>Variable List Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.VariableListExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getVariableListExpression()
		 * @generated
		 */
		EClass VARIABLE_LIST_EXPRESSION = eINSTANCE.getVariableListExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.FunctionExpressionImpl <em>Function Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.FunctionExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getFunctionExpression()
		 * @generated
		 */
		EClass FUNCTION_EXPRESSION = eINSTANCE.getFunctionExpression();

		/**
		 * The meta object literal for the '<em><b>Functor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_EXPRESSION__FUNCTOR = eINSTANCE.getFunctionExpression_Functor();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_EXPRESSION__ARGUMENTS = eINSTANCE.getFunctionExpression_Arguments();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FUNCTION_EXPRESSION___FULL_NAME = eINSTANCE.getFunctionExpression__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.FunctionComparableExpressionImpl <em>Function Comparable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.FunctionComparableExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getFunctionComparableExpression()
		 * @generated
		 */
		EClass FUNCTION_COMPARABLE_EXPRESSION = eINSTANCE.getFunctionComparableExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.FunctionArithmeticExpressionImpl <em>Function Arithmetic Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.FunctionArithmeticExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getFunctionArithmeticExpression()
		 * @generated
		 */
		EClass FUNCTION_ARITHMETIC_EXPRESSION = eINSTANCE.getFunctionArithmeticExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.FunctionLogicalExpressionImpl <em>Function Logical Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.FunctionLogicalExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getFunctionLogicalExpression()
		 * @generated
		 */
		EClass FUNCTION_LOGICAL_EXPRESSION = eINSTANCE.getFunctionLogicalExpression();

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
		 * The meta object literal for the '{@link relalg.impl.GraphObjectVariableImpl <em>Graph Object Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.GraphObjectVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getGraphObjectVariable()
		 * @generated
		 */
		EClass GRAPH_OBJECT_VARIABLE = eINSTANCE.getGraphObjectVariable();

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
		 * The meta object literal for the '<em><b>Properties</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_VARIABLE__PROPERTIES = eINSTANCE.getElementVariable_Properties();

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
		 * The meta object literal for the '<em><b>Vertex Label Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX_VARIABLE__VERTEX_LABEL_SET = eINSTANCE.getVertexVariable_VertexLabelSet();

		/**
		 * The meta object literal for the '{@link relalg.impl.AbstractEdgeVariableImpl <em>Abstract Edge Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AbstractEdgeVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getAbstractEdgeVariable()
		 * @generated
		 */
		EClass ABSTRACT_EDGE_VARIABLE = eINSTANCE.getAbstractEdgeVariable();

		/**
		 * The meta object literal for the '<em><b>Edge Label Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET = eINSTANCE.getAbstractEdgeVariable_EdgeLabelSet();

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
		 * The meta object literal for the '{@link relalg.impl.EdgeListVariableImpl <em>Edge List Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EdgeListVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getEdgeListVariable()
		 * @generated
		 */
		EClass EDGE_LIST_VARIABLE = eINSTANCE.getEdgeListVariable();

		/**
		 * The meta object literal for the '<em><b>Min Hops</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE_LIST_VARIABLE__MIN_HOPS = eINSTANCE.getEdgeListVariable_MinHops();

		/**
		 * The meta object literal for the '<em><b>Max Hops</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE_LIST_VARIABLE__MAX_HOPS = eINSTANCE.getEdgeListVariable_MaxHops();

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
		 * The meta object literal for the '<em><b>Exp Var</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_VARIABLE__EXP_VAR = eINSTANCE.getAttributeVariable_ExpVar();

		/**
		 * The meta object literal for the '<em><b>Get Base Variable</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATTRIBUTE_VARIABLE___GET_BASE_VARIABLE = eINSTANCE.getAttributeVariable__GetBaseVariable();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ATTRIBUTE_VARIABLE___FULL_NAME = eINSTANCE.getAttributeVariable__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.ListVariableImpl <em>List Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ListVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getListVariable()
		 * @generated
		 */
		EClass LIST_VARIABLE = eINSTANCE.getListVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.PathVariableImpl <em>Path Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.PathVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getPathVariable()
		 * @generated
		 */
		EClass PATH_VARIABLE = eINSTANCE.getPathVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.ExpressionVariableImpl <em>Expression Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ExpressionVariableImpl
		 * @see relalg.impl.RelalgPackageImpl#getExpressionVariable()
		 * @generated
		 */
		EClass EXPRESSION_VARIABLE = eINSTANCE.getExpressionVariable();

		/**
		 * The meta object literal for the '<em><b>Has Inferred Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION_VARIABLE__HAS_INFERRED_NAME = eINSTANCE.getExpressionVariable_HasInferredName();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_VARIABLE__EXPRESSION = eINSTANCE.getExpressionVariable_Expression();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EXPRESSION_VARIABLE___FULL_NAME = eINSTANCE.getExpressionVariable__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.OperatorImpl <em>Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.OperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getOperator()
		 * @generated
		 */
		EClass OPERATOR = eINSTANCE.getOperator();

		/**
		 * The meta object literal for the '<em><b>External Schema</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR__EXTERNAL_SCHEMA = eINSTANCE.getOperator_ExternalSchema();

		/**
		 * The meta object literal for the '<em><b>Extra Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR__EXTRA_VARIABLES = eINSTANCE.getOperator_ExtraVariables();

		/**
		 * The meta object literal for the '<em><b>Internal Schema</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR__INTERNAL_SCHEMA = eINSTANCE.getOperator_InternalSchema();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATOR__CARDINALITY = eINSTANCE.getOperator_Cardinality();

		/**
		 * The meta object literal for the '{@link relalg.impl.CardinalityImpl <em>Cardinality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CardinalityImpl
		 * @see relalg.impl.RelalgPackageImpl#getCardinality()
		 * @generated
		 */
		EClass CARDINALITY = eINSTANCE.getCardinality();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARDINALITY__VALUE = eINSTANCE.getCardinality_Value();

		/**
		 * The meta object literal for the '{@link relalg.impl.NullaryOperatorImpl <em>Nullary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.NullaryOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getNullaryOperator()
		 * @generated
		 */
		EClass NULLARY_OPERATOR = eINSTANCE.getNullaryOperator();

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
		 * The meta object literal for the '{@link relalg.impl.SingularObjectSourceOperatorImpl <em>Singular Object Source Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SingularObjectSourceOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getSingularObjectSourceOperator()
		 * @generated
		 */
		EClass SINGULAR_OBJECT_SOURCE_OPERATOR = eINSTANCE.getSingularObjectSourceOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.DualObjectSourceOperatorImpl <em>Dual Object Source Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.DualObjectSourceOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getDualObjectSourceOperator()
		 * @generated
		 */
		EClass DUAL_OBJECT_SOURCE_OPERATOR = eINSTANCE.getDualObjectSourceOperator();

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
		 * The meta object literal for the '{@link relalg.impl.UnaryOperatorImpl <em>Unary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnaryOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnaryOperator()
		 * @generated
		 */
		EClass UNARY_OPERATOR = eINSTANCE.getUnaryOperator();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OPERATOR__INPUT = eINSTANCE.getUnaryOperator_Input();

		/**
		 * The meta object literal for the '{@link relalg.impl.BeamerOperatorImpl <em>Beamer Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BeamerOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getBeamerOperator()
		 * @generated
		 */
		EClass BEAMER_OPERATOR = eINSTANCE.getBeamerOperator();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEAMER_OPERATOR__ELEMENTS = eINSTANCE.getBeamerOperator_Elements();

		/**
		 * The meta object literal for the '<em><b>Internal Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEAMER_OPERATOR__INTERNAL_ELEMENTS = eINSTANCE.getBeamerOperator_InternalElements();

		/**
		 * The meta object literal for the '<em><b>Tuple Indices</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEAMER_OPERATOR__TUPLE_INDICES = eINSTANCE.getBeamerOperator_TupleIndices();

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
		 * The meta object literal for the '{@link relalg.impl.AbstractConditionImpl <em>Abstract Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AbstractConditionImpl
		 * @see relalg.impl.RelalgPackageImpl#getAbstractCondition()
		 * @generated
		 */
		EClass ABSTRACT_CONDITION = eINSTANCE.getAbstractCondition();

		/**
		 * The meta object literal for the '<em><b>Condition String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONDITION__CONDITION_STRING = eINSTANCE.getAbstractCondition_ConditionString();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_CONDITION__CONDITION = eINSTANCE.getAbstractCondition_Condition();

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
		 * The meta object literal for the '<em><b>To String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SELECTION_OPERATOR___TO_STRING = eINSTANCE.getSelectionOperator__ToString();

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
		 * The meta object literal for the '{@link relalg.impl.GroupingOperatorImpl <em>Grouping Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.GroupingOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getGroupingOperator()
		 * @generated
		 */
		EClass GROUPING_OPERATOR = eINSTANCE.getGroupingOperator();

		/**
		 * The meta object literal for the '<em><b>Aggregation Criteria</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUPING_OPERATOR__AGGREGATION_CRITERIA = eINSTANCE.getGroupingOperator_AggregationCriteria();

		/**
		 * The meta object literal for the '<em><b>Order</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUPING_OPERATOR__ORDER = eINSTANCE.getGroupingOperator_Order();

		/**
		 * The meta object literal for the '{@link relalg.impl.CUDOperatorImpl <em>CUD Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CUDOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getCUDOperator()
		 * @generated
		 */
		EClass CUD_OPERATOR = eINSTANCE.getCUDOperator();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUD_OPERATOR__ELEMENTS = eINSTANCE.getCUDOperator_Elements();

		/**
		 * The meta object literal for the '{@link relalg.impl.CreateOperatorImpl <em>Create Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CreateOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getCreateOperator()
		 * @generated
		 */
		EClass CREATE_OPERATOR = eINSTANCE.getCreateOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.DeleteOperatorImpl <em>Delete Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.DeleteOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getDeleteOperator()
		 * @generated
		 */
		EClass DELETE_OPERATOR = eINSTANCE.getDeleteOperator();

		/**
		 * The meta object literal for the '<em><b>Detach</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_OPERATOR__DETACH = eINSTANCE.getDeleteOperator_Detach();

		/**
		 * The meta object literal for the '{@link relalg.impl.NavigationDescriptorImpl <em>Navigation Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.NavigationDescriptorImpl
		 * @see relalg.impl.RelalgPackageImpl#getNavigationDescriptor()
		 * @generated
		 */
		EClass NAVIGATION_DESCRIPTOR = eINSTANCE.getNavigationDescriptor();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_DESCRIPTOR__DIRECTION = eINSTANCE.getNavigationDescriptor_Direction();

		/**
		 * The meta object literal for the '<em><b>Source Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_DESCRIPTOR__SOURCE_VERTEX_VARIABLE = eINSTANCE.getNavigationDescriptor_SourceVertexVariable();

		/**
		 * The meta object literal for the '<em><b>Edge Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_DESCRIPTOR__EDGE_VARIABLE = eINSTANCE.getNavigationDescriptor_EdgeVariable();

		/**
		 * The meta object literal for the '<em><b>Target Vertex Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_DESCRIPTOR__TARGET_VERTEX_VARIABLE = eINSTANCE.getNavigationDescriptor_TargetVertexVariable();

		/**
		 * The meta object literal for the '{@link relalg.impl.MaxHopsImpl <em>Max Hops</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.MaxHopsImpl
		 * @see relalg.impl.RelalgPackageImpl#getMaxHops()
		 * @generated
		 */
		EClass MAX_HOPS = eINSTANCE.getMaxHops();

		/**
		 * The meta object literal for the '<em><b>Max Hops Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAX_HOPS__MAX_HOPS_TYPE = eINSTANCE.getMaxHops_MaxHopsType();

		/**
		 * The meta object literal for the '<em><b>Hops</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAX_HOPS__HOPS = eINSTANCE.getMaxHops_Hops();

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
		 * The meta object literal for the '{@link relalg.impl.PathOperatorImpl <em>Path Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.PathOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getPathOperator()
		 * @generated
		 */
		EClass PATH_OPERATOR = eINSTANCE.getPathOperator();

		/**
		 * The meta object literal for the '<em><b>Semantics</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH_OPERATOR__SEMANTICS = eINSTANCE.getPathOperator_Semantics();

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
		 * The meta object literal for the '{@link relalg.impl.SortOperatorImpl <em>Sort Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SortOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getSortOperator()
		 * @generated
		 */
		EClass SORT_OPERATOR = eINSTANCE.getSortOperator();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SORT_OPERATOR__ENTRIES = eINSTANCE.getSortOperator_Entries();

		/**
		 * The meta object literal for the '{@link relalg.impl.SortEntryImpl <em>Sort Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SortEntryImpl
		 * @see relalg.impl.RelalgPackageImpl#getSortEntry()
		 * @generated
		 */
		EClass SORT_ENTRY = eINSTANCE.getSortEntry();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SORT_ENTRY__EXPRESSION = eINSTANCE.getSortEntry_Expression();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SORT_ENTRY__DIRECTION = eINSTANCE.getSortEntry_Direction();

		/**
		 * The meta object literal for the '{@link relalg.impl.TopOperatorImpl <em>Top Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.TopOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getTopOperator()
		 * @generated
		 */
		EClass TOP_OPERATOR = eINSTANCE.getTopOperator();

		/**
		 * The meta object literal for the '<em><b>Skip</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOP_OPERATOR__SKIP = eINSTANCE.getTopOperator_Skip();

		/**
		 * The meta object literal for the '<em><b>Limit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOP_OPERATOR__LIMIT = eINSTANCE.getTopOperator_Limit();

		/**
		 * The meta object literal for the '{@link relalg.impl.SortAndTopOperatorImpl <em>Sort And Top Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SortAndTopOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getSortAndTopOperator()
		 * @generated
		 */
		EClass SORT_AND_TOP_OPERATOR = eINSTANCE.getSortAndTopOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnwindOperatorImpl <em>Unwind Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnwindOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnwindOperator()
		 * @generated
		 */
		EClass UNWIND_OPERATOR = eINSTANCE.getUnwindOperator();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNWIND_OPERATOR__ELEMENT = eINSTANCE.getUnwindOperator_Element();

		/**
		 * The meta object literal for the '{@link relalg.impl.BinaryOperatorImpl <em>Binary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BinaryOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getBinaryOperator()
		 * @generated
		 */
		EClass BINARY_OPERATOR = eINSTANCE.getBinaryOperator();

		/**
		 * The meta object literal for the '<em><b>Left Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATOR__LEFT_INPUT = eINSTANCE.getBinaryOperator_LeftInput();

		/**
		 * The meta object literal for the '<em><b>Right Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATOR__RIGHT_INPUT = eINSTANCE.getBinaryOperator_RightInput();

		/**
		 * The meta object literal for the '{@link relalg.impl.CommutativeBinaryOperatorImpl <em>Commutative Binary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CommutativeBinaryOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getCommutativeBinaryOperator()
		 * @generated
		 */
		EClass COMMUTATIVE_BINARY_OPERATOR = eINSTANCE.getCommutativeBinaryOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.AssociativeBinaryOperatorImpl <em>Associative Binary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AssociativeBinaryOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getAssociativeBinaryOperator()
		 * @generated
		 */
		EClass ASSOCIATIVE_BINARY_OPERATOR = eINSTANCE.getAssociativeBinaryOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.AbstractJoinOperatorImpl <em>Abstract Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AbstractJoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getAbstractJoinOperator()
		 * @generated
		 */
		EClass ABSTRACT_JOIN_OPERATOR = eINSTANCE.getAbstractJoinOperator();

		/**
		 * The meta object literal for the '<em><b>Common Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES = eINSTANCE.getAbstractJoinOperator_CommonVariables();

		/**
		 * The meta object literal for the '<em><b>Left Mask</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_JOIN_OPERATOR__LEFT_MASK = eINSTANCE.getAbstractJoinOperator_LeftMask();

		/**
		 * The meta object literal for the '<em><b>Right Mask</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_JOIN_OPERATOR__RIGHT_MASK = eINSTANCE.getAbstractJoinOperator_RightMask();

		/**
		 * The meta object literal for the '{@link relalg.impl.EquiJoinLikeOperatorImpl <em>Equi Join Like Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EquiJoinLikeOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getEquiJoinLikeOperator()
		 * @generated
		 */
		EClass EQUI_JOIN_LIKE_OPERATOR = eINSTANCE.getEquiJoinLikeOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.LeftOuterJoinOperatorImpl <em>Left Outer Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LeftOuterJoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getLeftOuterJoinOperator()
		 * @generated
		 */
		EClass LEFT_OUTER_JOIN_OPERATOR = eINSTANCE.getLeftOuterJoinOperator();

		/**
		 * The meta object literal for the '{@link relalg.impl.ThetaLeftOuterJoinOperatorImpl <em>Theta Left Outer Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ThetaLeftOuterJoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getThetaLeftOuterJoinOperator()
		 * @generated
		 */
		EClass THETA_LEFT_OUTER_JOIN_OPERATOR = eINSTANCE.getThetaLeftOuterJoinOperator();

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
		 * The meta object literal for the '{@link relalg.impl.TransitiveClosureJoinOperatorImpl <em>Transitive Closure Join Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.TransitiveClosureJoinOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getTransitiveClosureJoinOperator()
		 * @generated
		 */
		EClass TRANSITIVE_CLOSURE_JOIN_OPERATOR = eINSTANCE.getTransitiveClosureJoinOperator();

		/**
		 * The meta object literal for the '<em><b>Edge List Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE = eINSTANCE.getTransitiveClosureJoinOperator_EdgeListVariable();

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
		 * The meta object literal for the '{@link relalg.impl.UnionOperatorImpl <em>Union Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnionOperatorImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnionOperator()
		 * @generated
		 */
		EClass UNION_OPERATOR = eINSTANCE.getUnionOperator();

		/**
		 * The meta object literal for the '<em><b>Bag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNION_OPERATOR__BAG = eINSTANCE.getUnionOperator_Bag();

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
		 * The meta object literal for the '<em><b>Expression Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__EXPRESSION_CONTAINER = eINSTANCE.getExpression_ExpressionContainer();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__TEXT = eINSTANCE.getExpression_Text();

		/**
		 * The meta object literal for the '{@link relalg.impl.CaseExpressionImpl <em>Case Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CaseExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getCaseExpression()
		 * @generated
		 */
		EClass CASE_EXPRESSION = eINSTANCE.getCaseExpression();

		/**
		 * The meta object literal for the '<em><b>Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_EXPRESSION__CASES = eINSTANCE.getCaseExpression_Cases();

		/**
		 * The meta object literal for the '<em><b>Fallback</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_EXPRESSION__FALLBACK = eINSTANCE.getCaseExpression_Fallback();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CASE_EXPRESSION___FULL_NAME = eINSTANCE.getCaseExpression__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.GenericCaseExpressionImpl <em>Generic Case Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.GenericCaseExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getGenericCaseExpression()
		 * @generated
		 */
		EClass GENERIC_CASE_EXPRESSION = eINSTANCE.getGenericCaseExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.SimpleCaseExpressionImpl <em>Simple Case Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.SimpleCaseExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getSimpleCaseExpression()
		 * @generated
		 */
		EClass SIMPLE_CASE_EXPRESSION = eINSTANCE.getSimpleCaseExpression();

		/**
		 * The meta object literal for the '<em><b>Test</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMPLE_CASE_EXPRESSION__TEST = eINSTANCE.getSimpleCaseExpression_Test();

		/**
		 * The meta object literal for the '{@link relalg.impl.ArithmeticExpressionImpl <em>Arithmetic Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ArithmeticExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getArithmeticExpression()
		 * @generated
		 */
		EClass ARITHMETIC_EXPRESSION = eINSTANCE.getArithmeticExpression();

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
		 * The meta object literal for the '{@link relalg.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BinaryExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getBinaryExpression()
		 * @generated
		 */
		EClass BINARY_EXPRESSION = eINSTANCE.getBinaryExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnaryArithmeticOperationExpressionImpl <em>Unary Arithmetic Operation Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnaryArithmeticOperationExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperationExpression()
		 * @generated
		 */
		EClass UNARY_ARITHMETIC_OPERATION_EXPRESSION = eINSTANCE.getUnaryArithmeticOperationExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = eINSTANCE.getUnaryArithmeticOperationExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND = eINSTANCE.getUnaryArithmeticOperationExpression_Operand();

		/**
		 * The meta object literal for the '{@link relalg.impl.BinaryArithmeticOperationExpressionImpl <em>Binary Arithmetic Operation Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BinaryArithmeticOperationExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperationExpression()
		 * @generated
		 */
		EClass BINARY_ARITHMETIC_OPERATION_EXPRESSION = eINSTANCE.getBinaryArithmeticOperationExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR = eINSTANCE.getBinaryArithmeticOperationExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND = eINSTANCE.getBinaryArithmeticOperationExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getBinaryArithmeticOperationExpression_RightOperand();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BINARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME = eINSTANCE.getBinaryArithmeticOperationExpression__FullName();

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
		 * The meta object literal for the '<em><b>Left Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND = eINSTANCE.getArithmeticComparisonExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getArithmeticComparisonExpression_RightOperand();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ARITHMETIC_COMPARISON_EXPRESSION___FULL_NAME = eINSTANCE.getArithmeticComparisonExpression__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.StringExpressionImpl <em>String Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.StringExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getStringExpression()
		 * @generated
		 */
		EClass STRING_EXPRESSION = eINSTANCE.getStringExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.LogicalExpressionImpl <em>Logical Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LogicalExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getLogicalExpression()
		 * @generated
		 */
		EClass LOGICAL_EXPRESSION = eINSTANCE.getLogicalExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.ListExpressionImpl <em>List Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ListExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getListExpression()
		 * @generated
		 */
		EClass LIST_EXPRESSION = eINSTANCE.getListExpression();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_EXPRESSION__HEAD = eINSTANCE.getListExpression_Head();

		/**
		 * The meta object literal for the '<em><b>Tail</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_EXPRESSION__TAIL = eINSTANCE.getListExpression_Tail();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation LIST_EXPRESSION___FULL_NAME = eINSTANCE.getListExpression__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.EmptyListExpressionImpl <em>Empty List Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.EmptyListExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getEmptyListExpression()
		 * @generated
		 */
		EClass EMPTY_LIST_EXPRESSION = eINSTANCE.getEmptyListExpression();

		/**
		 * The meta object literal for the '{@link relalg.impl.IndexAccessExpressionImpl <em>Index Access Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.IndexAccessExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getIndexAccessExpression()
		 * @generated
		 */
		EClass INDEX_ACCESS_EXPRESSION = eINSTANCE.getIndexAccessExpression();

		/**
		 * The meta object literal for the '<em><b>List</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INDEX_ACCESS_EXPRESSION__LIST = eINSTANCE.getIndexAccessExpression_List();

		/**
		 * The meta object literal for the '{@link relalg.impl.IndexSimpleAccessExpressionImpl <em>Index Simple Access Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.IndexSimpleAccessExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getIndexSimpleAccessExpression()
		 * @generated
		 */
		EClass INDEX_SIMPLE_ACCESS_EXPRESSION = eINSTANCE.getIndexSimpleAccessExpression();

		/**
		 * The meta object literal for the '<em><b>Idx</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_SIMPLE_ACCESS_EXPRESSION__IDX = eINSTANCE.getIndexSimpleAccessExpression_Idx();

		/**
		 * The meta object literal for the '{@link relalg.impl.IndexRangeAccessExpressionImpl <em>Index Range Access Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.IndexRangeAccessExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getIndexRangeAccessExpression()
		 * @generated
		 */
		EClass INDEX_RANGE_ACCESS_EXPRESSION = eINSTANCE.getIndexRangeAccessExpression();

		/**
		 * The meta object literal for the '<em><b>Lower</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_RANGE_ACCESS_EXPRESSION__LOWER = eINSTANCE.getIndexRangeAccessExpression_Lower();

		/**
		 * The meta object literal for the '<em><b>Upper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_RANGE_ACCESS_EXPRESSION__UPPER = eINSTANCE.getIndexRangeAccessExpression_Upper();

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
		 * The meta object literal for the '<em><b>Left Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND = eINSTANCE.getBinaryLogicalExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getBinaryLogicalExpression_RightOperand();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnaryLogicalExpressionImpl <em>Unary Logical Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnaryLogicalExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnaryLogicalExpression()
		 * @generated
		 */
		EClass UNARY_LOGICAL_EXPRESSION = eINSTANCE.getUnaryLogicalExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_LOGICAL_EXPRESSION__OPERATOR = eINSTANCE.getUnaryLogicalExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_LOGICAL_EXPRESSION__OPERAND = eINSTANCE.getUnaryLogicalExpression_Operand();

		/**
		 * The meta object literal for the '{@link relalg.impl.UnaryGraphObjectLogicalExpressionImpl <em>Unary Graph Object Logical Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.UnaryGraphObjectLogicalExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getUnaryGraphObjectLogicalExpression()
		 * @generated
		 */
		EClass UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION = eINSTANCE.getUnaryGraphObjectLogicalExpression();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERATOR = eINSTANCE.getUnaryGraphObjectLogicalExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERAND = eINSTANCE.getUnaryGraphObjectLogicalExpression_Operand();

		/**
		 * The meta object literal for the '{@link relalg.impl.ComparableExpressionImpl <em>Comparable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ComparableExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getComparableExpression()
		 * @generated
		 */
		EClass COMPARABLE_EXPRESSION = eINSTANCE.getComparableExpression();

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
		 * The meta object literal for the '{@link relalg.impl.LiteralImpl <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.LiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getLiteral()
		 * @generated
		 */
		EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '{@link relalg.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BooleanLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getBooleanLiteral()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL__VALUE = eINSTANCE.getBooleanLiteral_Value();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BOOLEAN_LITERAL___FULL_NAME = eINSTANCE.getBooleanLiteral__FullName();

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
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STRING_LITERAL___FULL_NAME = eINSTANCE.getStringLiteral__FullName();

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
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOUBLE_LITERAL___FULL_NAME = eINSTANCE.getDoubleLiteral__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.BigIntegerLiteralImpl <em>Big Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BigIntegerLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getBigIntegerLiteral()
		 * @generated
		 */
		EClass BIG_INTEGER_LITERAL = eINSTANCE.getBigIntegerLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BIG_INTEGER_LITERAL__VALUE = eINSTANCE.getBigIntegerLiteral_Value();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BIG_INTEGER_LITERAL___FULL_NAME = eINSTANCE.getBigIntegerLiteral__FullName();

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
		 * The meta object literal for the '<em><b>Full Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation INTEGER_LITERAL___FULL_NAME = eINSTANCE.getIntegerLiteral__FullName();

		/**
		 * The meta object literal for the '{@link relalg.impl.NullLiteralImpl <em>Null Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.NullLiteralImpl
		 * @see relalg.impl.RelalgPackageImpl#getNullLiteral()
		 * @generated
		 */
		EClass NULL_LITERAL = eINSTANCE.getNullLiteral();

		/**
		 * The meta object literal for the '{@link relalg.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ParameterImpl
		 * @see relalg.impl.RelalgPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '{@link relalg.impl.ParameterComparableExpressionImpl <em>Parameter Comparable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ParameterComparableExpressionImpl
		 * @see relalg.impl.RelalgPackageImpl#getParameterComparableExpression()
		 * @generated
		 */
		EClass PARAMETER_COMPARABLE_EXPRESSION = eINSTANCE.getParameterComparableExpression();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_COMPARABLE_EXPRESSION__PARAMETER = eINSTANCE.getParameterComparableExpression_Parameter();

		/**
		 * The meta object literal for the '{@link relalg.impl.PropertyListImpl <em>Property List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.PropertyListImpl
		 * @see relalg.impl.RelalgPackageImpl#getPropertyList()
		 * @generated
		 */
		EClass PROPERTY_LIST = eINSTANCE.getPropertyList();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_LIST__ENTRIES = eINSTANCE.getPropertyList_Entries();

		/**
		 * The meta object literal for the '{@link relalg.impl.PropertyListEntryImpl <em>Property List Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.PropertyListEntryImpl
		 * @see relalg.impl.RelalgPackageImpl#getPropertyListEntry()
		 * @generated
		 */
		EClass PROPERTY_LIST_ENTRY = eINSTANCE.getPropertyListEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_LIST_ENTRY__KEY = eINSTANCE.getPropertyListEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_LIST_ENTRY__VALUE = eINSTANCE.getPropertyListEntry_Value();

		/**
		 * The meta object literal for the '{@link relalg.impl.CaseImpl <em>Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.CaseImpl
		 * @see relalg.impl.RelalgPackageImpl#getCase()
		 * @generated
		 */
		EClass CASE = eINSTANCE.getCase();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE__WHEN = eINSTANCE.getCase_When();

		/**
		 * The meta object literal for the '<em><b>Then</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE__THEN = eINSTANCE.getCase_Then();

		/**
		 * The meta object literal for the '{@link relalg.MaxHopsType <em>Max Hops Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.MaxHopsType
		 * @see relalg.impl.RelalgPackageImpl#getMaxHopsType()
		 * @generated
		 */
		EEnum MAX_HOPS_TYPE = eINSTANCE.getMaxHopsType();

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
		 * The meta object literal for the '{@link relalg.OrderDirection <em>Order Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.OrderDirection
		 * @see relalg.impl.RelalgPackageImpl#getOrderDirection()
		 * @generated
		 */
		EEnum ORDER_DIRECTION = eINSTANCE.getOrderDirection();

		/**
		 * The meta object literal for the '{@link relalg.BinaryArithmeticOperatorType <em>Binary Arithmetic Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.BinaryArithmeticOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getBinaryArithmeticOperatorType()
		 * @generated
		 */
		EEnum BINARY_ARITHMETIC_OPERATOR_TYPE = eINSTANCE.getBinaryArithmeticOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.ArithmeticComparisonOperatorType <em>Arithmetic Comparison Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.ArithmeticComparisonOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getArithmeticComparisonOperatorType()
		 * @generated
		 */
		EEnum ARITHMETIC_COMPARISON_OPERATOR_TYPE = eINSTANCE.getArithmeticComparisonOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.UnaryArithmeticOperatorType <em>Unary Arithmetic Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.UnaryArithmeticOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getUnaryArithmeticOperatorType()
		 * @generated
		 */
		EEnum UNARY_ARITHMETIC_OPERATOR_TYPE = eINSTANCE.getUnaryArithmeticOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.BinaryLogicalOperatorType <em>Binary Logical Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.BinaryLogicalOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getBinaryLogicalOperatorType()
		 * @generated
		 */
		EEnum BINARY_LOGICAL_OPERATOR_TYPE = eINSTANCE.getBinaryLogicalOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.UnaryLogicalOperatorType <em>Unary Logical Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.UnaryLogicalOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getUnaryLogicalOperatorType()
		 * @generated
		 */
		EEnum UNARY_LOGICAL_OPERATOR_TYPE = eINSTANCE.getUnaryLogicalOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.UnaryGraphObjectLogicalOperatorType <em>Unary Graph Object Logical Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.UnaryGraphObjectLogicalOperatorType
		 * @see relalg.impl.RelalgPackageImpl#getUnaryGraphObjectLogicalOperatorType()
		 * @generated
		 */
		EEnum UNARY_GRAPH_OBJECT_LOGICAL_OPERATOR_TYPE = eINSTANCE.getUnaryGraphObjectLogicalOperatorType();

		/**
		 * The meta object literal for the '{@link relalg.LabelSetStatus <em>Label Set Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.LabelSetStatus
		 * @see relalg.impl.RelalgPackageImpl#getLabelSetStatus()
		 * @generated
		 */
		EEnum LABEL_SET_STATUS = eINSTANCE.getLabelSetStatus();

		/**
		 * The meta object literal for the '{@link relalg.PathSemantics <em>Path Semantics</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.PathSemantics
		 * @see relalg.impl.RelalgPackageImpl#getPathSemantics()
		 * @generated
		 */
		EEnum PATH_SEMANTICS = eINSTANCE.getPathSemantics();

		/**
		 * The meta object literal for the '<em>Function</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.function.Function
		 * @see relalg.impl.RelalgPackageImpl#getFunction()
		 * @generated
		 */
		EDataType FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em>Bigint</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see relalg.impl.RelalgPackageImpl#getBigint()
		 * @generated
		 */
		EDataType BIGINT = eINSTANCE.getBigint();

	}

} //RelalgPackage
