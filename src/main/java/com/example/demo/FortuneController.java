package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FortuneController {
	@GetMapping("/")
	public String fortune() {
		return"占いを始めましょう";
	}
}
