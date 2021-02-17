package io.github.nan8279.jhttp.logger;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.request.raw_request.RawRequest;
import io.github.nan8279.jhttp.response.Response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The HTTP server logger.
 * Should only be used internally.
 */
public class Logger {

    /**
     * Logs a HTTP request.
     *
     * @param response the response that has been sent to the user.
     * @param request the request from the user.
     * @param client the user.
     */
    public static void logRequest(Response response, RawRequest request, Client client) {
        if (request == null) {
            System.out.println("[" + client.getIP() + "] " +
                    ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + " - "
                    + response.getStatus().getStatusCode());
            return;
        }

        System.out.println("[" + client.getIP() + "] " +
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
}
