package com.course.system.config;

import com.course.server.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public TestService getTestService(){
        return new TestService();
    }
}
