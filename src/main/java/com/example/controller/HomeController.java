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

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> top10Products = productService.findTop10Latest();
        req.setAttribute("top10Products", top10Products);
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}