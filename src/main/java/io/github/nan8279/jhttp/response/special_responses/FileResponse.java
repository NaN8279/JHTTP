package io.github.nan8279.jhttp.response.special_responses;

import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.response_types.ResponseType;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This response sends a file over HTTP.
 */
public class FileResponse extends Response {
    final private byte[] fileData;

    /**
     * @param file the file to send.
     * @throws IOException when an exception happens while reading the file.
     */
    public FileResponse(File file) throws IOException {
        super(StatusCode.STATUS_200);

        data = null;
        fileData = Files.readAllBytes(file.toPath());
        responseType = ResponseType.fromFile(file.getPath());
        addCharset = false;
        dataLength = fileData.length;
    }

    /**
     * @return the data of the file.
     */
    public byte[] getFileData() {
        return fileData;
    }
}
