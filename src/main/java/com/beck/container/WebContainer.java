package com.beck.container;

import com.beck.servletEx.HttpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WebContainer {

    private final int port;
    private final String configFileName;

    private Map<String,HttpServlet> handlers = new HashMap<>();

    public WebContainer(int port, String configFileName) {
        this.port = port;
        this.configFileName = configFileName;
    }

    public void begin() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            Thread socketHandler = new SocketHandler(socket,handlers);
            socketHandler.start();
        }
    }

    private void loadProperties(){
        InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (input == null){
            throw new RuntimeException("Unable find file: " + configFileName);
        }

        Properties properties = new Properties();
        try {
            properties.load(input);
            properties.forEach((key,value)-> {
                HttpServlet servlet = getServletInstance((String) value);
                servlet.init();
                handlers.put((String) key,servlet);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpServlet getServletInstance(String className){
        try {
            return (HttpServlet) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }




    public static void main(String[] args) throws IOException {
        WebContainer webContainer = new WebContainer(8888, "config.properties");
        webContainer.loadProperties();
//        webContainer.handlers.forEach((key,value)->{
//            System.out.println(key);
//            value.doGet();
//        });
        webContainer.begin();

    }
}
