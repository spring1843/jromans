package ops;

public class Metrics implements MetricsAgent {
    private Logger logger;

    Metrics(Logger l) {
        this.logger = l;
    }

    public void reportEvent(int eventID) {
        //Connect to stats server here
        this.logger.printf("Metrics - new event of type #%d reported\n", eventID);
    }
}
