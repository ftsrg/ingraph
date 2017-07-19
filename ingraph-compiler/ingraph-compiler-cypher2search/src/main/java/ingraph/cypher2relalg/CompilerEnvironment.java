package ingraph.cypher2relalg;

import ingraph.logger.IngraphLogger;
import relalg.RelalgContainer;
import relalg.RelalgFactory;

/**
 * Represent the environment of the compiler.
 * 
 * Environment includes the model factory, variable builder, logger.  
 */
public final class CompilerEnvironment {
	/** The model factory for the relational graph algebra representation */
	public RelalgFactory mf;
	/** Variable builder that wraps variable factories and utility methods */
	public VariableBuilder vb;
	/** The logger to be used */
	public IngraphLogger l;
	/** Top-level container used for the EMF-based relational graph algebra model */
	public RelalgContainer tlc;

	/**
	 * Default constructor populating the respective fields.
	 */
	CompilerEnvironment(VariableBuilder vb, IngraphLogger l, RelalgFactory mf, RelalgContainer tlc) {
		this.mf = mf;
		this.vb = vb;
		this.l = l;
		this.tlc = tlc;
	}

	/**
	 * Copy constructor overriding the variable builder with the supplied instance.
	 */
	public CompilerEnvironment(CompilerEnvironment original, VariableBuilder vb) {
		this.mf = original.mf;
		this.vb = vb;
		this.l = original.l;
		this.tlc = original.tlc;
	}
}
