package ingraph.relalg2tex

import de.oehme.xtend.contrib.Buildable
import org.eclipse.xtend.lib.annotations.Data

@Data
@Buildable
class RelalgSerializerConfig {

	/**
	 * whether to include cardinality information in the serializer (for trees)
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
	 * whether to include mutual variables for joins and antijoins
	 */
	boolean includeCommonVariables

	/**
	 * whether to include cardinality information in the serializer (for trees)
	 */
	boolean consoleOutput

}
