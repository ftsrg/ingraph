/**
 */
package relalg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Path Semantics</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * example data set:
 *  CREATE
 *   (a:Node {name: 'a'}),
 *   (b:Node {name: 'b'}),
 *   (c:Node {name: 'c'}),
 *   (a)-[:REL {x: 1}]->(b)-[:REL {x: 2}]->(c),
 *   (a)-[:REL {x: 3}]->(c),
 *   (a)-[:REL {x: 4}]->(c)
 * <!-- end-model-doc -->
 * @see relalg.RelalgPackage#getPathSemantics()
 * @model
 * @generated
 */
public enum PathSemantics implements Enumerator {
	/**
	 * The '<em><b>ALL PATHS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_PATHS_VALUE
	 * @generated
	 * @ordered
	 */
	ALL_PATHS(0, "ALL_PATHS", "ALL_PATHS"),

	/**
	 * The '<em><b>SHORTEST PATH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHORTEST_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	SHORTEST_PATH(0, "SHORTEST_PATH", "SHORTEST_PATH"),

	/**
	 * The '<em><b>ALL SHORTEST PATH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_SHORTEST_PATH_VALUE
	 * @generated
	 * @ordered
	 */
	ALL_SHORTEST_PATH(0, "ALL_SHORTEST_PATH", "ALL_SHORTEST_PATH");

	/**
	 * The '<em><b>ALL PATHS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * query
	 * 
	 * MATCH p=( (a {name: 'a'})-[r:REL*]->(c {name: 'c'}) )
	 * RETURN r, p
	 * 
	 * results
	 * ╒════════════════╤═════════════════════════════════════════════════╕
	 * │r               │p                                                │
	 * ╞════════════════╪═════════════════════════════════════════════════╡
	 * │[{x: 4}]        │[{name: a}, {x: 4}, {name: c}]                   │
	 * ├────────────────┼─────────────────────────────────────────────────┤
	 * │[{x: 3}]        │[{name: a}, {x: 3}, {name: c}]                   │
	 * ├────────────────┼─────────────────────────────────────────────────┤
	 * │[{x: 1}, {x: 2}]│[{name: a}, {x: 1}, {name: b}, {x: 2}, {name: c}]│
	 * └────────────────┴─────────────────────────────────────────────────┘
	 * <!-- end-model-doc -->
	 * @see #ALL_PATHS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALL_PATHS_VALUE = 0;

	/**
	 * The '<em><b>SHORTEST PATH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * query
	 * 
	 * MATCH p=shortestPath( (a {name: 'a'})-[r:REL*]->(c {name: 'c'}) )
	 * RETURN r, p
	 * 
	 * results
	 * ╒════════╤══════════════════════════════╕
	 * │r       │p                             │
	 * ╞════════╪══════════════════════════════╡
	 * │[{x: 3}]│[{name: a}, {x: 3}, {name: c}]│
	 * └────────┴──────────────────────────────┘
	 * <!-- end-model-doc -->
	 * @see #SHORTEST_PATH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHORTEST_PATH_VALUE = 0;

	/**
	 * The '<em><b>ALL SHORTEST PATH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * query
	 * 
	 * MATCH p=allShortestPaths( (a {name: 'a'})-[r:REL*]->(c {name: 'c'}) )
	 * RETURN r, p
	 * 
	 * results
	 * ╒════════╤══════════════════════════════╕
	 * │r       │p                             │
	 * ╞════════╪══════════════════════════════╡
	 * │[{x: 3}]│[{name: a}, {x: 3}, {name: c}]│
	 * ├────────┼──────────────────────────────┤
	 * │[{x: 4}]│[{name: a}, {x: 4}, {name: c}]│
	 * └────────┴──────────────────────────────┘
	 * <!-- end-model-doc -->
	 * @see #ALL_SHORTEST_PATH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALL_SHORTEST_PATH_VALUE = 0;

	/**
	 * An array of all the '<em><b>Path Semantics</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PathSemantics[] VALUES_ARRAY =
		new PathSemantics[] {
			ALL_PATHS,
			SHORTEST_PATH,
			ALL_SHORTEST_PATH,
		};

	/**
	 * A public read-only list of all the '<em><b>Path Semantics</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PathSemantics> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Path Semantics</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PathSemantics get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PathSemantics result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Path Semantics</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PathSemantics getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PathSemantics result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Path Semantics</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PathSemantics get(int value) {
		switch (value) {
			case ALL_PATHS_VALUE: return ALL_PATHS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PathSemantics(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PathSemantics
