/**
 */
package relalg.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import relalg.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelalgFactoryImpl extends EFactoryImpl implements RelalgFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RelalgFactory init() {
		try {
			RelalgFactory theRelalgFactory = (RelalgFactory)EPackage.Registry.INSTANCE.getEFactory(RelalgPackage.eNS_URI);
			if (theRelalgFactory != null) {
				return theRelalgFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RelalgFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RelalgPackage.PROJECTION_OPERATOR: return createProjectionOperator();
			case RelalgPackage.JOIN_OPERATOR: return createJoinOperator();
			case RelalgPackage.ANTI_JOIN_OPERATOR: return createAntiJoinOperator();
			case RelalgPackage.PRODUCTION_OPERATOR: return createProductionOperator();
			case RelalgPackage.FILTER_OPERATOR: return createFilterOperator();
			case RelalgPackage.EXPAND_OPERATOR: return createExpandOperator();
			case RelalgPackage.GET_VERTICES_OPERATOR: return createGetVerticesOperator();
			case RelalgPackage.DUPLICATE_ELIMINATION_OPERATOR: return createDuplicateEliminationOperator();
			case RelalgPackage.VERTEX_VARIABLE: return createVertexVariable();
			case RelalgPackage.EDGE_VARIABLE: return createEdgeVariable();
			case RelalgPackage.VERTEX_LABEL: return createVertexLabel();
			case RelalgPackage.EDGE_LABEL: return createEdgeLabel();
			case RelalgPackage.ALL_DIFFERENT_OPERATOR: return createAllDifferentOperator();
			case RelalgPackage.ATTRIBUTE_VARIABLE: return createAttributeVariable();
			case RelalgPackage.UNION_OPERATOR: return createUnionOperator();
			case RelalgPackage.CONTAINER: return createContainer();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case RelalgPackage.DIRECTION:
				return createDirectionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case RelalgPackage.DIRECTION:
				return convertDirectionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectionOperator createProjectionOperator() {
		ProjectionOperatorImpl projectionOperator = new ProjectionOperatorImpl();
		return projectionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinOperator createJoinOperator() {
		JoinOperatorImpl joinOperator = new JoinOperatorImpl();
		return joinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AntiJoinOperator createAntiJoinOperator() {
		AntiJoinOperatorImpl antiJoinOperator = new AntiJoinOperatorImpl();
		return antiJoinOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductionOperator createProductionOperator() {
		ProductionOperatorImpl productionOperator = new ProductionOperatorImpl();
		return productionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterOperator createFilterOperator() {
		FilterOperatorImpl filterOperator = new FilterOperatorImpl();
		return filterOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpandOperator createExpandOperator() {
		ExpandOperatorImpl expandOperator = new ExpandOperatorImpl();
		return expandOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GetVerticesOperator createGetVerticesOperator() {
		GetVerticesOperatorImpl getVerticesOperator = new GetVerticesOperatorImpl();
		return getVerticesOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DuplicateEliminationOperator createDuplicateEliminationOperator() {
		DuplicateEliminationOperatorImpl duplicateEliminationOperator = new DuplicateEliminationOperatorImpl();
		return duplicateEliminationOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexVariable createVertexVariable() {
		VertexVariableImpl vertexVariable = new VertexVariableImpl();
		return vertexVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeVariable createEdgeVariable() {
		EdgeVariableImpl edgeVariable = new EdgeVariableImpl();
		return edgeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexLabel createVertexLabel() {
		VertexLabelImpl vertexLabel = new VertexLabelImpl();
		return vertexLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeLabel createEdgeLabel() {
		EdgeLabelImpl edgeLabel = new EdgeLabelImpl();
		return edgeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllDifferentOperator createAllDifferentOperator() {
		AllDifferentOperatorImpl allDifferentOperator = new AllDifferentOperatorImpl();
		return allDifferentOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeVariable createAttributeVariable() {
		AttributeVariableImpl attributeVariable = new AttributeVariableImpl();
		return attributeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionOperator createUnionOperator() {
		UnionOperatorImpl unionOperator = new UnionOperatorImpl();
		return unionOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public relalg.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Direction createDirectionFromString(EDataType eDataType, String initialValue) {
		Direction result = Direction.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelalgPackage getRelalgPackage() {
		return (RelalgPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RelalgPackage getPackage() {
		return RelalgPackage.eINSTANCE;
	}

} //RelalgFactoryImpl
