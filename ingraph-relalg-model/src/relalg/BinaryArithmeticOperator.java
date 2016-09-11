/**
 */
package relalg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Binary Arithmetic Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see relalg.RelalgPackage#getBinaryArithmeticOperator()
 * @model
 * @generated
 */
public enum BinaryArithmeticOperator implements Enumerator {
	/**
	 * The '<em><b>PLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS(0, "PLUS", "+"),

	/**
	 * The '<em><b>MINUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS(1, "MINUS", "-"),

	/**
	 * The '<em><b>MULTIPLICATION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTIPLICATION_VALUE
	 * @generated
	 * @ordered
	 */
	MULTIPLICATION(2, "MULTIPLICATION", "*"),

	/**
	 * The '<em><b>DIVISION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVISION_VALUE
	 * @generated
	 * @ordered
	 */
	DIVISION(3, "DIVISION", "/"),

	/**
	 * The '<em><b>MOD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOD_VALUE
	 * @generated
	 * @ordered
	 */
	MOD(4, "MOD", "%"),

	/**
	 * The '<em><b>POWER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POWER_VALUE
	 * @generated
	 * @ordered
	 */
	POWER(5, "POWER", "^");

	/**
	 * The '<em><b>PLUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PLUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLUS
	 * @model literal="+"
	 * @generated
	 * @ordered
	 */
	public static final int PLUS_VALUE = 0;

	/**
	 * The '<em><b>MINUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUS
	 * @model literal="-"
	 * @generated
	 * @ordered
	 */
	public static final int MINUS_VALUE = 1;

	/**
	 * The '<em><b>MULTIPLICATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MULTIPLICATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MULTIPLICATION
	 * @model literal="*"
	 * @generated
	 * @ordered
	 */
	public static final int MULTIPLICATION_VALUE = 2;

	/**
	 * The '<em><b>DIVISION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIVISION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIVISION
	 * @model literal="/"
	 * @generated
	 * @ordered
	 */
	public static final int DIVISION_VALUE = 3;

	/**
	 * The '<em><b>MOD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MOD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MOD
	 * @model literal="%"
	 * @generated
	 * @ordered
	 */
	public static final int MOD_VALUE = 4;

	/**
	 * The '<em><b>POWER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POWER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POWER
	 * @model literal="^"
	 * @generated
	 * @ordered
	 */
	public static final int POWER_VALUE = 5;

	/**
	 * An array of all the '<em><b>Binary Arithmetic Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BinaryArithmeticOperator[] VALUES_ARRAY =
		new BinaryArithmeticOperator[] {
			PLUS,
			MINUS,
			MULTIPLICATION,
			DIVISION,
			MOD,
			POWER,
		};

	/**
	 * A public read-only list of all the '<em><b>Binary Arithmetic Operator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BinaryArithmeticOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Binary Arithmetic Operator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BinaryArithmeticOperator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BinaryArithmeticOperator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Binary Arithmetic Operator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BinaryArithmeticOperator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BinaryArithmeticOperator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Binary Arithmetic Operator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BinaryArithmeticOperator get(int value) {
		switch (value) {
			case PLUS_VALUE: return PLUS;
			case MINUS_VALUE: return MINUS;
			case MULTIPLICATION_VALUE: return MULTIPLICATION;
			case DIVISION_VALUE: return DIVISION;
			case MOD_VALUE: return MOD;
			case POWER_VALUE: return POWER;
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
	private BinaryArithmeticOperator(int value, String name, String literal) {
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
	
} //BinaryArithmeticOperator
