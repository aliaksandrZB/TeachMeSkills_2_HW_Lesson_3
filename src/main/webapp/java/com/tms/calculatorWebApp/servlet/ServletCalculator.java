package com.tms.calculatorWebApp.servlet;

import com.tms.calculatorWebApp.model.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class ServletCalculator extends HttpServlet {

    public static String bugReport = null;
    public static String sign = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String operation = req.getParameter("operation");

        double sum = Calculator.performOperation(Double.parseDouble(num1), Double.parseDouble(num2), operation);

        if (bugReport == null) {
            resp.getWriter().println(Double.parseDouble(num1) + " " + sign + " " +
                                     Double.parseDouble(num2) + " = " +
                                     sum);
            sign = null;
        } else {
            resp.getWriter().println(bugReport);
            bugReport = null;
        }
    }

}
