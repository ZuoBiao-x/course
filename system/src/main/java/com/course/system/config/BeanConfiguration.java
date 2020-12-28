package com.course.system.config;

import com.course.server.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService getUserService(){
        return new UserService();
    }
}
