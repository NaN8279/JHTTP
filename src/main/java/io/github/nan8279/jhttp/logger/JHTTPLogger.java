package io.github.nan8279.jhttp.logger;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.request.raw.RawRequest;
import io.github.nan8279.jhttp.response.Response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

/**
 * The HTTP server logger.
 * Should only be used internally.
 */
public class JHTTPLogger {
    final private static Logger logger = Logger.getLogger("JHTTP");

    /**
     * Logs a HTTP request.
     *
     * @param response the response that has been sent to the user.
     * @param request the request from the user.
     * @param client the user.
     */
    public static void logRequest(Response response, RawRequest request, Client client) {
        if (request == null) {
            logger.log(Level.INFO, "[" + client.getIP() + "] " +
                    ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + " - "
                    + response.getStatus().getStatusCode());
            return;
        }

        logger.log(Level.INFO, "[" + client.getIP() + "] " +
                ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + " - " + request.getCommand() +
                " " + request.getURI() + " - " + response.getStatus().getStatusCode());
    }

    /**
     * Logs a HTTP request that returned an exception.
     *
     * @param response the response send to the user.
     * @param request the request the user gave.
     * @param client the user.
     * @param exception the exception thrown.
     */
    public static void logRequest(Response response, RawRequest request, Client client, Exception exception) {
        logRequest(response, request, client);
        exception.printStackTrace();
    }

    public static void logStartUp(int port) {
        logger.log(Level.INFO, "JHTTP server now up on port " + port);
    }

    static {
        ConsoleHandler handler = new ConsoleHandler();

        JHTTPLogFormatter formatter = new JHTTPLogFormatter();
        handler.setFormatter(formatter);

        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }
}

/**
 * Used to format log messages.
 */
class JHTTPLogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return "[" + record.getLoggerName() + "]" +
                            "[" + record.getLevel().getName() + "]" +
                            "[Thread " + record.getThreadID() + "] " +
                            record.getMessage() + "\n";
    }
}
