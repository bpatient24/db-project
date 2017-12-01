import java.sql.*;
public class JDBC {
	public static Connection connection; 
	public static Statement statement;

	public static void initializeDB(){
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://cs174a.engr.ucsb.edu:3306/<database>", "bpatient", "439");
		statement = connection.createStatement();
	}

	public static void endSession(){
		if (statement != null) {
		    statement.close();
		}

		if (connection != null) {
		    connection.close();
		}
	}
}