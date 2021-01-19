package com.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easyexcel.entity.Demo;
import com.easyexcel.listener.DemoListener;
import com.easyexcel.service.TestService;
import com.easyexcel.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2021/1/19
 * @Time:11:57
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    private Validator globalValidator;



    @GetMapping("/downLoad")
    public void downLoadFile(HttpServletResponse response) throws Exception {
        String fileName = "模板.xls";
        Workbook workbook = WorkbookFactory.create(Thread.currentThread().getContextClassLoader().getResourceAsStream( "templates/".concat(fileName)));
        ExcelUtils.downLoadExcel("模板.xls", response, workbook);
    }

    @GetMapping("downLoadData")
    public void downLoadData(HttpServletResponse response) throws Exception{
        List<Demo> fileExports = new ArrayList<>();
        fileExports.add(Demo.builder()
                .name("张三")
                .grade("一年级")
                .email("100000@qq.com")
                .build());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //设置文件名
        String fileName = "数据文件".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        EasyExcel.write(response.getOutputStream(), Demo.class).sheet("sheet1").doWrite(fileExports);
    }

    @RequestMapping("import")
    public JSONObject importPosition(MultipartFile file) throws Exception{
        EasyExcel.read(file.getInputStream(), Demo.class,
                new DemoListener(testService,globalValidator))
                .sheet().doRead();
        JSONObject object = new JSONObject();
        object.put("success","导入成功");
        return object;
    }

}
