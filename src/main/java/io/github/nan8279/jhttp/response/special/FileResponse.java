package io.github.nan8279.jhttp.response.special;

import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.types.FileType;
import io.github.nan8279.jhttp.response.code.StatusCode;

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
        fileData = Files.readAllBytes(file.toPath());

        responseHeader.setResponseType(FileType.fromFile(file.getPath()));
        responseHeader.setResponseLength(fileData.length);
        responseHeader.setAddCharset(false);

        data = null;
    }

    /**
     * @return the data of the file.
     */
    public byte[] getFileData() {
        return fileData;
    }
}
