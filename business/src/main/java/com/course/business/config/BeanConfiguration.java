package com.course.business.config;

import com.course.server.config.LogAspect;
import com.course.server.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ChapterService getChapterService(){
        return new ChapterService();
    }

    @Bean
    public LogAspect getLogAspect(){
        return new LogAspect();
    }

    @Bean
    public SectionService getSectionService(){
        return new SectionService();
    }

    @Bean
    public CourseService getCourseService(){
        return new CourseService();
    }

    @Bean
    public CategoryService getCategoryService(){
        return new CategoryService();
    }

    @Bean
    public CourseCategoryService getCourseCategoryService(){
        return new CourseCategoryService();
    }

    @Bean
    public TeacherService getTeacherService(){
        return new TeacherService();
    }

    @Bean
    public CourseContentFileService getCourseContentFileService(){
        return new CourseContentFileService();
    }

    @Bean
    public MemberService getMemberService(){
        return new MemberService();
    }

    @Bean
    public SmsService getSmsService(){
        return new SmsService();
    }
}
