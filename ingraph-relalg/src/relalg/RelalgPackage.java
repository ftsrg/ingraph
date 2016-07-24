/**
 */
package relalg;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
	 * The meta object id for the '{@link relalg.impl.InputRelationImpl <em>Input Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.InputRelationImpl
	 * @see relalg.impl.RelalgPackageImpl#getInputRelation()
	 * @generated
	 */
	int INPUT_RELATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_RELATION__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_RELATION__ATTRIBUTES = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_RELATION__TYPE = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Input Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_RELATION_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Input Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_RELATION_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AlphaOperationImpl <em>Alpha Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AlphaOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getAlphaOperation()
	 * @generated
	 */
	int ALPHA_OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATION__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATION__PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Alpha Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATION_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Alpha Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALPHA_OPERATION_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.TrimmerOperationImpl <em>Trimmer Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.TrimmerOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getTrimmerOperation()
	 * @generated
	 */
	int TRIMMER_OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_OPERATION__NAME = ALPHA_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_OPERATION__PARENT = ALPHA_OPERATION__PARENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_OPERATION__ATTRIBUTES = ALPHA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trimmer Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_OPERATION_FEATURE_COUNT = ALPHA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Trimmer Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_OPERATION_OPERATION_COUNT = ALPHA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.BetaOperationImpl <em>Beta Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.BetaOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getBetaOperation()
	 * @generated
	 */
	int BETA_OPERATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION__NAME = ALGEBRA_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION__LEFT_PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION__RIGHT_PARENT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION__BINDINGS = ALGEBRA_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Beta Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION_FEATURE_COUNT = ALGEBRA_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Beta Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BETA_OPERATION_OPERATION_COUNT = ALGEBRA_EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.JoinOperationImpl <em>Join Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.JoinOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getJoinOperation()
	 * @generated
	 */
	int JOIN_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION__NAME = BETA_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION__LEFT_PARENT = BETA_OPERATION__LEFT_PARENT;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION__RIGHT_PARENT = BETA_OPERATION__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION__BINDINGS = BETA_OPERATION__BINDINGS;

	/**
	 * The number of structural features of the '<em>Join Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION_FEATURE_COUNT = BETA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Join Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION_OPERATION_COUNT = BETA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AntiJoinOperationImpl <em>Anti Join Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AntiJoinOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getAntiJoinOperation()
	 * @generated
	 */
	int ANTI_JOIN_OPERATION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION__NAME = BETA_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION__LEFT_PARENT = BETA_OPERATION__LEFT_PARENT;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION__RIGHT_PARENT = BETA_OPERATION__RIGHT_PARENT;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION__BINDINGS = BETA_OPERATION__BINDINGS;

	/**
	 * The number of structural features of the '<em>Anti Join Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION_FEATURE_COUNT = BETA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Anti Join Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANTI_JOIN_OPERATION_OPERATION_COUNT = BETA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.ProductionOperationImpl <em>Production Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ProductionOperationImpl
	 * @see relalg.impl.RelalgPackageImpl#getProductionOperation()
	 * @generated
	 */
	int PRODUCTION_OPERATION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATION__NAME = ALPHA_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATION__PARENT = ALPHA_OPERATION__PARENT;

	/**
	 * The number of structural features of the '<em>Production Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATION_FEATURE_COUNT = ALPHA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Production Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATION_OPERATION_COUNT = ALPHA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AttributeImpl
	 * @see relalg.impl.RelalgPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.AttributeSetImpl <em>Attribute Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.AttributeSetImpl
	 * @see relalg.impl.RelalgPackageImpl#getAttributeSet()
	 * @generated
	 */
	int ATTRIBUTE_SET = 9;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SET__ATTRIBUTES = 0;

	/**
	 * The number of structural features of the '<em>Attribute Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SET_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Attribute Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.JoinBindingImpl <em>Join Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.JoinBindingImpl
	 * @see relalg.impl.RelalgPackageImpl#getJoinBinding()
	 * @generated
	 */
	int JOIN_BINDING = 10;

	/**
	 * The feature id for the '<em><b>Left Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_BINDING__LEFT_ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Right Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_BINDING__RIGHT_ATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>Join Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_BINDING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Join Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_BINDING_OPERATION_COUNT = 0;


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
	 * Returns the meta object for class '{@link relalg.InputRelation <em>Input Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Relation</em>'.
	 * @see relalg.InputRelation
	 * @generated
	 */
	EClass getInputRelation();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.InputRelation#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see relalg.InputRelation#getAttributes()
	 * @see #getInputRelation()
	 * @generated
	 */
	EReference getInputRelation_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link relalg.InputRelation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see relalg.InputRelation#getType()
	 * @see #getInputRelation()
	 * @generated
	 */
	EAttribute getInputRelation_Type();

	/**
	 * Returns the meta object for class '{@link relalg.TrimmerOperation <em>Trimmer Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trimmer Operation</em>'.
	 * @see relalg.TrimmerOperation
	 * @generated
	 */
	EClass getTrimmerOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.TrimmerOperation#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see relalg.TrimmerOperation#getAttributes()
	 * @see #getTrimmerOperation()
	 * @generated
	 */
	EReference getTrimmerOperation_Attributes();

	/**
	 * Returns the meta object for class '{@link relalg.JoinOperation <em>Join Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Operation</em>'.
	 * @see relalg.JoinOperation
	 * @generated
	 */
	EClass getJoinOperation();

	/**
	 * Returns the meta object for class '{@link relalg.AlphaOperation <em>Alpha Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alpha Operation</em>'.
	 * @see relalg.AlphaOperation
	 * @generated
	 */
	EClass getAlphaOperation();

	/**
	 * Returns the meta object for the reference '{@link relalg.AlphaOperation#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see relalg.AlphaOperation#getParent()
	 * @see #getAlphaOperation()
	 * @generated
	 */
	EReference getAlphaOperation_Parent();

	/**
	 * Returns the meta object for class '{@link relalg.BetaOperation <em>Beta Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Beta Operation</em>'.
	 * @see relalg.BetaOperation
	 * @generated
	 */
	EClass getBetaOperation();

	/**
	 * Returns the meta object for the reference '{@link relalg.BetaOperation#getLeftParent <em>Left Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Parent</em>'.
	 * @see relalg.BetaOperation#getLeftParent()
	 * @see #getBetaOperation()
	 * @generated
	 */
	EReference getBetaOperation_LeftParent();

	/**
	 * Returns the meta object for the reference '{@link relalg.BetaOperation#getRightParent <em>Right Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Parent</em>'.
	 * @see relalg.BetaOperation#getRightParent()
	 * @see #getBetaOperation()
	 * @generated
	 */
	EReference getBetaOperation_RightParent();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.BetaOperation#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bindings</em>'.
	 * @see relalg.BetaOperation#getBindings()
	 * @see #getBetaOperation()
	 * @generated
	 */
	EReference getBetaOperation_Bindings();

	/**
	 * Returns the meta object for class '{@link relalg.AntiJoinOperation <em>Anti Join Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anti Join Operation</em>'.
	 * @see relalg.AntiJoinOperation
	 * @generated
	 */
	EClass getAntiJoinOperation();

	/**
	 * Returns the meta object for class '{@link relalg.ProductionOperation <em>Production Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Production Operation</em>'.
	 * @see relalg.ProductionOperation
	 * @generated
	 */
	EClass getProductionOperation();

	/**
	 * Returns the meta object for class '{@link relalg.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see relalg.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link relalg.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for class '{@link relalg.AttributeSet <em>Attribute Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Set</em>'.
	 * @see relalg.AttributeSet
	 * @generated
	 */
	EClass getAttributeSet();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.AttributeSet#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see relalg.AttributeSet#getAttributes()
	 * @see #getAttributeSet()
	 * @generated
	 */
	EReference getAttributeSet_Attributes();

	/**
	 * Returns the meta object for class '{@link relalg.JoinBinding <em>Join Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Binding</em>'.
	 * @see relalg.JoinBinding
	 * @generated
	 */
	EClass getJoinBinding();

	/**
	 * Returns the meta object for the reference '{@link relalg.JoinBinding#getLeftAttribute <em>Left Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Attribute</em>'.
	 * @see relalg.JoinBinding#getLeftAttribute()
	 * @see #getJoinBinding()
	 * @generated
	 */
	EReference getJoinBinding_LeftAttribute();

	/**
	 * Returns the meta object for the reference '{@link relalg.JoinBinding#getRightAttribute <em>Right Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Attribute</em>'.
	 * @see relalg.JoinBinding#getRightAttribute()
	 * @see #getJoinBinding()
	 * @generated
	 */
	EReference getJoinBinding_RightAttribute();

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
		 * The meta object literal for the '{@link relalg.impl.InputRelationImpl <em>Input Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.InputRelationImpl
		 * @see relalg.impl.RelalgPackageImpl#getInputRelation()
		 * @generated
		 */
		EClass INPUT_RELATION = eINSTANCE.getInputRelation();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_RELATION__ATTRIBUTES = eINSTANCE.getInputRelation_Attributes();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_RELATION__TYPE = eINSTANCE.getInputRelation_Type();

		/**
		 * The meta object literal for the '{@link relalg.impl.TrimmerOperationImpl <em>Trimmer Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.TrimmerOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getTrimmerOperation()
		 * @generated
		 */
		EClass TRIMMER_OPERATION = eINSTANCE.getTrimmerOperation();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIMMER_OPERATION__ATTRIBUTES = eINSTANCE.getTrimmerOperation_Attributes();

		/**
		 * The meta object literal for the '{@link relalg.impl.JoinOperationImpl <em>Join Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.JoinOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getJoinOperation()
		 * @generated
		 */
		EClass JOIN_OPERATION = eINSTANCE.getJoinOperation();

		/**
		 * The meta object literal for the '{@link relalg.impl.AlphaOperationImpl <em>Alpha Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AlphaOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getAlphaOperation()
		 * @generated
		 */
		EClass ALPHA_OPERATION = eINSTANCE.getAlphaOperation();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALPHA_OPERATION__PARENT = eINSTANCE.getAlphaOperation_Parent();

		/**
		 * The meta object literal for the '{@link relalg.impl.BetaOperationImpl <em>Beta Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.BetaOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getBetaOperation()
		 * @generated
		 */
		EClass BETA_OPERATION = eINSTANCE.getBetaOperation();

		/**
		 * The meta object literal for the '<em><b>Left Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATION__LEFT_PARENT = eINSTANCE.getBetaOperation_LeftParent();

		/**
		 * The meta object literal for the '<em><b>Right Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATION__RIGHT_PARENT = eINSTANCE.getBetaOperation_RightParent();

		/**
		 * The meta object literal for the '<em><b>Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BETA_OPERATION__BINDINGS = eINSTANCE.getBetaOperation_Bindings();

		/**
		 * The meta object literal for the '{@link relalg.impl.AntiJoinOperationImpl <em>Anti Join Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AntiJoinOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getAntiJoinOperation()
		 * @generated
		 */
		EClass ANTI_JOIN_OPERATION = eINSTANCE.getAntiJoinOperation();

		/**
		 * The meta object literal for the '{@link relalg.impl.ProductionOperationImpl <em>Production Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ProductionOperationImpl
		 * @see relalg.impl.RelalgPackageImpl#getProductionOperation()
		 * @generated
		 */
		EClass PRODUCTION_OPERATION = eINSTANCE.getProductionOperation();

		/**
		 * The meta object literal for the '{@link relalg.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AttributeImpl
		 * @see relalg.impl.RelalgPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '{@link relalg.impl.AttributeSetImpl <em>Attribute Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.AttributeSetImpl
		 * @see relalg.impl.RelalgPackageImpl#getAttributeSet()
		 * @generated
		 */
		EClass ATTRIBUTE_SET = eINSTANCE.getAttributeSet();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_SET__ATTRIBUTES = eINSTANCE.getAttributeSet_Attributes();

		/**
		 * The meta object literal for the '{@link relalg.impl.JoinBindingImpl <em>Join Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.JoinBindingImpl
		 * @see relalg.impl.RelalgPackageImpl#getJoinBinding()
		 * @generated
		 */
		EClass JOIN_BINDING = eINSTANCE.getJoinBinding();

		/**
		 * The meta object literal for the '<em><b>Left Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_BINDING__LEFT_ATTRIBUTE = eINSTANCE.getJoinBinding_LeftAttribute();

		/**
		 * The meta object literal for the '<em><b>Right Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_BINDING__RIGHT_ATTRIBUTE = eINSTANCE.getJoinBinding_RightAttribute();

	}

} //RelalgPackage
