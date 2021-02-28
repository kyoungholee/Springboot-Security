package com.cos.security2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //view를 리턴한다. 
public class IndexController {
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}

}
