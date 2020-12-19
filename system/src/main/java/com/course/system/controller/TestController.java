package com.course.system.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2020年12月19日00:12:14
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping(value = "/test")
    public List<Test> test(){
        return this.testService.queryTest();
    }

}
