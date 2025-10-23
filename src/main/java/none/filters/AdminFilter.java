// src/main/java/none/filters/AdminFilter.java
package none.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import none.entity.User;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override public void init(FilterConfig cfg) {}
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession s = req.getSession(false);
        User u = (s == null) ? null : (User) s.getAttribute("account");

        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if (u.getRoleId() == null || u.getRoleId() != 1) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập.");
            return;
        }
        chain.doFilter(request, response);
    }
}
