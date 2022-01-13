package org.example;

public class StdoutLogger implements Logger {
    private static StdoutLogger instance = null;

    private StdoutLogger() {}

    // Singleton pattern (may also add concurrency protection)
    public static StdoutLogger getInstance() {
        if(StdoutLogger.instance == null) {
            StdoutLogger.instance = new StdoutLogger();
        }

        return StdoutLogger.instance;
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
