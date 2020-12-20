package com.course.business.controller;

import com.course.server.domain.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2020年12月19日00:12:14
 */
@RestController
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @GetMapping(value = "/chapter")
    public List<Chapter> chapter(){
        return chapterService.queryChapter();
    }

}
