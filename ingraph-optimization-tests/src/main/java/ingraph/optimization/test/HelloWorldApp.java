package ingraph.optimization.test;

import ingraph.cypher2relalg.Cypher2Relalg;
import ingraph.relalg2rete.Relalg2ReteTransformation;
import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter;
import relalg.RelalgContainer;

public class HelloWorldApp {
	public static void main(String[] args) {
		final String queryName = "queryX";
		final String querySpecification = "MATCH (:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)<-[:hasModerator]-(forum:Forum)-[:containerOf]->(post:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass) RETURN forum.id, forum.title, forum.creationDate, person.id, count(post) AS count ORDER BY count DESC, forum.id ASC LIMIT 20";

		final Relalg2ReteTransformation relalg2ReteTransformation = new Relalg2ReteTransformation();
		final RelalgContainer containerSearch = Cypher2Relalg.processString(querySpecification);
		final RelalgContainer containerRete = relalg2ReteTransformation.transformToRete(containerSearch);

		final RelalgConverterConfig config = RelalgConverterConfig.builder().consoleOutput(false).standaloneDocument(true).build();
		final Relalg2TexTreeConverter drawer = new Relalg2TexTreeConverter(config);
		drawer.convert(containerRete, "sandbox/" + queryName + "-rete");
	}
}
