// src/main/java/none/controllers/admin/CategoryController.java
package none.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

import none.entity.Category;               // giữ nguyên package entity theo project của bạn
import none.service.ICategoryService;
import none.service.impl.CategoryServiceImpl;
import none.utils.Constant;

@MultipartConfig
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo(); // vì map /admin/category/* => /create,/edit,...

        if ("/edit".equals(path)) {
            try {
                Integer id = Integer.parseInt(req.getParameter("id"));
                Category cat = service.findById(id);
                if (cat == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category không tồn tại");
                    return;
                }
                req.setAttribute("category", cat);
                req.getRequestDispatcher("/views/admin/category/edit.jsp").forward(req, resp);
                return;
            } catch (NumberFormatException ex) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu hoặc sai tham số id");
                return;
            }
        }

        // Mặc định: list (có hỗ trợ filter nhẹ theo q & status nếu bạn có truyền param)
        // Nếu service của bạn chưa có filter, tạm thời cứ trả full list.
        req.setAttribute("categorys", service.findAll());
        req.getRequestDispatcher("/views/admin/category/list.jsp").forward(req, resp);
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

        Category c = new Category();
        c.setName(req.getParameter("name"));
        c.setCode(req.getParameter("code"));

        try {
            c.setStatus(Integer.parseInt(req.getParameter("status")));
        } catch (NumberFormatException e) {
            c.setStatus(1); // mặc định On nếu thiếu
        }

        Part img = req.getPart("images");
        if (img != null && img.getSize() > 0) {
            String fileName = "category_" + System.currentTimeMillis() + "_" + img.getSubmittedFileName();
            File dir = new File(Constant.DIR + File.separator + "category");
            if (!dir.exists()) dir.mkdirs();
            img.write(new File(dir, fileName).getAbsolutePath());
            c.setImages("category/" + fileName);
        }

        service.insert(c);
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Category c = service.findById(id);
            if (c == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category không tồn tại");
                return;
            }

            c.setName(req.getParameter("name"));
            c.setCode(req.getParameter("code"));
            try {
                c.setStatus(Integer.parseInt(req.getParameter("status")));
            } catch (NumberFormatException e) {
                // giữ nguyên status cũ nếu không parse được
            }

            Part img = req.getPart("images");
            if (img != null && img.getSize() > 0) {
                String fileName = "category_" + System.currentTimeMillis() + "_" + img.getSubmittedFileName();
                File dir = new File(Constant.DIR + File.separator + "category");
                if (!dir.exists()) dir.mkdirs();
                img.write(new File(dir, fileName).getAbsolutePath());
                c.setImages("category/" + fileName);
            }

            service.update(c);
            resp.sendRedirect(req.getContextPath() + "/admin/category");

        } catch (NumberFormatException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu hoặc sai tham số id");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            service.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        } catch (NumberFormatException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu hoặc sai tham số id");
        }
    }
}
