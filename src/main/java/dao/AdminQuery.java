package dao;

import java.sql.*;

import model.LoginModel;
import model.ProductModel;

public class AdminQuery {
	 ResultSet results = null;
	 Connection connection;
	
	public AdminQuery() throws Exception {
		connection = DB.getConnection();
	}
}