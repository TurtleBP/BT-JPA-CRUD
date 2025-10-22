// none/filters/NoCacheFilter.java
package none.filters;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
  @Override public void init(FilterConfig cfg){} @Override public void destroy(){}

  @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse resp=(HttpServletResponse)response;
    resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    resp.setHeader("Pragma","no-cache"); resp.setDateHeader("Expires",0);
    chain.doFilter(request, response);
  }
}
