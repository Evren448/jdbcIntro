package jdbcIntro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	
	
	public static void main(String[] args) throws SQLException {
		System.out.println("sasa");
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement = null;
		
		try {	
			
			connection = helper.getConnection();
			statement = connection.prepareStatement("Insert into city (Name,CountryCode,District,Population) values(?,?,?,?)");
			statement.setString(1, "Evren");
			statement.setString(2, "TUR");
			statement.setString(3, "Evren");
			statement.setInt(4, 555555);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			helper.showError(e);
			
		}
		finally {
			statement.close();
			connection.close();
			
		}
	}
	
	public static void Select() throws SQLException {
		
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement = null;
		ResultSet resultSet;
		
		try {	
			
			connection = helper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select Code,Name,Continent,Region from country");
			ArrayList<Country> countries = new ArrayList<>();
			while(resultSet.next()) {
				countries.add(new Country(
						resultSet.getString("Code"),
						resultSet.getString("Name"),
						resultSet.getString("Continent"),
						resultSet.getString("Region")));
			}
			
			System.out.println(countries.size());
			
		} catch (SQLException e) {
			
			helper.showError(e);
			
		}
		finally {
			
			connection.close();
			
		}
		
	}
}
