package ingraph.cypher2relalg.util

import java.util.ArrayList
import java.util.List
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger
import java.util.logging.ConsoleHandler

/**
 * This is a wrapper over java.util.logging.Logger
 *
 * The main difference is that IngraphLogger instances are not provide by any factory.
 * Under the hood, it forwards logging messages to a java.util.logging.Logger
 * provided upon object instantiation by name or by passing the Logger instance itself.
 */
class IngraphLogger {
	String name
	Logger logger

	List<LogRecord> records = new ArrayList<LogRecord>

	new(String loggerName) {
		this.name = loggerName
		this.logger = Logger.getLogger(name)
		/*
		 *  FIXME: for multiple runs in a single JVM, we should not add more than 1 ConsoleHandler
		 */

		this.logger.addHandler(new ConsoleHandler)
	}

	new(Logger logger) {
		this.name = logger.getName
		this.logger = logger
	}

	def warning(String msg) {
		log(Level.WARNING, msg)
	}

	def error(String msg) {
		log(Level.SEVERE, msg)
	}

	/**
	 * Indicates that the compiler encountered a query part that is not supported,
	 * but should be supported. UnsupportedOperationException is thrown.
	 */
	def unsupported(String msg) {
		error(msg)
		throw new UnsupportedOperationException('''Not supported (yet) «msg»''')
	}

	/**
	 * Indicates an unrecoverable error.
	 *
	 * TODO (to be determined later): Compilation might or might not be aborted.
	 */
	def unrecoverableError(String msg) {
		error(msg)
		throw new RuntimeException('''Unrecoverable error during compilation: «msg»''')
	}

	def log(Level level, String msg) {
		records.add(new LogRecord(level, msg))
		logger.log(level, msg)
	}
}
