package com.door;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.door.mapper")
public class DoorApplication extends SpringBootServletInitializer 
{
    public static void main( String[] args )
    {
        SpringApplication.run(DoorApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DoorApplication.class);
    }
}
