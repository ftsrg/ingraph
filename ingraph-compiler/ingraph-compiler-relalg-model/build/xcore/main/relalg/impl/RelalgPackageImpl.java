/**
 */
package relalg.impl;

import java.math.BigInteger;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import relalg.AbstractCondition;
import relalg.AbstractEdgeVariable;
import relalg.AbstractJoinOperator;
import relalg.AllDifferentOperator;
import relalg.AntiJoinOperator;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticComparisonOperatorType;
import relalg.ArithmeticExpression;
import relalg.AssociativeBinaryOperator;
import relalg.Atom;
import relalg.AttributeVariable;
import relalg.BeamerOperator;
import relalg.BigIntegerLiteral;
import relalg.BinaryArithmeticOperationExpression;
import relalg.BinaryArithmeticOperatorType;
import relalg.BinaryExpression;
import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperatorType;
import relalg.BinaryOperator;
import relalg.BooleanLiteral;
import relalg.CUDOperator;
import relalg.Cardinality;
import relalg.Case;
import relalg.CaseExpression;
import relalg.CommutativeBinaryOperator;
import relalg.ComparableExpression;
import relalg.CreateOperator;
import relalg.DeleteOperator;
import relalg.Direction;
import relalg.DoubleLiteral;
import relalg.DualObjectSourceOperator;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeLabel;
import relalg.EdgeLabelSet;
import relalg.EdgeListVariable;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.EmptyListExpression;
import relalg.EquiJoinLikeOperator;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.ExpressionVariable;
import relalg.FunctionArithmeticExpression;
import relalg.FunctionComparableExpression;
import relalg.FunctionExpression;
import relalg.FunctionLogicalExpression;
import relalg.GenericCaseExpression;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.GraphObjectVariable;
import relalg.GroupingOperator;
import relalg.IndexAccessExpression;
import relalg.IndexRangeAccessExpression;
import relalg.IndexSimpleAccessExpression;
import relalg.IntegerLiteral;
import relalg.JoinOperator;
import relalg.Label;
import relalg.LabelSet;
import relalg.LabelSetStatus;
import relalg.LeftOuterJoinOperator;
import relalg.ListExpression;
import relalg.ListVariable;
import relalg.Literal;
import relalg.LogicalExpression;
import relalg.MaxHops;
import relalg.MaxHopsType;
import relalg.NamedElement;
import relalg.NavigationDescriptor;
import relalg.NullLiteral;
import relalg.NullaryOperator;
import relalg.NumberLiteral;
import relalg.Operator;
import relalg.OrderDirection;
import relalg.Parameter;
import relalg.ParameterComparableExpression;
import relalg.PathOperator;
import relalg.PathSemantics;
import relalg.PathVariable;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.PropertyList;
import relalg.RelalgContainer;
import relalg.RelalgFactory;
import relalg.RelalgModelElement;
import relalg.RelalgPackage;
import relalg.SelectionOperator;
import relalg.SimpleCaseExpression;
import relalg.SingularObjectSourceOperator;
import relalg.SortAndTopOperator;
import relalg.SortEntry;
import relalg.SortOperator;
import relalg.StringExpression;
import relalg.StringLiteral;
import relalg.ThetaLeftOuterJoinOperator;
import relalg.TopOperator;
import relalg.TransitiveClosureJoinOperator;
import relalg.UnaryArithmeticOperationExpression;
import relalg.UnaryArithmeticOperatorType;
import relalg.UnaryExpression;
import relalg.UnaryGraphObjectLogicalExpression;
import relalg.UnaryGraphObjectLogicalOperatorType;
import relalg.UnaryLogicalExpression;
import relalg.UnaryLogicalOperatorType;
import relalg.UnaryOperator;
import relalg.UnionOperator;
import relalg.UnwindOperator;
import relalg.Variable;
import relalg.VariableArithmeticExpression;
import relalg.VariableComparableExpression;
import relalg.VariableExpression;
import relalg.VariableListExpression;
import relalg.VariableStringExpression;
import relalg.VertexLabel;
import relalg.VertexLabelSet;
import relalg.VertexVariable;

