package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2020年12月19日00:09:25
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> queryChapter(){
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("id desc");
        return chapterMapper.selectByExample(chapterExample);
    }
}
