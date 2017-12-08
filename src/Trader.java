import java.sql.*;
import java.util.*;
import java.lang.*;

public class Trader {
	public static String name;
	public static int taxID;
	public static String username;
	public static String password;
	static final ArrayList<String> validTraderInputs = new ArrayList<>(Arrays.asList("deposit", "withdraw", "buy", "sell", "show balance", "transactions", "check stock", "movie info", "reviews", "top movies", "logout", "help"));

public static void openAccount() {
		//register new account
		System.out.println("===================================================================");
		System.out.println("Welcome! Enter the following information below to join Stars-R-Us!");
		System.out.println("Enter Your Name: ");
		name = UI.getInput();
		System.out.println("Enter Your Address: ");
		String pAddress = UI.getInput();
		System.out.println("Enter Your State (2 letters): ");
		String state = UI.getInput();
		System.out.println("Enter Your Phone Number: ");
		String phoneNum = UI.getInput();
		System.out.println("Enter Your Email Address: ");
		String email = UI.getInput();
		System.out.println("Enter Your TaxID: ");
		enterTaxID();
		System.out.println("Enter Your User Name: ");
		username = UI.getInput();
		while (checkUser()) {
			System.out.println("Sorry, this username is already taken! Try another one.");
			username = UI.getInput();
		}
		System.out.println("Enter Your Password: ");
		password = UI.getInput();
		System.out.println("Enter Your Social Security Number: ");
		String socialSec = UI.getInput();
		System.out.println("How much money would you like to initially deposit? (Minimum must be $1,000)");
		double deposit = Double.parseDouble(UI.getInput());
		while (deposit < 1000.0) {
			System.out.println("Insufficient Funds. Enter more money.");
			deposit = Double.parseDouble(UI.getInput());
		}

		String sql = "insert into Customer(taxID,name,username,password,address,state,phone,email,ssn) values (" +taxID+ ", '" +name+ "', '" +username+ "', '" +password+ "', '" + pAddress + "', '" + state + "', '" +phoneNum+ "', '" + email + "', '"+ socialSec + "');";
		String sql2 = "insert into marketAccount(taxID,balance) values ("+ taxID + ", "+deposit+");";
		try {
	      	JDBC.statement.executeUpdate(sql);
	      	JDBC.statement.executeUpdate(sql2);
	      	System.out.println("Success! Account Created with Initial Deposit.");
	      	traderInterface();
		} catch(SQLException e){e.printStackTrace();}

	}

	public static void enterTaxID() {
		taxID = Integer.parseInt((UI.getInput()));
		if (checkTaxID()) {
			
		} else {
			System.out.println("Invalid TaxID. Enter one that isn't taken.");
			enterTaxID();
		}
	}

	 public static boolean checkTaxID(){
		String sql = "SELECT C.taxID FROM Customer C;";
		boolean valid = true;
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	while(rs.next()){
	      		int col1 = rs.getInt("taxID"); 
		      	//System.out.println(username);
		      	if (col1 == taxID){
		      		valid = false; 	
		      	}
	      	}
		}catch(SQLException e){e.printStackTrace();}
	    return valid;
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
 		MarketAccount.getInfo(taxID);
 		StockAccount.getTID(taxID);
 		Transaction.getTID(taxID);
 		while(!input.equals("logout")){
 			System.out.println("\nWhat would you like to do? Type 'help' for options");
    		input = UI.getInput();
    		if(validTraderInputs.contains(input)){
    			switch(input){
    				case "deposit":
    				MarketAccount.deposit();
    				break;

    				case "withdraw":
    				MarketAccount.withdraw();
    				break;

    				case "buy":
    				StockAccount.buy();
    				break;

    				case "sell":
    				StockAccount.sell();
    				break;

    				case "show balance":
    				showBalance();
    				break;

    				case "transactions":
    				Transaction.printTransactions();
    				break;

    				case "check stock":
    				
    				break;

    				case "movie info":
    				Movies.listMovieInfo();
    				break;

    				case "top movies":
    				Movies.showTopMovies();
    				break;

    				case "reviews":
    				Movies.showReviews();
    				break;

    				case "logout":
                    //UI.begin();
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
		System.out.println("$" + MarketAccount.currBalance);
	}

}