package com.threeAier.app.configuration;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.concurrent.TimeUnit;

/**
 * WebMVC配置，你可以集中在这里配置拦截器、过滤器、静态资源缓存等
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将所有/static/** 访问都映射到classpath:/static/ 目录下
        CacheControl cc = CacheControl.maxAge(30, TimeUnit.HOURS).mustRevalidate();
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
                .setCacheControl(cc);
    }


    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}
