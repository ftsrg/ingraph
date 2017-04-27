package ingraph.relalg2tex.config;

public class RelalgConverterConfig {

	/**
	 * whether to include cardinality information (for trees)
	 */
	private final boolean includeCardinality;

	/**
	 * whether to omit schema information (for trees)
	 */
	private final boolean omitSchema;

	/**
	 * whether to use parentheses (for expressions)
	 */
	private final boolean parentheses;

	/**
	 * whether to generate a standalone TeX document
	 */
	private final boolean standaloneDocument;

	/**
	 * whether to include common variables for joins and antijoins
	 */
	private final boolean includeCommonVariables;

	/**
	 * whether to output the generated TeX code to the console
	 */
	private final boolean consoleOutput;

	/**
	 * whether to use textual notation for operators
	 */
	private final boolean textualOperators;

	/**
	 * whether to include indices for schema, e.g. <[0] person, [1] person.name,
	 * [2] city>
	 */
	private final boolean schemaIndices;

	public boolean isIncludeCardinality() {
		return includeCardinality;
	}

	public boolean isOmitSchema() {
		return omitSchema;
	}

	public boolean isParentheses() {
		return parentheses;
	}

	public boolean isStandaloneDocument() {
		return standaloneDocument;
	}

	public boolean isIncludeCommonVariables() {
		return includeCommonVariables;
	}

	public boolean isConsoleOutput() {
		return consoleOutput;
	}

	public boolean isTextualOperators() {
		return textualOperators;
	}

	public boolean isSchemaIndices() {
		return schemaIndices;
	}

	public RelalgConverterConfig(final boolean includeCardinality, final boolean omitSchema, final boolean parentheses,
			final boolean standaloneDocument, final boolean includeCommonVariables, final boolean consoleOutput,
			final boolean textualOperators, final boolean schemaIndices) {
		super();
		this.includeCardinality = includeCardinality;
		this.omitSchema = omitSchema;
		this.parentheses = parentheses;
		this.standaloneDocument = standaloneDocument;
		this.includeCommonVariables = includeCommonVariables;
		this.consoleOutput = consoleOutput;
		this.textualOperators = textualOperators;
		this.schemaIndices = schemaIndices;
	}

}
