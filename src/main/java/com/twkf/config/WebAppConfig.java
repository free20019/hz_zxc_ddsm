package com.twkf.config;

import com.twkf.filter.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/4/29 - 11:31
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/","/error","/common/login","/common/index","/common/logout"
                ,"/asserts/**","/webjars/**"
                ,"/doc.html/**","/swagger-resources/**","/v2/**"
                ,"/css/**","/fonts/**","/img/**","/js/**","/*.*"
        );
    }

}
