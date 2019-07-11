package top.zywork.enums;

/**
 * MIME Type枚举，常用的MIME Type<br />
 * 创建于2017-09-01
 *
 * @author 王振宇
 * @version 1.0
 * @see top.zywork.enums.ContentTypeEnum
 */
public enum MIMETypeEnum {

    TEXT("text", ".txt", "text/plain"),
    HTML("html", ".html", "text/html"),
    XML("xml", ".xml", "text/xml"),
    JSON("json", ".json", "application/json"),
    JPG("jpg", ".jpg", "image/jpeg"),
    JPEG("jpeg", ".jpeg", "image/jpeg"),
    GIF("gif", ".gif", "image/gif"),
    PNG("png", ".png", "image/png"),
    BMP("bmp", ".bmp", "image/bmp"),
    PDF("pdf", ".pdf", "application/pdf"),
    DOC("doc", ".doc", "application/msword"),
    DOCX("docx", ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    PPT("ppt", ".ppt", "application/vnd.ms-powerpoint"),
    PPTX("pptx", ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    XLS("xls", ".xls", "application/vnd.ms-excel"),
    XLSX("xlsx", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    ZIP("zip", ".zip", "application/zip"),
    RAR("rar", ".rar", "application/x-rar-compressed");

    private String value;
    private String ext;
    private String mime;

    MIMETypeEnum(String value, String ext, String mime) {
        this.value = value;
        this.ext = ext;
        this.mime = mime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
