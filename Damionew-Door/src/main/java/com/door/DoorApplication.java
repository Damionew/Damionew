package com.door;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.door.mapper")
public class DoorApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(DoorApplication.class, args);
    }
}
