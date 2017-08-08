/**
 */
package relalg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Arithmetic Comparison Operator Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage#getArithmeticComparisonOperatorType()
 * @model
 * @generated
 */
public enum ArithmeticComparisonOperatorType implements Enumerator {
	/**
	 * The '<em><b>EQUAL TO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUAL_TO_VALUE
	 * @generated
	 * @ordered
	 */
	EQUAL_TO(0, "EQUAL_TO", "EQUAL_TO"),

	/**
	 * The '<em><b>NOT EQUAL TO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUAL_TO_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EQUAL_TO(0, "NOT_EQUAL_TO", "NOT_EQUAL_TO"),

	/**
	 * The '<em><b>GREATER THAN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_THAN(0, "GREATER_THAN", "GREATER_THAN"),

	/**
	 * The '<em><b>GREATER THAN OR EQUAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_OR_EQUAL_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_THAN_OR_EQUAL(0, "GREATER_THAN_OR_EQUAL", "GREATER_THAN_OR_EQUAL"),

	/**
	 * The '<em><b>LESS THAN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_THAN(0, "LESS_THAN", "LESS_THAN"),

	/**
	 * The '<em><b>LESS THAN OR EQUAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_OR_EQUAL_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_THAN_OR_EQUAL(0, "LESS_THAN_OR_EQUAL", "LESS_THAN_OR_EQUAL");

	/**
	 * The '<em><b>EQUAL TO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EQUAL TO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUAL_TO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EQUAL_TO_VALUE = 0;

	/**
	 * The '<em><b>NOT EQUAL TO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT EQUAL TO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUAL_TO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EQUAL_TO_VALUE = 0;

	/**
	 * The '<em><b>GREATER THAN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GREATER THAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THAN_VALUE = 0;

	/**
	 * The '<em><b>GREATER THAN OR EQUAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GREATER THAN OR EQUAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_THAN_OR_EQUAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_THAN_OR_EQUAL_VALUE = 0;

	/**
	 * The '<em><b>LESS THAN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LESS THAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN_VALUE = 0;

	/**
	 * The '<em><b>LESS THAN OR EQUAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LESS THAN OR EQUAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_THAN_OR_EQUAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LESS_THAN_OR_EQUAL_VALUE = 0;

	/**
	 * An array of all the '<em><b>Arithmetic Comparison Operator Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ArithmeticComparisonOperatorType[] VALUES_ARRAY =
		new ArithmeticComparisonOperatorType[] {
			EQUAL_TO,
			NOT_EQUAL_TO,
			GREATER_THAN,
			GREATER_THAN_OR_EQUAL,
			LESS_THAN,
			LESS_THAN_OR_EQUAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Arithmetic Comparison Operator Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ArithmeticComparisonOperatorType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Arithmetic Comparison Operator Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ArithmeticComparisonOperatorType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArithmeticComparisonOperatorType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Arithmetic Comparison Operator Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ArithmeticComparisonOperatorType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArithmeticComparisonOperatorType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Arithmetic Comparison Operator Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ArithmeticComparisonOperatorType get(int value) {
		switch (value) {
			case EQUAL_TO_VALUE: return EQUAL_TO;
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
	private ArithmeticComparisonOperatorType(int value, String name, String literal) {
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
	
} //ArithmeticComparisonOperatorType
