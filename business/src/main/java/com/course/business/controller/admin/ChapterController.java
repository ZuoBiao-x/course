package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2020年12月19日00:12:14
 */
@RestController
@RequestMapping(value = "/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @GetMapping(value = "/list")
    public List<ChapterDto> list(){
        return chapterService.queryChapter();
    }

}
