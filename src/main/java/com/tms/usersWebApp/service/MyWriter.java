package com.tms.usersWebApp.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyWriter {

    private static HttpServletResponse response;

    public MyWriter(HttpServletResponse response) {
        this.response = response;
    }

    public static void println(String massage) {
        System.out.println(massage);
    }

    public void webPrintln(String massage) throws IOException{
        response.getWriter().println(massage);
    }

}
