package win.chenliwei.jaxas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/phonefrom")
public class MobileFromQuery {
	  @Path("{f}")
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	public String QueryLocation(@PathParam("f") String phonenumber){
		return getFromAccess(phonenumber);
	}
	private String getFromAccess(String phonenumber){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "server error";
		}
		String url = "jdbc:ucanaccess://C:/Users\\chenliwe\\Downloads\\MobileBelongsLocationData.mdb";
		try {
			Connection conn = DriverManager.getConnection(url);
			ResultSet rs = conn.createStatement().executeQuery("select MobileArea from Dm_Mobile where MobileNumber = '" + phonenumber.substring(0,7) + "'");
			String returnStr = "";
			while(rs.next()) returnStr = rs.getString(1);
			rs.close();
			return returnStr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "server error";
		}
	}
	
	public static void main(String args[]){
		System.out.println(new MobileFromQuery().getFromAccess("13918704994"));
	}
}
