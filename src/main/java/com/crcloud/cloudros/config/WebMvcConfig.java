package com.crcloud.cloudros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.annotation.Resource;


//@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Resource
//    private ValidateIntercepter validateIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(validateIntercepter).addPathPatterns("/**").order(2);
        super.addInterceptors(registry);
    }
}
