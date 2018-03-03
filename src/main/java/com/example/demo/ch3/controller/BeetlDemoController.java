package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeetlDemoController {
	@RequestMapping("/userdetail.html")
	public String foo(String id){
		return "/admin/userInfo.btl";
	}
}
