// none/controllers/admin/CategoryController.java
package none.controllers.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import none.entity.Category;
import none.service.impl.CategoryServiceImpl;
import none.service.ICategoryService;
import none.utils.Constant;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin-category", "/admin-category/create", "/admin-category/update",
		"/admin-category/delete", "/admin-category/edit" })
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ICategoryService service = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/admin-category/edit".equals(path)) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("category", service.findById(id));
			req.getRequestDispatcher("/views/admin/category/edit.jsp").forward(req, resp);
			return;
		}
		// list
		req.setAttribute("categorys", service.findAll());
		req.getRequestDispatcher("/views/admin/category/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		if ("/admin-category/create".equals(path))
			create(req, resp);
		else if ("/admin-category/update".equals(path))
			update(req, resp);
		else if ("/admin-category/delete".equals(path))
			delete(req, resp);
	}

	private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Category c = new Category();
		c.setName(req.getParameter("name"));
		c.setCode(req.getParameter("code"));
		c.setStatus(Integer.parseInt(req.getParameter("status")));
		Part img = req.getPart("images");
		if (img != null && img.getSize() > 0) {
			String fileName = "category_" + System.currentTimeMillis() + "_" + img.getSubmittedFileName();
			File dir = new File(Constant.DIR + File.separator + "category");
			if (!dir.exists())
				dir.mkdirs();
			img.write(new File(dir, fileName).getAbsolutePath());
			c.setImages("category/" + fileName);
		}
		service.insert(c);
		resp.sendRedirect(req.getContextPath() + "/admin-category");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Category c = service.findById(id);
		c.setName(req.getParameter("name"));
		c.setCode(req.getParameter("code"));
		c.setStatus(Integer.parseInt(req.getParameter("status")));
		Part img = req.getPart("images");
		if (img != null && img.getSize() > 0) {
			String fileName = "category_" + System.currentTimeMillis() + "_" + img.getSubmittedFileName();
			File dir = new File(Constant.DIR + File.separator + "category");
			if (!dir.exists())
				dir.mkdirs();
			img.write(new File(dir, fileName).getAbsolutePath());
			c.setImages("category/" + fileName);
		}
		service.update(c);
		resp.sendRedirect(req.getContextPath() + "/admin-category");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		service.delete(id);
		resp.sendRedirect(req.getContextPath() + "/admin-category");
	}
}
