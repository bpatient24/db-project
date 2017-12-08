import java.sql.*;
import java.util.*;
import java.lang.*;


public class Manager {
	public static String name;
	public static String username;
	public static String password;

	public static boolean login(){
		getUser();
		if(checkUser()){
			getPassword();
			return checkPassword();
		}
		else{
			System.out.println("Invalid username, try again.");
			login();
		}	
		return false;	
	}

	public static void getName(){
		String sql = "SELECT M.name FROM Manager M WHERE M.username = '" + username + "';";
		//System.out.println(sql);
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		name = rs.getString("name"); 
		      
	      	}
	    }catch(SQLException e){e.printStackTrace();}
	}

	public static void getUser(){
		System.out.println("Enter your username:");
		username = UI.getInput();
    	//System.out.println(username);
	}

	public static boolean checkUser(){
		String sql = "SELECT M.username FROM Manager M WHERE M.username = '" + username + "';";
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
		String sql = "SELECT M.username, M.password FROM Manager M WHERE M.username = '" + username + "' AND M.password = '" + password + "';";
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

	public static void addInterest(){
		//For all market accousernamets, add the appropriate amousernamet of monthly interest to the balance. 
		//This is usually done at the end of a month.
	}

	public static void generateStatement() {
		//Scanner scan = new Scanner(System.in);
		System.out.println("==============================================================");
		System.out.println("Select a Customer TaxID to generate a Monthly Statement for their Accounts: ");
		//List<Integer> validTaxIDs = new ArrayList<>();
		String input = "";
		String sql = "SELECT * FROM Customer;";
		try {
			System.out.println("taxID \t  Name");
			System.out.println("==================="); 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
			while (resultSet.next()) {
				int col1 = resultSet.getInt("taxID");
				//validTaxIDs.add(col1);
	   			String col2 = resultSet.getString("name");
	    		System.out.println(col1 + "\t" + col2);
			}
			System.out.println("===================");
		} catch (SQLException e){e.printStackTrace(); }

		input = UI.getInput();			// input = Customer (taxID) to generate Monthly Statement for
										// TODO: Check if it's a valid input
		System.out.println("");
		System.out.print("Generating a monthly report for, ");

		String sql2 = String.format("SELECT * FROM Customer WHERE taxID='%s';", input);		
		try { 
			ResultSet resultSet = JDBC.statement.executeQuery(sql2);
			while (resultSet.next()) {
				String col1 = resultSet.getString("name");
	   			String col2 = resultSet.getString("email");
	    		System.out.println("Name: " + col1 + " \t Email: " + col2);
			}
		} catch (SQLException e){e.printStackTrace(); System.out.println("Entered an invalid TaxID"); }
		System.out.println("=====================================");

		// TODO: Generate A List of Transactions for Every Account the Customer Owns
		String sql3 = String.format("SELECT * FROM stockAccount WHERE taxID='%s';", input);
		try {
			System.out.println("======================");
			System.out.println("saID \t #shares \t stockID \t boughtAt");
			ResultSet resultSet = JDBC.statement.executeQuery(sql3);
		
			while (resultSet.next()) {
				int col1 = resultSet.getInt("saID");
				int col2 = resultSet.getInt("numShares");
	   			String col3 = resultSet.getString("stockID");
	   			float col4 = resultSet.getFloat("boughtAt");
	    		System.out.println(col1 + "\t" + col2 + "\t" + col3 + "\t" + col4);
			}
		} catch (SQLException e){e.printStackTrace(); }

	}

	public static void listActiveCustomers() {

		String sql = String.format("SELECT * FROM Customer;");
		
		try { 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
		 

			while (resultSet.next()) {
				int col1 = resultSet.getInt("taxID");
	   			String col2 = resultSet.getString("name");
	    		System.out.println("Tax ID: " + col1 + "\t Name: " + col2);
			}
		} catch (SQLException e){e.printStackTrace();}
	}

	public static void genereateDTER(){

	}
	
	public static void showCustomerReport() {
		System.out.println("==================================================");
		System.out.println("Select a Customer TaxID to generate a Customer Report for: ");
		String input = "";
		String sql = "SELECT * FROM Customer;";
		try {
			System.out.println("taxID \t  Name");
			System.out.println("==================="); 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
			while (resultSet.next()) {
				int col1 = resultSet.getInt("taxID");
	   			String col2 = resultSet.getString("name");
	    		System.out.println(col1 + "\t" + col2);
			}
			System.out.println("===================");
		} catch (SQLException e){e.printStackTrace();}

		input = UI.getInput();

		System.out.println("Generating a list of the Customer's accounts... ");
		System.out.println("====================================================");
		System.out.println("Stock Account Info for TaxID: " + input);

		String sql2 = String.format("SELECT * FROM stockAccount WHERE taxID='%s';", input);		
		try { 
			
			ResultSet resultSet = JDBC.statement.executeQuery(sql2);
			while (resultSet.next()) {
				String col1 = resultSet.getString("stockID");
	   			int col2 = resultSet.getInt("numshares");
	   			float col3 = resultSet.getFloat("boughtAt");
	    		System.out.println("Stock ID: " + col1 + " \t # of Shares: " + col2 + " \t boughtAt: " + col3);
			}

		} catch (SQLException e){e.printStackTrace(); System.out.println("Entered an invalid TaxID"); }

		System.out.println("====================================================");
		System.out.println("Market Account Info for TaxID: " + input);
		String sql3 = String.format("SELECT * FROM marketAccount WHERE taxID='%s';", input);		
		try { 
			
			ResultSet resultSet = JDBC.statement.executeQuery(sql3);
			while (resultSet.next()) {
				int col1 = resultSet.getInt("taxID");
	   			int col2 = resultSet.getInt("maID");
	   			float col3 = resultSet.getFloat("balance");
	    		System.out.println("TaxID: " + col1 + " \t MarketID: " + col2 + " \t Balance: " + col3);
			}

		} catch (SQLException e){e.printStackTrace(); System.out.println("Entered an invalid TaxID"); }

	}

	public static void deleteTransactions(){
		String sql = "delete from Transactions;";
		try {
	      	JDBC.statement.executeUpdate(sql);
	      	System.out.println("Successfully deleted transactions.");
		} catch(SQLException e){e.printStackTrace();}
	}
}