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
	 * The meta object id for the '{@link relalg.impl.ReteNodeImpl <em>Rete Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ReteNodeImpl
	 * @see relalg.impl.RelalgPackageImpl#getReteNode()
	 * @generated
	 */
	int RETE_NODE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NODE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Rete Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NODE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Rete Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link relalg.impl.InputNodeImpl <em>Input Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.InputNodeImpl
	 * @see relalg.impl.RelalgPackageImpl#getInputNode()
	 * @generated
	 */
	int INPUT_NODE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_NODE__NAME = RETE_NODE__NAME;

	/**
	 * The number of structural features of the '<em>Input Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_NODE_FEATURE_COUNT = RETE_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Input Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_NODE_OPERATION_COUNT = RETE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.TrimmerNodeImpl <em>Trimmer Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.TrimmerNodeImpl
	 * @see relalg.impl.RelalgPackageImpl#getTrimmerNode()
	 * @generated
	 */
	int TRIMMER_NODE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_NODE__NAME = RETE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_NODE__PARENT = RETE_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trimmer Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_NODE_FEATURE_COUNT = RETE_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Trimmer Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIMMER_NODE_OPERATION_COUNT = RETE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link relalg.impl.JoinNodeImpl <em>Join Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.JoinNodeImpl
	 * @see relalg.impl.RelalgPackageImpl#getJoinNode()
	 * @generated
	 */
	int JOIN_NODE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__NAME = RETE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Left Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__LEFT_PARENT = RETE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE__RIGHT_PARENT = RETE_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Join Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE_FEATURE_COUNT = RETE_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Join Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_NODE_OPERATION_COUNT = RETE_NODE_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link relalg.impl.ReteNetworkImpl <em>Rete Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see relalg.impl.ReteNetworkImpl
	 * @see relalg.impl.RelalgPackageImpl#getReteNetwork()
	 * @generated
	 */
	int RETE_NETWORK = 4;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NETWORK__NODES = 0;

	/**
	 * The number of structural features of the '<em>Rete Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NETWORK_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Rete Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETE_NETWORK_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link relalg.ReteNode <em>Rete Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rete Node</em>'.
	 * @see relalg.ReteNode
	 * @generated
	 */
	EClass getReteNode();

	/**
	 * Returns the meta object for the attribute '{@link relalg.ReteNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see relalg.ReteNode#getName()
	 * @see #getReteNode()
	 * @generated
	 */
	EAttribute getReteNode_Name();

	/**
	 * Returns the meta object for class '{@link relalg.InputNode <em>Input Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Node</em>'.
	 * @see relalg.InputNode
	 * @generated
	 */
	EClass getInputNode();

	/**
	 * Returns the meta object for class '{@link relalg.TrimmerNode <em>Trimmer Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trimmer Node</em>'.
	 * @see relalg.TrimmerNode
	 * @generated
	 */
	EClass getTrimmerNode();

	/**
	 * Returns the meta object for the reference '{@link relalg.TrimmerNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see relalg.TrimmerNode#getParent()
	 * @see #getTrimmerNode()
	 * @generated
	 */
	EReference getTrimmerNode_Parent();

	/**
	 * Returns the meta object for class '{@link relalg.JoinNode <em>Join Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Node</em>'.
	 * @see relalg.JoinNode
	 * @generated
	 */
	EClass getJoinNode();

	/**
	 * Returns the meta object for the reference '{@link relalg.JoinNode#getLeftParent <em>Left Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left Parent</em>'.
	 * @see relalg.JoinNode#getLeftParent()
	 * @see #getJoinNode()
	 * @generated
	 */
	EReference getJoinNode_LeftParent();

	/**
	 * Returns the meta object for the reference '{@link relalg.JoinNode#getRightParent <em>Right Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right Parent</em>'.
	 * @see relalg.JoinNode#getRightParent()
	 * @see #getJoinNode()
	 * @generated
	 */
	EReference getJoinNode_RightParent();

	/**
	 * Returns the meta object for class '{@link relalg.ReteNetwork <em>Rete Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rete Network</em>'.
	 * @see relalg.ReteNetwork
	 * @generated
	 */
	EClass getReteNetwork();

	/**
	 * Returns the meta object for the containment reference list '{@link relalg.ReteNetwork#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see relalg.ReteNetwork#getNodes()
	 * @see #getReteNetwork()
	 * @generated
	 */
	EReference getReteNetwork_Nodes();

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
		 * The meta object literal for the '{@link relalg.impl.ReteNodeImpl <em>Rete Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ReteNodeImpl
		 * @see relalg.impl.RelalgPackageImpl#getReteNode()
		 * @generated
		 */
		EClass RETE_NODE = eINSTANCE.getReteNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETE_NODE__NAME = eINSTANCE.getReteNode_Name();

		/**
		 * The meta object literal for the '{@link relalg.impl.InputNodeImpl <em>Input Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.InputNodeImpl
		 * @see relalg.impl.RelalgPackageImpl#getInputNode()
		 * @generated
		 */
		EClass INPUT_NODE = eINSTANCE.getInputNode();

		/**
		 * The meta object literal for the '{@link relalg.impl.TrimmerNodeImpl <em>Trimmer Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.TrimmerNodeImpl
		 * @see relalg.impl.RelalgPackageImpl#getTrimmerNode()
		 * @generated
		 */
		EClass TRIMMER_NODE = eINSTANCE.getTrimmerNode();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIMMER_NODE__PARENT = eINSTANCE.getTrimmerNode_Parent();

		/**
		 * The meta object literal for the '{@link relalg.impl.JoinNodeImpl <em>Join Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.JoinNodeImpl
		 * @see relalg.impl.RelalgPackageImpl#getJoinNode()
		 * @generated
		 */
		EClass JOIN_NODE = eINSTANCE.getJoinNode();

		/**
		 * The meta object literal for the '<em><b>Left Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_NODE__LEFT_PARENT = eINSTANCE.getJoinNode_LeftParent();

		/**
		 * The meta object literal for the '<em><b>Right Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_NODE__RIGHT_PARENT = eINSTANCE.getJoinNode_RightParent();

		/**
		 * The meta object literal for the '{@link relalg.impl.ReteNetworkImpl <em>Rete Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see relalg.impl.ReteNetworkImpl
		 * @see relalg.impl.RelalgPackageImpl#getReteNetwork()
		 * @generated
		 */
		EClass RETE_NETWORK = eINSTANCE.getReteNetwork();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETE_NETWORK__NODES = eINSTANCE.getReteNetwork_Nodes();

	}

} //RelalgPackage
