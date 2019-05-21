package ops;

public interface Logger {
    void setPrefix(String prefix);

    void printf(String format, Object... args);

    void println(String message);

    void fatalPrintf(String format, Object... args);

    void fatalPrintln(String message);
}
