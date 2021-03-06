import java.sql.*;
import java.util.*;
import java.lang.*;


public class Manager {
	public static String name;
	public static String username;
	public static String password;
	int MONTHSTARTDAY = 18;

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

		String sql = String.format("SELECT taxID,name FROM Customer;");
		try { 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
			while (resultSet.next()) {
				int tid = resultSet.getInt("taxID");
				String col2 = resultSet.getString("name");
	   			//int shares = resultSet.getString("numshares");
	   			if(calculateSharesTraded(tid) >= 1000){
	   				System.out.println("Tax ID: " + tid + "\t Name: " + col2);
	   			}
	    		
			}
		} catch (SQLException e){e.printStackTrace();}
	}

	public static void printCustomerInfo(int taxID, double earnings){
		String sql = String.format("SELECT * FROM Customer WHERE taxID = " + taxID+  ";");
		try { 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
			while(resultSet.next()){
	   			String name = resultSet.getString("name");
	    		System.out.println("Tax ID: " + taxID + "\t Name: " + name + "\t Earnings:$" + earnings);
			}
		} catch (SQLException e){e.printStackTrace();}
	}

	public static void genereateDTER(){
		String sql = "select * from Customer;";
		System.out.println("The following customers made over $10000 this month: ");
		try { 
			ResultSet resultSet = JDBC.statement.executeQuery(sql);
			while(resultSet.next()){
				int taxID = resultSet.getInt("taxID");
				String name = resultSet.getString("name");
	   			double earnings = calculateEarnings(taxID);
	   			//System.out.println("First" + name);
	   			if(earnings > 10000){
	   				System.out.println("Tax ID: " + taxID + "\t Name: " + name + "\t Earnings:$" + earnings);
	   			}
			}
		}catch (SQLException e){e.printStackTrace();}
	}

	public static int calculateSharesTraded(int taxID){
		String sql = "select * from Transactions where taxID = " + taxID+ " and ( (type = 'buy') or (type = 'sell'));";
		int shares=0;

		try {
	      	ResultSet rs = JDBC.statement2.executeQuery(sql);
	      	while(rs.next()){
	      		shares += rs.getInt("numshares");
	      	}
		} catch(SQLException e){e.printStackTrace();}
		//System.out.println(earned);
		return shares;
	}

	public static double calculateEarnings(int taxID){
		String sql = "select * from Transactions where taxID = " + taxID+ " and ( (type = 'buy') or (type = 'sell') or (type = 'interest'));";
		double amount=0;
		double earned=0;
		//System.out.println("second" + taxID);
		try {
	      	ResultSet rs = JDBC.statement2.executeQuery(sql);
	      	while(rs.next()){
	      		amount = rs.getDouble("amount");
	      		if(rs.getString("type") == "sell"){
	      			earned -= amount;
	      		}
	      		else{
	      			earned += amount;
	      		}
	      	}
	      	/*if(rs.last()){
	      		end = rs.getDouble("newbalance");
	      	}*/
		} catch(SQLException e){e.printStackTrace();}
		//System.out.println(earned);
		return earned;
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

	public static void addInterest(){
		String sql = "select * from marketAccount;";
		double interestEarned =0;
		try {
	      	ResultSet rs2 = JDBC.statement.executeQuery(sql);
	      	while(rs2.next()){
	      		int tid = rs2.getInt("taxID");
	      		interestEarned = getDailyBalance(tid) * .03;
	      		System.out.println("Account:" + tid + " earned:$" + interestEarned);
	      		MarketAccount.taxID = tid;
	      		MarketAccount.incrementBalance(interestEarned);
	      		Transaction.accrueInterest(interestEarned, tid, MarketAccount.currBalance);
	      	}
		} catch(SQLException e){e.printStackTrace();}
		System.out.println("Successfully applied interest to all eligible accounts.");
	}

	public static double getDailyBalance(int tid){
		String sql = "select * from Transactions where taxID = " + tid + ";";
		ArrayList<Double> dailyBalance = new ArrayList<>();
		ArrayList<String> dates = new ArrayList<>();
		ArrayList<Integer> days = new ArrayList<>();
		try {
	      	ResultSet rs = JDBC.statement2.executeQuery(sql);
	      	if(rs.next()){
	      		double temp1 = rs.getDouble("newbalance");
	      		double temp2 = rs.getDouble("amount");
	      		if(rs.getString("type").equals("sell")){
	      			dailyBalance.add(temp1 - temp2);
	      		}
	      		else{
	      			dailyBalance.add(temp1 + temp2);
	      		}
	      		if(rs.getString("date") != "3/18/2013"){
	      			dates.add(rs.getString("date"));
	      		}   		
	      		//System.out.println("daily balance 1:" + dailyBalance);
	      	}
	      	else{
	      		MarketAccount.taxID = tid;
	      		MarketAccount.getBalance();
	      		return MarketAccount.currBalance;
	      	}
	      	while(rs.next()){
	      		dailyBalance.add(rs.getDouble("newbalance"));
	      		dates.add(rs.getString("date"));
	      	}
		} catch(SQLException e){e.printStackTrace();}
		//System.out.println("daily balance end:" + dailyBalance);
		//System.out.println("dates:" + dates);

		for(int i=1; i < dates.size(); i++){
			String[] temp = dates.get(i-1).split("/");
			String[] temp2 = dates.get(i).split("/");
			int day = Integer.parseInt(temp[1]);
			int day2 = Integer.parseInt(temp2[1]);
			days.add(day2 - day);
		}
		//System.out.println("days at balance:" + days);
		//CALCULATE INTEREST
		double average =0;
		//System.out.println("average21:" + average);
		double totaldays=0;
		for (int i = 0; i < days.size(); i++){
			totaldays += days.get(i);
		}

		for(int j=0; j< days.size(); j++){
			double weight = days.get(j)/totaldays;
			average =average + dailyBalance.get(j) * weight;
			//System.out.println("average:" + average);
		}
		average = average;
		//System.out.println("daily average balance:" + average);
		return average;
	}
}