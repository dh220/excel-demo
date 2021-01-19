package com.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.easyexcel.entity.Demo;
import com.easyexcel.service.TestService;
import com.easyexcel.utils.EasyExcelValidParams;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2021/1/19
 * @Time:11:32
 */
@Slf4j
public class DemoListener extends AnalysisEventListener<Demo> {

    private TestService testService;
    private Validator globalValidator;

    private List<Demo> list = new ArrayList<>();

    /**
     * 由于listener不能被spring管理，所需参数可由构造方法传入
     */
    public DemoListener(TestService testService,Validator globalValidator) {
        this.testService = testService;
        this.globalValidator = globalValidator;
    }

    @Override
    public void invoke(Demo demo, AnalysisContext analysisContext) {
        Integer rowIndex = analysisContext.readRowHolder().getRowIndex() + 1;
        EasyExcelValidParams.validParams(demo,rowIndex,globalValidator);
        list.add(demo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //解析完成
        log.info("list size:{}",list.size());
        testService.testOut(list);
    }
}
