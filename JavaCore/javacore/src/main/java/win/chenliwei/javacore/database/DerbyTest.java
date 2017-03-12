/**
 * @author: ChenLiwei
 * 2017-03-12
 * DerbyTest.java
 * Comments:It is to test the connection to Derby database using JDBC
 */
package win.chenliwei.javacore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentSkipListSet;

public class DerbyTest {

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//String dbURL = "jdbc:derby:C:\\Users\\chenliwe\\Documents\\derbyDatabase\\javacore;user=test;password=test";
		String dbURL = "jdbc:derby://localhost:1527/javacore;user=test;password=test";
		ConcurrentSkipListSet<Integer> threads = new ConcurrentSkipListSet<Integer>();
		for(int i=1;i<100;i++) threads.add(i);
		threads.parallelStream().forEach(thread -> {insertGreetings(dbURL,"thread " + thread + " greetings");});
		ResultSet rs = getConnDB(dbURL).createStatement().executeQuery("select * from greetings");
		while(rs.next()) System.out.println(rs.getString(1));
		rs.close();
		
	}
	private static synchronized Connection getConnDB(String URL) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection conn = null;
		//Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); //it is another dirver
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		try {
			conn = DriverManager.getConnection(URL);
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	private static synchronized void insertGreetings(String dbURL, String statement){

		Connection conn;
		try {
			conn = getConnDB(dbURL);
			Statement stat = conn.createStatement();
			stat.executeUpdate("insert into greetings values('" + statement +"')");
			conn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
