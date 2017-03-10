/**
 * @author: ChenLiwei
 * 2017-03-10
 * DDLRetry.java
 * Comments: It is a tool for testing my oracle pl/sql package extend_purge_pkg to create
 * parallel multiply tenancies to drop partitions simultaneously
 */
package win.chenliwei.javacore.database;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleTypes;
import win.chenliwei.javacore.exception.LoggerTest;

public class DDLRetry {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	public static void main(String[] args) {
		String URL ="";
		java.util.Date workingDate = new GregorianCalendar(2017,3,13,0,0,0).getTime() ;
		ConcurrentSkipListSet<String> tenancies = new ConcurrentSkipListSet<String>();
		tenancies.add("CN000");tenancies.add("DA001");tenancies.add("DA002");tenancies.add("DA003");
		tenancies.parallelStream().forEach(tenancy->{
			Connection conn = getConnDB(URL); 
			extend(conn,tenancy,new java.sql.Date(workingDate.getTime()),10);
			try {
			conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}});
		//Wait for a while
		java.util.Date dateBefore = new GregorianCalendar(2017,3,23,0,0,0).getTime() ;
		tenancies.parallelStream().forEach(tenancy->{
			Connection conn = getConnDB(URL); 
			purge(conn, tenancy, new java.sql.Date(dateBefore.getTime()));
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	
	private static synchronized Connection getConnDB(String URL){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static synchronized void extend(Connection conn,String tenancy, Date dateFrom, int duration){
		try {
			CallableStatement stat = conn.prepareCall("{? = call partition_extend_pkg.dayfinal(?,?,?)}");
			stat.registerOutParameter(1, OracleTypes.ARRAY,"RETURN_TABLE");
			stat.setString(2, tenancy);
			stat.setDate(3, dateFrom);
			stat.setInt(4, duration);
			Array result = stat.getArray(1);
			ResultSet rs = result.getResultSet();
			while(rs.next()){
				logger.info(rs.getString(2));
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static synchronized void purge(Connection conn,String tenancy, Date dataBefore){
		try {
			CallableStatement stat = conn.prepareCall("{? = call partition_purge_pkg.dayfinal(?,?)}");
			stat.registerOutParameter(1, OracleTypes.ARRAY,"RETURN_TABLE");
			stat.setString(2, tenancy);
			stat.setDate(3, dataBefore);
			stat.execute();
			
			Array result = stat.getArray(1);
			ResultSet rs = result.getResultSet();
			while(rs.next()){
				logger.info(rs.getString(2));
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
