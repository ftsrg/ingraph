package project_work.test;


import java.io.IOException;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.transaction.xaframework.TxIdGenerator;

/**
 * Hello world!
 *
 */
public class App 
{
	private static enum RelTypes implements RelationshipType
	{
	    KNOWS
	}
    public static void main( String[] args )
    {
    	GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( "db" );
    	try (Transaction transaction = graphDb.beginTx()){
    		Node node1 = graphDb.createNode();
    		node1.addLabel(DynamicLabel.label("label1"));
    		Node node2 = graphDb.createNode();
    		node2.addLabel(DynamicLabel.label("label2"));
    		node2.createRelationshipTo(node1, RelTypes.KNOWS);
    		for (Relationship rel : node1.getRelationships()) {
    			rel.getStartNode().getLabels().forEach((label) -> System.out.println(label.toString()));
    		}
    		transaction.success();
    	}
    	finally{
    		graphDb.shutdown();
    	}
        
    }
}
