/**
 */
package relalg.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import relalg.AbstractJoinOperator;
import relalg.AllDifferentOperator;
import relalg.AlphaOperator;
import relalg.AntiJoinOperator;
import relalg.ArithmeticComparisonExpression;
import relalg.ArithmeticComparisonOperator;
import relalg.ArithmeticOperationExpression;
import relalg.Atom;
import relalg.AttributeVariable;
import relalg.BetaOperator;
import relalg.BinaryArithmeticOperator;
import relalg.BinaryExpression;
import relalg.BinaryLogicalExpression;
import relalg.BinaryLogicalOperator;
import relalg.ComparisonExpression;
import relalg.Direction;
import relalg.DoubleLiteral;
import relalg.DuplicateEliminationOperator;
import relalg.EdgeLabel;
import relalg.EdgeVariable;
import relalg.ElementVariable;
import relalg.ExpandOperator;
import relalg.Expression;
import relalg.GetEdgesOperator;
import relalg.GetVerticesOperator;
import relalg.IntegerLiteral;
import relalg.JoinOperator;
import relalg.Label;
import relalg.Literal;
import relalg.NamedElement;
import relalg.NumberLiteral;
import relalg.Operator;
import relalg.Order;
import relalg.ProductionOperator;
import relalg.ProjectionOperator;
import relalg.RelalgFactory;
import relalg.RelalgPackage;
import relalg.ReturnableElement;
import relalg.SelectionOperator;
import relalg.StringComparisonExpression;
import relalg.StringComparisonOperator;
import relalg.StringLiteral;
import relalg.UnaryArithmeticOperator;
import relalg.UnaryExpression;
import relalg.UnionOperator;
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
	private EClass operatorEClass = null;

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
	private EClass selectionOperatorEClass = null;

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
	private EClass unionOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

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
	private EClass returnableElementEClass = null;

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
	private EClass arithmeticOperationExpressionEClass = null;

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
	private EClass arithmeticComparisonExpressionEClass = null;

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
	private EClass stringComparisonExpressionEClass = null;

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
	private EClass numberLiteralEClass = null;

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
	private EClass doubleLiteralEClass = null;

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
	private EClass getEdgesOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparableEClass = null;

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
	private EClass elementVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparisonExpressionEClass = null;

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
	private EEnum directionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arithmeticComparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stringComparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum binaryLogicalOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum binaryArithmeticOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryArithmeticOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orderEEnum = null;

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
	public EClass getOperator() {
		return operatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperator_Schema() {
		return (EReference)operatorEClass.getEStructuralFeatures().get(0);
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
	public EClass getSelectionOperator() {
		return selectionOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelectionOperator_ConditionString() {
		return (EAttribute)selectionOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSelectionOperator_Condition() {
		return (EReference)selectionOperatorEClass.getEStructuralFeatures().get(1);
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
	public EReference getAttributeVariable_Element() {
		return (EReference)attributeVariableEClass.getEStructuralFeatures().get(0);
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
	public EReference getNamedElement_Container() {
		return (EReference)namedElementEClass.getEStructuralFeatures().get(1);
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
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_RootExpression() {
		return (EReference)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Elements() {
		return (EReference)containerEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getExpression_Text() {
		return (EAttribute)expressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReturnableElement() {
		return returnableElementEClass;
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
	public EClass getArithmeticOperationExpression() {
		return arithmeticOperationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArithmeticOperationExpression_Operator() {
		return (EAttribute)arithmeticOperationExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithmeticOperationExpression_LeftOperand() {
		return (EReference)arithmeticOperationExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArithmeticOperationExpression_RightOperand() {
		return (EReference)arithmeticOperationExpressionEClass.getEStructuralFeatures().get(2);
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
	public EReference getBinaryLogicalExpression_RightOperand() {
		return (EReference)binaryLogicalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryLogicalExpression_LeftOperand() {
		return (EReference)binaryLogicalExpressionEClass.getEStructuralFeatures().get(2);
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
	public EClass getUnaryExpression() {
		return unaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryExpression_Negated() {
		return (EAttribute)unaryExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryExpression_Operand() {
		return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringComparisonExpression() {
		return stringComparisonExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringComparisonExpression_Operator() {
		return (EAttribute)stringComparisonExpressionEClass.getEStructuralFeatures().get(0);
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
	public EClass getNumberLiteral() {
		return numberLiteralEClass;
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
	public EClass getGetEdgesOperator() {
		return getEdgesOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetEdgesOperator_SourceVertexVariable() {
		return (EReference)getEdgesOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetEdgesOperator_TargetVertexVariable() {
		return (EReference)getEdgesOperatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGetEdgesOperator_EdgeVariable() {
		return (EReference)getEdgesOperatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComparable() {
		return comparableEClass;
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
	public EClass getComparisonExpression() {
		return comparisonExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonExpression_LeftOperand() {
		return (EReference)comparisonExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonExpression_RightOperand() {
		return (EReference)comparisonExpressionEClass.getEStructuralFeatures().get(1);
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
	public EReference getAbstractJoinOperator_MutualVariables() {
		return (EReference)abstractJoinOperatorEClass.getEStructuralFeatures().get(0);
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
	public EEnum getArithmeticComparisonOperator() {
		return arithmeticComparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStringComparisonOperator() {
		return stringComparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBinaryLogicalOperator() {
		return binaryLogicalOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBinaryArithmeticOperator() {
		return binaryArithmeticOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryArithmeticOperator() {
		return unaryArithmeticOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrder() {
		return orderEEnum;
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
		operatorEClass = createEClass(OPERATOR);
		createEReference(operatorEClass, OPERATOR__SCHEMA);

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

		selectionOperatorEClass = createEClass(SELECTION_OPERATOR);
		createEAttribute(selectionOperatorEClass, SELECTION_OPERATOR__CONDITION_STRING);
		createEReference(selectionOperatorEClass, SELECTION_OPERATOR__CONDITION);

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

		edgeVariableEClass = createEClass(EDGE_VARIABLE);
		createEReference(edgeVariableEClass, EDGE_VARIABLE__EDGE_LABEL);

		labelEClass = createEClass(LABEL);

		vertexLabelEClass = createEClass(VERTEX_LABEL);

		edgeLabelEClass = createEClass(EDGE_LABEL);

		allDifferentOperatorEClass = createEClass(ALL_DIFFERENT_OPERATOR);
		createEReference(allDifferentOperatorEClass, ALL_DIFFERENT_OPERATOR__EDGE_VARIABLES);

		attributeVariableEClass = createEClass(ATTRIBUTE_VARIABLE);
		createEReference(attributeVariableEClass, ATTRIBUTE_VARIABLE__ELEMENT);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEReference(namedElementEClass, NAMED_ELEMENT__CONTAINER);

		unionOperatorEClass = createEClass(UNION_OPERATOR);

		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__ROOT_EXPRESSION);
		createEReference(containerEClass, CONTAINER__ELEMENTS);

		expressionEClass = createEClass(EXPRESSION);
		createEAttribute(expressionEClass, EXPRESSION__TEXT);

		returnableElementEClass = createEClass(RETURNABLE_ELEMENT);

		binaryExpressionEClass = createEClass(BINARY_EXPRESSION);

		arithmeticOperationExpressionEClass = createEClass(ARITHMETIC_OPERATION_EXPRESSION);
		createEAttribute(arithmeticOperationExpressionEClass, ARITHMETIC_OPERATION_EXPRESSION__OPERATOR);
		createEReference(arithmeticOperationExpressionEClass, ARITHMETIC_OPERATION_EXPRESSION__LEFT_OPERAND);
		createEReference(arithmeticOperationExpressionEClass, ARITHMETIC_OPERATION_EXPRESSION__RIGHT_OPERAND);

		binaryLogicalExpressionEClass = createEClass(BINARY_LOGICAL_EXPRESSION);
		createEAttribute(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__OPERATOR);
		createEReference(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__RIGHT_OPERAND);
		createEReference(binaryLogicalExpressionEClass, BINARY_LOGICAL_EXPRESSION__LEFT_OPERAND);

		arithmeticComparisonExpressionEClass = createEClass(ARITHMETIC_COMPARISON_EXPRESSION);
		createEAttribute(arithmeticComparisonExpressionEClass, ARITHMETIC_COMPARISON_EXPRESSION__OPERATOR);

		unaryExpressionEClass = createEClass(UNARY_EXPRESSION);
		createEAttribute(unaryExpressionEClass, UNARY_EXPRESSION__NEGATED);
		createEReference(unaryExpressionEClass, UNARY_EXPRESSION__OPERAND);

		stringComparisonExpressionEClass = createEClass(STRING_COMPARISON_EXPRESSION);
		createEAttribute(stringComparisonExpressionEClass, STRING_COMPARISON_EXPRESSION__OPERATOR);

		literalEClass = createEClass(LITERAL);

		numberLiteralEClass = createEClass(NUMBER_LITERAL);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__VALUE);

		doubleLiteralEClass = createEClass(DOUBLE_LITERAL);
		createEAttribute(doubleLiteralEClass, DOUBLE_LITERAL__VALUE);

		integerLiteralEClass = createEClass(INTEGER_LITERAL);
		createEAttribute(integerLiteralEClass, INTEGER_LITERAL__VALUE);

		getEdgesOperatorEClass = createEClass(GET_EDGES_OPERATOR);
		createEReference(getEdgesOperatorEClass, GET_EDGES_OPERATOR__SOURCE_VERTEX_VARIABLE);
		createEReference(getEdgesOperatorEClass, GET_EDGES_OPERATOR__TARGET_VERTEX_VARIABLE);
		createEReference(getEdgesOperatorEClass, GET_EDGES_OPERATOR__EDGE_VARIABLE);

		comparableEClass = createEClass(COMPARABLE);

		atomEClass = createEClass(ATOM);

		elementVariableEClass = createEClass(ELEMENT_VARIABLE);
		createEReference(elementVariableEClass, ELEMENT_VARIABLE__ATTRIBUTES);

		comparisonExpressionEClass = createEClass(COMPARISON_EXPRESSION);
		createEReference(comparisonExpressionEClass, COMPARISON_EXPRESSION__LEFT_OPERAND);
		createEReference(comparisonExpressionEClass, COMPARISON_EXPRESSION__RIGHT_OPERAND);

		abstractJoinOperatorEClass = createEClass(ABSTRACT_JOIN_OPERATOR);
		createEReference(abstractJoinOperatorEClass, ABSTRACT_JOIN_OPERATOR__MUTUAL_VARIABLES);

		// Create enums
		directionEEnum = createEEnum(DIRECTION);
		arithmeticComparisonOperatorEEnum = createEEnum(ARITHMETIC_COMPARISON_OPERATOR);
		stringComparisonOperatorEEnum = createEEnum(STRING_COMPARISON_OPERATOR);
		binaryLogicalOperatorEEnum = createEEnum(BINARY_LOGICAL_OPERATOR);
		binaryArithmeticOperatorEEnum = createEEnum(BINARY_ARITHMETIC_OPERATOR);
		unaryArithmeticOperatorEEnum = createEEnum(UNARY_ARITHMETIC_OPERATOR);
		orderEEnum = createEEnum(ORDER);
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
		joinOperatorEClass.getESuperTypes().add(this.getAbstractJoinOperator());
		alphaOperatorEClass.getESuperTypes().add(this.getOperator());
		betaOperatorEClass.getESuperTypes().add(this.getOperator());
		antiJoinOperatorEClass.getESuperTypes().add(this.getAbstractJoinOperator());
		productionOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		selectionOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		expandOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		getVerticesOperatorEClass.getESuperTypes().add(this.getOperator());
		duplicateEliminationOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		variableEClass.getESuperTypes().add(this.getNamedElement());
		variableEClass.getESuperTypes().add(this.getReturnableElement());
		variableEClass.getESuperTypes().add(this.getComparable());
		vertexVariableEClass.getESuperTypes().add(this.getElementVariable());
		edgeVariableEClass.getESuperTypes().add(this.getElementVariable());
		labelEClass.getESuperTypes().add(this.getNamedElement());
		vertexLabelEClass.getESuperTypes().add(this.getLabel());
		edgeLabelEClass.getESuperTypes().add(this.getLabel());
		allDifferentOperatorEClass.getESuperTypes().add(this.getAlphaOperator());
		attributeVariableEClass.getESuperTypes().add(this.getVariable());
		unionOperatorEClass.getESuperTypes().add(this.getBetaOperator());
		expressionEClass.getESuperTypes().add(this.getReturnableElement());
		binaryExpressionEClass.getESuperTypes().add(this.getExpression());
		arithmeticOperationExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		arithmeticOperationExpressionEClass.getESuperTypes().add(this.getComparable());
		binaryLogicalExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		arithmeticComparisonExpressionEClass.getESuperTypes().add(this.getComparisonExpression());
		unaryExpressionEClass.getESuperTypes().add(this.getExpression());
		stringComparisonExpressionEClass.getESuperTypes().add(this.getComparisonExpression());
		literalEClass.getESuperTypes().add(this.getAtom());
		literalEClass.getESuperTypes().add(this.getComparable());
		numberLiteralEClass.getESuperTypes().add(this.getLiteral());
		stringLiteralEClass.getESuperTypes().add(this.getLiteral());
		doubleLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		integerLiteralEClass.getESuperTypes().add(this.getNumberLiteral());
		getEdgesOperatorEClass.getESuperTypes().add(this.getOperator());
		atomEClass.getESuperTypes().add(this.getExpression());
		elementVariableEClass.getESuperTypes().add(this.getVariable());
		comparisonExpressionEClass.getESuperTypes().add(this.getBinaryExpression());
		abstractJoinOperatorEClass.getESuperTypes().add(this.getBetaOperator());

		// Initialize classes, features, and operations; add parameters
		initEClass(operatorEClass, Operator.class, "Operator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperator_Schema(), this.getVariable(), null, "schema", null, 0, -1, Operator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(projectionOperatorEClass, ProjectionOperator.class, "ProjectionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProjectionOperator_Variables(), this.getVariable(), null, "variables", null, 0, -1, ProjectionOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinOperatorEClass, JoinOperator.class, "JoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(alphaOperatorEClass, AlphaOperator.class, "AlphaOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlphaOperator_Input(), this.getOperator(), null, "input", null, 1, 1, AlphaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(betaOperatorEClass, BetaOperator.class, "BetaOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBetaOperator_LeftInput(), this.getOperator(), null, "leftInput", null, 1, 1, BetaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBetaOperator_RightInput(), this.getOperator(), null, "rightInput", null, 1, 1, BetaOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(antiJoinOperatorEClass, AntiJoinOperator.class, "AntiJoinOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productionOperatorEClass, ProductionOperator.class, "ProductionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(selectionOperatorEClass, SelectionOperator.class, "SelectionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectionOperator_ConditionString(), ecorePackage.getEString(), "conditionString", null, 0, 1, SelectionOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSelectionOperator_Condition(), this.getExpression(), null, "condition", null, 1, 1, SelectionOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		initEClass(edgeVariableEClass, EdgeVariable.class, "EdgeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdgeVariable_EdgeLabel(), this.getEdgeLabel(), null, "edgeLabel", null, 1, 1, EdgeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(vertexLabelEClass, VertexLabel.class, "VertexLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(edgeLabelEClass, EdgeLabel.class, "EdgeLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(allDifferentOperatorEClass, AllDifferentOperator.class, "AllDifferentOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAllDifferentOperator_EdgeVariables(), this.getEdgeVariable(), null, "edgeVariables", null, 0, -1, AllDifferentOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeVariableEClass, AttributeVariable.class, "AttributeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeVariable_Element(), this.getElementVariable(), this.getElementVariable_Attributes(), "element", null, 1, 1, AttributeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNamedElement_Container(), this.getContainer(), this.getContainer_Elements(), "container", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unionOperatorEClass, UnionOperator.class, "UnionOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, relalg.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_RootExpression(), this.getOperator(), null, "rootExpression", null, 0, 1, relalg.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Elements(), this.getNamedElement(), this.getNamedElement_Container(), "elements", null, 0, -1, relalg.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExpression_Text(), ecorePackage.getEString(), "text", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(returnableElementEClass, ReturnableElement.class, "ReturnableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(binaryExpressionEClass, BinaryExpression.class, "BinaryExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(arithmeticOperationExpressionEClass, ArithmeticOperationExpression.class, "ArithmeticOperationExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArithmeticOperationExpression_Operator(), this.getBinaryArithmeticOperator(), "operator", null, 0, 1, ArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithmeticOperationExpression_LeftOperand(), this.getExpression(), null, "leftOperand", null, 1, 1, ArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArithmeticOperationExpression_RightOperand(), this.getExpression(), null, "rightOperand", null, 1, 1, ArithmeticOperationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryLogicalExpressionEClass, BinaryLogicalExpression.class, "BinaryLogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBinaryLogicalExpression_Operator(), this.getBinaryLogicalOperator(), "operator", null, 0, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryLogicalExpression_RightOperand(), this.getExpression(), null, "rightOperand", null, 1, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryLogicalExpression_LeftOperand(), this.getExpression(), null, "leftOperand", null, 1, 1, BinaryLogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arithmeticComparisonExpressionEClass, ArithmeticComparisonExpression.class, "ArithmeticComparisonExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArithmeticComparisonExpression_Operator(), this.getArithmeticComparisonOperator(), "operator", null, 0, 1, ArithmeticComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnaryExpression_Negated(), ecorePackage.getEBoolean(), "negated", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnaryExpression_Operand(), this.getExpression(), null, "operand", null, 1, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringComparisonExpressionEClass, StringComparisonExpression.class, "StringComparisonExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringComparisonExpression_Operator(), this.getStringComparisonOperator(), "operator", null, 0, 1, StringComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(literalEClass, Literal.class, "Literal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numberLiteralEClass, NumberLiteral.class, "NumberLiteral", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, StringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleLiteralEClass, DoubleLiteral.class, "DoubleLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleLiteral_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, DoubleLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerLiteral_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getEdgesOperatorEClass, GetEdgesOperator.class, "GetEdgesOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGetEdgesOperator_SourceVertexVariable(), this.getVertexVariable(), null, "sourceVertexVariable", null, 1, 1, GetEdgesOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGetEdgesOperator_TargetVertexVariable(), this.getVertexVariable(), null, "targetVertexVariable", null, 1, 1, GetEdgesOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGetEdgesOperator_EdgeVariable(), this.getEdgeVariable(), null, "edgeVariable", null, 1, 1, GetEdgesOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparableEClass, relalg.Comparable.class, "Comparable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(atomEClass, Atom.class, "Atom", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementVariableEClass, ElementVariable.class, "ElementVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementVariable_Attributes(), this.getAttributeVariable(), this.getAttributeVariable_Element(), "attributes", null, 0, -1, ElementVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparisonExpressionEClass, ComparisonExpression.class, "ComparisonExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComparisonExpression_LeftOperand(), this.getComparable(), null, "leftOperand", null, 1, 1, ComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComparisonExpression_RightOperand(), this.getComparable(), null, "rightOperand", null, 1, 1, ComparisonExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractJoinOperatorEClass, AbstractJoinOperator.class, "AbstractJoinOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractJoinOperator_MutualVariables(), this.getVariable(), null, "mutualVariables", null, 0, -1, AbstractJoinOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(directionEEnum, Direction.class, "Direction");
		addEEnumLiteral(directionEEnum, Direction.BOTH);
		addEEnumLiteral(directionEEnum, Direction.IN);
		addEEnumLiteral(directionEEnum, Direction.OUT);

		initEEnum(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.class, "ArithmeticComparisonOperator");
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.EQUAL_TO);
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.NOT_EQUAL_TO);
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.GREATER_THAN);
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.GREATER_THAN_OR_EQUAL);
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.LESS_THAN);
		addEEnumLiteral(arithmeticComparisonOperatorEEnum, ArithmeticComparisonOperator.LESS_THAN_OR_EQUAL);

		initEEnum(stringComparisonOperatorEEnum, StringComparisonOperator.class, "StringComparisonOperator");
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.MATCHES);
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.IN);
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.STARTS_WITH);
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.ENDS_WITH);
		addEEnumLiteral(stringComparisonOperatorEEnum, StringComparisonOperator.CONTAINS);

		initEEnum(binaryLogicalOperatorEEnum, BinaryLogicalOperator.class, "BinaryLogicalOperator");
		addEEnumLiteral(binaryLogicalOperatorEEnum, BinaryLogicalOperator.OR);
		addEEnumLiteral(binaryLogicalOperatorEEnum, BinaryLogicalOperator.XOR);
		addEEnumLiteral(binaryLogicalOperatorEEnum, BinaryLogicalOperator.AND);

		initEEnum(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.class, "BinaryArithmeticOperator");
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.PLUS);
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.MINUS);
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.MULTIPLICATION);
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.DIVISION);
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.MOD);
		addEEnumLiteral(binaryArithmeticOperatorEEnum, BinaryArithmeticOperator.POWER);

		initEEnum(unaryArithmeticOperatorEEnum, UnaryArithmeticOperator.class, "UnaryArithmeticOperator");
		addEEnumLiteral(unaryArithmeticOperatorEEnum, UnaryArithmeticOperator.PLUS);
		addEEnumLiteral(unaryArithmeticOperatorEEnum, UnaryArithmeticOperator.MINUS);

		initEEnum(orderEEnum, Order.class, "Order");
		addEEnumLiteral(orderEEnum, Order.DESCENDING);
		addEEnumLiteral(orderEEnum, Order.ASCENDING);

		// Create resource
		createResource(eNS_URI);
	}

} //RelalgPackageImpl
