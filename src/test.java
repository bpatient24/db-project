import java.sql.*;
Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:mysql://cs174a.engr.ucsb.edu:3306/<database>", "bpatient", "439");
Statement statement = connection.createStatement();
String sql = "SQL QUERY HERE"
ResultSet resultSet = statement.executeQuery(sql);

while (resultSet.next()) {
    String col1 = resultSet.getString("columnName1");
    int col2 = resultSet.getInt("columnName2");
    int col3 = resultSet.getInt("columnName3");
    //get type ("attribute name")

    System.out.println(col1 + "\t" + col2 + "\t" + col3);
    // Perform other operations if needed
}    

if (statement != null) {
    statement.close();
}

if (connection != null) {
    connection.close();
}