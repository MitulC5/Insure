package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductClaim;
import model.ProductPurchaseModel;

public class UserQuery {
	 Connection connection;
		
	public UserQuery() throws Exception {
		connection = DB.getConnection();
	}


	public String fetchAllProducts(String username) throws SQLException {
		String query = "Select * from product";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet results = null;
		results = ps.executeQuery();
		
		String table = "";
		table += "<table class='allProducts' border=1><thead><tr><th>Name</th><th>Model</th><th colspan='2'>YearManufactured</th></thead></tr><tbody>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td><a href='./UserAddProduct?pId=" + results.getString(1) + "&username=" +username+"'>Add Product</a></td>";
			table += "</tr>";
		}

		table += "</tbody></table>";
		return table;
	}
	
	public int addProduct(ProductPurchaseModel product) throws SQLException {
		String query = "Insert into productpurchase(productId,username,serialNo,purchaseDate) values(?, ?, ?, ?);";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, product.getProductId());
		stmt.setString(2, product.getUsername());
		stmt.setString(3, product.getSerialNo());
		stmt.setString(4, product.getPurchaseDate());
		return stmt.executeUpdate();
	}
	
	public String fetchAllRegProducts(String username) throws SQLException {
		ResultSet results = null;
		String query = "Select productpurchase.id,product.name,product.model,product.yearManufactured,productpurchase.serialNo,productpurchase.purchaseDate from product"
				+ " INNER JOIN productpurchase ON product.id = productpurchase.productId and productpurchase.username = '"+ username + "'";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		
		String table = "";
		table += "<table class='allProducts' border=1><thead><tr><th>Name</th><th>Model</th><th>YearManufactured</th><th>SerialNo</th>"
				+ "<th>PurchaseDate</th><th colspan='2'>Action</th></thead></tr><tbody>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "<td><form action='./UserAddClaim' method='post'><input checked type=\"radio\" name=\"action\" value=\"Repair\">"
					+ "  <label for=\"action\">Repair</label>"
					+ "  <input type=\"radio\" name=\"action\" value=\"Replacement\">"
					+ "  <label for=\"action\">Replacement</label></td>";
			table += "<td><input type='text' name='desc' /></td>";
			table += "<td><input type='hidden' name='username' value=" + username + " />"
					 + "<input type='hidden' name='ppId' value=" +results.getString(1) + " />"
					  + "<input type='hidden' name='date' value=" + results.getString(6) +" />"
					  		+ "<input type='submit' class='subbtn' value='Claim Product' /> </form></td>";
			table += "</tr>";
		}

		table += "</tbody></table>";
		return table;
	}
	
	public int getClaimCount(int purchaseId) throws SQLException {
		String query = "Select count(*) from product_claim where purchasedId="+purchaseId;
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet results = null;
		results = ps.executeQuery();
		results.next();
		return results.getInt(1);
	}
	
	public int addClaims(ProductClaim product) throws SQLException {
		String query = "Insert into product_claim(username,purchasedId,dateOfClaim,issueDesc,status,action) values(?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, product.getUsername());
		stmt.setInt(2, product.getPurchaseId());
		stmt.setString(3, product.getDate());
		stmt.setString(4, product.getIssueDesc());
		stmt.setString(5, product.getStatus());
		stmt.setString(6, product.getAction());
		return stmt.executeUpdate();
	}
	
	public String fetchAllClaims(String username) throws SQLException {
		String query = "Select * from product_claim where username=?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, username);
		ResultSet results = null;
		results = ps.executeQuery();
		
		String table = "";
		table += "<table class='allProducts' border=1><thead><tr><th>DateOfClaim</th><th>Description</th><th>Action</th><th>Status</th></thead></tr><tbody>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(7);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "<td><form action='./UserViewClaims' method='post'>"
					+"<input type='hidden' name='id' value=" + results.getInt(1) + " />"
					+ "<input type='hidden' name='username' value=" +results.getString(2) + " /> "
					+ "<input type='submit' class='delbtn' value='Delete' /></form></td>";
			table += "</tr>";
		}

		table += "</tbody></table>";
		return table;
	}
	
	public int deleteClaim(int id) throws SQLException {
		String query = "DELETE FROM product_claim WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		return ps.executeUpdate();
	}
	
	public String getClaimStatus(int id) throws SQLException {
		String query = "Select * from product_claim WHERE id ="+id;
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet results = null;
		results = ps.executeQuery();
		results.next();
		return results.getString(6);
	}

}
