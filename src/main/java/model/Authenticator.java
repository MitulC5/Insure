package model;

import java.sql.ResultSet;

public class Authenticator {
 
	public static String authenticate(ResultSet rs, String username, String password) {
		try {
			String role = "";
			while(rs.next()) {
				if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					role = rs.getString(8);
				}
			}
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
}