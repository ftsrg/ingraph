package project_work.test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( "db" );
        graphDb.shutdown();
    }
}
