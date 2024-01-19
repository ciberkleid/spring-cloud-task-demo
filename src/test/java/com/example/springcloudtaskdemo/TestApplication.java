package com.example.springcloudtaskdemo;

import org.springframework.boot.SpringApplication;

class TestApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(SpringCloudTaskDemoApplication::main)
				.with(ContainersConfig.class)
				.run(args);
	}

}
