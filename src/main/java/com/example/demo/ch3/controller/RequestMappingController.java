package com.example.demo.ch3.controller;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ch3.entity.User;

/**
 * 1、consumes属性指定了： http请求的Content-type必须与consumes相同。
 * "application/json"对应的是http请求的请求头的Content-type必须为"application/json"。
 * 
 * 2、produces属性指定了： http请求的Accept字段，只有匹配得上的方法才能被调用
 * 
 * 3、params属性要求访问时必须带有指定属性，不然无法访问
 * 
 * 4、headers属性要求访问时请求头中需要指定的属性名称及内容
 * 
 * @author Liu
 *
 */
@RestController
public class RequestMappingController {
	/*
	 * 使用consumes属性demo
	 */
	@GetMapping(value = "/consumes/getUser.json", consumes = "application/json") // 该请求通过网页访问会报错，因为类型不支持，报415错误-媒体类型不支持
	public User forJson() {
		return new User();
	}

	/*
	 * 使用produces属性demo
	 */
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // 通过网页访问，网页得到的格式内容为Json
	public User getUser(@PathVariable Long userId, Model model) {
		User user = new User();
		user.setName("张三");
		user.setSex("12");
		return user;
	}
	
	/*
	 * 使用params参数属性demo
	 */
	@PostMapping(path="/update.json",params="action=save")//只支持post请求，同时请求参数中action的值为save。
	public void saveUser(){
		System.out.println("call save!");
	}
	@PostMapping(path="/update.json",params="action=update")//只支持post请求，同时请求参数中action的值为update。
	public void updateUser(){
		System.out.println("call update!");
	}
	@GetMapping(path="/update.html",params="action=update")//只支持get请求，同时请求参数中action的值为update。
	public String updateUser1(){
		return "update exec!";
	}
	/*
	 * 使用headers参数属性demo
	 */
	@GetMapping(path="/update.json",headers="action=update")//只支持get请求，同时在请求头中设置参数action的值为update。
	public String updateUser2(){
		return "update exec!!";
	}
}
