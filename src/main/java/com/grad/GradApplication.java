package com.grad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@ServletComponentScan
public class GradApplication {
	public static void main(String[] args) {
		SpringApplication.run(GradApplication.class, args);
	}
}
