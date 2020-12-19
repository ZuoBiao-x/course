package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
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
        TestExample testExample = new TestExample();
//        testExample.createCriteria().andIdEqualTo(1);
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
