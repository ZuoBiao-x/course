package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 2020年12月19日00:12:14
 */
@RestController
@RequestMapping(value = "/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    @PostMapping(value = "/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        chapterService.queryChapter(pageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping(value = "/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        LOG.info("chapterDto: {}", chapterDto);

        // 参数校验
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        chapterService.save(chapterDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto delete(@PathVariable(name = "id") String id){
        LOG.info("id: {}", id);
        chapterService.delete(id);
        return new ResponseDto();
    }

}
