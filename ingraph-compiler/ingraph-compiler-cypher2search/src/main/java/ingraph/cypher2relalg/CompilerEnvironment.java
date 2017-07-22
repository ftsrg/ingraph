package ingraph.cypher2relalg;

import ingraph.cypher2relalg.builders.VariableBuilder;
import ingraph.logger.IngraphLogger;
import relalg.RelalgContainer;
import relalg.RelalgFactory;

/**
 * Represent the environment of the compiler.
 * 
 * Environment includes the variable builder, logger and the EMF top-level container.
 */
public final class CompilerEnvironment {
	/** Variable builder that wraps variable factories and utility methods */
	public final VariableBuilder vb;
	/** The logger to be used */
	public final IngraphLogger l;
	/** Top-level container used for the EMF-based relational graph algebra model */
	public final RelalgContainer tlc;

	/**
	 * Default constructor populating the respective fields.
	 */
	private CompilerEnvironment(VariableBuilder vb, IngraphLogger l, RelalgContainer tlc) {
		this.vb = vb;
		this.l = l;
		this.tlc = tlc;

		checkEnvironment();
	}

	/**
	 * Copy constructor overriding the variable builder with the supplied instance.
	 */
	private CompilerEnvironment(CompilerEnvironment original, VariableBuilder vb) {
		this.vb = vb;
		this.l = original.l;
		this.tlc = original.tlc;

		checkEnvironment();
	}

	/**
	 * Creates and initializes a new compiler environment.
	 * @param loggerName Name of the logger 
	 * @return A new compiler environment with a logger of the given name.
	 */
	public static CompilerEnvironment newInstance(String loggerName) {
		RelalgFactory modelFactory = RelalgFactory.eINSTANCE;

		RelalgContainer topLevelContainer = modelFactory.createRelalgContainer();
		IngraphLogger logger = new IngraphLogger(loggerName);
		VariableBuilder variableBuilder = new VariableBuilder(topLevelContainer, logger);

		return new CompilerEnvironment(variableBuilder, logger, topLevelContainer);
	}

	/**
	 * Creates a copy of this compiler environment but overrides its variable builder.
	 * @param vb The new variable builder instance to use in the new compiler environment.
	 * @return A clone of the current compiler environment having vb as its variable builder.
	 */
	public CompilerEnvironment cloneInstanceOverriding(VariableBuilder vb) {
		return new CompilerEnvironment(this, vb);
	}

	protected void checkEnvironment() {
		if (this.tlc != this.vb.getTopLevelContainer()) {
			l.unrecoverableError("Top-level containers should match.");
		}
	}
}
