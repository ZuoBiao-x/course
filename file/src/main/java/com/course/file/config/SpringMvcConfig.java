package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot静态资源配置，静态资源包含图片、css、js等
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 在对静态资源进行暴露之后：
     * 在浏览器访问：http://localhost:9003/file/f/teacher/xxx.png
     * 实际请求的地址：http://localhost:9003/file/F:/file/imooc/course/teacher/xxx.png
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").
                addResourceLocations("file:F:/file/imooc/course/");
    }
}