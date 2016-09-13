/**
 */
package relalg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>String Comparison Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage#getStringComparisonOperator()
 * @model
 * @generated
 */
public enum StringComparisonOperator implements Enumerator {
	/**
	 * The '<em><b>MATCHES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATCHES_VALUE
	 * @generated
	 * @ordered
	 */
	MATCHES(0, "MATCHES", "=~"),

	/**
	 * The '<em><b>IN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_VALUE
	 * @generated
	 * @ordered
	 */
	IN(1, "IN", "IN"),

	/**
	 * The '<em><b>STARTS WITH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	STARTS_WITH(2, "STARTS_WITH", "STARTS WITH"),

	/**
	 * The '<em><b>ENDS WITH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENDS_WITH_VALUE
	 * @generated
	 * @ordered
	 */
	ENDS_WITH(3, "ENDS_WITH", "ENDS WITH"),

	/**
	 * The '<em><b>CONTAINS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINS_VALUE
	 * @generated
	 * @ordered
	 */
	CONTAINS(4, "CONTAINS", "CONTAINS");

	/**
	 * The '<em><b>MATCHES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MATCHES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MATCHES
	 * @model literal="=~"
	 * @generated
	 * @ordered
	 */
	public static final int MATCHES_VALUE = 0;

	/**
	 * The '<em><b>IN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IN_VALUE = 1;

	/**
	 * The '<em><b>STARTS WITH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STARTS WITH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTS_WITH
	 * @model literal="STARTS WITH"
	 * @generated
	 * @ordered
	 */
	public static final int STARTS_WITH_VALUE = 2;

	/**
	 * The '<em><b>ENDS WITH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ENDS WITH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENDS_WITH
	 * @model literal="ENDS WITH"
	 * @generated
	 * @ordered
	 */
	public static final int ENDS_WITH_VALUE = 3;

	/**
	 * The '<em><b>CONTAINS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTAINS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINS_VALUE = 4;

	/**
	 * An array of all the '<em><b>String Comparison Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StringComparisonOperator[] VALUES_ARRAY =
		new StringComparisonOperator[] {
			MATCHES,
			IN,
			STARTS_WITH,
			ENDS_WITH,
			CONTAINS,
		};

	/**
	 * A public read-only list of all the '<em><b>String Comparison Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<StringComparisonOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>String Comparison Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StringComparisonOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StringComparisonOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>String Comparison Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StringComparisonOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StringComparisonOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>String Comparison Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StringComparisonOperator get(int value) {
		switch (value) {
			case MATCHES_VALUE: return MATCHES;
			case IN_VALUE: return IN;
			case STARTS_WITH_VALUE: return STARTS_WITH;
			case ENDS_WITH_VALUE: return ENDS_WITH;
			case CONTAINS_VALUE: return CONTAINS;
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
	private StringComparisonOperator(int value, String name, String literal) {
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
	
} //StringComparisonOperator
