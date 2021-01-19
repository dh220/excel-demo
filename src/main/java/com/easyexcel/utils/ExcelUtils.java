package com.easyexcel.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2021/1/19
 * @Time:11:58
 */
@Slf4j
public class ExcelUtils {
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("下载Excel出现异常：{}",e);
        }
    }
}
