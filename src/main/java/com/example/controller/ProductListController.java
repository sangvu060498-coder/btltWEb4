package com.example.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.model.Product;
import com.example.service.IProductService;
import com.example.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/product"})
public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 6;

        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {}
        }

        int totalProducts = productService.count();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        List<Product> list = productService.findAll(page, pageSize);

        req.setAttribute("productList", list);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/views/product-user-list.jsp").forward(req, resp);
    }
}