package io.github.nan8279.jhttp.response.response_types;

/**
 * MIME types for a response.
 *
 * Set the MIME type of a response with {@link io.github.nan8279.jhttp.response.Response#setResponseType(ResponseType)}
 */
public enum ResponseType {
    AAC_AUDIO("audio/aac"),
    ABIWORD_DOCUMENT("application/x-abiword"),
    ARCHIVE_DOCUMENT("application/x-freearc"),
    AVI_VIDEO("video/x-msvideo"),
    AMAZON_KINDLE_EBOOK("application/vnd.amazon.ebook"),
    BINARY_DATA("application/octet-stream"),
    BITMAP("image/bmp"),
    BZIP_ARCHIVE("application/x-bzip"),
    BZIP2_ARCHIVE("application/x-bzip2"),
    C_SHELL("application/x-csh"),
    CSS("text/css"),
    CSV("text/csv"),
    MS_WORD("application/msword"),
    MS_WORD_XML("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    MS_EMBEDDED_OPENTYPE_FONTS("application/vnd.ms-fontobject"),
    EPUB("application/epub+zip"),
    GZIP_ARCHIVE("application/gzip"),
    GIF("image/gif"),
    HTML("text/html"),
    ICO("image/vnd.microsoft.icon"),
    ICALENDAR("text/calendar"),
    JAVA_ARCHIVE("application/java-archive"),
    JPEG_IMAGE("image/jpeg"),
    JAVASCRIPT("text/javascript"),
    JSON("application/json"),
    JSON_LD("application/ld+json"),
    MIDI_AUDIO("audio/midi"),
    MP3_AUDIO("audio/mpeg"),
    MPEG_VIDEO("video/mpeg"),
    APPLE_INSTALLER("application/vnd.apple.installer+xml"),
    OPENDOCUMENT_PRESENTATION("application/vnd.oasis.opendocument.presentation"),
    OPENDOCUMENT_SPREADSHEET("application/vnd.oasis.opendocument.spreadsheet"),
    OPENDOCUMENT_TEXT("application/vnd.oasis.opendocument.text"),
    OGG_AUDIO("audio/ogg"),
    OGG_VIDEO("audio/ogg"),
    OGG("application/ogg"),
    OPUS_AUDIO("audio/opus"),
    OPENTYPE_FONT("font/otf"),
    PNG_IMAGE("image/png"),
    PDF("application/pdf"),
    PHP("application/x-httpd-php"),
    MS_POWERPOINT("application/vnd.ms-powerpoint"),
    MS_POWERPOINT_XML("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    RAR_ARCHIVE("application/vnd.rar"),
    RTF("application/rtf"),
    BOURNE_SHELL_SCRIPT("application/x-sh"),
    SVG("image/svg+xml"),
    ADOBE_FLASH("application/x-shockwave-flash"),
    TAPE_ARCHIVE("application/x-tar"),
    TIF_IMAGE("image/tiff"),
    TRUETYPE_FONT("font/ttf"),
    PLAIN_TEXT("text/plain"),
    MICROSOFT_VISIO("application/vnd.visio"),
    WAVE_AUDIO("audio/wav"),
    WEBM_AUDIO("audio/webm"),
    WEBM_VIDEO("video/webm"),
    WEBP_IMAGE("image/webp"),
    WEB_OPEN_FONT("font/woff"),
    WEB_OPEN_FONT2("font/woff2"),
    XHTML("application/xhtml+xml"),
    MICROSOFT_EXCEL("application/vnd.ms-excel"),
    MICROSOFT_EXCEL_OPENXML("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    XML("text/xml"),
    XUL_XML("application/vnd.mozilla.xul+xml"),
    ZIP_ARCHIVE("application/zip"),
    _7_ZIP_ARCHIVE("application/x-7z-compressed");

    final private String MIMEString;

    /**
     * @param MIMEString the MIME string used in the Content-Type header in the response
     */
    ResponseType(String MIMEString) {
        this.MIMEString = MIMEString;
    }

    /**
     * @return the MIME string used in the Content-Type header in the response
     */
    public String getMIMEString() {
        return MIMEString;
    }
}
