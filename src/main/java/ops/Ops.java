package ops;

public class Ops {
    public Config config;
    public Logger logger;
    public MetricsAgent metrics;
    public HealthCheck healthCheck;

    public Ops() {
        this.config = new Config();
        this.logger = new Log(this.config.AppName);
        this.metrics = new Metrics(logger);
        this.healthCheck = new HealthCheck(this.metrics, this.config);
    }
}
