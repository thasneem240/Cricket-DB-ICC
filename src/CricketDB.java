import java.io.*;
import java.sql.*;

public class CricketDB 
{

	public static void main(String[] args) 
	{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int match_Id;
		String match_Name;
		
		
		
		final String jdbcURL = "jdbc:mysql://localhost:3306/CricketDB_20535155";
		final String user = "thasneem";
		final String password = "Thasneem@123";
		
		try 
		{
			// register MySQL thin driver with DriverManager service
			// It is optional for JDBC4.x version
			
			Class.forName("com.mysql.jdbc.Driver");
			
			
			// Establish the Connection
			
			connection = DriverManager.getConnection(jdbcURL,user,password);
			
			statement = connection.createStatement();
			String sql1 = "SELECT * FROM Matche";
			
			resultSet = statement.executeQuery(sql1);
			
			// Display All Values
			
			while(resultSet.next()) 
			{
				match_Id = resultSet.getInt("match_ID");
				match_Name = resultSet.getString("match_name");
				
				System.out.println(" match_Id : " + match_Id);
				System.out.println(" match_Name : " + match_Name);
			}
			
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}