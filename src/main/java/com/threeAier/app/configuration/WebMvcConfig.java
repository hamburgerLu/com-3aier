package com.threeAier.app.configuration;

import com.threeAier.app.framework.LogInterceptor;
import com.threeAier.app.framework.TraceInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
        factory.setMaxFileSize("51200KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("51200KB");
        factory.setLocation("/root/tempUpload/");
        return factory.createMultipartConfig();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器的配置 拦截所有业务操作 如为查询操作可以
        registry.addInterceptor(this.traceInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(this.logIntercept()).addPathPatterns("/**");
    }


    @Bean
    public LogInterceptor logIntercept() {
        return new LogInterceptor();
    }

    @Bean
    public TraceInterceptor traceInterceptor(){
        return new TraceInterceptor();
    }


}
