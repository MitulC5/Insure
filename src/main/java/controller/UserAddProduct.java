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
import model.ProductPurchaseModel;

/**
 * Servlet implementation class User
 */
@WebServlet("/UserAddProduct")
public class UserAddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddProduct() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String productId = request.getParameter("pId");
		RequestDispatcher rd = null;
		
		if(!username.equals("") && username != null && !productId.equals("") && productId != null) {
			request.setAttribute("username", username);
			request.setAttribute("productId", productId);
			rd = request.getRequestDispatcher("./view/user/registerDevice.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String productId = request.getParameter("pId");
		String serialNo = request.getParameter("serialNo");
		String purchaseDate = request.getParameter("date");
		RequestDispatcher rd = null;
		
		if(!username.equals("") && username != null && !productId.equals("") && productId != null
				&& !serialNo.equals("") && serialNo != null && !purchaseDate.equals("") && purchaseDate != null) {
			try {
				UserQuery userQuery = new UserQuery();
				ProductPurchaseModel prodPur = new ProductPurchaseModel();
				prodPur.setProductId(Integer.parseInt(productId));
				prodPur.setPurchaseDate(purchaseDate);
				prodPur.setSerialNo(serialNo);
				prodPur.setUsername(username);
				
				int rows = userQuery.addProduct(prodPur);
				if(rows == 0) {
					response.sendRedirect(request.getContextPath() + "/view/error.jsp");
				}else {
					String allProducts = userQuery.fetchAllProducts(username);
					request.setAttribute("allProducts", allProducts);
					rd = request.getRequestDispatcher("./view/user/userMainPage.jsp");
					rd.forward(request, response);
				}
				
			} catch (Exception e) {
			
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/view/index.jsp");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/view/error.jsp");
		}
		
	}

}