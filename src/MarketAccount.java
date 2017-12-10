import java.sql.*;
import java.util.*;
import java.lang.*;
public class MarketAccount{
	public static double currBalance;
	public static int taxID;

	public static void getInfo(int tid){
		taxID = tid; 
		getBalance();
	}

	public static void getBalance(){
		
		String sql = "SELECT balance FROM marketAccount WHERE taxID = '" + taxID + "';";
		try{
			Statement statement3 = JDBC.connection.createStatement();
	      	ResultSet rs = statement3.executeQuery(sql);
	      	if(rs.next()){
	      		currBalance = rs.getFloat("balance"); 
	      	}
	      	if (statement3 != null) {
				statement3.close();
			}
	    }catch(SQLException e){e.printStackTrace();}
	    
	}

	public static void deposit(){
		//add money to market account balance
		System.out.println("How much money would you like to deposit?");
		double money = Double.parseDouble(UI.getInput());
		if (money <= 0.0) {
			System.out.println("Invalid Deposit.");
			//money = Double.parseDouble(UI.getInput());
		}
		incrementBalance(money);
		Transaction.deposit(money);
		System.out.println("Success! Your new balance is:" + currBalance);
	}

	public static void incrementBalance(double amount){
		currBalance += amount;
		
		String sql = "update marketAccount set balance = balance + " +amount+ " WHERE taxID=" +taxID+ ";";
		try {
			Statement statement4 = JDBC.connection.createStatement();
	      	statement4.executeUpdate(sql);
	      	if (statement4 != null) {
				statement4.close();
			}
	    } catch(SQLException e){e.printStackTrace();}
	}

	public static void decreaseBalance(double amount){
		currBalance = currBalance - amount;
		String sql = "update marketAccount set balance = balance - " +amount+ " WHERE taxID=" +taxID+ ";";
		try {
			JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
		
	}

	public static boolean checkBrokeness(double amount){
		double temp = currBalance - amount;
		if(temp < 0){
			return false;
		}
		else{
			return true;
		}
	}


	public static void withdraw(){
		//subtract money from market account balance
		//check current balance so they don't overdraw
		System.out.println("How much money would you like to withdraw?");
		double money = Double.parseDouble(UI.getInput());
		while (!checkBrokeness(money)){
			System.out.println("Insufficient Funds. Enter a dollar amount greater than 0 and less than your balance ($" +currBalance+ ").");
			money = Double.parseDouble(UI.getInput());
		}
		decreaseBalance(money);
	    Transaction.withdraw(money);
	    System.out.println("Success! Your new balance is:" + currBalance);
	}

	public void accrueInterest(String marketAct){
		
	}
}