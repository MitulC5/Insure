package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCookies
 */
@WebServlet("/DeleteCookies")
public class DeleteCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCookies() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cookie cookie = null;
         Cookie[] cookies = null;

         // Get an array of Cookies associated with the this domain
         cookies = request.getCookies();

         if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
            	cookie = cookies[i];
	            cookie.setMaxAge(0);
	            response.addCookie(cookie);
            }
            response.sendRedirect(request.getContextPath() + "/view/index.jsp");
         } else {
        	 response.sendRedirect(request.getContextPath() + "/view/index.jsp");
         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}