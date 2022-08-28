package com.crcloud.cloudros;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.crcloud.cloudros.dao.mapper.**")
@ComponentScan(basePackages = {"com.crcloud.cloudros.*"})
public class CMPApplication {

	public static void main(String[] args) {
		SpringApplication.run(CMPApplication.class, args);
	}

}
