package com.gilclei.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HelloController {
	
	@GetMapping
	public String hello() {
		return "<h1>Hello, dev!</h1>";
	}
	
	@GetMapping("/test")
	public String saudacao(@RequestParam(name="nome", defaultValue = "DIO") String nome) {
		return String.format("Bem-vindo, %s", nome);
	}

}
;