package ingraph.neo4j.test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.api.exceptions.KernelException;
import org.neo4j.kernel.impl.proc.Procedures;
import org.neo4j.kernel.internal.GraphDatabaseAPI;

public class ApocHelper {

	// https://github.com/neo4j-contrib/neo4j-apoc-procedures/blob/a4423863ce77ef51c5d94cdbdc65d8a09172821b/src/test/java/apoc/util/TestUtil.java
	public static void registerProcedure(GraphDatabaseService db, Class<?>...procedures) throws KernelException {
		Procedures proceduresService = ((GraphDatabaseAPI) db).getDependencyResolver().resolveDependency(Procedures.class);
		for (Class<?> procedure : procedures) {
			proceduresService.registerProcedure(procedure);
			proceduresService.registerFunction(procedure);
		}
	}

}
