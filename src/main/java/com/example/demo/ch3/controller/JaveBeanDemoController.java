package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/javaBean")
public class JaveBeanDemoController {
	@GetMapping(path="/getUser")
	@ResponseBody
	public String getUser(String name,Integer age){

		return "姓名："+name+"，年龄："+age;
	}
}
