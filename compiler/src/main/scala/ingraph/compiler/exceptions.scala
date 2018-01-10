package ingraph.compiler.exceptions

class CompilerException(val message: String) extends RuntimeException(message)

class CompilerConfigurationException(override val message: String) extends CompilerException(message)

class ExpandChainException(override val message: String) extends CompilerException(message)

class PatternNotAllowedException(override val message: String) extends CompilerException(message)
