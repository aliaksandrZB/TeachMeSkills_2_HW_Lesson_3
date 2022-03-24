package com.tms.usersWebApp.servlet;

import com.tms.usersWebApp.service.MySQLConnector;
import com.tms.usersWebApp.service.MyWriter;
import com.tms.usersWebApp.service.crud.UserCRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class ServletUserCRUDService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String function = req.getParameter("function");
        String id = req.getParameter("id");
        String field = req.getParameter("field");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String age = req.getParameter("age");

        MySQLConnector connector = new MySQLConnector();
        UserCRUDService userCRUDService = new UserCRUDService(connector);
        MyWriter myWriter = new MyWriter(resp);

        switch (function) {
            case "add":
                userCRUDService.addedNewUser(name, lastname, Integer.parseInt(age), myWriter);
                break;
            case "rem":
                if(id != null) {
                    userCRUDService.removeUserById(Integer.parseInt(id), myWriter);
                } else {
                    userCRUDService.removeAllUsers(myWriter);
                }
                break;
            case "mod":
                userCRUDService.changeUserData(Integer.parseInt(id), field, name, lastname, Integer.parseInt(age), myWriter);
                break;
            case "inf":
                if (id != null) {
                    userCRUDService.displayUserInformationById(Integer.parseInt(id), myWriter);
                } else {
                    userCRUDService.displayInformationAboutAllUsers(myWriter);
                }
        }

        userCRUDService.closeConnection();

    }

}