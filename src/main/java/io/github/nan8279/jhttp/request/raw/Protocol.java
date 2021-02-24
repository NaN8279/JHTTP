package io.github.nan8279.jhttp.request.raw;

/**
 * HTTP Protocols.
 */
public enum Protocol {
    HTTP_1("HTTP/1.0"),
    HTTP_1_1("HTTP/1.1"),
    HTTP_2("HTTP/2.0");

    final private String protocol;

    /**
     * @param protocol how the protocol will be given in the HTTP request.
     */
    Protocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return how the protocol will be given in a HTTP request.
     */
    @Override
    public String toString() {
        return protocol;
    }

    /**
     * Returns a protocol from a protocol in the HTTP request.
     *
     * @param protocol the protocol in the HTTP request.
     * @return the protocol.
     */
    public static Protocol fromString(String protocol) {
        for (Protocol pr : Protocol.values()) {
            if (pr.toString().equals(protocol)) {
                return pr;
            }
        }
        return null;
    }
}
