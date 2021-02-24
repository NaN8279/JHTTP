package io.github.nan8279.jhttp.response.header;

import io.github.nan8279.jhttp.cookie.Cookie;
import io.github.nan8279.jhttp.response.types.FileType;
import io.github.nan8279.jhttp.response.code.StatusCode;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Used to manage headers for a response.
 */
public class ResponseHeader {
    final private ZonedDateTime date;
    final private Cookie[] cookies;
    final private HashMap<String, String> customHeaders = new HashMap<>();
    private StatusCode responseCode;
    private int responseLength;
    private FileType fileType;
    private boolean addCharset;

    /**
     * @param date the date the response is being send.
     * @param cookies the cookies to be set with the response.
     * @param responseCode the response code.
     * @param fileType the response file type.
     * @param responseLength the response length
     * @param addCharset if the response should include the charset.
     */
    public ResponseHeader(ZonedDateTime date, Cookie[] cookies,
                          StatusCode responseCode, FileType fileType, int responseLength,
                          boolean addCharset) {
        this.date = date;
        this.cookies = cookies;
        this.responseCode = responseCode;
        this.fileType = fileType;
        this.responseLength = responseLength;
        this.addCharset = addCharset;
    }

    public void setResponseType(FileType fileType) {
        this.fileType = fileType;
    }

    public void setAddCharset(boolean addCharset) {
        this.addCharset = addCharset;
    }

    public void setResponseCode(StatusCode responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseLength(int responseLength) {
        this.responseLength = responseLength;
    }

    public void addCustomHeader(String name, String value) {
        customHeaders.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder headerBuilder = new StringBuilder();

        headerBuilder.append("HTTP/1.1 ");
        headerBuilder.append(responseCode.getStatusCode());
        headerBuilder.append(" ");
        headerBuilder.append(responseCode.getStatusMessage());
        headerBuilder.append("\r\n");

        headerBuilder.append("Date: ");
        headerBuilder.append(date.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        headerBuilder.append("\r\n");

        headerBuilder.append("Server: JHTTP/1.0\r\n");

        headerBuilder.append("Connection: close\r\n");

        for (Cookie cookie : cookies) {
            headerBuilder.append(cookie.toString());
            headerBuilder.append("\r\n");
        }

        for (String customHeader : customHeaders.keySet()) {
            headerBuilder.append(customHeader);
            headerBuilder.append(": ");
            headerBuilder.append(customHeaders.get(customHeader));
            headerBuilder.append("\r\n");
        }

        headerBuilder.append("Content-Length: ");
        headerBuilder.append(responseLength);
        headerBuilder.append("\r\n");

        headerBuilder.append("Content-Type: ");
        headerBuilder.append(fileType.getMIMEString());

        if (addCharset) {
            headerBuilder.append("; charset=utf-8");
        }

        return headerBuilder.toString();
    }
}
