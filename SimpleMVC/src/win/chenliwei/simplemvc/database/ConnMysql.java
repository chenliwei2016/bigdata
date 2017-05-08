
package win.chenliwei.simplemvc.database;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnMysql {
	private static String ConnStr = null;

	public static void main(String[] args) throws SQLException, IOException {
		Connection test = conn();
		ResultSet rs = test.createStatement().executeQuery("select salary from employee where name='zhangwei'");
		String returnStr = "";
		while(rs.next()) returnStr = rs.getString(1);
		rs.close();
		System.out.println(returnStr);
	}
	
	public static Connection conn(){
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getProperties();
			connect = DriverManager.getConnection(ConnStr);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void getProperties() throws IOException{
		String lpath = ConnMysql.class.getResource("/").toString().substring(6) + "win/chenliwei/simplemvc/database/dbconfig.properties";
				
		InputStream in = new BufferedInputStream(new FileInputStream(lpath));

		Properties p = new Properties();
		try {
			p.load(in);
			ConnStr = "jdbc:mysql://localhost:" + p.getProperty("port") + "/" + p.getProperty("database") 
			+ "?user=" + p.getProperty("username") + "&password=" + p.getProperty("password");
			System.out.println(ConnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
