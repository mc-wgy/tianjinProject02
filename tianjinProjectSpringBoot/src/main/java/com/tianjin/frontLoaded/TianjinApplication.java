package com.tianjin.frontLoaded;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.tianjin.frontLoaded.mapper")
public class TianjinApplication {
    public static void main(String[] args) {
        SpringApplication.run(TianjinApplication.class);
    }
}
