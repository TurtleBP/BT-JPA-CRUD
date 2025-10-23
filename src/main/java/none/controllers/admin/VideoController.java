// src/main/java/none/controllers/admin/VideoController.java
package none.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

import none.entity.Video;
import none.entity.Category;
import none.service.IVideoService;
import none.service.ICategoryService;
import none.service.impl.VideoServiceImpl;
import none.service.impl.CategoryServiceImpl;
import none.utils.Constant;

@MultipartConfig
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IVideoService service = new VideoServiceImpl();
    private final ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo(); // "/edit" | null ...

        if ("/edit".equals(path)) {
            String id = req.getParameter("id");
            if (id == null || id.isBlank()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id");
                return;
            }
            Video v = service.findById(id);
            if (v == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video không tồn tại");
                return;
            }
            req.setAttribute("video", v);
            req.setAttribute("categories", cateService.findAll());
            req.getRequestDispatcher("/views/admin/video/edit.jsp").forward(req, resp);
            return;
        }

        // List mặc định
        req.setAttribute("videos", service.findAll());
        req.setAttribute("categories", cateService.findAll());
        req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo(); // "/create" | "/update" | "/delete"

        if ("/create".equals(path)) {
            create(req, resp);
        } else if ("/update".equals(path)) {
            update(req, resp);
        } else if ("/delete".equals(path)) {
            delete(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Video v = new Video();
        v.setVideoId(req.getParameter("videoId"));
        v.setTitle(req.getParameter("title"));
        v.setDescription(req.getParameter("description"));
        v.setActive("on".equals(req.getParameter("active")));
        v.setViews(0);

        try {
            Integer cateId = Integer.parseInt(req.getParameter("categoryId"));
            Category c = cateService.findById(cateId);
            v.setCategory(c);
        } catch (NumberFormatException ignore) {
            v.setCategory(null);
        }

        Part poster = req.getPart("poster");
        if (poster != null && poster.getSize() > 0) {
            String fileName = "video_" + System.currentTimeMillis() + "_" + poster.getSubmittedFileName();
            File dir = new File(Constant.DIR + File.separator + "videos");
            if (!dir.exists()) dir.mkdirs();
            poster.write(new File(dir, fileName).getAbsolutePath());
            v.setPoster("videos/" + fileName);
        }

        service.insert(v);
        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String id = req.getParameter("videoId");
        if (id == null || id.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số videoId");
            return;
        }

        Video v = service.findById(id);
        if (v == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video không tồn tại");
            return;
        }

        v.setTitle(req.getParameter("title"));
        v.setDescription(req.getParameter("description"));
        v.setActive("on".equals(req.getParameter("active")));

        try {
            Integer cateId = Integer.parseInt(req.getParameter("categoryId"));
            v.setCategory(cateService.findById(cateId));
        } catch (NumberFormatException ignore) { /* giữ nguyên category */ }

        Part poster = req.getPart("poster");
        if (poster != null && poster.getSize() > 0) {
            String fileName = "video_" + System.currentTimeMillis() + "_" + poster.getSubmittedFileName();
            File dir = new File(Constant.DIR + File.separator + "videos");
            if (!dir.exists()) dir.mkdirs();
            poster.write(new File(dir, fileName).getAbsolutePath());
            v.setPoster("videos/" + fileName);
        }

        service.update(v);
        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String id = req.getParameter("id");
        if (id == null || id.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số id");
            return;
        }
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }
}