import relalg.function.Function;

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
	private EClass relalgContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relalgModelElementEClass = null;

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
	private EClass labelSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexLabelSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeLabelSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableComparableExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableStringExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableArithmeticExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableListExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionComparableExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionArithmeticExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionLogicalExpressionEClass = null;

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
	private EClass graphObjectVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementVariableEClass = null;

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
	private EClass abstractEdgeVariableEClass = null;

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
	private EClass edgeListVariableEClass = null;

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
	private EClass listVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cardinalityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullaryOperatorEClass = null;

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
	private EClass singularObjectSourceOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dualObjectSourceOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getEdgesOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beamerOperatorEClass = null;

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
	private EClass abstractConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectionOperatorEClass = null;

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
	private EClass groupingOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cudOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deleteOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass maxHopsEClass = null;

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
	private EClass pathOperatorEClass = null;

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
	private EClass allDifferentOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass topOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortAndTopOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unwindOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commutativeBinaryOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associativeBinaryOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractJoinOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equiJoinLikeOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leftOuterJoinOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thetaLeftOuterJoinOperatorEClass = null;

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
	private EClass transitiveClosureJoinOperatorEClass = null;

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
	private EClass unionOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass caseExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericCaseExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simpleCaseExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arithmeticExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryArithmeticOperationExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryArithmeticOperationExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arithmeticComparisonExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emptyListExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexAccessExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexSimpleAccessExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexRangeAccessExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryLogicalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryLogicalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryGraphObjectLogicalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparableExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bigIntegerLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterComparableExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyListEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass caseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum maxHopsTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum directionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orderDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum binaryArithmeticOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arithmeticComparisonOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryArithmeticOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum binaryLogicalOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryLogicalOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryGraphObjectLogicalOperatorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum labelSetStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pathSemanticsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType functionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bigintEDataType = null;

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

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

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
	public EClass getRelalgContainer() {
		return relalgContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_Name() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelalgContainer_RootExpression() {
		return (EReference)relalgContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelalgContainer_Elements() {
		return (EReference)relalgContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelalgContainer_Expressions() {
		return (EReference)relalgContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_SimplifiedPlan() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_IncrementalPlan() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_ExternalSchemaInferred() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_ExtraVariablesInferred() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelalgContainer_InternalSchemaInferred() {
		return (EAttribute)relalgContainerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelalgModelElement() {
		return relalgModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getRelalgModelElement__FullName() {
		return relalgModelElementEClass.getEOperations().get(0);
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
	public EReference getNamedElement_NamedElementContainer() {
		return (EReference)namedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getNamedElement__FullName() {
		return namedElementEClass.getEOperations().get(0);
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
	public EClass getLabelSet() {
		return labelSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelSet_Status() {
		return (EAttribute)labelSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertexLabelSet() {
		return vertexLabelSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertexLabelSet_VertexLabels() {
		return (EReference)vertexLabelSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdgeLabelSet() {
		return edgeLabelSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeLabelSet_EdgeLabels() {
		return (EReference)edgeLabelSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableExpression() {
		return variableExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableExpression_Variable() {
		return (EReference)variableExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getVariableExpression__FullName() {
		return variableExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableComparableExpression() {
		return variableComparableExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableStringExpression() {
		return variableStringExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableArithmeticExpression() {
		return variableArithmeticExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableListExpression() {
		return variableListExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionExpression() {
		return functionExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionExpression_Functor() {
		return (EAttribute)functionExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionExpression_Arguments() {
		return (EReference)functionExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionExpression__FullName() {
		return functionExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionComparableExpression() {
		return functionComparableExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionArithmeticExpression() {
		return functionArithmeticExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionLogicalExpression() {
		return functionLogicalExpressionEClass;
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
	public EClass getGraphObjectVariable() {
		return graphObjectVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementVariable() {
		return elementVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementVariable_Attributes() {
		return (EReference)elementVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementVariable_Properties() {
		return (EReference)elementVariableEClass.getEStructuralFeatures().get(1);
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
	public EReference getVertexVariable_VertexLabelSet() {
		return (EReference)vertexVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractEdgeVariable() {
		return abstractEdgeVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractEdgeVariable_EdgeLabelSet() {
		return (EReference)abstractEdgeVariableEClass.getEStructuralFeatures().get(0);
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
	public EClass getEdgeListVariable() {
		return edgeListVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeListVariable_MinHops() {
		return (EAttribute)edgeListVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeListVariable_MaxHops() {
		return (EReference)edgeListVariableEClass.getEStructuralFeatures().get(1);
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
	public EReference getAttributeVariable_Element() {
		return (EReference)attributeVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeVariable_ExpVar() {
		return (EReference)attributeVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAttributeVariable__GetBaseVariable() {
		return attributeVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAttributeVariable__FullName() {
		return attributeVariableEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListVariable() {
		return listVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPathVariable() {
		return pathVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpressionVariable() {
		return expressionVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpressionVariable_HasInferredName() {
		return (EAttribute)expressionVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpressionVariable_Expression() {
		return (EReference)expressionVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getExpressionVariable__FullName() {
		return expressionVariableEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperator() {
		return operatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperator_ExternalSchema() {
		return (EReference)operatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperator_ExtraVariables() {
		return (EReference)operatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperator_InternalSchema() {
		return (EReference)operatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperator_Cardinality() {
		return (EReference)operatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCardinality() {
		return cardinalityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCardinality_Value() {
		return (EAttribute)cardinalityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNullaryOperator() {
		return nullaryOperatorEClass;
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
	public EClass getSingularObjectSourceOperator() {
		return singularObjectSourceOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDualObjectSourceOperator() {
		return dualObjectSourceOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGetEdgesOperator() {
		return getEdgesOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryOperator() {
		return unaryOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryOperator_Input() {
		return (EReference)unaryOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeamerOperator() {
		return beamerOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBeamerOperator_Elements() {
		return (EReference)beamerOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBeamerOperator_InternalElements() {
		return (EReference)beamerOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeamerOperator_TupleIndices() {
		return (EAttribute)beamerOperatorEClass.getEStructuralFeatures().get(2);
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
	public EClass getAbstractCondition() {
		return abstractConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCondition_ConditionString() {
		return (EAttribute)abstractConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractCondition_Condition() {
		return (EReference)abstractConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelectionOperator() {
		return selectionOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSelectionOperator__ToString() {
		return selectionOperatorEClass.getEOperations().get(0);
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
	public EClass getGroupingOperator() {
		return groupingOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGroupingOperator_AggregationCriteria() {
		return (EReference)groupingOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroupingOperator_Order() {
		return (EAttribute)groupingOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCUDOperator() {
		return cudOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCUDOperator_Elements() {
		return (EReference)cudOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreateOperator() {
		return createOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeleteOperator() {
		return deleteOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeleteOperator_Detach() {
		return (EAttribute)deleteOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationDescriptor() {
		return navigationDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNavigationDescriptor_Direction() {
		return (EAttribute)navigationDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationDescriptor_SourceVertexVariable() {
		return (EReference)navigationDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationDescriptor_EdgeVariable() {
		return (EReference)navigationDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationDescriptor_TargetVertexVariable() {
		return (EReference)navigationDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMaxHops() {
		return maxHopsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMaxHops_MaxHopsType() {
		return (EAttribute)maxHopsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMaxHops_Hops() {
		return (EAttribute)maxHopsEClass.getEStructuralFeatures().get(1);
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
	public EClass getPathOperator() {
		return pathOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPathOperator_Semantics() {
		return (EAttribute)pathOperatorEClass.getEStructuralFeatures().get(0);
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
	public EClass getSortOperator() {
		return sortOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSortOperator_Entries() {
		return (EReference)sortOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSortEntry() {
		return sortEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSortEntry_Expression() {
		return (EReference)sortEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSortEntry_Direction() {
		return (EAttribute)sortEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTopOperator() {
		return topOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopOperator_Skip() {
		return (EReference)topOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopOperator_Limit() {
		return (EReference)topOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSortAndTopOperator() {
		return sortAndTopOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnwindOperator() {
		return unwindOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnwindOperator_Element() {
		return (EReference)unwindOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryOperator() {
		return binaryOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperator_LeftInput() {
		return (EReference)binaryOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperator_RightInput() {
		return (EReference)binaryOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommutativeBinaryOperator() {
		return commutativeBinaryOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociativeBinaryOperator() {
		return associativeBinaryOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractJoinOperator() {
		return abstractJoinOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractJoinOperator_CommonVariables() {
		return (EReference)abstractJoinOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractJoinOperator_LeftMask() {
		return (EAttribute)abstractJoinOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractJoinOperator_RightMask() {
		return (EAttribute)abstractJoinOperatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEquiJoinLikeOperator() {
		return equiJoinLikeOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeftOuterJoinOperator() {
		return leftOuterJoinOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThetaLeftOuterJoinOperator() {
		return thetaLeftOuterJoinOperatorEClass;
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
	public EClass getTransitiveClosureJoinOperator() {
		return transitiveClosureJoinOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitiveClosureJoinOperator_EdgeListVariable() {
		return (EReference)transitiveClosureJoinOperatorEClass.getEStructuralFeatures().get(0);
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
	public EClass getUnionOperator() {
		return unionOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnionOperator_Bag() {
		return (EAttribute)unionOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpression_ExpressionContainer() {
		return (EReference)expressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExpression_Text() {
		return (EAttribute)expressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCaseExpression() {
		return caseExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCaseExpression_Cases() {
		return (EReference)caseExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCaseExpression_Fallback() {
		return (EReference)caseExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getCaseExpression__FullName() {
		return caseExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericCaseExpression() {
		return genericCaseExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimpleCaseExpression() {
		return simpleCaseExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimpleCaseExpression_Test() {
		return (EReference)simpleCaseExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArithmeticExpression() {
		return arithmeticExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryExpression() {
		return unaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryExpression() {
		return binaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryArithmeticOperationExpression() {
		return unaryArithmeticOperationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryArithmeticOperationExpression_Operator() {
		return (EAttribute)unaryArithmeticOperationExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryArithmeticOperationExpression_Operand() {
		return (EReference)unaryArithmeticOperationExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryArithmeticOperationExpression() {
		return binaryArithmeticOperationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinaryArithmeticOperationExpression_Operator() {
		return (EAttribute)binaryArithmeticOperationExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryArithmeticOperationExpression_LeftOperand() {
		return (EReference)binaryArithmeticOperationExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryArithmeticOperationExpression_RightOperand() {
		return (EReference)binaryArithmeticOperationExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBinaryArithmeticOperationExpression__FullName() {
		return binaryArithmeticOperationExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArithmeticComparisonExpression() {
		return arithmeticComparisonExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArithmeticComparisonExpression_Operator() {
		return (EAttribute)arithmeticComparisonExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithmeticComparisonExpression_LeftOperand() {
		return (EReference)arithmeticComparisonExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithmeticComparisonExpression_RightOperand() {
		return (EReference)arithmeticComparisonExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getArithmeticComparisonExpression__FullName() {
		return arithmeticComparisonExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringExpression() {
		return stringExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalExpression() {
		return logicalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListExpression() {
		return listExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListExpression_Head() {
		return (EReference)listExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListExpression_Tail() {
		return (EReference)listExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getListExpression__FullName() {
		return listExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyListExpression() {
		return emptyListExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexAccessExpression() {
		return indexAccessExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIndexAccessExpression_List() {
		return (EReference)indexAccessExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexSimpleAccessExpression() {
		return indexSimpleAccessExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexSimpleAccessExpression_Idx() {
		return (EAttribute)indexSimpleAccessExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexRangeAccessExpression() {
		return indexRangeAccessExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexRangeAccessExpression_Lower() {
		return (EAttribute)indexRangeAccessExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexRangeAccessExpression_Upper() {
		return (EAttribute)indexRangeAccessExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryLogicalExpression() {
		return binaryLogicalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinaryLogicalExpression_Operator() {
		return (EAttribute)binaryLogicalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryLogicalExpression_LeftOperand() {
		return (EReference)binaryLogicalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryLogicalExpression_RightOperand() {
		return (EReference)binaryLogicalExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryLogicalExpression() {
		return unaryLogicalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryLogicalExpression_Operator() {
		return (EAttribute)unaryLogicalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryLogicalExpression_Operand() {
		return (EReference)unaryLogicalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryGraphObjectLogicalExpression() {
		return unaryGraphObjectLogicalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryGraphObjectLogicalExpression_Operator() {
		return (EAttribute)unaryGraphObjectLogicalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryGraphObjectLogicalExpression_Operand() {
		return (EReference)unaryGraphObjectLogicalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComparableExpression() {
		return comparableExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtom() {
		return atomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteral() {
		return literalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanLiteral() {
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanLiteral_Value() {
		return (EAttribute)booleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBooleanLiteral__FullName() {
		return booleanLiteralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringLiteral() {
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_Value() {
		return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStringLiteral__FullName() {
		return stringLiteralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberLiteral() {
		return numberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleLiteral() {
		return doubleLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleLiteral_Value() {
		return (EAttribute)doubleLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDoubleLiteral__FullName() {
		return doubleLiteralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBigIntegerLiteral() {
		return bigIntegerLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBigIntegerLiteral_Value() {
		return (EAttribute)bigIntegerLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBigIntegerLiteral__FullName() {
		return bigIntegerLiteralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerLiteral() {
		return integerLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerLiteral_Value() {
		return (EAttribute)integerLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIntegerLiteral__FullName() {
		return integerLiteralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNullLiteral() {
		return nullLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterComparableExpression() {
		return parameterComparableExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterComparableExpression_Parameter() {
		return (EReference)parameterComparableExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyList() {
		return propertyListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyList_Entries() {
		return (EReference)propertyListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyListEntry() {
		return propertyListEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyListEntry_Key() {
		return (EAttribute)propertyListEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyListEntry_Value() {
		return (EReference)propertyListEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCase() {
		return caseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCase_When() {
		return (EReference)caseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCase_Then() {
		return (EReference)caseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMaxHopsType() {
		return maxHopsTypeEEnum;
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
	public EEnum getOrderDirection() {
		return orderDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBinaryArithmeticOperatorType() {
		return binaryArithmeticOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArithmeticComparisonOperatorType() {
		return arithmeticComparisonOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryArithmeticOperatorType() {
		return unaryArithmeticOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBinaryLogicalOperatorType() {
		return binaryLogicalOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryLogicalOperatorType() {
		return unaryLogicalOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryGraphObjectLogicalOperatorType() {
		return unaryGraphObjectLogicalOperatorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLabelSetStatus() {
		return labelSetStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPathSemantics() {
		return pathSemanticsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFunction() {
		return functionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBigint() {
		return bigintEDataType;
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
		relalgContainerEClass = createEClass(RELALG_CONTAINER);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__NAME);
		createEReference(relalgContainerEClass, RELALG_CONTAINER__ROOT_EXPRESSION);
		createEReference(relalgContainerEClass, RELALG_CONTAINER__ELEMENTS);
		createEReference(relalgContainerEClass, RELALG_CONTAINER__EXPRESSIONS);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__SIMPLIFIED_PLAN);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__INCREMENTAL_PLAN);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__EXTERNAL_SCHEMA_INFERRED);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__EXTRA_VARIABLES_INFERRED);
		createEAttribute(relalgContainerEClass, RELALG_CONTAINER__INTERNAL_SCHEMA_INFERRED);

		relalgModelElementEClass = createEClass(RELALG_MODEL_ELEMENT);
		createEOperation(relalgModelElementEClass, RELALG_MODEL_ELEMENT___FULL_NAME);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEReference(namedElementEClass, NAMED_ELEMENT__NAMED_ELEMENT_CONTAINER);
		createEOperation(namedElementEClass, NAMED_ELEMENT___FULL_NAME);

		labelEClass = createEClass(LABEL);

		vertexLabelEClass = createEClass(VERTEX_LABEL);

		edgeLabelEClass = createEClass(EDGE_LABEL);

		labelSetEClass = createEClass(LABEL_SET);
		createEAttribute(labelSetEClass, LABEL_SET__STATUS);

		vertexLabelSetEClass = createEClass(VERTEX_LABEL_SET);
		createEReference(vertexLabelSetEClass, VERTEX_LABEL_SET__VERTEX_LABELS);

		edgeLabelSetEClass = createEClass(EDGE_LABEL_SET);
		createEReference(edgeLabelSetEClass, EDGE_LABEL_SET__EDGE_LABELS);

		variableExpressionEClass = createEClass(VARIABLE_EXPRESSION);
		createEReference(variableExpressionEClass, VARIABLE_EXPRESSION__VARIABLE);
		createEOperation(variableExpressionEClass, VARIABLE_EXPRESSION___FULL_NAME);

		variableComparableExpressionEClass = createEClass(VARIABLE_COMPARABLE_EXPRESSION);

		variableStringExpressionEClass = createEClass(VARIABLE_STRING_EXPRESSION);

		variableArithmeticExpressionEClass = createEClass(VARIABLE_ARITHMETIC_EXPRESSION);

		variableListExpressionEClass = createEClass(VARIABLE_LIST_EXPRESSION);

		functionExpressionEClass = createEClass(FUNCTION_EXPRESSION);
		createEAttribute(functionExpressionEClass, FUNCTION_EXPRESSION__FUNCTOR);
		createEReference(functionExpressionEClass, FUNCTION_EXPRESSION__ARGUMENTS);
		createEOperation(functionExpressionEClass, FUNCTION_EXPRESSION___FULL_NAME);

		functionComparableExpressionEClass = createEClass(FUNCTION_COMPARABLE_EXPRESSION);

		functionArithmeticExpressionEClass = createEClass(FUNCTION_ARITHMETIC_EXPRESSION);

		functionLogicalExpressionEClass = createEClass(FUNCTION_LOGICAL_EXPRESSION);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__DONT_CARE);

		graphObjectVariableEClass = createEClass(GRAPH_OBJECT_VARIABLE);

		elementVariableEClass = createEClass(ELEMENT_VARIABLE);
		createEReference(elementVariableEClass, ELEMENT_VARIABLE__ATTRIBUTES);
		createEReference(elementVariableEClass, ELEMENT_VARIABLE__PROPERTIES);

		vertexVariableEClass = createEClass(VERTEX_VARIABLE);
		createEReference(vertexVariableEClass, VERTEX_VARIABLE__VERTEX_LABEL_SET);

		abstractEdgeVariableEClass = createEClass(ABSTRACT_EDGE_VARIABLE);
		createEReference(abstractEdgeVariableEClass, ABSTRACT_EDGE_VARIABLE__EDGE_LABEL_SET);

		edgeVariableEClass = createEClass(EDGE_VARIABLE);

		edgeListVariableEClass = createEClass(EDGE_LIST_VARIABLE);
		createEAttribute(edgeListVariableEClass, EDGE_LIST_VARIABLE__MIN_HOPS);
		createEReference(edgeListVariableEClass, EDGE_LIST_VARIABLE__MAX_HOPS);

		attributeVariableEClass = createEClass(ATTRIBUTE_VARIABLE);
		createEReference(attributeVariableEClass, ATTRIBUTE_VARIABLE__ELEMENT);
		createEReference(attributeVariableEClass, ATTRIBUTE_VARIABLE__EXP_VAR);
		createEOperation(attributeVariableEClass, ATTRIBUTE_VARIABLE___GET_BASE_VARIABLE);
		createEOperation(attributeVariableEClass, ATTRIBUTE_VARIABLE___FULL_NAME);

		listVariableEClass = createEClass(LIST_VARIABLE);

		pathVariableEClass = createEClass(PATH_VARIABLE);

		expressionVariableEClass = createEClass(EXPRESSION_VARIABLE);
		createEAttribute(expressionVariableEClass, EXPRESSION_VARIABLE__HAS_INFERRED_NAME);
		createEReference(expressionVariableEClass, EXPRESSION_VARIABLE__EXPRESSION);
		createEOperation(expressionVariableEClass, EXPRESSION_VARIABLE___FULL_NAME);

		operatorEClass = createEClass(OPERATOR);
		createEReference(operatorEClass, OPERATOR__EXTERNAL_SCHEMA);
		createEReference(operatorEClass, OPERATOR__EXTRA_VARIABLES);
		createEReference(operatorEClass, OPERATOR__INTERNAL_SCHEMA);
		createEReference(operatorEClass, OPERATOR__CARDINALITY);

		cardinalityEClass = createEClass(CARDINALITY);
		createEAttribute(cardinalityEClass, CARDINALITY__VALUE);

		nullaryOperatorEClass = createEClass(NULLARY_OPERATOR);

		getVerticesOperatorEClass = createEClass(GET_VERTICES_OPERATOR);
		createEReference(getVerticesOperatorEClass, GET_VERTICES_OPERATOR__VERTEX_VARIABLE);

		singularObjectSourceOperatorEClass = createEClass(SINGULAR_OBJECT_SOURCE_OPERATOR);

		dualObjectSourceOperatorEClass = createEClass(DUAL_OBJECT_SOURCE_OPERATOR);

		getEdgesOperatorEClass = createEClass(GET_EDGES_OPERATOR);

		unaryOperatorEClass = createEClass(UNARY_OPERATOR);
		createEReference(unaryOperatorEClass, UNARY_OPERATOR__INPUT);

		beamerOperatorEClass = createEClass(BEAMER_OPERATOR);
		createEReference(beamerOperatorEClass, BEAMER_OPERATOR__ELEMENTS);
		createEReference(beamerOperatorEClass, BEAMER_OPERATOR__INTERNAL_ELEMENTS);
		createEAttribute(beamerOperatorEClass, BEAMER_OPERATOR__TUPLE_INDICES);

		productionOperatorEClass = createEClass(PRODUCTION_OPERATOR);

		abstractConditionEClass = createEClass(ABSTRACT_CONDITION);
		createEAttribute(abstractConditionEClass, ABSTRACT_CONDITION__CONDITION_STRING);
		createEReference(abstractConditionEClass, ABSTRACT_CONDITION__CONDITION);

		selectionOperatorEClass = createEClass(SELECTION_OPERATOR);
		createEOperation(selectionOperatorEClass, SELECTION_OPERATOR___TO_STRING);

		projectionOperatorEClass = createEClass(PROJECTION_OPERATOR);

		groupingOperatorEClass = createEClass(GROUPING_OPERATOR);
		createEReference(groupingOperatorEClass, GROUPING_OPERATOR__AGGREGATION_CRITERIA);
		createEAttribute(groupingOperatorEClass, GROUPING_OPERATOR__ORDER);

		cudOperatorEClass = createEClass(CUD_OPERATOR);
		createEReference(cudOperatorEClass, CUD_OPERATOR__ELEMENTS);

		createOperatorEClass = createEClass(CREATE_OPERATOR);

		deleteOperatorEClass = createEClass(DELETE_OPERATOR);
		createEAttribute(deleteOperatorEClass, DELETE_OPERATOR__DETACH);

		navigationDescriptorEClass = createEClass(NAVIGATION_DESCRIPTOR);
		createEAttribute(navigationDescriptorEClass, NAVIGATION_DESCRIPTOR__DIRECTION);
		createEReference(navigationDescriptorEClass, NAVIGATION_DESCRIPTOR__SOURCE_VERTEX_VARIABLE);
		createEReference(navigationDescriptorEClass, NAVIGATION_DESCRIPTOR__EDGE_VARIABLE);
		createEReference(navigationDescriptorEClass, NAVIGATION_DESCRIPTOR__TARGET_VERTEX_VARIABLE);

		maxHopsEClass = createEClass(MAX_HOPS);
		createEAttribute(maxHopsEClass, MAX_HOPS__MAX_HOPS_TYPE);
		createEAttribute(maxHopsEClass, MAX_HOPS__HOPS);

		expandOperatorEClass = createEClass(EXPAND_OPERATOR);

		pathOperatorEClass = createEClass(PATH_OPERATOR);
		createEAttribute(pathOperatorEClass, PATH_OPERATOR__SEMANTICS);

		duplicateEliminationOperatorEClass = createEClass(DUPLICATE_ELIMINATION_OPERATOR);

		allDifferentOperatorEClass = createEClass(ALL_DIFFERENT_OPERATOR);
		createEReference(allDifferentOperatorEClass, ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES);

		sortOperatorEClass = createEClass(SORT_OPERATOR);
		createEReference(sortOperatorEClass, SORT_OPERATOR__ENTRIES);

		sortEntryEClass = createEClass(SORT_ENTRY);
		createEReference(sortEntryEClass, SORT_ENTRY__EXPRESSION);
		createEAttribute(sortEntryEClass, SORT_ENTRY__DIRECTION);

		topOperatorEClass = createEClass(TOP_OPERATOR);
		createEReference(topOperatorEClass, TOP_OPERATOR__SKIP);
		createEReference(topOperatorEClass, TOP_OPERATOR__LIMIT);

		sortAndTopOperatorEClass = createEClass(SORT_AND_TOP_OPERATOR);

		unwindOperatorEClass = createEClass(UNWIND_OPERATOR);
		createEReference(unwindOperatorEClass, UNWIND_OPERATOR__ELEMENT);

		binaryOperatorEClass = createEClass(BINARY_OPERATOR);
		createEReference(binaryOperatorEClass, BINARY_OPERATOR__LEFT_INPUT);
		createEReference(binaryOperatorEClass, BINARY_OPERATOR__RIGHT_INPUT);

		commutativeBinaryOperatorEClass = createEClass(COMMUTATIVE_BINARY_OPERATOR);

		associativeBinaryOperatorEClass = createEClass(ASSOCIATIVE_BINARY_OPERATOR);

		abstractJoinOperatorEClass = createEClass(ABSTRACT_JOIN_OPERATOR);
		createEReference(abstractJoinOperatorEClass, ABSTRACT_JOIN_OPERATOR__COMMON_VARIABLES);
		createEAttribute(abstractJoinOperatorEClass, ABSTRACT_JOIN_OPERATOR__LEFT_MASK);
		createEAttribute(abstractJoinOperatorEClass, ABSTRACT_JOIN_OPERATOR__RIGHT_MASK);

		equiJoinLikeOperatorEClass = createEClass(EQUI_JOIN_LIKE_OPERATOR);

		leftOuterJoinOperatorEClass = createEClass(LEFT_OUTER_JOIN_OPERATOR);

		thetaLeftOuterJoinOperatorEClass = createEClass(THETA_LEFT_OUTER_JOIN_OPERATOR);

		joinOperatorEClass = createEClass(JOIN_OPERATOR);

		transitiveClosureJoinOperatorEClass = createEClass(TRANSITIVE_CLOSURE_JOIN_OPERATOR);
		createEReference(transitiveClosureJoinOperatorEClass, TRANSITIVE_CLOSURE_JOIN_OPERATOR__EDGE_LIST_VARIABLE);

		antiJoinOperatorEClass = createEClass(ANTI_JOIN_OPERATOR);

		unionOperatorEClass = createEClass(UNION_OPERATOR);
		createEAttribute(unionOperatorEClass, UNION_OPERATOR__BAG);

		expressionEClass = createEClass(EXPRESSION);
		createEReference(expressionEClass, EXPRESSION__EXPRESSION_CONTAINER);
		createEAttribute(expressionEClass, EXPRESSION__TEXT);

		caseExpressionEClass = createEClass(CASE_EXPRESSION);
		createEReference(caseExpressionEClass, CASE_EXPRESSION__CASES);
		createEReference(caseExpressionEClass, CASE_EXPRESSION__FALLBACK);
		createEOperation(caseExpressionEClass, CASE_EXPRESSION___FULL_NAME);

		genericCaseExpressionEClass = createEClass(GENERIC_CASE_EXPRESSION);

		simpleCaseExpressionEClass = createEClass(SIMPLE_CASE_EXPRESSION);
		createEReference(simpleCaseExpressionEClass, SIMPLE_CASE_EXPRESSION__TEST);

		arithmeticExpressionEClass = createEClass(ARITHMETIC_EXPRESSION);

		unaryExpressionEClass = createEClass(UNARY_EXPRESSION);

		binaryExpressionEClass = createEClass(BINARY_EXPRESSION);

		unaryArithmeticOperationExpressionEClass = createEClass(UNARY_ARITHMETIC_OPERATION_EXPRESSION);
		createEAttribute(unaryArithmeticOperationExpressionEClass, UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR);
		createEReference(unaryArithmeticOperationExpressionEClass, UNARY_ARITHMETIC_OPERATION_EXPRESSION__OPERAND);

		binaryArithmeticOperationExpressionEClass = createEClass(BINARY_ARITHMETIC_OPERATION_EXPRESSION);
		createEAttribute(binaryArithmeticOperationExpressionEClass, BINARY_ARITHMETIC_OPERATION_EXPRESSION__OPERATOR);
		createEReference(binaryArithmeticOperationExpressionEClass, BINARY_ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND);
		createEReference(binaryArithmeticOperationExpressionEClass, BINARY_ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND);
		createEOperation(binaryArithmeticOperationExpressionEClass, BINARY_ARITHMETIC_OPERATION_EXPRESSION___FULL_NAME);

		arithmeticComparisonExpressionEClass = createEClass(ARITHMETIC_COMPARISON_EXPRESSION);
		createEAttribute(arithmeticComparisonExpressionEClass, ARITHMETIC_COMPARISON_EXPRESSION__OPERATOR);
		createEReference(arithmeticComparisonExpressionEClass, ARITHMETIC_COMPARISON_EXPRESSION__LEFT_OPERAND);
		createEReference(arithmeticComparisonExpressionEClass, ARITHMETIC_COMPARISON_EXPRESSION__RIGHT_OPERAND);
		createEOperation(arithmeticComparisonExpressionEClass, ARITHMETIC_COMPARISON_EXPRESSION___FULL_NAME);

		stringExpressionEClass = createEClass(STRING_EXPRESSION);

		logicalExpressionEClass = createEClass(LOGICAL_EXPRESSION);

		listExpressionEClass = createEClass(LIST_EXPRESSION);
		createEReference(listExpressionEClass, LIST_EXPRESSION__HEAD);
		createEReference(listExpressionEClass, LIST_EXPRESSION__TAIL);
		createEOperation(listExpressionEClass, LIST_EXPRESSION___FULL_NAME);

		emptyListExpressionEClass = createEClass(EMPTY_LIST_EXPRESSION);

		indexAccessExpressionEClass = createEClass(INDEX_ACCESS_EXPRESSION);
		createEReference(indexAccessExpressionEClass, INDEX_ACCESS_EXPRESSION__LIST);

		indexSimpleAccessExpressionEClass = createEClass(INDEX_SIMPLE_ACCESS_EXPRESSION);
		createEAttribute(indexSimpleAccessExpressionEClass, INDEX_SIMPLE_ACCESS_EXPRESSION__IDX);

		indexRangeAccessExpressionEClass = createEClass(INDEX_RANGE_ACCESS_EXPRESSION);
		createEAttribute(indexRangeAccessExpressionEClass, INDEX_RANGE_ACCESS_EXPRESSION__LOWER);
		createEAttribute(indexRangeAccessExpressionEClass, INDEX_RANGE_ACCESS_EXPRESSION__UPPER);

		binaryLogicalExpressionEClass = createEClass(BINARY_LOGICAL_EXPRESSION);
		createEAttribute(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__OPERATOR);
		createEReference(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND);
		createEReference(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND);

		unaryLogicalExpressionEClass = createEClass(UNARY_LOGICAL_EXPRESSION);
		createEAttribute(unaryLogicalExpressionEClass, UNARY_LOGICAL_EXPRESSION__OPERATOR);
		createEReference(unaryLogicalExpressionEClass, UNARY_LOGICAL_EXPRESSION__OPERAND);

		unaryGraphObjectLogicalExpressionEClass = createEClass(UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION);
		createEAttribute(unaryGraphObjectLogicalExpressionEClass, UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERATOR);
		createEReference(unaryGraphObjectLogicalExpressionEClass, UNARY_GRAPH_OBJECT_LOGICAL_EXPRESSION__OPERAND);

		comparableExpressionEClass = createEClass(COMPARABLE_EXPRESSION);

		atomEClass = createEClass(ATOM);

		literalEClass = createEClass(LITERAL);

		booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
		createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__VALUE);
		createEOperation(booleanLiteralEClass, BOOLEAN_LITERAL___FULL_NAME);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__VALUE);
		createEOperation(stringLiteralEClass, STRING_LITERAL___FULL_NAME);

		numberLiteralEClass = createEClass(NUMBER_LITERAL);

		doubleLiteralEClass = createEClass(DOUBLE_LITERAL);
		createEAttribute(doubleLiteralEClass, DOUBLE_LITERAL__VALUE);
		createEOperation(doubleLiteralEClass, DOUBLE_LITERAL___FULL_NAME);

		bigIntegerLiteralEClass = createEClass(BIG_INTEGER_LITERAL);
		createEAttribute(bigIntegerLiteralEClass, BIG_INTEGER_LITERAL__VALUE);
		createEOperation(bigIntegerLiteralEClass, BIG_INTEGER_LITERAL___FULL_NAME);

		integerLiteralEClass = createEClass(INTEGER_LITERAL);
		createEAttribute(integerLiteralEClass, INTEGER_LITERAL__VALUE);
		createEOperation(integerLiteralEClass, INTEGER_LITERAL___FULL_NAME);

		nullLiteralEClass = createEClass(NULL_LITERAL);

		parameterEClass = createEClass(PARAMETER);

		parameterComparableExpressionEClass = createEClass(PARAMETER_COMPARABLE_EXPRESSION);
		createEReference(parameterComparableExpressionEClass, PARAMETER_COMPARABLE_EXPRESSION__PARAMETER);

		propertyListEClass = createEClass(PROPERTY_LIST);
		createEReference(propertyListEClass, PROPERTY_LIST__ENTRIES);

		propertyListEntryEClass = createEClass(PROPERTY_LIST_ENTRY);
		createEAttribute(propertyListEntryEClass, PROPERTY_LIST_ENTRY__KEY);
		createEReference(propertyListEntryEClass, PROPERTY_LIST_ENTRY__VALUE);

		caseEClass = createEClass(CASE);
		createEReference(caseEClass, CASE__WHEN);
		createEReference(caseEClass, CASE__THEN);

		// Create enums
		maxHopsTypeEEnum = createEEnum(MAX_HOPS_TYPE);
		directionEEnum = createEEnum(DIRECTION);
		orderDirectionEEnum = createEEnum(ORDER_DIRECTION);
		binaryArithmeticOperatorTypeEEnum = createEEnum(BINARY_ARITHMETIC_OPERATOR_TYPE);
		arithmeticComparisonOperatorTypeEEnum = createEEnum(ARITHMETIC_COMPARISON_OPERATOR_TYPE);
		unaryArithmeticOperatorTypeEEnum = createEEnum(UNARY_ARITHMETIC_OPERATOR_TYPE);
		binaryLogicalOperatorTypeEEnum = createEEnum(BINARY_LOGICAL_OPERATOR_TYPE);
		unaryLogicalOperatorTypeEEnum = createEEnum(UNARY_LOGICAL_OPERATOR_TYPE);
		unaryGraphObjectLogicalOperatorTypeEEnum = createEEnum(UNARY_GRAPH_OBJECT_LOGICAL_OPERATOR_TYPE);
		labelSetStatusEEnum = createEEnum(LABEL_SET_STATUS);
		pathSemanticsEEnum = createEEnum(PATH_SEMANTICS);

		// Create data types
		functionEDataType = createEDataType(FUNCTION);
		bigintEDataType = createEDataType(BIGINT);
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

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		namedElementEClass.getESuperTypes().add(this.getRelalgModelElement());
		labelEClass.getESuperTypes().add(this.getNamedElement());
		vertexLabelEClass.getESuperTypes().add(this.getLabel());
		edgeLabelEClass.getESuperTypes().add(this.getLabel());
		vertexLabelSetEClass.getESuperTypes().add(this.getLabelSet());
		edgeLabelSetEClass.getESuperTypes().add(this.getLabelSet());
		variableExpressionEClass.getESuperTypes().add(this.getExpression());
		variableComparableExpressionEClass.getESuperTypes().add(this.getVariableExpression());
		variableComparableExpressionEClass.getESuperTypes().add(this.getComparableExpression());
		variableStringExpressionEClass.getESuperTypes().add(this.getVariableExpression());
		variableStringExpressionEClass.getESuperTypes().add(this.getStringExpression());
		variableArithmeticExpressionEClass.getESuperTypes().add(this.getVariableExpression());
		variableArithmeticExpressionEClass.getESuperTypes().add(this.getArithmeticExpression());
		variableListExpressionEClass.getESuperTypes().add(this.getVariableExpression());
		variableListExpressionEClass.getESuperTypes().add(this.getListExpression());
		functionExpressionEClass.getESuperTypes().add(this.getExpression());
		functionComparableExpressionEClass.getESuperTypes().add(this.getFunctionExpression());
		functionComparableExpressionEClass.getESuperTypes().add(this.getComparableExpression());
		functionArithmeticExpressionEClass.getESuperTypes().add(this.getFunctionExpression());
		functionArithmeticExpressionEClass.getESuperTypes().add(this.getArithmeticExpression());
		functionLogicalExpressionEClass.getESuperTypes().add(this.getFunctionExpression());
		functionLogicalExpressionEClass.getESuperTypes().add(this.getLogicalExpression());
		variableEClass.getESuperTypes().add(this.getNamedElement());
		graphObjectVariableEClass.getESuperTypes().add(this.getVariable());
		elementVariableEClass.getESuperTypes().add(this.getGraphObjectVariable());
		vertexVariableEClass.getESuperTypes().add(this.getElementVariable());
		abstractEdgeVariableEClass.getESuperTypes().add(this.getElementVariable());
		edgeVariableEClass.getESuperTypes().add(this.getAbstractEdgeVariable());
		edgeListVariableEClass.getESuperTypes().add(this.getAbstractEdgeVariable());
		attributeVariableEClass.getESuperTypes().add(this.getGraphObjectVariable());
		listVariableEClass.getESuperTypes().add(this.getVariable());
		pathVariableEClass.getESuperTypes().add(this.getVariable());
		expressionVariableEClass.getESuperTypes().add(this.getVariable());
		nullaryOperatorEClass.getESuperTypes().add(this.getOperator());
		getVerticesOperatorEClass.getESuperTypes().add(this.getNullaryOperator());
		singularObjectSourceOperatorEClass.getESuperTypes().add(this.getNullaryOperator());
		dualObjectSourceOperatorEClass.getESuperTypes().add(this.getNullaryOperator());
		getEdgesOperatorEClass.getESuperTypes().add(this.getNullaryOperator());
		getEdgesOperatorEClass.getESuperTypes().add(this.getNavigationDescriptor());
		unaryOperatorEClass.getESuperTypes().add(this.getOperator());
		beamerOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		productionOperatorEClass.getESuperTypes().add(this.getBeamerOperator());
		selectionOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		selectionOperatorEClass.getESuperTypes().add(this.getAbstractCondition());
		projectionOperatorEClass.getESuperTypes().add(this.getBeamerOperator());
		groupingOperatorEClass.getESuperTypes().add(this.getProjectionOperator());
		cudOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		createOperatorEClass.getESuperTypes().add(this.getCUDOperator());
		deleteOperatorEClass.getESuperTypes().add(this.getCUDOperator());
		navigationDescriptorEClass.getESuperTypes().add(this.getExpression());
		expandOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		expandOperatorEClass.getESuperTypes().add(this.getNavigationDescriptor());
		pathOperatorEClass.getESuperTypes().add(this.getExpandOperator());
		duplicateEliminationOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		allDifferentOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		sortOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		topOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		sortAndTopOperatorEClass.getESuperTypes().add(this.getSortOperator());
		sortAndTopOperatorEClass.getESuperTypes().add(this.getTopOperator());
		unwindOperatorEClass.getESuperTypes().add(this.getUnaryOperator());
		binaryOperatorEClass.getESuperTypes().add(this.getOperator());
		commutativeBinaryOperatorEClass.getESuperTypes().add(this.getBinaryOperator());
		associativeBinaryOperatorEClass.getESuperTypes().add(this.getBinaryOperator());
		abstractJoinOperatorEClass.getESuperTypes().add(this.getBinaryOperator());
		equiJoinLikeOperatorEClass.getESuperTypes().add(this.getAbstractJoinOperator());
		leftOuterJoinOperatorEClass.getESuperTypes().add(this.getEquiJoinLikeOperator());
		thetaLeftOuterJoinOperatorEClass.getESuperTypes().add(this.getLeftOuterJoinOperator());
		thetaLeftOuterJoinOperatorEClass.getESuperTypes().add(this.getAbstractCondition());
		joinOperatorEClass.getESuperTypes().add(this.getEquiJoinLikeOperator());
		joinOperatorEClass.getESuperTypes().add(this.getCommutativeBinaryOperator());
		joinOperatorEClass.getESuperTypes().add(this.getAssociativeBinaryOperator());
		transitiveClosureJoinOperatorEClass.getESuperTypes().add(this.getEquiJoinLikeOperator());
		antiJoinOperatorEClass.getESuperTypes().add(this.getAbstractJoinOperator());
		unionOperatorEClass.getESuperTypes().add(this.getBinaryOperator());
		unionOperatorEClass.getESuperTypes().add(this.getCommutativeBinaryOperator());
		unionOperatorEClass.getESuperTypes().add(this.getAssociativeBinaryOperator());
		expressionEClass.getESuperTypes().add(this.getRelalgModelElement());
		caseExpressionEClass.getESuperTypes().add(this.getExpression());
		genericCaseExpressionEClass.getESuperTypes().add(this.getCaseExpression());
		simpleCaseExpressionEClass.getESuperTypes().add(this.getCaseExpression());
		arithmeticExpressionEClass.getESuperTypes().add(this.getComparableExpression());
		unaryExpressionEClass.getESuperTypes().add(this.getExpression());
		binaryExpressionEClass.getESuperTypes().add(this.getExpression());
		unaryArithmeticOperationExpressionEClass.getESuperTypes().add(this.getUnaryExpression());
		unaryArithmeticOperationExpressionEClass.getESuperTypes().add(this.getArithmeticExpression());
		binaryArithmeticOperationExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		binaryArithmeticOperationExpressionEClass.getESuperTypes().add(this.getArithmeticExpression());
		arithmeticComparisonExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		arithmeticComparisonExpressionEClass.getESuperTypes().add(this.getLogicalExpression());
		stringExpressionEClass.getESuperTypes().add(this.getComparableExpression());
		logicalExpressionEClass.getESuperTypes().add(this.getExpression());
		listExpressionEClass.getESuperTypes().add(this.getExpression());
		emptyListExpressionEClass.getESuperTypes().add(this.getListExpression());
		indexAccessExpressionEClass.getESuperTypes().add(this.getExpression());
		indexSimpleAccessExpressionEClass.getESuperTypes().add(this.getIndexAccessExpression());
		indexRangeAccessExpressionEClass.getESuperTypes().add(this.getIndexAccessExpression());
		binaryLogicalExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		binaryLogicalExpressionEClass.getESuperTypes().add(this.getLogicalExpression());
		unaryLogicalExpressionEClass.getESuperTypes().add(this.getUnaryExpression());
		unaryLogicalExpressionEClass.getESuperTypes().add(this.getLogicalExpression());
		unaryGraphObjectLogicalExpressionEClass.getESuperTypes().add(this.getUnaryExpression());
		unaryGraphObjectLogicalExpressionEClass.getESuperTypes().add(this.getLogicalExpression());
		comparableExpressionEClass.getESuperTypes().add(this.getExpression());
		atomEClass.getESuperTypes().add(this.getExpression());
		literalEClass.getESuperTypes().add(this.getAtom());
		booleanLiteralEClass.getESuperTypes().add(this.getLiteral());
		booleanLiteralEClass.getESuperTypes().add(this.getLogicalExpression());
		stringLiteralEClass.getESuperTypes().add(this.getLiteral());
		stringLiteralEClass.getESuperTypes().add(this.getStringExpression());
		numberLiteralEClass.getESuperTypes().add(this.getLiteral());
		numberLiteralEClass.getESuperTypes().add(this.getArithmeticExpression());
		doubleLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		bigIntegerLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		integerLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		nullLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		nullLiteralEClass.getESuperTypes().add(this.getStringLiteral());
		parameterEClass.getESuperTypes().add(this.getNamedElement());
		parameterEClass.getESuperTypes().add(this.getAtom());
		parameterComparableExpressionEClass.getESuperTypes().add(this.getComparableExpression());
		propertyListEClass.getESuperTypes().add(this.getExpression());

		// Initialize classes, features, and operations; add parameters
		initEClass(relalgContainerEClass, RelalgContainer.class, "RelalgContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelalgContainer_Name(), theEcorePackage.getEString(), "name", null, 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelalgContainer_RootExpression(), this.getOperator(), null, "rootExpression", null, 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelalgContainer_Elements(), this.getNamedElement(), this.getNamedElement_NamedElementContainer(), "elements", null, 0, -1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelalgContainer_Expressions(), this.getExpression(), this.getExpression_ExpressionContainer(), "expressions", null, 0, -1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelalgContainer_SimplifiedPlan(), theEcorePackage.getEBoolean(), "simplifiedPlan", "false", 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelalgContainer_IncrementalPlan(), theEcorePackage.getEBoolean(), "incrementalPlan", "false", 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelalgContainer_ExternalSchemaInferred(), theEcorePackage.getEBoolean(), "externalSchemaInferred", "false", 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelalgContainer_ExtraVariablesInferred(), theEcorePackage.getEBoolean(), "extraVariablesInferred", "false", 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelalgContainer_InternalSchemaInferred(), theEcorePackage.getEBoolean(), "internalSchemaInferred", "false", 0, 1, RelalgContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relalgModelElementEClass, RelalgModelElement.class, "RelalgModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getRelalgModelElement__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNamedElement_NamedElementContainer(), this.getRelalgContainer(), this.getRelalgContainer_Elements(), "namedElementContainer", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getNamedElement__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(vertexLabelEClass, VertexLabel.class, "VertexLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(edgeLabelEClass, EdgeLabel.class, "EdgeLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelSetEClass, LabelSet.class, "LabelSet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelSet_Status(), this.getLabelSetStatus(), "status", "EMPTY", 0, 1, LabelSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexLabelSetEClass, VertexLabelSet.class, "VertexLabelSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVertexLabelSet_VertexLabels(), this.getVertexLabel(), null, "vertexLabels", null, 0, -1, VertexLabelSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeLabelSetEClass, EdgeLabelSet.class, "EdgeLabelSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdgeLabelSet_EdgeLabels(), this.getEdgeLabel(), null, "edgeLabels", null, 0, -1, EdgeLabelSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableExpressionEClass, VariableExpression.class, "VariableExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableExpression_Variable(), this.getVariable(), null, "variable", null, 0, 1, VariableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getVariableExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(variableComparableExpressionEClass, VariableComparableExpression.class, "VariableComparableExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableStringExpressionEClass, VariableStringExpression.class, "VariableStringExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableArithmeticExpressionEClass, VariableArithmeticExpression.class, "VariableArithmeticExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableListExpressionEClass, VariableListExpression.class, "VariableListExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionExpressionEClass, FunctionExpression.class, "FunctionExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionExpression_Functor(), this.getFunction(), "functor", null, 0, 1, FunctionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionExpression_Arguments(), this.getExpression(), null, "arguments", null, 0, -1, FunctionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getFunctionExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(functionComparableExpressionEClass, FunctionComparableExpression.class, "FunctionComparableExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionArithmeticExpressionEClass, FunctionArithmeticExpression.class, "FunctionArithmeticExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionLogicalExpressionEClass, FunctionLogicalExpression.class, "FunctionLogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableEClass, Variable.class, "Variable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariable_DontCare(), theEcorePackage.getEBoolean(), "dontCare", "false", 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphObjectVariableEClass, GraphObjectVariable.class, "GraphObjectVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementVariableEClass, ElementVariable.class, "ElementVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementVariable_Attributes(), this.getAttributeVariable(), this.getAttributeVariable_Element(), "attributes", null, 0, -1, ElementVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementVariable_Properties(), this.getPropertyList(), null, "properties", null, 0, 1, ElementVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexVariableEClass, VertexVariable.class, "VertexVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVertexVariable_VertexLabelSet(), this.getVertexLabelSet(), null, "vertexLabelSet", null, 0, 1, VertexVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractEdgeVariableEClass, AbstractEdgeVariable.class, "AbstractEdgeVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractEdgeVariable_EdgeLabelSet(), this.getEdgeLabelSet(), null, "edgeLabelSet", null, 0, 1, AbstractEdgeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeVariableEClass, EdgeVariable.class, "EdgeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(edgeListVariableEClass, EdgeListVariable.class, "EdgeListVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEdgeListVariable_MinHops(), theEcorePackage.getEInt(), "minHops", null, 0, 1, EdgeListVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeListVariable_MaxHops(), this.getMaxHops(), null, "maxHops", null, 0, 1, EdgeListVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeVariableEClass, AttributeVariable.class, "AttributeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeVariable_Element(), this.getElementVariable(), this.getElementVariable_Attributes(), "element", null, 0, 1, AttributeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttributeVariable_ExpVar(), this.getExpressionVariable(), null, "expVar", null, 0, 1, AttributeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getAttributeVariable__GetBaseVariable(), this.getVariable(), "getBaseVariable", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getAttributeVariable__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(listVariableEClass, ListVariable.class, "ListVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pathVariableEClass, PathVariable.class, "PathVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expressionVariableEClass, ExpressionVariable.class, "ExpressionVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExpressionVariable_HasInferredName(), theEcorePackage.getEBoolean(), "hasInferredName", null, 0, 1, ExpressionVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExpressionVariable_Expression(), this.getExpression(), null, "expression", null, 0, 1, ExpressionVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getExpressionVariable__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(operatorEClass, Operator.class, "Operator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperator_ExternalSchema(), this.getVariable(), null, "externalSchema", null, 0, -1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperator_ExtraVariables(), this.getVariable(), null, "extraVariables", null, 0, -1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperator_InternalSchema(), this.getVariable(), null, "internalSchema", null, 0, -1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperator_Cardinality(), this.getCardinality(), null, "cardinality", null, 0, 1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cardinalityEClass, Cardinality.class, "Cardinality", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCardinality_Value(), theEcorePackage.getEDouble(), "value", null, 0, 1, Cardinality.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nullaryOperatorEClass, NullaryOperator.class, "NullaryOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(getVerticesOperatorEClass, GetVerticesOperator.class, "GetVerticesOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGetVerticesOperator_VertexVariable(), this.getVertexVariable(), null, "vertexVariable", null, 0, 1, GetVerticesOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singularObjectSourceOperatorEClass, SingularObjectSourceOperator.class, "SingularObjectSourceOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dualObjectSourceOperatorEClass, DualObjectSourceOperator.class, "DualObjectSourceOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(getEdgesOperatorEClass, GetEdgesOperator.class, "GetEdgesOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unaryOperatorEClass, UnaryOperator.class, "UnaryOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryOperator_Input(), this.getOperator(), null, "input", null, 0, 1, UnaryOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beamerOperatorEClass, BeamerOperator.class, "BeamerOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBeamerOperator_Elements(), this.getExpressionVariable(), null, "elements", null, 0, -1, BeamerOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBeamerOperator_InternalElements(), this.getExpressionVariable(), null, "internalElements", null, 0, -1, BeamerOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBeamerOperator_TupleIndices(), theEcorePackage.getEInt(), "tupleIndices", null, 0, -1, BeamerOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productionOperatorEClass, ProductionOperator.class, "ProductionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractConditionEClass, AbstractCondition.class, "AbstractCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractCondition_ConditionString(), theEcorePackage.getEString(), "conditionString", null, 0, 1, AbstractCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractCondition_Condition(), this.getLogicalExpression(), null, "condition", null, 0, 1, AbstractCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(selectionOperatorEClass, SelectionOperator.class, "SelectionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getSelectionOperator__ToString(), theEcorePackage.getEString(), "toString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(projectionOperatorEClass, ProjectionOperator.class, "ProjectionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(groupingOperatorEClass, GroupingOperator.class, "GroupingOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGroupingOperator_AggregationCriteria(), this.getExpression(), null, "aggregationCriteria", null, 0, -1, GroupingOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupingOperator_Order(), theEcorePackage.getEInt(), "order", null, 0, -1, GroupingOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cudOperatorEClass, CUDOperator.class, "CUDOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCUDOperator_Elements(), this.getExpressionVariable(), null, "elements", null, 0, -1, CUDOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createOperatorEClass, CreateOperator.class, "CreateOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deleteOperatorEClass, DeleteOperator.class, "DeleteOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeleteOperator_Detach(), theEcorePackage.getEBoolean(), "detach", null, 0, 1, DeleteOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationDescriptorEClass, NavigationDescriptor.class, "NavigationDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNavigationDescriptor_Direction(), this.getDirection(), "direction", null, 0, 1, NavigationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationDescriptor_SourceVertexVariable(), this.getVertexVariable(), null, "sourceVertexVariable", null, 0, 1, NavigationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationDescriptor_EdgeVariable(), this.getAbstractEdgeVariable(), null, "edgeVariable", null, 0, 1, NavigationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationDescriptor_TargetVertexVariable(), this.getVertexVariable(), null, "targetVertexVariable", null, 0, 1, NavigationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(maxHopsEClass, MaxHops.class, "MaxHops", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMaxHops_MaxHopsType(), this.getMaxHopsType(), "maxHopsType", null, 0, 1, MaxHops.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaxHops_Hops(), theEcorePackage.getEInt(), "hops", null, 0, 1, MaxHops.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expandOperatorEClass, ExpandOperator.class, "ExpandOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pathOperatorEClass, PathOperator.class, "PathOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPathOperator_Semantics(), this.getPathSemantics(), "semantics", null, 0, 1, PathOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(duplicateEliminationOperatorEClass, DuplicateEliminationOperator.class, "DuplicateEliminationOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(allDifferentOperatorEClass, AllDifferentOperator.class, "AllDifferentOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAllDifferentOperator_EdgeVariables(), this.getAbstractEdgeVariable(), null, "edgeVariables", null, 0, -1, AllDifferentOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortOperatorEClass, SortOperator.class, "SortOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSortOperator_Entries(), this.getSortEntry(), null, "entries", null, 0, -1, SortOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortEntryEClass, SortEntry.class, "SortEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSortEntry_Expression(), this.getExpression(), null, "expression", null, 0, 1, SortEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSortEntry_Direction(), this.getOrderDirection(), "direction", null, 0, 1, SortEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(topOperatorEClass, TopOperator.class, "TopOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTopOperator_Skip(), this.getExpression(), null, "skip", null, 0, 1, TopOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopOperator_Limit(), this.getExpression(), null, "limit", null, 0, 1, TopOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortAndTopOperatorEClass, SortAndTopOperator.class, "SortAndTopOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unwindOperatorEClass, UnwindOperator.class, "UnwindOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnwindOperator_Element(), this.getExpressionVariable(), null, "element", null, 0, 1, UnwindOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryOperatorEClass, BinaryOperator.class, "BinaryOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryOperator_LeftInput(), this.getOperator(), null, "leftInput", null, 0, 1, BinaryOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryOperator_RightInput(), this.getOperator(), null, "rightInput", null, 0, 1, BinaryOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commutativeBinaryOperatorEClass, CommutativeBinaryOperator.class, "CommutativeBinaryOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(associativeBinaryOperatorEClass, AssociativeBinaryOperator.class, "AssociativeBinaryOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractJoinOperatorEClass, AbstractJoinOperator.class, "AbstractJoinOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractJoinOperator_CommonVariables(), this.getVariable(), null, "commonVariables", null, 0, -1, AbstractJoinOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractJoinOperator_LeftMask(), theEcorePackage.getEInt(), "leftMask", null, 0, -1, AbstractJoinOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractJoinOperator_RightMask(), theEcorePackage.getEInt(), "rightMask", null, 0, -1, AbstractJoinOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(equiJoinLikeOperatorEClass, EquiJoinLikeOperator.class, "EquiJoinLikeOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(leftOuterJoinOperatorEClass, LeftOuterJoinOperator.class, "LeftOuterJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(thetaLeftOuterJoinOperatorEClass, ThetaLeftOuterJoinOperator.class, "ThetaLeftOuterJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(joinOperatorEClass, JoinOperator.class, "JoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transitiveClosureJoinOperatorEClass, TransitiveClosureJoinOperator.class, "TransitiveClosureJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitiveClosureJoinOperator_EdgeListVariable(), this.getEdgeListVariable(), null, "edgeListVariable", null, 0, 1, TransitiveClosureJoinOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(antiJoinOperatorEClass, AntiJoinOperator.class, "AntiJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unionOperatorEClass, UnionOperator.class, "UnionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnionOperator_Bag(), theEcorePackage.getEBoolean(), "bag", null, 0, 1, UnionOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExpression_ExpressionContainer(), this.getRelalgContainer(), this.getRelalgContainer_Expressions(), "expressionContainer", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExpression_Text(), theEcorePackage.getEString(), "text", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(caseExpressionEClass, CaseExpression.class, "CaseExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCaseExpression_Cases(), this.getCase(), null, "cases", null, 0, -1, CaseExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCaseExpression_Fallback(), this.getExpression(), null, "fallback", null, 0, 1, CaseExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getCaseExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(genericCaseExpressionEClass, GenericCaseExpression.class, "GenericCaseExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(simpleCaseExpressionEClass, SimpleCaseExpression.class, "SimpleCaseExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimpleCaseExpression_Test(), this.getExpression(), null, "test", null, 0, 1, SimpleCaseExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arithmeticExpressionEClass, ArithmeticExpression.class, "ArithmeticExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(binaryExpressionEClass, BinaryExpression.class, "BinaryExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unaryArithmeticOperationExpressionEClass, UnaryArithmeticOperationExpression.class, "UnaryArithmeticOperationExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnaryArithmeticOperationExpression_Operator(), this.getUnaryArithmeticOperatorType(), "operator", null, 0, 1, UnaryArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnaryArithmeticOperationExpression_Operand(), this.getArithmeticExpression(), null, "operand", null, 0, 1, UnaryArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryArithmeticOperationExpressionEClass, BinaryArithmeticOperationExpression.class, "BinaryArithmeticOperationExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBinaryArithmeticOperationExpression_Operator(), this.getBinaryArithmeticOperatorType(), "operator", null, 0, 1, BinaryArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryArithmeticOperationExpression_LeftOperand(), this.getArithmeticExpression(), null, "leftOperand", null, 0, 1, BinaryArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryArithmeticOperationExpression_RightOperand(), this.getArithmeticExpression(), null, "rightOperand", null, 0, 1, BinaryArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBinaryArithmeticOperationExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(arithmeticComparisonExpressionEClass, ArithmeticComparisonExpression.class, "ArithmeticComparisonExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArithmeticComparisonExpression_Operator(), this.getArithmeticComparisonOperatorType(), "operator", null, 0, 1, ArithmeticComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithmeticComparisonExpression_LeftOperand(), this.getComparableExpression(), null, "leftOperand", null, 0, 1, ArithmeticComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithmeticComparisonExpression_RightOperand(), this.getComparableExpression(), null, "rightOperand", null, 0, 1, ArithmeticComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getArithmeticComparisonExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(stringExpressionEClass, StringExpression.class, "StringExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(logicalExpressionEClass, LogicalExpression.class, "LogicalExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listExpressionEClass, ListExpression.class, "ListExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getListExpression_Head(), this.getExpression(), null, "head", null, 0, 1, ListExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListExpression_Tail(), this.getListExpression(), null, "tail", null, 0, 1, ListExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getListExpression__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(emptyListExpressionEClass, EmptyListExpression.class, "EmptyListExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(indexAccessExpressionEClass, IndexAccessExpression.class, "IndexAccessExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIndexAccessExpression_List(), this.getExpression(), null, "list", null, 0, 1, IndexAccessExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(indexSimpleAccessExpressionEClass, IndexSimpleAccessExpression.class, "IndexSimpleAccessExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIndexSimpleAccessExpression_Idx(), theEcorePackage.getEInt(), "idx", null, 0, 1, IndexSimpleAccessExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(indexRangeAccessExpressionEClass, IndexRangeAccessExpression.class, "IndexRangeAccessExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIndexRangeAccessExpression_Lower(), theEcorePackage.getEInt(), "lower", null, 0, 1, IndexRangeAccessExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIndexRangeAccessExpression_Upper(), theEcorePackage.getEInt(), "upper", null, 0, 1, IndexRangeAccessExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryLogicalExpressionEClass, BinaryLogicalExpression.class, "BinaryLogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBinaryLogicalExpression_Operator(), this.getBinaryLogicalOperatorType(), "operator", null, 0, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryLogicalExpression_LeftOperand(), this.getLogicalExpression(), null, "leftOperand", null, 0, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryLogicalExpression_RightOperand(), this.getLogicalExpression(), null, "rightOperand", null, 0, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryLogicalExpressionEClass, UnaryLogicalExpression.class, "UnaryLogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnaryLogicalExpression_Operator(), this.getUnaryLogicalOperatorType(), "operator", null, 0, 1, UnaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnaryLogicalExpression_Operand(), this.getLogicalExpression(), null, "operand", null, 0, 1, UnaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryGraphObjectLogicalExpressionEClass, UnaryGraphObjectLogicalExpression.class, "UnaryGraphObjectLogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnaryGraphObjectLogicalExpression_Operator(), this.getUnaryGraphObjectLogicalOperatorType(), "operator", null, 0, 1, UnaryGraphObjectLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnaryGraphObjectLogicalExpression_Operand(), this.getVariable(), null, "operand", null, 0, 1, UnaryGraphObjectLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparableExpressionEClass, ComparableExpression.class, "ComparableExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(atomEClass, Atom.class, "Atom", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(literalEClass, Literal.class, "Literal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanLiteral_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, BooleanLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBooleanLiteral__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringLiteral_Value(), theEcorePackage.getEString(), "value", null, 0, 1, StringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getStringLiteral__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(numberLiteralEClass, NumberLiteral.class, "NumberLiteral", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(doubleLiteralEClass, DoubleLiteral.class, "DoubleLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleLiteral_Value(), theEcorePackage.getEDouble(), "value", null, 0, 1, DoubleLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDoubleLiteral__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(bigIntegerLiteralEClass, BigIntegerLiteral.class, "BigIntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBigIntegerLiteral_Value(), this.getBigint(), "value", null, 0, 1, BigIntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBigIntegerLiteral__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerLiteral_Value(), theEcorePackage.getELong(), "value", null, 0, 1, IntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getIntegerLiteral__FullName(), theEcorePackage.getEString(), "fullName", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(nullLiteralEClass, NullLiteral.class, "NullLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parameterComparableExpressionEClass, ParameterComparableExpression.class, "ParameterComparableExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterComparableExpression_Parameter(), this.getParameter(), null, "parameter", null, 0, 1, ParameterComparableExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyListEClass, PropertyList.class, "PropertyList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyList_Entries(), this.getPropertyListEntry(), null, "entries", null, 0, -1, PropertyList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyListEntryEClass, Map.Entry.class, "PropertyListEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyListEntry_Key(), theEcorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyListEntry_Value(), this.getExpression(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(caseEClass, Case.class, "Case", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCase_When(), this.getExpression(), null, "when", null, 0, 1, Case.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCase_Then(), this.getExpression(), null, "then", null, 0, 1, Case.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(maxHopsTypeEEnum, MaxHopsType.class, "MaxHopsType");
		addEEnumLiteral(maxHopsTypeEEnum, MaxHopsType.LIMITED);
		addEEnumLiteral(maxHopsTypeEEnum, MaxHopsType.UNLIMITED);

		initEEnum(directionEEnum, Direction.class, "Direction");
		addEEnumLiteral(directionEEnum, Direction.BOTH);
		addEEnumLiteral(directionEEnum, Direction.IN);
		addEEnumLiteral(directionEEnum, Direction.OUT);

		initEEnum(orderDirectionEEnum, OrderDirection.class, "OrderDirection");
		addEEnumLiteral(orderDirectionEEnum, OrderDirection.ASCENDING);
		addEEnumLiteral(orderDirectionEEnum, OrderDirection.DESCENDING);

		initEEnum(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.class, "BinaryArithmeticOperatorType");
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.PLUS);
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.MINUS);
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.MULTIPLICATION);
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.DIVISION);
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.MOD);
		addEEnumLiteral(binaryArithmeticOperatorTypeEEnum, BinaryArithmeticOperatorType.POWER);

		initEEnum(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.class, "ArithmeticComparisonOperatorType");
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.EQUAL_TO);
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.NOT_EQUAL_TO);
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.GREATER_THAN);
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL);
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.LESS_THAN);
		addEEnumLiteral(arithmeticComparisonOperatorTypeEEnum, ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL);

		initEEnum(unaryArithmeticOperatorTypeEEnum, UnaryArithmeticOperatorType.class, "UnaryArithmeticOperatorType");
		addEEnumLiteral(unaryArithmeticOperatorTypeEEnum, UnaryArithmeticOperatorType.MINUS);
		addEEnumLiteral(unaryArithmeticOperatorTypeEEnum, UnaryArithmeticOperatorType.PLUS);

		initEEnum(binaryLogicalOperatorTypeEEnum, BinaryLogicalOperatorType.class, "BinaryLogicalOperatorType");
		addEEnumLiteral(binaryLogicalOperatorTypeEEnum, BinaryLogicalOperatorType.AND);
		addEEnumLiteral(binaryLogicalOperatorTypeEEnum, BinaryLogicalOperatorType.OR);
		addEEnumLiteral(binaryLogicalOperatorTypeEEnum, BinaryLogicalOperatorType.XOR);

		initEEnum(unaryLogicalOperatorTypeEEnum, UnaryLogicalOperatorType.class, "UnaryLogicalOperatorType");
		addEEnumLiteral(unaryLogicalOperatorTypeEEnum, UnaryLogicalOperatorType.NOT);
		addEEnumLiteral(unaryLogicalOperatorTypeEEnum, UnaryLogicalOperatorType.IS_NULL);
		addEEnumLiteral(unaryLogicalOperatorTypeEEnum, UnaryLogicalOperatorType.IS_NOT_NULL);

		initEEnum(unaryGraphObjectLogicalOperatorTypeEEnum, UnaryGraphObjectLogicalOperatorType.class, "UnaryGraphObjectLogicalOperatorType");
		addEEnumLiteral(unaryGraphObjectLogicalOperatorTypeEEnum, UnaryGraphObjectLogicalOperatorType.IS_NULL);
		addEEnumLiteral(unaryGraphObjectLogicalOperatorTypeEEnum, UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL);

		initEEnum(labelSetStatusEEnum, LabelSetStatus.class, "LabelSetStatus");
		addEEnumLiteral(labelSetStatusEEnum, LabelSetStatus.EMPTY);
		addEEnumLiteral(labelSetStatusEEnum, LabelSetStatus.NON_EMPTY);
		addEEnumLiteral(labelSetStatusEEnum, LabelSetStatus.CONTRADICTING);

		initEEnum(pathSemanticsEEnum, PathSemantics.class, "PathSemantics");
		addEEnumLiteral(pathSemanticsEEnum, PathSemantics.ALL_PATHS);
		addEEnumLiteral(pathSemanticsEEnum, PathSemantics.SHORTEST_PATH);
		addEEnumLiteral(pathSemanticsEEnum, PathSemantics.ALL_SHORTEST_PATH);

		// Initialize data types
		initEDataType(functionEDataType, Function.class, "Function", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(bigintEDataType, BigInteger.class, "Bigint", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //RelalgPackageImpl
