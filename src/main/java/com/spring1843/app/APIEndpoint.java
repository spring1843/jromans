package com.spring1843.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ops.Ops;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class APIEndpoint implements HttpHandler {
    private static final String get = "GET";
    private static final String methodNotAllowed = "HTTP Method not allowed";
    private static Ops ops;
    private HttpHandler handler;

    APIEndpoint(HttpHandler handler, Ops ops) {
        APIEndpoint.ops = ops;
        this.handler = handler;
    }

    static void writeResponse(HttpExchange t, int responseCode, String response) throws IOException {
        t.sendResponseHeaders(responseCode, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static void addLongCachingHeaders(HttpExchange t) {
        t.getResponseHeaders().add("max-age", "31556926");
        t.getResponseHeaders().add("Cache-Control", "immutable");
    }

    static String errorResponse(String response) {
        return "{error:\"" + response + "\"}";
    }

    static Map<String, String> queryToMap(HttpExchange t) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : t.getRequestURI().getRawQuery().split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public void handle(HttpExchange t) throws IOException {
        if (!t.getRequestMethod().equals(get)) {
            APIEndpoint.writeResponse(t, 405, errorResponse(methodNotAllowed));
            return;
        }
        t.getResponseHeaders().add("content-type", "application/json");
        APIEndpoint.ops.logger.printf("Access Log\t%s\t%s\t%s \n", t.getRemoteAddress(), t.getRequestMethod(), t.getRequestURI());
        this.handler.handle(t);
    }
}
