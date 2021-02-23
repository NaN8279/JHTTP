package io.github.nan8279.jhttp.response;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.response.response_types.ResponseType;
import io.github.nan8279.jhttp.response.special_responses.FileResponse;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Response {
    final private static String defaultData =
            """
            <!DOCTYPE html>
            <html lang="en-US">
                <head>
                    <style>
                        body {
                            font-family: Arial;
                        }
                    </style>
                    <title>{status_code} {status_message}</title>
                    <meta name="description" content="An error page">
                    <meta name="viewport" content="width=device-width initial-scale=1">
                </head>
                <body>
                    <h1>{status_code} {status_message}</h1>
                    <p>The requested URL returned message {status_message} (code {status_code}).</p>
                    <h2>What to do?</h2>
                    <h3>If you are a server coder</h3>
                    <ul>
                        <li>Try checking the server logs for more info about this message.</li>
                        <li>Did your page returned a correct response?</li>
                        <ul>
                            <li>If this page returned null, the server will send this page with code 404.</li>
                            <li>If this page returned a response without any data, the server will send this page with code 200.</li>
                            <li>If your page caused an exception, the server will send this page with code 500 error. Try checking the server logs.</li>
                        </ul>
                    </ul>
                    <h3>If you are a client</h3>
                    <ul>
                        <li>Make sure you have entered the correct URL.</li>
                        <li>If the message code starts with a 4, this is an error on the client side. DO NOT CONTACT THE SERVER ADMIN FOR THIS.</li>
                        <li>Try to contact the server admin if you are sure this is an error on their side.</li>
                    </ul>
                    <br>
                    <a style="color: blue;"
                        href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/{status_code}">More info about this message</a>
                    <p><em>This server is powered by JHTTP</em></p>
                </body>
            </html>
            """;

    final private ArrayList<Cookie> cookies = new ArrayList<>();
    final protected StatusCode status;
    protected ResponseType responseType = ResponseType.HTML;
    protected String data;
    protected boolean addCharset = true;
    protected int dataLength = 0;

    /**
     * A HTTP response.
     *
     * @param status the HTTP response status code.
     * @param data the HTTP response message.
     */
    public Response(StatusCode status, String data) {
        this.status = status;
        this.data = data;
    }

    /**
     * A HTTP response.
     *
     * @param status the HTTP response status code.
     */
    public Response(StatusCode status) {
        this.status = status;
        data = generateDefaultData(status);
    }

    /**
     * A HTTP response. Status code 200 will be used.
     *
     * @param data the HTTP response message.
     */
    public Response(String data) {
        status = StatusCode.STATUS_200;
        this.data = data;
    }

    /**
     * Should only be used internally.
     *
     * @return the cookies that will be sent with this response
     */
    public ArrayList<Cookie> getCookies() {
        return cookies;
    }

    /**
     * Should only be used internally.
     *
     * @param cookie adds a cookie to this response.
     */
    public void setCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    /**
     * @return the status code of this response.
     */
    public StatusCode getStatus() {
        return status;
    }

    /**
     * @return what will be sent to the user.
     */
    public byte[] getBytes() {
        return generateResponse().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Sets the data of the response;
     *
     * @param data the data to set in the response;
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * This generates the default HTTP response message.
     *
     * @param status the status code to generate the default message for.
     * @return the default HTTP response message.
     */
    private static String generateDefaultData(StatusCode status) {
        return defaultData.replace("{status_code}", String.valueOf(status.getStatusCode())).replace(
                "{status_message}", status.getStatusMessage());
    }

    /**
     * @return the response to send to the user.
     */
    protected String generateResponse() {
        if (dataLength == 0) {
            dataLength = data.length();
        }

        StringBuilder response =
                new StringBuilder("HTTP/1.1" + " " + status.getStatusCode() + " " + status.getStatusMessage() + "\r\n" +
                        "Connection: Closed\r\n" +
                        "Server: JHTTP/1.0\r\n" +
                        "Content-Length: " + dataLength + "\r\n" +
                        "Content-Type: " + responseType.getMIMEString());

        if (addCharset) {
            response.append("; charset=utf-8");
        }

        for (Cookie cookie : getCookies()) {
            response.append("\r\n");
            response.append(cookie.toString());
        }

        if (data != null) {
            response.append("\r\n");
            response.append("\r\n");
            response.append(data);
        }

        return response.toString();
    }

    /**
     * Reads the given file and makes a response.
     *
     * @param path the path of the template.
     * @return the response to send to the user.
     */
    public static Response renderTemplate(String path) {
        try {
            ResponseType responseType = ResponseType.fromFile(path);

            if (responseType != ResponseType.HTM && responseType != ResponseType.HTML) {
                return new FileResponse(new File(path));
            }

            String data = Files.readString(Path.of(path));
            Response response = new Response(data);

            String[] name = new File(path).getName().split("\\.");
            String extension = name[name.length - 1];

            response.setResponseType(responseType);
            return new Response(data);
        } catch (IOException exception) {
            return new Response(StatusCode.STATUS_404);
        }
    }

    /**
     * Sets the response type of the response.
     *
     * @param responseType the response type.
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * @return the response type given in this response.
     */
    public ResponseType getResponseType() {
        return responseType;
    }
}
