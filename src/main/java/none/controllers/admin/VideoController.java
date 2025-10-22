// none/controllers/admin/VideoController.java
package none.controllers.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import none.entity.Video;
import none.entity.Category;
import none.service.impl.VideoServiceImpl;
import none.service.IVideoService;
import none.service.impl.CategoryServiceImpl;
import none.service.ICategoryService;
import none.utils.Constant;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin-video", "/admin-video/create", "/admin-video/update", "/admin-video/delete",
		"/admin-video/edit" })
public class VideoController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IVideoService service = new VideoServiceImpl();
	private final ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/admin-video/edit".equals(path)) {
			String id = req.getParameter("id");
			req.setAttribute("video", service.findById(id));
			req.setAttribute("categories", cateService.findAll());
			req.getRequestDispatcher("/views/admin/video/edit.jsp").forward(req, resp);
			return;
		}
		req.setAttribute("videos", service.findAll());
		req.setAttribute("categories", cateService.findAll());
		req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		if ("/admin-video/create".equals(path))
			create(req, resp);
		else if ("/admin-video/update".equals(path))
			update(req, resp);
		else if ("/admin-video/delete".equals(path))
			delete(req, resp);
	}

	private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Video v = new Video();
		v.setVideoId(req.getParameter("videoId"));
		v.setTitle(req.getParameter("title"));
		v.setDescription(req.getParameter("description"));
		v.setActive("on".equals(req.getParameter("active")));
		v.setViews(0);

		Integer cateId = Integer.parseInt(req.getParameter("categoryId"));
		Category c = cateService.findById(cateId);
		v.setCategory(c);

		Part poster = req.getPart("poster");
		if (poster != null && poster.getSize() > 0) {
			String fileName = "video_" + System.currentTimeMillis() + "_" + poster.getSubmittedFileName();
			File dir = new File(Constant.DIR + File.separator + "videos");
			if (!dir.exists())
				dir.mkdirs();
			poster.write(new File(dir, fileName).getAbsolutePath());
			v.setPoster("videos/" + fileName);
		}
		service.insert(v);
		resp.sendRedirect(req.getContextPath() + "/admin-video");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("videoId");
		Video v = service.findById(id);
		v.setTitle(req.getParameter("title"));
		v.setDescription(req.getParameter("description"));
		v.setActive("on".equals(req.getParameter("active")));
		Integer cateId = Integer.parseInt(req.getParameter("categoryId"));
		v.setCategory(cateService.findById(cateId));

		Part poster = req.getPart("poster");
		if (poster != null && poster.getSize() > 0) {
			String fileName = "video_" + System.currentTimeMillis() + "_" + poster.getSubmittedFileName();
			File dir = new File(Constant.DIR + File.separator + "videos");
			if (!dir.exists())
				dir.mkdirs();
			poster.write(new File(dir, fileName).getAbsolutePath());
			v.setPoster("videos/" + fileName);
		}
		service.update(v);
		resp.sendRedirect(req.getContextPath() + "/admin-video");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		service.delete(id);
		resp.sendRedirect(req.getContextPath() + "/admin-video");
	}
}
