package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.NoSuchElementException;


@WebServlet(urlPatterns = {"/updateCart"})
public class UpdateCartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        ProductDao productDao = ProductDaoMem.getInstance();

        String productName = request.getParameter("name");


        if (productName.equals("show")) {}

        else if (productName.equals("clear")) {
            orderDataStore.getShoppingCart().clear();
        }

        else {
            String modifier = request.getParameter("mod");
            Product newProduct = productDao.getByName(productName);
            if (productName != null) {
                boolean inOrder = false;
                for (Product product : orderDataStore.getAll().keySet()) {
                    if (product.getName().equals(newProduct.getName())) {
                        if (modifier.equals("add")) {
                            orderDataStore.increaseQuantity(product);
                        } else if (modifier.equals("del")) {
                            orderDataStore.decreaseQuantity(product);
                        }
                        inOrder = true;
                    }
                }
                if (!inOrder) orderDataStore.addToCart(newProduct, 1);
            }
        }

        Gson gson = new Gson();

        String json = gson.toJson(orderDataStore.getShoppingCart());
        out.println(json);
    }

}