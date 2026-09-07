package com.example.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.example.model.Category;
import com.example.service.ICategoryService;
import com.example.service.impl.CategoryServiceImpl;
import com.example.util.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
@WebServlet(urlPatterns = { 
    "/admin/categories", 
    "/admin/category/add", 
    "/admin/category/insert",
    "/admin/category/edit", 
    "/admin/category/update", 
    "/admin/category/delete" 
})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("/admin/categories")) {
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            cateService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();

        if (uri.contains("/admin/category/insert")) {
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String images = req.getParameter("images");

            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setStatus(status);

            handleUploadImage(req, category, images, null);
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (uri.contains("/admin/category/update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String images = req.getParameter("images");

            Category category = cateService.findById(categoryid);
            String oldImage = category.getImages();
            category.setCategoryname(categoryname);
            category.setStatus(status);

            handleUploadImage(req, category, images, oldImage);
            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    private void handleUploadImage(HttpServletRequest req, Category category, String linkImage, String oldImage) {
        String uploadPath = Constant.DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            Part part = req.getPart("images1");
            if (part != null && part.getSize() > 0) {
                if (oldImage != null && !oldImage.startsWith("http")) {
                    deleteFile(uploadPath + File.separator + oldImage);
                }

                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = filename.substring(filename.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                part.write(uploadPath + File.separator + fname);
                category.setImages(fname);
            } else if (linkImage != null && !linkImage.trim().isEmpty()) {
                category.setImages(linkImage);
            } else {
                category.setImages(oldImage != null ? oldImage : Constant.DEFAULT_IMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            category.setImages(oldImage != null ? oldImage : Constant.DEFAULT_IMAGE);
        }
    }

    private void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}