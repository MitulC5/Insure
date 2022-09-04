package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static Connection conn;
	public static Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mitul_project", "root","hello123");
		}
		return conn;
	}
}
