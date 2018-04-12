package com.grad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@ServletComponentScan
public class GradApplication {
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(GradApplication.class, args);
	}
}
