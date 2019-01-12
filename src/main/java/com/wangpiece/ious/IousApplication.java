package com.wangpiece.ious;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangpiece.ious.mapper")
public class IousApplication {

	public static void main(String[] args) {
		SpringApplication.run(IousApplication.class, args);
	}

}

