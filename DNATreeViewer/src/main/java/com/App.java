package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostMapping("/data")
	public String data(@RequestParam(name = "text", required = true) String text) throws Exception {
		return TreeJSON.getJson(text);
	}
}

