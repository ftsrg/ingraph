package ingraph.relalg2tex

class RelalgSerializerConfig {

	/**
	 * whether to include cardinality information in the serializer (for trees)
	 */
	protected var boolean includeCardinality = true 

	/**
	 * whether to use parentheses for expressions)
	 */
	protected var boolean parentheses = false

	/**
	 * whether to generate a standalone TeX document
	 */
	protected val boolean standaloneDocument = false

	/**
	 * whether to include mutual variables for joins and antijoins
	 */
	protected val boolean includeMutualVariables = false
	
	def static defaultConfig() {
		val config = new RelalgSerializerConfig()
		return config
	}

}
