package br.com.droid.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectarBanco {
	private static final String url = "jdbc:mysql://localhost/dbeasyroom";
	private static final String usuario ="root";
	private static final String senha ="root";
	
	public static Connection GetConnection() throws SQLException{
		   
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			  
			e.printStackTrace();
		}
		return DriverManager.getConnection(url,usuario,senha);
		
	}

}
