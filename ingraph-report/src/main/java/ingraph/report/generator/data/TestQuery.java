package ingraph.report.generator.data;

public class TestQuery {
	private final String queryName;
	private final String querySpecification;
	private final boolean regressionTest;

	public String getQueryName() {
		return queryName;
	}

	public String getQuerySpecification() {
		return querySpecification;
	}

	public boolean isRegressionTest() {
		return regressionTest;
	}

	public TestQuery(String queryName, String querySpecification, boolean regressionTest) {
		this.queryName = queryName;
		this.querySpecification = querySpecification;
		this.regressionTest = regressionTest;
	}
}
