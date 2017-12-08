import java.sql.*;
import java.util.*;
import java.lang.*;

public class Stocks {
	//public static double openPrice;
	public static double closingPrice;
	public static double currentPrice;

	public static void setStockPrice(String stockID, double newPrice){
		String sql = "update Stock set currentprice = " +newPrice+ " WHERE stockID= '" +stockID+ "';";
		try {
	      	JDBC.statement.executeUpdate(sql);
	      	System.out.println("Success! Set " + stockID + "'s price to " + newPrice);
	    } catch(SQLException e){e.printStackTrace();}
	}

	public static double getStockPrice(String stockID){
		String sql = "select * from Stock where stockID = '" + stockID +"';";
			try{
		      	ResultSet rs = JDBC.statement.executeQuery(sql);
		      	if(rs.next()){
		      		currentPrice = rs.getDouble("currentprice");
		      		closingPrice = rs.getDouble("closingprice");
		      	}
		    }catch(SQLException e){e.printStackTrace();}
		return currentPrice;
	}
}