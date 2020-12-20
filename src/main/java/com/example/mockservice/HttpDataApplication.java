package com.example.mockservice;

import com.jd.security.configsec.spring.config.JDSecurityPropertyCleanService;
import com.jd.security.configsec.spring.config.JDSecurityPropertySourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.example.mockservice.dao")
//@PropertySource(value = "classpath:/important.properties", encoding = "utf-8", factory = JDSecurityPropertySourceFactory.class)
@Import({JDSecurityPropertyCleanService.class})
public class HttpDataApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HttpDataApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HttpDataApplication.class);
    }
}