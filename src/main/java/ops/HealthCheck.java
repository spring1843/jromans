package ops;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HealthCheck implements HttpHandler {
    private MetricsAgent metrics;
    private Config config;

    public HealthCheck(MetricsAgent metrics, Config config) {
        this.metrics = metrics;
        this.config = config;
    }

    public void handle(HttpExchange t) throws IOException {
        String response = "{status:\"OK\"}";
        t.sendResponseHeaders(200, response.length());
        this.metrics.reportEvent(this.config.eventTypeHealthCheck);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
