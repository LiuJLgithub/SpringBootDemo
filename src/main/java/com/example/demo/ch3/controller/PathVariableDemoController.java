package com.example.demo.ch3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.ch3.entity.User;

/**
 * Spring 的controller可以接受多种参数：
 * PathVariable,@MatrixVariable
 * @author Liu
 *
 */
@Controller
@RequestMapping("user/{name}")
public class PathVariableDemoController {
	/*
	 * PathVariable使用demo
	 */
	@GetMapping(path="/{sex}/get.json")
	@ResponseBody
	public User getUser(@PathVariable String name,@PathVariable("sex") String sex_rename){//将sex变量对应到sex_rename变量上
		User user=new User();
		user.setName(name);
		user.setSex(sex_rename);
		return user;
	}
	/*
	 * @MatrixVariable使用demo-->目前仍然存在问题
	 */
	@GetMapping(path="/{sex}/get.html")
	@ResponseBody
	public User getUser1(@PathVariable String name,@PathVariable("sex") String sex_rename,
			@MatrixVariable(value="age",pathVar="sex")String age){//将sex变量对应到sex_rename变量上
		User user=new User();
		user.setName(name);
		user.setSex(sex_rename);
		user.setAge(age);
		return user;
	}
	
}
