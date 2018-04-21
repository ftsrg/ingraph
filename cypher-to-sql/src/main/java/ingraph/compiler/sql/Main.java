package ingraph.compiler.sql;

import ingraph.driver.CypherDriverFactory;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.Driver;
import org.sqlite.SQLiteConfig;

import static org.neo4j.driver.v1.Values.parameters;

import java.sql.*;
import java.sql.Statement;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {
		String cypherSelect = "MATCH (n), (m)\nRETURN n.value AS n, m.value AS m";
//		String cypherSelect = "MATCH ()-[rel:X]-(a)\nWHERE a.name = 'Andres'\nRETURN a";
		CompileSql compileSql = new CompileSql(cypherSelect);
		String sql = compileSql.run();

		runGraphQuery("CREATE ({value: 1}), ({value: 2}), ({value: 3})", sql);
//		runGraphQuery("CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})", sql);
	}

	private static void runGraphQuery(String createCypherQuery, String selectSqlQuery) {
		try (Driver driver = CypherDriverFactory.createNeo4jDriver("bolt://localhost:7687",
			AuthTokens.basic("neo4j", "admin"))) {
			try (Session cypherSession = driver.session()) {
				try (Transaction tx = cypherSession.beginTransaction()) {
					tx.run(createCypherQuery);
					tx.success();
				}

				SQLiteConfig config = new SQLiteConfig();
				config.enforceForeignKeys(true);
				try (Connection sqlConnection = DriverManager.getConnection("jdbc:sqlite:", config.toProperties());
						 Statement sqlStatement = sqlConnection.createStatement()) {

					sqlStatement.executeUpdate(SqlQueries.createTables());

					ExportSteps.execute(cypherSession, sqlConnection);

					dump(sqlStatement, selectSqlQuery);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void dumpTable(Statement sqlStatement, String tablename) throws SQLException {
		dump(sqlStatement, "SELECT * FROM " + tablename);
	}

	private static void dump(Statement sqlStatement, String query) throws SQLException {
		System.out.println(query + ": ");
		try (ResultSet rs = sqlStatement.executeQuery(query)) {
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				System.out.println(rs.getMetaData().getColumnName(i) + ": " + rs.getMetaData().getColumnTypeName(i));
			}
			while (rs.next()) {
				System.out.println("---");
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					System.out.println(rs.getMetaData().getColumnName(i) + " = " + rs.getObject(i));
				}
			}
			System.out.println("----------------------------------");
		}
	}
}
