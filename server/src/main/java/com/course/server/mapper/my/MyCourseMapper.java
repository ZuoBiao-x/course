package com.course.server.mapper.my;

import com.course.server.dto.CourseDto;
import com.course.server.dto.CoursePageDto;
import com.course.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCourseMapper {
    /**
     * 跟新课程时长
     * @param courseId
     * @return
     */
    int updateTime(@Param("courseId") String courseId);

    /*
    * 用于修改课程顺序的三个方法
    * */
    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);

    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);
}
