package quickstart.datastax.cassandra.run;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;

/*
 * CREATE KEYSPACE IF NOT EXISTS test WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
 * CREATE TABLE test (
		name text,
		number text,
		PRIMARY KEY ((name), number)
	);

	INSERT INTO test (name, number)
	VALUES ('one', '1');
	INSERT INTO test (name, number)
	VALUES ('two', '2');
	INSERT INTO test (name, number)
	VALUES ('three', '3');
	INSERT INTO test (name, number)
	VALUES ('four', '4');
	INSERT INTO test (name, number)
	VALUES ('four', '1+3');
	INSERT INTO test (name, number)
	VALUES ('four', '2+2');
	INSERT INTO test (name, number)
	VALUES ('five', '5');
	INSERT INTO test (name, number)
	VALUES ('six', '6');
	INSERT INTO test (name, number)
	VALUES ('seven', '7');
	INSERT INTO test (name, number)
	VALUES ('seven', '1+6');
	INSERT INTO test (name, number)
	VALUES ('seven', '2+5');
	INSERT INTO test (name, number)
	VALUES ('seven', '3+4');
	*/

public class App {

	private final static Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	private final static Session session = cluster.connect("test");
	private final static int pageSize = 2;
	private final static String selectAll = "SELECT * FROM test WHERE name = ?";

	public static void main(String[] args) {
		
		paginateResults(pageSize);

		cluster.close();
	}

	private static void paginateResults(int fetchSize) {
		ResultSet results = null;
		PreparedStatement statement = session.prepare(selectAll);  
		BoundStatement boundStatement = new BoundStatement(statement);
		boundStatement.bind("seven");
		boundStatement.setFetchSize(fetchSize);
		
		for (int i = 1; ; i++) {
			System.out.println("Page " + i + "\n-------");
			PagingState thisPageState = getPagingState(results);
			
			if (thisPageState != null) {
				boundStatement.setPagingState(thisPageState);
			}

			results = session.execute(boundStatement);
			PagingState nextPageState = getPagingState(results);
			if (thisPageState != null)
				System.out.format("(%s %s\n","thisPageState:", thisPageState.toString() + ")");
			
			
			int remaining = results.getAvailableWithoutFetching();
			for (Row row : results) {
				System.out.format("%s %s\n", row.getString("name"), row.getString("number"));
				if (--remaining == 0) {
					break;
				}
			}
			if (nextPageState != null)
				System.out.format("(%s %s\n","nextPageState:", nextPageState.toString() + ")");
			thisPageState = nextPageState;
			if (nextPageState == null)
				break;
		}
	}

	private static PagingState getPagingState(ResultSet results) {
		PagingState ps = null;
		if (results != null) {
			ps = results.getExecutionInfo().getPagingState();
		}
		return ps;
	}

}
