package com.course.system.config;

import com.course.server.service.ResourceService;
import com.course.server.service.RoleResourceService;
import com.course.server.service.RoleService;
import com.course.server.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService getUserService(){
        return new UserService();
    }

    @Bean
    public ResourceService getResourceService(){
        return new ResourceService();
    }

    @Bean
    public RoleService getRoleService(){
        return new RoleService();
    }

    @Bean
    public RoleResourceService getRoleResourceService(){
        return new RoleResourceService();
    }
}
