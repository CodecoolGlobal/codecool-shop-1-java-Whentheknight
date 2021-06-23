package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserOrderDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserOrder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DecimalFormat df = new DecimalFormat("0.00");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("total_price", df.format(OrderDaoMem.getInstance().getPrice()));

        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("orderId"));
        String cardName = req.getParameter("cardName");
        String cardNumber = req.getParameter("cardNumber");
        String expDate = req.getParameter("expDate");
        String cvv = req.getParameter("cvv");

        UserOrder userOrder = UserOrderDaoMem.getInstance().find(id);
        userOrder.setPaymentData(cardName, cardNumber, expDate, cvv);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_yyyy_MM_dd");
        String fileName = "order_" + String.valueOf(id) + dtf.format(LocalDate.now());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("Orders" +
                    "/" + fileName +
                    ".json"), userOrder);
        } catch (IOException ioException) {
            ioException.printStackTrace();
       }

        resp.sendRedirect(req.getContextPath() + "/confirmation");
    }

}