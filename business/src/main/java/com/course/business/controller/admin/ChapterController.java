package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
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

    /**
     * 列表查询
     * @param chapterPageDto
     * @return
     */
    @PostMapping(value = "/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto){
        chapterService.queryChapter(chapterPageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param chapterDto
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        // 参数校验
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        chapterService.save(chapterDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto delete(@PathVariable(name = "id") String id){
        chapterService.delete(id);
        return new ResponseDto();
    }

}
