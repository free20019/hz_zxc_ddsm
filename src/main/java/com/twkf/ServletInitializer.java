package com.twkf;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/5/6 - 17:03
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HzZxcDdsmApplication.class);
    }
}
