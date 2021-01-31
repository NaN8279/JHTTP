package io.github.nan8279.jhttp;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.request.RequestManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A HTTP server.
 */
public class JHTTP {
    final private int port;
    final private RequestManager manager = new RequestManager();
    private boolean stopping = false;

    /**
     * Creates a HTTP server.
     *
     * The port argument is the port the server is running on.
     * To add request handlers, use the {@code getManager()} method.
     *
     * @param port the port the server runs on.
     */
    public JHTTP(int port) {
        this.port = port;
    }


    /**
     * Starts the HTTP server.
     *
     * @throws IOException when a problem with creating the HTTP server socket occurs.
     */
    public void run() throws IOException {
        while (!stopping) {
            ServerSocket serverSocket = new ServerSocket(getPort());
            Socket clientSocket = serverSocket.accept();

            Client client = new Client(clientSocket, manager);
            client.start();

            serverSocket.close();
        }
    }

    /**
     * Stops the HTTP server.
     */
    public void stop() {
        stopping = true;
    }

    /**
     * @return the port of the HTTP server.
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the request manager of the server.
     */
    public RequestManager getManager() {
        return manager;
    }
}
