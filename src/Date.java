import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.sql.*;

public class Date{
	public static String date;
	public static int month;
	public static int day;
	public static int year;

	public static void getDate(){
		String sql = "SELECT D.date FROM Date D;";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		date = rs.getString("date");
	      	}  	
	    }catch(SQLException e){e.printStackTrace();}
	    parseDate(date);
	}

	public static void printDate(){
		getDate();
		System.out.println(date);
	}

	public static void updateDateTable(){
		date = month + "/" + day + "/" + year; 
		String sql = "update Date set date = '" + date + "';";
		try{
	      	JDBC.statement.executeUpdate(sql);
	    }catch(SQLException e){e.printStackTrace();}
	}

	public static void setDate(String newDate){
		parseDate(newDate);
		updateDateTable();
	}

	public static void nextDay(){
		if(day < 31){
			day++;
		}
		else{
			day = 1;
			if(month < 12){
				month++;
			}
			else{
				month = 1;
				year++;
			}
		}
		
		updateDateTable();
	}

	public static void parseDate(String in){
		String[] temp = in.split("/");
		month = Integer.parseInt(temp[0]);
		day = Integer.parseInt(temp[1]);
		year = Integer.parseInt(temp[2]);
	}

}