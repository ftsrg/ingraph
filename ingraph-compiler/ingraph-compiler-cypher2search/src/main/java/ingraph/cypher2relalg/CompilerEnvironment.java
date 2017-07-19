package ingraph.cypher2relalg;

import ingraph.cypher2relalg.builders.VariableBuilder;
import ingraph.logger.IngraphLogger;
import relalg.RelalgContainer;

/**
 * Represent the environment of the compiler.
 * 
 * Environment includes the variable builder, logger and the EMF top-level container.
 */
public final class CompilerEnvironment {
	/** Variable builder that wraps variable factories and utility methods */
	public VariableBuilder vb;
	/** The logger to be used */
	public IngraphLogger l;
	/** Top-level container used for the EMF-based relational graph algebra model */
	public RelalgContainer tlc;

	/**
	 * Default constructor populating the respective fields.
	 */
	public CompilerEnvironment(VariableBuilder vb, IngraphLogger l, RelalgContainer tlc) {
		this.vb = vb;
		this.l = l;
		this.tlc = tlc;

		checkEnvironment();
	}

	/**
	 * Copy constructor overriding the variable builder with the supplied instance.
	 */
	public CompilerEnvironment(CompilerEnvironment original, VariableBuilder vb) {
		this.vb = vb;
		this.l = original.l;
		this.tlc = original.tlc;

		checkEnvironment();
	}

	protected void checkEnvironment() {
		if (this.tlc != this.vb.getTopLevelContainer()) {
			l.unrecoverableError("Top-level containers should match.");
		}
	}
}
