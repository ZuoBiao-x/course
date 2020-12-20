package com.course.business.config;

import com.course.server.domain.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ChapterService getChapterService(){
        return new ChapterService();
    }
}
