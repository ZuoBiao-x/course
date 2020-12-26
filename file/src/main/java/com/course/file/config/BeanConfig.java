package com.course.file.config;

import com.course.server.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FileService getFileService(){
        return new FileService();
    }

}
