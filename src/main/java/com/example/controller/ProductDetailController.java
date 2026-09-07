package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.model.Product;
import com.example.service.IProductService;
import com.example.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/product/detail"})
public class ProductDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Product product = productService.findById(id);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/product-detail.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/product");
        }
    }
}