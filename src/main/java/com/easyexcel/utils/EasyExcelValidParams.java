package com.easyexcel.utils;

import com.alibaba.excel.exception.ExcelAnalysisException;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2020/12/16
 * @Time:15:05
 */
@Log4j2
public class EasyExcelValidParams {

    public static void validParams(Object t, Integer rowIndex, Validator globalValidator) {
        Set<ConstraintViolation<Object>> set = globalValidator.validate(t);
        for (ConstraintViolation<Object> constraintViolation : set) {
            log.error("第{}行：{}",rowIndex,constraintViolation.getMessage());
            throw new ExcelAnalysisException("第" + rowIndex + "行：" + constraintViolation.getMessage());
        }
    }
}
