import java.sql.*;
public class JDBC {
	public static Connection connection; 
	public static Statement statement;

	public JDBC(){
		initializeDriver();
		initializeConnection();
		initializeStatement();
	}

	public void initializeDriver(){
		try{
	      Class.forName("com.mysql.jdbc.Driver");
	    }catch(ClassNotFoundException e){e.printStackTrace();}
		
	}
		
	public void initializeConnection(){
		try{
			connection = DriverManager.getConnection("jdbc:mysql://cs174a.engr.ucsb.edu:3306/bpatientDB", "bpatient", "439");
		}catch(SQLException e){e.printStackTrace();}
	}

	public void initializeStatement(){
		try{
			statement = connection.createStatement();
		}catch(SQLException e){e.printStackTrace();}
	}

	public void endSession(){
		if (statement != null) {
			try{
				statement.close();
			}catch(SQLException e){e.printStackTrace();}  
		}

		if (connection != null) {
			try{
				connection.close();
			}catch(SQLException e){e.printStackTrace();}
		}
	}
}