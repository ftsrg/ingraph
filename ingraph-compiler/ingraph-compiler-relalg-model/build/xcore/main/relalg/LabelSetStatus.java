/**
 */
package relalg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Label Set Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Indicates if the labelset represented is theoretically satisfiable or not.
 *  * If it is undefined, it matches every labels.
 * If it is defined, but empty, matching semantics differs:
 *  1. a vertex must have *all* of the labels
 *  2. an edge must have *one* of the labels
 *  * A labelset can be non-satisfiable (false, also known as contradicting) if,
 * for the same edge variable edge1, the following labelset constraints
 * were given in the query:
 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
 *  2. [edge1:C], so edge1 needs to have label C.
 *  These are contradicting as a single edge instance can have at most one label.
 * <!-- end-model-doc -->
 * @see relalg.RelalgPackage#getLabelSetStatus()
 * @model
 * @generated
 */
public enum LabelSetStatus implements Enumerator {
	/**
	 * The '<em><b>EMPTY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EMPTY_VALUE
	 * @generated
	 * @ordered
	 */
	EMPTY(0, "EMPTY", "EMPTY"),

	/**
	 * The '<em><b>NON EMPTY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NON_EMPTY_VALUE
	 * @generated
	 * @ordered
	 */
	NON_EMPTY(0, "NON_EMPTY", "NON_EMPTY"),

	/**
	 * The '<em><b>CONTRADICTING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTRADICTING_VALUE
	 * @generated
	 * @ordered
	 */
	CONTRADICTING(0, "CONTRADICTING", "CONTRADICTING");

	/**
	 * The '<em><b>EMPTY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Each edge and vertex satisfies an empty labelset constraint
	 * <!-- end-model-doc -->
	 * @see #EMPTY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EMPTY_VALUE = 0;

	/**
	 * The '<em><b>NON EMPTY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Theoretically, a non-empty labelset constraint is satisfiable,
	 * but matching semantics differ for different variables:
	 *  1. a vertex must have *all* of the labels
	 *  2. an edge must have *one* of the labels
	 * <!-- end-model-doc -->
	 * @see #NON_EMPTY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NON_EMPTY_VALUE = 0;

	/**
	 * The '<em><b>CONTRADICTING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A labelset constraint might be non-satisfiable,
	 * in case it is a combination of multiple  labelset constraints.
	 *    * For an edge variable, the combination of the following labelset constraints
	 * is contradicting, thus un-satisfiable:
	 *  1. [edge1:A|B], so edge1 needs to have either label A, either label B
	 *  2. [edge1:C], so edge1 needs to have label C.
	 * These are contradicting as a single edge instance can have at most one label.
	 * <!-- end-model-doc -->
	 * @see #CONTRADICTING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONTRADICTING_VALUE = 0;

	/**
	 * An array of all the '<em><b>Label Set Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LabelSetStatus[] VALUES_ARRAY =
		new LabelSetStatus[] {
			EMPTY,
			NON_EMPTY,
			CONTRADICTING,
		};

	/**
	 * A public read-only list of all the '<em><b>Label Set Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<LabelSetStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Label Set Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LabelSetStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelSetStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Set Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LabelSetStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelSetStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Set Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LabelSetStatus get(int value) {
		switch (value) {
			case EMPTY_VALUE: return EMPTY;
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
	private LabelSetStatus(int value, String name, String literal) {
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
	
} //LabelSetStatus
