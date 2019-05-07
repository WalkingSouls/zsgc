package com.zsgc.admin.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class ExcelUtils {
    public static void exportToExcel(String templateName, Map<String, Object> params, String filename) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);

        InputStream in = null;
        OutputStream out = null;
        try {
            in = ExcelUtils.class.getResourceAsStream("/excel/" + templateName);
            out = response.getOutputStream();
            JxlsHelper.getInstance().processTemplate(in, out, new Context(params));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    private ExcelUtils() {
    }
}
