package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

public interface MyCourseMapper {
    /**
     * 跟新课程时长
     * @param courseId
     * @return
     */
    int updateTime(@Param("courseId") String courseId);
}
