// src/main/java/none/filters/NoCacheFilter.java
package none.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
    @Override public void init(FilterConfig filterConfig) {}
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;

        // Chặn cache ở mọi tầng
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP/1.1
        resp.setHeader("Pragma", "no-cache");                                   // HTTP/1.0
        resp.setDateHeader("Expires", 0);                                       // Proxies

        // Giảm khả năng re-use cache với ETag/Last-Modified
        resp.setHeader("Surrogate-Control", "no-store");

        chain.doFilter(request, response);
    }
}
