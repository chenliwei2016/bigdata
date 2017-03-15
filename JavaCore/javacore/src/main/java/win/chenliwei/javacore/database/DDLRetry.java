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

public class DDLRetry {
	private static final Logger logger = LoggerFactory.getLogger(DDLRetry.class);
	public static void main(String[] args) {
		String URL ="";
		if(args.length != 2) {
			System.out.println("This program needs 2 parameters: date yyyy-mm-dd and e/p for extend/purge");
			System.exit(0);
		}
		String[] pDate = args[0].split("-");
		java.util.Date workingDate = new GregorianCalendar(Integer.parseInt(pDate[0]),Integer.parseInt(pDate[1])-1,Integer.parseInt(pDate[2]),0,0,0).getTime() ;
		ConcurrentSkipListSet<String> tenancies = new ConcurrentSkipListSet<String>();
		tenancies.add("CN000");tenancies.add("DA001");tenancies.add("DA002");tenancies.add("DA003");
		tenancies.add("DB001");tenancies.add("DB002");tenancies.add("DB003");tenancies.add("DB004");tenancies.add("DB005");
		tenancies.add("DB006");tenancies.add("DB007");tenancies.add("DB008");tenancies.add("DB009");tenancies.add("DB010");
		if(args[1].equalsIgnoreCase("e")){
			tenancies.parallelStream().forEach(tenancy->{
				Connection conn = getConnDB(URL); 
				extend(conn,tenancy,new java.sql.Date(workingDate.getTime()),10);
				try {
				conn.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}});
		} else {
			if(!args[1].equalsIgnoreCase("p")){
				System.out.println("cannot recognize " + args[1] + ", either e or p");
				System.exit(0);
			}
			tenancies.parallelStream().forEach(tenancy->{
				Connection conn = getConnDB(URL); 
				purge(conn, tenancy, new java.sql.Date(workingDate.getTime()+1));
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
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
