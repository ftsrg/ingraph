package ingraph.relalg2tex.config

import de.oehme.xtend.contrib.Buildable
import org.eclipse.xtend.lib.annotations.Data

@Data
@Buildable
class RelalgConverterConfig {

	/**
	 * whether to include cardinality information in the converter (for trees)
	 */
	boolean includeCardinality

	/**
	 * whether to use parentheses for expressions)
	 */
	boolean parentheses

	/**
	 * whether to generate a standalone TeX document
	 */
	boolean standaloneDocument

	/**
	 * whether to include common variables for joins and antijoins
	 */
	boolean includeCommonVariables

	/**
	 * whether to output the generated TeX code to the console
	 */
	boolean consoleOutput

	/**
	 * whether to use textual notation for operators
	 */
	boolean textualOperators

	/**
	 * whether to include indices for schema, e.g. <[0] person, [1] person.name, [2] city>
	 */
	boolean schemaIndices

}
