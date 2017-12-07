import java.util.*;
import java.lang.*;
import java.sql.*;
public class Operator {
	public static void openMarket(){
		Date.nextDay();
	}

	public static void closeMarket(){

	}

	public static void setStockPrice(){

	}

	public static void setDate(){
		String newDate;
		System.out.println("What is the date today? (MM/DD/YYYY)");
		newDate = UI.getInput();
		Date.setDate(newDate);
		System.out.println("Date set to:" + Date.date);
	}
}