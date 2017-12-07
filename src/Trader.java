import java.sql.*;
import java.util.*;
import java.lang.*;

public class Trader {
	public static String name;
	public static int taxID;
	public static String username;
	public static String password;
	static final ArrayList<String> validTraderInputs = new ArrayList<>(Arrays.asList("deposit", "withdraw", "buy", "sell", "show balance", "transactions", "check stock", "movie info", "logout", "help"));


	public static void openAccount(){
		//register new account
	}

	public static void login(){
		getUser();
		if(checkUser()){
			getPassword();
			if(checkPassword()){
				traderInterface();
			}
			else{
				System.out.println("Invalid password");
				UI.accountCheck();
			}
		}
		else{
			System.out.println("Invalid username, try again.");
			login();
		}	
	}

	public static void traderInterface(){
		System.out.println("Hello, " + name + ".");
 		System.out.println("Welcome back to the Stars R Us Trader Interface");
 		String input = "";
 		while(!input.equals("logout")){
 			System.out.println("\n What would you like to do? Type 'help' for options");
    		input = UI.getInput();
    		if(validTraderInputs.contains(input)){
    			switch(input){
    				case "deposit":
    				
    				break;

    				case "withdraw":
    				
    				break;

    				case "buy":
    				
    				break;

    				case "sell":
    				
    				break;

    				case "show balance":
    				showBalance();
    				break;

    				case "transactions":
    				
    				break;

    				case "check stock":
    				
    				break;

    				case "movie info":
    				
    				break;

    				case "logout":
                    UI.begin();
    				break;

    				case "help":
    				UI.help(validTraderInputs);
                    break;
    			}	
    		}
            else{
                System.out.println("Invalid input, type 'help' for input options");
            }
    	}
 	}
	
	public static void getInfo(){
		String sql = "SELECT C.name, C.taxID FROM Customer C WHERE C.username = '" + username + "';";
		//System.out.println(sql);
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		name = rs.getString("name");
	      		taxID = rs.getInt("taxID"); 
		      	//System.out.println(name);
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
		      		getInfo();	
		      	}		
	      	}
			else{
				valid = false;
			}	
	    }catch(SQLException e){e.printStackTrace();}
	    return valid;
	}
	
	public static void showBalance() {
		String sql = "SELECT MA.balance FROM marketAccount MA WHERE MA.taxID = '" + taxID + "';";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		float balance = rs.getFloat("balance"); 
	      		System.out.println("Your current balance is: " + balance);
	      	}
	    }catch(SQLException e){e.printStackTrace();}
	}

	public static void showTransactionHistory() {
		//String sql = "";
		//JDBC.connection.statement.executeQuery(sql);
	}

	public static void showStockInfo(String symbol) {
		//String sql = "";
		//JDBC.connection.statement.executeQuery(sql);
	}

	public static void listMovieInfo() {
		//String sql = "";
		//JDBC.connection.statement.executeQuery(sql);
	}

	public static void showTopMovies() {
		//String sql = "";
		//JDBC.connection.statement.executeQuery(sql);
	}

	public static void showReviews(String movieName) {
		//String sql = "";
		//JDBC.connection.statement.executeQuery(sql);
	}
}