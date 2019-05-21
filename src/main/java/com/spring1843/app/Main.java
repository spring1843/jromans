package com.spring1843.app;

import ops.Ops;

public class Main {
    public static void main(String[] args) {
        Ops ops = new Ops();
        ops.logger.printf("Serving http endpoint at http://localhost:%d/%s\n", ops.config.Port, ops.config.AppName);
        try {
            new WebService(ops);
        } catch (Throwable e) {
            ops.logger.fatalPrintln(e.getMessage());
        }
    }
}
