package com.course.business.controller.admin;

import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 2020年12月19日00:12:14
 */
@RestController
@RequestMapping(value = "/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping(value = "/list")
    public PageDto list(@RequestBody PageDto pageDto){
        chapterService.queryChapter(pageDto);
        return pageDto;
    }

}
