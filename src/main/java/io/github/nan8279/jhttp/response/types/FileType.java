package io.github.nan8279.jhttp.response.types;

/**
 * MIME types for a response.
 *
 * Set the MIME type of a response with {@link io.github.nan8279.jhttp.response.Response#setResponseType(FileType)}
 */
public enum FileType {
    AAC_AUDIO("audio/aac", "aac"),
    ABIWORD_DOCUMENT("application/x-abiword", "abw"),
    ARCHIVE_DOCUMENT("application/x-freearc", "arc"),
    AVI_VIDEO("video/x-msvideo", "avi"),
    AMAZON_KINDLE_EBOOK("application/vnd.amazon.ebook", "azw"),
    BINARY_DATA("application/octet-stream", "bin"),
    BITMAP("image/bmp", "bmp"),
    BZIP_ARCHIVE("application/x-bzip", "bz"),
    BZIP2_ARCHIVE("application/x-bzip2", "bz2"),
    C_SHELL("application/x-csh", "csh"),
    CSS("text/css", "css"),
    CSV("text/csv", "csv"),
    MS_WORD("application/msword", "doc"),
    MS_WORD_XML("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"),
    MS_EMBEDDED_OPENTYPE_FONTS("application/vnd.ms-fontobject", "eot"),
    EPUB("application/epub+zip", "epub"),
    GZIP_ARCHIVE("application/gzip", "gz"),
    GIF("image/gif", "gif"),
    HTML("text/html", "html"),
    HTM("tex/html", "html"),
    ICO("image/vnd.microsoft.icon", "ico"),
    ICALENDAR("text/calendar", "ics"),
    JAVA_ARCHIVE("application/java-archive", "jar"),
    JPEG_IMAGE("image/jpeg", "jpeg"),
    JPG_IMAGE("image/jpeg", "jpg"),
    JAVASCRIPT("text/javascript", "js"),
    JSON("application/json", "json"),
    JSON_LD("application/ld+json", "jsonld"),
    MID_AUDIO("audio/midi", "mid"),
    MIDI_AUDIO("audio/midi", "midi"),
    JAVASCRIPT_MODULE("text/javascript", "mjs"),
    MP3_AUDIO("audio/mpeg", "mp3"),
    MPEG_VIDEO("video/mpeg", "mpeg"),
    APPLE_INSTALLER("application/vnd.apple.installer+xml", "mpkg"),
    OPENDOCUMENT_PRESENTATION("application/vnd.oasis.opendocument.presentation","ödp"),
    OPENDOCUMENT_SPREADSHEET("application/vnd.oasis.opendocument.spreadsheet", "ods"),
    OPENDOCUMENT_TEXT("application/vnd.oasis.opendocument.text", "odt"),
    OGG_AUDIO("audio/ogg", "oga"),
    OGG_VIDEO("video/ogg", "ogv"),
    OGG("application/ogg", "ogx"),
    OPUS_AUDIO("audio/opus", "opus"),
    OPENTYPE_FONT("font/otf", "otf"),
    PNG_IMAGE("image/png", "png"),
    PDF("application/pdf", "pdf"),
    PHP("application/x-httpd-php", "php"),
    MS_POWERPOINT("application/vnd.ms-powerpoint", "ppt"),
    MS_POWERPOINT_XML("application/vnd.openxmlformats-officedocument.presentationml.presentation",
            "pptx"),
    RAR_ARCHIVE("application/vnd.rar", "rar"),
    RTF("application/rtf", "rtf"),
    BOURNE_SHELL_SCRIPT("application/x-sh", "sh"),
    SVG("image/svg+xml", "svg"),
    ADOBE_FLASH("application/x-shockwave-flash", "swf"),
    TAPE_ARCHIVE("application/x-tar", "tar"),
    TIF_IMAGE("image/tiff", "tif"),
    TIFF_IMAGE("image/tiff", "tiff"),
    TRUETYPE_FONT("font/ttf", "ttf"),
    PLAIN_TEXT("text/plain", "txt"),
    MICROSOFT_VISIO("application/vnd.visio", "vsd"),
    WAVE_AUDIO("audio/wav", "wav"),
    WEBM_AUDIO("audio/webm", "weba"),
    WEBM_VIDEO("video/webm", "webm"),
    WEBP_IMAGE("image/webp", "webp"),
    WEB_OPEN_FONT("font/woff", "woff"),
    WEB_OPEN_FONT2("font/woff2", "woff2"),
    XHTML("application/xhtml+xml", "xhtml"),
    MICROSOFT_EXCEL("application/vnd.ms-excel", "xls"),
    MICROSOFT_EXCEL_OPENXML("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "xlsx"),
    XML("text/xml", "xml"),
    XUL_XML("application/vnd.mozilla.xul+xml", "xul"),
    ZIP_ARCHIVE("application/zip", "zip"),
    _7_ZIP_ARCHIVE("application/x-7z-compressed", "7z");

    final private String MIMEString;
    final private String extension;

    /**
     * @param MIMEString the MIME string used in the Content-Type header in the response.
     * @param extension the extension of the file, without a dot.
     */
    FileType(String MIMEString, String extension) {
        this.MIMEString = MIMEString;
        this.extension = extension;
    }

    /**
     * @return the MIME string used in the Content-Type header in the response
     */
    public String getMIMEString() {
        return MIMEString;
    }

    public String getExtension() {
        return extension;
    }

    public static FileType fromExtension(String extension) {
        for (FileType type : FileType.values()) {
            if (type.getExtension().equals(extension)) {
                return type;
            }
        }
        return FileType.BINARY_DATA;
    }

    public static FileType fromFile(String path) {
        return fromExtension(path.split("\\.")[path.split("\\.").length - 1]);
    }
}
