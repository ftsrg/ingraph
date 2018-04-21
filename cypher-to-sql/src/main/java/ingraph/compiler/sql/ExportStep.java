package ingraph.compiler.sql;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class ExportStep {
	private String exportCypherQuery, tableName;

	public ExportStep(String exportCypherQuery, String tableName) {
		this.exportCypherQuery = exportCypherQuery;
		this.tableName = tableName;
	}

	public void exportToTable(Session cypherSession, Connection sqlConnection) throws SQLException {
		try (Transaction cypherTransaction = cypherSession.beginTransaction()) {
			StatementResult cypherResult = cypherTransaction.run(exportCypherQuery);

			int keyNumber = cypherResult.keys().size();
			String insertQueryString = "INSERT INTO " + tableName + " VALUES (" + String.join(", ", Collections.nCopies(keyNumber, "?")) + ")";

			try (PreparedStatement insertStatement = sqlConnection.prepareStatement(insertQueryString)) {
				for (Record cypherRecord : (Iterable<Record>) () -> cypherResult) {
					for (int keyIndex = 0; keyIndex < keyNumber; keyIndex++) {
						int columnIndex = keyIndex + 1;
						insertStatement.setObject(columnIndex, cypherRecord.get(keyIndex).asObject());
					}
					insertStatement.addBatch();
				}
				insertStatement.executeBatch();
			}
		}
	}
}
