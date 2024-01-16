package com.beck.servletEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private BufferedReader in;
    private String method;
    private String path;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> requestParameters = new HashMap<>();

    public Request(BufferedReader in) {
        this.in = in;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public String getRequestParameter(String name) {
        return requestParameters.get(name);
    }

    private void parseRequestParams(String queryString) {
        for (String pair : queryString.split("&")) {
            String[] pairArr = pair.split("=");
            requestParameters.put(pairArr[0], pairArr[1]);
        }
    }

    public boolean parse() throws IOException {
        String line = in.readLine();

        String[] lineArray = line.split(" ");
        if (lineArray.length != 3) return false;
        method = lineArray[0];

        String url = lineArray[1];
        int queryStringIndex = url.indexOf("?");
        if (queryStringIndex > -1) {
            path = url.substring(0, queryStringIndex);
            parseRequestParams(url.substring(queryStringIndex + 1));

        } else {
            path = url;
        }

        while (!line.isEmpty()) {
            line = in.readLine(); // request headers

            if (!"".equals(line)) {
                String[] headerPair = line.split(":");
                headers.put(headerPair[0], headerPair[1]);
            }
        }
        if ("POST".equals(method)) {
            StringBuilder body = new StringBuilder();
            while (in.ready()) {
                body.append((char) in.read());
            }
//            System.out.println(body.toString());
            parseRequestParams(body.toString());
        }
        return true;
    }
}
