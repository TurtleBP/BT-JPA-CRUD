// none/controllers/ImageServlet.java
package none.controllers;

import jakarta.servlet.http.*;
import java.io.*;
import org.apache.commons.io.IOUtils;
import none.utils.Constant;

public class ImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fname = req.getParameter("fname");
		File f = new File(Constant.DIR + File.separator + fname);
		resp.setContentType("image/jpeg");
		if (f.exists())
			try (FileInputStream in = new FileInputStream(f)) {
				IOUtils.copy(in, resp.getOutputStream());
			}
	}
}
