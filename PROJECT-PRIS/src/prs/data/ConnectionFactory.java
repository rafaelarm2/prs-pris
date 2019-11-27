package prs.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	//SET THE VARIABLES TO CONNECT
	private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_SPR";
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String usuario = "sa";
	private static final String senha = "Sqlserverrm2";
	
	public static Connection getConnection() throws SQLException {
		try {

			Class.forName(driver);
			
			//DEFINE THE CONNECTION
			return DriverManager.getConnection(url, usuario, senha);
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		} catch (SQLException e){
			throw new SQLException(e.getMessage());
		}
	}

	public static void closeConnection(Connection connect) throws SQLException {
		connect.close();
	}
}
