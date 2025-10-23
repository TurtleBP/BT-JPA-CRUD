// none/controllers/LoginServlet.java
package none.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import none.entity.User;
import none.service.UserService;
import none.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private UserService userService;
  @Override public void init(){ userService = new UserServiceImpl(); }

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
  }

  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String username = trim(req.getParameter("username"));
    String password = trim(req.getParameter("password"));
    if (isBlank(username)||isBlank(password)){
      req.setAttribute("error","Vui lòng nhập đầy đủ thông tin.");
      req.getRequestDispatcher("/views/login.jsp").forward(req, resp); return;
    }
    User user = userService.login(username, password);
    if (user==null){
      req.setAttribute("error","Tên đăng nhập hoặc mật khẩu không đúng.");
      req.getRequestDispatcher("/views/login.jsp").forward(req, resp); return;
    }
    HttpSession session = req.getSession(true);
    session.setAttribute("account", user);
    session.setMaxInactiveInterval(30*60);

    if (user.getRoleId()!=null && user.getRoleId()==1) resp.sendRedirect(req.getContextPath()+"/admin/home");
    else resp.sendRedirect(req.getContextPath()+"/home");
  }
  private boolean isBlank(String s){ return s==null || s.isBlank(); }
  private String trim(String s){ return s==null?null:s.trim(); }
}
