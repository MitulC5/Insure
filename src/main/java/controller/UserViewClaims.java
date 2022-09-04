package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import dao.UserQuery;

/**
 * Servlet implementation class UserViewClaims
 */
@WebServlet("/UserViewClaims")
public class UserViewClaims extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserViewClaims() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = null;
		String username = request.getParameter("username");
		if(!username.equals("") && username != null) {
			UserQuery userQuery;
			try {
				userQuery = new UserQuery();
				String claims = userQuery.fetchAllClaims(username);
				request.setAttribute("allClaims", claims);
				rd = request.getRequestDispatcher("./view/user/viewClaims.jsp");
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/view/index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		if(id != null) {
			
			String status;
			try {
				UserQuery userQuery = new UserQuery();
				status = userQuery.getClaimStatus(Integer.parseInt(id));
				if(status.equals("pending")) {
					userQuery.deleteClaim(Integer.parseInt(id));
					String claims = userQuery.fetchAllClaims(username);
					request.setAttribute("message", "Success: Product deleted from claims");
					request.setAttribute("allClaims", claims);
					rd = request.getRequestDispatcher("./view/user/viewClaims.jsp");
					rd.forward(request, response);
				}else {
					String claims = userQuery.fetchAllClaims(username);
					request.setAttribute("message", "Error: Product should be in pending status to delete");
					request.setAttribute("allClaims", claims);
					rd = request.getRequestDispatcher("./view/user/viewClaims.jsp");
					rd.forward(request, response);
				}
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
			} catch (SQLException e) {
			
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
				e.printStackTrace();
			} catch (Exception e) {
			
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
				e.printStackTrace();
			}
			
		}
	}

}