package io.github.nan8279.jhttp.client;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.logger.Logger;
import io.github.nan8279.jhttp.request.raw_request.RawRequest;
import io.github.nan8279.jhttp.request.RequestManager;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles requests from a client.
 */
public class Client extends Thread {
    final private BufferedReader input;
    final private PrintWriter output;
    final private Socket socket;
    final private RequestManager manager;
    final private ArrayList<Cookie> cookies = new ArrayList<>();

    /**
     * Creates a new client.
     * This should only be used internally.
     *
     * @param socket the socket that connects with the client.
     * @param manager the request manager.
     * @throws IOException when the socket returns an error.
     */
    public Client(Socket socket, RequestManager manager) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
        this.manager = manager;
    }

    /**
     * @param withServerCookies when true, this also returns the cookies made by the server.
     * @return the cookies the client has.
     */
    public ArrayList<Cookie> getCookies(boolean withServerCookies) {
        if (withServerCookies) {
            return cookies;
        }
        ArrayList<Cookie> withoutServerCookies = new ArrayList<>();

        for (Cookie cookie : cookies) {
            if (!cookie.isServerMade()) {
                withoutServerCookies.add(cookie);
            }
        }
        return withoutServerCookies;
    }

    /**
     * @param withServerCookies when true, this also returns the cookies made by the server.
     * @return the cookies the client has, in a hashmap.
     */
    public HashMap<String, String> getCookieHashmap(boolean withServerCookies) {
        HashMap<String, String> cookieHashmap = new HashMap<>();

        for (Cookie cookie : cookies) {
            if (!withServerCookies) {
                if (!cookie.isServerMade()) {
                    cookieHashmap.put(cookie.getName(), cookie.getValue());
                    continue;
                }
            }
            cookieHashmap.put(cookie.getName(), cookie.getValue());
        }

        return cookieHashmap;
    }

    /**
     * Sets a cookie.
     *
     * @param cookie the cookie to set.
     */
    public void setCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    /**
     * @return the IP of the client.
     */
    public String getIP() {
        return socket.getInetAddress().getHostAddress();
    }

    /**
     * This should only be used in special cases, since it interrupts the connection with the client.
     * Return the response in a request handler instead.
     *
     * @param response the response to send.
     */
    public void sendResponse(Response response) {
        for (Cookie cookie : cookies) {
            if (cookie.isServerMade()) {
                response.setCookie(cookie);
            }
        }

        try {
            output.write(response.toString());
            output.flush();

            output.close();
            input.close();

            socket.close();
            this.interrupt();
        } catch (IOException ignored) {}
    }

    /**
     * Handles the clients request.
     * This should only be used internally.
     *
     * @throws IOException when an error occurs while handling the client.
     */
    public void handleClient() throws IOException {
        RawRequest rawRequest;

        String request;
        String payload;
        try {
            StringBuilder requestBuilder = new StringBuilder();

            String line;
            while((line=input.readLine())!=null) {
                if (line.isBlank()) {
                    break;
                }
                requestBuilder.append(line).append("\r\n");
            }

            StringBuilder payloadBuilder = new StringBuilder();
            while(input.ready()){
                payloadBuilder.append((char) input.read());
            }

            request = requestBuilder.toString();
            payload = payloadBuilder.toString();
        } catch (SocketException exception) {
            return;
        }

        Response response;

        try {
            rawRequest = new RawRequest(request, payload);
            cookies.addAll(rawRequest.getCookies());

            try {
                response = manager.parseRequest(rawRequest, this);
                Logger.logRequest(response, rawRequest, this);
            } catch (Exception exception) {
                response = new Response(StatusCode.STATUS_500);
                Logger.logRequest(response, rawRequest, this, exception);
            }

        } catch (ArrayIndexOutOfBoundsException exception) {
            response = new Response(StatusCode.STATUS_400);
            Logger.logRequest(response, null, this);
        }

        sendResponse(response);
    }

    /**
     * Should only be used internally.
     */
    @Override
    public void run() {
        try {
            this.handleClient();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
