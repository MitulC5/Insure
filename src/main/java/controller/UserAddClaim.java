package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import dao.UserQuery;
import model.ProductClaim;

/**
 * Servlet implementation class UserAddClaim
 */
@WebServlet("/UserAddClaim")
public class UserAddClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddClaim() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			RequestDispatcher rd = null;
			String username = request.getParameter("username");
			if(!username.equals("") && username != null) {
				UserQuery userQuery = new UserQuery();
				String regProd = userQuery.fetchAllRegProducts(username);
				request.setAttribute("regProd", regProd);
				rd = request.getRequestDispatcher("./view/user/addClaimPage.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/view/index.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		String username = request.getParameter("username");
		String action = request.getParameter("action");
		String ppId = request.getParameter("ppId");
		String date = request.getParameter("date");
		String desc = request.getParameter("desc");
		
		
			try {
				if(!username.equals("") && username != null && !desc.equals("") && desc != null) {
					
					UserQuery userQuery = new UserQuery();
					
					if(userQuery.getClaimCount(Integer.parseInt(ppId)) < 3) {
						ProductClaim pc = new ProductClaim();
						pc.setAction(action);
						pc.setDate(date);
						pc.setIssueDesc(desc);
						pc.setPurchaseId(Integer.parseInt(ppId));
						pc.setStatus("pending");
						pc.setUsername(username);
						
						int count = userQuery.addClaims(pc);
						
						if(count > 0) {
							String regProd = userQuery.fetchAllRegProducts(username);
							request.setAttribute("regProd", regProd);
							request.setAttribute("message", "Success: Product added for claims");
							rd = request.getRequestDispatcher("./view/user/addClaimPage.jsp");
							rd.forward(request, response);
						}else {
							String regProd = userQuery.fetchAllRegProducts(username);
							request.setAttribute("regProd", regProd);
							request.setAttribute("message", "Error: Please try again!!");
							rd = request.getRequestDispatcher("./view/user/addClaimPage.jsp");
							rd.forward(request, response);
						}
					}else {
						String regProd = userQuery.fetchAllRegProducts(username);
						request.setAttribute("regProd", regProd);
						request.setAttribute("message", "Error: You are eligible to claim upto 3 times only");
						rd = request.getRequestDispatcher("./view/user/addClaimPage.jsp");
						rd.forward(request, response);
					}
					
				}else {
					UserQuery userQuery = new UserQuery();
					String regProd = userQuery.fetchAllRegProducts(username);
					request.setAttribute("regProd", regProd);
					request.setAttribute("message", "Error: Enter the description");
					rd = request.getRequestDispatcher("./view/user/addClaimPage.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {

				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
			}
		
	}

}