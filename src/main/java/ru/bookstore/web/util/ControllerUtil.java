package ru.bookstore.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.apache.commons.codec.CharEncoding.UTF_8;

public class ControllerUtil {
    public static final String XLSX_MEDIA_TYPE = "application/vnd.ms-excel";

    public static void setResponseHeadersForAttachmentFile(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           String reportName,
                                                           String contentType) throws UnsupportedEncodingException {
        response.setContentType(contentType);
        response.setCharacterEncoding(UTF_8);
        setFileNameToResponseHeader(response, reportName);
    }

    private static void setFileNameToResponseHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "UTF8").replace("+", " ");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
    }
}
