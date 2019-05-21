package ops;

public class Log implements Logger {
    private final String separator = "\t";
    private String prefix;

    Log(String prefix) {
        this.setPrefix(prefix);
    }

    private String getPrefix() {
        return this.prefix.concat(separator).concat(String.valueOf(System.currentTimeMillis() / 1000L));
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void printf(String format, Object... args) {
        System.out.printf(this.getPrefix().concat(separator) + format, args);
    }

    public void println(String message) {
        this.printf(message.concat("\n"));
    }

    private void exitWithError() {
        System.exit(1);
    }

    public void fatalPrintf(String format, Object... args) {
        this.printf(format, args);
        this.exitWithError();
    }

    public void fatalPrintln(String message) {
        this.println(message);
        this.exitWithError();
    }
}
