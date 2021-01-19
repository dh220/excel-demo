package com.easyexcel.exp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author daihang
 * 全局异常处理
 */
@Log4j2
@ControllerAdvice
public class ControllerExceptionAdvice {
	  /**
     * 全局异常处理，异常返回统一
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JSONObject exceptionHandler(Exception ex){
        log.error("发生异常：{}",ex);
        JSONObject object = new JSONObject();
        object.put("error",ex.getMessage());
        return object;
     }
}