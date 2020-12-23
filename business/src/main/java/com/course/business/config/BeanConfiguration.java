package com.course.business.config;

import com.course.server.config.LogAspect;
import com.course.server.service.ChapterService;
import com.course.server.service.SectionService;
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
}
