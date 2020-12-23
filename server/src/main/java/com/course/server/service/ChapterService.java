package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.*;

/**
 * 2020年12月19日00:09:25
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 由于 public void queryChapter(PageDto pageDto) 中的PageDto是从controller中传递过来的，
     * 所以其实再次不用返回PageDto对象，在controller中也能获取到数据
     * 实际开发中几乎不会这样做，但由于这里是请求和响应都封装在一个对象中，所以为了简便才会这样做
     * @param pageDto
     */
    public void queryChapter(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("id desc");

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        // 将查询到的list放入PageInfo对象中，然后从PageInfo对象中获取分页所需要的的参数
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        pageDto.setList(chapterDtoList);
    }

    /**
     * 添加大章
     * @param chapterDto
     */
    public void save(ChapterDto chapterDto){
        chapterDto.setId(UuidUtil.getShortUuid());
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterDto,chapter);
        chapterMapper.insert(chapter);
    }
}
