package com.course.system.config;

import com.course.server.service.*;
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

    @Bean
    public RoleUserService getRoleUserService(){
        return new RoleUserService();
    }
}
