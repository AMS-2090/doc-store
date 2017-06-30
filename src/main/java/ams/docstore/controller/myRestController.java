package ams.docstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myRestController {
	
	@RequestMapping("/hello")
	public String Hello() {
		return "Hello";
	}
}
