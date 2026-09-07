package com.example.controller.admin;

import java.io.File;
import java.io.IOException;
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
import com.example.model.Product;
import com.example.service.ICategoryService;
import com.example.service.IProductService;
import com.example.service.impl.CategoryServiceImpl;
import com.example.service.impl.ProductServiceImpl;
import com.example.util.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/products", "/admin/product/add", "/admin/product/edit", "/admin/product/delete"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();
    private ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        
        if (url.contains("/admin/products")) {
            List<Product> list = productService.findAll();
            req.setAttribute("productList", list);
            req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/product/add")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/product/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("product", productService.findById(id));
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/product/delete")) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                productService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Category category = categoryService.findById(categoryId);

        // Xử lý upload ảnh
        Part part = req.getPart("images");
        String fileName = "";
        if (part != null && part.getSize() > 0) {
            String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            int extIndex = originalFileName.lastIndexOf(".");
            String ext = extIndex != -1 ? originalFileName.substring(extIndex) : "";
            fileName = System.currentTimeMillis() + ext;

            File uploadDir = new File(Constant.DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            part.write(Constant.DIR + File.separator + fileName);
        }

        if (url.contains("/admin/product/add")) {
            Product product = new Product();
            product.setProductName(productName);
            product.setPrice(price);
            product.setDescription(description);
            product.setStatus(status);
            product.setCategory(category);
            product.setImages(fileName.isEmpty() ? Constant.DEFAULT_IMAGE : fileName);

            productService.insert(product);
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        } else if (url.contains("/admin/product/edit")) {
            int id = Integer.parseInt(req.getParameter("productId"));
            Product product = productService.findById(id);
            product.setProductName(productName);
            product.setPrice(price);
            product.setDescription(description);
            product.setStatus(status);
            product.setCategory(category);
            if (!fileName.isEmpty()) {
                product.setImages(fileName);
            }

            productService.update(product);
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        }
    }
}