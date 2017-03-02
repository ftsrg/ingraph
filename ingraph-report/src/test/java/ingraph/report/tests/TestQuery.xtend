package ingraph.report.tests

import org.eclipse.xtend.lib.annotations.Data
import de.oehme.xtend.contrib.Buildable

@Data
@Buildable
class TestQuery {
	String queryName
	String querySpecification
	boolean regressionTest
}
