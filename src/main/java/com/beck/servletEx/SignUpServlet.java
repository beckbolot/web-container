package com.beck.servletEx;

import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("SignUpServlet init() called....");
    }

    @Override
    public void doGet(Request request, Response response) {
        PrintWriter out = response.getPrintWriter();
        out.println("<html><body>");
        out.println("<form method=\"post\">");
        out.println("First Name: <input type=\"text\" id=\"fname\" name=\"fname\" value=\"Beck\"><br>");
        out.println("Last Name: <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Ege\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</body></html>");
    }

    @Override
    public void doPost(Request request, Response response) {
        PrintWriter out = response.getPrintWriter();
        out.println("<html><body>");
        out.println("First Name: " + request.getRequestParameter("fname"));
        out.println("<br>");
        out.println("Last Name: " + request.getRequestParameter("lname"));
        out.println("<br>");
        out.println("doPost() in SignupServlet.........");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("close connections in SignupServlet destory() *******");
    }
}
