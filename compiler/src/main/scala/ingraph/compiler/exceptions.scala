package ingraph.compiler.exceptions

class CompilerException(val message: String) extends RuntimeException(message)

class CompilerConfigurationException(override val message: String) extends CompilerException(message)

class ExpandChainException(override val message: String) extends CompilerException(message)

class PatternNotAllowedException(override val message: String) extends CompilerException(message)

class NameResolutionException(val name: String) extends CompilerException(s"Unresolvable name ${name}.")

class IllegalAggregationException(val info: String) extends CompilerException(s"Aggregation found at non top-level in return item: ${info}.")

class IncompleteCompilationException(val info: String) extends CompilerException(s"Incomplete compilation found: ${info}.")

class IncompleteResolutionException(val info: String) extends CompilerException(s"Unresolved part found: ${info}.")

class UnexpectedTypeException(val a: Any, val info: String = "(details not given)")
  extends CompilerException(s"Unexpected type found: ${a.getClass} at ${info}. String value of the object is: ${a}")
