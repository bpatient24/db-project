import java.sql.*;
public class Customer {
	public static String name;
	public static int taxID;
	public static String username;
	public static String password;

	public static void openAccount(){
		//register new account
	}

	public static void login(){
		getUser();
		if(checkUser()){
			getPassword();
			if(checkPassword()){
				//open trader menu
			}
		}
		else{
			System.out.println("Invalid username, try again.");
			login();
		}	
	}
	
	public static void getInfo(){
		String sql = "SELECT C.name, C.taxID FROM Customer M WHERE C.username = '" + username + "';";
		//System.out.println(sql);
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		name = rs.getString("name");
	      		taxID = rs.getInt("taxID"); 
		      
	      	}
	    }catch(SQLException e){e.printStackTrace();}
	}

	public static void getUser(){
		System.out.println("Enter your username:");
		username = UI.getInput();
    	//System.out.println(username);
	}

	public static boolean checkUser(){
		String sql = "SELECT C.username FROM Customer C WHERE C.username = '" + username + "';";
		//System.out.println(sql);
		boolean valid = false;
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		String testUser = rs.getString("username"); 
		      	//System.out.println(username);
		      	if(testUser.equals(username)){
		      		valid = true; 	
		      	}
	      	}
			else{
				valid = false;
			}	
	    }catch(SQLException e){e.printStackTrace();}
	    return valid;
	}

	public static void getPassword(){
		System.out.println("Enter your password:");
	    password = UI.getInput();	
	    //System.out.println(password);
	}

	
	public static boolean checkPassword(){
		String sql = "SELECT C.username, C.password FROM Customer C WHERE C.username = '" + username + "' AND C.password = '" + password + "';";
		//System.out.println(sql);
		boolean valid = false;
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		String testUser = rs.getString("username"); 
	      		String testPassword = rs.getString("password");
		      	//System.out.println(username);
		      	if(testUser.equals(username) && testPassword.equals(password)){
		      		valid = true; 
		      		getName();	
		      	}		
	      	}
			else{
				valid = false;
			}	
	    }catch(SQLException e){e.printStackTrace();}
	    return valid;
	}
}