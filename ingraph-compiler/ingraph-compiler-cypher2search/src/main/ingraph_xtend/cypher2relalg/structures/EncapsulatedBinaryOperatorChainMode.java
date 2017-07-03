package ingraph.cypher2relalg.structures;

/**
 * Chain mode of relalg trees that has a binary operator.
 * 
 * Different modes define how we find the BinaryOperator
 * where to append what we have built so far. 
 */
public enum EncapsulatedBinaryOperatorChainMode {
	/**
	 * Root of the tree must be the BinaryOperator to be used for chaining.
	 */
	CHAIN_AT_TREE_ROOT,
	/**
	 * First BinaryOperator found by traversing from the root (i.e. through UnaryOperators)
	 * to be used for chaining.
	 */
	CHAIN_AT_FIRST_BINARY_OPERATOR,
	/**
	 * First BinaryOperator having leftInput unpopulated (i.e. === null)
	 * will be used for chaining
	 * found by traversing from the root through UnaryOperatorn instances and
	 * leftInput of BinaryOperator instances w/ populated leftInput (i.e. !== null).
	 */
	CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC,
}
