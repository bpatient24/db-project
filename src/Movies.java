import java.sql.*;
public class Movies{
	public static Connection connection; 
	public static Statement statement;

	public Movies(){
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
			connection = DriverManager.getConnection("jdbc:mysql://cs174a.engr.ucsb.edu:3306/moviesDB", "bpatient", "439");
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

	public static void listMovieInfo() {
		System.out.println("Enter the movie title:");
		String title = UI.getInput();
		String sql = "select * from Movies where title='" + title + "';";
		try{
			ResultSet rs = statement.executeQuery(sql);
			//System.out.println("ID\tTitle\tRating\tYear\n");
			if(rs.next()){
				int id = rs.getInt("id");
				double rating = rs.getDouble("rating");
				int year = rs.getInt("production_year");
				System.out.println("ID:" + id + "\nTitle:" + title + "\nRating:" + rating + "\nYear:" + year);
			}		
		}catch(SQLException e){e.printStackTrace();}
	}

	public static void showTopMovies() {
		System.out.println("Enter time interval(1970-1980):");
		String years = UI.getInput();
		String[] temp = years.split("-");
		int start = Integer.parseInt(temp[0]);
		int end = Integer.parseInt(temp[1]);
		String sql = "select * from Movies where rating=5 and production_year >= " + start + " and production_year <= " + end + ";";
		//System.out.println(sql);
		try{
			ResultSet rs = statement.executeQuery(sql);
			//System.out.println("ID\tTitle\tRating\tYear\n");
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				double rating = rs.getDouble("rating");
				int year = rs.getInt("production_year");
				System.out.println("ID:" + id + "\nTitle:" + title + "\nRating:" + rating + "\nYear:" + year);
			}			
		}catch(SQLException e){ System.out.println("Movies Sucked during that time period.");}
	}

	public static void showReviews() {
		System.out.println("Enter the movie title:");
		String title = UI.getInput();
		String sql = "select * from Movies M, Reviews R where M.id = R.movie_id and M.title = '" + title + "';";
		//System.out.println(sql);
		try{
			ResultSet rs = statement.executeQuery(sql);
			//System.out.println("ID\tTitle\tRating\tYear\n");
			if(rs.next()){
				while(rs.next()){
					String author = rs.getString("author");
					String review = rs.getString("review");
					System.out.println("Reviews for " + title + ":");
					System.out.println(author + " wrote: " + review);
				}
			}
			else{
				System.out.println("No reviews for that movie");
			}
					
		}catch(SQLException e){e.printStackTrace();}
	}
}