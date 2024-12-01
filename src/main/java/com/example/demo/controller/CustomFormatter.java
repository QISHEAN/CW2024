package com.example.demo.controller;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();

        // Format the timestamp
        String timestamp = DATE_FORMATTER.format(Instant.ofEpochMilli(record.getMillis()));

        // Build the log message
        sb.append(timestamp) // Timestamp
                .append(" [") // Separator
                .append(record.getLevel().getName()) // Log level
                .append("] ")
                .append(formatMessage(record)) // Actual log message
                .append(System.lineSeparator());

        // Append exception stack trace if available
        if (record.getThrown() != null) {
            Throwable throwable = record.getThrown();
            sb.append("Exception: ").append(throwable.toString())
                    .append(System.lineSeparator());

            for (StackTraceElement element : throwable.getStackTrace()) {
                sb.append("\tat ").append(element.toString())
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}

