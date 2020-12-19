package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.mapper.TestMapper;
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
//        return this.testMapper.queryTest();
        return null;
    }
}
