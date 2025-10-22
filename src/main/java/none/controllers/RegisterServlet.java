// none/controllers/RegisterServlet.java
package none.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import none.entity.User;
import none.service.UserService;
import none.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;

	@Override
	public void init() {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fullName = req.getParameter("fullName");
		int roleId = 3; // USER mặc định
		try {
			roleId = Integer.parseInt(req.getParameter("roleId"));
		} catch (Exception ignore) {
		}

		if (username == null || username.isBlank() || password == null || password.isBlank()) {
			req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin.");
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		try {
			User u = userService.register(username, password, fullName, roleId);
			HttpSession s = req.getSession(true);
			s.setAttribute("account", u);
			resp.sendRedirect(req.getContextPath() + "/home");
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
		}
	}
}
