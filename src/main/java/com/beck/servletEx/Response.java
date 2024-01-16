package com.beck.servletEx;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {

    private OutputStream out;
    private PrintWriter printWriter;

    public Response(OutputStream out) {
        this.out = out;
        this.printWriter = new PrintWriter(out);
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
