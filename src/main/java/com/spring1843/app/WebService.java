package com.spring1843.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ops.Ops;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

class WebService {
    private static final String invalidRequest = "Invalid request. Example request: http://localhost:8080/romannumeral?query={integer}";
    public static Ops ops;
    private static RomanNumbers romanNumbers;

    WebService(Ops ops) throws IOException {
        WebService.ops = ops;
        romanNumbers = new RomanNumbers();
        HttpServer server = HttpServer.create(new InetSocketAddress(ops.config.Port), 0);
        server.createContext("/romannumeral", new APIEndpoint(new RomanNumeral(), ops));
        server.createContext("/health", new APIEndpoint(ops.healthCheck, ops));
        server.setExecutor(null);
        server.start();
    }

    static class RomanNumeral implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            int query = getQuery(t);

            if (query == -1) {
                WebService.ops.metrics.reportEvent(WebService.ops.config.eventTypeFailedRomanNumericalConversion);
                return;
            }

            String response = WebService.romanNumbers.FromDecimal(query);
            APIEndpoint.addLongCachingHeaders(t);
            WebService.ops.metrics.reportEvent(WebService.ops.config.eventTypeSuccessfulRomanNumericalConversion);
            APIEndpoint.writeResponse(t, 200, validResponse(response));
        }

        private int getQuery(HttpExchange t) throws IOException {
            Map<String, String> queryMap = APIEndpoint.queryToMap(t);

            if (!queryMap.containsKey("query")) {
                APIEndpoint.writeResponse(t, 400, APIEndpoint.errorResponse(invalidRequest));
                return -1;
            }
            String query = queryMap.get("query");
            return Integer.parseInt(query);
        }

        private String validResponse(String response) {
            return "{roman:\"" + response + "\"}";
        }
    }
}
