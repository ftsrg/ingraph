package ingraph.relalg2tex

class RelalgSerializerConfig {

	/**
	 * whether to include cardinality information in the serializer (for trees)
	 */
	public var boolean includeCardinality = false

	/**
	 * whether to use parentheses for expressions)
	 */
	public var boolean parentheses = false

	/**
	 * whether to generate a standalone TeX document
	 */
	public var boolean standaloneDocument = false

	/**
	 * whether to include mutual variables for joins and antijoins
	 */
	public var boolean includeCommonVariables = false
	
	def static defaultConfig() {
		return new RelalgSerializerConfig
	}

}
