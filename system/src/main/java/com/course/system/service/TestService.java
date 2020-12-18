package com.course.system.service;

import com.course.system.domain.Test;
import com.course.system.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2020年12月19日00:09:25
 */
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> queryTest(){
        return this.testMapper.queryTest();
    }
}
