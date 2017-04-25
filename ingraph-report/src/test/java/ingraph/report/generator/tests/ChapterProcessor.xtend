package ingraph.report.generator.tests

import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.report.generator.QueryProcessor
import ingraph.report.generator.data.TestQuery
import java.util.ArrayList
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

class ChapterProcessor {
	
	@Accessors(PUBLIC_GETTER) var totalQueries = 0
	@Accessors(PUBLIC_GETTER) var compilingQueries = 0
	@Accessors(PUBLIC_GETTER) val List<CharSequence> subsections = new ArrayList

	val RelalgConverterConfig treeSerializerConfig

	new(RelalgConverterConfig treeSerializerConfig) {
		this.treeSerializerConfig = treeSerializerConfig
	}

	def void processQueries(Iterable<TestQuery> testQueries) {
		for (testQuery : testQueries) {
			val qp = new QueryProcessor(treeSerializerConfig)
			val compiled = qp.toSubsection(testQuery, subsections)
			totalQueries++
			if (compiled) {
				compilingQueries++
			}
		}
	}
	
}