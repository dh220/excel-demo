package com.easyexcel.service.impl;

import com.easyexcel.entity.Demo;
import com.easyexcel.service.TestService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created with IDEA
 * @author:DaiHang
 * @Date:2021/1/19
 * @Time:11:50
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void testOut(List<Demo> list) {
        System.out.println("---------调用-----------");
        System.out.println(list.toString());
    }
}
