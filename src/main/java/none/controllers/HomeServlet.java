// none/controllers/HomeServlet.java
package none.controllers;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException;

public class HomeServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
  }
}
