package com.nb.pachong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.nb.pachong.dao")
@SpringBootApplication
@EnableScheduling
public class PachongApplication {

	public static void main(String[] args) {
		SpringApplication.run(PachongApplication.class, args);
	}

}
