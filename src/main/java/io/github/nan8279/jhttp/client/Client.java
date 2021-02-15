package io.github.nan8279.jhttp.client;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.logger.Logger;
import io.github.nan8279.jhttp.request.request_headers.RequestHeaders;
import io.github.nan8279.jhttp.request.RequestManager;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

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
     * This should only be used internally.
     *
     * @return the cookies the client has.
     */
    public ArrayList<Cookie> getCookies() {
        return cookies;
    }

    /**
     * @return the IP of the client.
     */
    public String getIP() {
        return socket.getInetAddress().getHostAddress();
    }

    /**
     * Handles the clients request.
     * This should only be used internally.
     *
     * @throws IOException when an error occurs while handling the client.
     */
    public void handleClient() throws IOException {
        RequestHeaders requestHeaders;

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
            requestHeaders = new RequestHeaders(request, payload);
            cookies.addAll(requestHeaders.getCookies());

            try {
                response = manager.parseRequest(requestHeaders, this);
                Logger.logRequest(response, requestHeaders, this);
            } catch (Exception exception) {
                response = new Response(StatusCode.STATUS_500);
                Logger.logRequest(response, requestHeaders, this, exception);
            }

        } catch (ArrayIndexOutOfBoundsException exception) {
            response = new Response(StatusCode.STATUS_400);
            Logger.logRequest(response, null, this);
        }

        try {
            output.write(response.toString());
            output.flush();

            output.close();
            input.close();

            socket.close();
        } catch (IOException ignored) {}
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
