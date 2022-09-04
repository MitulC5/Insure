package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminQuery;
import dao.DB;
import dao.DBQuery;
import dao.UserQuery;
import helper.Validator;
import model.Authenticator;
import model.LoginModel;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		if(Validator.isValidCredentials(username, password)) { // user entered some value
			try {
				DBQuery dbQuery = new DBQuery();
				AdminQuery adminQuery = new AdminQuery();
				UserQuery userQuery = new UserQuery();
				String role = Authenticator.authenticate(dbQuery.doRead(username), username, password);
				if(!role.isEmpty()) {
					if(role.equals("admin")) {
						
					}else {
						Cookie uNameCookie = new Cookie("username",username);
						Cookie pCookie = new Cookie("password",password);
						String allProducts = userQuery.fetchAllProducts(username);
						response.addCookie(uNameCookie);
						response.addCookie(pCookie);
						request.setAttribute("allProducts", allProducts);
						rd = request.getRequestDispatcher("./view/user/userMainPage.jsp");
						rd.forward(request, response);
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/view/error.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/view/error.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			response.sendRedirect(request.getContextPath() + "/view/error.jsp"); 
		}
	}

}
