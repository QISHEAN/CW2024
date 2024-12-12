package com.example.demo.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Custom log formatter for Java's logging framework.
 * Formats log messages to include a timestamp, log level, message, and optionally exception stack traces.
 */
public class CustomFormatter extends Formatter {

    //Formatter for timestamps in the log messages.
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    /**
     * Formats a LogRecord into a readable string for logging output.
     *
     * @param record The log record to format.
     * @return The formatted log message as a string.
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();

        //Format the timestamp of the log record.
        String timestamp = DATE_FORMATTER.format(Instant.ofEpochMilli(record.getMillis()));

        //Build the log message with timestamp, level, and message.
        sb.append(timestamp) // Add the formatted timestamp.
                .append(" [") // Separator before the log level.
                .append(record.getLevel().getName()) // Add the log level (e.g., INFO, SEVERE).
                .append("] ") // Close the level section.
                .append(formatMessage(record)) // Append the actual log message.
                .append(System.lineSeparator()); // Add a newline.

        //If an exception is attached to the record, include its stack trace.
        if (record.getThrown() != null) {
            Throwable throwable = record.getThrown();
            sb.append("Exception: ").append(throwable.toString()) // Add exception details.
                    .append(System.lineSeparator());

            //Append the stack trace of the exception.
            for (StackTraceElement element : throwable.getStackTrace()) {
                sb.append("\tat ").append(element.toString()) // Format each stack trace element.
                        .append(System.lineSeparator());
            }
        }

        return sb.toString(); //Return the formatted log message.
    }
}
