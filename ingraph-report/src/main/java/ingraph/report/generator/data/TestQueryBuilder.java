package ingraph.report.generator.data;

public class TestQueryBuilder {
	private String queryName;
	private String querySpecification;
	private boolean regressionTest;

	public TestQueryBuilder setQueryName(String queryName) {
		this.queryName = queryName;
		return this;
	}

	public TestQueryBuilder setQuerySpecification(String querySpecification) {
		this.querySpecification = querySpecification;
		return this;
	}

	public TestQueryBuilder setRegressionTest(boolean regressionTest) {
		this.regressionTest = regressionTest;
		return this;
	}

	public TestQuery build() {
		return new TestQuery(queryName, querySpecification, regressionTest);
	}
}