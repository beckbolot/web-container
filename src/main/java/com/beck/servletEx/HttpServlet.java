package com.beck.servletEx;

public abstract class HttpServlet {

    public void init(){
        System.out.println("HttpServlet default init Impl.... ");
    }

    public void service(Request request, Response response){
        String method = request.getMethod();
        if ("GET".equals(method)){
            this.doGet(request,response);
        } else if ("POST".equals(method)) {
            this.doPost(request, response);
        }else {
            throw new RuntimeException("Method not supportde");
        }

    }

    public void doGet(Request request, Response response){
        System.out.println("HttpServlet doGet default impl...");
    }

    public void doPost(Request request, Response response){
        System.out.println("HttpServlet doPost default impl...");

    }

    public void destroy(){
        System.out.println("HttpServlet destory() Default Impl....");
    }


}
