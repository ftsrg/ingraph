package ingraph.compiler.exceptions

class CompilerException(val message: String) extends RuntimeException(message)

class CompilerConfigurationException(override val message: String) extends CompilerException(message)

class ExpandChainException(override val message: String) extends CompilerException(message)

class PatternNotAllowedException(override val message: String) extends CompilerException(message)

class NameResolutionException(val name: String) extends CompilerException(s"Unresolvable name ${name}.")

class IllegalAggregationException(val info: String) extends CompilerException(s"Aggregation found at non top-level in return item: ${info}.")
