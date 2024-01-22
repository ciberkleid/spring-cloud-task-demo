package com.example.springcloudtaskdemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class Application {

	@Bean
	public ApplicationRunner applicationRunner() {
		return new SpringCloudTaskDemoApplicationRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static class SpringCloudTaskDemoApplicationRunner implements ApplicationRunner {

		@Override
		public void run(ApplicationArguments args) throws Exception {
			System.out.println("Hello, world!");
		}
	}
}
